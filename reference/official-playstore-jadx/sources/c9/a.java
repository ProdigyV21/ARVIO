package c9;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.q;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f7424a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7425b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7426c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7427d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f7428e;

    public a(int... iArr) {
        List listC1;
        this.f7424a = iArr;
        Integer numB0 = r.b0(iArr, 0);
        this.f7425b = numB0 != null ? numB0.intValue() : -1;
        Integer numB02 = r.b0(iArr, 1);
        this.f7426c = numB02 != null ? numB02.intValue() : -1;
        Integer numB03 = r.b0(iArr, 2);
        this.f7427d = numB03 != null ? numB03.intValue() : -1;
        if (iArr.length <= 3) {
            listC1 = z.f19728i;
        } else {
            if (iArr.length > 1024) {
                throw new IllegalArgumentException(a0.c.o(new StringBuilder("BinaryVersion with length more than 1024 are not supported. Provided length "), iArr.length, '.'));
            }
            listC1 = x.c1(new kotlin.collections.e(new q(iArr), 3, iArr.length));
        }
        this.f7428e = listC1;
    }

    public final boolean a(int i10, int i11, int i12) {
        int i13 = this.f7425b;
        if (i13 > i10) {
            return true;
        }
        if (i13 < i10) {
            return false;
        }
        int i14 = this.f7426c;
        if (i14 > i11) {
            return true;
        }
        return i14 >= i11 && this.f7427d >= i12;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        a aVar = (a) obj;
        return this.f7425b == aVar.f7425b && this.f7426c == aVar.f7426c && this.f7427d == aVar.f7427d && p.a(this.f7428e, aVar.f7428e);
    }

    public final int hashCode() {
        int i10 = this.f7425b;
        int i11 = (i10 * 31) + this.f7426c + i10;
        int i12 = (i11 * 31) + this.f7427d + i11;
        return this.f7428e.hashCode() + (i12 * 31) + i12;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        for (int i10 : this.f7424a) {
            if (i10 == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i10));
        }
        return arrayList.isEmpty() ? "unknown" : x.u0(arrayList, ".", null, null, null, 62);
    }
}
