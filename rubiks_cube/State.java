import java.util.ArrayList;

public class State implements Comparable<State>{
    //initialization of the six sides of the cube
    private RubikSide main;
    private RubikSide right;
    private RubikSide left;
    private RubikSide back;
    private RubikSide top;
    private RubikSide bottom;
    private FaceAction face;//object of class FaceAction we have created
    private RubikScrumbler moves;//object of class RubikScrumbler we have created
    private RubikScrumbler rb;//object of class RubikScrumbler we have created

    private State father = null;
    private int size;
    private int score;//the score of the heuristic
    private int rootDist;//the distance(total moves performed)from the root state
    

    public  State(int size){//constructor with parameter the size of the cube(3x3)
        this.size = size;
        this.main = new RubikSide(size, 1);//main side=WHITE
        this.right = new RubikSide(size, 2);//right side=BLUE
        this.back = new RubikSide(size, 3);//back side = ORANGE
        this.left = new RubikSide(size, 4);//left side= GREEN
        this.top = new RubikSide(size, 5);//top side=RED
        this.bottom = new RubikSide(size, 6);//bottom=YELLOW
        this.face= new FaceAction(this);
        this.moves= new RubikScrumbler(this);
        this.rb=new RubikScrumbler(this);
        this.score=0;
        this.rootDist=0;
    }

    public  State(State R){//copy constructor
        this.size = R.getSize();
        //copies the sides of the State R passed as a parameter
        this.main= new RubikSide(R.getMain());
        this.back= new RubikSide(R.getBack());
        this.left= new RubikSide(R.getLeft());
        this.right= new RubikSide(R.getRight());
        this.bottom= new RubikSide(R.getBottom());
        this.top= new RubikSide(R.getTop());
        this.face= new FaceAction(R);
        this.moves= new RubikScrumbler(R);
        this.rb=new RubikScrumbler(R);
        this.score=0;
        this.rootDist=0;
    }


    public FaceAction getFace(){//returns the object FaceAction we created at the start
        return this.face;
    }

    public int getSize(){//returns the size of the cube that was passed as a parameter
        return this.size;
    }

    public RubikSide getMain(){//returns the main side of the cube
        return this.main;
    }
   
    public RubikSide getLeft(){//returns the left side of the cube
        return this.left;
    }
    
    public RubikSide getRight(){//returns the right side of the cube
        return this.right;
    }
    
    public RubikSide getBack(){//returns the back side of the cube
        return this.back;
    }
    
    public RubikSide getTop(){//returns the top side of the cube
        return this.top;
    }
    
    public RubikSide getBottom(){//returns the bottom side of the cube
        return this.bottom;
    }

    public State getFather(){//returns the father of this state
        return this.father;
    }
    public void setFather(State father)//sets the father of this state
	{
        this.father = father;
    }

    public int getScore() {//returns the score of the heuristic
        return this.score;
    }

    public void setScore(int Score){//setter
        this.score=Score;
    } 
    
    public int getRootDist(){//returns the distance of this state from the root(total moves)
        return this.rootDist;
    }

    public void setRootDist(int dist){//setter
        this.rootDist=dist;
    } 

//moves
    public void turnRowToRight(int row){//turns the x row RIGHT on the main side
        int[] mainTopRow = getMain().getRow(row);
        getMain().setRow(row, getLeft().getRow(row));
        getLeft().setRow(row, getBack().getRow(row));
        getBack().setRow(row, getRight().getRow(row));
        getRight().setRow(row, mainTopRow);
        if(row == 0){
            getTop().rotateAntiClockwise();
        }else if(row == (getSize() - 1)){
            getBottom().rotateClockwise();
        }
    }

    public void turnRowToLeft(int row){//turns the x row LEFT on the main side
        int[] mainTopRow = getMain().getRow(row);
        getMain().setRow(row, getRight().getRow(row));
        getRight().setRow(row, getBack().getRow(row));
        getBack().setRow(row, getLeft().getRow(row));
        getLeft().setRow(row, mainTopRow);
        if(row == 0){
            getTop().rotateClockwise();//method we created that rotates the cube values clockwise
        }else if(row == (getSize() - 1)){
            getBottom().rotateAntiClockwise();//method we created that rotates the cube values anti-clockwise
        }
    }


