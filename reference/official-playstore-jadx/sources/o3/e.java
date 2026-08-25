package o3;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.app.c0;
import androidx.core.app.x;
import androidx.fragment.app.b1;
import androidx.fragment.app.h0;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.d0;
import com.google.android.gms.common.internal.e0;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f20740c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f20741d = new e();

    public static AlertDialog d(Activity activity, int i10, e0 e0Var, DialogInterface.OnCancelListener onCancelListener) {
        if (i10 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(activity, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setMessage(d0.b(activity, i10));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Resources resources = activity.getResources();
        String string = i10 != 1 ? i10 != 2 ? i10 != 3 ? resources.getString(R.string.ok) : resources.getString(com.arvio.tv.R.string.common_google_play_services_enable_button) : resources.getString(com.arvio.tv.R.string.common_google_play_services_update_button) : resources.getString(com.arvio.tv.R.string.common_google_play_services_install_button);
        if (string != null) {
            builder.setPositiveButton(string, e0Var);
        }
        String strA = d0.a(activity, i10);
        if (strA != null) {
            builder.setTitle(strA);
        }
        Log.w("GoogleApiAvailability", a0.c.i(i10, "Creating dialog for Google Play services availability issue. ConnectionResult="), new IllegalArgumentException());
        return builder.create();
    }

    public static void g(Activity activity, AlertDialog alertDialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof h0) {
                b1 supportFragmentManager = ((h0) activity).getSupportFragmentManager();
                k kVar = new k();
                t.j(alertDialog, "Cannot display null dialog");
                alertDialog.setOnCancelListener(null);
                alertDialog.setOnDismissListener(null);
                kVar.f20751i = alertDialog;
                if (onCancelListener != null) {
                    kVar.f20752l = onCancelListener;
                }
                kVar.show(supportFragmentManager, str);
                return;
            }
        } catch (NoClassDefFoundError unused) {
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        c cVar = new c();
        t.j(alertDialog, "Cannot display null dialog");
        alertDialog.setOnCancelListener(null);
        alertDialog.setOnDismissListener(null);
        cVar.f20733i = alertDialog;
        if (onCancelListener != null) {
            cVar.f20734l = onCancelListener;
        }
        cVar.show(fragmentManager, str);
    }

    public final void c(GoogleApiActivity googleApiActivity, int i10, GoogleApiActivity googleApiActivity2) {
        AlertDialog alertDialogD = d(googleApiActivity, i10, new e0(super.a(googleApiActivity, i10, "d"), googleApiActivity, 0), googleApiActivity2);
        if (alertDialogD == null) {
            return;
        }
        g(googleApiActivity, alertDialogD, "GooglePlayServicesErrorDialog", googleApiActivity2);
    }

    public final void e(Activity activity, com.google.android.gms.common.api.internal.k kVar, int i10, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog alertDialogD = d(activity, i10, new e0(super.a(activity, i10, "d"), kVar, 1), onCancelListener);
        if (alertDialogD == null) {
            return;
        }
        g(activity, alertDialogD, "GooglePlayServicesErrorDialog", onCancelListener);
    }

    public final void f(Context context, int i10, PendingIntent pendingIntent) {
        int i11;
        Log.w("GoogleApiAvailability", androidx.compose.foundation.c.o(i10, "GMS core API Availability. ConnectionResult=", ", tag=null"), new IllegalArgumentException());
        if (i10 == 18) {
            new l(this, context).sendEmptyMessageDelayed(1, 120000L);
            return;
        }
        if (pendingIntent == null) {
            if (i10 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String strE = i10 == 6 ? d0.e(context, "common_google_play_services_resolution_required_title") : d0.a(context, i10);
        if (strE == null) {
            strE = context.getResources().getString(com.arvio.tv.R.string.common_google_play_services_notification_ticker);
        }
        String strD = (i10 == 6 || i10 == 19) ? d0.d(context, "common_google_play_services_resolution_required_text", d0.c(context)) : d0.b(context, i10);
        Resources resources = context.getResources();
        Object systemService = context.getSystemService("notification");
        t.i(systemService);
        NotificationManager notificationManager = (NotificationManager) systemService;
        androidx.core.app.d0 d0Var = new androidx.core.app.d0(context, null);
        d0Var.f1978r = true;
        d0Var.c(16, true);
        d0Var.f1966e = androidx.core.app.d0.b(strE);
        c0 c0Var = new c0(0);
        c0Var.f1961b = androidx.core.app.d0.b(strD);
        d0Var.e(c0Var);
        PackageManager packageManager = context.getPackageManager();
        if (c4.d.f7403b == null) {
            c4.d.f7403b = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        boolean zBooleanValue = c4.d.f7403b.booleanValue();
        int i12 = R.drawable.stat_sys_warning;
        if (zBooleanValue) {
            int i13 = context.getApplicationInfo().icon;
            if (i13 != 0) {
                i12 = i13;
            }
            d0Var.B.icon = i12;
            d0Var.f1970i = 2;
            if (c4.d.d(context)) {
                d0Var.f1963b.add(new x(2131230950, resources.getString(com.arvio.tv.R.string.common_open_on_phone), pendingIntent));
            } else {
                d0Var.f1968g = pendingIntent;
            }
        } else {
            d0Var.B.icon = R.drawable.stat_sys_warning;
            d0Var.B.tickerText = androidx.core.app.d0.b(resources.getString(com.arvio.tv.R.string.common_google_play_services_notification_ticker));
            d0Var.B.when = System.currentTimeMillis();
            d0Var.f1968g = pendingIntent;
            d0Var.f1967f = androidx.core.app.d0.b(strD);
        }
        if (c4.d.c()) {
            if (!c4.d.c()) {
                throw new IllegalStateException();
            }
            synchronized (f20740c) {
            }
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
            String string = context.getResources().getString(com.arvio.tv.R.string.common_google_play_services_notification_channel_name);
            if (notificationChannel == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string, 4));
            } else if (!string.contentEquals(notificationChannel.getName())) {
                notificationChannel.setName(string);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            d0Var.x = "com.google.android.gms.availability";
        }
        Notification notificationA = d0Var.a();
        if (i10 == 1 || i10 == 2 || i10 == 3) {
            g.f20744a.set(false);
            i11 = 10436;
        } else {
            i11 = 39789;
        }
        notificationManager.notify(i11, notificationA);
    }
}
