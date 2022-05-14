package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *   考察点：滑动窗口，Map
 */
public class Code_03 {

    public static int lengthOfLongestSubstring(String s) {
        int ans = 0,len = s.length();
        Map<Character,Integer> charMap = new HashMap<>(16);

        for (int start = 0,end = 0; end < len; end++) {
            char cru = s.charAt(end);
            if(charMap.containsKey(cru)){
                start = Math.max(start,charMap.get(cru));
            }
            charMap.put(cru,end+1);
            ans = Math.max(ans,end-start+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dadadjadjdajavg"));
    }
}
