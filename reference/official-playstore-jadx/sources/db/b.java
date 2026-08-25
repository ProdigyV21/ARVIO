package db;

import com.google.android.gms.internal.auth.d1;
import eb.i0;
import io.ktor.http.LinkHeader;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements ya.x {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f14877d = new a(new i(false, false, false, false, true, "    ", LinkHeader.Parameters.Type, false, true), fb.d.f15400a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f14878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final fb.b f14879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a8.e f14880c = new a8.e(16);

    public b(i iVar, fb.b bVar) {
        this.f14878a = iVar;
        this.f14879b = bVar;
    }

    @Override // ya.o
    public final fb.b a() {
        return this.f14879b;
    }

    @Override // ya.x
    public final String b(ya.h hVar, Object obj) {
        char[] cArr;
        androidx.core.provider.j jVar = new androidx.core.provider.j(2, (byte) 0);
        eb.g gVar = eb.g.f15137c;
        synchronized (gVar) {
            kotlin.collections.p pVar = gVar.f15146a;
            cArr = null;
            char[] cArr2 = (char[]) (pVar.isEmpty() ? null : pVar.removeLast());
            if (cArr2 != null) {
                gVar.f15147b -= cArr2.length;
                cArr = cArr2;
            }
        }
        if (cArr == null) {
            cArr = new char[128];
        }
        jVar.f2160c = cArr;
        try {
            new eb.g0(new d1(jVar), this, 1, new s[h.f0.d(4).length]).z(hVar, obj);
            return jVar.toString();
        } finally {
            jVar.e();
        }
    }

    @Override // ya.x
    public final Object c(String str, ya.h hVar) {
        i0 i0Var = new i0(str);
        Object objQ = new eb.f0(this, 1, i0Var, hVar.getDescriptor(), null).q(hVar);
        i0Var.q();
        return objQ;
    }

    public final Object f(ya.h hVar, m mVar) {
        j tVar;
        if (mVar instanceof b0) {
            tVar = new eb.w(this, (b0) mVar, null, null);
        } else if (mVar instanceof d) {
            tVar = new eb.x(this, (d) mVar);
        } else {
            if (!(mVar instanceof u ? true : mVar.equals(y.INSTANCE))) {
                throw new NoWhenBranchMatchedException();
            }
            tVar = new eb.t(this, (g0) mVar);
        }
        return eb.r.j(tVar, hVar);
    }

    public final m g(ya.h hVar, Object obj) {
        k0 k0Var = new k0();
        new eb.u(this, new ab.h(k0Var, 13), 1).z(hVar, obj);
        Object obj2 = k0Var.f19746i;
        if (obj2 != null) {
            return (m) obj2;
        }
        kotlin.jvm.internal.p.i("result");
        throw null;
    }
}
