package com.bhmedia.lichvannien2016.ui.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Van on 3/4/2015.
 */
public class BroadCastManager {
    private static BroadCastManager broadcastManger;

    public static BroadCastManager getInstance() {
        if (broadcastManger == null) {
            broadcastManger = new BroadCastManager();
        }
        return broadcastManger;
    }

    /**
     * This method using to register broadcast receiver
     *
     * @param context
     * @param actionFilter
     * @param
     */
    public void register(Context context, String actionFilter, BroadcastReceiver receiver) {
        LocalBroadcastManager broadCast = LocalBroadcastManager.getInstance(context);
        IntentFilter intent = new IntentFilter(actionFilter);
        broadCast.registerReceiver(receiver, intent);
    }

    public void register(Context context, IntentFilter intentFilter, BroadcastReceiver receiver) {
        LocalBroadcastManager broadCast = LocalBroadcastManager.getInstance(context);
        broadCast.registerReceiver(receiver, intentFilter);
    }

    /**
     * This method using to unregister broadcast
     *
     * @param context
     * @param receiver
     */
    public void unregister(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager broadCast = LocalBroadcastManager.getInstance(context);
        try {
            broadCast.unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context
     * @param actionFilter
     * @param bundle
     */
    public void sendBroadcast(Context context, String actionFilter, Bundle bundle) {
        LocalBroadcastManager broadCast = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(actionFilter);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        broadCast.sendBroadcast(intent);
    }
}
