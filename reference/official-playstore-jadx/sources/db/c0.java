package db;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f14882a = new LinkedHashMap();

    public final b0 a() {
        return new b0(this.f14882a);
    }

    public final m b(m mVar, String str) {
        return (m) this.f14882a.put(str, mVar);
    }
}
