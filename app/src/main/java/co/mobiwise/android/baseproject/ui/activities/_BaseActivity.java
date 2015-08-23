package co.mobiwise.android.baseproject.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.mobiwise.android.baseproject.utils.BusUtil;

public class _BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    BusUtil.BUS.register(this);
  }

  @Override
  protected void onDestroy() {
    try {
      BusUtil.BUS.unregister(this);
    } catch (Exception ignore) {
    }
    super.onDestroy();
  }
}