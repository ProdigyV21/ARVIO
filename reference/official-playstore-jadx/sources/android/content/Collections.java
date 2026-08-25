package android.content;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.x;
import kotlin.collections.z;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: renamed from: coil.util.-Collections, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010&\n\u0002\b\u0007\u001a4\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0080\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a:\u0010\t\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0007H\u0080\b¢\u0006\u0004\b\t\u0010\n\u001aH\u0010\u000e\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\f\u001a\u00028\u00012\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007H\u0080\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a>\u0010\u0011\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0002H\u0080\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a4\u0010\u0016\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140\u0002H\u0080\b¢\u0006\u0004\b\u0016\u0010\u0006\u001ad\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u001a\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018\"\b\b\u0002\u0010\u000b*\u00020\u0019*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2 \u0010\u0010\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001b\u0012\u0006\u0012\u0004\u0018\u00018\u00020\u0002H\u0080\b¢\u0006\u0004\b\u001c\u0010\u001d\u001a7\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aH\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a%\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"T", "", "Lkotlin/Function1;", "Lx6/t0;", "action", "forEachIndices", "(Ljava/util/List;Lr7/l;)V", "Lkotlin/Function2;", "", "forEachIndexedIndices", "(Ljava/util/List;Lr7/p;)V", "R", "initial", "operation", "foldIndices", "(Ljava/util/List;Ljava/lang/Object;Lr7/p;)Ljava/lang/Object;", "transform", "firstNotNullOfOrNullIndices", "(Ljava/util/List;Lr7/l;)Ljava/lang/Object;", "", "", "predicate", "removeIfIndices", "K", "V", "", "", "", "mapNotNullValues", "(Ljava/util/Map;Lr7/l;)Ljava/util/Map;", "toImmutableMap", "(Ljava/util/Map;)Ljava/util/Map;", "toImmutableList", "(Ljava/util/List;)Ljava/util/List;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Collections {
    public static final <R, T> T firstNotNullOfOrNullIndices(List<? extends R> list, l<? super R, ? extends T> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = (T) lVar.invoke(list.get(i10));
            if (t2 != null) {
                return t2;
            }
        }
        return null;
    }

    public static final <T, R> R foldIndices(List<? extends T> list, R r4, p<? super R, ? super T, ? extends R> pVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            r4 = (R) pVar.invoke(r4, list.get(i10));
        }
        return r4;
    }

    public static final <T> void forEachIndexedIndices(List<? extends T> list, p<? super Integer, ? super T, t0> pVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            pVar.invoke(Integer.valueOf(i10), list.get(i10));
        }
    }

    public static final <T> void forEachIndices(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            lVar.invoke(list.get(i10));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V, R> Map<K, R> mapNotNullValues(Map<K, ? extends V> map, l<? super Map.Entry<? extends K, ? extends V>, ? extends R> lVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<K, ? extends V> entry : map.entrySet()) {
            Object objInvoke = lVar.invoke(entry);
            if (objInvoke != null) {
                linkedHashMap.put(entry.getKey(), objInvoke);
            }
        }
        return linkedHashMap;
    }

    public static final <T> void removeIfIndices(List<T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            int i12 = i11 - i10;
            if (((Boolean) lVar.invoke(list.get(i12))).booleanValue()) {
                list.remove(i12);
                i10++;
            }
        }
    }

    public static final <T> List<T> toImmutableList(List<? extends T> list) {
        int size = list.size();
        return size != 0 ? size != 1 ? java.util.Collections.unmodifiableList(new ArrayList(list)) : java.util.Collections.singletonList(x.m0(list)) : z.f19728i;
    }

    public static final <K, V> Map<K, V> toImmutableMap(Map<K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return a0.f19683i;
        }
        if (size != 1) {
            return java.util.Collections.unmodifiableMap(new LinkedHashMap(map));
        }
        Map.Entry entry = (Map.Entry) x.l0(map.entrySet());
        return java.util.Collections.singletonMap(entry.getKey(), entry.getValue());
    }
}
