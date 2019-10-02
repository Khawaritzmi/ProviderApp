package com.muflihun.favoriteapptester.fragment;

import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muflihun.favoriteapptester.R;
import com.muflihun.favoriteapptester.activity.DetailActivity;
import com.muflihun.favoriteapptester.adapter.ListItemAdapter;
import com.muflihun.favoriteapptester.helper.MappingHelper;
import com.muflihun.favoriteapptester.model.Item;

import java.util.ArrayList;

import static com.muflihun.favoriteapptester.db.DatabaseContract.AUTHORITY;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TABLE_MOVIE;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment implements ListItemAdapter.OnItemClickCallback {
    private RecyclerView rvMovie;
    private static final String TAG = "FavoriteMovieFragment";

    private ListItemAdapter adapter;
    private ArrayList<Item> listItem;

    public MovieFragment () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_fav_movie);

        listItem = new ArrayList<>();
        adapter = new ListItemAdapter(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        Uri uri = Uri.parse("content://"+AUTHORITY+"/"+TABLE_MOVIE);
        ContentProviderClient cpClient = getContext().getContentResolver().acquireContentProviderClient(uri);
        Cursor cursor = null;
        try {
            cursor = cpClient.query(uri, null, null, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listItem.clear();
        listItem.addAll(MappingHelper.mapCursorToArrayList(cursor));
        adapter.setOnClickCallback(this);
        adapter.setData(listItem);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onClicked(View v, Item item, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ITEM, item);
        startActivity(intent);
    }
}