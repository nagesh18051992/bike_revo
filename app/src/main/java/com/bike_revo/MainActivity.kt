package com.bike_revo

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.ngoclong.googlemapviewwithmarker.BitmapUtils


class MainActivity : Activity(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnMapLoadedCallback {

    companion object {
        private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
        private val markerIconSize = 100
    }

    internal var marker: Marker? = null
    internal var map: GoogleMap? = null
    private var mMapView: MapView? = null

    internal var isMarkerCenter = true
    internal var latLng = LatLng(21.028511, 105.804817)

    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter

    private val options = MarkerOptions()
    private val latlngs: ArrayList<LatLng> = ArrayList()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapViewBundle: Bundle? = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mMapView = findViewById(R.id.map) as MapView
        mMapView?.onCreate(mapViewBundle)
        mMapView?.getMapAsync(this)

        val recyclerView: RecyclerView = findViewById(R.id.vehicle_detail)
        moviesAdapter = MoviesAdapter(this,movieList)
        val layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = moviesAdapter
        prepareMovieData()

    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mMapView?.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }

    override fun onPause() {
        mMapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }

    private fun prepareMovieData() {
        var movie = MovieModel("Scooty", R.drawable.scooter)
        movieList.add(movie)
        var movie1 = MovieModel("Bike", R.drawable.scooter)
        movieList.add(movie1)
        var movie2 = MovieModel("Tvs", R.drawable.scooter)
        movieList.add(movie2)
        var movie3 = MovieModel("Honda", R.drawable.scooter)
        movieList.add(movie3)
        var movie4 = MovieModel("Yamaha", R.drawable.scooter)
        movieList.add(movie4)
        moviesAdapter.notifyDataSetChanged()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        latlngs.add(LatLng(13.95534199681095, 79.35008170000002))
        latlngs.add(LatLng(13.96534199681095, 79.36008170000002))
        latlngs.add(LatLng(13.97534199681095, 79.37008170000002))
        latlngs.add(LatLng(13.98534199681095, 79.38008170000002))
        latlngs.add(LatLng(13.99534199681095, 79.39008170000002))

        for (point in latlngs) {

            val markerIcon = BitmapUtils.resizeMapIcons(
                this,
                R.drawable.scooter,
                markerIconSize,
                markerIconSize
            )
            marker = map?.addMarker(
                MarkerOptions().position(point!!).icon(BitmapDescriptorFactory.fromBitmap(markerIcon)).title("bike")
            )
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(marker?.position, 17f))
            map?.setOnCameraMoveListener(this)
            map?.setOnMapLoadedCallback(this)
            marker?.showInfoWindow()
        }
    }

    override fun onCameraMove() {
    }

    override fun onMapLoaded() {
    }
}
