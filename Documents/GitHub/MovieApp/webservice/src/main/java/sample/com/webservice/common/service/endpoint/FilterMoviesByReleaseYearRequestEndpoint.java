package sample.com.webservice.common.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sample.com.webservice.common.data.MovieResponseData;

/**
 * Created by anfer on 24-Sep-18.
 */

public interface FilterMoviesByReleaseYearRequestEndpoint {

    @GET("discover/movie")
    Call<MovieResponseData> fetchMoviesByReleaseYear(@Query("api_key") String apiKey, @Query("page")
            int pageIndex, @Query("primary_release_year") int releaseYear);
}
