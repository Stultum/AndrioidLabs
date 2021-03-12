package ru.stultus.calculator.dblab

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ManEntity::class], version = 1)
abstract class ManDB : RoomDatabase() {
    abstract fun manDao(): ManDAO
}