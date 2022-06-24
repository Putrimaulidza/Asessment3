package org.d3if3117.rumuspersegi.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3117.rumuspersegi.R
import org.d3if3117.rumuspersegi.model.HasilPersegi
import org.d3if3117.rumuspersegi.databinding.FragmentHitungBinding
import org.d3if3117.rumuspersegi.db.PersegiDb

class HitungFragment : Fragment(){
    lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = PersegiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_bangun_datar -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_bangunDatarFragment
                )
                return true
            }
        }
            return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnKeliling.setOnClickListener { hitungPersegi() }
        binding.btnShare.setOnClickListener { shareData() }
        viewModel.getHasilPersegi().observe(requireActivity(), { showResult(it) })
    }
    private fun hitungPersegi() {
        val sisi = binding.etSisi.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(context, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
       viewModel.hitungPersegi(
            sisi.toFloat()
        )

    }
    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.tvKeliling.text,
            binding.tvLuas.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


    private fun showResult(result: HasilPersegi?) {
        if (result == null) return
        binding.tvKeliling.text = getString(R.string.keliling_x, result.keliling)
        binding.tvLuas.text = getString(R.string.luas_x, result.luas)
    }

}