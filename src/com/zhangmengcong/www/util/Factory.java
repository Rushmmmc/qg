package com.zhangmengcong.www.util;

import com.zhangmengcong.www.dao.dao.AdminDao.AdminDao;
import com.zhangmengcong.www.dao.dao.goodsdao.GoodsDao;
import com.zhangmengcong.www.dao.dao.printdao.IndentPrintDao;
import com.zhangmengcong.www.dao.impl.printdaoimpl.GoodsPrintDaoImpl;
import com.zhangmengcong.www.dao.dao.printdao.GoodsPrintDao;
import com.zhangmengcong.www.dao.dao.printdao.PrintDao;
import com.zhangmengcong.www.dao.dao.UserDao.UserDao;
import com.zhangmengcong.www.dao.impl.AdminDaoImpl.AdminDaoImpl;
import com.zhangmengcong.www.dao.impl.goodsimpl.GoodsDaoImpl;
import com.zhangmengcong.www.dao.impl.printdaoimpl.IndentPrintDaoImpl;
import com.zhangmengcong.www.dao.impl.printdaoimpl.PrintDaoImpl;
import com.zhangmengcong.www.dao.impl.UserDaoImpl.UserDaoImpl;
import com.zhangmengcong.www.service.impl.changepageserviceimpl.GoToMainpageServiceImpl;
import com.zhangmengcong.www.service.impl.adminimpl.AdminBanOrUnbanUserServiceImpl;
import com.zhangmengcong.www.service.impl.goodsservice.*;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintIndentServiceImpl;
import com.zhangmengcong.www.service.impl.userserviceimpl.*;
import com.zhangmengcong.www.service.service.goodsservice.*;
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
    public SellGoodsToBuyerService getSellGoodsToBuyerService(){return new SellGoodsToBuyerServiceImpl(); }
    public BuyGoodsService getBuyGoodsService(){return new BuyGoodsServiceImpl(); }
    /**
     *  关于跳转页面的服务
     */
    public GoToMainpageService getGoToMainpageService(){
        return new GoToMainpageServiceImpl();
    }
}
