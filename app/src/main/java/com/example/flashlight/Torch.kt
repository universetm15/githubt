package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context:Context) {
    private var cameraId:String?= null
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager //as: 객체 형 변환
    //클래스 초기화
    init {
        cameraId=getCameraId()
    }
    //카메라 플래시를 켜는 메소드
    fun flashOn(){
        cameraId?.let { cameraManager.setTorchMode(it,true) }
    }
    //카메라 플래시를 끄는 메소드
    fun flashOff(){
        cameraId?.let { cameraManager.setTorchMode(it,false) }
    }
    //카메라 고유 아이디 얻는 메소드
    private fun getCameraId():String?{
        val cameraIds = cameraManager.cameraIdList //카메라에 대한 정보 목록
        for(id in cameraIds){ //향상된 for 문
            val info=cameraManager.getCameraCharacteristics(id) //각 id별로 세부정보
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) //플래시 켤 수 있는지 가능여부
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING) //카메라 렌즈 방향
            if(flashAvailable != null
               && flashAvailable
               && lensFacing!=null
               && lensFacing==CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }
}