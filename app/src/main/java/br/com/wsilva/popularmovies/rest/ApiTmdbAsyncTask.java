package br.com.wsilva.popularmovies.rest;

import android.content.Context;
import android.os.AsyncTask;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;

public class ApiTmdbAsyncTask extends AsyncTask<MovieBy, TmdbResultDTO, TmdbResultDTO> {

    public interface ApiResult {
        void onResult(TmdbResultDTO tmdbResultDTO);
    }

    Context context;
    ApiResult listener;

    public ApiTmdbAsyncTask(Context context, ApiResult listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected TmdbResultDTO doInBackground(MovieBy... movieBy) {
        // Create API
        ApiTmdb api = new ApiTmdb(context.getResources().getString(R.string.app_tmdb_api_key));
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
        if (this.listener != null) {
            this.listener.onResult(tmdbResultDTO);
        } else {
            this.listener.onResult(new TmdbResultDTO());
        }
    }
}
