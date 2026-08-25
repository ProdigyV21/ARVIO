package androidx.core.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.i1;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;
import com.google.common.util.concurrent.w0;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2167i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f2168l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f2169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2170n;

    public /* synthetic */ o() {
        this.f2167i = 0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        switch (this.f2167i) {
            case 0:
                try {
                    objCall = ((f) this.f2168l).call();
                    break;
                } catch (Exception unused) {
                    objCall = null;
                }
                ((Handler) this.f2170n).post(new w0((g) this.f2169m, objCall, 5));
                return;
            case 1:
                BroadcastReceiver.PendingResult pendingResult = (BroadcastReceiver.PendingResult) this.f2170n;
                Context context = (Context) this.f2169m;
                Intent intent = (Intent) this.f2168l;
                try {
                    boolean booleanExtra = intent.getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra2 = intent.getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false);
                    boolean booleanExtra3 = intent.getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra4 = intent.getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false);
                    t.d().a(ConstraintProxyUpdateReceiver.f6728a, "Updating proxies: (BatteryNotLowProxy (" + booleanExtra + "), BatteryChargingProxy (" + booleanExtra2 + "), StorageNotLowProxy (" + booleanExtra3 + "), NetworkStateProxy (" + booleanExtra4 + "), ");
                    androidx.work.impl.utils.j.a(context, ConstraintProxy$BatteryNotLowProxy.class, booleanExtra);
                    androidx.work.impl.utils.j.a(context, ConstraintProxy$BatteryChargingProxy.class, booleanExtra2);
                    androidx.work.impl.utils.j.a(context, ConstraintProxy$StorageNotLowProxy.class, booleanExtra3);
                    androidx.work.impl.utils.j.a(context, ConstraintProxy$NetworkStateProxy.class, booleanExtra4);
                    return;
                } finally {
                    pendingResult.finish();
                }
            default:
                ((androidx.work.impl.g) this.f2168l).j((androidx.work.impl.l) this.f2169m, (i1) this.f2170n);
                return;
        }
    }

    public /* synthetic */ o(Object obj, Object obj2, Object obj3, int i10) {
        this.f2167i = i10;
        this.f2168l = obj;
        this.f2169m = obj2;
        this.f2170n = obj3;
    }
}
