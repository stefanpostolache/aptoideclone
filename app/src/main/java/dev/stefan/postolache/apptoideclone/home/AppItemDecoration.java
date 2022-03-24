package dev.stefan.postolache.apptoideclone.home;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class AppItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpacing;

    public AppItemDecoration(int spacing) {
        super();
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(
            @NonNull @NotNull Rect outRect,
            @NonNull @NotNull View view,
            @NonNull @NotNull RecyclerView parent,
            @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // adding spacing of 8dp between items
        outRect.top = mSpacing;
        outRect.bottom = mSpacing;
        outRect.right = mSpacing;
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = mSpacing;
        }
    }
}
