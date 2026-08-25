package io.sentry.rrweb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f17800i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f17801l = System.currentTimeMillis();

    public b(c cVar) {
        this.f17800i = cVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f17801l == bVar.f17801l && this.f17800i == bVar.f17800i;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17800i, Long.valueOf(this.f17801l)});
    }
}
