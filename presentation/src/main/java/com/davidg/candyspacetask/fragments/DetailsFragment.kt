package com.davidg.candyspacetask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected
import coil.load
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.common.extensions.isGreaterThan
import com.davidg.candyspacetask.common.utils.DateTimeUtils
import com.davidg.candyspacetask.domain.model.StackUsersModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.userDetails
        setUpToolBar(data.userName)
        setUpUserDetails(data)

    }

    private fun setUpUserDetails(data: StackUsersModel) {
        imgBackdrop.load(data.avatar)
        username.text = "Name: ${data.userName}"
        reputation.text = "Reputation: ${data.reputation}"
        if (data.noOfGold isGreaterThan 0 == true){
            goldBadge.visibility = View.VISIBLE
            goldBadge.text = "Gold badges: ${data.noOfGold}"
        }
        if (data.noOfSilver isGreaterThan 0 == true){
            silverBadge.visibility = View.VISIBLE
            silverBadge.text = "Silver badges: ${data.noOfSilver}"
        }

        if (data.noOfBronze isGreaterThan 0 == true){
            broonzeBadge.visibility = View.VISIBLE
            broonzeBadge.text = "Bronze badges: ${data.noOfBronze}"
        }
        creation_date.text = DateTimeUtils.getDate(data.creation_date)
        if (location.text.isNullOrEmpty().not()) {
            location.text = "Location: ${data.location}"
        }else{
            location.visibility = View.GONE
        }
        creation_date.text =  "Creation date: ${DateTimeUtils.getDate(data.creation_date)}"
    }


    private fun setUpToolBar(userName: String) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = userName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }

}