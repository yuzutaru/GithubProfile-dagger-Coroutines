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
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.ProfileListAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.FavoriteViewModel

class FavoriteFragment: Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

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

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = ProfileListAdapter(requireContext(), it,
                OnClickListener { it1 ->
                val args = bundleOf("ARGUMENT_PROFILE" to it1)
                findNavController().navigate(R.id.action_favorite_screen_to_profile_screen, args)
            })
        }
    }
}