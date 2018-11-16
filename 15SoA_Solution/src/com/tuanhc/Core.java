package com.tuanhc;

import com.tuanhc.model.State;

import java.util.ArrayList;

public class Core {
    ArrayList<State> open;
    ArrayList<State> close;

    public Core() {
        open = new ArrayList<>();
        close = new ArrayList<>();
    }

    public void start(State start) {
        open.add(start);
        while (open.size() != 0) {
            State state0 = open.get(0);
            open.remove(0);

            if (!existIn(state0, close))
                close.add(state0);

            if (state0.isGoal()) {
                System.out.println("Found Goal!, Step = " + state0.g);
                state0.println();
                break;
            }

            State newState;
            newState = state0.moveUp();
            if (newState != null) {
                if (!existIn(newState, open) && !existIn(newState, close)) {
                    newState.parent = state0;
                    addToOpen(newState);
                }
            }

            newState = state0.moveDown();
            if (newState != null) {
                if (!existIn(newState, open) && !existIn(newState, close)) {
                    newState.parent = state0;
                    addToOpen(newState);
                }
            }

            newState = state0.moveLeft();
            if (newState != null) {
                if (!existIn(newState, open) && !existIn(newState, close)) {
                    newState.parent = state0;
                    addToOpen(newState);
                }
            }

            newState = state0.moveRight()   ;
            if (newState != null) {
                if (!existIn(newState, open) && !existIn(newState, close)) {
                    newState.parent = state0;
                    addToOpen(newState);
                }
            }
        }
    }

    public boolean existIn(State state, ArrayList<State> states) {
        for(int i = 0; i < states.size(); i++) {
            if(state.equals(states.get(i)))
                return true;
        }
        return false;
    }

    void addToOpen(State state) {
        int i;
        for(i = 0; i < open.size(); i++) {
            if (open.get(0).getFx() > state.getFx())
                break;
        }
        if (i == open.size() - 1)
            i++;
        open.add(i, state);
    }
}
