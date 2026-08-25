package w5;

import android.content.Context;
import androidx.tv.foundation.lazy.grid.a1;
import com.google.common.util.concurrent.r0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class x implements t {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final u f22492e = new u();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b1.a f22493f = r0.B(s.f22485a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f22494a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d7.j f22495b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReference f22496c = new AtomicReference();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final na.s f22497d;

    public x(Context context, d7.j jVar) {
        this.f22494a = context;
        this.f22495b = jVar;
        f22492e.getClass();
        d7.d dVar = null;
        this.f22497d = new na.s(new na.s(((z0.g) f22493f.getValue(context, u.f22487a[0])).getData(), new androidx.work.impl.constraints.h(3, 1, dVar), 0), this, 4);
        ka.m0.p(ka.l0.a(jVar), null, 0, new a1(this, dVar, 3), 3);
    }

    @Override // w5.t
    public final String a() {
        n nVar = (n) this.f22496c.get();
        if (nVar != null) {
            return nVar.f22472a;
        }
        return null;
    }

    @Override // w5.t
    public final void b(String str) {
        ka.m0.p(ka.l0.a(this.f22495b), null, 0, new androidx.work.impl.constraints.controllers.c(this, str, null, 6), 3);
    }
}
