package org.d3if3117.rumuspersegi.ui.histori

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3117.rumuspersegi.db.PersegiEntity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import org.d3if3117.rumuspersegi.databinding.ItemHistoriBinding
import org.d3if3117.rumuspersegi.R
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<PersegiEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersegiEntity>() {
                override fun areItemsTheSame(
                    oldData: PersegiEntity, newData: PersegiEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: PersegiEntity, newData: PersegiEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        @SuppressLint("StringFormatMatches")
        fun bind(item: PersegiEntity) = with(binding) {

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            persegiTextView.text = root.context.getString(R.string.hasil_x, item.keliling)
            dataTextView.text = root.context.getString(R.string.data_x, item.luas)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHistoriBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }
    }
