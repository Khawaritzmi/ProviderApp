package com.muflihun.favoriteapptester.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_MOVIE = "movie";
    public static final String TABLE_TV = "tv";
    public static final String AUTHORITY = "com.muflihun.moviecatalogue5";
    private static final String SCHEME = "content";

    public static final class TableColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release";
        public static final String LANGUAGE = "language";
        public static final String POSTER = "poster";
        public static final String BACKDROP = "backdrop";
        public static final String VOTE = "vote";
        public static final String POPULARITY = "popularity";

        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();

        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
    }
}
