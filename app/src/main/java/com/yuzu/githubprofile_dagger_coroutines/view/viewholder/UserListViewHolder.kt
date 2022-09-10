package com.yuzu.githubprofile_dagger_coroutines.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yuzu.githubprofile_dagger_coroutines.databinding.ItemUserListBinding
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.view.adapter.OnClickListener

class UserListViewHolder(view: View): RecyclerView.ViewHolder(view)  {
    private val binding = ItemUserListBinding.bind(itemView)

    fun bind(user: User?, clickListener: OnClickListener) {
        Glide.with(itemView).load(user?.avatarUrl).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.avatar)
        binding.login.text = user?.login
        binding.repos.text = user?.reposUrl

        binding.background.setOnClickListener {
            user?.let { it1 -> clickListener.onClick(it1) }
        }
    }
}