package com.zhangmengcong.www.util;

import com.zhangmengcong.www.dao.dao.admindao.AdminDao;
import com.zhangmengcong.www.dao.dao.appealdao.GenerateAppealDao;
import com.zhangmengcong.www.dao.dao.goodsdao.GoodsDao;
import com.zhangmengcong.www.dao.dao.goodsdao.SelectGoodsByInterest;
import com.zhangmengcong.www.dao.dao.indentdao.IndentDao;
import com.zhangmengcong.www.dao.dao.message.AddMessageDao;
import com.zhangmengcong.www.dao.dao.packagingdao.*;
import com.zhangmengcong.www.dao.dao.printdao.*;
import com.zhangmengcong.www.dao.impl.appealdao.GenerateAppealDaoImpl;
import com.zhangmengcong.www.dao.impl.goodsimpl.SelectGoodsByInterestImpl;
import com.zhangmengcong.www.dao.impl.indentdaoimpl.IndentDaoImpl;
import com.zhangmengcong.www.dao.impl.messageimpl.AddMessageDaoImpl;
import com.zhangmengcong.www.dao.impl.packagingdaoimpl.*;
import com.zhangmengcong.www.dao.impl.printdaoimpl.*;
import com.zhangmengcong.www.dao.dao.userdao.UserDao;
import com.zhangmengcong.www.dao.impl.admindaoimpl.AdminDaoImpl;
import com.zhangmengcong.www.dao.impl.goodsimpl.GoodsDaoImpl;
import com.zhangmengcong.www.dao.impl.userdaoimpl.UserDaoImpl;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.service.impl.formatserviceimpl.FormatServiceImpl;
import com.zhangmengcong.www.service.impl.messageserviceimpl.AddMessageServiceImpl;
import com.zhangmengcong.www.service.impl.messageserviceimpl.DeleteMessageServiceImpl;
import com.zhangmengcong.www.service.impl.messageserviceimpl.ReplyMessageServicImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintAppealServiceImpl;
import com.zhangmengcong.www.service.impl.adminimpl.ManageAppealServiceImpl;
import com.zhangmengcong.www.service.impl.changepageserviceimpl.GoToMainpageServiceImpl;
import com.zhangmengcong.www.service.impl.adminimpl.AdminBanOrUnbanUserServiceImpl;
import com.zhangmengcong.www.service.impl.generratefileserviceimpl.GenerateFileServiceImpl;
import com.zhangmengcong.www.service.impl.goodsserviceimpl.*;
import com.zhangmengcong.www.service.impl.indentserviceimpl.IndentServiceImpl;
import com.zhangmengcong.www.service.impl.indentserviceimpl.IntegralServiceImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintIndentServiceImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintMessageServiceImpl;
import com.zhangmengcong.www.service.impl.userserviceimpl.*;
import com.zhangmengcong.www.service.service.adminservice.ManageAppealService;
import com.zhangmengcong.www.service.service.formatservice.FormatService;
import com.zhangmengcong.www.service.service.generatefileservice.GeneateFileService;
import com.zhangmengcong.www.service.service.goodsservice.*;
import com.zhangmengcong.www.service.service.indentservice.IndentService;
import com.zhangmengcong.www.service.service.indentservice.IntegralService;
import com.zhangmengcong.www.service.service.messageservice.AddMessageService;
import com.zhangmengcong.www.service.service.messageservice.DeleteMessageService;
import com.zhangmengcong.www.service.service.messageservice.ReplyMessageService;
import com.zhangmengcong.www.service.service.pageservice.GoToMainpageService;
import com.zhangmengcong.www.service.service.adminservice.AdminBanOrUnbanUserService;
import com.zhangmengcong.www.service.service.adminservice.BecomeAdminService;
import com.zhangmengcong.www.service.service.printtableservice.PrintAppealService;
import com.zhangmengcong.www.service.service.printtableservice.PrintIndentService;
import com.zhangmengcong.www.service.service.printtableservice.PrintMessageService;
import com.zhangmengcong.www.service.service.printtableservice.PrintTableService;
import com.zhangmengcong.www.service.impl.adminimpl.BecomeAdminServiceImpl;
import com.zhangmengcong.www.service.impl.printtableserviceimpl.PrintTableServiceImpl;
import com.zhangmengcong.www.service.service.userservice.*;

import java.util.Objects;


/**
 * @author:zmc
 * @function: 工厂类
 * @date: 2020/4/29 9:29
 */
public class Factory {
    /**
     * 关于dao层封装方法
     */
    public DeleteDao getDeleteDao(){return new DeleteDaoImpl();}
    public UpdateDao getUpdateDao(){return new UpdateDaoImpl();}
    public DeleteOrChangeDao getDeleteOrChangeDao(){return new DeleteOrChangeDaoImpl(); }
    public QueryDao getQueryDao(){return new QueryDaoImpl(); }
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
    public IntegralService getIntegralService() {return new IntegralServiceImpl();}
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
    public ManageAppealService getManageAppealService(){return new ManageAppealServiceImpl();
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
    public GetGoodsParametersDao getGoodsParametersDao(){return new GetGoodsParametersDaoImpl(); }
    public SelectGoodsByInterest getSelectGoodsByInterest(){return new SelectGoodsByInterestImpl(); }
    public GetPriceAndGoodsNameService getGetPriceAndGoodsNameService() {return new GetPriceAndGoodsNameServiceImpl();}
    public GoodsRecommendService getGoodsRecommendService(){return new GoodsRecommendServiceImpl();}
    public UpdateGoodsPhotoService getUpdateGoodsPhotoService(){return new UpdateGoodsPhotoServiceImpl();}
    /**
     *  关于订单的服务
     */
    public IndentDao getIndentDao(){return new IndentDaoImpl();}
    public IndentService getIndentService(){return new IndentServiceImpl();}
    public GeneateFileService getGeneateFileService(){return new GenerateFileServiceImpl();
    }
    /**
     *  关于跳转页面的服务
     */
    public GoToMainpageService getGoToMainpageService(){
        return new GoToMainpageServiceImpl();
    }
    /**
     *  关于申诉的服务
     */
    public AppealPrintDao getAppealPrintDao(){return new AppealPrintDaoImpl();}
    public PrintAppealService getPrintAppealService(){return new PrintAppealServiceImpl(); }
    public GenerateAppealDao getGenerateAppealDao(){return new GenerateAppealDaoImpl();}
    public GenerateAppealService getGenerateAppealService(){return new GenerateAppealServiceImpl();}
    /**
     *  关于数据检验的服务
     */
    public FormatService getFormatService(){return new FormatServiceImpl();}
    /**
     *  关于用户和商家的互相留言的服务
     */
    public MessagePrintDao getMessagePrintDao(){return new MessagePrintDaoImpl(); }
    public PrintMessageService getPrintMessageService(){return new PrintMessageServiceImpl();}
    public AddMessageDao getAddMessageDao() {return new AddMessageDaoImpl();}
    public AddMessageService getAddMessageService(){return new AddMessageServiceImpl();}
    public ReplyMessageService getReplyMessageService(){return new ReplyMessageServicImpl();}
    public DeleteMessageService getDeleteMessageService(){return  new DeleteMessageServiceImpl();}
}
