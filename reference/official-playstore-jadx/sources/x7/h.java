package x7;

import java.util.NoSuchElementException;
import kotlin.collections.e0;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22622i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22623l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22625n;

    public h(int i10, int i11, int i12) {
        this.f22622i = i12;
        this.f22623l = i11;
        boolean z = false;
        if (i12 <= 0 ? i10 >= i11 : i10 <= i11) {
            z = true;
        }
        this.f22624m = z;
        this.f22625n = z ? i10 : i11;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22624m;
    }

    @Override // kotlin.collections.e0
    public final int nextInt() {
        int i10 = this.f22625n;
        if (i10 != this.f22623l) {
            this.f22625n = this.f22622i + i10;
            return i10;
        }
        if (!this.f22624m) {
            throw new NoSuchElementException();
        }
        this.f22624m = false;
        return i10;
    }
}
