package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentMainMenuBinding
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.ViewPagerAdapter

class MainMenuFragment: Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    private val title = arrayOf("Popular", "Favorite")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = title[position]
        }.attach()
    }
}