package org.trustnote.activity.common.utils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 */
public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    public final static String exportExcel(final String fileName, final List<String> Title, final List<Map<String, String>> listContent, final int type, final HttpServletResponse response) {
        final String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            final OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            // 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            final WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            final WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            final jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            final WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            final WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            final WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行
            //设置单元格颜色
            wcf_center.setBackground(jxl.format.Colour.RED);
            // 用于正文居左
            final WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行

            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.size(); i++) {
                sheet.addCell(new Label(i, 0,Title.get(i),wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            sheet.getSettings().setDefaultColumnWidth(30);
            final int[] sequence = new int[6];
            if (type == 1) {
                sequence[0] = 0;sequence[1]=4;sequence[2]=1;sequence[3]=2;sequence[4]=3;
            }else if (type == 2) {
                sequence[0] = 0;sequence[1]=4;sequence[2]=1;sequence[3]=2;sequence[4]=3;sequence[5]=5;
            } else if (type == 3) {
                sequence[0] = 0;
                sequence[1] = 1;
                sequence[2] = 2;
                sequence[3] = 3;
                sequence[4] = 4;
                sequence[5] = 5;
            } else if (type == 4) {
                sequence[0] = 0;
                sequence[1] = 1;
            }
            int i = 1;
            for (final Map<String, String> map : listContent) {
                int idx = 0;
                for (final Map.Entry entry : map.entrySet()) {
                    sheet.addCell(new Label(sequence[idx], i, entry.getValue().toString(), wcf_left));
                    idx++;
                }
                i++;
            }

            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();
        } catch (final Exception e) {
            ExcelUtils.logger.error("系统提示：Excel文件导出失败，原因： {}", e);
        }
        return result;
    }

}