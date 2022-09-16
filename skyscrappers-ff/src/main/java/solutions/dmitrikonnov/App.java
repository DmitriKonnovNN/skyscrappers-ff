package solutions.dmitrikonnov;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        SkyScrappers4x4 skyScrappers4x4 = new SkyScrappers4x4();
        int []clue =  { 2, 2, 1, 3,
                2, 2, 3, 1,
                1, 2, 2, 3,
                3, 2, 1, 3 };
        int[] clue2 = {       0, 0, 1, 2,
                0, 2, 0, 0,
                0, 3, 0, 0,
                0, 1, 0, 0 };
        skyScrappers4x4.solvePuzzle(clue2);
    }
}
