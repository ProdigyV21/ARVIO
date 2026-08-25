package androidx.media3.session;

import android.app.ForegroundServiceStartNotAllowedException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.media3.common.util.Log;
import j$.util.Objects;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    private static final String[] ACTIONS = {"android.intent.action.MEDIA_BUTTON", MediaLibraryService.SERVICE_INTERFACE, MediaSessionService.SERVICE_INTERFACE};
    private static final String TAG = "MediaButtonReceiver";

    public static final class Api31 {
        private Api31() {
        }

        public static ForegroundServiceStartNotAllowedException castToForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return v.a(illegalStateException);
        }

        public static boolean instanceOfForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return v.b(illegalStateException);
        }
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        StringBuilder sbT = a0.c.t("Expected 1 service that handles ", str, ", found ");
        sbT.append(listQueryIntentServices.size());
        throw new IllegalStateException(sbT.toString());
    }

    public final void handleIntentAndMaybeStartTheService(Context context, Intent intent) {
        if (intent == null || !Objects.equals(intent.getAction(), "android.intent.action.MEDIA_BUTTON") || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d(TAG, "Ignore unsupported intent: " + intent);
            return;
        }
        Bundle extras = intent.getExtras();
        extras.getClass();
        KeyEvent keyEvent = (KeyEvent) extras.getParcelable("android.intent.extra.KEY_EVENT");
        if (keyEvent != null && keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            if (Build.VERSION.SDK_INT >= 26 && keyEvent.getKeyCode() != 126 && keyEvent.getKeyCode() != 85 && keyEvent.getKeyCode() != 79) {
                Log.w(TAG, "Ignore key event that is not a `play` command on API 26 or above to avoid an 'ForegroundServiceDidNotStartInTimeException'");
                return;
            }
            for (String str : ACTIONS) {
                ComponentName serviceComponentByAction = getServiceComponentByAction(context, str);
                if (serviceComponentByAction != null) {
                    Intent intent2 = new Intent();
                    intent2.setComponent(serviceComponentByAction);
                    intent2.fillIn(intent, 0);
                    if (!shouldStartForegroundService(context, intent2)) {
                        Log.i(TAG, "onReceive(Intent) does not start the media button event target service into the foreground on app request: " + serviceComponentByAction.getClassName());
                        return;
                    }
                    try {
                        qb.d.U(context, intent2);
                        return;
                    } catch (IllegalStateException e5) {
                        if (Build.VERSION.SDK_INT < 31 || !Api31.instanceOfForegroundServiceStartNotAllowedException(e5)) {
                            throw e5;
                        }
                        onForegroundServiceStartNotAllowedException(context, intent2, Api31.castToForegroundServiceStartNotAllowedException(e5));
                        return;
                    }
                }
            }
            throw new IllegalStateException("Could not find any Service that handles any of the actions " + Arrays.toString(ACTIONS));
        }
    }

    @Deprecated
    public void onForegroundServiceStartNotAllowedException(Intent intent, ForegroundServiceStartNotAllowedException foregroundServiceStartNotAllowedException) {
        Log.e(TAG, "caught exception when trying to start a foreground service from the background: " + foregroundServiceStartNotAllowedException.getMessage());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        handleIntentAndMaybeStartTheService(context, intent);
    }

    public boolean shouldStartForegroundService(Context context, Intent intent) {
        return true;
    }

    public void onForegroundServiceStartNotAllowedException(Context context, Intent intent, ForegroundServiceStartNotAllowedException foregroundServiceStartNotAllowedException) {
        onForegroundServiceStartNotAllowedException(intent, foregroundServiceStartNotAllowedException);
    }
}
