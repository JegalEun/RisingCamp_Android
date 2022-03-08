package com.example.rc_5th_api

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.rc_5th_api.databinding.ActivitySetAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource

class SetAddressActivity : AppCompatActivity(), OnMapReadyCallback {
//class SetAddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySetAddressBinding

    val PERMISSIONS = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val REQUEST_PERMISSION_CODE = 1
    val DEFAULT_ZOOM_LEVEL = 17f
    val CITY_HALL = LatLng(37.5662952, 126.97794509999994)
    var googleMap : GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var cancellationTokenSource : CancellationTokenSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySetAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initVariables()
        // 시작하자마자 권한 요청
        requestLocationPermissions()

//        setupGoogleMap()

//        binding.mapView.onCreate(savedInstanceState)

//        binding.flSetGps.setOnClickListener {
//            Log.d("눌림","눌림")
//            onMyLocationButtonClick()
//        }

//        if(checkPermissions()) {
////            initMap()
//        } else {
//            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)
//        }

        // 구글 맵
        setupGoogleMap()
    }

//    private fun checkPermissions() : Boolean {
//        for(permission in PERMISSIONS) {
//            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                return false
//            }
//        }
//        return true
//    }

    override fun onDestroy() {
        super.onDestroy()
        cancellationTokenSource?.cancel()
    }


//    @SuppressLint("MissingPermission")
//    fun initMap() {
//        // 구글맵을 불러오는 함수
//        binding.mapView.getMapAsync {
//            googleMap = it
//            // 현재화 위치이동 버튼 비활성
//            it.uiSettings.isMyLocationButtonEnabled = false
//
//            when {
//                checkPermissions() -> {
//                    // 권한이 있을 경우 현재 위치 이동 활성화하고 카메라 이동
//                    it.isMyLocationEnabled = true
//                    it.moveCamera((CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL)))
//                }
//                else -> {
//                    // 권한이 없다면 지정해둔 서울시청의 위치로 이동
//                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(CITY_HALL, DEFAULT_ZOOM_LEVEL))
//                }
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    fun getMyLocation() : LatLng {
//
//        val locationProvider : String = LocationManager.GPS_PROVIDER
//        val locationNetwork : String = LocationManager.NETWORK_PROVIDER
//        val locationManager  = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val lastKnowLocation : Location? = locationManager.getLastKnownLocation(locationProvider)
//
//        if(lastKnowLocation==null) {
//            val lastKnowLocation : Location? = locationManager.getLastKnownLocation(locationNetwork)
//            val latitude = lastKnowLocation?.latitude
//            val longitude = lastKnowLocation?.longitude
//            Log.d("Test", "GPS Location changed, Latitude: $latitude" + ", Longitude: $longitude")
//            return LatLng(lastKnowLocation!!.latitude, lastKnowLocation!!.longitude)
//        }
//        return LatLng(lastKnowLocation!!.latitude, lastKnowLocation!!.longitude)
//
//    }
//
//    private fun onMyLocationButtonClick(){
//        when {
//            checkPermissions() -> googleMap?.moveCamera(
//                CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL)
//            )
//            else -> Toast.makeText(this, "위치사용권한 설정에 동의해주세요", Toast.LENGTH_SHORT).show()
//        }
//    }
//
    private fun setupGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.frgment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val locationPermissionGranted =
            requestCode == REQUEST_ACCESS_LOCATION_PERMISSIONS &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED

        if(!locationPermissionGranted){
            finish()
        }else {
            cancellationTokenSource = CancellationTokenSource()

            fusedLocationProviderClient.
            getCurrentLocation(
                com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource!!.token
            ).addOnSuccessListener { location ->
                binding.tvAddressDetail.text = "${location.latitude}, ${location.longitude}"
            }
        }
//        initMap()
    }

    private fun initVariables() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }


    private fun requestLocationPermissions(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            REQUEST_ACCESS_LOCATION_PERMISSIONS
        )
    }

    companion object {
        private const val REQUEST_ACCESS_LOCATION_PERMISSIONS = 100

    }
}