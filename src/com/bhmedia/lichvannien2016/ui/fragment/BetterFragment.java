package com.bhmedia.lichvannien2016.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Van on 1/14/2015.
 */
public abstract class BetterFragment extends Fragment {

    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId > 0) {
            contentView = inflater.inflate(layoutId, null);

        } else {
            contentView = super.onCreateView(inflater, container,
                    savedInstanceState);
        }

        onInitializeView(savedInstanceState);
        return contentView;
    }

    protected View findViewById(int resId) {
        return contentView.findViewById(resId);
    }

    protected void onInitializeView(Bundle savedInstanceState) {

    }


    protected int getLayoutId() {
        return 0;
    }
}
