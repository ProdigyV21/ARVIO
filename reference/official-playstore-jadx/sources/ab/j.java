package ab;

import cb.z;
import java.util.Iterator;
import java.util.NoSuchElementException;
import t.m0;
import x6.k0;
import x6.o0;
import x6.r0;

/* JADX INFO: loaded from: classes5.dex */
public class j implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f811i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f812l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f813m;

    public /* synthetic */ j(Object obj, int i10) {
        this.f811i = i10;
        this.f813m = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f811i) {
            case 0:
                if (this.f812l > 0) {
                }
                break;
            case 1:
                if (this.f812l < ((kotlin.collections.f) this.f813m).size()) {
                }
                break;
            case 2:
                if (this.f812l < ((double[]) this.f813m).length) {
                }
                break;
            case 3:
                if (this.f812l < ((float[]) this.f813m).length) {
                }
                break;
            case 4:
                if (this.f812l < ((Object[]) this.f813m).length) {
                }
                break;
            case 5:
                if (this.f812l < ((m0) this.f813m).f()) {
                }
                break;
            case 6:
                if (this.f812l < ((byte[]) this.f813m).length) {
                }
                break;
            case 7:
                if (this.f812l < ((int[]) this.f813m).length) {
                }
                break;
            case 8:
                if (this.f812l < ((long[]) this.f813m).length) {
                }
                break;
            default:
                if (this.f812l < ((short[]) this.f813m).length) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f811i) {
            case 0:
                z zVar = (z) this.f813m;
                int i10 = zVar.f7479c;
                int i11 = this.f812l;
                this.f812l = i11 - 1;
                return zVar.f7481e[i10 - i11];
            case 1:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                kotlin.collections.f fVar = (kotlin.collections.f) this.f813m;
                int i12 = this.f812l;
                this.f812l = i12 + 1;
                return fVar.get(i12);
            case 2:
                try {
                    double[] dArr = (double[]) this.f813m;
                    int i13 = this.f812l;
                    this.f812l = i13 + 1;
                    return Double.valueOf(dArr[i13]);
                } catch (ArrayIndexOutOfBoundsException e5) {
                    this.f812l--;
                    throw new NoSuchElementException(e5.getMessage());
                }
            case 3:
                try {
                    float[] fArr = (float[]) this.f813m;
                    int i14 = this.f812l;
                    this.f812l = i14 + 1;
                    return Float.valueOf(fArr[i14]);
                } catch (ArrayIndexOutOfBoundsException e6) {
                    this.f812l--;
                    throw new NoSuchElementException(e6.getMessage());
                }
            case 4:
                try {
                    Object[] objArr = (Object[]) this.f813m;
                    int i15 = this.f812l;
                    this.f812l = i15 + 1;
                    return objArr[i15];
                } catch (ArrayIndexOutOfBoundsException e10) {
                    this.f812l--;
                    throw new NoSuchElementException(e10.getMessage());
                }
            case 5:
                m0 m0Var = (m0) this.f813m;
                int i16 = this.f812l;
                this.f812l = i16 + 1;
                return m0Var.g(i16);
            case 6:
                int i17 = this.f812l;
                byte[] bArr = (byte[]) this.f813m;
                if (i17 >= bArr.length) {
                    throw new NoSuchElementException(String.valueOf(this.f812l));
                }
                this.f812l = i17 + 1;
                return new k0(bArr[i17]);
            case 7:
                int i18 = this.f812l;
                int[] iArr = (int[]) this.f813m;
                if (i18 >= iArr.length) {
                    throw new NoSuchElementException(String.valueOf(this.f812l));
                }
                this.f812l = i18 + 1;
                return new x6.m0(iArr[i18]);
            case 8:
                int i19 = this.f812l;
                long[] jArr = (long[]) this.f813m;
                if (i19 >= jArr.length) {
                    throw new NoSuchElementException(String.valueOf(this.f812l));
                }
                this.f812l = i19 + 1;
                return new o0(jArr[i19]);
            default:
                int i20 = this.f812l;
                short[] sArr = (short[]) this.f813m;
                if (i20 >= sArr.length) {
                    throw new NoSuchElementException(String.valueOf(this.f812l));
                }
                this.f812l = i20 + 1;
                return new r0(sArr[i20]);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f811i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 2:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 3:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 4:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 5:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 6:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 7:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 8:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public j(z zVar) {
        this.f811i = 0;
        this.f813m = zVar;
        this.f812l = zVar.f7479c;
    }
}
