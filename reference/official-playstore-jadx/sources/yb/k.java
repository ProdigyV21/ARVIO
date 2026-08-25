package yb;

import java.io.IOException;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f0 f23050i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f23051l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ j0 f23052m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i0 f23053n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ j0 f23054o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ j0 f23055p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(f0 f0Var, long j10, j0 j0Var, i0 i0Var, j0 j0Var2, j0 j0Var3) {
        super(2);
        this.f23050i = f0Var;
        this.f23051l = j10;
        this.f23052m = j0Var;
        this.f23053n = i0Var;
        this.f23054o = j0Var2;
        this.f23055p = j0Var3;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) throws IOException {
        int iIntValue = ((Number) obj).intValue();
        long jLongValue = ((Number) obj2).longValue();
        if (iIntValue == 1) {
            f0 f0Var = this.f23050i;
            if (f0Var.f19738i) {
                throw new IOException("bad zip: zip64 extra repeated");
            }
            f0Var.f19738i = true;
            if (jLongValue < this.f23051l) {
                throw new IOException("bad zip: zip64 extra too short");
            }
            j0 j0Var = this.f23052m;
            long jK = j0Var.f19745i;
            i0 i0Var = this.f23053n;
            if (jK == 4294967295L) {
                jK = i0Var.k();
            }
            j0Var.f19745i = jK;
            j0 j0Var2 = this.f23054o;
            j0Var2.f19745i = j0Var2.f19745i == 4294967295L ? i0Var.k() : 0L;
            j0 j0Var3 = this.f23055p;
            j0Var3.f19745i = j0Var3.f19745i == 4294967295L ? i0Var.k() : 0L;
        }
        return t0.f22605a;
    }
}
