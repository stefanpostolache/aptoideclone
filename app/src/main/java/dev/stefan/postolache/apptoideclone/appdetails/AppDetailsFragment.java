package dev.stefan.postolache.apptoideclone.appdetails;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dev.stefan.postolache.apptoideclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_details, container, false);
    }
}