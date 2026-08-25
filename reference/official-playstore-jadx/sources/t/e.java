package t;

import androidx.datastore.preferences.protobuf.w2;
import j$.util.Map;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final class e extends l0 implements Map, j$.util.Map {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public w2 f21801n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b f21802o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d f21803p;

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

    @Override // java.util.Map
    public final Set entrySet() {
        w2 w2Var = this.f21801n;
        if (w2Var != null) {
            return w2Var;
        }
        w2 w2Var2 = new w2(2, this);
        this.f21801n = w2Var2;
        return w2Var2;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map
    public final Set keySet() {
        b bVar = this.f21802o;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(this);
        this.f21802o = bVar2;
        return bVar2;
    }

    public final boolean l(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!super.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final boolean m(Collection collection) {
        int i10 = this.f21849m;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            super.remove(it.next());
        }
        return i10 != this.f21849m;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    public final boolean o(Collection collection) {
        int i10 = this.f21849m;
        for (int i11 = i10 - 1; i11 >= 0; i11--) {
            if (!collection.contains(h(i11))) {
                i(i11);
            }
        }
        return i10 != this.f21849m;
    }

    @Override // java.util.Map
    public final void putAll(java.util.Map map) {
        int size = map.size() + this.f21849m;
        int i10 = this.f21849m;
        int[] iArr = this.f21847i;
        if (iArr.length < size) {
            this.f21847i = Arrays.copyOf(iArr, size);
            this.f21848l = Arrays.copyOf(this.f21848l, size * 2);
        }
        if (this.f21849m != i10) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    @Override // java.util.Map
    public final Collection values() {
        d dVar = this.f21803p;
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this);
        this.f21803p = dVar2;
        return dVar2;
    }
}
