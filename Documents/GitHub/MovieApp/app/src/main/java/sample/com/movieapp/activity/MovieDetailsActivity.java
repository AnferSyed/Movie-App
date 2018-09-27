package sample.com.movieapp.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sample.com.movieapp.R;
import sample.com.movieapp.databinding.ActivityMovieDetailsBinding;
import sample.com.webservice.common.data.Results;

public class MovieDetailsActivity extends AppCompatActivity {

    /**
     * detailsBinding
     */
    ActivityMovieDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Results movieItem = getIntent().getExtras().getParcelable("movie_item");
        detailsBinding.setMovieDetails(movieItem);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
