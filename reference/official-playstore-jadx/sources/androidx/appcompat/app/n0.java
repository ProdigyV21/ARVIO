package androidx.appcompat.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.mediarouter.media.m1;
import com.google.android.gms.internal.cast.l2;
import com.google.android.gms.internal.cast.o2;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1121b;

    public /* synthetic */ n0(Object obj, int i10) {
        this.f1120a = i10;
        this.f1121b = obj;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        switch (this.f1120a) {
            case 0:
                ((o0) this.f1121b).h();
                break;
            case 1:
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    ((androidx.mediarouter.app.g) this.f1121b).dismiss();
                }
                break;
            case 2:
                ((m1) this.f1121b).a();
                break;
            case 3:
                ((androidx.work.impl.constraints.trackers.d) this.f1121b).f(intent);
                break;
            case 4:
                l2 l2Var = (l2) this.f1121b;
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals("android.intent.action.SCREEN_ON")) {
                        l2Var.b();
                    } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                        l2Var.a();
                    }
                    break;
                }
                break;
            default:
                ((o2) this.f1121b).c();
                break;
        }
    }
}
