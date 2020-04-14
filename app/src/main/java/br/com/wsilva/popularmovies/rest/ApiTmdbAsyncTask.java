package br.com.wsilva.popularmovies.rest;

import android.os.AsyncTask;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;

public class ApiTmdbAsyncTask extends AsyncTask<MovieBy, TmdbResultDTO, TmdbResultDTO> {

    public interface ApiResult {
        void onResult(TmdbResultDTO tmdbResultDTO);
    }

    private final String apiKey;
    private final ApiResult listener;

    public ApiTmdbAsyncTask(String apiKey, ApiResult listener) {
        this.apiKey = apiKey;
        this.listener = listener;
    }

    @Override
    protected TmdbResultDTO doInBackground(MovieBy... movieBy) {
        // Create API
        ApiTmdb api = new ApiTmdb(apiKey);
        switch (movieBy[0]) {
            case POPULAR:
                return api.getMoviePopular();
            case TOP_RATED:
                return api.getMovieTopRated();
        }
        return null;
    }

    @Override
    protected void onPostExecute(TmdbResultDTO tmdbResultDTO) {
        if (tmdbResultDTO != null) {
            this.listener.onResult(tmdbResultDTO);
        } else {
            this.listener.onResult(new TmdbResultDTO());
        }
    }
}
