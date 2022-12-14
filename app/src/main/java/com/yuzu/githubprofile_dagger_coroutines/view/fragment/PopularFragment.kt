package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentPopularBinding
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Status
import com.yuzu.githubprofile_dagger_coroutines.view.activity.MainActivity
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.LoadingStateAdapter
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserListAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.PopularViewModel
import kotlinx.coroutines.launch


class PopularFragment(private val viewModel: PopularViewModel): BaseFragment() {
    private lateinit var binding: FragmentPopularBinding
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        viewModel.getUser("")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initSwipeToRefresh()
        onBackPressed()
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val args = bundleOf("ARGUMENT_PROFILE" to it.data)
                    findNavController().navigate(R.id.action_main_to_profile, args)
                    binding.progress.visibility = View.GONE
                }
                Status.ERROR -> {
                    showError(it.message!!)
                    binding.progress.visibility = View.GONE
                }
                Status.LOADING -> showLoading(binding.progress)
            }
        }
    }

    private fun initAdapter() {
        adapter = UserListAdapter(requireContext(), OnClickListener {
            viewModel.profile.value = it.login
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter { adapter.retry() }
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.mediator?.refresh is LoadState.Loading
            }
        }

        adapter.addLoadStateListener { loadState ->

            if (loadState.mediator?.refresh is LoadState.Loading) {

                if (adapter.snapshot().isEmpty()) {
                    binding.progress.isVisible = true
                }
                binding.errorTxt.isVisible = false

            } else {
                binding.progress.isVisible = false
                //binding.swipeRefreshLayout.isRefreshing = false

                val error = when {
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (adapter.snapshot().isEmpty()) {
                        binding.errorTxt.isVisible = true
                        binding.errorTxt.text = it.error.localizedMessage
                    }

                }

            }
        }
    }

    private fun initSwipeToRefresh() {

        binding.swipeRefresh.setOnRefreshListener { viewModel.getUser("")}
    }

    private fun onBackPressed() {
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    (activity as MainActivity).finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }
}