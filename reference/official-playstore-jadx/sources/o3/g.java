package o3;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.t;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public abstract class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f20745b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f20746c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f20748e = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f20744a = new AtomicBoolean();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicBoolean f20747d = new AtomicBoolean();

    public static void a(Context context) {
        if (f20744a.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException e5) {
            Log.d("GooglePlayServicesUtil", "Suppressing Security Exception %s in cancelAvailabilityErrorNotifications.", e5);
        }
    }

    public static void b(Context context, int i10) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        f fVar = f.f20743b;
        int iB = fVar.b(context, i10);
        if (iB != 0) {
            Intent intentA = fVar.a(context, iB, "e");
            StringBuilder sb2 = new StringBuilder(String.valueOf(iB).length() + 46);
            sb2.append("GooglePlayServices not available due to error ");
            sb2.append(iB);
            Log.e("GooglePlayServicesUtil", sb2.toString());
            if (intentA != null) {
                throw new GooglePlayServicesRepairableException(intentA, iB);
            }
            throw new GooglePlayServicesNotAvailableException();
        }
    }

    public static boolean c(Context context, String str) throws PackageManager.NameNotFoundException {
        boolean zEquals = str.equals("com.google.android.gms");
        try {
            Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getAppPackageName())) {
                    return true;
                }
            }
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (zEquals) {
                return applicationInfo.enabled;
            }
            if (!applicationInfo.enabled) {
                return false;
            }
            Object systemService = context.getSystemService("user");
            t.i(systemService);
            Bundle applicationRestrictions = ((UserManager) systemService).getApplicationRestrictions(context.getPackageName());
            return applicationRestrictions == null || !"true".equals(applicationRestrictions.getString("restricted_profile"));
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }
}
