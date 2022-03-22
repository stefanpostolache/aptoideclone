package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class InfoDTO {
    @NonNull
    private final String status;
    @NonNull
    private final TimeDTO time;

    public InfoDTO(@NonNull String status, @NonNull TimeDTO time) {
        this.status = status;
        this.time = time;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    @NonNull
    public TimeDTO getTime() {
        return time;
    }
}
