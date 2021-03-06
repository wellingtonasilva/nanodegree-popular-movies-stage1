package br.com.wsilva.popularmovies.features.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.features.list.MovieListActivity;
import br.com.wsilva.popularmovies.model.TmdbMovieDTO;
import br.com.wsilva.popularmovies.rest.NetworkUtil;

public class MovieDetailActivity extends AppCompatActivity {

    private TmdbMovieDTO movie;
    private ImageView backdrop;
    private ImageView movie_poster;
    private TextView release_date;
    private TextView title;
    private TextView synopsis;
    private TextView votes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        backdrop = findViewById(R.id.iv_backdrop);
        movie_poster = findViewById(R.id.iv_movie_poster);
        release_date = findViewById(R.id.tv_release_date);
        title = findViewById(R.id.tv_title);
        synopsis = findViewById(R.id.tv_synopsis);
        votes = findViewById(R.id.tv_votes);

        if (savedInstanceState != null) {
            movie = (TmdbMovieDTO) savedInstanceState.getSerializable(MovieListActivity.KEY_MOVIE);
        } else {
            movie = (TmdbMovieDTO) Objects.requireNonNull(getIntent().getExtras()).getSerializable(MovieListActivity.KEY_MOVIE);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        updateUI(movie);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MovieListActivity.KEY_MOVIE, movie);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(TmdbMovieDTO movie) {
        Picasso.get().load(NetworkUtil.URL_TMDB_IMAGE + movie.getBackdropPath()).into(backdrop);
        Picasso.get().load(NetworkUtil.URL_TMDB_BACKDROP_IMAGE + movie.getPosterPath()).into(movie_poster);
        release_date.setText(getString(R.string.app_release_date, movie.getReleaseDate()));
        title.setText(movie.getTitle());
        synopsis.setText(movie.getOverview());
        votes.setText(getString(R.string.app_vote_count, movie.getVoteCount()));
    }
}
