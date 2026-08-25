package io.sentry.android.core.performance;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f16701i = new h();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final h f16702l = new h();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        c cVar = (c) obj;
        int iCompare = Long.compare(this.f16701i.f16722m, cVar.f16701i.f16722m);
        return iCompare == 0 ? Long.compare(this.f16702l.f16722m, cVar.f16702l.f16722m) : iCompare;
    }
}
