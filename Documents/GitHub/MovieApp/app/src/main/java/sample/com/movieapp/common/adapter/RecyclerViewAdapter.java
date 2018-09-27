package sample.com.movieapp.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

import sample.com.movieapp.R;
import sample.com.movieapp.util.databinding.clickhandlers.MovieItemClickHandler;
import sample.com.webservice.common.data.Results;

/**
 * Created by anfer on 25-Sep-18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * ITEM
     */
    private static final int ITEM = 0;

    /**
     * LOADING
     */
    private static final int LOADING = 1;

    /**
     * movieResults
     */
    private List<Results> movieResults;

    /**
     * context
     */
    private Context context;

    /**
     * isLoadingAdded
     */
    private boolean isLoadingAdded = false;

    /**
     * RecyclerViewAdapter
     *
     * @param context
     */
    public RecyclerViewAdapter(Context context) {
        this.context = context;
        movieResults = new ArrayList<>();
    }

    /**
     * getMovies
     *
     * @return
     */
    public List<Results> getMovies() {
        return movieResults;
    }

    /**
     * setMovies
     *
     * @param movieResults
     */
    public void setMovies(List<Results> movieResults) {
        this.movieResults = movieResults;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;

            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new MovieViewHolder(v1);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Results result = movieResults.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                final MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
                movieViewHolder.getBindings().setVariable(BR.movieItem, result);
                movieViewHolder.getBindings().setVariable(BR.movieItemClickHandler, new MovieItemClickHandler());
                movieViewHolder.getBindings().executePendingBindings();
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movieResults == null ? 0 : movieResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /**
     * add
     *
     * @param results
     */
    public void add(Results results) {
        movieResults.add(results);
        notifyItemInserted(movieResults.size() - 1);

    }

    /**
     * addAll
     *
     * @param moveResults
     */
    public void addAll(List<Results> moveResults) {

        for (Results result : moveResults) {
            add(result);
        }

    }

    /**
     * remove
     *
     * @param results
     */
    public void remove(Results results) {

        int position = movieResults.indexOf(results);

        if (position > -1) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }


    /**
     * clear
     */
    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    /**
     * isEmpty
     *
     * @return
     */
    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * addLoadingFooter
     */
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Results());
    }

    /**
     * removeLoadingFooter
     */
    public void removeLoadingFooter() {

        isLoadingAdded = false;
        int position = movieResults.size() - 1;
        Results result = getItem(position);
        if (result != null) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * getItem
     *
     * @param position
     * @return
     */
    public Results getItem(int position) {
        return movieResults.get(position);
    }

    /**
     * MovieViewHolder
     */
    protected class MovieViewHolder extends RecyclerView.ViewHolder {

        /**
         * movieItemBindings
         */
        private ViewDataBinding movieItemBindings;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieItemBindings = DataBindingUtil.bind(itemView);
        }

        /**
         * getBindings
         *
         * @return movieItemBindings
         */
        public ViewDataBinding getBindings() {
            return movieItemBindings;
        }
    }

    /**
     * LoadingViewHolder
     */
    protected class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
