import java.util.ArrayList;
import java.util.Collections;

public class main {
    public static void main(String[] args){
        State initialState = new State(3);//create a 3x3 cube
        initialState.Scrumble();//scrumbles the cube(we do 3 random moves)
        initialState.print();
        SpaceSearcher searcher = new SpaceSearcher();
        long start = System.currentTimeMillis();
        State terminalState = searcher.ASTARClosed(initialState,2,2);//we use the ASTARClosed searching method, with the second heuristic(minimum number of misplaced cubes) and m=2(the number of solved sides we need)
        long end = System.currentTimeMillis();
        if(terminalState == null) System.out.println("Could not find a solution.");
        else
        {
			// print the path from beggining to start.
            State temp = terminalState; // begin from the end.
            ArrayList<State> path = new ArrayList<>();
			path.add(terminalState);
            while(temp.getFather() != null) // if father is null, then we are at the root.
            {
                path.add(temp.getFather());
                temp = temp.getFather();
            }
			// reverse the path and print.
            Collections.reverse(path);
            for(State item: path)
            {
                item.print();
            }
            System.out.println();
            System.out.println("Search time:" + (double)(end - start) / 1000 + " sec.");  // total time of searching in seconds.
        }
    }
}
