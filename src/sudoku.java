
public class sudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr= {{8,0,9,4,5,0,7,0,0},
				     {0,3,7,0,0,2,0,0,0},
				     {1,0,2,0,0,8,6,6,9},
				     {4,0,3,8,7,0,2,0,0},
				     {0,0,4,0,2,0,1,0,0},
				     {0,0,0,0,9,5,0,0,7},
				     {3,4,0,0,0,0,0,0,8},
				     {0,0,0,3,0,0,5,1,0},
				     {0,0,1,0,8,9,3,0,2}};
		int[] rownos=new int[arr.length];
		int[] colnos=new int[arr[0].length];
		int[] submnos=new int[arr[0].length];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(arr[i][j]!=0) {
					makeachoice(arr,i*arr.length+j+1,rownos,colnos,submnos,arr[i][j]) ;
				}
			}
		}
		sudokuu(arr, 1, rownos, colnos, submnos);
	}
	public static void makeachoice(int[][] arr,int cellno,int[] rownos,int[]colnos,int[]submnos,int choice){
		int rn=(cellno-1)/arr.length;
		int cn=(cellno-1)%arr.length;
		arr[rn][cn]=choice;
		rownos[rn]^=(1<<choice);
		colnos[cn]^=(1<<choice);
		submnos[(rn/3)*3+cn/3]^=(1<<choice);
			
		}
			
		
	
	public static void unmakeachoice(int[][] arr,int cellno,int[] rownos,int[]colnos,int[]submnos,int choice){
		int rn=(cellno-1)/arr.length;
		int cn=(cellno-1)%arr.length;
		arr[rn][cn]=0;
		rownos[rn]^=(1<<choice);
		colnos[cn]^=(1<<choice);
		submnos[(rn/3)*3+cn/3]^=(1<<choice);
			
		}
			
		
	
	public static void sudokuu(int[][] arr,int cellno,int[] rownos,int[] colnos,int[] submnos) {
		if(cellno == 82) {
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[i].length;j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			return;
		}
		int rn=(cellno-1)/arr.length;
		int cn=(cellno-1)%arr.length;
		
		if(arr[rn][cn]==0) {
			int rown=rownos[rn];
			int coln=colnos[cn];
			int subno=submnos[(rn/3)*3+cn/3];
			int no=rown|coln|subno;
			
			for(int choice=1;choice<=9;choice++) {
				if((no&(1<<choice))==0) {
					makeachoice(arr, cellno, rownos, colnos, submnos, choice);
					sudokuu(arr, cellno+1, rownos, colnos, submnos);
					unmakeachoice(arr, cellno, rownos, colnos, submnos,choice);
				}
			}
			
		}else {
			sudokuu(arr, cellno+1, rownos, colnos, submnos);
		}
		
		
	}
}
