package dev.stefan.postolache.apptoideclone;

import android.app.Application;
import timber.log.Timber;

/**
 * Custom Application instance to attach Timber debug tree
 */
public class AptoideCloneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // I use Timber because it reduces boiler plate code such as TAG constant and it is simpler to prepare for
        // deployment as we just need not to plant the DebugTree
        Timber.plant(new Timber.DebugTree());
    }
}
