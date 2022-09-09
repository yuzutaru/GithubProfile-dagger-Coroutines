package com.yuzu.githubprofile_dagger_coroutines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentMainMenuBinding

class MainMenuFragment: Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}