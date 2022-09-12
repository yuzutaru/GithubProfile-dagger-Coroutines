package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentFavoriteBinding
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Status
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.ProfileListAdapter
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserListAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.FavoriteViewModel

class FavoriteFragment: BaseFragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: ProfileListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profile.value = "profile"
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null && it.data.isNotEmpty()) {
                        adapter = ProfileListAdapter(requireContext(), it.data, OnClickListener {

                        })
                        binding.recyclerView.adapter = adapter
                        binding.progress.visibility = View.GONE

                    } else {
                        showError("no favorite data")
                    }
                }
                Status.ERROR -> showError(it.message!!)
                Status.LOADING -> showLoading(binding.progress)
            }
        }
    }
}