package sample.com.webservice.common.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sample.com.webservice.BuildConfig;
import sample.com.webservice.common.data.MovieResponseData;
import sample.com.webservice.common.service.data.ServiceNames;

/**
 * Created by anfer on 24-Sep-18.
 */

public interface LatestMoviesRequestEndpoint {

    @GET("movie/popular")
    Call<MovieResponseData> fetchLatestMovies(@Query("api_key") String apiKey, @Query("page") int pageIndex);
}
