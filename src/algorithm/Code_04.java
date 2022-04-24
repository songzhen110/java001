package algorithm;

import java.util.Arrays;

/***
 * 合并两个有序数组
 *
 * 逆向双指针接发
 *
 */
public class Code_04 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = nums1.length;
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                // 替代swap，参考官方题解“逆向双指针解法”的公式
                nums1[--length] = nums1[--m];
            } else {
                // 替代swap，参考官方题解“逆向双指针解法”的公式
                nums1[--length] = nums2[--n];
            }
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {

    }
}
