package sample.com.webservice.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anfer on 24-Sep-18.
 */

public class Results implements Parcelable {

    /**
     * vote_average
     */
    private String vote_average;

    /**
     * backdrop_path
     */
    private String backdrop_path;

    /**
     * adult
     */
    private String adult;

    /**
     * id
     */
    private String id;

    /**
     * title
     */
    private String title;

    /**
     * overview
     */
    private String overview;

    /**
     * original_language
     */
    private String original_language;

    /**
     * genre_ids
     */
    private String[] genre_ids;

    /**
     * release_date
     */
    private String release_date;

    /**
     * original_title
     */
    private String original_title;

    /**
     * vote_count
     */
    private String vote_count;

    /**
     * poster_path
     */
    private String poster_path;

    /**
     * video
     */
    private String video;

    /**
     * popularity
     */
    private String popularity;

    /**
     * return the vote_average
     */
    public String getVote_average() {
        return vote_average;
    }

    /**
     * param vote_average
     * the vote_average to set
     */
    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    /**
     * return the backdrop_path
     */
    public String getBackdrop_path() {
        return backdrop_path;
    }

    /**
     * param backdrop_path
     * the backdrop_path to set
     */
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    /**
     * return the adult
     */
    public String getAdult() {
        return adult;
    }

    /**
     * param adult
     * the adult to set
     */
    public void setAdult(String adult) {
        this.adult = adult;
    }

    /**
     * return the id
     */
    public String getId() {
        return id;
    }

    /**
     * param id
     * the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * param title
     * the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * param overview
     * the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * return the original_language
     */
    public String getOriginal_language() {
        return original_language;
    }

    /**
     * param original_language
     * the original_language to set
     */
    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    /**
     * return the genre_ids
     */
    public String[] getGenre_ids() {
        return genre_ids;
    }

    /**
     * param genre_ids
     * the genre_ids to set
     */
    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    /**
     * return the release_date
     */
    public String getRelease_date() {
        return release_date;
    }

    /**
     * param release_date
     * the release_date to set
     */
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    /**
     * return the original_title
     */
    public String getOriginal_title() {
        return original_title;
    }

    /**
     * param original_title
     * the original_title to set
     */
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    /**
     * return the vote_count
     */
    public String getVote_count() {
        return vote_count;
    }

    /**
     * param vote_count
     * the vote_count to set
     */
    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    /**
     * return the poster_path
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     * param poster_path
     * the poster_path to set
     */
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    /**
     * return the video
     */
    public String getVideo() {
        return video;
    }

    /**
     * param video
     * the video to set
     */
    public void setVideo(String video) {
        this.video = video;
    }

    /**
     * return the popularity
     */
    public String getPopularity() {
        return popularity;
    }

    /**
     * param popularity
     * the popularity to set
     */
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    /**
     * Results
     */
    public Results() {

    }

    /**
     * Results
     *
     * @param in
     */
    public Results(Parcel in) {
        readFromParcel(in);
    }

    /**
     * readFromParcel
     *
     * @param in
     */
    private void readFromParcel(Parcel in) {

        vote_average = in.readString();
        backdrop_path = in.readString();
        adult = in.readString();
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        original_language = in.readString();
        genre_ids = in.createStringArray();
        release_date = in.readString();
        original_title = in.readString();
        vote_count = in.readString();
        poster_path = in.readString();
        video = in.readString();
        popularity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vote_average);
        dest.writeString(backdrop_path);
        dest.writeString(adult);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(original_language);
        dest.writeStringArray(genre_ids);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeString(vote_count);
        dest.writeString(poster_path);
        dest.writeString(video);
        dest.writeString(popularity);
    }

    /**
     * CREATOR
     */
    public static final Parcelable.Creator<Results> CREATOR =
            new Parcelable.Creator<Results>() {

                @Override
                public Results createFromParcel(Parcel source) {
                    return new Results(source);
                }

                @Override
                public Results[] newArray(int size) {
                    return new Results[size];
                }

            };

    @Override
    public int describeContents() {
        return 0;
    }
}
