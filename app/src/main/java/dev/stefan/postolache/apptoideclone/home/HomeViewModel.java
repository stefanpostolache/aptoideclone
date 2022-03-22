package dev.stefan.postolache.apptoideclone.home;

import androidx.lifecycle.ViewModel;
import dev.stefan.postolache.apptoideclone.K;
import dev.stefan.postolache.apptoideclone.networking.AptoideClientHolder;
import dev.stefan.postolache.apptoideclone.networking.AptoideService;
import dev.stefan.postolache.apptoideclone.networking.dtos.ResultDTO;
import io.reactivex.rxjava3.core.Observable;

public class HomeViewModel extends ViewModel {

    private final AptoideService mService;

    public HomeViewModel() {
        mService = AptoideClientHolder.INSTANCE.getAptoideService();
    }

    public Observable<ResultDTO> getAppData() {
        return mService
                .fetchApps(K.APP_DATA_URL);
    }
}
