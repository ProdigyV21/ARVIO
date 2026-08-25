package cb;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class j1 extends t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.reflect.d f7522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f7523c;

    public j1(kotlin.reflect.d dVar, ya.h hVar) {
        super(hVar);
        this.f7522b = dVar;
        this.f7523c = new c(hVar.getDescriptor(), 0);
    }

    @Override // cb.a
    public final Object a() {
        return new ArrayList();
    }

    @Override // cb.a
    public final int b(Object obj) {
        return ((ArrayList) obj).size();
    }

    @Override // cb.a
    public final Iterator c(Object obj) {
        return new ab.j((Object[]) obj, 4);
    }

    @Override // cb.a
    public final int d(Object obj) {
        return ((Object[]) obj).length;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        return new ArrayList(Arrays.asList(null));
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return this.f7523c;
    }

    @Override // cb.a
    public final Object h(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        return arrayList.toArray((Object[]) Array.newInstance((Class<?>) ((kotlin.jvm.internal.f) this.f7522b).c(), arrayList.size()));
    }

    @Override // cb.t
    public final void i(int i10, Object obj, Object obj2) {
        ((ArrayList) obj).add(i10, obj2);
    }
}
