package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.repository.data.User
import com.yuzu.githubprofile_dagger_coroutines.view.viewholder.UserListViewHolder

class UserListAdapter(context: Context, private val clickListener: OnClickListener<User>): PagingDataAdapter<User, UserListViewHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
        }
    }

    private var inflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(inflater.inflate(R.layout.item_user_list, parent, false))
    }
}

class OnClickListener<T>(val clickListener: (T: T) -> Unit) {
    fun onClick(T: T) = clickListener(T)
}
