package br.com.wsilva.popularmovies.features.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.features.detail.MovieDetailActivity;
import br.com.wsilva.popularmovies.model.TmdbMovieDTO;
import br.com.wsilva.popularmovies.model.TmdbResultDTO;
import br.com.wsilva.popularmovies.rest.ApiTmdbAsyncTask;
import br.com.wsilva.popularmovies.rest.MovieBy;

public class MovieListActivity extends AppCompatActivity implements ApiTmdbAsyncTask.ApiResult {

    public static final String KEY_MOVIE = "KEY_MOVIE";
    private static final int NUM_OF_COLUMNS = 2;
    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;
    MovieBy selectedSortBy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Load information from the TMDB Service
        selectedSortBy = MovieBy.POPULAR;
        new ApiTmdbAsyncTask(MovieListActivity.this, this::onResult).execute(selectedSortBy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort_by, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_most_popular:
                selectedSortBy = MovieBy.POPULAR;
                loadMovie(selectedSortBy);
                return true;
            case R.id.action_top_rated:
                selectedSortBy = MovieBy.TOP_RATED;
                loadMovie(selectedSortBy);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResult(TmdbResultDTO tmdbResultDTO) {
        if (tmdbResultDTO != null) {
            movieListAdapter = new MovieListAdapter(tmdbResultDTO.getResults(), (item) -> {
                showDetail(item);
            });
            recyclerView.setAdapter(movieListAdapter);
        }
    }

    private void loadMovie(MovieBy movieBy) {
        // Load information from the TMDB Service
        new ApiTmdbAsyncTask(MovieListActivity.this, this::onResult).execute(movieBy);
    }

    private void showDetail(TmdbMovieDTO movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_MOVIE, movie);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
