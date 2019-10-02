package com.muflihun.favoriteapptester.helper;

import android.database.Cursor;

import com.muflihun.favoriteapptester.model.Item;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.BACKDROP;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.LANGUAGE;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.OVERVIEW;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.POPULARITY;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.POSTER;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.RELEASE_DATE;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.TITLE;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TableColumns.VOTE;

public class MappingHelper {
    public static ArrayList<Item> mapCursorToArrayList(Cursor itemCursor) {
        ArrayList<Item> itemList = new ArrayList<>();

        while (itemCursor.moveToNext()) {
            int id = itemCursor.getInt(itemCursor.getColumnIndexOrThrow(_ID));
            String title = itemCursor.getString(itemCursor.getColumnIndexOrThrow(TITLE));
            String poster = itemCursor.getString(itemCursor.getColumnIndexOrThrow(POSTER));
            String overview = itemCursor.getString(itemCursor.getColumnIndexOrThrow(OVERVIEW));
            double vote = itemCursor.getDouble(itemCursor.getColumnIndexOrThrow(VOTE));;
            double popularity = itemCursor.getDouble(itemCursor.getColumnIndexOrThrow(POPULARITY));
            String release = itemCursor.getString(itemCursor.getColumnIndexOrThrow(RELEASE_DATE));
            String language = itemCursor.getString(itemCursor.getColumnIndexOrThrow(LANGUAGE));
            String backdrop = itemCursor.getString(itemCursor.getColumnIndexOrThrow(BACKDROP));
            itemList.add(new Item(id, title, poster, overview, vote, popularity, release, language, backdrop));
        }
        return itemList;
    }
}
