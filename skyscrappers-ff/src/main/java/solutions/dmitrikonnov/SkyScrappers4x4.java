package solutions.dmitrikonnov;

import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;

public class SkyScrappers4x4 {

    int [][] skyScrprs = {{4,4},{3,4},{2,4}}; // 4 floors, 3 floors, 2 floors
    int [][] grid = new int[6][6];
    Stack<Integer[]> stack = new Stack<>();
    final int gLength = grid.length;
    final int gDepth = grid[0].length;

    public int[][] solvePuzzle (int[] clues) {
        int i = 0;
        int k = 0;
        int j = 0;

        initGrid(clues, grid);
        
        attemptToSet(i,j,k,grid);

        return format(grid);

    } 

    private void attemptToSet(int i, int j, int k, int [][] grid){

        outer:
        for (; k <skyScrprs[0].length; k++) {
            // not sure about gDepth-1 down below.
            inner:
            for (; i < gDepth-1; i++){
                for (; j < gLength; j++){
                    if(isEdge(i, j)) continue;
                        
                    // вот здесь надо подумать хорошенько!
                        boolean success = attemptToSet(i, j, grid, skyScrprs[k][0],k);
                        if (success) {
                            skyScrprs[k][1]-=1;
                            if(skyScrprs[k][1]==0)continue outer;
                            else continue inner;
                        }
                        if (j== gLength-2){
                            if(skyScrprs[k][1]==4)skyScrprs[k-1][1]+=1;
                            skyScrprs[k][1]+=1;
                            Integer [] temp = stack.pop();
                            grid[temp[0]+1][temp[1]]=temp[3];
                            attemptToSet(temp[0],temp[1],temp[2],grid);
                        }
                    
            }

        }
        }

    }

    private boolean attemptToSet(int i, int j, int [][] grid, int skyscpr, int k){
        //boolean successfull = true;
        if (grid[i+1][j]!=1) return false; // tryna figure out whether it's occupied;
        //grid[i+1][j] = skyscpr;
        int [] sliceV = new int [gDepth];
        int [] sliceH = new int [gLength];
        //vertical check
        for (int v = 0; v < gDepth; v++){
            if(v==i+1){sliceV[v]=skyscpr; continue;}
            sliceV[v]=grid[v][j];

        }
        if(isDuplicated(skyscpr, sliceV)) return false;
        if (!isVisible(sliceV))return false;

        //horizontal check
        for (int h = 0; h < gLength; h++){
            if(h==j){sliceH[h]=skyscpr;continue;}
            sliceH[h]=grid[i][h];
        }
        if (isDuplicated(skyscpr, sliceH)) return false;
        if (!isVisible(sliceH)) return false;

        Integer[]stackFrame ={i,j,k,grid[i+1][j]};
        stack.push(stackFrame);
        grid[i+1][j] = skyscpr;
        return true;
    }

    private void initGrid( int[] clues, int[][]grid){
        for (int i = 0; i < gDepth; i++){
            for (int j = 0; j < gLength; j++)
            {
                grid[i][j]=1;
            }
        }
        cutEdge(grid);
        int counter = 0;
        for (int j = 1; j<gLength-1; j++) grid[0][j]=clues[counter++];
        for (int i = 1; i<gDepth-1; i++) grid[i][gLength-1]=clues[counter++];
        for (int j = gLength-1-1; j>0; j--) grid[gDepth-1][j]=clues[counter++];
        for (int i = gDepth-1-1; i>0; i--) grid[i][0]=clues[counter++];

    }

    // private boolean isCloseTo1 (int number, int [] line) {
    //     if (line[0]==1 && )
    // }

    // private boolean isOccupied(int[] line) {

    // }

    private boolean isDuplicated (int number, int [] line){
        for (int i = 1; i< line.length-1; i++){
            if (line[i]== number) return true;
        }
        return false;
    }

    private boolean isVisible (int[] line) {
        int viewPoint1 = line[0];
        int viewPoint2 = line[line.length-1];
        int counter = 1;
        boolean visible = false;
        int temp = 0; // temorary highest point

        for (int i = 2; i<line.length-1; i++){
            if (temp == 0) temp = line[i-1];
            if(line[i]>temp) {
                temp = line[i];
                counter++;
            } 
        }
        if (counter==viewPoint1)visible =true; 
        counter = 1; 
        temp = 0; 

        for (int i = line.length-3; i>0; i--){
            if (temp == 0) temp = line[i+1];
            if(line[i]>temp) {
                temp = line[i];
                counter++;
            } 
        }
        if (counter==viewPoint2)visible = true; 

        return visible; 
    }



    private boolean isEdge(int i, int j){
        
        return i == 0 && j == 0 || i==0 && j==gLength-1 || i == gDepth-1 && j ==0 || i == gDepth-1 && j == gLength -1;
    }

    private void cutEdge(int [][]grid) {
        grid[0][0] = 0;
        grid[0][gLength-1]=0;
        grid[gDepth-1][0]=0;
        grid[gDepth-1][gLength-1]=0;

    }

    private int[][] format(int [][] grid) {
        int [][] result = new int [4][4];
        for (int i = 1; i<gDepth-1; i++){
            for(int j = 1; j<gLength-1;j++){
                result[i-1][j-1]=grid[i][j];
            } 
        }
        return result;
    }

}
