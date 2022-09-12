package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yuzu.githubprofile_dagger_coroutines.view.fragment.FavoriteFragment
import com.yuzu.githubprofile_dagger_coroutines.view.fragment.PopularFragment
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.PopularViewModel


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val popularViewModel: PopularViewModel):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment(popularViewModel)
            1 -> return FavoriteFragment()
        }
        return PopularFragment(popularViewModel)
    }
}