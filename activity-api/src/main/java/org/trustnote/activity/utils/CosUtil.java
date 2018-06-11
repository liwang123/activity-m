package org.trustnote.activity.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.trustnote.activity.common.enume.ImageTypeEnum;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author zhuxl
 */
@Service
public class CosUtil {
    private static final Logger logger = LogManager.getLogger(CosUtil.class);
    @Value("${APP_ID}")
    private String appId;
    @Value("${SECRET_ID}")
    private String secretId;
    @Value("${SECRET_KEY}")
    private String secretKey;
    @Value("${REGION}")
    private String region;
    @Value("${BUCKET_NAME}")
    private String bucketName;
    @Value("${TENCENT_URL}")
    private String tencentUrl;

    private COSClient init() {
        final COSCredentials cred = new BasicCOSCredentials(this.secretId, this.secretKey);
        final ClientConfig clientConfig = new ClientConfig(new Region(this.region));
        return new COSClient(cred, clientConfig);
    }

    private void close(final COSClient cosClient) {
        if (cosClient != null) {
            cosClient.shutdown();
        }
    }

    public Result uploadFile(final HttpServletRequest request, final String typePath,
                             final boolean isImage,
                             final Integer imageWidth, final Integer imageHeight, final Long imageSize) throws Exception {
        final Result result = new Result();
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!commonsMultipartResolver.isMultipart(request)) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg());
            return result;
        }
        final MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        final Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        while (iterator.hasNext()) {
            final MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if (multipartFile == null) {
                continue;
            }
            final String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isBlank(fileName)) {
                continue;
            }
            if (isImage) {
                final byte[] bytes = new byte[4];
                multipartFile.getInputStream().read(bytes, 0, bytes.length);
                final String imageType = this.bytesToHexString(bytes).toUpperCase();
                final ImageTypeEnum imageTypeEnum = ImageTypeEnum.getItem(imageType);
                if (imageTypeEnum == null) {
                    result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                    result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 图片格式必须为png,jpg,bpm");
                    return result;
                }
                final BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
                if (bufferedImage != null) {
                    if (multipartFile.getSize() > imageSize) {
                        result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                        result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + "大小超出限制");
                        return result;
                    }
                    if (imageHeight != null && imageWidth != null) {
                        if (bufferedImage.getWidth() > imageWidth || bufferedImage.getHeight() > imageHeight) {
                            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 像素大小不能超出" + imageHeight + "*" + imageWidth);
                            return result;
                        }
                    }
                } else {
                    result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                    result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + "请上传图片");
                    return result;
                }
            }
            CosUtil.logger.info("uploadFile: file original name: {}", fileName);
            final String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF")
                    + File.separator + "tmp" + File.separator;
            CosUtil.logger.info("uploadFile: file upload path: {}", filePath);
            final String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            CosUtil.logger.info("uploadFile: file new name: {}", newFileName);
            final File localFile = new File(filePath, newFileName);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            multipartFile.transferTo(localFile);
            final boolean isSucceed = this.uploadFile(filePath, newFileName, typePath);
            if (isSucceed) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(newFileName);

                localFile.delete();

                return result;
            }else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                return result;
            }
        }
        return result;
    }

    private boolean uploadFile(final String filePath, final String fileName, final String typePath) {
        CosUtil.logger.info("PutObjectRequest typePath: {}", typePath);
        CosUtil.logger.info("PutObjectRequest bucket: {}", new StringBuilder(this.bucketName).append("-").append(this.appId).toString());
        CosUtil.logger.info("PutObjectRequest key: {}", typePath + fileName);
        CosUtil.logger.info("PutOBjectRequest file: {}", filePath + fileName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(new StringBuilder(this.bucketName).append("-").append(this.appId).toString(), typePath + fileName, new File(filePath + fileName));
        final COSClient cosClient = this.init();
        final PutObjectResult putObjectResult;
        try {
            putObjectResult = cosClient.putObject(putObjectRequest);
            if (StringUtils.isNotBlank(putObjectResult.getETag())) {
                this.close(cosClient);
                return true;
            }
        } catch (final CosClientException cle) {
            CosUtil.logger.error("cos put error: {}", cle);
            this.close(cosClient);
            return false;
        }
        return false;
    }

    public boolean deleteFile(final String typePath, final String fileName) {
        CosUtil.logger.info("deleteObject typePath: {}", typePath);
        CosUtil.logger.info("deleteObject fileName: {}", fileName);
        final COSClient cosClient = this.init();
        try {
            cosClient.deleteObject(new StringBuilder(this.bucketName).append("-").append(this.appId).toString(), typePath + fileName);
            this.close(cosClient);
            return true;
        } catch (final CosClientException cle) {
            CosUtil.logger.error("cos delete error: {}", cle);
            this.close(cosClient);
            return false;
        }
    }

    public boolean downFile(final HttpServletRequest request, final String typePath, final String fileName) {
        final File downFile = new File(request.getSession().getServletContext().getRealPath("/WEB-INF")
                + File.separator + "tmp" + File.separator + fileName);
        final COSClient cosClient = this.init();
        try {
            final GetObjectRequest getObjectRequest = new GetObjectRequest(new StringBuilder(this.bucketName).append("-").append(this.appId).toString(), typePath + fileName);
            final ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
            if (StringUtils.isNotBlank(downObjectMeta.getETag())) {
                this.close(cosClient);
                return true;
            }
        } catch (final CosClientException cle) {
            CosUtil.logger.error("cos delete error: {}", cle);
            this.close(cosClient);
            return false;
        }
        return false;
    }

    private String bytesToHexString(final byte[] src) {
        final StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            final int v = src[i] & 0xFF;
            final String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
