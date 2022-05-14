package algorithm;

import java.util.*;

/***
 * 寻找两个正序数组的中位数
 */
public class Code_05 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] mn = new int[nums1.length+nums2.length];
        System.arraycopy(nums1,0,mn,0,nums1.length);
        System.arraycopy(nums2,0,mn,nums1.length,nums2.length);

        Arrays.sort(mn);

        if(mn.length%2==0){
            return  (mn[mn.length/2-1] + mn[mn.length/2])/2.0;
        } else {
            return  mn[mn.length/2];
        }
    }

    public static void main(String[] args) {

        int[] a = {1,3};
        int[] b = {2};

        findMedianSortedArrays(a,b);

    }
}
