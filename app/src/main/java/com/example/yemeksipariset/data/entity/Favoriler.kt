package com.example.yemeksipariset.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriler")
data class Favoriler(
    @PrimaryKey(autoGenerate = true) val id:Int =0,
    val yemek_id:Int,
    val yemek_adi:String,
    val yemek_resim_adi:String,
    val yemek_fiyat:Int
) {
}