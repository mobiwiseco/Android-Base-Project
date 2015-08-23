package co.mobiwise.android.baseproject.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public final class BusUtil {

  public static final MainThreadBus BUS = new MainThreadBus();

  private BusUtil() {
  }

  public static class MainThreadBus extends Bus {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        super.post(event);
      } else {
        handler.post(new Runnable() {
          @Override
          public void run() {
            MainThreadBus.super.post(event);
          }
        });
      }
    }
  }
}