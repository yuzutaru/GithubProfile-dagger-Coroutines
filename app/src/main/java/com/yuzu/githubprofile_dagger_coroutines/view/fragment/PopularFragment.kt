package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentPopularBinding
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.LoadingStateAdapter
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.UserListAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.PopularViewModel
import kotlinx.coroutines.launch

class PopularFragment: Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private lateinit var viewModel: PopularViewModel
    private lateinit var adapter: UserListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[PopularViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        viewModel.getUser("")
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        adapter = UserListAdapter(requireContext(), OnClickListener {

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter { adapter.retry() }
        )

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
}