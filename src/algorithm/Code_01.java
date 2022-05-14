package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 思考点：数组 和 Map
 */
public class Code_01 {

    public static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2) return null;

        Map<Integer,Integer> numMap = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            // 伙伴数
            int partner = target - nums[i];
            if(numMap.containsKey(partner)){
                return new int[]{numMap.get(partner),i};
            }
            numMap.put(nums[i],i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1,5,8,9,2,6,7};
        int[] ints = twoSum(nums, 13);

        System.out.println(Arrays.toString(ints));
    }

}
