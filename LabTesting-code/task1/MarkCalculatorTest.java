/**
 * Sample code with JUnit 4 for the parameterized test
 * 
 */

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MarkCalculatorTest {
	/**
	 * Return a list of parameters which are different implementation of 
	 * interface {@linkplain MarkCalculator}. 
	 * Do NOT Modify this part
	*/
	@Parameters
    public static Iterable<? extends Object> getImplementations() {
        return Arrays.asList(
                new MarkCalculator0(),
                new MarkCalculator1(),
                new MarkCalculator2(),
                new MarkCalculator3(),
                new MarkCalculator4(),
                new MarkCalculator5(),
                new MarkCalculator6(),
                new MarkCalculator7(),
                new MarkCalculator8(),
                new MarkCalculator9()
        );
    }
	
	@Parameter
	public MarkCalculator calculator;
	
	
	// ########## YOUR CODE STARTS HERE ##########
	
	/* EXAMPLE Test case 1 */
	@Test(expected = ComponentOutOfRangeException.class)
	public void testException() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(-1, 0, 0, 0, true);
	}

	/* EXAMPLE Test case 2 */
	@Test
	public void testGradeN() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(0, Grade.N), this.calculator.calculateMark(0, 0, 0, 0, true));
	}
	
	//TODO: write other test cases
	@Test(timeout=1000)
	public void testGradeNCN() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(null, Grade.NCN), this.calculator.calculateMark(0,0,0,0,false));
	}

	@Test(timeout=1000)
	public void testGradePX() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(45, Grade.PX), this.calculator.calculateMark(0,0,0,75,true));
		assertEquals(new MarkGrade(49, Grade.PX), this.calculator.calculateMark(4,0,0,75,true));
	}

	@Test(timeout=1000)
	public void testGradeP() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(50, Grade.P), this.calculator.calculateMark(5,0,0,75,true));
		assertEquals(new MarkGrade(59, Grade.P), this.calculator.calculateMark(5,6,0,75,true));
	}

	@Test(timeout=1000)
	public void testGradeC() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(60, Grade.C), this.calculator.calculateMark(9,4,0,75,true));
		assertEquals(new MarkGrade(69, Grade.C), this.calculator.calculateMark(9,4,6,75,true));
	}

	@Test(timeout=1000)
	public void testGradeD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(70, Grade.D), this.calculator.calculateMark(10,4,6,75,true));
		assertEquals(new MarkGrade(79, Grade.D), this.calculator.calculateMark(10,8,8,75,true));
	}

	@Test(timeout=1000)
	public void testGradeHD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(80, Grade.HD), this.calculator.calculateMark(8,10,8,75,true));
		assertEquals(new MarkGrade(100, Grade.HD), this.calculator.calculateMark(10,10,10,100,true));
	}

	@Test(timeout=1000)
	public void testGradeS4() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(50, Grade.P), this.calculator.calculateMark(2,7,8,43,true));
	}

	@Test(timeout=1000)
	public void testGradeS7() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(52, Grade.P), this.calculator.calculateMark(2,8,8,43,true));
	}

	@Test(timeout=1000)
	public void testGradeS0S5() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(59, Grade.P), this.calculator.calculateMark(0,0,1,95,true));
	}

	//Error in MarkCalculator6 and MarkCalculator9 are the value of assignment1 and assignment2 are over the border "0-10".
	@Test(expected = ComponentOutOfRangeException.class)
	public void testGradeS6S9() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(74, Grade.D), this.calculator.calculateMark(0,2,15,80,true));
	}

	//Thus, the correct code is MarkCalculator8.

	// ########## YOUR CODE ENDS HERE ##########
}
