package dev.stefan.postolache.apptoideclone.networking;

import com.squareup.moshi.Moshi;
import dev.stefan.postolache.apptoideclone.K;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import hu.akarnokd.rxjava3.retrofit.*;

import java.sql.Timestamp;

/**
 * A Singleton class that builds and provides the Retrofit client for the Aptoide API
 */
public class AptoideClientHolder {

    public static AptoideClientHolder INSTANCE = new AptoideClientHolder();

    AptoideService aptoideService;

    private AptoideClientHolder() {

        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(K.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
                .build();
        aptoideService = retrofit.create(AptoideService.class);

    }

    public AptoideService getAptoideService() {
        return aptoideService;
    }
}
