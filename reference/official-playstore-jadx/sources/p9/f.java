package p9;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.x;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f21203c = new l();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f21204d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f21205e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f21206f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f21207g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f21208h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f21209i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f21210j;
    public static final int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f21211l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f21212m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f21213n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final f f21214o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f f21215p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f f21216q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final ArrayList f21217r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ArrayList f21218s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f21219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f21220b;

    static {
        int i10 = f21204d;
        int i11 = i10 << 1;
        f21205e = i10;
        int i12 = i10 << 2;
        f21206f = i11;
        int i13 = i10 << 3;
        f21207g = i12;
        int i14 = i10 << 4;
        f21208h = i13;
        int i15 = i10 << 5;
        f21209i = i14;
        f21210j = i15;
        f21204d = i10 << 7;
        int i16 = (i10 << 6) - 1;
        k = i16;
        int i17 = i10 | i11 | i12;
        f21211l = i17;
        f21212m = new f(i16);
        f21213n = new f(i14 | i15);
        new f(i10);
        new f(i11);
        new f(i12);
        f21214o = new f(i17);
        new f(i13);
        f21215p = new f(i14);
        f21216q = new f(i15);
        new f(i11 | i14 | i15);
        Field[] fields = f.class.getFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Field field2 = (Field) it.next();
            Object obj = field2.get(null);
            f fVar = obj instanceof f ? (f) obj : null;
            e eVar = fVar != null ? new e(fVar.f21220b, field2.getName()) : null;
            if (eVar != null) {
                arrayList2.add(eVar);
            }
        }
        f21217r = arrayList2;
        Field[] fields2 = f.class.getFields();
        ArrayList arrayList3 = new ArrayList();
        for (Field field3 : fields2) {
            if (Modifier.isStatic(field3.getModifiers())) {
                arrayList3.add(field3);
            }
        }
        ArrayList<Field> arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            if (kotlin.jvm.internal.p.a(((Field) obj2).getType(), Integer.TYPE)) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Field field4 : arrayList4) {
            int iIntValue = ((Integer) field4.get(null)).intValue();
            e eVar2 = iIntValue == ((-iIntValue) & iIntValue) ? new e(iIntValue, field4.getName()) : null;
            if (eVar2 != null) {
                arrayList5.add(eVar2);
            }
        }
        f21218s = arrayList5;
    }

    public f(int i10, List list) {
        this.f21219a = list;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            i10 &= ~((d) it.next()).a();
        }
        this.f21220b = i10;
    }

    public final boolean a(int i10) {
        return (i10 & this.f21220b) != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!f.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        f fVar = (f) obj;
        return kotlin.jvm.internal.p.a(this.f21219a, fVar.f21219a) && this.f21220b == fVar.f21220b;
    }

    public final int hashCode() {
        return (this.f21219a.hashCode() * 31) + this.f21220b;
    }

    public final String toString() {
        Object next;
        Iterator it = f21217r.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((e) next).f21201a == this.f21220b) {
                break;
            }
        }
        e eVar = (e) next;
        String strU0 = eVar != null ? eVar.f21202b : null;
        if (strU0 == null) {
            ArrayList arrayList = new ArrayList();
            for (e eVar2 : f21218s) {
                String str = a(eVar2.f21201a) ? eVar2.f21202b : null;
                if (str != null) {
                    arrayList.add(str);
                }
            }
            strU0 = x.u0(arrayList, " | ", null, null, null, 62);
        }
        StringBuilder sbT = a0.c.t("DescriptorKindFilter(", strU0, ", ");
        sbT.append(this.f21219a);
        sbT.append(')');
        return sbT.toString();
    }

    public /* synthetic */ f(int i10) {
        this(i10, z.f19728i);
    }
}
