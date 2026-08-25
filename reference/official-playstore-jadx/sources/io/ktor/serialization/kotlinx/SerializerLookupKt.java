package io.ktor.serialization.kotlinx;

import a.a;
import cb.b1;
import cb.d;
import cb.g0;
import cb.i1;
import cb.r1;
import fb.b;
import io.ktor.util.reflect.TypeInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.q;
import m2.f0;
import ya.e;
import ya.g;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a/\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0003\"\b\b\u0000\u0010\u0007*\u00020\u0006*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\f\u0010\r\u001a#\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0003*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lfb/b;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "Lya/h;", "serializerForTypeInfo", "(Lfb/b;Lio/ktor/util/reflect/TypeInfo;)Lya/h;", "", "T", "maybeNullable", "(Lya/h;Lio/ktor/util/reflect/TypeInfo;)Lya/h;", "value", "module", "guessSerializer", "(Ljava/lang/Object;Lfb/b;)Lya/h;", "", "elementSerializer", "(Ljava/util/Collection;Lfb/b;)Lya/h;", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SerializerLookupKt {
    private static final h<?> elementSerializer(Collection<?> collection, b bVar) {
        Collection<?> collection2 = collection;
        ArrayList arrayListK0 = x.k0(collection2);
        ArrayList arrayList = new ArrayList(s.U(arrayListK0, 10));
        Iterator it = arrayListK0.iterator();
        while (it.hasNext()) {
            arrayList.add(guessSerializer(it.next(), bVar));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (hashSet.add(((h) obj).getDescriptor().h())) {
                arrayList2.add(obj);
            }
        }
        if (arrayList2.size() > 1) {
            StringBuilder sb2 = new StringBuilder("Serializing collections of different element types is not yet supported. Selected serializers: ");
            ArrayList arrayList3 = new ArrayList(s.U(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(((h) it2.next()).getDescriptor().h());
            }
            sb2.append(arrayList3);
            throw new IllegalStateException(sb2.toString().toString());
        }
        h<?> hVar = (h) x.T0(arrayList2);
        if (hVar == null) {
            hVar = r1.f7553a;
        }
        if (!hVar.getDescriptor().b() && (!(collection2 instanceof Collection) || !collection2.isEmpty())) {
            Iterator<T> it3 = collection2.iterator();
            while (it3.hasNext()) {
                if (it3.next() == null) {
                    return a.Q(hVar);
                }
            }
        }
        return hVar;
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object, java.util.Map] */
    public static final h<Object> guessSerializer(Object obj, b bVar) throws IllegalAccessException, InvocationTargetException {
        h<Object> hVarGuessSerializer;
        if (obj == null) {
            return a.Q(r1.f7553a);
        }
        if (obj instanceof List) {
            return new d(elementSerializer((Collection) obj, bVar), 0);
        }
        if (obj instanceof Object[]) {
            Object objZ = r.Z((Object[]) obj);
            return (objZ == null || (hVarGuessSerializer = guessSerializer(objZ, bVar)) == null) ? new d(r1.f7553a, 0) : hVarGuessSerializer;
        }
        if (obj instanceof Set) {
            return new d(elementSerializer((Collection) obj, bVar), 2);
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return new g0(elementSerializer(map.keySet(), bVar), elementSerializer(map.values(), bVar), 1);
        }
        Class<?> cls = obj.getClass();
        m0 m0Var = l0.f19747a;
        m0Var.b(cls);
        bVar.getClass();
        kotlin.reflect.d dVarB = m0Var.b(obj.getClass());
        h<Object> hVarD = b1.d(dVarB, new h[0]);
        if (hVarD == null) {
            hVarD = (h) i1.f7517a.get(dVarB);
        }
        if (hVarD != null) {
            return hVarD;
        }
        b1.g(dVarB);
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> h<?> maybeNullable(h<T> hVar, TypeInfo typeInfo) {
        q kotlinType = typeInfo.getKotlinType();
        return (kotlinType == null || !kotlinType.j()) ? hVar : a.Q(hVar);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.util.Map] */
    @g
    @e
    public static final h<?> serializerForTypeInfo(b bVar, TypeInfo typeInfo) throws IllegalAccessException, InvocationTargetException {
        q kotlinType = typeInfo.getKotlinType();
        if (kotlinType != null) {
            h<?> hVarL = kotlinType.g().isEmpty() ? null : f0.L(bVar, kotlinType, false);
            if (hVarL != null) {
                return hVarL;
            }
        }
        typeInfo.getType();
        bVar.getClass();
        kotlin.reflect.d<?> type = typeInfo.getType();
        h hVarD = b1.d(type, new h[0]);
        if (hVarD == null) {
            hVarD = (h) i1.f7517a.get(type);
        }
        if (hVarD != null) {
            return maybeNullable(hVarD, typeInfo);
        }
        b1.g(type);
        throw null;
    }
}
