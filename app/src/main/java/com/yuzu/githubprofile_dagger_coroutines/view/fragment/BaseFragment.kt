package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    @SuppressLint("SetTextI18n")
    fun showLoading(progress: ProgressBar) {
        progress.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun showError(message: String) {
        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
    }
}