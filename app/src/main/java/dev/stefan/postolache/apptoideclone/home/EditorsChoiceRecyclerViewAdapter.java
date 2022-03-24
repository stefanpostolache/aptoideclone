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
import dev.stefan.postolache.apptoideclone.databinding.FragmentHomeEditorsChoiceListItemBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EditorsChoiceRecyclerViewAdapter extends RecyclerView.Adapter<EditorsChoiceRecyclerViewAdapter.AppViewHolder> {

    public interface OnAppCardClickedListener {
        void showDetailsForApp(AppDTO app);
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {
        public final ImageView appGraphic;
        public final TextView appName;
        public final TextView rating;
        public AppDTO mItem;

        public AppViewHolder(FragmentHomeEditorsChoiceListItemBinding binding) {
            super(binding.getRoot());
            appGraphic = binding.applicationGraphic;
            appName = binding.applicationName;
            rating = binding.applicationRating;
        }
    }

    private List<AppDTO> mItems;
    private int mItemCount = 0;
    private final DisplayMetrics mMetrics;
    private final OnAppCardClickedListener mListener;

    public EditorsChoiceRecyclerViewAdapter(DisplayMetrics metrics, OnAppCardClickedListener listener) {
        mMetrics = metrics;
        mListener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        FragmentHomeEditorsChoiceListItemBinding binding = FragmentHomeEditorsChoiceListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.getRoot().getLayoutParams().width = (int) (mMetrics.widthPixels * 0.9);
        return new AppViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EditorsChoiceRecyclerViewAdapter.AppViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.appName.setText(holder.mItem.name);
        holder.rating.setText(String.valueOf(holder.mItem.rating));
        holder.itemView.setOnClickListener(view -> mListener.showDetailsForApp(holder.mItem));
        Picasso.get()
                .load(holder.mItem.graphic)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(holder.appGraphic);
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<AppDTO> items) {
        mItems = items;
        mItemCount = mItems.size();
        notifyDataSetChanged();
    }
}
