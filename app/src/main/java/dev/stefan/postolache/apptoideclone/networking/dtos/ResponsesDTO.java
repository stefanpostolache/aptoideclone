package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class ResponsesDTO {

    @NonNull
    private final ListAppsDTO listApps;

    public ResponsesDTO(@NonNull ListAppsDTO listApps) {
        this.listApps = listApps;
    }

    public ListAppsDTO getListApps() {
        return listApps;
    }
}
