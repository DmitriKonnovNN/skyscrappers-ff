package solutions.dmitrikonnov;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiPredicate;

class SkyScrappers4x4Test {

    int clues [][];
    int outcomes [][];
    int clue1[];
    int innerGrid1[][];
    int outerInitGrid1[][];
    int outerGrid1[][];
    int outerGrid2[][];
    int outerGrid3[][];
    SkyScrappers4x4 underTest;


    @BeforeEach
    void setUp() {
        underTest = new SkyScrappers4x4();
        outerGrid1 = new int[][]{
                {0, 1, 4, 3, 1, 1},
                {1, 1, 1, 1, 1, 0},
                {4, 1, 1, 1, 1, 2},
                {3, 1, 1, 1, 1, 4},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 3, 2, 1, 0}};
        outerGrid2 = new int [][]{
                {0, 2, 4, 3, 2, 1},
                {1, 2, 2, 3, 4, 0},
                {4, 3, 2, 5, 3, 2},
                {3, 4, 2, 2, 3, 4},
                {1, 4, 3, 2, 3, 1},
                {0, 2, 3, 2 ,1 ,0 }};
        outerGrid3 = new int[][]{
                {0,2,2,1,3,0},
                {3,1,1,1,1,2},
                {1,1,1,1,1,2},
                {2,1,1,1,1,3},
                {3,1,1,1,1,1},
                {0,3,2,2,1,0}

        };
        clue1 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        outerInitGrid1 = new int[][]{
                {0,1,2,3,4,0},
                {16,1,1,1,1,5},
                {15,1,1,1,1,6},
                {14,1,1,1,1,7},
                {13,1,1,1,1,8},
                {0,12,11,10,9,0}
        };
        innerGrid1 = new int[][]{
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solvePuzzle() {
    }


    @Test
    void attemptSuccessful(){
        int [][] outerGridExp = new int[][]{
                {0,2,2,1,3,0},
                {3,1,1,1,1,2},
                {1,1,1,1,1,2},
                {2,1,1,1,1,3},
                {3,1,1,1,4,1},
                {0,3,2,2,1,0}

        };
        Integer [] exp = {4,4,1,1};
        Assertions.assertTrue(underTest.attemptSuccessful(4,4,outerGrid3,4,1));
        System.out.println(Arrays.deepToString(underTest.grid));
        Integer[] result = underTest.stack.pop();
        Assertions.assertArrayEquals(exp,result);
        Assertions.assertEquals(0,underTest.stack.size());

    }

    @Test
    void initGrid() {
        int [][] grid = new int[6][6];
        underTest.initGrid(clue1,grid);
        Assertions.assertArrayEquals(grid,outerInitGrid1);

    }

    @Test
    void isDuplicated_shouldSucceed_if_4_004000_false() {

        //given
        int [] line = {0,0,4,0,0,0};
        int ssToSet = 4;
        boolean exp = false;
        //when
        var actual = underTest.isDuplicated(ssToSet, line);
        Assertions.assertEquals(exp, actual);
        //then


    }
    @Test
    void isDuplicated_shouldSucceed_if_2_102232_true() {

        //given
        int [] line = {1,0,2,2,3,2};
        int ssToSet = 2;
        boolean exp = true;
        //when
        var actual = underTest.isDuplicated(ssToSet, line);
        Assertions.assertEquals(exp, actual);
        //then


    }
    @Test
    void isDuplicated_shouldSucceed_if_4_400004_false() {

        //given
        int [] line = {4,0,0,0,0,4};
        int ssToSet = 4;
        boolean exp = false;
        //when
        var actual = underTest.isDuplicated(ssToSet, line);
        Assertions.assertEquals(exp, actual);
        //then

    }
    @Test
    void isDuplicated_shouldSucceed_if_2_243103_false() {

        //given
        int [] line = {2,4,4,1,0,3};
        int ssToSet = 2;
        boolean exp = false;
        //when
        var actual = underTest.isDuplicated(ssToSet, line);
        Assertions.assertEquals(exp, actual);
        ArrayUtils.reverse(line);
        actual = underTest.isDuplicated(ssToSet, line);
        Assertions.assertEquals(exp, actual);

        //then

    }

    @Test
    void isOccupied_shouldSucceed_if_gridFull(){
        final boolean exp = true;

        for (int i = 0; i < outerGrid2[0].length; i++) {
            for (int j = 0; j < outerGrid2.length; j++) {
                if (underTest.isClue(i,j)) continue;
                var actual = underTest.isOccupied(i,j,outerGrid2);
                System.out.println(actual +" i=" + i +  " j=" +j);
                Assertions.assertEquals(exp, actual);
            }
        }

    }

    @Test
    void isOccupied_shouldSucceed_if_any_gridEmpty() {


        final boolean exp = false;


        for (int i = 0; i < outerGrid1[0].length; i++) {
            for (int j = 0; j < outerGrid1.length; j++) {
                if (underTest.isClue(i,j)) continue;
                var actual = underTest.isOccupied(i,j,outerGrid1);
                System.out.println(actual +" i=" + i +  " j=" +j);
                Assertions.assertEquals(exp, actual);
            }
        }

    }

    @Test
    void isClue_shouldSucceed_if_anyClue () {
        String s = "i= %d j= %d value= %d actual= %s ";

            for (int i = 0; i<outerGrid1[0].length; i++) {
                for(int j = 0; j<outerGrid1.length; j++){

                }
            }


        for (int i = 0; i < outerGrid1[0].length; i++) {
                var actual = underTest.isClue(i,0);
                System.out.println(String.format(s,i,0,outerGrid1[i][0],actual));
                Assertions.assertTrue(actual);
                actual = underTest.isClue(i, outerGrid1.length-1);
                System.out.println(String.format(s,i,outerGrid1.length-1,outerGrid1[i][outerGrid1.length-1],actual));
                Assertions.assertTrue(actual);
        }
        for (int j = 0; j < outerGrid1.length; j++) {
            var actual = underTest.isClue(0,j);
            System.out.println(String.format(s,0,j,outerGrid1[0][j],actual));
            Assertions.assertTrue(actual);
            actual = underTest.isClue(outerGrid1[0].length-1,j);
            System.out.println(String.format(s,outerGrid1[0].length-1,j,outerGrid1[outerGrid1[0].length-1][j],actual));
            Assertions.assertTrue(actual);
        }


    }

    @Test
    void isClue_shouldSucceed_if_noneIsClue () {

        for (int i = 1; i < outerGrid1.length -1; i++){
            for (int j = 1; j < outerGrid1[0].length -1; j++) {
                var actual = underTest.isClue(i, j);
                Assertions.assertFalse(actual);
            }}

    }
    @Test
    void isVisible_assertTrue_if_1_4_3_2_1_4() {

        int [] line = {1,4,3,2,1,4};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }
    @Test
    void isVisible_assertTrue_if_1_4_1_1_1_2() {

        int [] line = {1,4,1,1,1,2};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertTrue_if_1_7_1_1_6_1_1_1_3() {

        int [] line = {1,7,1,1,6,1,1,1,1,3};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertTrue_if_1_1_1_1_7_1_1_1_3() {

        int [] line = {1,1,1,1,7,1,1,1,1,3};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }




    @Test
    void isVisible_assertTrue_if_3_1_1_4_1_2() {

        int [] line = {3,1,1,4,1,2};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }
    @Test
    void isVisible_assertTrue_if_3_1_1_1_4_1() {

        int [] line = {3,1,1,1,4,1};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }
    @Test
    void isVisible_assertFalse_if_1_3_1_4_2_3() {

        int [] line = {1,3,1,4,2,3};
        final boolean exp = false;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertFalse_if_1_4_1_1_3_2(){
        int [] line = {1,4,1,1,3,2};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));
    }
    @Test
    void isVisible_assertFalse_if_3_1_1_3_4_1(){
        int [] line = {3,1,1,3,4,1};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));
    }

