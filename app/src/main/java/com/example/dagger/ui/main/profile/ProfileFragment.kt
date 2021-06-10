package com.example.dagger.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dagger.R
import com.example.dagger.databinding.FragmentProfileBinding
import com.example.dagger.models.User
import com.example.dagger.ui.auth.AuthResource
import com.example.dagger.viewmodels.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    lateinit var binding: FragmentProfileBinding

    lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner) {
            if (it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserData(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setError(it.message)
                    }
                    else -> {
                        setError(getString(R.string.warning))
                    }
                }
            }
        }
    }

    private fun setUserData(user: User?) {
        with(binding) {
            email.text = user?.email
            username.text = user?.username
            website.text = user?.website
        }
    }

    private fun setError(message: String?) {
        with(binding) {
            email.text = message
            username.text = String()
            website.text = String()
        }
    }
}