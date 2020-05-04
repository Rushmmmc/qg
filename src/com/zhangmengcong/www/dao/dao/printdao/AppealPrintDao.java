package com.zhangmengcong.www.dao.dao.printdao;

import com.zhangmengcong.www.po.Appeal;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印所有申诉列表
 * @date: 2020/5/3 19:02
 */
public interface AppealPrintDao {
   /** 打印所有申诉列表
    * @param ifAll 是否使用打印全部申诉表格 仅管理员使用
    * @param username 打印具体某个用户的申诉信息
    * @return 申诉列表
    */
   List<Appeal> appealPrintDao(boolean ifAll,String username);
}
