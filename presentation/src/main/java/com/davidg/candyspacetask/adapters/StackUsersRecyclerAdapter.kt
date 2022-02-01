package com.davidg.candyspacetask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.adapters.adaptersInterface.StackItemInterface
import com.davidg.candyspacetask.domain.model.StackUsersModel
import kotlinx.coroutines.CoroutineScope

class StackUsersRecyclerAdapter(private var users: List<StackUsersModel>,
    private val coroutineScope: CoroutineScope,
                                private val stackItemInterface: StackItemInterface) :
    RecyclerView.Adapter<UserHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserHolder(view, stackItemInterface, coroutineScope)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        users[position]?.let { holder.bindItem(it) }
    }

    fun submitData(users: List<StackUsersModel>) {
        this.users = users
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
      return users.size
    }
}
