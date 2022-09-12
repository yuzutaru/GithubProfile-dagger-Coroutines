package com.yuzu.githubprofile_dagger_coroutines.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yuzu.githubprofile_dagger_coroutines.databinding.ItemUserListBinding
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener

class ProfileListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemUserListBinding.bind(itemView)

    fun bind(profile: Profile?, clickListener: OnClickListener<Profile>) {
        Glide.with(itemView).load(profile?.avatarUrl).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.avatar)
        binding.login.text = profile?.login
        binding.repos.text = profile?.reposUrl

        binding.background.setOnClickListener {
            profile?.let { it1 -> clickListener.onClick(it1) }
        }
    }
}