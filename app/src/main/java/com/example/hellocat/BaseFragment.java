package com.example.hellocat;

import android.app.Fragment;
import android.os.Bundle;

import io.reactivex.disposables.Disposable;

/**
 * @author gegeding
 */
public abstract class BaseFragment extends Fragment {

    protected Disposable disposable;

    protected boolean isVisibleToUser;
    protected boolean isViewInitiated;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();

    }

    public boolean prepareFetchData() {
        if (isVisibleToUser && isViewInitiated) {
            fetchData();
            return true;
        }
        return false;
    }

    public abstract void fetchData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected abstract int geTitleRes();
}
