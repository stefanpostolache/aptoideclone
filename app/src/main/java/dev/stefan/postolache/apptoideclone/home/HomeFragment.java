package dev.stefan.postolache.apptoideclone.home;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
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
import dev.stefan.postolache.apptoideclone.R;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressWarnings("DuplicatedCode")
public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel mViewModel;

    private Disposable mDisposable;

    private RecyclerView mEditorsChoiceRecyclerView;
    private RecyclerView mLocalTopAppsRecyclerView;

    private NavController mNavController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setupEditorsChoiceRecyclerView(view);
        setupLocalTopAppsRecyclerView(view);
        mNavController = Navigation.findNavController(container);
        mDisposable = mViewModel
                .getAppData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    result -> {
                        List<AppDTO> apps = result
                            .getResponses()
                            .getListApps()
                            .getDatasets()
                            .getAll()
                            .getData()
                            .getList();
                        EditorsChoiceRecyclerViewAdapter editorsChoiceAdapter = (EditorsChoiceRecyclerViewAdapter) mEditorsChoiceRecyclerView.getAdapter();
                        if (editorsChoiceAdapter != null) {
                            editorsChoiceAdapter.setItems(apps.subList(0, 5));
                        }
                        LocalTopAppsRecyclerViewAdapter localTopAppsAdapter = (LocalTopAppsRecyclerViewAdapter) mLocalTopAppsRecyclerView.getAdapter();
                        if (localTopAppsAdapter != null) {
                            localTopAppsAdapter.setItems(apps.subList(5, apps.size()-1));
                        }
                    },
                    throwable -> Timber.e(throwable.getLocalizedMessage())
                );
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Timber.d("Options menu inflating");
        inflater.inflate(R.menu.action_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

    public void setupEditorsChoiceRecyclerView(View view) {
        mEditorsChoiceRecyclerView = view.findViewById(R.id.editors_choice_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        mEditorsChoiceRecyclerView.setLayoutManager(layoutManager);
        mEditorsChoiceRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                // adding spacing of 8dp between items
                int spacing = requireActivity().getResources().getDimensionPixelSize(R.dimen.spacing_margin);
                outRect.top = spacing;
                outRect.bottom = spacing;
                outRect.right = spacing;
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = spacing;
                }
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();

        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mEditorsChoiceRecyclerView.setAdapter(new EditorsChoiceRecyclerViewAdapter(metrics, app -> {
            mNavController.navigate(HomeFragmentDirections.actionHomeFragmentToAppDetailsFragment());
        }));
    }

    public void setupLocalTopAppsRecyclerView(View view) {
        mLocalTopAppsRecyclerView = view.findViewById(R.id.local_top_apps_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        mLocalTopAppsRecyclerView.setLayoutManager(layoutManager);
        mLocalTopAppsRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view,
                                       @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int spacing = requireActivity().getResources().getDimensionPixelSize(R.dimen.spacing_margin);
                outRect.top = spacing;
                outRect.bottom = spacing;
                outRect.right = spacing;
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = spacing;
                }
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mLocalTopAppsRecyclerView.setAdapter(new LocalTopAppsRecyclerViewAdapter(metrics, app -> {
            mNavController.navigate(HomeFragmentDirections.actionHomeFragmentToAppDetailsFragment());
        }));
    }
}