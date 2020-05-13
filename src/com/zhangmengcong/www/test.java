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
        Pattern abc = Pattern.compile("^[0-9]*[1-9][0-9]*$");
        Matcher matcher = abc.matcher("123.");
        System.out.println(factory.getFormatService().ifIdFormatWrong("123中"));
    }
}
