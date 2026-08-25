package gb;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 extends s0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f15810l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f15811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f15812n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final xb.l f15813o;

    public /* synthetic */ r0(Object obj, long j10, xb.l lVar, int i10) {
        this.f15810l = i10;
        this.f15812n = obj;
        this.f15811m = j10;
        this.f15813o = lVar;
    }

    @Override // gb.s0
    public final long k() {
        switch (this.f15810l) {
        }
        return this.f15811m;
    }

    @Override // gb.s0
    public final d0 l() {
        int i10 = this.f15810l;
        Object obj = this.f15812n;
        switch (i10) {
            case 0:
                return (d0) obj;
            default:
                String str = (String) obj;
                if (str == null) {
                    return null;
                }
                Pattern pattern = d0.f15633d;
                try {
                    return xc.d.i0(str);
                } catch (IllegalArgumentException unused) {
                    return null;
                }
        }
    }

    @Override // gb.s0
    public final xb.l o() {
        switch (this.f15810l) {
            case 0:
                return (xb.j) this.f15813o;
            default:
                return (xb.i0) this.f15813o;
        }
    }
}
