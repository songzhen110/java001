import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {



        String a = "abc";
        Field declaredField = a.getClass().getDeclaredField("value");
        //Field declaredField = String.class.getDeclaredField("value");
        declaredField.setAccessible(true);
        declaredField.set(a,"exd".toCharArray());


        // 寻找水王数
       //int[] arr = new int[]{1,2,3,4,5,3,3,3,3};
        //System.out.println("-----="+ findKing(arr));

        // 整形的字符串转换成整形（不使用Integer.valueOf(str,radix)）
        int convert = convert("--3234");
        System.out.println(convert);

        //字符串转换成大写（不使用.toUpperCase())）
        String t = toUpperCase("aBc");
        System.out.println(t);

    }

    public static int findKing(int[] arr){

        if(arr==null || arr.length==0){
            return -1;
        }

        int leader = 0;
        int retHP = 0;
        for (int cur : arr) {
            if(retHP==0){
                leader = cur;
                retHP = 1;
            }else if(leader!=cur){
                retHP--;
            } else {
                retHP++;
            }
        }

        if(retHP==0){
            return -1;
        }

        int count = 0;
        for (int cur : arr) {
            if(cur==leader){
                count++;
            }
        }

        return count>(arr.length>>1)?leader:-1;
    }

    public static int convert(String str){
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = Character.digit(chars[idx],10);
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;
    }

    public static String toUpperCase(String str){
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int cha = 'A' -'a';
        for (int i = 0; i < chars.length; i++) {
            if('a' <= chars[i] && chars[i] <='z'){
                chars[i] = (char)(chars[i]+cha);
            }
            stringBuilder.append(chars[i]);
        }

        return stringBuilder.toString();

    }

}
