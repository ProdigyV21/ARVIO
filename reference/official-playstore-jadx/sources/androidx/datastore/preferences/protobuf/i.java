package androidx.datastore.preferences.protobuf;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2446i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2447l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m f2448m;

    public i(m mVar) {
        this.f2448m = mVar;
        this.f2447l = mVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2446i < this.f2447l;
    }

    @Override // java.util.Iterator
    public final Byte next() {
        int i10 = this.f2446i;
        if (i10 >= this.f2447l) {
            throw new NoSuchElementException();
        }
        this.f2446i = i10 + 1;
        return Byte.valueOf(this.f2448m.f2473l[i10]);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
