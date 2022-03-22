package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

public class ListAppsDTO {

    @NonNull
    private final InfoDTO info;
    @NonNull
    private final DatasetsDTO datasets;

    public ListAppsDTO(@NonNull InfoDTO info, @NonNull DatasetsDTO datasets) {
        this.info = info;
        this.datasets = datasets;
    }

    public InfoDTO getInfo() {
        return info;
    }

    @NonNull
    public DatasetsDTO getDatasets() {
        return datasets;
    }
}
