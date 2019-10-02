package com.muflihun.favoriteapptester.fragment;


import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muflihun.favoriteapptester.R;
import com.muflihun.favoriteapptester.activity.DetailActivity;
import com.muflihun.favoriteapptester.adapter.ListItemAdapter;
import com.muflihun.favoriteapptester.helper.MappingHelper;
import com.muflihun.favoriteapptester.model.Item;

import java.util.ArrayList;

import static com.muflihun.favoriteapptester.db.DatabaseContract.AUTHORITY;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TABLE_MOVIE;
import static com.muflihun.favoriteapptester.db.DatabaseContract.TABLE_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements ListItemAdapter.OnItemClickCallback {
    private RecyclerView rvTv;
    private ListItemAdapter adapter;
    private ArrayList<Item> listItem;

    public TvFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTv = view.findViewById(R.id.rv_fav_tv);

        adapter = new ListItemAdapter(getContext());
        listItem = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        Uri uri = Uri.parse("content://"+AUTHORITY+"/"+TABLE_TV);
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
        rvTv.setLayoutManager(layoutManager);
        rvTv.setAdapter(adapter);
    }

    @Override
    public void onClicked(View v, Item item, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ITEM, item);
        startActivity(intent);
    }
}
