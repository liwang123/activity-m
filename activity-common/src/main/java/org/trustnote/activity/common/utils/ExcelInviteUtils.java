package org.trustnote.activity.common.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.trustnote.activity.common.pojo.InviteRecord;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 */
public class ExcelInviteUtils {
    private static final Logger logger = LogManager.getLogger(ExcelInviteUtils.class);

    public static List readExcel(String filePath) throws IOException, BiffException {
        List list = new ArrayList();
        //创建输入流
        InputStream stream = new FileInputStream(filePath);
        //获取Excel文件对象
        Workbook  rwb = Workbook.getWorkbook(stream);
        //获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(0);
        //行数(表头的目录不需要，从1开始)
        for(int i=1; i<sheet.getRows(); i++){
            //创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];
            Cell cell = null;
            //列数
            for(int j=0; j<sheet.getColumns(); j++){
                //获取第i行，第j列的值
                cell = sheet.getCell(j,i);
                str[j] = cell.getContents();
            }
            //把刚获取的列存入list
            list.add(str);
        }
        rwb.close();
        stream.close();
        return list;
    }
    private void outData(List list){
        for(int i=0;i<list.size();i++){
            String[] str = (String[])list.get(i);
            for(int j=0;j<str.length;j++){
                System.out.print(str[j]+'\t');
            }
            System.out.println();
        }
    }

    public  final static String exportExcel(String fileName, List<String> Title, List<InviteRecord> listContent, int type, HttpServletResponse response) {
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
            wcf_center.setBackground(jxl.format.Colour.RED);//设置单元格颜色
            // 用于正文居左
//            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
//            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
//            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
//            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
//            wcf_left.setWrap(false); // 文字是否换行
            WritableCellFormat wcf_left = new WritableCellFormat(BoldFont);
            wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行

            WritableCellFormat wcf = new WritableCellFormat(BoldFont);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf.setWrap(false); // 文字是否换行

            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.size(); i++) {
                sheet.addCell(new Label(i, 0,Title.get(i),wcf));
            }
            /** ***************以下是EXCEL正文数据********************* */
            sheet.getSettings().setDefaultColumnWidth(40);
            if(listContent!=null) {
                int idx = 1;
                    for (int i = 0; i < listContent.size(); i++) {
                        if (listContent.get(i).getId() == -1) {
                            sheet.addCell(new Label(0, idx, listContent.get(i).getTrustnoteAddress(), wcf_center));
                            sheet.addCell(new Label(1, idx, listContent.get(i).getMobilePhone(), wcf_center));
                            sheet.addCell(new Label(2, idx, listContent.get(i).getInviteCode(), wcf_center));
                            sheet.addCell(new Label(3, idx, listContent.get(i).getRewardTtn().toString(), wcf_center));
                            sheet.addCell(new Label(4, idx, listContent.get(i).getInviteSeveral().toString(), wcf_center));
                            if(listContent.get(i).getCrtTime()==null){
                                sheet.addCell(new Label(5, idx, null, wcf_center));
                            }else {
                                sheet.addCell(new Label(5, idx, listContent.get(i).getCrtTime().toString().replaceAll("T", " "), wcf_center));
                            }
                        }else {
                            sheet.addCell(new Label(0, idx, listContent.get(i).getTrustnoteAddress(), wcf_left));
                            sheet.addCell(new Label(1, idx, listContent.get(i).getMobilePhone(), wcf_left));
                            sheet.addCell(new Label(2, idx, listContent.get(i).getInviteCode(), wcf_left));
                            sheet.addCell(new Label(3, idx, listContent.get(i).getRewardTtn().toString(), wcf_left));
                            sheet.addCell(new Label(4, idx, listContent.get(i).getInviteSeveral().toString(), wcf_left));
                            if(listContent.get(i).getCrtTime()==null){
                                sheet.addCell(new Label(5, idx, null, wcf_left));
                            }else {
                                sheet.addCell(new Label(5, idx, listContent.get(i).getCrtTime().toString().replaceAll("T", " "), wcf_left));
                            }
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