package gol.controller;

import javafx.util.Duration;

public enum Speed
{
    SLOWEST("slowest", Duration.millis(1750)),
    SLOWER("slower", Duration.millis(1500)),
    SLOW("slow", Duration.millis(1250)),
    NORMAL("normal", Duration.millis(1000)),
    FAST("fast", Duration.millis(750)),
    FASTER("faster", Duration.millis(500)),
    FASTEST("fastest", Duration.millis(250)),
    ULTRAFAST("ultrafast", Duration.millis(100));

    private String title;
    private Duration duration;

    Speed(String title, Duration duration)
    {
        this.title = title;
        this.duration = duration;
    }

    public Duration getDuration()
    {
        return duration;
    }

    @Override
    public String toString()
    {
        return title + " (" + duration.toSeconds() + " s)";
    }
}
