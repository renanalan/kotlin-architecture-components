package br.com.iteris.sampleandroidarchitecture

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.iteris.sampleandroidarchitecture.databinding.RecyclerItemBinding

class UserAdapter internal constructor() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.binding.user = current
    }

    internal fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class UserViewHolder(
            val binding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}