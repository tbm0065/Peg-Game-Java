import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class pegjava {
	public static void main(String args[]){
		Random r = new Random();
		int[][] original = {{1},{1,1},{1,1,1},{0,1,1,1},{1,1,1,1,1}};//the board
		boolean status = false;//when true, solution found
		while(status == false){//run while false
			int[][] board = deepCopy(original);
			while(true){//run loop
				showBoard(board);//print the current board
				if(isSolved(board)){//if true board is solved
					status = true;
					break;
				}else{//if still trying to solve
					ArrayList<int[][]> o = getOptions(board,true);//find the different options
					ArrayList<String[]> i = getOptions(board,false);//find the instructions
					if(o.isEmpty()){
						status = false;
						break;
					}else{
						int index = r.nextInt(o.size());
						board = o.get(index);
						//System.out.println(i.get(index));//prints out the move
					}
				}
			}
		}
	}
	static boolean isSolved(int[][] b){//check if the board is solved
		int xs = 0;//count how many pegs on the board
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < y+1; x++){
				if(b[y][x]==1){
					xs++;//increment how many pegs
				}
			}
		}
		return xs==1;//true if only one peg
	}
	public static void showBoard(int[][] b){//simple function to print out the board
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < y+1; x++){
				System.out.print(b[y][x]);
			}
			System.out.println();
		}
	}
	public static ArrayList getOptions(int[][] b, boolean t){//find the different options
		ArrayList<int[][]> options = new ArrayList<int[][]>();//an array of integers for the options
		ArrayList<String> instructions = new ArrayList<String>();//an array of strings for the instructions
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < y+1; x++){
				try{ //down right
					if(b[y][x] ==1 && b[y+1][x+1] == 1 && b[y+2][x+2] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y+1][x+1] = 0;
						temp[y+2][x+2] = 1;
						instructions.add(y+" "+x+" over "+ (y+1) + " " + (x+1));
						options.add(temp);
					}
				}catch(Exception e){}
				try{ //down left
					if(b[y][x] ==1 && b[y+1][x] == 1 && b[y+2][x] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y+1][x] = 0;
						temp[y+2][x] = 1;
						instructions.add(y+" "+x+" over "+ (y+1) + " " + (x));
						options.add(temp);
					}
				}catch(Exception e){}
				try{ //up right
					if(b[y][x] ==1 && b[y-1][x] == 1 && b[y-2][x] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y-1][x] = 0;
						temp[y-2][x] = 1;
						instructions.add(y+" "+x+" over "+ (y-1) + " " + (x));
						options.add(temp);
					}
				}catch(Exception e){}
				try{ //up left
					if(b[y][x] ==1 && b[y-1][x-1] == 1 && b[y-2][x-2] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y-1][x-1] = 0;
						temp[y-2][x-2] = 1;
						instructions.add(y+" "+x+" over "+ (y-1) + " " + (x-1));
						options.add(temp);
					}
				}catch(Exception e){}
				try{ //right
					if(b[y][x] ==1 && b[y][x+1] == 1 && b[y][x+2] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y][x+1] = 0;
						temp[y][x+2] = 1;
						instructions.add(y+" "+x+" over "+ (y) + " " + (x+1));
						options.add(temp);
					}
				}catch(Exception e){}
				try{ //left
					if(b[y][x] ==1 && b[y][x-1] == 1 && b[y][x-2] == 0){
						int[][] temp = deepCopy(b);
						temp[y][x] = 0;
						temp[y][x-1] = 0;
						temp[y][x-2] = 1;
						instructions.add(y+" "+x+" over "+ (y) + " " + (x-1));
						options.add(temp);
					}
				}catch(Exception e){}
			}
		}
		if(t){
			return options;
		}else{
			return instructions;
		}
		
		//return options;
	}
	public static int[][] deepCopy(int[][] b) {//returns an the array given with additional length
	    if (b == null) {
	        return null;
	    }

	    final int[][] result = new int[b.length][];
	    for (int i = 0; i < b.length; i++) {
	        result[i] = Arrays.copyOf(b[i], b[i].length);
	        // For Java versions prior to Java 6 use the next:
	        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
	    }
	    return result;
	}
}
