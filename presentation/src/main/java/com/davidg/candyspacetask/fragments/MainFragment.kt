package com.davidg.candyspacetask.fragments
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.viewmodels.MainViewModel
import kotlinx.coroutines.flow.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.progress_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolBar()
        mainViewModel.searchUser()
    }

    private fun setUpToolBar(){
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

}
