package br.com.wsilva.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import br.com.wsilva.popularmovies.rest.ApiTmdbAsyncTask;
import br.com.wsilva.popularmovies.rest.MovieBy;

public class MainActivity extends AppCompatActivity {

    Button btnLoadTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadTopRated = findViewById(R.id.btnLoadTopRated);
        btnLoadTopRated.setOnClickListener((v) -> {
            new ApiTmdbAsyncTask(MainActivity.this).execute(MovieBy.TOP_RATED);
        });
    }
}
