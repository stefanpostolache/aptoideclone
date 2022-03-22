package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class DatasetsDTO {

    @NonNull
    private final AllDTO all;

    public DatasetsDTO(AllDTO all) {
        this.all = all;
    }

    @NonNull
    public AllDTO getAll() {
        return all;
    }
}
