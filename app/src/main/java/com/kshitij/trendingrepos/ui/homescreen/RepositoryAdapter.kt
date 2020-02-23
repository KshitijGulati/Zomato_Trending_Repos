package com.kshitij.trendingrepos.ui.homescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kshitij.trendingrepos.databinding.RowTrendingRepoBinding
import com.kshitij.trendingrepos.utils.hide
import com.kshitij.trendingrepos.utils.setDrawableStartColor
import com.kshitij.trendingrepos.utils.setRoundedImage
import com.kshitij.trendingrepos.utils.show

class RepositoryAdapter(private val list: List<RepositoryItem>?) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        val binding =
            RowTrendingRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.setOnItemClick {
            val repositoryItem = list?.get(position)
            repositoryItem?.let {
                it.isExpanded = !it.isExpanded
            }
            notifyItemChanged(position)
        }
        holder.bind(list?.get(position))
    }


    class RepositoryItemViewHolder(private val binding: RowTrendingRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryItem: RepositoryItem?) {
            binding.apply {
                repositoryItem?.let {
                    tvStars.text = it.stars.toString()
                    tvFork.text = it.forks.toString()
                    tvLanguage.text = it.language
                    tvRepositoryName.text = it.repoName
                    tvUsername.text = it.username
                    tvDesc.text = it.repoDesc
                    it.languageColor?.let { binding.tvLanguage.setDrawableStartColor(it) }
                    it.avatarUrl?.let { binding.ivAvatar.setRoundedImage(it) }
                    if (it.isExpanded) {
                        expansionGroup.show()
                    } else {
                        expansionGroup.hide()
                    }
                }
            }
        }

        fun setOnItemClick(onClick: () -> Unit) {
            binding.root.setOnClickListener {
                onClick.invoke()
            }
        }
    }
}