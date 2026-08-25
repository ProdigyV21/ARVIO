package android.support.v4.os;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import androidx.emoji2.text.j;
import androidx.mediarouter.media.z;
import androidx.work.impl.foreground.SystemForegroundService;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.internal.auth.f0;
import com.google.android.gms.internal.cast.c6;
import com.google.android.gms.internal.cast.f1;
import com.google.android.gms.internal.cast.g1;
import com.google.android.gms.internal.cast.h1;
import com.google.android.gms.internal.cast.ja;
import com.google.android.gms.internal.cast.x0;
import com.google.android.gms.internal.cast.z4;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;
import com.google.android.gms.tasks.s;
import com.google.android.gms.tasks.t;
import java.util.ArrayList;
import java.util.List;
import p0.g;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f911i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f912l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f913m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f914n;

    public /* synthetic */ e(int i10, int i11, Object obj, Object obj2) {
        this.f911i = i11;
        this.f913m = obj;
        this.f914n = obj2;
        this.f912l = i10;
    }

    private final /* synthetic */ void a() {
        x0 x0Var = (x0) this.f913m;
        z zVar = (z) this.f914n;
        int i10 = this.f912l;
        synchronized (x0Var.f13675e) {
            x0Var.c(zVar, i10);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        s sVar;
        t tVar;
        switch (this.f911i) {
            case 0:
                ((f) this.f914n).onReceiveResult(this.f912l, (Bundle) this.f913m);
                return;
            case 1:
                ((TextView) this.f913m).setTypeface((Typeface) this.f914n, this.f912l);
                return;
            case 2:
                ArrayList arrayList = (ArrayList) this.f913m;
                int size = arrayList.size();
                int i10 = 0;
                if (this.f912l != 1) {
                    while (i10 < size) {
                        ((j) arrayList.get(i10)).onFailed((Throwable) this.f914n);
                        i10++;
                    }
                    return;
                } else {
                    while (i10 < size) {
                        ((j) arrayList.get(i10)).onInitialized();
                        i10++;
                    }
                    return;
                }
            case 3:
                ((androidx.work.impl.background.systemalarm.j) this.f913m).a((Intent) this.f914n, this.f912l);
                return;
            case 4:
                ((SystemForegroundService) this.f914n).f6863o.notify(this.f912l, (Notification) this.f913m);
                return;
            case 5:
                a();
                return;
            default:
                z4 z4Var = (z4) this.f913m;
                c6 c6Var = (c6) this.f914n;
                int i11 = this.f912l;
                h1 h1Var = z4Var.f13716h;
                if (h1Var == null) {
                    return;
                }
                synchronized (h1Var) {
                    i iVar = new i();
                    f0 f0Var = h1Var.f13274a;
                    f0Var.getClass();
                    x xVarBuilder = y.builder();
                    xVarBuilder.f12807a = new ja(f0Var, 6);
                    xVarBuilder.f12810d = 4501;
                    h hVarDoRead = f0Var.doRead(xVarBuilder.a());
                    f1 f1Var = new f1(iVar);
                    t tVar2 = (t) hVarDoRead;
                    tVar2.getClass();
                    sVar = com.google.android.gms.tasks.j.f13856a;
                    tVar2.c(sVar, f1Var);
                    tVar2.b(sVar, new f1(iVar));
                    h1Var.f13276c.postDelayed(new g1(iVar, 0), h1Var.f13275b * 1000);
                    tVar = iVar.f13855a;
                }
                androidx.appcompat.widget.f0 f0Var2 = new androidx.appcompat.widget.f0(z4Var, c6Var, i11);
                tVar.getClass();
                tVar.c(sVar, f0Var2);
                return;
        }
    }

    public /* synthetic */ e(Object obj, int i10, Parcelable parcelable, int i11) {
        this.f911i = i11;
        this.f914n = obj;
        this.f912l = i10;
        this.f913m = parcelable;
    }

    public e(List list, int i10, Throwable th) {
        this.f911i = 2;
        g.b(list, "initCallbacks cannot be null");
        this.f913m = new ArrayList(list);
        this.f912l = i10;
        this.f914n = th;
    }
}
