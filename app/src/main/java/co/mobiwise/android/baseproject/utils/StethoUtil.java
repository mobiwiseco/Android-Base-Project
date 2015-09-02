package co.mobiwise.android.baseproject.utils;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import co.mobiwise.android.baseproject.BuildConfig;

public final class StethoUtil {
  private static boolean INIT = false;

  private StethoUtil() {

  }

  public static void init(Context context) {
    if (BuildConfig.DEBUG) {
      Stetho.initialize(
          Stetho.newInitializerBuilder(context)
              .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
              .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
              .build());
      INIT = true;
    }
  }

  public static void addNetworkInterceptor(OkHttpClient client) {
    if (INIT) {
      client.networkInterceptors().add(new StethoInterceptor());
    }
  }
}