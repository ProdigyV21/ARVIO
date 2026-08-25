package gb;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends s0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final jb.f f15629l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f15630m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f15631n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final xb.i0 f15632o;

    public d(jb.f fVar, String str, String str2) {
        this.f15629l = fVar;
        this.f15630m = str;
        this.f15631n = str2;
        this.f15632o = new xb.i0(new c((xb.o0) fVar.f19431m.get(1), this));
    }

    @Override // gb.s0
    public final long k() {
        String str = this.f15631n;
        if (str == null) {
            return -1L;
        }
        byte[] bArr = ib.c.f16268a;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    @Override // gb.s0
    public final d0 l() {
        String str = this.f15630m;
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

    @Override // gb.s0
    public final xb.l o() {
        return this.f15632o;
    }
}
