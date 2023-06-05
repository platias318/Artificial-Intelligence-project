import java.util.stream.IntStream;
public class RubikSide {
    private  int size;//the size of the rubiks cube(3x3...)
    private int[][] values;//dimensional array with the value(color)of each side
    private int value;//the starting colour of the side

    public RubikSide(int size, int value){ // value=colour of the face(1=White,2=Blue etc...)
        this.size = size;
        this.value=value;
        this.values= new int[this.size][this.size];
    
        for(int i=0;i<this.size;i++){//fills the cube with the colour that was passed as a parameter
            for(int j=0;j<this.size;j++){
                this.values[i][j]=this.value; 
            }
        }
    }

    public RubikSide(RubikSide R){//COPY CONSTRUCTOR
        this(3,0);//creating the values array because it is NULL
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.values[i][j]= Integer.valueOf(R.getValue(i, j));
            }
        }
    }

    public int[]getRow(int row){//returns the whole specific row of the cube
        return values[row];
    }

    public int[] getCol(int col){//returns the whole specicif column AS an array
        return IntStream.range(0, size).map(i -> values[i][col]).toArray();
    }

    public int getValue(int i, int j){//returns the value of the cube that is in ROW i and COLUMN j(on the main side)
        return values[i][j];
    }

    public void setRow(int row, int[] newValues){
        values[row] = newValues;
    }
    
    public void setCol(int col, int[] newValues){
        for(int i=0;i<size;i++){
            values[i][col] = newValues[i];
        }
    }

    public void setValue(int i, int j,int value){//changes the value(colour) of a specific cube(ROW=i,COLUMN=j) 
        values[i][j]=value;
    }
    
    public void setSide(RubikSide newCopy){ //changes all the cubes of a side with the cubes of another side that we are passing as a parameter
        this.setRow(0,newCopy.getRow(0));
        this.setRow(1,newCopy.getRow(1));
        this.setRow(2,newCopy.getRow(2));
    }

    public void rotateClockwise(){//rotates the side clockwise(down right)
        RubikSide copy= new RubikSide(this);//creates a copy side(using the copy constructor above)
        this.setCol(2,copy.getRow(0));
        this.setCol(1,copy.getRow(1));
        this.setCol(0,copy.getRow(2)); 
    }

    public void rotateAntiClockwise(){//rotates the side anticlockwise(down left)
        RubikSide copy= new RubikSide(this);//creates a copy side(using the copy constructor above)
        this.setRow(2,copy.getCol(0));
        this.setRow(1,copy.getCol(1));
        this.setRow(0,copy.getCol(2)); 
    }

}

