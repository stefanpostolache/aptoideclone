package dev.stefan.postolache.apptoideclone.home;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.stefan.postolache.apptoideclone.databinding.FragmentHomeBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import org.jetbrains.annotations.NotNull;

public class LocalTopAppsRecyclerViewAdapter extends RecyclerView.Adapter<LocalTopAppsRecyclerViewAdapter.AppViewHolder> {

    public LocalTopAppsRecyclerViewAdapter() {
    }

    @NonNull
    @NotNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LocalTopAppsRecyclerViewAdapter.AppViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        public AppDTO mItem;

        public AppViewHolder(FragmentHomeBinding binding) {
            super(binding.getRoot());

        }
    }

}
