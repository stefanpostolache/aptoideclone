package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class TimeDTO {

    private double seconds;
    @NonNull
    private String human;

    public TimeDTO(double seconds, @NonNull String human) {
        this.seconds = seconds;
        this.human = human;
    }

    public double getSeconds() {
        return seconds;
    }

    public String getHuman() {
        return human;
    }
}
