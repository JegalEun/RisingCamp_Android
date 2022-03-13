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
        scope.cancel()
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
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    // 권한을 요청하고 돌아오는 함수
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val locationPermissionGranted = requestCode == REQUEST_ACCESS_LOCATION_PERMISSIONS &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED

        if(!locationPermissionGranted){
            // 권한 요청이 거부
            finish()
        }else {
//             권한 요청을 허락했을 경우 대기정보 가져오기
            fetchAirQualityData()
        }
//        initMap()
    }

    private fun initVariables() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }


    // 권한 요청
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

    // 현재 위치 경도, 위도 가져오기
    override fun onGetTmSuccess(response: TmCoordinatesResponse) {
        for (tm in response.documents!!) {
            tm_x = tm.x!!
            tm_y = tm.y!!
        }
        showCustomToast("Get TM 성공")
    }

    override fun onGetTmFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    // 경도, 위도 기준으로 근처 측정소 가져오기
    override fun onGetStationSuccess(response: MonitoringStationsResponse) {
        for (station in response.response?.body?.monitoringStations!!) {
            near_station = station.stationName.toString()
            add = station.addr.toString()
        }
        showCustomToast("Get station 성공")
    }

    override fun onGetStationFailure(message: String) {
        TODO("Not yet implemented")
    }

    // 근처 측정소에서 대기질 정보 가져오기
    override fun onGetAirQualitySuccess(response: AirQualityResponse) {
        for (data in response.response?.body?.measuredValues!!) {
            khaiGrade = data.khaiGrade?.emoji.toString()
            air_quality = data.khaiGrade?.label.toString()
        }
        showCustomToast("Get station 성공")
    }

    override fun onGetAirQualityFailure(message: String) {
        TODO("Not yet implemented")
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
            scope.launch {
//                val monitoringStation = Repository.getNearByMonitoringStation(location.latitude, location.longitude)
//                //경도,위도 가져오기
//                SetAddressService(this).tryGetTm(location.latitude, location.longitude)
//                //근처 미세먼지 측정장소 가져오기
//                SetAddressService(this).tryGetStation(tm_x, tm_y)
//                //측정장소에서 미세먼지 결과 가져오기
//                SetAddressService(this).tryGetAirQuality(near_station)

//                binding.tvAddressDetail.text = monitoringStation?.stationName
//                val measuredValue =
//                    Repository.getLatestAirQualityData(monitoringStation!!.stationName!!)
//
//                displayAirQualityData(monitoringStation, measuredValue!!)
//                binding.tvAirQuality.text=measuredValue.toString()
            }
            //경도,위도 가져오기
            SetAddressService(this).tryGetTm(location.latitude, location.longitude)
            //근처 미세먼지 측정장소 가져오기
            SetAddressService(this).tryGetStation(tm_x, tm_y)
            //측정장소에서 미세먼지 결과 가져오기
            SetAddressService(this).tryGetAirQuality(near_station)
        }

    }

//    fun displayAirQualityData(monitoringStation: MonitoringStation, measuredValue: MeasuredValue){
//        binding.tvAddressDetail.text = monitoringStation.addr
//
//        (measuredValue.khaiGrade?: Grade.UNKNOWN).let { grade ->
//            binding.tvAirQuality.setText("미세먼지 "+grade.label + grade.emoji)     // 미측정
//        }
//
//    }

    companion object {
        private const val REQUEST_ACCESS_LOCATION_PERMISSIONS = 100
        private var tm_x : Double = 0.0
        private var tm_y : Double = 0.0
        private var near_station : String = ""
        private var add : String = ""
        private lateinit var khaiGrade : String
        private var air_quality : String = ""

    }
}