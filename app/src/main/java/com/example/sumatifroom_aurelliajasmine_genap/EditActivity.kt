package com.example.sumatifroom_aurelliajasmine_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_aurelliajasmine_genap.Room.Constant
import com.example.sumatifroom_aurelliajasmine_genap.Room.TbBarang
import com.example.sumatifroom_aurelliajasmine_genap.Room.codepelita
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    private var tbBrgID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolPerintah()
        setUpView()
        tbBrgID = intent.getIntExtra("intent_id",tbBrgID)
        Toast.makeText(this,tbBrgID.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setUpView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                cvbtnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                cvbtnSimpan.visibility = View.GONE
                cvbtnUpdate.visibility = View.GONE
                etId_brg.visibility = View.GONE
                tampilBrg()
            }
            Constant.TYPE_UPDATE -> {
                cvbtnSimpan.visibility = View.GONE
                etId_brg.visibility = View.GONE
                tampilBrg()
            }
        }
    }

    fun tampilBrg() {
        tbBrgID = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.barangDAO().tampilId(tbBrgID)[0]
            val dataId : String = barang.id.toString()
            val dataharga : String = barang.harga.toString()
            val dataqty : String = barang.qty.toString()
            etId_brg.setText(dataId)
            etNama_brg.setText(barang.nama_barang)
            etHarga_brg.setText(dataharga)
            etQty_brg.setText(dataqty)
        }
    }

    fun tombolPerintah() {
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.barangDAO().addBarang(
                    TbBarang(
                        etId_brg.text.toString().toInt(),
                        etNama_brg.text.toString(),
                        etHarga_brg.text.toString().toInt(),
                        etQty_brg.text.toString().toInt()
                    )
                )
                finish()
            }
        }

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.barangDAO().updateBarang(
                    TbBarang(
                        tbBrgID,
                        etNama_brg.text.toString(),
                        etHarga_brg.text.toString().toInt(),
                        etQty_brg.text.toString().toInt()
                    )
                )
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}