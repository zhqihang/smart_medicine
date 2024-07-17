package qihang.smart.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 变量名工具
 * @author: zhqihang
 * @date: 2024/07/17
 */
public class VariableNameUtils {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
