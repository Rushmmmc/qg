package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.service.service.goodsservice.UpdateGoodsPhotoService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.ADD_PHOTO_SUCCESS;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 11:34
 */
public class UpdateGoodsPhotoServiceImpl implements UpdateGoodsPhotoService {

    @Override
    public String updateGoodsPhotoService(String photoName,String goodsName){
        Factory factory = new Factory();
        //用于去除文件中的句号再进行检测
        String tempPhotoName = goodsName.replace(".","");
        boolean ifPhotoNameFormatWrong = factory.getFormatService().ifIncludeSymbol(tempPhotoName);

        if(ifPhotoNameFormatWrong){
            return "图片名格式有误,不可包含特殊符号";
        }
        boolean ifGoodsIdFormatWrong = factory.getFormatService().ifIncludeSymbol(goodsName);
        if(ifGoodsIdFormatWrong){
            return "商品id格式有误";
        }
        //检测数据库是否存在该图片名 防止出错
        String elsePhotoName =  factory.getQueryDao().queryDao("photoName","goods","photoName"
                ,"\""+photoName+"\"");
        if(!"".equals(elsePhotoName)){
            return "该图片名已存在,请更改图片名";
        }
        factory.getUpdateDao().updateDao("goods","photoName",
                "\""+photoName+"\"","goodsName","\""+goodsName+"\"");
        return ADD_PHOTO_SUCCESS;
    }
}
