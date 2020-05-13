package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.DividePageParameter;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.PageBean;
import com.zhangmengcong.www.service.service.PrintTableByPageService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印各种表单的实现类
 * @date: 2020/4/29 15:55
 */
public class PrintTableByPageServiceImpl implements PrintTableByPageService {


    @Override
    public PageBean<Goods> byPage(DividePageParameter dividePageParameter) {
        //创建工厂对象
        Factory factory = new Factory();
        String tempCurrentPage = dividePageParameter.getCurrentPage();
        String tempRows = dividePageParameter.getRows();
        String tempRangeMax = dividePageParameter.getRangeMax();
        String tempRangeMin = dividePageParameter.getRangeMin();
        Goods goods = dividePageParameter.getGoods();
        String rank = dividePageParameter.getRank();
        if(tempCurrentPage == null ){
            tempCurrentPage = "1";
            tempRows = "3";
        }
        if(tempRangeMax == null || tempRangeMin == null || "".equals(tempRangeMax) || "".equals(tempRangeMin)){
            tempRangeMax = "0";
            tempRangeMin = "0";
        }
        int rangeMin = Integer.parseInt(tempRangeMin);
        int rangeMax = Integer.parseInt(tempRangeMax);
        if(rangeMax < rangeMin){
            rangeMax = rangeMin = 0;
        }

        int currentPage = Integer.parseInt(tempCurrentPage);
        int rows = Integer.parseInt(tempRows);
        //创建空的PageBean对象
        PageBean<Goods> pb = new PageBean<>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = factory.getPrintByPageDao().findTotalCount(goods);
        pb.setTotalCount(totalCount);
        //调用list集合
        //计算开始记录的索引
        int start = (currentPage - 1) *rows;
        List<Goods> list = factory.getPrintByPageDao().findByPage(start,rows,rangeMin,rangeMax,rank,goods);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount % rows  == 0)? (totalCount/rows) :(totalCount/rows+1);
        pb.setTotalPage(totalPage);
        return pb;
    }


}