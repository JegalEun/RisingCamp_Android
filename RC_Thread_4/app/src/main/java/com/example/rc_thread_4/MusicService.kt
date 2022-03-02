package com.example.rc_thread_4

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {
    lateinit var mp : MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    // 서비스가 호출되었을 때 한번만 호출
    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this, R.raw.bgm)
        // bgm 무한루프
        mp.isLooping=true
    }

    //  서비스가 호출될 때마다 호출
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    //  서비스가 종료될 때 음악 종료
    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        mp.stop()
    }
}