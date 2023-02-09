package com.example.sumatifroom_aurelliajasmine_genap.Room

import androidx.room.*

@Dao
interface BarangDAO {
    @Insert
    fun addBarang (tbBarang: TbBarang)

    @Update
    fun updateBarang (tbBarang: TbBarang)

    @Delete
    fun deleteBarang (tbBarang: TbBarang)

    @Query ("SELECT*FROM TbBarang")
    fun getBarang() : List<TbBarang>

    @Query ("SELECT*FROM TbBarang WHERE id=:tbBrg_id")
    fun tampilId(tbBrg_id: Int): List<TbBarang>

}