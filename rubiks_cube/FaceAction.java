public class FaceAction {
    private State r;//the state that we are changing the main side

    public FaceAction(State R){//constructor that gives the state as a parameter
        this.r=R;
    }

    public void setBackFaceMain(){//sets the back face as the NEW main side
        RubikSide copymain = new RubikSide(r.getMain());
        RubikSide copymain1 = new RubikSide(r.getLeft());


        r.getMain().setSide(r.getBack());


        r.getBack().setSide(copymain);
        r.getBottom().rotateClockwise();
        r.getBottom().rotateClockwise();
        r.getTop().rotateClockwise();
        r.getTop().rotateClockwise();

        r.getLeft().setSide(r.getRight());
        r.getRight().setSide(copymain1);
    }

    public void setRightFaceMain(){//sets the right face as the NEW main side
        RubikSide copymain = new RubikSide(r.getMain());

        r.getTop().rotateClockwise();
        r.getBottom().rotateAntiClockwise();

        r.getMain().setSide(r.getRight());
        r.getRight().setSide(r.getBack());
        r.getBack().setSide(r.getLeft());
        r.getLeft().setSide(copymain);

       
    }

    public void setLeftFaceMain(){//sets the left face as the NEW main side
        RubikSide copymain = new RubikSide(r.getMain());

        r.getTop().rotateAntiClockwise();
        r.getBottom().rotateClockwise();

        r.getMain().setSide(r.getLeft());
        r.getLeft().setSide(r.getBack());
        r.getBack().setSide(r.getRight());
        r.getRight().setSide(copymain);

    }


    public void setTopFaceMain(){//sets the top face as the NEW main side
        RubikSide copymain = new RubikSide(r.getMain());
        r.getMain().setSide(r.getTop());
        r.getBack().rotateClockwise();
        r.getBack().rotateClockwise();

        r.getRight().rotateAntiClockwise();

        r.getLeft().rotateClockwise();

        r.getBottom().rotateClockwise();
        r.getBottom().rotateClockwise();
        r.getTop().setSide(r.getBack());
        r.getBack().setSide(r.getBottom());
        r.getBottom().setSide(copymain);
        

    }
    public void setBottomFaceMain(){//sets the bottom face as the NEW main side
        RubikSide copymain = new RubikSide(r.getMain());
        r.getMain().setSide(r.getBottom());

        r.getBack().rotateAntiClockwise();
        r.getBack().rotateAntiClockwise();

        r.getTop().rotateAntiClockwise();
        r.getTop().rotateAntiClockwise();
        r.getRight().rotateClockwise();
        r.getLeft().rotateAntiClockwise();
        
        r.getBottom().setSide(r.getBack());
        r.getBack().setSide(r.getTop());

        r.getTop().setSide(copymain);
    }
}