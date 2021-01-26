package mx.tecnm.tepic.tap_u5_ejercicio4_vectoresdinamicosytimers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var vector = ArrayList<String>()
    var timer = object : CountDownTimer(20000,500){
        override fun onFinish() {
            start()
        }

        override fun onTick(p0: Long) {
            mostrarData()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer.start()

        button.setOnClickListener {
            var cadena = "Nombre: ${nombre.text.toString()}\nEdad: ${edad.text.toString()}"+
                    "\nTelefono: ${telefono.text.toString()}";

            vector.add(cadena)
            Toast.makeText(this,"SE CAPTURO CON EXITO", Toast.LENGTH_SHORT).show()
            nombre.setText(""); edad.setText("")
            telefono.setText("")
        }
        button2.setOnClickListener {
            var campoPosicion = EditText(this)

            campoPosicion.inputType = InputType.TYPE_CLASS_NUMBER

            AlertDialog.Builder(this)
                    .setTitle("Escriba posicion elimnar")
                    .setView(campoPosicion)
                    .setPositiveButton("BORRAR"){d,i->
                        eliminar(campoPosicion.text.toString().toInt())
                    }
                    .setNegativeButton("CANCELAR"){d, i->

                    }
                    .show()
        }
    }
    fun eliminar(posicion:Int){
        try{
            vector.removeAt(posicion)
            Toast.makeText(this,"SE CAPTURO CON EXITO", Toast.LENGTH_SHORT).show()

        }catch(e:Exception){
            AlertDialog.Builder(this).setTitle("ERROR").setMessage(e.message)
                    .show()
        }
    }
    fun mostrarData(){
        var cadena = "MOSTRAR: \n"

        if(vector.size==0){
            textView.setText("MOSTRAR: \nNO EXISTEN DATOS AUN")
            return
        }

        var ultimoIndice = vector.size-1;
        (0..ultimoIndice).forEach {
            cadena += "POSICION: ${it}\n${vector.get(it)}\n\n"
        }
        textView.setText(cadena)
    }
}