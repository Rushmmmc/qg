import com.zhangmengcong.www.util.Factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zhangmengcong.www.constant.FormatConstant.IF_INCLUDE_CHINESE_PATTERN;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/4 8:57
 */
public class test {
    public static Pattern IF_HAHAHA = Pattern.compile("[\\u4E00-\\u9FA5+a-zA-Z0-9_-]{4,50}$");
    public static void main(String[] args) {
        Factory factory = new Factory();
        String phone = "6";
        System.out.println(factory.getFormatService().formatService(phone));
    }
}