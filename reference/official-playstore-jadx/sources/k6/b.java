package k6;

import j$.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.collections.k;
import kotlin.collections.s;
import kotlin.reflect.l;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends k implements Map {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f19518i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object[] f19519l;

    public b(List list, Object[] objArr) {
        this.f19518i = list;
        this.f19519l = objArr;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return (obj instanceof l) && this.f19519l[((l) obj).getIndex()] != c.f19520a;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        if ((obj instanceof l) && (obj2 = this.f19519l[((l) obj).getIndex()]) != c.f19520a) {
            return obj2;
        }
        return null;
    }

    @Override // kotlin.collections.k
    public final Set getEntries() {
        List list = this.f19518i;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        int i10 = 0;
        for (Object obj : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList.add(new AbstractMap.SimpleEntry((l) obj, this.f19519l[i10]));
            i10 = i11;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj2 : arrayList) {
            if (((AbstractMap.SimpleEntry) obj2).getValue() != c.f19520a) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    @Override // java.util.Map, j$.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof l) ? obj2 : Map.CC.$default$getOrDefault(this, (l) obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return null;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj instanceof l) {
            return super.remove((l) obj);
        }
        return null;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if (obj instanceof l) {
            return Map.CC.$default$remove(this, (l) obj, obj2);
        }
        return false;
    }
}
