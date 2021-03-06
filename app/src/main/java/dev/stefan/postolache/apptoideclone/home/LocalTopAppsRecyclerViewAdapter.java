package dev.stefan.postolache.apptoideclone.home;

import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import dev.stefan.postolache.apptoideclone.R;
import dev.stefan.postolache.apptoideclone.databinding.FragmentHomeLocalTopAppsListItemBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static dev.stefan.postolache.apptoideclone.home.EditorsChoiceRecyclerViewAdapter.*;

public class LocalTopAppsRecyclerViewAdapter extends RecyclerView.Adapter<LocalTopAppsRecyclerViewAdapter.AppViewHolder> {

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        public AppDTO mItem;
        public final ImageView appIconImageView;
        public final TextView appNameTextView;
        public final TextView appRatingTextView;

        public AppViewHolder(FragmentHomeLocalTopAppsListItemBinding binding) {
            super(binding.getRoot());
            appIconImageView = binding.appIcon;
            appNameTextView = binding.appName;
            appRatingTextView = binding.rating;
        }
    }

    private List<AppDTO> mItems;
    private int mItemCount;
    private final DisplayMetrics mMetrics;
    private final OnAppCardClickedListener mListener;


    public LocalTopAppsRecyclerViewAdapter(DisplayMetrics metrics, OnAppCardClickedListener listener) {
        mMetrics = metrics;
        mListener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        FragmentHomeLocalTopAppsListItemBinding binding = FragmentHomeLocalTopAppsListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.getRoot().getLayoutParams().width = (int) (mMetrics.widthPixels * 0.28); // each item's width is 28% of screen
        return new AppViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LocalTopAppsRecyclerViewAdapter.AppViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.appNameTextView.setText(holder.mItem.name);
        holder.appRatingTextView.setText(String.valueOf(holder.mItem.rating));
        holder.itemView.setOnClickListener(view -> mListener.showDetailsForApp(holder.mItem));
        Picasso.get()
                .load(holder.mItem.icon)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .centerInside()
                .into(holder.appIconImageView);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItemCount : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<AppDTO> items) {
        mItems = items;
        mItemCount = items.size();
        notifyDataSetChanged();
    }
}
