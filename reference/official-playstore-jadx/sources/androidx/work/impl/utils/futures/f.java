package androidx.work.impl.utils.futures;

import com.google.common.util.concurrent.d1;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k f7028i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d1 f7029l;

    public f(k kVar, d1 d1Var) {
        this.f7028i = kVar;
        this.f7029l = d1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f7028i.f7037i != this) {
            return;
        }
        if (i.f7035p.e(this.f7028i, this, i.e(this.f7029l))) {
            i.b(this.f7028i);
        }
    }
}
