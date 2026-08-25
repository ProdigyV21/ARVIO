package com.pairip.licensecheck;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LicenseActivity extends Activity {
    public static final String ACTIVITY_TYPE_ARG_NAME = "activitytype";
    public static final String PAYWALL_INTENT_ARG_NAME = "paywallintent";
    private static final String TAG = "LicenseActivity";

    public enum ActivityType {
        PAYWALL,
        ERROR_DIALOG
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        try {
            int iOrdinal = ((ActivityType) getIntent().getSerializableExtra(ACTIVITY_TYPE_ARG_NAME)).ordinal();
            if (iOrdinal == 0) {
                showPaywallAndCloseApp();
            } else {
                if (iOrdinal != 1) {
                    return;
                }
                showErrorDialog();
            }
        } catch (Exception e5) {
            logAndShowErrorDialog("Couldn't process license activity correctly.", e5);
        }
    }

    private void showPaywallAndCloseApp() {
        final PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra(PAYWALL_INTENT_ARG_NAME);
        if (pendingIntent == null) {
            logAndShowErrorDialog("Paywall intent is not provided.");
        } else {
            runOnUiThread(new Runnable() { // from class: com.pairip.licensecheck.LicenseActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showPaywallAndCloseApp$0(pendingIntent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPaywallAndCloseApp$0(PendingIntent pendingIntent) {
        try {
            if (Build.VERSION.SDK_INT >= 34) {
                pendingIntent.send(ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle());
            } else {
                pendingIntent.send();
            }
            closeApp();
        } catch (PendingIntent.CanceledException e5) {
            logAndShowErrorDialog("Paywall intent unexpectedly cancelled.", e5);
        }
    }

    private void logAndShowErrorDialog(String message, Exception ex) {
        logAndShowErrorDialog(message + " " + Log.getStackTraceString(ex));
    }

    private void logAndShowErrorDialog(String message) {
        Log.e(TAG, message);
        showErrorDialog();
    }

    private void showErrorDialog() {
        runOnUiThread(new Runnable() { // from class: com.pairip.licensecheck.LicenseActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showErrorDialog$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showErrorDialog$0() {
        try {
            new AlertDialog.Builder(this).setTitle("Something went wrong").setMessage("Check that Google Play is enabled on your device and that you're using an up-to-date version before opening the app. If the problem persists try reinstalling the app.").setPositiveButton("Close", new DialogInterface.OnClickListener() { // from class: com.pairip.licensecheck.LicenseActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    this.f$0.lambda$showErrorDialog$1(dialogInterface, i10);
                }
            }).setCancelable(false).show();
        } catch (RuntimeException e5) {
            Log.d(TAG, "Couldn't show the error dialog. " + Log.getStackTraceString(e5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showErrorDialog$1(DialogInterface dialogInterface, int i10) {
        closeApp();
    }

    private void closeApp() {
        if (LicenseClient.gracefulShutdownEnabled) {
            closeAllTasks();
        } else {
            exitApp();
        }
    }

    protected void exitApp() {
        finishAndRemoveTask();
        System.exit(0);
    }

    protected void closeAllTasks() {
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager != null) {
            for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
                try {
                    appTask.finishAndRemoveTask();
                } catch (RuntimeException e5) {
                    Log.e(TAG, "Failed to gracefully clear task=" + appTask.getTaskInfo().id, e5);
                }
            }
        }
        exitApp();
    }
}
