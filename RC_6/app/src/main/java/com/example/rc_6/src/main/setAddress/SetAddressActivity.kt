package com.example.rc_6.src.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.rc_6.R
import com.example.rc_6.config.BaseActivity
import com.example.rc_6.databinding.ActivitySetAddressBinding
import com.example.rc_6.src.main.setAddress.SetAddressActivityView
import com.example.rc_6.src.main.setAddress.SetAddressService
import com.example.rc_6.src.main.setAddress.models.airQuality.AirQualityResponse
import com.example.rc_6.src.main.setAddress.models.airQuality.Grade
import com.example.rc_6.src.main.setAddress.models.airQuality.MeasuredValue
import com.example.rc_6.src.main.setAddress.models.kakao.TmCoordinatesResponse
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStation
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStationsResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SetAddressActivity : BaseActivity<ActivitySetAddressBinding>(ActivitySetAddressBinding::inflate), OnMapReadyCallback,
    SetAddressActivityView {
//class SetAddressActivity : AppCompatActivity() {


    val PERMISSIONS = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val REQUEST_PERMISSION_CODE = 1
    val DEFAULT_ZOOM_LEVEL = 17f
    val CITY_HALL = LatLng(37.5662952, 126.97794509999994)
    var googleMap : GoogleMap? = null
    private val scope = MainScope()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var cancellationTokenSource : CancellationTokenSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVariables()
        // ?????????????????? ?????? ??????
        requestLocationPermissions()

        setupGoogleMap()

//        binding.mapView.onCreate(savedInstanceState)

//        binding.flSetGps.setOnClickListener {
//            Log.d("??????","??????")
//            onMyLocationButtonClick()
//        }

//        if(checkPermissions()) {
////            initMap()
//        } else {
//            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)
//        }

        // ?????? ???
//        setupGoogleMap()
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
        scope.cancel()
    }


    //    @SuppressLint("MissingPermission")
//    fun initMap() {
//        // ???????????? ???????????? ??????
//        binding.mapView.getMapAsync {
//            googleMap = it
//            // ????????? ???????????? ?????? ?????????
//            it.uiSettings.isMyLocationButtonEnabled = false
//
//            when {
//                checkPermissions() -> {
//                    // ????????? ?????? ?????? ?????? ?????? ?????? ??????????????? ????????? ??????
//                    it.isMyLocationEnabled = true
//                    it.moveCamera((CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL)))
//                }
//                else -> {
//                    // ????????? ????????? ???????????? ??????????????? ????????? ??????
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
//            else -> Toast.makeText(this, "?????????????????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
//        }
//    }
//
    private fun setupGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    // ????????? ???????????? ???????????? ??????
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val locationPermissionGranted = requestCode == REQUEST_ACCESS_LOCATION_PERMISSIONS &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED

        if(!locationPermissionGranted){
            // ?????? ????????? ??????
            finish()
        }else {
//             ?????? ????????? ???????????? ?????? ???????????? ????????????
            fetchAirQualityData()
        }
//        initMap()
    }

    private fun initVariables() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }


    // ?????? ??????
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

    // ?????? ?????? ??????, ?????? ????????????
    override fun onGetTmSuccess(response: TmCoordinatesResponse) {
        for (tm in response.documents!!) {
            tm_x = tm.x!!
            tm_y = tm.y!!
        }
        Log.d("Get Tm ??????","??????")
//        showCustomToast("Get TM ??????")
    }

    override fun onGetTmFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("?????? : $message")
    }

    // ??????, ?????? ???????????? ?????? ????????? ????????????
    override fun onGetStationSuccess(response: MonitoringStationsResponse) {
        for (station in response.response?.body?.monitoringStations!!) {
            near_station = station.stationName.toString()
            add = station.addr.toString()
        }
        showCustomToast("Get station ??????")
        Log.d("Get station ??????","??????")
    }

    override fun onGetStationFailure(message: String) {
    }

    // ?????? ??????????????? ????????? ?????? ????????????
    override fun onGetAirQualitySuccess(response: AirQualityResponse) {
        for (data in response.response?.body?.measuredValues!!) {
            khaiGrade = data.khaiGrade?.emoji.toString()
            air_quality = data.khaiGrade?.label.toString()
        }
        showCustomToast("Get station ??????")
    }

    override fun onGetAirQualityFailure(message: String) {
    }


    @SuppressLint("MissingPermission")
    private fun fetchAirQualityData(){
        cancellationTokenSource = CancellationTokenSource()

        fusedLocationProviderClient.
        getCurrentLocation(
            com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource!!.token
        ).addOnSuccessListener { location ->
            binding.tvAddressDetail.text = "${location.latitude}, ${location.longitude}"
            Log.d("latitude",location.latitude.toString())
            Log.d("longitude",location.longitude.toString())

            //??????,?????? ????????????
//            SetAddressService(this).tryGetTm(location.latitude, location.longitude)
//            Log.d("tm_x", tm_x.toString())
//            Log.d("tm_y", tm_y.toString())
            //?????? ???????????? ???????????? ????????????
            SetAddressService(this).tryGetStation(location.latitude, location.longitude)
            Log.d("tm_x", tm_x.toString())
            Log.d("tm_y", tm_y.toString())
            //?????????????????? ???????????? ?????? ????????????
            SetAddressService(this).tryGetAirQuality(near_station)
            Log.d("near_station", near_station)

            binding.tvAddressDetail.text = add
            binding.tvAirQuality.setText("????????????"+ air_quality+khaiGrade)

//            khaiGrade.let { khaiGrade ->
//                binding.tvAirQuality.setText("????????????"+ air_quality+khaiGrade)
//            }
        }

    }

//    fun displayAirQualityData(monitoringStation: MonitoringStation, measuredValue: MeasuredValue){
//        binding.tvAddressDetail.text = monitoringStation.addr
//
//        (measuredValue.khaiGrade?: Grade.UNKNOWN).let { grade ->
//            binding.tvAirQuality.setText("???????????? "+grade.label + grade.emoji)     // ?????????
//        }
//
//    }

    companion object {
        private const val REQUEST_ACCESS_LOCATION_PERMISSIONS = 100
        private var tm_x : Double = 0.0
        private var tm_y : Double = 0.0
        private var near_station : String = ""
        private var add : String = ""
        private var khaiGrade : String = ""
        private var air_quality : String = ""

    }
}