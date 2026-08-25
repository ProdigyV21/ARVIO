package x7;

import java.util.NoSuchElementException;
import kotlin.collections.f0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends f0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f22630i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f22631l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22632m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f22633n;

    public k(long j10, long j11, long j12) {
        this.f22630i = j12;
        this.f22631l = j11;
        boolean z = false;
        if (j12 <= 0 ? j10 >= j11 : j10 <= j11) {
            z = true;
        }
        this.f22632m = z;
        this.f22633n = z ? j10 : j11;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22632m;
    }

    @Override // kotlin.collections.f0
    public final long nextLong() {
        long j10 = this.f22633n;
        if (j10 != this.f22631l) {
            this.f22633n = this.f22630i + j10;
            return j10;
        }
        if (!this.f22632m) {
            throw new NoSuchElementException();
        }
        this.f22632m = false;
        return j10;
    }
}
