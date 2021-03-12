package ru.stultus.calculator.dblab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ManDAO {

    @Insert
    fun insertAll(vararg users: ManEntity)

    @Query("SELECT * FROM ManEntity ORDER BY age ASC")
    fun loadSortedDB(): List<ManEntity>
}