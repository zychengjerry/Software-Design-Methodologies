/*
 * The given code is provided to assist you to complete the required tasks. But the
 * given code is often incomplete. You have to read and understand the given code
 * carefully, before you can apply the code properly.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Please review Lecture 5 Algorithms Part I, slide 20 to complete this task.
* */
public class MergeSort {

    /**
     * Sorts an array [1...n] by divide-and-conquer. It must use merge() method.
     *
     * @param input array a needs to be sorted.
     */
    public static void mergeSort(int[] a) {
        /*  */
        int[] L = null; // This array will store the left half of array 
        int[] R = null; // This array will store the right half of array 
        // TODO: Complete this method
        // START YOUR CODE
        if (a.length<=1){
            return;
        }
        int mid = a.length/2;
        L = Arrays.copyOfRange(a,0,mid);
        R = Arrays.copyOfRange(a,mid,a.length);
        //System.out.println(L);
        //System.out.println(R);
        mergeSort(L);
        mergeSort(R);
        // END YOUR CODE
        merge(a,L,R); //Do not modify this part of code.
    }


    /**
     * Merges sorted subarray L and subarray R into sorted array a.
     *
     * @param merged array a,
     * @param input sorted subarray L,
     * @param input sorted subarray R.
     */
    public static void merge(int[] a, int[] L, int[] R) {

        tracker.calltracking(a,L,R); //Do not modify this method. Otherwise, you will be penalized for violation.
        // TODO: Complete this method
        // START YOUR CODE
        int left = 0;
        int right = 0;
        List<Integer> newList = new ArrayList<Integer>();
        while (left<L.length && right<R.length) {
            if (L[left]<R[right]){
                newList.add(L[left]);
                left += 1;
            }else {
                newList.add(R[right]);
                right += 1;
            }
        }
        if (left>=L.length){
            for (int i = right; i<R.length; i++){
                newList.add(R[i]);
            }
        }
        if (right>=R.length){
            for (int i = left; i<L.length; i++){
                newList.add(L[i]);
            }
        }
        for (int i = 0; i<newList.size(); i++){
            a[i] = newList.get(i);
        }
        // END YOUR CODE

    }

}