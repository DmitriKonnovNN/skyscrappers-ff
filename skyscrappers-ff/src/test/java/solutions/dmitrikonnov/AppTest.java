package solutions.dmitrikonnov;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    SkyScrappers4x4 s;
    int clues[][] = {
            {       2, 2, 1, 3,
                    2, 2, 3, 1,
                    1, 2, 2, 3,
                    3, 2, 1, 3 },
            {       0, 0, 1, 2,
                    0, 2, 0, 0,
                    0, 3, 0, 0,
                    0, 1, 0, 0 }
    };
    int outcomes[][][] = {
                { { 1, 3, 4, 2 },
                    { 4, 2, 1, 3 },
                    { 3, 4, 2, 1 },
                    { 2, 1, 3, 4 } },

                    { { 2, 1, 4, 3 },
                    { 3, 4, 1, 2 },
                    { 4, 2, 3, 1 },
                    { 1, 3, 2, 4 } }
    };

    @BeforeEach
    public void preSet (){

        s = new SkyScrappers4x4();
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }



    @Test
    public void testSolvePuzzle1 () {
        var exp = outcomes[0];
        var actual = s.solvePuzzle(clues[0]);

        System.out.println("Actual= " + actual);
        assertArrayEquals(exp,actual);


    }

    @Test
    public void testSolvePuzzle2 () {
        var exp = outcomes[1];
        var actual = s.solvePuzzle(clues[1]);

        System.out.println("Actual= " + Arrays.deepToString(actual));

        assertArrayEquals(exp, actual);
    }
}