    public void turnColUp(int col){//moves the col x UP on the main side
        int[] mainTopRow = getMain().getCol(col);

        getMain().setCol(col, getBottom().getCol(col));//this is the same regardless of the column we push UP

        if(col==2){//if the col we go UP is the right one
            getBottom().setValue(0,2,getBack().getValue(2,0));
            getBottom().setValue(1,2,getBack().getValue(1,0));
            getBottom().setValue(2,2,getBack().getValue(0,0));

            getBack().setValue(2,0,getTop().getValue(0,2));
            getBack().setValue(1,0,getTop().getValue(1,2));
            getBack().setValue(0,0,getTop().getValue(2,2));

        }else if(col==1){//if the col we go UP is the middle one
            getBottom().setValue(0,1,getBack().getValue(2,1));
            getBottom().setValue(1,1,getBack().getValue(1,1));
            getBottom().setValue(2,1,getBack().getValue(0,1));
    
            getBack().setValue(2,1,getTop().getValue(0,1));
            getBack().setValue(1,1,getTop().getValue(1,1));
            getBack().setValue(0,1,getTop().getValue(2,1));
    
        }else{//if the col we go UP is the left one
            getBottom().setValue(0,0,getBack().getValue(2,2));
            getBottom().setValue(1,0,getBack().getValue(1,2));
            getBottom().setValue(2,0,getBack().getValue(0,2));
    
            getBack().setValue(2,2,getTop().getValue(0,0));
            getBack().setValue(1,2,getTop().getValue(1,0));
            getBack().setValue(0,2,getTop().getValue(2,0)); 
           
        }
        getTop().setCol(col, mainTopRow);//this is the same regardless of the column we push UP
        
        if(col==0){//ean i stili h aristerh, rotate aristera tin aristeri pleura
            getLeft().rotateAntiClockwise();
        }else if(col==2){//ean i stili h deksia, rotate deksia tin deksia pleura
            getRight().rotateClockwise();
        }
    }

    public void turnColDown(int col){//moves the col x DOWN on the main side
        if(col==0){//if the col we want to push down is the left one
            face.setBackFaceMain();//sets the back face of the cube as the new main
            turnColUp(2);//turns his right col  UP(its the same as turning the main col 0 down)
            face.setBackFaceMain();//resets the face
        }else if(col==1){
            face.setBackFaceMain();
            turnColUp(1);
            face.setBackFaceMain();
        }else{
            face.setBackFaceMain();
            turnColUp(0);
            face.setBackFaceMain();
        }
    }


    public void Scrumble(){//performs a number of random moves
        for(int i=0;i<=2;i++){
            moves.RandomMove();//method that randoms the cube 
        }
    }


public void CountOffMainSide(){// counts the score for the off placed cubies on the main side
    for(int i=0;i<=2;i++){
        for(int j=0;j<=2;j++){
            if(i==1 && j==1){ 
                continue;  //because these are the middle squares , which they already are in their correct place, so score+=0
            }
            if((i+j)%2==0){//corner piece
                if(getMain().getValue(1, 1)==getMain().getValue(i, j)){
                    continue;
                }else if(getBack().getValue(1, 1)==getMain().getValue(i, j)){
                    
                    this.score+=2;
                }else{
                    this.score+=1;
                }
            }else{//edge piece
                if(i==1){
                    if(getMain().getValue(1, 1)==getMain().getValue(i, j)){
                        continue;
                    }else if(getLeft().getValue(1, 1)==getMain().getValue(i, j)|| getRight().getValue(1, 1)==getMain().getValue(i, j)||getBack().getValue(1, 1)==getMain().getValue(i, j)){
                        this.score+=2;
                    }else{
                        this.score+=1;
                    }
                }else if(j==1){
                    if(getMain().getValue(1, 1)==getMain().getValue(i, j)){
                        continue;
                    }else if(getBack().getValue(1, 1)==getMain().getValue(i, j)||getTop().getValue(1, 1)==getMain().getValue(i, j)||getBottom().getValue(1, 1)==getMain().getValue(i, j))
                        this.score+=2;
                    else{
                        this.score+=1;
                    }
                }
            }
        }            
    }
}

    private void countOffPlace(){
        CountOffMainSide();
        face.setLeftFaceMain();
        CountOffMainSide();
        face.setLeftFaceMain();
        CountOffMainSide();
        face.setLeftFaceMain();
        CountOffMainSide();
        face.setLeftFaceMain();//back to first main
        face.setTopFaceMain();
        CountOffMainSide();
        face.setTopFaceMain();
        face.setTopFaceMain();
        CountOffMainSide();
        face.setTopFaceMain();//back to first main
    }
    
