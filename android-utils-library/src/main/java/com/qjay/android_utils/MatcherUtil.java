package com.qjay.android_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配工具类
 * Created by Q.Jay on 2015/8/28 0028.
 */
public final class MatcherUtil {
    private MatcherUtil() {
        throw new UnsupportedOperationException("不支持实例化");
    }

    /**
     * @param regex 正则表达式
     * @param input 匹配内容
     * @return 返回结果
     */
    private static boolean regexMatcher(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }

    /**
     * 匹配是否是邮箱
     * @param email 邮箱
     */
    public static boolean matchEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return regexMatcher(regex, email);
    }

    /**
     * 匹配国内座机号码
     * @param number 座机号码
     */
    public static boolean matchTelephone (String number) {
        String regex = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
        return regexMatcher(regex, number);
    }
    /**
     * 匹配手机号码
     * @param phone 手机号码
     */
    public static boolean matchPhone(String phone) {
        /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String regex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        String regex = "^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+";
        return regexMatcher(regex, phone);
    }
    /**
     * 匹配QQ号码
     * @param qq QQ号码
     */
    public static boolean matchQQ(String qq) {
        String regex = "[1-9][0-9]{4,}";
        return regexMatcher(regex, qq);
    }
    /**
     * 匹配邮编号码
     * @param zipCode 邮编号码
     */
    public static boolean matchZipCode(String zipCode) {
        String regex = "[1-9]\\d{5}(?!\\d)";
        return regexMatcher(regex, zipCode);
    }
    /**
     * 匹配身份证号码
     * @param idCardNo 身份证号码
     */
    public static boolean matchIdCardNo(String idCardNo) {
        String regex = "\\d{15}|\\d{18}";
        return regexMatcher(regex, idCardNo);
    }

    /**
     * 检测密码格式
     */
    public static boolean checkPassword(String password) {
        int length = password.length();
        if (length < 6 || length > 20) {
            return false;
        }
        return  true;
       /* String regex = "^[a-zA-Z0-9\\_\\-]+$";
        return regexMatcher(regex, password);*/
    }

}
