// Generated by view binder compiler. Do not edit!
package com.davidg.candyspacetask.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.davidg.candyspacetask.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ProgressLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout progress;

  @NonNull
  public final ProgressBar progressScan;

  @NonNull
  public final TextView txtConnecting;

  private ProgressLayoutBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout progress,
      @NonNull ProgressBar progressScan, @NonNull TextView txtConnecting) {
    this.rootView = rootView;
    this.progress = progress;
    this.progressScan = progressScan;
    this.txtConnecting = txtConnecting;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ProgressLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ProgressLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.progress_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ProgressLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout progress = (LinearLayout) rootView;

      id = R.id.progress_scan;
      ProgressBar progressScan = rootView.findViewById(id);
      if (progressScan == null) {
        break missingId;
      }

      id = R.id.txtConnecting;
      TextView txtConnecting = rootView.findViewById(id);
      if (txtConnecting == null) {
        break missingId;
      }

      return new ProgressLayoutBinding((LinearLayout) rootView, progress, progressScan,
          txtConnecting);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
