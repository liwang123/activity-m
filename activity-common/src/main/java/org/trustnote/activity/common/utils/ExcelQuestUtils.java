package org.trustnote.activity.common.utils;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.trustnote.activity.common.pojo.Questionnaire;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 */
public class ExcelQuestUtils {
    private static final Logger logger = LogManager.getLogger(ExcelQuestUtils.class);

    public  final static String exportExcel(String fileName, List<String> Title, List<Questionnaire> listContent, int type, HttpServletResponse response) {
        String result="系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        String[] job={"各级党政机关及企事业单位领导","专业技术人员","普通职员","销售及商务人员","服务人员","农林牧渔劳动者","产业工人","私营企业主","学生","其它"};
        String[] balance={"10万以下","10万-50万","50万-100万","100万-500万","500万-1000万"};
        String[] education={"高中及以下","专科","本科","硕士","博士及以上"};
        String[] tactics={"日交易量排名前5交易平台：上交易平台成本较高，可能导致后续运营资金缩减，排队时间较长 (预计2018年6月)","日交易量排名前50交易平台：上交易平台成本较低，排队时间较短 (预计2018年4月初)","日交易量排名前15交易平台：上交易平台成本较低，排队时间较长 (预计2018年5月初)"};
        String[] amount={"1~3家","4~10家"};
        String[] platform={"币安/","OKEX/","火币/","Bitfinex/","Bittex/","HitBtc/","CoinEgg/","Bit-z/","CoinBene/","Btctrade/","其他"};
        String[] str={"a","b","c","d","e","f","g","h"};
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
            if(listContent!=null) {
                int idx = 1;
                for (int i = 0; i < listContent.size(); i++) {
                    sheet.addCell(new Label(0, idx, listContent.get(i).getAddress(), wcf_left));
                    for(int j = 0; j <5; j++){
                        if(listContent.get(i).getBalance().equals(str[j])){
                            sheet.addCell(new Label(1, idx, balance[j], wcf_left));
                        }
                    }
                    sheet.addCell(new Label(2, idx, job[Integer.parseInt(listContent.get(i).getJob())-1], wcf_left));

                    if(listContent.get(i).getEducation()!=null){
                    sheet.addCell(new Label(3, idx, education[listContent.get(i).getEducation()-1], wcf_left));
                    }
                    if(listContent.get(i).getImmediately()!=null){
                        if(listContent.get(i).getImmediately()){
                            sheet.addCell(new Label(4, idx, "是", wcf_left));
                        }else {
                            sheet.addCell(new Label(4, idx, "否", wcf_left));
                        }
                    }
                    if(listContent.get(i).getTactics()!=null) {
                        sheet.addCell(new Label(5, idx, listContent.get(i).getTactics(), wcf_left));
                    }
                    if(listContent.get(i).getAmount()!=null){
                        if(listContent.get(i).getAmount().equals(str[0])){
                            sheet.addCell(new Label(6, idx, amount[0], wcf_left));
                        }else {
                            sheet.addCell(new Label(6, idx, amount[1], wcf_left));
                        }
                    }
                    if(listContent.get(i).getPlatform()!=null){
                        String value="";
                        String platform1 = listContent.get(i).getPlatform();
                        String[] split = platform1.split("/");
                        for(int j = 0; j <split.length; j++){
                            value=value+platform[Integer.parseInt(split[j])-1];
                        }
                        sheet.addCell(new Label(7, idx, value, wcf_left));
                    }
                    if(listContent.get(i).getSubTime()!=null){
                        sheet.addCell(new Label(8, idx, listContent.get(i).getSubTime().toString().replaceAll("T", " "), wcf_left));
                    }
                    sheet.addCell(new Label(9, idx, listContent.get(i).getSuggest(), wcf_left));
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