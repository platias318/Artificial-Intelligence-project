import java.util.Random;
public class RubikScrumbler { 

    State s;//the current state we want to apply the moves
    public RubikScrumbler(State S){//passes the state as a patameter
        this.s=S;
    }

    public boolean U(){//turn row 0 left
        s.turnRowToLeft(0);
        return true;
    }

    public boolean UR(){//turn row 0 right
        s.turnRowToRight(0);
        return true;
    }
    public boolean D(){//turn row 2 to right
        s.turnRowToRight(2);
        return true;
    }
    public boolean DR(){//turn row 2 to left
        s.turnRowToLeft(2);
        return true;
    }
    public boolean R(){//turn column 2 up
        s.turnColUp(2);
        return true;
    }
    public boolean RR(){//turn column 2 down
        s.turnColDown(2);
        return true;
    }
    public boolean L(){//turn column 0 down
        s.turnColDown(0);
        return true;
    }
    
    public boolean LR(){//turn column 0 up
        s.turnColUp(0);
        return true;
    }
    
    public boolean F(){//turn the column 0 down of the right side
        s.getFace().setRightFaceMain();
        s.turnColDown(0);
        s.getFace().setLeftFaceMain();
        return true;
    }
    public boolean FR(){ //turn the column 0 up of the right side
        s.getFace().setRightFaceMain();
        s.turnColUp(0);
        s.getFace().setLeftFaceMain();
        return true;
    }

    public boolean B(){//turn the column 2 up of the right side
        s.getFace().setRightFaceMain();
        s.turnColUp(2);
        s.getFace().setLeftFaceMain();
        return true;
    }
    public boolean BR(){//turn the column 2 down of the right side
        s.getFace().setRightFaceMain();
        s.turnColDown(2);
        s.getFace().setLeftFaceMain();
        return true;

    }
    
    public void RandomMove(){//applies a move to the current State, based on a random number from 0 to 11(12 moves)
        Random random = new Random();
        int rand= random.nextInt(12);
        switch(rand){
            case 0:
                U();
                break;
            case 1:
                UR();
                break;
            case 2:
                D();
                break;
            case 3:
                DR();
                break;
            case 4:
                R();
                break;
            case 5:
                RR();
                break;
            case 6:
                L();
                break;
            case 7:
                LR();
                break;
            case 8:
                F();
                break;
            case 9:
                FR();
                break;
            case 10:
                B();
                break;
            case 11:
                BR();
                break;
            
        }
    }

}
