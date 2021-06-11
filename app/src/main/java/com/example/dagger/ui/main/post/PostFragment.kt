package com.example.dagger.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dagger.R
import com.example.dagger.databinding.FragmentPostBinding
import com.example.dagger.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var binding: FragmentPostBinding
    lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post,
            container,
            false
        )



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePost().removeObservers(viewLifecycleOwner)
        viewModel.observePost().observe(viewLifecycleOwner) {
            if (it != null) {
                Log.i("zxcv", "SUCCESS!!!")
                it.data?.map { post ->
                    Log.i("zxcv", post.title)
                }
            }
        }
    }
}