package ob;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f20916e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f20917f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f20918g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(String str, long j10, int i10, Object obj) {
        super(str, true);
        this.f20916e = i10;
        this.f20918g = obj;
        this.f20917f = j10;
    }

    private final long b() {
        o oVar;
        boolean z;
        synchronized (((o) this.f20918g)) {
            oVar = (o) this.f20918g;
            long j10 = oVar.f20934w;
            long j11 = oVar.f20933v;
            if (j10 < j11) {
                z = true;
            } else {
                oVar.f20933v = j11 + 1;
                z = false;
            }
        }
        if (z) {
            oVar.i(2, 2, null);
            return -1L;
        }
        try {
            oVar.H.x(1, 0, false);
        } catch (IOException e5) {
            oVar.i(2, 2, e5);
        }
        return this.f20917f;
    }

    @Override // kb.a
    public final long a() {
        vb.k kVar;
        switch (this.f20916e) {
            case 0:
                return b();
            default:
                vb.g gVar = (vb.g) this.f20918g;
                synchronized (gVar) {
                    try {
                        if (!gVar.f22297t && (kVar = gVar.f22288j) != null) {
                            int i10 = gVar.f22299v ? gVar.f22298u : -1;
                            gVar.f22298u++;
                            gVar.f22299v = true;
                            if (i10 != -1) {
                                StringBuilder sb2 = new StringBuilder("sent ping but didn't receive pong within ");
                                sb2.append(gVar.f22281c);
                                sb2.append("ms (after ");
                                gVar.i(new SocketTimeoutException(androidx.compose.material3.d.j(i10 - 1, " successful ping/pongs)", sb2)), null);
                            } else {
                                try {
                                    kVar.i(9, xb.m.f22772n);
                                } catch (IOException e5) {
                                    gVar.i(e5, null);
                                }
                            }
                            break;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return this.f20917f;
        }
    }
}
