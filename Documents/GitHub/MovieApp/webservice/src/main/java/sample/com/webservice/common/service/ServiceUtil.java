package sample.com.webservice.common.service;

import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sample.com.webservice.BuildConfig;
import sample.com.webservice.common.service.data.ServiceNames;
import sample.com.webservice.common.service.endpoint.FilterMoviesByReleaseYearRequestEndpoint;
import sample.com.webservice.common.service.endpoint.LatestMoviesRequestEndpoint;

/**
 * Created by anfer on 24-Sep-18.
 */

public class ServiceUtil {

    /**
     * serviceMap
     */
    private static Map<String, Object> serviceMap = new HashMap<String, Object>();

    /**
     * SVC_FETCH_LATEST_MOVIES
     */
    public static String SVC_FETCH_LATEST_MOVIES = "LATEST_MOVIES";

    /**
     * SVC_FILTER_MOVIES_BY_RELEASE_YEAR
     */
    public static String SVC_FILTER_MOVIES_BY_RELEASE_YEAR = "MOVIES_BY_RELEASE_YEAR";

    /**
     * retrofit
     */
    private static Retrofit retrofit = null;

    /**
     * okHttpClient
     */
    public static OkHttpClient okHttpClient =
            new OkHttpClient.Builder().readTimeout(180, TimeUnit.SECONDS).connectTimeout(180, TimeUnit.SECONDS)
                    .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)).build();

    /**
     * get Client
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            retrofit = new Retrofit.Builder().baseUrl(ServiceNames.BASE_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create())).build();

        }
        return retrofit;
    }

    /**
     * getService
     *
     * @return
     */
    public static Object getService(String requestSvc) {

        if (ServiceUtil.SVC_FETCH_LATEST_MOVIES.equals(requestSvc)) {

            if (!serviceMap.containsKey(ServiceUtil.SVC_FETCH_LATEST_MOVIES)) {
                LatestMoviesRequestEndpoint latestMoviesRequestEndpoint = ServiceUtil.getClient().create(LatestMoviesRequestEndpoint.class);
                serviceMap.put(ServiceUtil.SVC_FETCH_LATEST_MOVIES, latestMoviesRequestEndpoint);
            }
            return serviceMap.get(ServiceUtil.SVC_FETCH_LATEST_MOVIES);
        } else if (ServiceUtil.SVC_FILTER_MOVIES_BY_RELEASE_YEAR.equals(requestSvc)) {
            if (!serviceMap.containsKey(ServiceUtil.SVC_FILTER_MOVIES_BY_RELEASE_YEAR)) {
                FilterMoviesByReleaseYearRequestEndpoint moviesByReleaseYearRequestEndpoint = ServiceUtil.getClient().create(FilterMoviesByReleaseYearRequestEndpoint.class);
                serviceMap.put(ServiceUtil.SVC_FILTER_MOVIES_BY_RELEASE_YEAR, moviesByReleaseYearRequestEndpoint);
            }
            return serviceMap.get(ServiceUtil.SVC_FILTER_MOVIES_BY_RELEASE_YEAR);
        }
        return null;
    }
}
