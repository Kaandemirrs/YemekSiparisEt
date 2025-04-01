package com.example.yemeksipariset.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yemeksipariset.data.entity.Favoriler


@Database(entities = [Favoriler::class], version = 1)
abstract class Veritabani : RoomDatabase() {

    abstract fun getFavorilerDao() : FavorilerDao


}