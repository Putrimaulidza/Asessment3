package org.d3if3117.rumuspersegi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persegi")
data class PersegiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var keliling: Float,
    var luas: Float
)
