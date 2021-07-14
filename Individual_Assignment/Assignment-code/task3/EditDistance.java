/*
 * The given code is provided to assist you to complete the required tasks. But the
 * given code is often incomplete. You have to read and understand the given code
 * carefully, before you can apply the code properly.
 */


/*
 * Please review Lecture 7 Algorithms Part III, slides 10-14 to complete this task.
 * */

import java.util.Arrays;

/*
* EditCost defines three character edit costs. INSERT and DELETE will cost 1, and REPLACE will cost 2.
* Do not modify the character edit costs. Otherwise, your answers will not be marked correctly.
* */
enum EditCost
{
    INSERT (1),
    DELETE (1),
    REPLACE (2);

    private final int costValue;

    EditCost(int value) {
        this.costValue = value;
    }

    public int getEditCost(){
        return this.costValue;
    }
}

public class EditDistance {


/* This method computes the minimal total cost of a sequence of character edits between two strings.
 * The costs of character edits are defined in EditCost enum
 * @param seq1 the original string.
 * @param seq2 the target string.
 * @return the minimal cost of the character edits.
 * */
    public static int minDistance(String seq1, String seq2){
        // TODO: Complete this method
        // START YOUR CODE
        int lseq1 = seq1.length();
        int lseq2 = seq2.length();

        if (seq1.equals(seq2)) {
            return 0;
        }else {
            if (lseq1==lseq2){
                int n = 0;
                for (int i=0;i<lseq1;i++){
                    if (seq1.charAt(i)!=seq2.charAt(i)){
                        n++;
                    }
                }
                return n*EditCost.REPLACE.getEditCost();
            }else {
                int n_re, n_de, n_in= 0;
                String maxStr = (seq1.length() >= seq2.length()) ? seq1 : seq2;
                String minStr = (seq1.length() < seq2.length()) ? seq1 : seq2;
                String maxSubStr = minStr;
                int length = minStr.length();

                for (int i = 0; i < length; i++) {

                    for (int x = 0, y = length-i; y <= length; x++, y++) {
                        if (maxStr.contains(minStr.substring(x, y))) {
                            maxSubStr = minStr.substring(x, y);
                        }
                    }
                }

                for (int i=0;i<maxSubStr.length();i++){
                    maxStr = maxStr.replaceAll(String.valueOf(maxSubStr.charAt(i)),"%");
                    minStr = minStr.replaceAll(String.valueOf(maxSubStr.charAt(i)),"%");
                }

                String[] maxSubStrs = maxStr.split("%");
                String[] minSubStrs = minStr.split("%");
                int lenmaxSS = Math.max(maxSubStrs.length,minSubStrs.length);
                int lenminSS = Math.min(maxSubStrs.length,minSubStrs.length);
                String[] minDivDistanceList = new String[lenminSS];

                for (int i=0,j=0; i<maxSubStrs.length && j<minSubStrs.length; i++, j++){
                    int lenMaxSubStrs = maxSubStrs[i].length();
                    int lenMinSubStrs = minSubStrs[j].length();
                    n_re = Math.min(lenMaxSubStrs,lenMinSubStrs);
                    n_de = Math.max(lenMaxSubStrs,lenMinSubStrs) - n_re;
                    int minDivDistance = n_re*EditCost.REPLACE.getEditCost() + n_de*EditCost.DELETE.getEditCost();
                    if (i>=j){
                        minDivDistanceList[i] = String.valueOf(minDivDistance);
                    }else {
                        minDivDistanceList[j] = String.valueOf(minDivDistance);
                    }
                }

                int minDistance = 0;
                for (String s : minDivDistanceList) {
                    minDistance = minDistance + Integer.parseInt(s);
                }
                if (maxSubStrs.length>minSubStrs.length){
                    minDistance = minDistance + maxSubStrs[maxSubStrs.length-1].length()*EditCost.DELETE.getEditCost();
                }else if (maxSubStrs.length<minSubStrs.length){
                    minDistance = minDistance + minSubStrs[minSubStrs.length-1].length()*EditCost.DELETE.getEditCost();
                }
                return minDistance;
            }
        }

        // END YOUR CODE

    }
}
