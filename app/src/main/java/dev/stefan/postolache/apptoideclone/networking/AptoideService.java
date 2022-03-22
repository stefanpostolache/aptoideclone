package dev.stefan.postolache.apptoideclone.networking;


import dev.stefan.postolache.apptoideclone.networking.dtos.ResultDTO;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface AptoideService {

    @GET
    Observable<ResultDTO> fetchApps(@Url String url);

}
