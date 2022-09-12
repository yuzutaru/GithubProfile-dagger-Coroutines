package com.yuzu.githubprofile_dagger_coroutines.view.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    private lateinit var progress: ProgressBar

    @SuppressLint("SetTextI18n")
    fun showLoading(progress: ProgressBar) {
        this.progress = progress
        progress.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun showError(message: String) {
        this.progress.visibility = View.GONE
        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
    }
}