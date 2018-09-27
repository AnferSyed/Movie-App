package sample.com.movieapp.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by anfer on 25-Sep-18.
 */

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    /**
     * layoutManager
     */
    LinearLayoutManager layoutManager;

    /**
     * loadMoreItems
     */
    protected abstract void loadMoreItems();

    /**
     * getTotalPageCount
     *
     * @return
     */
    public abstract int getTotalPageCount();

    /**
     * isLastPage
     *
     * @return
     */
    public abstract boolean isLastPage();

    /**
     * isLoading
     *
     * @return
     */
    public abstract boolean isLoading();

    public InfiniteScrollListener(LinearLayoutManager linearLayoutManager) {
        this.layoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }
    }
}
