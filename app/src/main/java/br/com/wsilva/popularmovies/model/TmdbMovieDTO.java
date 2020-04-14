package br.com.wsilva.popularmovies.model;

import java.io.Serializable;
import java.util.List;

public class TmdbMovieDTO implements Serializable {
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String title;
    private String backdropPath;
    private int voteCount;

    public TmdbMovieDTO() {
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(boolean adult) {
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenreIds(List<Integer> genreIds) {
    }

    public void setId(int id) {
    }

    public void setOriginalTitle(String originalTitle) {
    }

    public void setOriginalLanguage(String originalLanguage) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPopularity(double popularity) {
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(boolean video) {
    }

    public void setVoteAverage(double voteAverage) {
    }
}


