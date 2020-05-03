package com.zhangmengcong.www.service.service.generatefileservice;

/**
 * @author:zmc
 * @function: 生成商家订单xls文件
 * @date: 2020/5/2 18:59
 */
public interface GeneateFileService {
    /**生成xls文件并下载
     *
     * @param path 下载的路径
     * @param username 商家名
     */
    void geneateFileService(String username,String path);
}
