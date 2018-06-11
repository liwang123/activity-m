package org.trustnote.activity.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.trustnote.activity.common.pojo.FeedBack;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel导出工具类
 */
public class ExcelExplorerManagerUtils {
    private static final Logger logger = LogManager.getLogger(ExcelExplorerManagerUtils.class);

    public  final static String exportExcel(String fileName, List<String> Title, JSONArray listContent, int type, HttpServletResponse response) {
        String result="系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            // 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
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
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行
            //设置单元格颜色
            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(true); // 文字是否换行

            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.size(); i++) {
                sheet.addCell(new Label(i, 0,Title.get(i),wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            sheet.getSettings().setDefaultColumnWidth(50);

            if(listContent!=null){
                int idx=1;
                for(int i=0;i<listContent.size();i++){
                    JSONObject job = listContent.getJSONObject(i);
                    sheet.addCell(new Label(0, idx, job.get("address").toString(), wcf_center));
                    sheet.addCell(new Label(1, idx, job.get("amount").toString(), wcf_center));
                    if(job.get("date")!=null){
                        sheet.addCell(new Label(2, idx, job.get("date").toString(), wcf_center));
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