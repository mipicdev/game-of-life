package gol.model;

public class Cell
{
    private State nextState = State.DEAD;
    private State currentState;

    Cell(State currentState)
    {
        this.currentState = currentState;
    }

    void turnAlive()
    {
        nextState = State.ALIVE;
    }

    void turnDead()
    {
        nextState = State.DEAD;
    }

    void staySameState()
    {
        nextState = currentState;
    }

    void updateCurrentState()
    {
        currentState = nextState;
    }

    public boolean isAlive()
    {
        return currentState.equals(State.ALIVE);
    }

    public boolean isDead()
    {
        return currentState.equals(State.DEAD);
    }

    public boolean isBorder()
    {
        return currentState.equals(State.BORDER);
    }

    @Override
    public String toString()
    {
        return currentState.toString();
    }
}
