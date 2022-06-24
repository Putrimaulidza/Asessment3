package org.d3if3117.rumuspersegi.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3117.rumuspersegi.db.PersegiDao

class HistoriViewModel(private val db: PersegiDao) : ViewModel() {
    val data = db.getLastPersegi()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}