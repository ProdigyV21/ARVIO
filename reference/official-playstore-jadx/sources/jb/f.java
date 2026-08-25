package jb;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import xb.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f19429i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f19430l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f19431m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f19432n;

    public f(h hVar, String str, long j10, ArrayList arrayList) {
        this.f19432n = hVar;
        this.f19429i = str;
        this.f19430l = j10;
        this.f19431m = arrayList;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator it = this.f19431m.iterator();
        while (it.hasNext()) {
            ib.c.d((o0) it.next());
        }
    }
}
