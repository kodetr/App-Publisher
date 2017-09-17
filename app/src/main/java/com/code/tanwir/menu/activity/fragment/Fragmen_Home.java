package com.code.tanwir.menu.activity.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.code.tanwir.menu.R;
import com.code.tanwir.menu.activity.adapter.RequestHandler;
import com.code.tanwir.menu.activity.network.Config;
import com.code.tanwir.menu.activity.view_home.AddHome;
import com.code.tanwir.menu.activity.view_home.CustomList;
import com.code.tanwir.menu.activity.view_home.GetAlIHome;
import com.code.tanwir.menu.activity.view_home.ViewFullIHome;
import com.github.fabtransitionactivity.SheetLayout;

import org.json.JSONException;

/**
 * Created by Tanwir on 17/03/2016.
 */

public class Fragmen_Home extends Fragment implements ListView.OnItemClickListener,SheetLayout.OnFabAnimationEndListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefresh;
    public GetAlIHome getAlIHome;

    private static final int REQUEST_CODE = 1;
    private SheetLayout mSheetLayout;

        public Fragmen_Home() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View view = inflater.inflate(R.layout.fragmen_home, container, false);

            listView = (ListView) view.findViewById(R.id.list);
            listView.setOnItemClickListener(this);

            swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
            swipeRefresh.setOnRefreshListener(this);

            getURLs();

            FloatingActionButton mFab = (FloatingActionButton) view.findViewById(R.id.fab);
            mSheetLayout =(SheetLayout) view.findViewById(R.id.bottom_sheet);

            mSheetLayout.setFab(mFab);
            mSheetLayout.setFabAnimationEndListener(this);

            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSheetLayout.expandFab();
                }
            });

            return view;
        }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        getURLs();
    }

    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getContext(), AddHome.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }

    private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Loding...","Please wait...",false,false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                CustomList customList = new CustomList((Activity) getContext(), GetAlIHome.bitmaps, GetAlIHome.name, GetAlIHome.pb);
                listView.setAdapter(customList);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlIHome.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getAlIHome = new GetAlIHome(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.DATA_URL);
                    return s;
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ViewFullIHome.class);
        intent.putExtra(Config.BITMAP_ID,i);
        startActivity(intent);
    }
}
