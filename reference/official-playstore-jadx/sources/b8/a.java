package b8;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f7212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f7213b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7214c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f7215d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f7216e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f7217f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f7218g;

    public a(Class cls, ArrayList arrayList, int i10, int i11, List list) {
        this.f7212a = cls;
        this.f7213b = arrayList;
        this.f7214c = i10;
        this.f7215d = list;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Method) it.next()).getGenericReturnType());
        }
        this.f7216e = arrayList2;
        List list2 = this.f7215d;
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            Class<?> returnType = ((Method) it2.next()).getReturnType();
            Class<?> cls2 = (Class) m8.c.f20268c.get(returnType);
            if (cls2 != null) {
                returnType = cls2;
            }
            arrayList3.add(returnType);
        }
        this.f7217f = arrayList3;
        List list3 = this.f7215d;
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(list3, 10));
        Iterator it3 = list3.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((Method) it3.next()).getDefaultValue());
        }
        this.f7218g = arrayList4;
        if (this.f7214c == 2 && i11 == 1 && !kotlin.collections.x.E0("value", this.f7213b).isEmpty()) {
            throw new UnsupportedOperationException("Positional call of a Java annotation constructor is allowed only if there are no parameters or one parameter named \"value\". This restriction exists because Java annotations (in contrast to Kotlin)do not impose any order on their arguments. Use KCallable#callBy instead.");
        }
    }

    @Override // b8.e
    public final List a() {
        return this.f7216e;
    }

    @Override // b8.e
    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    @Override // b8.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object call(java.lang.Object[] r18) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b8.a.call(java.lang.Object[]):java.lang.Object");
    }

    @Override // b8.e
    public final Type getReturnType() {
        return this.f7212a;
    }

    public /* synthetic */ a(Class cls, ArrayList arrayList, int i10) {
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(cls.getDeclaredMethod((String) it.next(), null));
        }
        this(cls, arrayList, i10, 2, arrayList2);
    }
}