    @Test
    void isVisible_assertFalse_if_2_3_1_4_2_3() {

        int [] line = {2,3,1,4,2,3};
        final boolean exp = false;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertTrue_if_0_1_4_1_1_0() {

        int [] line = {0,1,4,1,1,0};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertTrue_if_3_1_2_4_1_0() {

        int [] line = {3,1,2,4,1,0};
        final boolean exp = true;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }

    @Test
    void isVisible_assertTrue_if_3_4_2_4_1_0() {

        int [] line = {3,4,2,4,1,0};
        final boolean exp = false;
        Assertions.assertEquals(exp,underTest.isVisible(line));
        ArrayUtils.reverse(line);
        Assertions.assertEquals(exp, underTest.isVisible(line));

    }


    @Test
    void isEdge() {

    }

    @Test
    void cutEdge() {
    }

    @Test
    void format_shouldSucceed_if_equals() {
        final var exp = innerGrid1;
        var actual = underTest.format(outerInitGrid1);
        Assertions.assertArrayEquals(exp,actual);

    }

    @Test
    void format_shouldSucceed_if_notequals() {
        var formatted = underTest.format(outerGrid2);
        final boolean exp = false;
        var actual = Arrays.equals(formatted,innerGrid1);
        Assertions.assertEquals(exp, actual);
    }
}