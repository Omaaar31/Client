package com.example.projetcommun


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetcommun.databinding.ActivityMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.concurrent.thread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

     var mMap: GoogleMap? = null
    lateinit var binding: ActivityMapsBinding


    val data = ArrayList<PointBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.layout) as SupportMapFragment
        mapFragment.getMapAsync(this)

       //Point temporaire
        data.add(PointBean(1, 1.2, 2.3))

        loadPoint()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 1, 0, "Accueil")
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        return super.onOptionsItemSelected(item)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        refreshMap()

    }

    private fun refreshMap() {
        if (mMap == null) {
            return
        }
        //mMap!!.isMyLocationEnabled(true)

        runOnUiThread {
            mMap!!.clear() //efface tous les points
            //Affiche un marker sur la
            data.forEach {
                val latlng = LatLng(it.lattitude, it.longitude)
                mMap!!.addMarker(MarkerOptions().position(latlng).title("Vous êtes ici"))

            }

            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }


    fun loadPoint(){
        thread {
            try {
                //Chercher les données avec WSUtils
                data.clear()
                data.addAll(WSUtils.getPoints())
                //mettre à jour la liste data
                refreshMap()
            }
            catch(e:Exception) {
                runOnUiThread {
                    Toast.makeText(MapsActivity@this, "Erreur : " + e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
