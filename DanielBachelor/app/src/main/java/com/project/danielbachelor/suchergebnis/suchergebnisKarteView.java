package com.project.danielbachelor.suchergebnis;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.List;

public class suchergebnisKarteView extends Fragment implements suchergebnisKontrakt.KarteView {
    private suchergebnisKontrakt.KartePresenter mPresenter;

    private MapView map;
    private FloatingActionButton SuchergebnisKarteFAB;

    public suchergebnisKarteView() {
    }

    public static suchergebnisKarteView newInstance(){
        suchergebnisKarteView fragment = new suchergebnisKarteView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_suchergebnis_osmdroid, container, false);
        map = (MapView) root.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        SuchergebnisKarteFAB = root.findViewById(R.id.SuchergebnisKarteFAB);
        SuchergebnisKarteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fuehreRoutensucheDurch(getContext());
            }
        });
        return root;
    }

    @Override
    public void setPresenter(suchergebnisKontrakt.KartePresenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public void setzeGeoPoints(List<standort> mStandortListe) {

        double[][] Koordinaten = new double[mStandortListe.size()][2];
        for (int i = 0; i < mStandortListe.size(); i++) {
            Koordinaten[i][0] = mStandortListe.get(i).getSBreitengrad();
            Koordinaten[i][1] = mStandortListe.get(i).getSLaengengrad();
        }

        double[] Koordinatendurchschnitt = new double[2];
        Koordinatendurchschnitt[0] = 0.;
        Koordinatendurchschnitt[1] = 0.;
        for (int i = 0; i <  mStandortListe.size(); i++) {
            Koordinatendurchschnitt[0] += Koordinaten[i][0];
            Koordinatendurchschnitt[1] += Koordinaten[i][1];

            if(i == mStandortListe.size()-1) {

                Koordinatendurchschnitt[0] /= i+1;
                Koordinatendurchschnitt[1] /= i+1;
            }
        }

        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        //new GeoPoint(Breitengrad, Längengrad)
        GeoPoint startPoint = new GeoPoint(Koordinatendurchschnitt[0], Koordinatendurchschnitt[1]);
        mapController.setCenter(startPoint);

        for (int i = 0; i < mStandortListe.size(); i++) {
            GeoPoint mPoint = new GeoPoint(Koordinaten[i][0], Koordinaten[i][1]);
            Marker mMarker = new Marker(map);
            mMarker.setPosition(mPoint);
            map.getOverlays().add(mMarker);
        }
    }



}

