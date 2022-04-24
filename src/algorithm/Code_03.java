package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *   考察点：滑动窗口，Map
 */
public class Code_03 {

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length(),ans = 0;
        Map<Character,Integer> charIndexMap = new HashMap<>();
        for (int start = 0,end = 0; end < len; end++) {
            char cru = s.charAt(end);
            if(charIndexMap.containsKey(cru)){
                start = Math.max(start,charIndexMap.get(cru));
            }
            ans = Math.max(ans,end-start+1);
            charIndexMap.put(cru,end+1);
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
