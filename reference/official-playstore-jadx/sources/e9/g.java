package e9;

import ab.k;
import d9.m;
import d9.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.collections.d0;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements c9.f {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final List f15076n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String[] f15077i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Set f15078l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f15079m;

    static {
        String strU0 = x.u0(t7.a.E('k', 'o', 't', 'l', 'i', 'n'), "", null, null, null, 62);
        List listE = t7.a.E(androidx.compose.material3.d.m(strU0, "/Any"), androidx.compose.material3.d.m(strU0, "/Nothing"), androidx.compose.material3.d.m(strU0, "/Unit"), androidx.compose.material3.d.m(strU0, "/Throwable"), androidx.compose.material3.d.m(strU0, "/Number"), androidx.compose.material3.d.m(strU0, "/Byte"), androidx.compose.material3.d.m(strU0, "/Double"), androidx.compose.material3.d.m(strU0, "/Float"), androidx.compose.material3.d.m(strU0, "/Int"), androidx.compose.material3.d.m(strU0, "/Long"), androidx.compose.material3.d.m(strU0, "/Short"), androidx.compose.material3.d.m(strU0, "/Boolean"), androidx.compose.material3.d.m(strU0, "/Char"), androidx.compose.material3.d.m(strU0, "/CharSequence"), androidx.compose.material3.d.m(strU0, "/String"), androidx.compose.material3.d.m(strU0, "/Comparable"), androidx.compose.material3.d.m(strU0, "/Enum"), androidx.compose.material3.d.m(strU0, "/Array"), androidx.compose.material3.d.m(strU0, "/ByteArray"), androidx.compose.material3.d.m(strU0, "/DoubleArray"), androidx.compose.material3.d.m(strU0, "/FloatArray"), androidx.compose.material3.d.m(strU0, "/IntArray"), androidx.compose.material3.d.m(strU0, "/LongArray"), androidx.compose.material3.d.m(strU0, "/ShortArray"), androidx.compose.material3.d.m(strU0, "/BooleanArray"), androidx.compose.material3.d.m(strU0, "/CharArray"), androidx.compose.material3.d.m(strU0, "/Cloneable"), androidx.compose.material3.d.m(strU0, "/Annotation"), androidx.compose.material3.d.m(strU0, "/collections/Iterable"), androidx.compose.material3.d.m(strU0, "/collections/MutableIterable"), androidx.compose.material3.d.m(strU0, "/collections/Collection"), androidx.compose.material3.d.m(strU0, "/collections/MutableCollection"), androidx.compose.material3.d.m(strU0, "/collections/List"), androidx.compose.material3.d.m(strU0, "/collections/MutableList"), androidx.compose.material3.d.m(strU0, "/collections/Set"), androidx.compose.material3.d.m(strU0, "/collections/MutableSet"), androidx.compose.material3.d.m(strU0, "/collections/Map"), androidx.compose.material3.d.m(strU0, "/collections/MutableMap"), androidx.compose.material3.d.m(strU0, "/collections/Map.Entry"), androidx.compose.material3.d.m(strU0, "/collections/MutableMap.MutableEntry"), androidx.compose.material3.d.m(strU0, "/collections/Iterator"), androidx.compose.material3.d.m(strU0, "/collections/MutableIterator"), androidx.compose.material3.d.m(strU0, "/collections/ListIterator"), androidx.compose.material3.d.m(strU0, "/collections/MutableListIterator"));
        f15076n = listE;
        k kVarH1 = x.h1(listE);
        int iQ0 = i0.q0(s.U(kVarH1, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        Iterator it = kVarH1.iterator();
        while (true) {
            ga.d dVar = (ga.d) it;
            if (!dVar.f15570m.hasNext()) {
                return;
            }
            d0 d0Var = (d0) dVar.next();
            linkedHashMap.put((String) d0Var.f19689b, Integer.valueOf(d0Var.f19688a));
        }
    }

    public g(o oVar, String[] strArr) {
        List list = oVar.f14859m;
        Set setG1 = list.isEmpty() ? b0.f19686i : x.g1(list);
        List<m> list2 = oVar.f14858l;
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(list2.size());
        for (m mVar : list2) {
            int i10 = mVar.f14845m;
            for (int i11 = 0; i11 < i10; i11++) {
                arrayList.add(mVar);
            }
        }
        arrayList.trimToSize();
        this.f15077i = strArr;
        this.f15078l = setG1;
        this.f15079m = arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    @Override // c9.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getString(int r9) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: e9.g.getString(int):java.lang.String");
    }

    @Override // c9.f
    public final boolean k(int i10) {
        return this.f15078l.contains(Integer.valueOf(i10));
    }

    @Override // c9.f
    public final String o(int i10) {
        return getString(i10);
    }
}
