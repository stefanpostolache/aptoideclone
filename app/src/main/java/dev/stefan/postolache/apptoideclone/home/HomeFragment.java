package dev.stefan.postolache.apptoideclone.home;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import dev.stefan.postolache.apptoideclone.R;
import dev.stefan.postolache.apptoideclone.databinding.FragmentHomeBinding;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import dev.stefan.postolache.apptoideclone.networking.dtos.ResultDTO;
import io.reactivex.rxjava3.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel mViewModel;

    private Disposable mDisposable;

    private NavController mNavController;
    private FragmentHomeBinding mBinding;

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

        mNavController = Navigation.findNavController(container);
        setupRecyclerView(mBinding.editorsChoiceList, new EditorsChoiceRecyclerViewAdapter(metrics,
                app -> mNavController.navigate(HomeFragmentDirections.actionHomeFragmentToAppDetailsFragment(app))));
        setupRecyclerView(mBinding.localTopAppsList, new LocalTopAppsRecyclerViewAdapter(metrics,
                app -> mNavController.navigate(HomeFragmentDirections.actionHomeFragmentToAppDetailsFragment(app))));

        mViewModel.getFailedDataRetrieval().observe(this, didFail -> {
            if (didFail) didFailRetrievingData();
        });

        mDisposable = mViewModel
                .getAppData()
                .subscribe(
                    this::didReceiveAppData
                );

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
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
     * @param result the result of the network call
     */
    public void didReceiveAppData(ResultDTO result) {
        List<AppDTO> apps = result
                .getResponses()
                .getListApps()
                .getDatasets()
                .getAll()
                .getData()
                .getList();
        EditorsChoiceRecyclerViewAdapter editorsChoiceAdapter = (EditorsChoiceRecyclerViewAdapter) mBinding.editorsChoiceList.getAdapter();
        if (editorsChoiceAdapter != null) {
            editorsChoiceAdapter.setItems(apps.subList(0, 5));
        }
        LocalTopAppsRecyclerViewAdapter localTopAppsAdapter = (LocalTopAppsRecyclerViewAdapter) mBinding.localTopAppsList.getAdapter();
        if (localTopAppsAdapter != null) {
            localTopAppsAdapter.setItems(apps.subList(5, apps.size()-1));
        }
    }

    public void didFailRetrievingData() {

        Snackbar.make(mBinding.getRoot(),
                        R.string.fragment_home_error_message,
                        Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.fragment_home_try_again, view -> mViewModel.retryDownload())
                .show();
    }
}