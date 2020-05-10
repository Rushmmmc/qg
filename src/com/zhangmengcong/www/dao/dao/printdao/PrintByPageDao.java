package com.zhangmengcong.www.dao.dao.printdao;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.User;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印各种表格到页面上
 * @date: 2020/4/29 13:47
 */
public interface PrintByPageDao {
    /** 找到模糊查询对象的总数量
     *
     * @param goods 商品对象
     * @return 数据总条数
     */
    int findTotalCount(Goods goods);

    /** 封装冗余代码
     *
     * @param goods 传入的查询信息
     * @return 更新后的list
     */
    List<Object> finallyGetList(Goods goods);

    /** 拼接sql
     *
     * @param sb 拼接的sql
     * @param type   类型对应的值 如goosType对应的 "家用型"
     * @param typeName 类型名 如需要从查询的商品类型:"goodsType"
     * @return 拼接后的sql
     *
     */
    StringBuilder getAppend(StringBuilder sb,String type, String typeName);
    /** 封装冗余代码
     *
     * @param goods 传入的查询信息
     * @param sql sql语句
     * @return 拼接后的sql语句
     */
    StringBuilder finallyAppendSql(Goods goods, String sql);




    /** 获得分页信息
     *
     * @param currentPage 当前页码
     * @param rows 每页条数
     * @param rangeMin 用户输入的价格最小值
     * @param rangeMax 用户输入的价格最大值
     * @param rank 用户选择价格的正序或逆序
     * @param goods 商品对象
     * @return 最终展示的表格
     */
    List<Goods> findByPage(int currentPage, int rows,int rangeMin,int rangeMax,String rank,Goods goods);



}
