package com.example.sumatifroom_aurelliajasmine_genap.Room

import androidx.room.*

@Entity
class TbBarang (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nama_barang : String,
    val harga : Int,
    val qty : Int
    )