    private void countWrongColours(){//heuristic that counts how many cubes are on the wrong side
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(i==1&&j==1){//if its the center piece it is in its correct place always
                    continue;
                }
                if(getMain().getValue(i, j)!=getMain().getValue(1,1)){//if this cube doesnt have the same value as the value on the main side
                    this.score+=1;
                }
                if(getLeft().getValue(i, j)!=getLeft().getValue(1, 1)){//if this cube doesnt have the same value as the value on the left side
                    this.score+=1;
                }
                if(getRight().getValue(i, j)!=getRight().getValue(1, 1)){//if this cube doesnt have the same value as the value on the right side
                    this.score+=1;
                }
                if(getBack().getValue(i, j)!=getBack().getValue(1, 1)){//if this cube doesnt have the same value as the value on the back side
                    this.score+=1;
                }
                if(getTop().getValue(i, j)!=getTop().getValue(1, 1)){//if this cube doesnt have the same value as the value on the top side
                    this.score+=1;
                }
                if(getBottom().getValue(i, j)!=getBottom().getValue(1, 1)){//if this cube doesnt have the same value as the value on the bottom side
                    this.score+=1;
                }
            }
        }
        this.score=this.score/3;//divide this by 3 to get the moves
    }

    public void evaluate(int heuristic)//chooses between 2 heuristics
    {
        switch(heuristic)
        {
            case 1:
                this.countOffPlace();
                break;
            case 2:
                this.countWrongColours();
                break;
            default:
                break;
        }
    }


    ArrayList<State> getChildren(int heuristic)
    {
        ArrayList<State> children = new ArrayList<>();
        State child = new State(this);//creates a copy of the state
        if(child.rb.U())//move #1
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);//root distance of this state is the root distance of the father +1 (g function)
            child.setScore(child.getRootDist()+child.getScore());//add to the score the distance from the root(f=g+h)
            children.add(child);
        }
        child = new State(this); 
        if(child.rb.UR())//move #2
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }

        child = new State(this);
        if(child.rb.D())//move #3
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }

        child = new State(this);
        if(child.rb.DR())//move #4
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        
        child = new State(this);
        if(child.rb.R())//move #5
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this);
        if(child.rb.RR())//move #6
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this); 
        if(child.rb.L())//move #7
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this);
        if(child.rb.LR())//move #8
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        } 
        child = new State(this); 
        if(child.rb.F())//move #9
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this); 
        if(child.rb.FR())//move #10
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this); 
        if(child.rb.B())//move #11
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        child = new State(this); 
        if(child.rb.BR())//move #12
        {
            if(heuristic > 0) child.evaluate(heuristic);
			child.setFather(this);
            child.setRootDist(child.getFather().getRootDist()+1);
            child.setScore(child.getRootDist()+child.getScore());
            children.add(child);
        }
        return children;
    }




    public boolean isFinal(int m){ //m=the number of solved sides we want
        int l=0;//how many sides are actually ready
        boolean flag=true;//help flag

            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;// if it is the center piece we continue
                    if(this.getMain().getValue(i, j)!=this.getMain().getValue(1, 1)){//if this cube has not the same value-colour as the middle cube,then this side isnt finished
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){//if the cubes have all the correct colour on the main side
                l+=1;
            }else{//else flag=false and we reset the flag without updating l variable
                flag=true;
            }
           
            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;
                    if(this.getLeft().getValue(i, j)!=this.getLeft().getValue(1, 1)){
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){
                l+=1;
            }else{//else flag=false and we reset the flag
                flag=true;
            }

            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;
                    if(this.getRight().getValue(i, j)!=this.getRight().getValue(1, 1)){
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){
                l+=1;
            }else{//else flag=false and we reset the flag
                flag=true;
            }

            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;
                    if(this.getBack().getValue(i, j)!=this.getBack().getValue(1, 1)){
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){
                l+=1;
            }else{//else flag=false and we reset the flag
                flag=true;
            }

            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;
                    if(this.getTop().getValue(i, j)!=this.getTop().getValue(1, 1)){
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){
                l+=1;
            }else{//else flag=false and we reset the flag
                flag=true;
            }

            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(i==1&&j==1)continue;
                    if(this.getBottom().getValue(i, j)!=this.getBottom().getValue(1, 1)){
                        i=3;//i do it so that we can break out of the second for loop as well
                        flag=false;
                        break;
                    }
                }
            }

            if(flag==true){
                l+=1;
            }
            

            if(m<=l){//if m(the sides we want) are equal or less than the actual sides solved
                return true;
            }else{
                return false;
            }
    }
    
     public boolean equals(Object obj){
        if (this.size!=((State)obj).size){
            return false;
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){//checks if the cube is the same on the 2 states given , we dont need to rotate or anything because they always see the same main side, our moves do not change the side, the main side is always the white one
                if(getMain().getValue(i, j)!=((State)obj).getMain().getValue(i, j)){
                    return false;
                }else if(getRight().getValue(i, j)!=((State)obj).getRight().getValue(i, j)){
                    return false;

                }else if(getLeft().getValue(i, j)!=((State)obj).getLeft().getValue(i, j)){
                    return false;

                }else if(getTop().getValue(i, j)!=((State)obj).getTop().getValue(i, j)){
                    return false;
                
                }else if(getBottom().getValue(i, j)!=((State)obj).getBottom().getValue(i, j)){
                    return false;
                
                }else if(getBack().getValue(i, j)!=((State)obj).getBack().getValue(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    public int compareTo(State s)
    {
        return Double.compare(this.score, s.score); // compare based on the heuristic score.
    }
    
        public void print(){
            System.out.println("\n\n------------------------------------------------------------\nMain side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getMain().getValue(i, j));
             }
            }
            System.out.println("\n\nTop side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getTop().getValue(i, j));
             }
            }
            System.out.println("\n\nBottom side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getBottom().getValue(i, j));
             }
            }
            System.out.println("\n\nBack side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getBack().getValue(i, j));
             }
            }
            System.out.println("\n\nRight side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getRight().getValue(i, j));
             }
            }
            System.out.println("\n\nLeft side");
            for(int i=0;i<=2;i++){
             System.out.print("\n");
             for(int j=0;j<=2;j++){
 
                 System.out.print(" "+getLeft().getValue(i, j));
             }
            }

    }
}