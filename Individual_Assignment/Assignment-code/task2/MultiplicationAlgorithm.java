/*
 * The given code is provided to assist you to complete the required tasks. But the
 * given code is often incomplete. You have to read and understand the given code
 * carefully, before you can apply the code properly.
 */


/*
 * Please review Lecture 5 Algorithms Part I, slide 49 to complete this task.
 * */

public class MultiplicationAlgorithm {

    /**
     * Using divide-and-conquer to implement the Karatsuba multiplication to compute the product x times y. 
	 * x and y are two n-digit input numbers.
     *
     * @param input x
     * @param input y
     */
    public static long KMultiply(long x, long y){
        // TODO: Complete this method
        tracker.calltracking(x,y); //Do not modify this method. Otherwise, you will be penalized for violation.
        // START YOUR CODE
        if(x == 0 || y == 0) return 0;
        if(x < 10 && y < 10) return (x * y);
        if (x == 1 || y == 1) {
            if(x == 1) return y;
            return x;
        }

        long biggerNum = Math.max(x, y);
        long smallerNum = Math.min(x, y);
        String bigNum = Long.toString(biggerNum);
        String smallNum = Long.toString(smallerNum);
        int bigLen = bigNum.length();
        int smallLen = smallNum.length();

        int m = Math.min(bigLen/2, smallLen);
        int n = 2 * m;

        StringBuilder numStr = new StringBuilder();
        numStr.append(smallNum);
        int n1 = bigLen - smallLen;
        for (int i = 0; i < n1; i++) {
            numStr = numStr.insert(0, "0");
        }

        long a = Long.parseLong(bigNum.substring(0, bigLen-m));
        long b = Long.parseLong(bigNum.substring(bigLen-m));
        long c = Long.parseLong(numStr.substring(0, numStr.length()-m));
        long d = Long.parseLong(numStr.substring(numStr.length()-m, numStr.length()));

        return (long) (Math.pow(10, n) * KMultiply(a, c) + Math.pow(10, m) * (KMultiply(a, d) + KMultiply(b, c)) + KMultiply(b, d));

        // END YOUR CODE
    }

}
