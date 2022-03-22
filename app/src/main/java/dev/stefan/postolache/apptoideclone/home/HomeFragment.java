package dev.stefan.postolache.apptoideclone.home;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import dev.stefan.postolache.apptoideclone.R;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import timber.log.Timber;

import java.io.IOException;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDisposable = mViewModel
                .getAppData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> Timber.d(String.valueOf(result
                                .getResponses()
                                .getListApps()
                                .getDatasets()
                                .getAll()
                                .getData()
                                .getList())),
                        throwable -> Timber.e(throwable.getLocalizedMessage()),
                        () -> Timber.d("Should not display on complete")
                );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
    }
}