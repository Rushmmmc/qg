package com.zhangmengcong.www.util;

import com.zhangmengcong.www.dao.dao.GenerateAppealDao;
import com.zhangmengcong.www.dao.dao.*;
import com.zhangmengcong.www.dao.dao.GenerateIndentDao;
import com.zhangmengcong.www.dao.dao.AddNewMessageDao;
import com.zhangmengcong.www.dao.dao.CheckIfReplyIdExistDao;
import com.zhangmengcong.www.dao.impl.*;
import com.zhangmengcong.www.dao.impl.GenerateIndentDaoImpl;
import com.zhangmengcong.www.dao.impl.AddNewMessageDaoImpl;
import com.zhangmengcong.www.dao.impl.CheckIfReplyIdExistDaoImpl;
import com.zhangmengcong.www.service.impl.*;
import com.zhangmengcong.www.service.impl.AddMessageServiceImpl;
import com.zhangmengcong.www.service.impl.DeleteMessageServiceImpl;
import com.zhangmengcong.www.service.impl.ReplyMessageServicImpl;
import com.zhangmengcong.www.service.service.*;
import com.zhangmengcong.www.service.service.AddMessageService;
import com.zhangmengcong.www.service.service.DeleteMessageService;
import com.zhangmengcong.www.service.service.ReplyMessageService;
import com.zhangmengcong.www.service.service.GoToMainpageService;


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
    public UserApplySalesReturnService getUserApplySalesReturnService(){return new UserApplySalesReturnServiceImpl();}
    public RegisterDao getRegisterDao() {return new RegisterDaoImpl();}
    public CheckNameAndPasswordDao getCheckNameAndPasswordDao() {return new CheckNameAndPasswordDaoImpl(); }
    public UserChangeMessageDao getUserChangeMessageDao() {return new UserChangeMessageDaoImpl(); }
    public IfMailExistService getIfMailExistService(){return new IfMailExistServiceImpl();}
    /**
     * 关于打印表格的服务
     */
    public PrintByPageDao getPrintByPageDao(){
        return new PrintByPageDaoImpl();
    }
    public PrintTableByPageService getPrintTableService(){
        return new PrintTableByPageServiceImpl();
    }
    public GoodsPrintDao getGoodsPrintDao(){ return new GoodsPrintDaoImpl(); }
    public  GoodsPrintService getGoodsPrintService() {return new GoodsPrintServiceImpl(); }
    public IndentPrintDao getIndentPrintDao() {return  new IndentPrintDaoImpl(); }
    public PrintIndentService getPrintIndentService(){return new PrintIndentServiceImpl();}
    public  SelectPersonalMessageService getSelectPersonalMessageService(){return new SelectPersonalMessageServiceImpl();}
    public SelectPersonalMessageDao getSelectPersonalMessageDao(){return new SelectPersonalMessageDaoImpl();}
    /**
     * 关于管理员的服务
     */
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
    public AddGoodsDao getAddGoodsDao(){
        return new AddGoodsDaoImpl();
    }
    public AddGoodsService getAddGoodsService(){
        return new AddGoodsServiceImpl();
    }
    public DeleteOrPassGoodsService getDeleteOrPassGoodsService(){return new DeleteOrPassGoodsServiceImpl();}
    public BuyGoodsService getBuyGoodsService(){return new BuyGoodsServiceImpl(); }
    public GetGoodsParametersDao getGoodsParametersDao(){return new GetGoodsParametersDaoImpl(); }
    public SelectGoodsByInterest getSelectGoodsByInterest(){return new SelectGoodsByInterestImpl(); }
    public GetPriceAndGoodsNameService getGetPriceAndGoodsNameService() {return new GetPriceAndGoodsNameServiceImpl();}
    public GoodsRecommendService getGoodsRecommendService(){return new GoodsRecommendServiceImpl();}
    public ChangeSellerAllGoodsReputationDao getChangeSellerAllGoodsReputationDao(){return new ChangeSellerAllGoodsReputationDaoImpl(); }
    public CheckIfGoodsInShoppingCarDao getCheckIfGoodsInShoppingCarDao(){return new CheckIfGoodsInShoppingCarDaoImpl(); }
    public CheckUserIfTheGoodsSellerService getCheckUserIfTheGoodsSellerService(){return new CheckUserIfTheGoodsSellerServiceImpl();}
    public CheckIfUserExistGoodsDao getCheckIfUserExistGoodsDao(){return new CheckIfUserExistGoodsDaoImpl();}
    public CheckIfUserExistGoodsService getCheckIfUserExistGoodsService(){return new CheckIfUserExistGoodsServiceImpl();}
    public ChangeGoodsMessageService getChangeGoodsMessageService(){return new ChangeGoodsMessageServiceImpl();}
    public BanOrUnbanGoodsService getBanOrUnbanGoodsService(){return new BanOrUnbanGoodsServiceImpl();}
    /**
     *  关于订单的服务
     */
    public GenerateIndentDao getGenerateIndentDao(){return new GenerateIndentDaoImpl();}
    public DeleteIndentService getDeleteIndentService(){return new DeleteIndentServiceImpl();}
    public GeneateFileService getGenerateFileService(){return new GenerateFileServiceImpl();}
    public GiveUpIndentService getGiveUpIndentService(){return new GiveUpIndentServiceImpl(); }
    public SellerChangeIndentService getSellerChangeIndentService(){return new SellerChangeIndentServiceImpl();}
    public FinishIndentService getFinishIndentService(){return new FinishIndentServiceImpl();}
    public UserEvaluateIndentService getUserEvaluateIndentService(){return new UserEvaluateIndentServiceImpl();}
    public UpdateGoodsPhotoService getUpdateGoodsPhotoService(){return new UpdateGoodsPhotoServiceImpl();}
    public SellerSendGoodsService getSellerSendGoodsService(){return new SellerSendGoodsServiceImpl();}
    public DeleteShoppingCarIndentService getDeleteShoppingCarIndentService() {return new DeleteShoppingCarIndentServiceImpl();}
    public GiveGoodsReputationService getGiveGoodsReputationService(){return new GiveGoodsReputationServiceImpl();}
    public RejectSellGoodsService getRejectSellGoodsService(){return new RejectSellGoodsServiceImpl();}
    public BuyGoodsFromShoppingCarService getBuyGoodsFromShoppingCarService(){return new BuyGoodsFromShoppingCarServiceImpl();}
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
    public AddNewMessageDao getAddNewMessageDao(){return new AddNewMessageDaoImpl(); }
    public MessagePrintDao getMessagePrintDao(){return new MessagePrintDaoImpl(); }
    public PrintMessageService getPrintMessageService(){return new PrintMessageServiceImpl();}
    public CheckIfReplyIdExistDao getCheckIfReplyIdExistDao() {return new CheckIfReplyIdExistDaoImpl();}
    public AddMessageService getAddMessageService(){return new AddMessageServiceImpl();}
    public ReplyMessageService getReplyMessageService(){return new ReplyMessageServicImpl();}
    public DeleteMessageService getDeleteMessageService(){return  new DeleteMessageServiceImpl();}
}
