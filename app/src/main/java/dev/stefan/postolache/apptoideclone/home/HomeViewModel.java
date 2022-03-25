package dev.stefan.postolache.apptoideclone.home;

import androidx.lifecycle.ViewModel;
import dev.stefan.postolache.apptoideclone.K;
import dev.stefan.postolache.apptoideclone.networking.AptoideClientHolder;
import dev.stefan.postolache.apptoideclone.networking.AptoideService;
import dev.stefan.postolache.apptoideclone.networking.dtos.AppDTO;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.List;

public class HomeViewModel extends ViewModel {

    /**
     * Aptoide Api client
     */
    private final AptoideService mService;

    private final PublishSubject<Boolean> mFailedDataRetrieval = PublishSubject.create(); // subject to notify data fetch failures

    private final PublishSubject<Object> mRetrySubject = PublishSubject.create(); // subject to push retry signals

    public HomeViewModel() {
        mService = AptoideClientHolder.INSTANCE.getAptoideService();
    }

    public PublishSubject<Boolean> hasFailedDataRetrieval() {
        return mFailedDataRetrieval;
    }

    /**
     * Fetches app data.
     *
     * If the connection fails it retries the connection 2 times, and if this still fails it notifies subscribers of
     * failure, and waits for signal to retry the connection.
     *
     * @return an observable of a list of apps which will be retrieved by the server.
     */
    public Observable<List<AppDTO>> getAppData() {

        return mService
                .fetchApps(K.APP_DATA_URL)
                .retry(2) // retry connection 2 times
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.getResponses().getListApps().getDatasets().getAll().getData().getList()) // get apps from server response
                .doOnError(throwable -> mFailedDataRetrieval.onNext(true)) // if connection still fails notify
                .retryWhen(errors -> errors.zipWith(mRetrySubject, (i, n) -> i)); //  wait for retry signal

    }

    /**
     * Triggers retrial of the connection
     */
    public void retryDownload() {
        mRetrySubject.onNext(new Object());
    }


}
