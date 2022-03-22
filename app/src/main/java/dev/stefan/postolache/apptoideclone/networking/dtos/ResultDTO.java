package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class ResultDTO {

    @NonNull
    private final String status;
    @NonNull
    private final ResponsesDTO responses;

    public ResultDTO(@NonNull String status, @NonNull ResponsesDTO responses) {
        this.status = status;
        this.responses = responses;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    @NonNull
    public ResponsesDTO getResponses() {
        return responses;
    }
}
