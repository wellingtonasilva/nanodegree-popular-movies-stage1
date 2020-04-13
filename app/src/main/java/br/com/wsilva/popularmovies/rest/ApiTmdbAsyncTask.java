package br.com.wsilva.popularmovies.rest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;

public class ApiTmdbAsyncTask extends AsyncTask<MovieBy, TmdbResultDTO, TmdbResultDTO> {

    Context context;

    public ApiTmdbAsyncTask(Context context) {
        this.context = context;
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
        if (tmdbResultDTO != null) {
            Log.d("###", String.valueOf(tmdbResultDTO.getTotalResults()));
        } else {
            Log.d("###", "No data");
        }
    }
}
