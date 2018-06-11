package org.trustnote.activity.utils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.pojo.FeedBack;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel导出工具类
 */
@Service
public class ExcelFeedBackUtils {
    private static final Logger logger = LogManager.getLogger(ExcelFeedBackUtils.class);
    @Resource
    private CosUtil cosUtil;

    public String exportExcel(String fileName, List<String> Title, List<FeedBack> listContent, int type, HttpServletRequest request, HttpServletResponse response) {
        String result="系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            // 设定输出文件头
            response.setContentType("application/msexcel");
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf_center.setAlignment(Alignment.CENTRE);
            wcf_center.setWrap(false);
            //设置单元格颜色
            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN);
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf_left.setAlignment(Alignment.LEFT);
            wcf_left.setWrap(true);

            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.size(); i++) {
                sheet.addCell(new Label(i, 0,Title.get(i),wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            sheet.getSettings().setDefaultColumnWidth(50);
            String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF")
                    + File.separator + "tmp" + File.separator;
            File dir = new File(filePath);
            if (!dir.exists()){ dir.mkdirs();}
            if(listContent!=null) {
                int idx=1;
                for(int i=0;i<listContent.size();i++){
                    sheet.addCell(new Label(0, idx, listContent.get(i).getEmail(), wcf_center));
                    sheet.addCell(new Label(1, idx, listContent.get(i).getContent(), wcf_center));
                    sheet.addCell(new Label(2, idx, listContent.get(i).getCrtTime().toString(), wcf_center));
                    String screenshots = listContent.get(i).getScreenshots();
                    if (StringUtils.isNotBlank(screenshots)) {
                        cosUtil.downFile(request, "/feedback/", screenshots);
                        File file = new File(filePath + screenshots);
                        WritableImage image = new WritableImage(3,idx,120,120,file);
                        sheet.addImage(image);
                    }
                    idx++;
                }
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();
        } catch (Exception e) {
            result="系统提示：Excel文件导出失败，原因："+ e.toString();
            logger.error(result);
        }
        return result;
    }

}