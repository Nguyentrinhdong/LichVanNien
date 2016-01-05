package com.bhmedia.lichvannien2016.ui.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Van on 1/14/2015.
 */
public class FragmentUtils {

    public static void clearBackStackByPopping(FragmentManager fm) {
        if (fm == null)
            return;

        try {
            //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    public static Fragment getCurrentFragment(FragmentActivity activity) {
        if (activity == null)
            return null;

        FragmentManager manager = activity.getSupportFragmentManager();
        List<Fragment> fragmentList = manager.getFragments();
        if (fragmentList != null) {
            for (int i = fragmentList.size() - 1; i > -1; i--) {
                Fragment aFragmentList = fragmentList.get(i);
                if (aFragmentList != null && aFragmentList.isVisible())
                    return aFragmentList;
            }
        }
        return null;
    }

    public static List<Fragment> getCurrentChildFragment(FragmentActivity activity) {
        if (activity == null)
            return null;

        Fragment currentFragment = getCurrentFragment(activity);
        if (currentFragment == null)
            return null;
        FragmentManager manager = currentFragment.getChildFragmentManager();
        List<Fragment> fragmentList = manager.getFragments();
        List<Fragment> fragVisibleList = new ArrayList<Fragment>();
        if (fragmentList != null) {
            for (int i = fragmentList.size() - 1; i > -1; i--) {
                Fragment aFragmentList = fragmentList.get(i);
                if (aFragmentList != null
                        && !aFragmentList.getClass().getName().equals(currentFragment.getClass().getName())
                        && aFragmentList.isVisible())
                    fragVisibleList.add(aFragmentList);
            }
        }
        if (!fragmentList.isEmpty())
            return fragVisibleList;
        return null;
    }

    public static Fragment getCurrentAChildFragment(FragmentActivity activity) {
        if (activity == null)
            return null;

        Fragment currentFragment = getCurrentFragment(activity);
        if (currentFragment == null)
            return null;
        FragmentManager manager = currentFragment.getChildFragmentManager();
        List<Fragment> fragmentList = manager.getFragments();
        if (fragmentList != null) {
            for (int i = fragmentList.size() - 1; i > -1; i--) {
                Fragment aFragmentList = fragmentList.get(i);
                if (aFragmentList != null && aFragmentList.isVisible())
                    return aFragmentList;
            }
        }
        return null;
    }


}


