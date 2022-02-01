package com.davidg.candyspacetask.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.adapters.StackUsersRecyclerAdapter
import com.davidg.candyspacetask.adapters.adaptersInterface.StackItemInterface
import com.davidg.candyspacetask.common.extensions.hideKeyboard
import com.davidg.candyspacetask.common.utils.CountingIdlingResourceSingleton
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.ui.StackListViewState
import com.davidg.candyspacetask.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.empty.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.progress_layout.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException

final class MainFragment : Fragment(R.layout.fragment_main), StackItemInterface {

   val mainViewModel: MainViewModel by viewModel()

    private val usersAdapter: StackUsersRecyclerAdapter by lazy {
        StackUsersRecyclerAdapter(emptyList(), coroutineScope = lifecycleScope, stackItemInterface = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolBar()
        setupRecyclerView()
        setUpOnClickListeners()
        getUsers()
        recycler_users.adapter = usersAdapter
    }

    private fun setUpOnClickListeners() {
        searchBtn.setOnClickListener {
          if(search_bar.text.toString().isNotEmpty()) {
               mainViewModel.getUsers(search_bar.text.toString())
                search_bar.text.clear()
                hideKeyboard()
            }
        }
    }

    @VisibleForTesting
    fun getUsersTest(){
        mainViewModel.getUsers()
    }

    @VisibleForTesting
    fun getUsers(){
        lifecycleScope.launchWhenStarted {
          mainViewModel._usersStateFlow.collect {
                when (it) {
                    is StackListViewState.Loading -> {
                        progress.isVisible = true
                        recycler_users.visibility = View.GONE
                    }

                    is StackListViewState.Success -> {
                        progress.isVisible = false
                        recycler_users.visibility = View.VISIBLE
                        empty.visibility = View.GONE
                        usersAdapter.submitData(it.data)
                    }

                    is StackListViewState.Failure -> {
                        //This is how we would retrieve the api error respond. I'm NOT going
                        //to use it because the messages are not user friendly, so instead I'm just going to display a generic message
                        try {
                            val apiErrorResponse = it.errorModel as ErrorModel.ServerError
                            if (apiErrorResponse != null) {
                                Log.v("DAVID", "Response code is ${apiErrorResponse.code}")
                                Log.v("DAVID", "Response message is ${apiErrorResponse.message}")
                            }
                        }catch (ex: ClassCastException){
                            ex.printStackTrace()
                        }
                        progress.isVisible = false
                        recycler_users.visibility = View.GONE
                        empty.visibility = View.VISIBLE
                        message.text = getString(R.string.opps)
                    }

                is StackListViewState.Empty -> {
                        progress.isVisible = false
                        recycler_users.visibility = View.GONE
                        empty.visibility = View.VISIBLE
                        message.text = getString(R.string.no_results)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManagerVertical = LinearLayoutManager(requireContext())
        recycler_users.layoutManager = layoutManagerVertical
        val layoutManagerHorizontal =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler_users.layoutManager = layoutManagerHorizontal
    }


    private fun setUpToolBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowHomeEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.app_name)
    }

    override fun onItemClickListener(item: StackUsersModel) {
        item.let {
            val action = MainFragmentDirections.toDetails(it)
            view?.findNavController()?.navigate(action)
        }
    }
    companion object {
        fun newInstance() = MainFragment()
    }
}

