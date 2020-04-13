package br.com.wsilva.popularmovies.features.list;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;
import br.com.wsilva.popularmovies.rest.ApiTmdbAsyncTask;
import br.com.wsilva.popularmovies.rest.MovieBy;

public class MovieListActivity extends AppCompatActivity implements ApiTmdbAsyncTask.ApiResult {

    private static final int NUM_OF_COLUMNS = 2;
    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Load information from the TMDB Service
        new ApiTmdbAsyncTask(MovieListActivity.this, this::onResult).execute(MovieBy.TOP_RATED);
    }

    @Override
    public void onResult(TmdbResultDTO tmdbResultDTO) {
        if (tmdbResultDTO != null) {
            movieListAdapter = new MovieListAdapter(tmdbResultDTO.getResults(), (item) -> {
                Toast.makeText(MovieListActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            });
            recyclerView.setAdapter(movieListAdapter);
        }
    }
}
