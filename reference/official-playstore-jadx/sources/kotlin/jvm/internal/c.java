package kotlin.jvm.internal;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends kotlin.collections.f0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long[] f19735i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19736l;

    public c(long[] jArr) {
        this.f19735i = jArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f19736l < this.f19735i.length;
    }

    @Override // kotlin.collections.f0
    public final long nextLong() {
        try {
            long[] jArr = this.f19735i;
            int i10 = this.f19736l;
            this.f19736l = i10 + 1;
            return jArr[i10];
        } catch (ArrayIndexOutOfBoundsException e5) {
            this.f19736l--;
            throw new NoSuchElementException(e5.getMessage());
        }
    }
}
