package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f932b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public kotlin.jvm.internal.m f933c;

    public d0(boolean z) {
        this.f931a = z;
    }

    public void a() {
    }

    public abstract void b();

    public void c(c cVar) {
    }

    public final void e() {
        Iterator it = this.f932b.iterator();
        while (it.hasNext()) {
            ((d) it.next()).cancel();
        }
    }

    public void d() {
    }
}
