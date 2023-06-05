import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class SpaceSearcher {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;

    SpaceSearcher()
    {
        this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
    }  


    public State ASTAR(State initialState, int heuristic,int m)
    {
        if(initialState.isFinal(m)) return initialState;
        // step 1: put initial state in the frontier.
        this.frontier.add(initialState);
        // step 2: check for empty frontier.
        while(this.frontier.size() > 0)
        {
            // step 3: get the first node out of the frontier.
            State currentState = this.frontier.remove(0);
            // step 4: if final state, return.
            if(currentState.isFinal(m)) return currentState;
            // step 5: put the children at the frontier
            this.frontier.addAll(currentState.getChildren(heuristic));
            // step 6: sort the frontier based on the heuristic score to get best as first
            Collections.sort(this.frontier);
        }
                return null;
    }



    public State ASTARClosed(State initialState, int heuristic,int m)
    {
        if(initialState.isFinal(m)) return initialState;
        // step 1: put initial state in the frontier.
        this.frontier.add(initialState);
        // step 2: check for empty frontier.
        while(this.frontier.size() > 0)
        {
            // step 3: get the first node out of the frontier.
            State currentState = this.frontier.remove(0);
            // step 4: if final state, return.
            if(currentState.isFinal(m)) return currentState;
            // step 5: put the children at the frontier
            if(!this.closedSet.contains(currentState)){
                
                this.closedSet.add(currentState);
                this.frontier.addAll(currentState.getChildren(heuristic));
                // step 6: sort the frontier based on the heuristic score to get best as first
                Collections.sort(this.frontier);
            }
        }
                return null;
    }
}