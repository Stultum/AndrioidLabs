package ru.stultus.calculator.dblab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ManEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var weight: Int,
    var height: Int,
    var age: Int
)