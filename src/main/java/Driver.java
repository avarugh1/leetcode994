import java.util.ArrayList;

public class Driver {
    class Coord{
        int x;
        int y;
        public Coord(int xIn, int yIn){ x = xIn; y = yIn;}
    }

    public int[][] grid;

    public int orangesRotting(int[][] gridIn) {
        this.grid = gridIn;
        int minutes = 0;
        ArrayList<Coord> newRotten = getInitialRot(2);
        if(newRotten.size() > 0) newRotten = rot(newRotten); // they will rot before start of minute 1

        while(newRotten.size() > 0){ // while eligible fruit can rot
            minutes++;
            newRotten = rot(newRotten); // rot them
        }

        if(getInitialRot(1).size() > 0) return -1; // if there is good fruit which cannot be reached

        return minutes;
    }

    private ArrayList<Coord> getInitialRot(int checkDigit) {
        ArrayList<Coord> retList = new ArrayList<Coord>();
        for(int row=0;row<grid.length;row++){
            for(int col=0;col< grid[row].length;col++){
                if(grid[row][col] == checkDigit){
                    retList.add(new Coord(row, col));
                }
            }
        }
        return retList;
    }

    private ArrayList<Coord> rot(ArrayList<Coord> newRotten) {
        ArrayList<Coord> retList = new ArrayList<Coord>();
        for(Coord rotten : newRotten){
            // 4 directions search and rot - Simple Breadth First Search
            // bounds checking could be cleaner and better performance
            // if defining bounds checking instead of catching IndexOutOfBoundsException
            try{
                if(grid[rotten.x -1][rotten.y] == 1){
                    retList.add(new Coord(rotten.x-1, rotten.y));
                    grid[rotten.x -1][rotten.y] = 2;
                }
            }catch (IndexOutOfBoundsException e){}

            try{
                if(grid[rotten.x][rotten.y-1] == 1){
                    retList.add(new Coord(rotten.x, rotten.y-1));
                    grid[rotten.x][rotten.y-1] = 2;
                }
            }catch (IndexOutOfBoundsException e){}

            try{
                if(grid[rotten.x][rotten.y+1] == 1){
                    retList.add(new Coord(rotten.x, rotten.y+1));
                    grid[rotten.x][rotten.y+1] = 2;
                }
            }catch (IndexOutOfBoundsException e){}

            try{
                if(grid[rotten.x+1][rotten.y] == 1){
                    retList.add(new Coord(rotten.x+1, rotten.y));
                    grid[rotten.x+1][rotten.y] = 2;
                }
            }catch (IndexOutOfBoundsException e){}
        }

        return retList;
    }


    public static void main(String[] args) {
        {
            Driver d = new Driver();
            int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
            int minutes = d.orangesRotting(grid);
            System.out.println(minutes); // 4
        }
        {
            Driver d = new Driver();
            int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
            int minutes = d.orangesRotting(grid);
            System.out.println(minutes); // -1
        }
        {
            Driver d = new Driver();
            int[][] grid = {{0,2}};
            int minutes = d.orangesRotting(grid);
            System.out.println(minutes); // 0
        }
    }
}
