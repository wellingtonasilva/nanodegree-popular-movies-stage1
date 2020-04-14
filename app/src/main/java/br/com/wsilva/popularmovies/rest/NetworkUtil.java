package br.com.wsilva.popularmovies.rest;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class NetworkUtil {

    public static final String URL_TMDB_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated";
    public static final String URL_TMDB_POPULAR = "https://api.themoviedb.org/3/movie/popular";
    public static final String URL_TMDB_IMAGE = "http://image.tmdb.org/t/p/w185";
    public static final String URL_TMDB_BACKDROP_IMAGE = "http://image.tmdb.org/t/p/w780";

    public static String getResponseFromHttpUrl(String url, Map<String, String> queryParameter) {
        Uri uri = getUriFromMap(url, queryParameter);
        try {
            return getResponseFromHttpUrl(getUrlFromUri(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = connection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            return hasInput ? scanner.next() : null;
        } finally {
            connection.disconnect();
        }
    }

    private static Uri getUriFromMap(String url, Map<String, String> queryParameter) {
        Uri.Builder builder = Uri
                .parse(url)
                .buildUpon();

        for (Map.Entry<String, String> entry : queryParameter.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private static URL getUrlFromUri(Uri uri) throws MalformedURLException {
        return new URL(uri.toString());
    }
}
