package com.yuzu.githubprofile_dagger_coroutines.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuzu.githubprofile_dagger_coroutines.R
import com.yuzu.githubprofile_dagger_coroutines.repository.data.Profile
import com.yuzu.githubprofile_dagger_coroutines.view.viewholder.ProfileListViewHolder

class ProfileListAdapter(context: Context, private val profiles: List<Profile>,
                         private val clickListener: OnClickListener<Profile>):
    RecyclerView.Adapter<ProfileListViewHolder>() {
    private var inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListViewHolder {
        return ProfileListViewHolder(inflater.inflate(R.layout.item_user_list, parent, false))
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        holder.bind(profiles[position], clickListener)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}