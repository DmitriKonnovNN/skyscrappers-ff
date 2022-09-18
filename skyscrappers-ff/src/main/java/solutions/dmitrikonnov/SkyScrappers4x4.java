package solutions.dmitrikonnov;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SkyScrappers4x4 {

    int [][] skyScrprs = {  {4,4},
                            {3,4},
                            {2,4}}; // 4 floors, 3 floors, 2 floors
    int [][] grid = new int[6][6];
    Stack<Integer[]> stack = new Stack<>();
    final int gLength = grid.length;
    final int gDepth = grid[0].length;
    static int jtemp = 1;
    final int MAX = 4;
    static int invokeCounter = 0;
    static boolean full = false;

    public int[][] solvePuzzle (int[] clues) {


        int i = 0;
        int k = 0;
        int j = 0;


        initGrid(clues, grid);
        attemptToSet(i,j,k,grid,skyScrprs);


        return format(grid);

    }

    protected boolean attemptToSet (int in, int jn, int kn, int [][] grid, int [][] skyScrprs){
        int [][] skyScrprs1 = Arrays.stream(skyScrprs).map(int[]::clone).toArray(int[][]::new);
        int[][]grid1 = Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);

        boolean deepSuccess = true;



        for (int k = kn; k <skyScrprs1.length; k++){
            for (int i = in; i < gDepth-1; i++){
                for (int j = deepSuccess==false ? jtemp : jn ; j < gLength; j++) {
                    if (isClue(i, j)) continue;
                    if(!deepSuccess){
                        skyScrprs1 = Arrays.stream(skyScrprs).map(int[]::clone).toArray(int[][]::new);
                        grid1 = Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);
                        deepSuccess = true;

                    }
                    boolean success = attemptSuccessful(i, j, grid1, skyScrprs1[k][0],k);
                    if (success){
                        jtemp = j;
                        grid1[i][j] = skyScrprs1[k][0];
                        skyScrprs1[k][1]-=1;
                        if(skyScrprs1[k][1]==0){
                            deepSuccess = attemptToSet(0,0,k+1, grid1, skyScrprs1);
                        }
                        deepSuccess = attemptToSet(i+1,j=1,k,grid1, skyScrprs1);
                    }
                    if (j== gLength-2){
                        return false;
                    }

                }}
        }
        return deepSuccess;
    }

    /*protected void attemptToSe1(int in, int jn, int kn, int [][] grid){
        if(kn==3){full = true;}
        if(full)return;

        invokeCounter++;
       System.out.println("invoked= " + invokeCounter + " in jn kn =" + in + jn + kn);


        System.out.println("SK LENGTH= " + skyScrprs.length + " Kn before loop= " + kn);

        *//*for (int k = kn; k <skyScrprs.length; k++)*//*

        while(kn<skyScrprs.length){
            int k = kn;
            System.out.println("K= " +k);
            for (int i = in; i < gDepth-1; i++){
                for (int j = jn ; j < gLength; j++){
                    if(full)return;

                    if (isClue(i, j)) continue;

                        boolean success = attemptSuccessful(i, j, grid, skyScrprs[k][0],k);
                        if (success) {
                            if(full)return;
                            skyScrprs[k][1]-=1;

                            if(skyScrprs[k][1]==0){
                                System.out.println("k1 = "+skyScrprs[k][1]);
                                System.out.println("JUMP to the next TYPE: ijk= "+i+j+k);
                                attemptToSet(0,0,k+1,grid);
                            }
                            if (skyScrprs[k][1]>0){
                                System.out.println("k1 = "+skyScrprs[k][1]);
                                System.out.println("CAAAAAAAAAAAAAAAAALL ijk= " +i+j+k);
                                attemptToSet(i+1,j=1,k,grid);
                            }
                        }
                        if (j== gLength-2){
                            if(full)return;
                            if(skyScrprs[k][1]==4){
                                //jumpBack(k-1);
                              skyScrprs[k-1][1]+=1;
                                Integer [] temp = stack.pop();
                                grid[temp[0]][temp[1]]=temp[3];
                                 if (temp[1]==4){
                                skyScrprs[k][1]+=1;
                                Integer [] temp1 = stack.pop();
                                grid[temp1[0]][temp1[1]] = temp1[3];
                                attemptToSet(temp1[0],temp1[1]+1,temp1[2],grid);
                                 }
                                attemptToSet(temp[0],temp[1]+1,temp[2],grid);
                            }
                            //jumpBack(k);
                            skyScrprs[k][1]+=1;
                            Integer [] temp = stack.pop(); // i,j,k,previous value = succeeded attempt
                            grid[temp[0]][temp[1]]=temp[3];
                            if (temp[1]==4){
                                skyScrprs[k][1]+=1;
                                Integer [] temp1 = stack.pop();
                                grid[temp1[0]][temp1[1]] = temp1[3];
                                attemptToSet(temp1[0],temp1[1]+1,temp1[2],grid);
                                }
                            attemptToSet(temp[0],temp[1]+1,temp[2],grid);
                        }
                    
            }

        }
        }

        System.out.println("AFTER WHILE");

    }*/



  /*  private void jumpBack(int k){
        skyScrprs[k][1]+=1;
        Integer [] temp = stack.pop(); // i,j,k,previous value = succeeded attempt
        grid[temp[0]][temp[1]]=temp[3];
        if (temp[1]==4){
           jumpBack(k);
        }
        attemptToSet(temp[0],temp[1]+1,temp[2],grid);
    }*/

    protected boolean attemptSuccessful(int i, int j, int [][] grid, int skyscpr, int k){
    //    System.out.println("attemptToSet outerloop: i j k skyscrpr" + i+j+k+skyscpr);
        if(isOccupied(i,j, grid)) return false;
        int [] sliceV = new int [gDepth];
        int [] sliceH = new int [gLength];
        //vertical check
        for (int v = 0; v < gDepth; v++){

            if(v==i){
                sliceV[v]=skyscpr;
                continue;}
            sliceV[v]=grid[v][j];

        }
        if(isDuplicated(skyscpr, sliceV)) return false;
        if (!isVisible(sliceV))return false;

        //horizontal check
        for (int h = 0; h < gLength; h++){
            if(h==j){
                sliceH[h]=skyscpr;
                continue;}
            sliceH[h]=grid[i][h];
        }
        if (isDuplicated(skyscpr, sliceH)) return false;
        if (!isVisible(sliceH)) return false;

//        Integer[]stackFrame ={i,j,k,grid[i][j]};
//        System.out.println("Stack size = "+stack.size());
//        stack.push(stackFrame);

        return true;
    }

    protected boolean isOccupied(int i, int j, int [][] grid){

        return grid[i][j]!=1;
    }

    protected void initGrid( int[] clues, int[][]grid){
        System.out.println("void initGrid. clues= " + clues.toString() + " grid= " + grid.toString());
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
        System.out.println("void initGrid finished");
    }

    // private boolean isCloseTo1 (int number, int [] line) {
    //     if (line[0]==1 && )
    // }

    // private boolean isOccupied(int[] line) {

    // }

    protected boolean isDuplicated (int number, int [] line){
        int counter = 0;
        for (int i = 1; i< line.length-1; i++){
            if (line[i]== number) counter++;
        }
        return counter > 1;
    }

    boolean isVisible1 (int[]line) {
        int viewPoint1 = line[0];
        int viewPoint2 = line[line.length-1];
        int counter = 0;
        int highest = 0;
        int unkown = 0;
        if (viewPoint1 != 0) {
        for (int i = 1; i<line.length-1; i++){
            if(highest==0) {highest = line[i];
            counter++;}

            if(line[i]>highest){
                highest = line[i];
                counter++;
            }
            if(line[i]==line[i+1]){
                unkown++;
            }
        }
            if (counter!=viewPoint1)return false;
        }
        if(viewPoint2 != 0){
            counter = 0;
            highest = 0;
            for (int i = line.length-2; i>0; i--){
                if(highest==0) {highest = line[i];
                counter++;}
                if(line[i]>highest){
                    highest = line[i];
                    counter++;
                }
            }
            if (counter!=viewPoint2)return false;
        }


        return true;
    }

    protected boolean isVisible (int[] line) {
        int viewPoint1 = line[0];
        int viewPoint2 = line[line.length-1];

        int counter = 1;
        int unknown = 0;
        int temp = 0; // temorary highest point
        if (viewPoint1 != 0) {
            for (int i = 2; i<line.length-1; i++){
                if (temp == 0) temp = line[i-1];
                if (line[i]==temp){
                    temp=line[i];
                    unknown++;
                    if(unknown<viewPoint1-1)counter++;
                }
                if(line[i]>temp) {
                    temp = line[i];
                    counter++;
                }
            }
            if (counter!=viewPoint1)return false;
        }

        if(viewPoint2 != 0){
            counter = 1;
            unknown = 0;
            temp = 0;

            for (int i = line.length-3; i>0; i--){
                if (temp == 0) temp = line[i+1];
                if (line[i]==temp){
                    temp=line[i];
                    unknown++;
                    if(unknown<viewPoint2-1)counter++;
                }
                if(line[i]>temp) {
                    temp = line[i];
                    counter++;
                }
            }
            if (counter!=viewPoint2)return false;
        }


        return true;
    }


    protected boolean isClue(int i, int j){
        return i == 0 || i == gDepth-1 || j == 0 || j == gDepth-1;
    }


    protected void cutEdge(int [][]grid) {
        grid[0][0] = 0;
        grid[0][gLength-1]=0;
        grid[gDepth-1][0]=0;
        grid[gDepth-1][gLength-1]=0;

    }

    protected int[][] format(int [][] grid) {
        int [][] result = new int [4][4];
        for (int i = 1; i<gDepth-1; i++){
            for(int j = 1; j<gLength-1;j++){
                result[i-1][j-1]=grid[i][j];
            } 
        }
        return result;
    }

}
