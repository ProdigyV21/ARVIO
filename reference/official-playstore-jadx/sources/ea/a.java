package ea;

import androidx.work.impl.t;
import java.io.Serializable;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import y8.q;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f15081b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f15082c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Serializable f15083d;

    public /* synthetic */ a(Object obj, Serializable serializable, int i10) {
        this.f15081b = i10;
        this.f15082c = obj;
        this.f15083d = serializable;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // ea.o, ea.c
    public void a(Object obj) {
        switch (this.f15081b) {
            case 2:
                g8.c cVar = (g8.c) obj;
                k0 k0Var = (k0) this.f15082c;
                if (k0Var.f19746i == null && ((Boolean) ((r) this.f15083d).invoke(cVar)).booleanValue()) {
                    k0Var.f19746i = cVar;
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, r7.l] */
    @Override // ea.c
    public final boolean b(Object obj) {
        int i10 = this.f15081b;
        Object obj2 = this.f15083d;
        ?? r4 = this.f15082c;
        switch (i10) {
            case 0:
                boolean[] zArr = (boolean[]) obj2;
                if (((Boolean) r4.invoke(obj)).booleanValue()) {
                    zArr[0] = true;
                }
                break;
            case 1:
                g8.f fVar = (g8.f) obj;
                k0 k0Var = (k0) obj2;
                String str = (String) r4;
                String str2 = f8.d.f15297a;
                f9.b bVarE = f8.d.e(m9.d.g(fVar).i());
                String strM = androidx.compose.foundation.c.m('.', bVarE != null ? n9.b.b(bVarE).e() : t.i(fVar, q.f22971d), str);
                if (f8.q.f15349b.contains(strM)) {
                    k0Var.f19746i = f8.i.f15322i;
                } else if (f8.q.f15351d.contains(strM)) {
                    k0Var.f19746i = f8.i.f15323l;
                } else if (f8.q.f15350c.contains(strM)) {
                    k0Var.f19746i = f8.i.f15324m;
                } else if (f8.q.f15348a.contains(strM)) {
                    k0Var.f19746i = f8.i.f15326o;
                }
                if (k0Var.f19746i == null) {
                }
                break;
            default:
                if (((k0) r4).f19746i == null) {
                }
                break;
        }
        return true;
    }

    @Override // ea.c
    public final Object result() {
        switch (this.f15081b) {
            case 0:
                return Boolean.valueOf(((boolean[]) this.f15083d)[0]);
            case 1:
                f8.i iVar = (f8.i) ((k0) this.f15083d).f19746i;
                return iVar == null ? f8.i.f15325n : iVar;
            default:
                return (g8.c) ((k0) this.f15082c).f19746i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(r7.l lVar, k0 k0Var) {
        this.f15081b = 2;
        this.f15082c = k0Var;
        this.f15083d = (r) lVar;
    }
}
