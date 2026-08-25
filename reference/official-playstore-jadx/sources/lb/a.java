package lb;

import com.google.android.gms.common.api.internal.g0;
import gb.c0;
import gb.h0;
import gb.p0;
import java.io.IOException;
import kotlin.jvm.internal.p;
import okhttp3.internal.connection.RouteException;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f20079a = new a();

    @Override // gb.c0
    public final p0 intercept(c0.a aVar) throws IOException {
        mb.f fVar = (mb.f) aVar;
        i iVar = fVar.f20362a;
        synchronized (iVar) {
            try {
                if (!iVar.f20123y) {
                    throw new IllegalStateException("released");
                }
                if (iVar.x) {
                    throw new IllegalStateException("Check failed.");
                }
                if (iVar.f20122w) {
                    throw new IllegalStateException("Check failed.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        e eVar = iVar.f20118s;
        h0 h0Var = iVar.f20110i;
        try {
            g0 g0Var = new g0(iVar, iVar.f20114o, eVar, eVar.a(fVar.f20367f, fVar.f20368g, fVar.f20369h, h0Var.f15705p, h0Var.K, !p.a(fVar.f20366e.f15731b, "GET")).k(h0Var, fVar));
            iVar.f20121v = g0Var;
            iVar.A = g0Var;
            synchronized (iVar) {
                iVar.f20122w = true;
                iVar.x = true;
            }
            if (iVar.z) {
                throw new IOException("Canceled");
            }
            return mb.f.b(fVar, 0, g0Var, null, 61).a(fVar.f20366e);
        } catch (IOException e5) {
            eVar.b(e5);
            throw new RouteException(e5);
        } catch (RouteException e6) {
            eVar.b(e6.f20988l);
            throw e6;
        }
    }
}
