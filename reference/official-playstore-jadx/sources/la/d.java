package la;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.w0;
import d7.j;
import java.util.concurrent.CancellationException;
import ka.h2;
import ka.k2;
import ka.l;
import ka.m0;
import ka.t2;
import ka.x0;
import ka.z0;
import kotlin.jvm.internal.p;
import pa.q;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends e {
    private volatile d _immediate;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Handler f20074i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f20075l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f20076m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d f20077n;

    public d(Handler handler, String str, boolean z) {
        this.f20074i = handler;
        this.f20075l = str;
        this.f20076m = z;
        this._immediate = z ? this : null;
        d dVar = this._immediate;
        if (dVar == null) {
            dVar = new d(handler, str, true);
            this._immediate = dVar;
        }
        this.f20077n = dVar;
    }

    @Override // ka.f0
    public final void dispatch(j jVar, Runnable runnable) {
        if (this.f20074i.post(runnable)) {
            return;
        }
        q0(jVar, runnable);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof d) && ((d) obj).f20074i == this.f20074i;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f20074i);
    }

    @Override // ka.f0
    public final boolean isDispatchNeeded(j jVar) {
        return (this.f20076m && p.a(Looper.myLooper(), this.f20074i.getLooper())) ? false : true;
    }

    @Override // ka.r0
    public final z0 l(long j10, final t2 t2Var, j jVar) {
        if (j10 > 4611686018427387903L) {
            j10 = 4611686018427387903L;
        }
        if (this.f20074i.postDelayed(t2Var, j10)) {
            return new z0() { // from class: la.c
                @Override // ka.z0
                public final void dispose() {
                    this.f20072i.f20074i.removeCallbacks(t2Var);
                }
            };
        }
        q0(jVar, t2Var);
        return k2.f19604i;
    }

    public final void q0(j jVar, Runnable runnable) {
        m0.g(jVar, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        x0.f19655d.dispatch(jVar, runnable);
    }

    @Override // ka.f0
    public final String toString() {
        d dVar;
        String str;
        x0 x0Var = x0.f19652a;
        h2 h2Var = q.f21294a;
        if (this == h2Var) {
            str = "Dispatchers.Main";
        } else {
            try {
                dVar = ((d) h2Var).f20077n;
            } catch (UnsupportedOperationException unused) {
                dVar = null;
            }
            str = this == dVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.f20075l;
        if (string == null) {
            string = this.f20074i.toString();
        }
        return this.f20076m ? androidx.compose.material3.d.m(string, ".immediate") : string;
    }

    @Override // ka.r0
    public final void y(long j10, l lVar) {
        w0 w0Var = new w0(lVar, this, 26);
        if (j10 > 4611686018427387903L) {
            j10 = 4611686018427387903L;
        }
        if (this.f20074i.postDelayed(w0Var, j10)) {
            lVar.c(new androidx.navigation.p(this, w0Var, 12));
        } else {
            q0(lVar.f19609o, w0Var);
        }
    }

    public d(Handler handler) {
        this(handler, null, false);
    }
}
