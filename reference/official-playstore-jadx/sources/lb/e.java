package lb;

import gb.t0;
import gb.u;
import java.io.IOException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f20095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final gb.a f20096b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f20097c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f20098d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public androidx.core.provider.j f20099e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public androidx.tv.foundation.lazy.list.k f20100f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20101g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20102h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20103i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public t0 f20104j;

    public e(io.sentry.android.core.internal.util.g gVar, gb.a aVar, i iVar, u uVar) {
        this.f20095a = gVar;
        this.f20096b = aVar;
        this.f20097c = iVar;
        this.f20098d = uVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final lb.k a(int r14, int r15, int r16, boolean r17, int r18, boolean r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: lb.e.a(int, int, int, boolean, int, boolean):lb.k");
    }

    public final void b(IOException iOException) {
        this.f20104j = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).f20989i == 8) {
            this.f20101g++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.f20102h++;
        } else {
            this.f20103i++;
        }
    }
}
