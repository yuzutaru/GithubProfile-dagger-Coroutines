package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentProfileBinding
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Status
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.ProfileListAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.ProfileViewModel


class ProfileFragment: BaseFragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()
        viewModel.getArgs(arguments)

        viewModel.profileLiveData().observe(viewLifecycleOwner) {
            it.login?.let { it1 -> viewModel.isFavorited.value = it1 }
        }

        viewModel.isFavoritedLiveData().observe(viewLifecycleOwner) {
            if (it.data != null) {
                binding.favorite.visibility = View.GONE

            } else {
                binding.favorite.visibility = View.VISIBLE
            }
        }

        viewModel.favoriteProfile.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            Toast.makeText(requireContext(), "Added profile to Favorite",
                                Toast.LENGTH_SHORT).show()
                            binding.favorite.visibility = View.GONE
                            binding.progress.visibility = View.GONE

                        } else {
                            showError("save favorite error")
                            binding.progress.visibility = View.GONE
                        }
                    }
                    Status.ERROR -> {
                        showError(it.message!!)
                        binding.progress.visibility = View.GONE
                    }
                    Status.LOADING -> showLoading(binding.progress)
                }
            }
        }

        binding.favorite.setOnClickListener {
            viewModel.saveFavorite.value = "save"
        }

        binding.back.setOnClickListener {
            Log.e("devLog", "on icon back")
            this@ProfileFragment.findNavController().navigate(R.id.action_profile_screen_to_main_screen)
        }
    }

    private fun onBackPressed() {
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_profile_screen_to_main_screen)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }
}