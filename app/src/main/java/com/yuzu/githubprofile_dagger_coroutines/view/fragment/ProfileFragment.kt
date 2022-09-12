package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yuzu.githubprofile_dagger_coroutines.databinding.FragmentProfileBinding
import com.yuzu.githubprofile_dagger_coroutines.view.activity.MainActivity
import com.yuzu.githubprofile_dagger_coroutines.viewmodel.ProfileViewModel

class ProfileFragment: Fragment() {
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
        viewModel.getArgs(arguments)
        onBackPressed()

    }

    private fun onBackPressed() {
        requireView().isFocusableInTouchMode = true;
        requireView().requestFocus();
        requireView().setOnKeyListener { _, p1, _ ->
            if (p1 == KeyEvent.KEYCODE_BACK)
                (activity as MainActivity).onBackPressed()

            true
        }
    }
}