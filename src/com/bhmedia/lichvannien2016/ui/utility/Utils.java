package com.bhmedia.lichvannien2016.ui.utility;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bhmedia.lichvannien2016.R;


public class Utils {

   static ProgressDialog progressDialog;

   public static void clearBackStackByPopping(FragmentManager fm) {
      //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
      for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
         fm.popBackStack();
      }
   }

   public static void hideSoftKeyboard(Activity activity) {
      InputMethodManager inputMethodManager = (InputMethodManager) activity
         .getSystemService(Activity.INPUT_METHOD_SERVICE);
      try {
         inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
      } catch (NullPointerException ignored) {

      }
   }


   public static boolean isNetworkConnected(Context context) {
      ConnectivityManager connMgr = (ConnectivityManager) context
         .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
      return (networkInfo != null && networkInfo.isConnected());
   }

   public static void showAlertDialog(Context context, int titleID, int messageID,
                                      boolean isSuccess) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setIcon(
         !isSuccess ? android.R.drawable.stat_sys_warning : android.R.drawable.ic_dialog_info)
         .setTitle(titleID).setMessage(messageID).setCancelable(true)
         .setPositiveButton(R.string.string_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
               // do nothing
            }
         });
      AlertDialog dialog = builder.create();
      dialog.show();
   }

   /**
    * @param context   must be Activity Context
    * @param titleID
    * @param message
    * @param isSuccess
    */
   public static void showAlertDialog(Context context, int titleID, String message,
                                      boolean isSuccess) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setIcon(
         !isSuccess ? android.R.drawable.stat_sys_warning : android.R.drawable.ic_dialog_info)
         .setTitle(titleID).setMessage(message).setCancelable(true)
         .setPositiveButton(R.string.string_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
               // do nothing
            }
         });
      AlertDialog dialog = builder.create();
      dialog.show();
   }

   public static void setupUI(View view, final Activity activity) {

      //Set up touch listener for non-text box views to hide keyboard.
      if (!(view instanceof EditText)) {

         view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
               hideSoftKeyboard(activity);
               return false;
            }

         });
      }

      //If a layout container, iterate over children and seed recursion.
      if (view instanceof ViewGroup) {

         for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

            View innerView = ((ViewGroup) view).getChildAt(i);

            setupUI(innerView, activity);
         }
      }
   }

   public static String parseDateToString(Date date, String pattern) {
      return date != null && pattern != null ? new SimpleDateFormat(pattern).format(date) : null;
   }

   public static Date parseStringtoDate(String data, String pattern) {
      SimpleDateFormat formatter = new SimpleDateFormat(pattern);
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date date = null;
      try {
         date = formatter.parse(data);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return date;
   }

   public static void sendLocalBroadcast(Context context, Intent intent) {
      LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
   }

   public static void registerLocalReceiver(Context context, BroadcastReceiver receiver,
                                            IntentFilter filter) {
      LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
   }

   public static void unregisterLocalReceiver(Context context, BroadcastReceiver receiver) {
      if (receiver != null) {
         LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
      }
   }

   public static boolean isValidEmail(String email) {
      String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
         + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

      Pattern pattern = Pattern.compile(EMAIL_PATTERN);
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
   }

   public static boolean isValidPassword(String pass) {
      return pass != null && pass.length() >= 3;
   }

   public static boolean isValidConfirmPassword(String pass, String confirmPass) {
      return pass.equals(confirmPass);
   }

   public static SpannableStringBuilder errorText(String errorText) {
      ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#ffffff"));
      SpannableStringBuilder builder = new SpannableStringBuilder(errorText);
      builder.setSpan(span, 0, errorText.length(), 0);
      return builder;
   }

   public static boolean isStringEmpty(String str) {
      return (str == null || "".equals(str.trim()));
   }

   public static int[] getTimeExpired(String fromTime, String toTime) {
      int res[] = new int[3];
      if (isStringEmpty(fromTime) || isStringEmpty(toTime))
         return res;

      SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
      try {
         long delta = format.parse(toTime).getTime() - format.parse(fromTime).getTime();
         if (delta < 0)
            res[0] = res[1] = res[2] = 0;
         else {
            Date dateExpired = new Date(delta);
            Calendar cl = Calendar.getInstance();
            cl.setTime(dateExpired);
            res[0] = cl.get(Calendar.DAY_OF_MONTH);
            res[1] = cl.get(Calendar.HOUR_OF_DAY);
            res[2] = cl.get(Calendar.MINUTE);
         }

      } catch (ParseException e) {
      }

      return res;
   }

   public static void showProgressDialog(Context context, int titleId) {
       if (progressDialog != null) {
           progressDialog.dismiss();
       }
       progressDialog = new ProgressDialog(context);
      progressDialog.setTitle(null);
      progressDialog.setMessage(context.getString(titleId));
      progressDialog.setCanceledOnTouchOutside(false);
      if (progressDialog.isShowing()) {
         progressDialog.dismiss();
      }
      progressDialog.show();
   }

    public static void showProgressDialog(Context context, String message) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(null);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

   public static void hideProgressDialog() {
      if (progressDialog != null && progressDialog.isShowing()) {
         progressDialog.dismiss();
      }
   }

}