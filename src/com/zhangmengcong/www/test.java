package com.zhangmengcong.www;

import com.zhangmengcong.www.util.Factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/12 17:05
 */
public class test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Pattern abc = Pattern.compile("[\\u4E00-\\u9FA5+a-zA-Z0-9_\\?\\.,!\\-，。？！]{1,50}$");
        Matcher matcher = abc.matcher(">");
        System.out.println(factory.getFormatService().ifIncludeSymbol(".,?？，。！!<>"));
    }
}
