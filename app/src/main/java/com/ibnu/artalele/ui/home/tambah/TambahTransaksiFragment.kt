package com.ibnu.artalele.ui.home.tambah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibnu.artalele.databinding.TambahTransaksiFragmentBinding

class TambahTransaksiFragment : Fragment() {

    private var _bindingTambahTraksaksiFragment: TambahTransaksiFragmentBinding? = null
    private val binding get() = _bindingTambahTraksaksiFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingTambahTraksaksiFragment =
            TambahTransaksiFragmentBinding.inflate(inflater, container, false)
        return _bindingTambahTraksaksiFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingTambahTraksaksiFragment = null
    }
}