import com.zhangmengcong.www.util.Factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zhangmengcong.www.constant.FormatConstant.IF_INCLUDE_CHINESE_PATTERN;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/4 8:57
 */
public class    test {
    public static Pattern IF_HAHAHA = Pattern.compile("(^[0-9]{1,8}$)|(^[0-9]{1,6}[\\.]{1}[0-9]{1,2}$)");
    public static void main(String[] args) {
        Factory factory = new Factory();
        String phone = "9999.11";
        Matcher matcher = IF_HAHAHA.matcher(phone);
        System.out.println(factory.getFormatService().formatService(phone));
    }
}