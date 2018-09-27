package sample.com.movieapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Calendar;

import sample.com.movieapp.R;
import sample.com.movieapp.common.adapter.RecyclerViewAdapter;
import sample.com.movieapp.common.view.BaseAppCompatActivity;
import sample.com.movieapp.util.InfiniteScrollListener;
import sample.com.webservice.common.MoviesServiceImpl;
import sample.com.webservice.common.data.MovieResponseData;

public class MainActivity extends BaseAppCompatActivity {

    /**
     * recyclerViewAdapter
     */
    private RecyclerViewAdapter recyclerViewAdapter;

    /**
     * linearLayoutManager
     */
    private LinearLayoutManager linearLayoutManager;

    /**
     * fabFilter
     */
    private FloatingActionButton fabFilter;

    /**
     * recyclerView
     */
    private RecyclerView recyclerView;

    /**
     * progressBar
     */
    private ProgressBar progressBar;

    /**
     * PAGE_START
     */
    private static final int PAGE_START = 1;

    /**
     * isLoading
     */
    private boolean isLoading = false;

    /**
     * isLastPage
     */
    private boolean isLastPage = false;

    /**
     * year
     */
    private int year = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * filterYear
     */
    private int filterYear;

    /**
     * TOTAL_PAGES
     */
    private int TOTAL_PAGES;

    /**
     * latestMoviesCurrentPage
     */
    private int latestMoviesCurrentPage = PAGE_START;

    /**
     * filterMoviesCurrentPage
     */
    private int filterMoviesCurrentPage = PAGE_START;

    /**
     * loadLatestMoviesFlag
     */
    private boolean loadLatestMoviesFlag = true;

    /**
     * moviesService
     */
    private MoviesServiceImpl moviesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViewToActivity();
        setRecyclerViewAdapter();

        recyclerView.addOnScrollListener(new InfiniteScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                if (loadLatestMoviesFlag) {
                    latestMoviesCurrentPage += 1;
                    loadLatestMovies();
                } else {
                    filterMoviesCurrentPage += 1;
                    filterMoviesByReleaseDate(filterYear);
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        fabFilter.setOnClickListener(v -> showYearDialog());

        loadLatestMovies();
    }

    /**
     * loadLatestMovies
     */
    private void loadLatestMovies() {

        loadLatestMoviesFlag = true;
        if (moviesService.isConnected(this)) {
            Log.d("MovieApp", "Latest Movies Current Page: " + latestMoviesCurrentPage);
            moviesService.fetchLatestMovies(object -> {
                MovieResponseData responseData = (MovieResponseData) object;
                TOTAL_PAGES = Integer.valueOf(responseData.getTotal_pages());
                if (latestMoviesCurrentPage == 1) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    recyclerViewAdapter.removeLoadingFooter();
                    isLoading = false;
                }
                if (responseData.isSuccess()) {
                    recyclerViewAdapter.addAll(responseData.getResults());

                    if (latestMoviesCurrentPage <= TOTAL_PAGES)
                        recyclerViewAdapter.addLoadingFooter();
                    else isLastPage = true;
                } else {
                    showToast(responseData.getErrorMessage(), Toast.LENGTH_SHORT);
                }
            }, latestMoviesCurrentPage);
        } else {
            showToast("Not Connected to Internet!", Toast.LENGTH_SHORT);
        }
    }

    /**
     * filterMoviesByReleaseDate
     *
     * @param releaseYear
     */
    private void filterMoviesByReleaseDate(int releaseYear) {

        loadLatestMoviesFlag = false;
        if (moviesService.isConnected(this)) {
            Log.d("MovieApp", "Filtered Movies Current Page: " + filterMoviesCurrentPage);
            moviesService.filterMoviesByReleaseYear(object -> {
                MovieResponseData responseData = (MovieResponseData) object;
                TOTAL_PAGES = Integer.valueOf(responseData.getTotal_pages());
                if (filterMoviesCurrentPage == 1) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    recyclerViewAdapter.removeLoadingFooter();
                    isLoading = false;
                }
                if (responseData.isSuccess()) {
                    recyclerViewAdapter.addAll(responseData.getResults());

                    if (filterMoviesCurrentPage <= TOTAL_PAGES)
                        recyclerViewAdapter.addLoadingFooter();
                    else isLastPage = true;
                } else {
                    showToast(responseData.getErrorMessage(), Toast.LENGTH_SHORT);
                }
            }, filterMoviesCurrentPage, releaseYear);
        } else {
            showToast("Not Connected to Internet!", Toast.LENGTH_SHORT);
        }
    }

    /**
     * bindViewToActivity
     */
    private void bindViewToActivity() {
        moviesService = new MoviesServiceImpl();
        recyclerView = findViewById(R.id.main_recycler_view);
        progressBar = findViewById(R.id.main_progress);
    }

    /**
     * setRecyclerViewAdapter - Method to set RecyclerView Adapter
     */
    private void setRecyclerViewAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        fabFilter = findViewById(R.id.fab_filter);
    }

    /**
     * showYearDialog - Method to show year picker dialog.
     */
    public void showYearDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.year_picker_dialog);
        Button set = dialog.findViewById(R.id.btn_set);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        final NumberPicker numberPicker = dialog.findViewById(R.id.np_year_picker);

        numberPicker.setMaxValue(year + 50);
        numberPicker.setMinValue(year - 50);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setValue(year);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(v -> {
            recyclerViewAdapter.clear();
            filterYear = numberPicker.getValue();
            showToast("Loading Movies for the Year : " + filterYear, Toast.LENGTH_SHORT);
            filterMoviesByReleaseDate(filterYear);
            dialog.dismiss();
        });
        cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

}
