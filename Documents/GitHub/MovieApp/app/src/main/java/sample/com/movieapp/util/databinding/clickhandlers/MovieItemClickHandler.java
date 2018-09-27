package sample.com.movieapp.util.databinding.clickhandlers;

import android.content.Intent;
import android.view.View;

import sample.com.movieapp.activity.MovieDetailsActivity;
import sample.com.webservice.common.data.Results;

/**
 * Created by anfer on 26-Sep-18.
 */

public class MovieItemClickHandler {

    /**
     * viewMovieDetails - Method to navigate to details activity to show-up movie details
     */
    public void viewMovieDetails(View view, Results movieItem) {
        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
        intent.putExtra("movie_item", movieItem);
        view.getContext().startActivity(intent);
    }

}
