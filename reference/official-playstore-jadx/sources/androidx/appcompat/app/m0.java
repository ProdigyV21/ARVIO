package androidx.appcompat.app;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.PowerManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends o0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1117m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ s0 f1118n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Object f1119o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(s0 s0Var, i1 i1Var) {
        super(s0Var);
        this.f1118n = s0Var;
        this.f1119o = i1Var;
    }

    @Override // androidx.appcompat.app.o0
    public final IntentFilter f() {
        switch (this.f1117m) {
            case 0:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            default:
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.TIME_SET");
                intentFilter2.addAction("android.intent.action.TIMEZONE_CHANGED");
                intentFilter2.addAction("android.intent.action.TIME_TICK");
                return intentFilter2;
        }
    }

    @Override // androidx.appcompat.app.o0
    public final int g() {
        Location location;
        boolean z;
        long j10;
        switch (this.f1117m) {
            case 0:
                return ((PowerManager) this.f1119o).isPowerSaveMode() ? 2 : 1;
            default:
                i1 i1Var = (i1) this.f1119o;
                h1 h1Var = (h1) i1Var.f1061n;
                LocationManager locationManager = (LocationManager) i1Var.f1060m;
                if (h1Var.f1055b <= System.currentTimeMillis()) {
                    Context context = (Context) i1Var.f1059l;
                    Location lastKnownLocation = null;
                    if (qb.l.e(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                        try {
                        } catch (Exception e5) {
                            Log.d("TwilightManager", "Failed to get last known location", e5);
                        }
                        Location lastKnownLocation2 = locationManager.isProviderEnabled("network") ? locationManager.getLastKnownLocation("network") : null;
                        location = lastKnownLocation2;
                    } else {
                        location = null;
                    }
                    if (qb.l.e(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                        try {
                            if (locationManager.isProviderEnabled("gps")) {
                                lastKnownLocation = locationManager.getLastKnownLocation("gps");
                            }
                        } catch (Exception e6) {
                            Log.d("TwilightManager", "Failed to get last known location", e6);
                        }
                    }
                    if (lastKnownLocation == null || location == null ? lastKnownLocation != null : lastKnownLocation.getTime() > location.getTime()) {
                        location = lastKnownLocation;
                    }
                    if (location != null) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (g1.f1050d == null) {
                            g1.f1050d = new g1();
                        }
                        g1 g1Var = g1.f1050d;
                        g1Var.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
                        g1Var.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
                        z = g1Var.f1051a == 1;
                        long j11 = g1Var.f1053c;
                        long j12 = g1Var.f1052b;
                        g1Var.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
                        long j13 = g1Var.f1053c;
                        if (j11 == -1 || j12 == -1) {
                            j10 = jCurrentTimeMillis + 43200000;
                        } else {
                            if (jCurrentTimeMillis > j12) {
                                j11 = j13;
                            } else if (jCurrentTimeMillis > j11) {
                                j11 = j12;
                            }
                            j10 = j11 + 60000;
                        }
                        h1Var.f1054a = z;
                        h1Var.f1055b = j10;
                    } else {
                        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                        int i10 = Calendar.getInstance().get(11);
                        if (i10 < 6 || i10 >= 22) {
                            z = true;
                        }
                    }
                    break;
                } else {
                    z = h1Var.f1054a;
                }
                return z ? 2 : 1;
        }
    }

    @Override // androidx.appcompat.app.o0
    public final void h() throws IllegalAccessException {
        switch (this.f1117m) {
            case 0:
                this.f1118n.o(true, true);
                break;
            default:
                this.f1118n.o(true, true);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(s0 s0Var, Context context) {
        super(s0Var);
        this.f1118n = s0Var;
        this.f1119o = (PowerManager) context.getApplicationContext().getSystemService("power");
    }
}
