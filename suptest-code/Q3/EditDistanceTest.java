/**
 * These test cases are provided to assist your implementation.
 * However, note that these test cases may not be used in actual marking.
 * You are encouraged to write your own test case to test the correctness
 * of your implementation.
 */
 

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EditDistanceTest {

    @Test(timeout=1000)
    public void Test1() {
        String str1 = "ACG?";
        String str2 = "ACGT";
        assertEquals(0,EditDistance.minDistance(str1,str2));
    }

    @Test(timeout=1000)
    public void Test2() {
        String str1 = "ACAGTCGGT";
        String str2 = "AGGTCGACT";
        assertEquals(5,EditDistance.minDistance(str1,str2));
    }

    @Test(timeout=1000)
    public void Test3() {
        String str1 = "ACCCCGGGT";
        String str2 = "ATTTCCA";
        assertEquals(10,EditDistance.minDistance(str1,str2));
    }

    @Test(timeout=1000)
    public void Test4() {
        String str1 = "GG???";
        String str2 = "ACGGT";
        assertEquals(3,EditDistance.minDistance(str1,str2));
    }

    @Test(timeout=1000)
    public void Test5() {
        String str1 = "?AAAC?CCTT?TGGG?";
        String str2 = "ACTGACTGACTGACTG";
        assertEquals(12,EditDistance.minDistance(str1,str2));
    }

}

