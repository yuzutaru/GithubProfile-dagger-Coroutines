package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentMainMenuBinding
import com.yuzu.githubprofile_dagger_coroutines.view.activity.MainActivity
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.ViewPagerAdapter
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.PopularViewModel

class MainMenuFragment: Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var viewModel: PopularViewModel
    private val title = arrayOf("Popular", "Favorite")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[PopularViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initViewPager()
        initQuery()
    }

    private fun initAdapter() {
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, viewModel)
        binding.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
    }

    private fun initViewPager() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = title[position]
        }.attach()
    }

    private fun initQuery() {
        binding.search.addTextChangedListener(object : TextWatcher {
            val handler = Handler(Looper.getMainLooper())

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                val runnable = Runnable {
                    context?.let {
                        //Log.e("devLog", "text = $p0")
                        viewModel.getUser(p0.toString())
                        handler.removeCallbacksAndMessages(null)
                    }
                }

                handler.postDelayed(runnable, 2000);
            }
        })
    }
}