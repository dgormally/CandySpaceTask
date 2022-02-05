package com.davidg.candyspacetask.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.davidg.candyspacetask.domain.model.StackUsersModel
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class DetailsFragmentArgs(
  public val userDetails: StackUsersModel
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailsFragmentArgs {
      bundle.setClassLoader(DetailsFragmentArgs::class.java.classLoader)
      val __userDetails : StackUsersModel?
      if (bundle.containsKey("userDetails")) {
        if (Parcelable::class.java.isAssignableFrom(StackUsersModel::class.java) ||
            Serializable::class.java.isAssignableFrom(StackUsersModel::class.java)) {
          __userDetails = bundle.get("userDetails") as StackUsersModel?
        } else {
          throw UnsupportedOperationException(StackUsersModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__userDetails == null) {
          throw IllegalArgumentException("Argument \"userDetails\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"userDetails\" is missing and does not have an android:defaultValue")
      }
      return DetailsFragmentArgs(__userDetails)
    }
  }
}
