package coil.network;

import android.content.Utils;
import f4.f;
import gb.a0;
import gb.d0;
import gb.h;
import gb.p0;
import gb.z;
import kotlin.Metadata;
import x6.s;
import xb.k;
import xb.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rR\u001b\u0010\u0013\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b!\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcoil/network/CacheResponse;", "", "Lxb/l;", "source", "<init>", "(Lxb/l;)V", "Lgb/p0;", "response", "(Lgb/p0;)V", "Lxb/k;", "sink", "Lx6/t0;", "writeTo", "(Lxb/k;)V", "Lgb/h;", "cacheControl$delegate", "Lx6/s;", "getCacheControl", "()Lgb/h;", "cacheControl", "Lgb/d0;", "contentType$delegate", "getContentType", "()Lgb/d0;", "contentType", "", "sentRequestAtMillis", "J", "getSentRequestAtMillis", "()J", "receivedResponseAtMillis", "getReceivedResponseAtMillis", "", "isTls", "Z", "()Z", "Lgb/a0;", "responseHeaders", "Lgb/a0;", "getResponseHeaders", "()Lgb/a0;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CacheResponse {

    /* JADX INFO: renamed from: cacheControl$delegate, reason: from kotlin metadata */
    private final s cacheControl = f.o(3, new CacheResponse$cacheControl$2(this));

    /* JADX INFO: renamed from: contentType$delegate, reason: from kotlin metadata */
    private final s contentType = f.o(3, new CacheResponse$contentType$2(this));
    private final boolean isTls;
    private final long receivedResponseAtMillis;
    private final a0 responseHeaders;
    private final long sentRequestAtMillis;

    public CacheResponse(l lVar) {
        this.sentRequestAtMillis = Long.parseLong(lVar.Q());
        this.receivedResponseAtMillis = Long.parseLong(lVar.Q());
        this.isTls = Integer.parseInt(lVar.Q()) > 0;
        int i10 = Integer.parseInt(lVar.Q());
        z zVar = new z();
        for (int i11 = 0; i11 < i10; i11++) {
            Utils.addUnsafeNonAscii(zVar, lVar.Q());
        }
        this.responseHeaders = zVar.e();
    }

    public final h getCacheControl() {
        return (h) this.cacheControl.getValue();
    }

    public final d0 getContentType() {
        return (d0) this.contentType.getValue();
    }

    public final long getReceivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public final a0 getResponseHeaders() {
        return this.responseHeaders;
    }

    public final long getSentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    /* JADX INFO: renamed from: isTls, reason: from getter */
    public final boolean getIsTls() {
        return this.isTls;
    }

    public final void writeTo(k sink) {
        sink.Y(this.sentRequestAtMillis).writeByte(10);
        sink.Y(this.receivedResponseAtMillis).writeByte(10);
        sink.Y(this.isTls ? 1L : 0L).writeByte(10);
        sink.Y(this.responseHeaders.size()).writeByte(10);
        int size = this.responseHeaders.size();
        for (int i10 = 0; i10 < size; i10++) {
            sink.H(this.responseHeaders.b(i10)).H(": ").H(this.responseHeaders.e(i10)).writeByte(10);
        }
    }

    public CacheResponse(p0 p0Var) {
        this.sentRequestAtMillis = p0Var.f15790u;
        this.receivedResponseAtMillis = p0Var.f15791v;
        this.isTls = p0Var.f15784o != null;
        this.responseHeaders = p0Var.f15785p;
    }
}
