package org.d3if3117.rumuspersegi.ui.bangun_datar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3117.rumuspersegi.R
import org.d3if3117.rumuspersegi.databinding.ItemBangunDatarBinding
import org.d3if3117.rumuspersegi.model.BangunDatar
import org.d3if3117.rumuspersegi.network.ApiBangunDatar

class BangunDatarAdapter : RecyclerView.Adapter<BangunDatarAdapter.ViewHolder>() {
    private val data = mutableListOf<BangunDatar>()
    fun updateData(newData: List<BangunDatar>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemBangunDatarBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bangunDatar: BangunDatar) = with(binding) {
            namaTextView.text = bangunDatar.nama
            rumusTextView.text = bangunDatar.rumus
            Glide.with(imageView.context)
                .load(ApiBangunDatar.getBangunDatarUrl(bangunDatar.gambar))
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBangunDatarBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}