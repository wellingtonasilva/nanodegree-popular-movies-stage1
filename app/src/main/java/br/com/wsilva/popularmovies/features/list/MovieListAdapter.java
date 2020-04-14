package br.com.wsilva.popularmovies.features.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.wsilva.popularmovies.R;
import br.com.wsilva.popularmovies.model.TmdbMovieDTO;
import br.com.wsilva.popularmovies.rest.NetworkUtil;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    public interface MovieIemClickListener {
        void onMovieItemClick(TmdbMovieDTO movie);
    }

    List<TmdbMovieDTO> movies;
    MovieIemClickListener listener;

    public MovieListAdapter(List<TmdbMovieDTO> movies, MovieIemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    private MovieListViewHolder from(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_list_item,
                parent, false);
        return new MovieListViewHolder(view);
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(this::onClick);
        }

        public void bind(TmdbMovieDTO movie) {
            Picasso.get()
                    .load(NetworkUtil.URL_TMDB_IMAGE + movie.getPosterPath())
                    .placeholder(R.drawable.ic_image_grey)
                    .error(R.drawable.ic_broken_image_red)
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onMovieItemClick(movies.get(getAdapterPosition()));
            }
        }
    }
}
