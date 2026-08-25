package vb;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import xb.m;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.k f22319i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Random f22320l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22321m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f22322n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f22323o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final xb.j f22325q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f22326r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a f22327s;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final xb.j f22324p = new xb.j();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final byte[] f22328t = new byte[4];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final xb.g f22329u = new xb.g();

    public k(xb.k kVar, Random random, boolean z, boolean z5, long j10) {
        this.f22319i = kVar;
        this.f22320l = random;
        this.f22321m = z;
        this.f22322n = z5;
        this.f22323o = j10;
        this.f22325q = kVar.h();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        a aVar = this.f22327s;
        if (aVar != null) {
            aVar.close();
        }
    }

    public final void i(int i10, m mVar) {
        if (this.f22326r) {
            throw new IOException("closed");
        }
        int iF = mVar.f();
        if (iF > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        xb.j jVar = this.f22325q;
        jVar.M0(i10 | 128);
        jVar.M0(iF | 128);
        Random random = this.f22320l;
        byte[] bArr = this.f22328t;
        random.nextBytes(bArr);
        jVar.write(bArr, 0, bArr.length);
        if (iF > 0) {
            long j10 = jVar.f22759l;
            mVar.u(jVar, mVar.f());
            xb.g gVar = this.f22329u;
            jVar.R(gVar);
            gVar.j(j10);
            ac.b.Y(gVar, bArr);
            gVar.close();
        }
        this.f22319i.flush();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(xb.m r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: vb.k.j(xb.m):void");
    }
}
