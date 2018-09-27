package sample.com.webservice.common.data;

import java.util.ArrayList;

/**
 * Created by anfer on 24-Sep-18.
 */

public class MovieResponseData {

    /**
     * page - indicate current page
     */
    private String page;

    /**
     * total_pages - total number of pages
     */
    private String total_pages;

    /**
     * total_results - total number of movies
     */
    private String total_results;

    /**
     * results - list of movies
     */
    private ArrayList<Results> results;

    /**
     * success - api request status
     */
    private boolean success;

    /**
     * errorMessage - error message
     */
    private String errorMessage;

    /**
     * return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * param page
     * the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * return the total_pages
     */
    public String getTotal_pages() {
        return total_pages;
    }

    /**
     * param total_pages
     * the total_pages to set
     */
    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * return the total_results
     */
    public String getTotal_results() {
        return total_results;
    }

    /**
     * param total_results
     * the total_results to set
     */
    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    /**
     * return the results
     */
    public ArrayList<Results> getResults() {
        return results;
    }

    /**
     * param results
     * the results to set
     */
    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    /**
     * return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * param success
     * the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * param errorMessage
     * the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "MovieResponseData [results = " + results + ", page = " + page + ", total_pages = " + total_pages + ", total_results = " + total_results + "]";
    }

}
