import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Skeleton code for Edit Distance Computation of DNA sequences.
 * The DNA sequence consists of possible characters from the set {'A', 'C', 'G', 'T'}, 
 * and a special character '?'. Character '?' can be replaced by any character in 
  * {'A', 'C', 'G', 'T'} at zero cost. But character '?' cannot be deleted or inserted.
 * You are required to implement the minDistance method by dynamic programming.
 * The character-dependent edit costs are defined in EditCost class.
 *
 * The given code is provided to assist you to complete the required tasks. But the 
 * given code is often incomplete. You have to read and understand the given code 
 * carefully, before you can apply the code properly. You might need to implement 
 * additional procedures, such as error checking and handling, in order to apply the 
 */

public class EditDistance {

    /* Compute the minimal total cost of character edits between two DNA sequences by dynamic programming.
     * 
     * @param seq1 the original sequence.
     * @param seq2 the target sequence.
     * @return the minimal cost of the character edits.
     */
    public static int minDistance(String seq1, String seq2) {
        // TODO: Complete this method
        // START YOUR CODE
        int seq1_length = seq1.length();
        int seq2_length = seq2.length();
        int iteration = (seq1_length+1) * (seq2_length+1);

        int[][] dp = new int[seq1_length+1][seq2_length+1];

        for (int i = 0; i <= seq1_length; i++) {
            for (int j = 0; j <= seq2_length; j++) {
                if (i == seq1_length && j == seq2_length) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int x = 0; x < iteration; x++) {
            for (int i = 0; i <= seq1_length; i++) {
                for (int j = 0; j <= seq2_length; j++) {
                    Set<Integer> op_cost = new HashSet<>();

                    //Delete
                    if (i < Math.abs(seq1_length)) {
                        char current = seq1.charAt(i);
                        op_cost.add(EditCost.getDeleteCost(EditCost.convertToIndex(current)) + dp[i+1][j]);
                    }

                    if (j < Math.abs(seq2_length)) {
                        char current = seq2.charAt(j);
                        op_cost.add(EditCost.getInsertCost(EditCost.convertToIndex(current)) + dp[i][j+1]);
                    }

                    if (i < Math.abs(seq1_length) && j < Math.abs(seq2_length)) {
                        char current1 = seq1.charAt(i);
                        char current2 = seq2.charAt(j);
                        op_cost.add(EditCost.getReplaceCost(EditCost.convertToIndex(current1),
                                EditCost.convertToIndex(current2)) + dp[i+1][j+1]);
                    }

                    if (!op_cost.isEmpty()) {
                        dp[i][j] = Collections.min(op_cost);
                    }
                }
            }
        }
        return dp[0][0];
        // END YOUR CODE
    }



}