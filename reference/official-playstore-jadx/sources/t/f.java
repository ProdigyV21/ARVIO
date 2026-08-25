package t;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Collection, Set, s7.b, s7.h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f21809i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f21810l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21811m;

    public f(int i10) {
        this.f21809i = u.a.f22008a;
        this.f21810l = u.a.f22010c;
        if (i10 > 0) {
            this.f21809i = new int[i10];
            this.f21810l = new Object[i10];
        }
    }

    public final Object a(int i10) {
        int i11;
        int i12 = this.f21811m;
        Object[] objArr = this.f21810l;
        Object obj = objArr[i10];
        if (i12 <= 1) {
            clear();
            return obj;
        }
        int i13 = i12 - 1;
        int[] iArr = this.f21809i;
        if (iArr.length <= 8 || i12 >= iArr.length / 3) {
            if (i10 < i13) {
                int i14 = i10 + 1;
                kotlin.collections.r.K(i10, i14, i12, iArr, iArr);
                Object[] objArr2 = this.f21810l;
                kotlin.collections.r.N(objArr2, objArr2, i10, i14, i12);
            }
            this.f21810l[i13] = null;
        } else {
            int i15 = i12 > 8 ? i12 + (i12 >> 1) : 8;
            int[] iArr2 = new int[i15];
            this.f21809i = iArr2;
            this.f21810l = new Object[i15];
            if (i10 > 0) {
                kotlin.collections.r.O(0, i10, 6, iArr, iArr2);
                i11 = i10;
                kotlin.collections.r.R(objArr, this.f21810l, 0, 0, i11, 6);
            } else {
                i11 = i10;
            }
            if (i11 < i13) {
                int i16 = i11 + 1;
                kotlin.collections.r.K(i11, i16, i12, iArr, this.f21809i);
                kotlin.collections.r.N(objArr, this.f21810l, i11, i16, i12);
            }
        }
        if (i12 != this.f21811m) {
            throw new ConcurrentModificationException();
        }
        this.f21811m = i13;
        return obj;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i10;
        int iB;
        int i11 = this.f21811m;
        if (obj == null) {
            iB = q.b(this, null, 0);
            i10 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i10 = iHashCode;
            iB = q.b(this, obj, iHashCode);
        }
        if (iB >= 0) {
            return false;
        }
        int i12 = ~iB;
        int[] iArr = this.f21809i;
        if (i11 >= iArr.length) {
            int i13 = 8;
            if (i11 >= 8) {
                i13 = (i11 >> 1) + i11;
            } else if (i11 < 4) {
                i13 = 4;
            }
            Object[] objArr = this.f21810l;
            int[] iArr2 = new int[i13];
            this.f21809i = iArr2;
            this.f21810l = new Object[i13];
            if (i11 != this.f21811m) {
                throw new ConcurrentModificationException();
            }
            if (iArr2.length != 0) {
                kotlin.collections.r.O(0, iArr.length, 6, iArr, iArr2);
                kotlin.collections.r.R(objArr, this.f21810l, 0, 0, objArr.length, 6);
            }
        }
        if (i12 < i11) {
            int[] iArr3 = this.f21809i;
            int i14 = i12 + 1;
            kotlin.collections.r.K(i14, i12, i11, iArr3, iArr3);
            Object[] objArr2 = this.f21810l;
            kotlin.collections.r.N(objArr2, objArr2, i14, i12, i11);
        }
        int i15 = this.f21811m;
        if (i11 == i15) {
            int[] iArr4 = this.f21809i;
            if (i12 < iArr4.length) {
                iArr4[i12] = i10;
                this.f21810l[i12] = obj;
                this.f21811m = i15 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        int size = collection.size() + this.f21811m;
        int i10 = this.f21811m;
        int[] iArr = this.f21809i;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f21810l;
            int[] iArr2 = new int[size];
            this.f21809i = iArr2;
            this.f21810l = new Object[size];
            if (i10 > 0) {
                kotlin.collections.r.O(0, i10, 6, iArr, iArr2);
                kotlin.collections.r.R(objArr, this.f21810l, 0, 0, this.f21811m, 6);
            }
        }
        if (this.f21811m != i10) {
            throw new ConcurrentModificationException();
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this.f21811m != 0) {
            this.f21809i = u.a.f22008a;
            this.f21810l = u.a.f22010c;
            this.f21811m = 0;
        }
        if (this.f21811m != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? q.b(this, null, 0) : q.b(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set) || this.f21811m != ((Set) obj).size()) {
            return false;
        }
        try {
            int i10 = this.f21811m;
            for (int i11 = 0; i11 < i10; i11++) {
                if (!((Set) obj).contains(this.f21810l[i11])) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.f21809i;
        int i10 = this.f21811m;
        int i11 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            i11 += iArr[i12];
        }
        return i11;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f21811m <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new a(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iB = obj == null ? q.b(this, null, 0) : q.b(this, obj, obj.hashCode());
        if (iB < 0) {
            return false;
        }
        a(iB);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i10 = this.f21811m - 1; -1 < i10; i10--) {
            if (!kotlin.collections.x.f0(collection, this.f21810l[i10])) {
                a(i10);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f21811m;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return kotlin.collections.r.S(this.f21810l, 0, this.f21811m);
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb2 = new StringBuilder(this.f21811m * 14);
        sb2.append('{');
        int i10 = this.f21811m;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb2.append(", ");
            }
            Object obj = this.f21810l[i11];
            if (obj != this) {
                sb2.append(obj);
            } else {
                sb2.append("(this Set)");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        int i10 = this.f21811m;
        if (objArr.length < i10) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i10);
        } else if (objArr.length > i10) {
            objArr[i10] = null;
        }
        kotlin.collections.r.N(this.f21810l, objArr, 0, 0, this.f21811m);
        return objArr;
    }
}
