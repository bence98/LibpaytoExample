package hu.agyklub.csokicraft.android.libpayto.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.agyklub.csokicraft.libpayto.LibPayto
import hu.agyklub.csokicraft.libpayto.PaytoHandlerRegistry
import hu.agyklub.csokicraft.libpayto.objects.PaytoIbanData
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PaytoHandlerRegistry.INSTANCE.register(this::onIban)
    }

    override fun onResume(){
        super.onResume()
        tryDispatch(intent)
    }

    override fun onNewIntent(intent: Intent?){
        super.onNewIntent(intent)
        tryDispatch(intent)
    }

    fun tryDispatch(intent: Intent?){
        val uri=intent?.data
        if (uri!=null){
            LibPayto.dispatchURI(URI(uri.toString()))
        }
    }

    fun onIban(data: PaytoIbanData){
        //Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_LONG).show()
        tvData.text=data.toString()
    }
}
