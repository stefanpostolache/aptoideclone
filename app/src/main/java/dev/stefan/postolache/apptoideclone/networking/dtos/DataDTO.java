package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;

import java.util.List;

public class DataDTO {
    private final long total;
    private final int offset;
    private final int limit;
    private final int next;
    private final int hidden;
    @NonNull
    private final List<AppDTO> list;

    public DataDTO(long total, int offset, int limit, int next, int hidden, @NonNull List<AppDTO> list) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.next = next;
        this.hidden = hidden;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getNext() {
        return next;
    }

    public int getHidden() {
        return hidden;
    }

    @NonNull
    public List<AppDTO> getList() {
        return list;
    }
}
