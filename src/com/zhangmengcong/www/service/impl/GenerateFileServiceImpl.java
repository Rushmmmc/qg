package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.GeneateFileService;
import com.zhangmengcong.www.util.Factory;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/2 18:58
 */
public class GenerateFileServiceImpl implements GeneateFileService {

    @Override
    public void geneateFileService(String username,String path) {
        Factory factory = new Factory();
        List<Indent> list = factory.getPrintIndentService().printIndentService(0,username,IF_SELLER,0);

        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet(username);


        //写入文件
        //创建第一行
        //设置表头
        HSSFRow row = sheet.createRow(0);
        String[] headers = {"订单id","商品名称","购买用户","单价","购买数量","总价","积分抵现","实际付款","商家","状态","评价","详细评价","用户留言","商家回复"};
        // 设置列宽
        for(int i = 0; i < headers.length; i++){
            sheet.setColumnWidth(i, 5000);
        }
        for(int i = 0; i < headers.length; i++){
            row.createCell(i).setCellValue(new HSSFRichTextString(headers[i]));
        }

        if(null != list && list.size() > 0){
            Indent indent;
            //第一行已被表头占用 从第二行开始
            for(int i = 1; i <= list.size(); i++){

                row = sheet.createRow(i);
                indent = list.get(i-1);

                //跳过商家删除的订单
                if(indent.getIfSellerDelete() == 1){
                    continue;
                }

                row.createCell(0).setCellValue(new HSSFRichTextString(String.valueOf(indent.getId())));
                row.createCell(1).setCellValue(new HSSFRichTextString(indent.getGoodsName()));
                row.createCell(2).setCellValue(new HSSFRichTextString(indent.getBuyer()));
                row.createCell(3).setCellValue(new HSSFRichTextString(String.valueOf(indent.getPrice())));
                row.createCell(4).setCellValue(new HSSFRichTextString(String.valueOf(indent.getAmount())));
                row.createCell(5).setCellValue(new HSSFRichTextString(String.valueOf(indent.getTotalPrice())));
                row.createCell(6).setCellValue(new HSSFRichTextString(String.valueOf(indent.getUseIntegral())));
                row.createCell(7).setCellValue(new HSSFRichTextString(String.valueOf(indent.getActuallyPrice())));
                row.createCell(8).setCellValue(new HSSFRichTextString(indent.getSeller()));
                row.createCell(9).setCellValue(new HSSFRichTextString(indent.getStatus()));
                row.createCell(10).setCellValue(new HSSFRichTextString(indent.getReputation()));
                row.createCell(11).setCellValue(new HSSFRichTextString(indent.getEvaluate()));
                row.createCell(12).setCellValue(new HSSFRichTextString(indent.getBuyerMessage()));
                row.createCell(13).setCellValue(new HSSFRichTextString(indent.getSellerMessage()));
            }
        }

        File file = new File(path+"/data/");

        if (!file.exists()) {
            file.mkdirs(); //创建目录
        }
        // 保存到服务器
        try {
            wk.write((new FileOutputStream(new File(path+"/data/" + username + ".xls"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
