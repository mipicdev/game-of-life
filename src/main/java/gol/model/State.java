package gol.model;

public enum State
{
    ALIVE("@"), DEAD(" "), BORDER("-");

    private final String name;

    State(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
