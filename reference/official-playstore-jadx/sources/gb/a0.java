package gb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String[] f15615i;

    public a0(String[] strArr) {
        this.f15615i = strArr;
    }

    public final String a(String str) {
        String[] strArr = this.f15615i;
        int length = strArr.length - 2;
        int iA = i7.n.a(length, 0, -2);
        if (iA > length) {
            return null;
        }
        while (!kotlin.text.u.L(str, strArr[length], true)) {
            if (length == iA) {
                return null;
            }
            length -= 2;
        }
        return strArr[length + 1];
    }

    public final String b(int i10) {
        return this.f15615i[i10 * 2];
    }

    public final z c() {
        z zVar = new z();
        kotlin.collections.x.c0(zVar.f15836a, this.f15615i);
        return zVar;
    }

    public final TreeMap d() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            String lowerCase = b(i10).toLowerCase(Locale.US);
            List arrayList = (List) treeMap.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                treeMap.put(lowerCase, arrayList);
            }
            arrayList.add(e(i10));
        }
        return treeMap;
    }

    public final String e(int i10) {
        return this.f15615i[(i10 * 2) + 1];
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a0) {
            return Arrays.equals(this.f15615i, ((a0) obj).f15615i);
        }
        return false;
    }

    public final List f(String str) {
        int size = size();
        ArrayList arrayList = null;
        for (int i10 = 0; i10 < size; i10++) {
            if (str.equalsIgnoreCase(b(i10))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(e(i10));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : kotlin.collections.z.f19728i;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f15615i);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int size = size();
        x6.x[] xVarArr = new x6.x[size];
        for (int i10 = 0; i10 < size; i10++) {
            xVarArr[i10] = new x6.x(b(i10), e(i10));
        }
        return new ab.j(xVarArr, 4);
    }

    public final int size() {
        return this.f15615i.length / 2;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            String strB = b(i10);
            String strE = e(i10);
            sb2.append(strB);
            sb2.append(": ");
            if (ib.c.q(strB)) {
                strE = "██";
            }
            sb2.append(strE);
            sb2.append("\n");
        }
        return sb2.toString();
    }
}
