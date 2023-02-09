package com.example.sumatifroom_aurelliajasmine_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_aurelliajasmine_genap.Room.Constant
import com.example.sumatifroom_aurelliajasmine_genap.Room.TbBarang
import com.example.sumatifroom_aurelliajasmine_genap.Room.codepelita
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    lateinit var barangAdapter: BarangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setUpRV()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.barangDAO().getBarang()
            Log.d("MainActivity", "dbResponse:$barang")
            withContext(Dispatchers.Main) {
                barangAdapter.setData(barang)
            }
        }
    }

    private fun halEdit() {
        btnAdd_brg.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun intentEdit(tbBrgID: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id", tbBrgID)
                .putExtra("intent_type", intentType)
        )
    }

    fun setUpRV() {
        barangAdapter = BarangAdapter(arrayListOf(), object : BarangAdapter.OnAdapterListener {
            override fun onClick(tbBarang: TbBarang) {
                intentEdit(tbBarang.id,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBarang: TbBarang) {
                intentEdit(tbBarang.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBarang: TbBarang) {
                hapusBrg(tbBarang)
            }
        })
        // id RV
        list_RV.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = barangAdapter
        }
    }

    private fun hapusBrg(tbBarang: TbBarang) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Applikasi Naenya")
            setMessage("Yakin Hapus ${tbBarang.nama_barang}?")
            setNegativeButton("Batal Gais") {dialogInterface,i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Ha.a Tenan") {dialogInterface,i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.barangDAO().deleteBarang(tbBarang)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}