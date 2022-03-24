package dev.stefan.postolache.apptoideclone.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dev.stefan.postolache.apptoideclone.K;
import dev.stefan.postolache.apptoideclone.networking.AptoideClientHolder;
import dev.stefan.postolache.apptoideclone.networking.AptoideService;
import dev.stefan.postolache.apptoideclone.networking.dtos.ResultDTO;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class HomeViewModel extends ViewModel {

    private final AptoideService mService;

    private final MutableLiveData<Boolean> mFailedDataRetrieval = new MutableLiveData<>(false);

    private final PublishSubject<Object> mRetrySubject = PublishSubject.create();

    public HomeViewModel() {
        mService = AptoideClientHolder.INSTANCE.getAptoideService();
    }

    public LiveData<Boolean> getFailedDataRetrieval() {
        return mFailedDataRetrieval;
    }

    public Observable<ResultDTO> getAppData() {

        return mService
                .fetchApps(K.APP_DATA_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mFailedDataRetrieval.setValue(true))
                .retryWhen(errors -> errors.zipWith(mRetrySubject, (i, n) -> i));

    }

    public void retryDownload() {
        mFailedDataRetrieval.setValue(false);
        mRetrySubject.onNext(new Object());
    }


}
