package co.mobiwise.android.baseproject.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class _BaseFragment extends Fragment {


  protected static final String PARAM_BUNDLE = "PARAM_BUNDLE";
  protected int fragmentId;

  private Bundle savedState;

  public _BaseFragment() {
    fragmentId = (int) System.nanoTime();
    setArguments(new Bundle());
  }

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
  }

  @Override
  public void onActivityCreated(Bundle bundle) {
    super.onActivityCreated(bundle);
    if (!restoreStateFromArguments()) {
      initialize();
    } else {
      onRestore();
    }
  }

  protected abstract void onRestore();

  protected abstract void initialize();

  protected abstract void onSaveState(Bundle bundle);

  protected abstract void onRestoreState(Bundle bundle);

  @Override
  public void onSaveInstanceState(Bundle bundle) {
    saveStateToArguments();
    super.onSaveInstanceState(bundle);
  }

  @Override
  public void onDestroyView() {
    saveStateToArguments();
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  private void saveStateToArguments() {
    if (getView() != null)
      savedState = saveState();
    if (savedState != null) {
      Bundle bundle = getArguments();
      if (bundle != null) {
        bundle.putBundle(PARAM_BUNDLE, savedState);
      }
    }
  }

  private boolean restoreStateFromArguments() {
    Bundle bundle = getArguments();
    if (bundle == null) {
      return false;
    }
    savedState = bundle.getBundle(PARAM_BUNDLE);
    if (savedState == null) {
      return false;
    }
    restoreState();
    return true;
  }

  private void restoreState() {
    if (savedState != null) {
      onRestoreState(savedState);
    }
  }

  private Bundle saveState() {
    Bundle state = new Bundle();
    onSaveState(state);
    return state;
  }

  public boolean onBackPressed() {
    return false;
  }

  public int getFragmentId() {
    return fragmentId;
  }
}