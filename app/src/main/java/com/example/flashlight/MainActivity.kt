package com.example.flashlight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val torch = Torch(this)
        swFlash.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
               /* var intent=Intent(this,TorchService::class.java)
                intent.action="on"
                startService(intent) */
                startService(intentFor<TorchService>().setAction("on"))
            }else{
                /*var intent=Intent(this,TorchService::class.java)
                intent.action="off"
                startService(intent) */
                startService(intentFor<TorchService>().setAction("off"))
            }
        }
    }
}