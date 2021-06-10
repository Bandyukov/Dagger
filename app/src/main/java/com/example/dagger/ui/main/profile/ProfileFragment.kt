package com.example.dagger.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.dagger.R
import com.example.dagger.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

class ProfileFragment : DaggerFragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )



        return binding.root
    }
}