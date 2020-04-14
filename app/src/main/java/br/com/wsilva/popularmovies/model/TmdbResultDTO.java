package br.com.wsilva.popularmovies.model;

import java.util.ArrayList;
import java.util.List;

public class TmdbResultDTO {

    private int page;
    private int totalResults;
    private int totalPages;
    private List<TmdbMovieDTO> results = new ArrayList<>();

    public TmdbResultDTO() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TmdbMovieDTO> getResults() {
        return results;
    }

    public void setResults(List<TmdbMovieDTO> results) {
        this.results = results;
    }
}