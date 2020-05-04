import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/4 8:57
 */
public class test {
    private static Pattern NUMBER_PATTERN = Pattern.compile("[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");

    public static void main(String[] args) {
        String phone = "1907779674@qq.com";
        Matcher matcher = NUMBER_PATTERN.matcher(phone);
        System.out.println(matcher.matches());
    }


}