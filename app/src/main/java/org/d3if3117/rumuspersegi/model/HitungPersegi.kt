package org.d3if3117.rumuspersegi.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3117.rumuspersegi.db.PersegiDao
import org.d3if3117.rumuspersegi.db.PersegiEntity

class HitungViewModel(private val db: PersegiDao) : ViewModel() {
    private val hasilPersegi = MutableLiveData<HasilPersegi?>()

    fun hitungPersegi(sisi: Float) {
        val keliling = 4 * sisi
        val luas = sisi * sisi
        hasilPersegi.value = HasilPersegi(keliling, luas)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataPersegi = PersegiEntity(
                    keliling = keliling,
                    luas = luas,
                )
                db.insert(dataPersegi)
            }
        }
    }
}