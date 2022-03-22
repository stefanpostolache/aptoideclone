package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class AllDTO {

    @NonNull
    private final InfoDTO info;
    @NonNull
    private final DataDTO data;

    public AllDTO(@NonNull InfoDTO info, @NonNull DataDTO data) {
        this.info = info;
        this.data = data;
    }

    @NonNull
    public InfoDTO getInfo() {
        return info;
    }

    @NonNull
    public DataDTO getData() {
        return data;
    }
}
