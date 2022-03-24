package dev.stefan.postolache.apptoideclone.appdetails;

import android.os.Bundle;
import android.text.format.Formatter;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import dev.stefan.postolache.apptoideclone.databinding.FragmentAppDetailsBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    private AppDTO mApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDetailsFragmentArgs args = AppDetailsFragmentArgs.fromBundle(getArguments());
        mApp = args.getApp();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAppDetailsBinding mBinding = FragmentAppDetailsBinding.inflate(inflater, container, false);
        mBinding.setAppName(mApp.name);
        mBinding.setStoreName(mApp.storeName);

        Picasso.get()
                .load(mApp.icon)
                .fit()
                .centerInside()
                .into(mBinding.AppIcon);

        mBinding.setRating(mApp.rating);
        mBinding.setDownloads(NumberFormat.getNumberInstance().format(mApp.downloads));
        mBinding.setSize(Formatter.formatFileSize(requireActivity(), mApp.size));

        mBinding.setPackageName(mApp.packageName);
        mBinding.setVersionName(mApp.verName);
        LocalDateTime dateTime = LocalDateTime.parse(mApp.updated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        mBinding.setUpdatedAt(dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return mBinding.getRoot();
    }


}