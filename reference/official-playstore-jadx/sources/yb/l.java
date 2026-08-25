package yb;

import java.io.IOException;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends r implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ i0 f23056i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k0 f23057l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ k0 f23058m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ k0 f23059n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(i0 i0Var, k0 k0Var, k0 k0Var2, k0 k0Var3) {
        super(2);
        this.f23056i = i0Var;
        this.f23057l = k0Var;
        this.f23058m = k0Var2;
        this.f23059n = k0Var3;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) throws IOException {
        int iIntValue = ((Number) obj).intValue();
        long jLongValue = ((Number) obj2).longValue();
        if (iIntValue == 21589) {
            if (jLongValue < 1) {
                throw new IOException("bad zip: extended timestamp extra too short");
            }
            i0 i0Var = this.f23056i;
            byte b10 = i0Var.readByte();
            boolean z = (b10 & 1) == 1;
            boolean z5 = (b10 & 2) == 2;
            boolean z10 = (b10 & 4) == 4;
            long j10 = z ? 5L : 1L;
            if (z5) {
                j10 += 4;
            }
            if (z10) {
                j10 += 4;
            }
            if (jLongValue < j10) {
                throw new IOException("bad zip: extended timestamp extra too short");
            }
            if (z) {
                this.f23057l.f19746i = Long.valueOf(((long) i0Var.j()) * 1000);
            }
            if (z5) {
                this.f23058m.f19746i = Long.valueOf(((long) i0Var.j()) * 1000);
            }
            if (z10) {
                this.f23059n.f19746i = Long.valueOf(((long) i0Var.j()) * 1000);
            }
        }
        return t0.f22605a;
    }
}
