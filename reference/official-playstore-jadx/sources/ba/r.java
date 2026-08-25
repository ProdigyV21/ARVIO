package ba;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v9.h f7306i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f7307l;

    public r(int i10, v9.h hVar) {
        this.f7306i = hVar;
        this.f7307l = i10;
    }

    @Override // ba.a
    public final int a() {
        return 1;
    }

    @Override // ba.a
    public final void b(int i10, v9.h hVar) {
        throw new IllegalStateException();
    }

    @Override // ba.a
    public final Object get(int i10) {
        if (i10 == this.f7307l) {
            return this.f7306i;
        }
        return null;
    }

    @Override // ba.a, java.lang.Iterable
    public final Iterator iterator() {
        return new q(this, 0);
    }
}
