package com.davidg.candyspacetask.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.domain.model.StackUsersModel
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class MainFragmentDirections private constructor() {
  private data class ToDetails(
    public val userDetails: StackUsersModel
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.toDetails

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(StackUsersModel::class.java)) {
        result.putParcelable("userDetails", this.userDetails as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(StackUsersModel::class.java)) {
        result.putSerializable("userDetails", this.userDetails as Serializable)
      } else {
        throw UnsupportedOperationException(StackUsersModel::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  public companion object {
    public fun toDetails(userDetails: StackUsersModel): NavDirections = ToDetails(userDetails)
  }
}
