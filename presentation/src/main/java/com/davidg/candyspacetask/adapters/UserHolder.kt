package com.davidg.candyspacetask.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.davidg.candyspacetask.adapters.adaptersInterface.StackItemInterface
import com.davidg.candyspacetask.domain.model.StackUsersModel
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

class UserHolder(
    itemView: View,
    private val stackItemInterface: StackItemInterface,
    private val coroutineScope: CoroutineScope

) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(user: StackUsersModel) {
        itemView.heroName.text = user.userName
        itemView.clicks().onEach { stackItemInterface.onItemClickListener(user) }.launchIn(coroutineScope)
    }

}