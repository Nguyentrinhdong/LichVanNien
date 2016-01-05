package com.bhmedia.lichvannien2016.ui.fragment;

import com.bhmedia.lichvannien2016.ui.activity.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Van on 1/14/2015.
 */
public class BaseFragment extends BetterFragment {
    public MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected void finish() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
