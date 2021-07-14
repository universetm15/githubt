package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {
    private var isRunning = false
    private val torch:Torch by lazy {  //늦은 초기화
        Torch(this)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { //액티비티 시작할때
        when(intent?.action){
            //앱의 액티비티에서 실행할 경우
            "on" -> {
                torch.flashOn()
                isRunning=true
            }
            "off" -> {
                torch.flashOff()
                isRunning=false
            }
            //서비스에서 곧바로 실행할 경우
            else -> {
                isRunning!=isRunning
                if(isRunning){
                    torch.flashOn()
                }else{
                    torch.flashOff()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}