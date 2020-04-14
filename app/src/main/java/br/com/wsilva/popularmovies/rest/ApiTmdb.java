package br.com.wsilva.popularmovies.rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.wsilva.popularmovies.model.TmdbMovieDTO;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;

final class ApiTmdb {

    private static final String QUERY_PARAMETER_KEY = "api_key";

    // Response fields from TMDB
    private static final String FIELD_PAGE = "page";
    private static final String FIELD_TOTAL_RESULTS = "total_results";
    private static final String FIELD_TOTAL_PAGES = "total_pages";
    private static final String FIELD_RESULTS = "results";
    private static final String FIELD_POSTER_PATH = "poster_path";
    private static final String FIELD_ADULT = "adult";
    private static final String FIELD_OVERVIEW = "overview";
    private static final String FIELD_RELEASE_DATE = "release_date";
    private static final String FIELD_GENRE_IDS = "genre_ids";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ORIGINAL_TITLE = "original_title";
    private static final String FIELD_ORIGINAL_LANGUAGE = "original_language";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_BACKDROP_PATH = "backdrop_path";
    private static final String FIELD_POPULARITY = "popularity";
    private static final String FIELD_VOTE_COUNT = "vote_count";
    private static final String FIELD_VIDEO = "video";
    private static final String FIELD_VOTE_AVERAGE = "vote_average";

    private final String apiKey;

    public ApiTmdb(String apiKey) {
        this.apiKey = apiKey;
    }

    public TmdbResultDTO getMoviePopular() {
        // Parameter to send to service
        Map<String, String> query = new HashMap<>();
        query.put(QUERY_PARAMETER_KEY, apiKey);

        String result = NetworkUtil.getResponseFromHttpUrl(NetworkUtil.URL_TMDB_POPULAR, query);
        if (result != null && !result.equals("")) {
            return from(result);
        }

        return null;
    }

    public TmdbResultDTO getMovieTopRated() {
        // Parameter to send to service
        Map<String, String> query = new HashMap<>();
        query.put(QUERY_PARAMETER_KEY, apiKey);

        String result = NetworkUtil.getResponseFromHttpUrl(NetworkUtil.URL_TMDB_TOP_RATED, query);
        if (result != null && !result.equals("")) {
            return from(result);
        }

        return null;
    }

    private TmdbResultDTO from(String json) {
        TmdbResultDTO result = new TmdbResultDTO();
        try {
            JSONObject jsonObject = new JSONObject(json);
            // Default values
            result.setPage(jsonObject.optInt(FIELD_PAGE));
            result.setTotalPages(jsonObject.optInt(FIELD_TOTAL_PAGES));
            result.setTotalResults(jsonObject.optInt(FIELD_TOTAL_RESULTS));
            // Results
            result.setResults(moviesFromJsonArray(jsonObject.optJSONArray(FIELD_RESULTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<TmdbMovieDTO> moviesFromJsonArray(JSONArray jsonArray) {
        List<TmdbMovieDTO> list = new ArrayList<>();

        if (jsonArray != null && jsonArray.length() > 0) {
            int length = jsonArray.length();
            for (int index = 0; index < length; index++) {
                TmdbMovieDTO movie = convertToMovieDTO(jsonArray.optJSONObject(index));
                list.add(movie);
            }
        }

        return list;
    }

    private TmdbMovieDTO convertToMovieDTO(JSONObject jsonObject) {
        TmdbMovieDTO movie = new TmdbMovieDTO();
        movie.setPosterPath(jsonObject.optString(FIELD_POSTER_PATH));
        movie.setAdult(jsonObject.optBoolean(FIELD_ADULT));
        movie.setOverview(jsonObject.optString(FIELD_OVERVIEW));
        movie.setReleaseDate(jsonObject.optString(FIELD_RELEASE_DATE));
        movie.setId(jsonObject.optInt(FIELD_ID));
        movie.setOriginalTitle(jsonObject.optString(FIELD_ORIGINAL_TITLE));
        movie.setOriginalLanguage(jsonObject.optString(FIELD_ORIGINAL_LANGUAGE));
        movie.setTitle(jsonObject.optString(FIELD_TITLE));
        movie.setBackdropPath(jsonObject.optString(FIELD_BACKDROP_PATH));
        movie.setPopularity(jsonObject.optDouble(FIELD_POPULARITY));
        movie.setVoteCount(jsonObject.optInt(FIELD_VOTE_COUNT));
        movie.setVideo(jsonObject.optBoolean(FIELD_VIDEO));
        movie.setVoteAverage(jsonObject.optDouble(FIELD_VOTE_AVERAGE));
        movie.setGenreIds(genIdsFromJsonArray(Objects.requireNonNull(jsonObject.optJSONArray(FIELD_GENRE_IDS))));

        return movie;
    }

    private List<Integer> genIdsFromJsonArray(JSONArray jsonArray) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < jsonArray.length(); index++) {
            result.add(jsonArray.optInt(index));
        }
        return result;
    }
}
