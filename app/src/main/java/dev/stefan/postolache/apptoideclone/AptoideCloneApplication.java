package dev.stefan.postolache.apptoideclone;

import android.app.Application;
import timber.log.Timber;

public class AptoideCloneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
