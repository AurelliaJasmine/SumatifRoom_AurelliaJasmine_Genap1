package com.example.sumatifroom_aurelliajasmine_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_aurelliajasmine_genap.Room.TbBarang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*

class BarangAdapter (private var barang:ArrayList<TbBarang>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {
    class BarangViewHolder (val view: View) : RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_barang_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val tbBrg = barang[position]
        holder.view.textId.text = tbBrg.id.toString()
        holder.view.textNama.text = tbBrg.nama_barang
        holder.view.textNama.setOnClickListener {
            listener.onClick(tbBrg)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(tbBrg)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(tbBrg)
        }
    }

    override fun getItemCount() = barang.size

    fun setData (list:List<TbBarang>) {
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
    fun onClick  (tbBarang: TbBarang)
    fun onUpdate (tbBarang: TbBarang)
    fun onDelete (tbBarang: TbBarang)
    }
}