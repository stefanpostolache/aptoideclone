package dev.stefan.postolache.apptoideclone.home;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import dev.stefan.postolache.apptoideclone.R;
import dev.stefan.postolache.apptoideclone.databinding.FragmentHomeBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import io.reactivex.rxjava3.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public interface OnFragmentInteractionListener {
        void showDetailsOf(AppDTO appDTO);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel mViewModel;

    private Disposable mDataFetchDisposable;
    private Disposable mFailureDisposable;

    private FragmentHomeBinding mBinding;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new InvalidParameterException("Activity must implement HomeFragment.OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentHomeBinding.inflate(inflater, container, false);

        DisplayMetrics metrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setupRecyclerView(mBinding.editorsChoiceList, new EditorsChoiceRecyclerViewAdapter(metrics, mListener::showDetailsOf));

        setupRecyclerView(mBinding.localTopAppsList, new LocalTopAppsRecyclerViewAdapter(metrics, mListener::showDetailsOf));

        mFailureDisposable = mViewModel.hasFailedDataRetrieval()
                .subscribe(this::didFailRetrievingData);

        mDataFetchDisposable = mViewModel
                .getAppData()
                .subscribe(
                    this::didReceiveAppData
                );

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataFetchDisposable.dispose();
        mFailureDisposable.dispose();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

    /**
     * Prepares Recyclerview to be displayed in the HomeFragment
     * @param list a recycler view
     * @param adapter a recycler view adapter
     */
    public void setupRecyclerView(RecyclerView list, RecyclerView.Adapter adapter) {
        int spacing = requireActivity().getResources().getDimensionPixelSize(R.dimen.spacing_margin);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);

        list.setLayoutManager(layoutManager);

        list.addItemDecoration(new AppItemDecoration(spacing));

        list.setAdapter(adapter);
    }

    /**
     * Handles successful retrieval of app data
     * @param apps list of apps to be displayed in the home page
     */
    public void didReceiveAppData(List<AppDTO> apps) {
        EditorsChoiceRecyclerViewAdapter editorsChoiceAdapter = (EditorsChoiceRecyclerViewAdapter) mBinding.editorsChoiceList.getAdapter();
        if (editorsChoiceAdapter != null) {
            editorsChoiceAdapter.setItems(apps.subList(0, 5));
        }
        LocalTopAppsRecyclerViewAdapter localTopAppsAdapter = (LocalTopAppsRecyclerViewAdapter) mBinding.localTopAppsList.getAdapter();
        if (localTopAppsAdapter != null) {
            localTopAppsAdapter.setItems(apps.subList(5, apps.size()-1));
        }
    }

    /**
     * Handles failure of data fetch by displaying a snackbar, which
     * enables the user to retry the connection.
     * @param failure emitted failure from FailedDataRetrieval subject
     */
    public void didFailRetrievingData(Boolean failure) {
        Snackbar.make(mBinding.getRoot(),
                        R.string.fragment_home_error_message,
                        Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.fragment_home_try_again, view -> mViewModel.retryDownload())
                .show();
    }
}