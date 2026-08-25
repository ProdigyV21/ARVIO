package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public class v6 extends c implements Consumer, Iterable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f18930e = new Object[1 << 4];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object[][] f18931f;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    public final void p(long j10) {
        long length;
        int i10 = this.f18647c;
        if (i10 == 0) {
            length = this.f18930e.length;
        } else {
            length = this.f18648d[i10] + ((long) this.f18931f[i10].length);
        }
        if (j10 > length) {
            if (this.f18931f == null) {
                Object[][] objArr = new Object[8][];
                this.f18931f = objArr;
                this.f18648d = new long[8];
                objArr[0] = this.f18930e;
            }
            int i11 = i10 + 1;
            while (j10 > length) {
                Object[][] objArr2 = this.f18931f;
                if (i11 >= objArr2.length) {
                    int length2 = objArr2.length * 2;
                    this.f18931f = (Object[][]) Arrays.copyOf(objArr2, length2);
                    this.f18648d = Arrays.copyOf(this.f18648d, length2);
                }
                int iMin = this.f18645a;
                if (i11 != 0 && i11 != 1) {
                    iMin = Math.min((iMin + i11) - 1, 30);
                }
                int i12 = 1 << iMin;
                Object[][] objArr3 = this.f18931f;
                objArr3[i11] = new Object[i12];
                long[] jArr = this.f18648d;
                int i13 = i11 - 1;
                jArr[i11] = jArr[i13] + ((long) objArr3[i13].length);
                length += (long) i12;
                i11++;
            }
        }
    }

    @Override // j$.util.stream.c
    public final void clear() {
        Object[][] objArr = this.f18931f;
        if (objArr != null) {
            this.f18930e = objArr[0];
            int i10 = 0;
            while (true) {
                Object[] objArr2 = this.f18930e;
                if (i10 >= objArr2.length) {
                    break;
                }
                objArr2[i10] = null;
                i10++;
            }
            this.f18931f = null;
            this.f18648d = null;
        } else {
            for (int i11 = 0; i11 < this.f18646b; i11++) {
                this.f18930e[i11] = null;
            }
        }
        this.f18646b = 0;
        this.f18647c = 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        j$.util.Spliterator spliterator = spliterator();
        Objects.requireNonNull(spliterator);
        return new j$.util.p0(spliterator);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer consumer) {
        for (int i10 = 0; i10 < this.f18647c; i10++) {
            for (Object obj : this.f18931f[i10]) {
                consumer.n(obj);
            }
        }
        for (int i11 = 0; i11 < this.f18646b; i11++) {
            consumer.n(this.f18930e[i11]);
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public void n(Object obj) {
        long length;
        int i10 = this.f18646b;
        Object[] objArr = this.f18930e;
        if (i10 == objArr.length) {
            if (this.f18931f == null) {
                Object[][] objArr2 = new Object[8][];
                this.f18931f = objArr2;
                this.f18648d = new long[8];
                objArr2[0] = objArr;
            }
            int i11 = this.f18647c;
            int i12 = i11 + 1;
            Object[][] objArr3 = this.f18931f;
            if (i12 >= objArr3.length || objArr3[i12] == null) {
                if (i11 == 0) {
                    length = objArr.length;
                } else {
                    length = ((long) objArr3[i11].length) + this.f18648d[i11];
                }
                p(length + 1);
            }
            this.f18646b = 0;
            int i13 = this.f18647c + 1;
            this.f18647c = i13;
            this.f18930e = this.f18931f[i13];
        }
        Object[] objArr4 = this.f18930e;
        int i14 = this.f18646b;
        this.f18646b = i14 + 1;
        objArr4[i14] = obj;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(arrayList);
        forEach(new j$.util.function.i(arrayList, 7));
        return "SpinedBuffer:" + arrayList.toString();
    }

    @Override // java.lang.Iterable
    public j$.util.Spliterator spliterator() {
        return new m6(this, 0, this.f18647c, 0, this.f18646b);
    }
}
