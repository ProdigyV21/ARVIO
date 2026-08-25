package j$.util.stream;

import j$.util.Spliterator;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class u6 extends c implements Iterable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f18909e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object[] f18910f;

    public abstract Object newArray(int i10);

    public abstract void p(Object obj, int i10, int i11, Object obj2);

    public abstract int q(Object obj);

    public abstract Spliterator spliterator();

    @Override // java.lang.Iterable
    public final /* synthetic */ java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    public abstract Object[] t();

    public u6(int i10) {
        super(i10);
        this.f18909e = newArray(1 << this.f18645a);
    }

    public u6() {
        this.f18909e = newArray(16);
    }

    public final void s(long j10) {
        long jQ;
        int i10 = this.f18647c;
        if (i10 == 0) {
            jQ = q(this.f18909e);
        } else {
            jQ = ((long) q(this.f18910f[i10])) + this.f18648d[i10];
        }
        if (j10 > jQ) {
            if (this.f18910f == null) {
                Object[] objArrT = t();
                this.f18910f = objArrT;
                this.f18648d = new long[8];
                objArrT[0] = this.f18909e;
            }
            int i11 = this.f18647c + 1;
            while (j10 > jQ) {
                Object[] objArr = this.f18910f;
                if (i11 >= objArr.length) {
                    int length = objArr.length * 2;
                    this.f18910f = Arrays.copyOf(objArr, length);
                    this.f18648d = Arrays.copyOf(this.f18648d, length);
                }
                int iMin = this.f18645a;
                if (i11 != 0 && i11 != 1) {
                    iMin = Math.min((iMin + i11) - 1, 30);
                }
                int i12 = 1 << iMin;
                this.f18910f[i11] = newArray(i12);
                long[] jArr = this.f18648d;
                int i13 = i11 - 1;
                jArr[i11] = jArr[i13] + ((long) q(this.f18910f[i13]));
                jQ += (long) i12;
                i11++;
            }
        }
    }

    public final int r(long j10) {
        if (this.f18647c == 0) {
            if (j10 < this.f18646b) {
                return 0;
            }
            throw new IndexOutOfBoundsException(Long.toString(j10));
        }
        if (j10 >= count()) {
            throw new IndexOutOfBoundsException(Long.toString(j10));
        }
        for (int i10 = 0; i10 <= this.f18647c; i10++) {
            if (j10 < this.f18648d[i10] + ((long) q(this.f18910f[i10]))) {
                return i10;
            }
        }
        throw new IndexOutOfBoundsException(Long.toString(j10));
    }

    public void e(Object obj, int i10) {
        long j10 = i10;
        long jCount = count() + j10;
        if (jCount > q(obj) || jCount < j10) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.f18647c == 0) {
            System.arraycopy(this.f18909e, 0, obj, i10, this.f18646b);
            return;
        }
        for (int i11 = 0; i11 < this.f18647c; i11++) {
            Object obj2 = this.f18910f[i11];
            System.arraycopy(obj2, 0, obj, i10, q(obj2));
            i10 += q(this.f18910f[i11]);
        }
        int i12 = this.f18646b;
        if (i12 > 0) {
            System.arraycopy(this.f18909e, 0, obj, i10, i12);
        }
    }

    public Object b() {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object objNewArray = newArray((int) jCount);
        e(objNewArray, 0);
        return objNewArray;
    }

    public final void u() {
        long jQ;
        if (this.f18646b == q(this.f18909e)) {
            if (this.f18910f == null) {
                Object[] objArrT = t();
                this.f18910f = objArrT;
                this.f18648d = new long[8];
                objArrT[0] = this.f18909e;
            }
            int i10 = this.f18647c;
            int i11 = i10 + 1;
            Object[] objArr = this.f18910f;
            if (i11 >= objArr.length || objArr[i11] == null) {
                if (i10 == 0) {
                    jQ = q(this.f18909e);
                } else {
                    jQ = ((long) q(objArr[i10])) + this.f18648d[i10];
                }
                s(jQ + 1);
            }
            this.f18646b = 0;
            int i12 = this.f18647c + 1;
            this.f18647c = i12;
            this.f18909e = this.f18910f[i12];
        }
    }

    @Override // j$.util.stream.c
    public final void clear() {
        Object[] objArr = this.f18910f;
        if (objArr != null) {
            this.f18909e = objArr[0];
            this.f18910f = null;
            this.f18648d = null;
        }
        this.f18646b = 0;
        this.f18647c = 0;
    }

    public void g(Object obj) {
        for (int i10 = 0; i10 < this.f18647c; i10++) {
            Object obj2 = this.f18910f[i10];
            p(obj2, 0, q(obj2), obj);
        }
        p(this.f18909e, 0, this.f18646b, obj);
    }
}
