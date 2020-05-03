package com.zhangmengcong.www.util;

import com.zhangmengcong.www.dao.dao.admindao.AdminDao;
import com.zhangmengcong.www.dao.dao.goodsdao.GoodsDao;
import com.zhangmengcong.www.dao.dao.indentdao.IndentDao;
import com.zhangmengcong.www.dao.dao.packagingdao.DeleteOrChangeDao;
import com.zhangmengcong.www.dao.dao.printdao.IndentPrintDao;
import com.zhangmengcong.www.dao.impl.indentdaoimpl.IndentDaoImpl;
import com.zhangmengcong.www.dao.impl.packagingdaoimpl.DeleteOrChangeDaoImpl;
import com.zhangmengcong.www.dao.impl.printdaoimpl.GoodsPrintDaoImpl;
import com.zhangmengcong.www.dao.dao.printdao.GoodsPrintDao;
import com.zhangmengcong.www.dao.dao.printdao.PrintDao;
import com.zhangmengcong.www.dao.dao.userdao.UserDao;
import com.zhangmengcong.www.dao.impl.admindaoimpl.AdminDaoImpl;
import com.zhangmengcong.www.dao.impl.goodsimpl.GoodsDaoImpl;
import com.zhangmengcong.www.dao.impl.printdaoimpl.IndentPrintDaoImpl;
import com.zhangmengcong.www.dao.impl.printdaoimpl.PrintDaoImpl;
import com.zhangmengcong.www.dao.impl.userdaoimpl.UserDaoImpl;
import com.zhangmengcong.www.service.impl.changepageserviceimpl.GoToMainpageServiceImpl;
import com.zhangmengcong.www.service.impl.adminimpl.AdminBanOrUnbanUserServiceImpl;
import com.zhangmengcong.www.service.impl.generratefileserviceimpl.GenerateFileServiceImpl;
import com.zhangmengcong.www.service.impl.goodsserviceimpl.*;
import com.zhangmengcong.www.service.impl.indentserviceimpl.IndentServiceImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintIndentServiceImpl;
import com.zhangmengcong.www.service.impl.userserviceimpl.*;
import com.zhangmengcong.www.service.service.generatefileservice.GeneateFileService;
import com.zhangmengcong.www.service.service.goodsservice.*;
import com.zhangmengcong.www.service.service.indentservice.IndentService;
import com.zhangmengcong.www.service.service.pageservice.GoToMainpageService;
import com.zhangmengcong.www.service.service.adminservice.AdminBanOrUnbanUserService;
import com.zhangmengcong.www.service.service.adminservice.BecomeAdminService;
import com.zhangmengcong.www.service.service.printtableservice.PrintIndentService;
import com.zhangmengcong.www.service.service.printtableservice.PrintTableService;
import com.zhangmengcong.www.service.impl.adminimpl.BecomeAdminServiceImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintTableServiceImpl;
import com.zhangmengcong.www.service.service.userservice.*;

/**
 * @author:zmc
 * @function: 工厂类
 * @date: 2020/4/29 9:29
 */
public class Factory {
    /**
     * 关于dao层封装方法
     */
    public DeleteOrChangeDao getDeleteOrChangeDao(){return new DeleteOrChangeDaoImpl(); }
    /**
     * 关于用户服务
     */
    public  UserDao getUserDao() {
        return new UserDaoImpl();
    }
    public  RegisterOrLoginService getRegisterAndLogin() {
        return new RegisterOrLoginServiceImpl();
    }
    public  Encode getEncode() {
        return new Encode();
    }
    public  EstimateStatusService getEstimateStatus() {
        return new EstimateStatusServiceImpl();
    }
    public  ChangeMessageService getChangeMessageService(){
        return new ChangeMessageServiceImpl();
    }
    public  GetLevelService getLevelService(){
        return new GetLevelServiceImpl();
    }
    public  ForgetPasswordService getForgetPasswordService() {return new ForgetPasswordServiceImpl(); }
    public CheckIfUserBeBanedService getCheckIfUserBeBanedService() {return new CheckIfUserBeBanedServiceImpl();}
    public AddExpAndIntegralService getAddExpAndIntegralService(){return new AddExpAndIntegralServiceImpl();}
    /**
     * 关于打印表格的服务
     */
    public  PrintDao getPrintDao(){
        return new PrintDaoImpl();
    }
    public  PrintTableService getPrintTableService(){
        return new PrintTableServiceImpl();
    }
    public GoodsPrintDao getGoodsPrintDao(){ return new GoodsPrintDaoImpl(); }
    public  GoodsPrintService getGoodsPrintService() {return new GoodsPrintServiceImpl(); }
    public IndentPrintDao getIndentPrintDao() {return  new IndentPrintDaoImpl(); }
    public PrintIndentService getPrintIndentService(){return new PrintIndentServiceImpl();
    }
    /**
     * 关于管理员的服务
     */
    public  AdminDao getAdminDao(){
        return new AdminDaoImpl();
    }
    public  BecomeAdminService getBecomeAdminService(){
        return new BecomeAdminServiceImpl();
    }
    public AdminBanOrUnbanUserService getAdminBanOrUnbanUserService(){return new AdminBanOrUnbanUserServiceImpl();
    }
    /**
     * 有关商品的服务
     */
    public  GoodsDao getGoodsDao(){
        return new GoodsDaoImpl();
    }
    public  AddGoodsService getAddGoodsService(){
        return new AddGoodsServiceImpl();
    }
    public DeleteOrPassGoodsService getDeleteOrPassGoodsService(){return new DeleteOrPassGoodsServiceImpl();}
    public BuyGoodsService getBuyGoodsService(){return new BuyGoodsServiceImpl(); }
    /**
     *  关于订单的服务
     */
    public IndentDao getIndentDao(){return new IndentDaoImpl();}
    public IndentService getIndentService(){return new IndentServiceImpl();}
    //打印订单
    public GeneateFileService getGeneateFileService(){return new GenerateFileServiceImpl();
    }
    /**
     *  关于跳转页面的服务
     */
    public GoToMainpageService getGoToMainpageService(){
        return new GoToMainpageServiceImpl();
    }

}
