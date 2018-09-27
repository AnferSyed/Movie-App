package sample.com.webservice.common;

import android.content.Context;

import retrofit2.Call;
import sample.com.webservice.common.data.MovieResponseData;
import sample.com.webservice.common.service.CallBackInterface;
import sample.com.webservice.common.service.CallResponse;
import sample.com.webservice.common.service.ServiceUtil;
import sample.com.webservice.common.service.data.ServiceNames;
import sample.com.webservice.common.service.endpoint.FilterMoviesByReleaseYearRequestEndpoint;
import sample.com.webservice.common.service.endpoint.LatestMoviesRequestEndpoint;
import sample.com.webservice.common.util.NetworkUtil;

/**
 * Created by anfer on 24-Sep-18.
 */

public class MoviesServiceImpl {

    /**
     * fetchLatestMovies
     *
     * @param callBackInterface
     */
    public void fetchLatestMovies(CallBackInterface callBackInterface, int currentPage) {
        MovieResponseData movieResponseData = new MovieResponseData();
        LatestMoviesRequestEndpoint latestMoviesRequestEndpoint = (LatestMoviesRequestEndpoint) ServiceUtil.getService(ServiceUtil.SVC_FETCH_LATEST_MOVIES);
        Call latestMoviesCall = latestMoviesRequestEndpoint.fetchLatestMovies(ServiceNames.API_KEY, currentPage);
        CallResponse callBack = new CallResponse(callBackInterface, movieResponseData);
        latestMoviesCall.enqueue(callBack);
    }

    /**
     * filterMoviesByReleaseYear
     *
     * @param callBackInterface
     */
    public void filterMoviesByReleaseYear(CallBackInterface callBackInterface, int currentPage, int releaseYear) {
        MovieResponseData movieResponseData = new MovieResponseData();
        FilterMoviesByReleaseYearRequestEndpoint filterMoviesByReleaseYearEndpoint = (FilterMoviesByReleaseYearRequestEndpoint)
                ServiceUtil.getService(ServiceUtil.SVC_FILTER_MOVIES_BY_RELEASE_YEAR);
        Call filterMoviesCall = filterMoviesByReleaseYearEndpoint.fetchMoviesByReleaseYear(ServiceNames.API_KEY, currentPage, releaseYear);
        CallResponse callBack = new CallResponse(callBackInterface, movieResponseData);
        filterMoviesCall.enqueue(callBack);
    }

    /**
     * isConnected
     *
     * @param context
     * @return
     */
    public boolean isConnected(Context context) {
        return NetworkUtil.isConnected(context);
    }
}
