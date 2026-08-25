package ya;

import j8.t0;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends cb.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.reflect.d f22991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f22992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f22993c = f4.f.o(2, new t0(this, 15));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f22994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f22995e;

    public n(kotlin.reflect.d dVar, kotlin.reflect.d[] dVarArr, h[] hVarArr, Annotation[] annotationArr) {
        this.f22991a = dVar;
        this.f22992b = kotlin.collections.z.f19728i;
        if (dVarArr.length != hVarArr.length) {
            throw new IllegalArgumentException("All subclasses of sealed class " + dVar.r() + " should be marked @Serializable");
        }
        Map mapA0 = h0.A0(kotlin.collections.r.q0(dVarArr, hVarArr));
        this.f22994d = mapA0;
        Set<Map.Entry> setEntrySet = mapA0.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : setEntrySet) {
            String strH = ((h) entry.getValue()).getDescriptor().h();
            Object obj = linkedHashMap.get(strH);
            if (obj == null) {
                linkedHashMap.containsKey(strH);
            }
            Map.Entry entry2 = (Map.Entry) obj;
            if (entry2 != null) {
                throw new IllegalStateException(("Multiple sealed subclasses of '" + this.f22991a + "' have the same serial name '" + strH + "': '" + entry2.getKey() + "', '" + entry.getKey() + '\'').toString());
            }
            linkedHashMap.put(strH, entry);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i0.q0(linkedHashMap.size()));
        for (Map.Entry entry3 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry3.getKey(), (h) ((Map.Entry) entry3.getValue()).getValue());
        }
        this.f22995e = linkedHashMap2;
        this.f22992b = Arrays.asList(annotationArr);
    }

    @Override // cb.b
    public final c a(bb.b bVar, String str) {
        h hVar = (h) this.f22995e.get(str);
        if (hVar != null) {
            return hVar;
        }
        super.a(bVar, str);
        return null;
    }

    @Override // cb.b
    public final s b(bb.e eVar, Object obj) {
        h hVar = (h) this.f22994d.get(l0.f19747a.b(obj.getClass()));
        if (hVar == null) {
            super.b(eVar, obj);
            hVar = null;
        }
        if (hVar != null) {
            return hVar;
        }
        return null;
    }

    @Override // cb.b
    public final kotlin.reflect.d c() {
        return this.f22991a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return (ab.g) this.f22993c.getValue();
    }
}
