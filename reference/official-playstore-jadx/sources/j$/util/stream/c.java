package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f18647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f18648d;

    public abstract void clear();

    public c() {
        this.f18645a = 4;
    }

    public c(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + i10);
        }
        this.f18645a = Math.max(4, 32 - Integer.numberOfLeadingZeros(i10 - 1));
    }

    public final long count() {
        int i10 = this.f18647c;
        if (i10 == 0) {
            return this.f18646b;
        }
        return this.f18648d[i10] + ((long) this.f18646b);
    }
}
