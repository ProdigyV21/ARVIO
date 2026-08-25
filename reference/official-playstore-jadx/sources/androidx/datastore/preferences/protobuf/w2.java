package androidx.datastore.preferences.protobuf;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class w2 extends AbstractSet {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2546i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Map f2547l;

    public /* synthetic */ w2(int i10, Map map) {
        this.f2546i = i10;
        this.f2547l = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        switch (this.f2546i) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                if (contains(entry)) {
                    return false;
                }
                ((r2) this.f2547l).put((Comparable) entry.getKey(), entry.getValue());
                return true;
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                if (contains(entry2)) {
                    return false;
                }
                ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l).put((Comparable) entry2.getKey(), entry2.getValue());
                return true;
            default:
                return super.add(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        switch (this.f2546i) {
            case 0:
                ((r2) this.f2547l).clear();
                break;
            case 1:
                ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l).clear();
                break;
            default:
                super.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        switch (this.f2546i) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                Object obj2 = ((r2) this.f2547l).get(entry.getKey());
                Object value = entry.getValue();
                return obj2 == value || (obj2 != null && obj2.equals(value));
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                Object obj3 = ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l).get(entry2.getKey());
                Object value2 = entry2.getValue();
                return obj3 == value2 || (obj3 != null && obj3.equals(value2));
            default:
                return super.contains(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        switch (this.f2546i) {
            case 0:
                return new v2((r2) this.f2547l, 0);
            case 1:
                return new v2((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l, 1);
            default:
                return new t.c((t.e) this.f2547l);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        switch (this.f2546i) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                if (!contains(entry)) {
                    return false;
                }
                ((r2) this.f2547l).remove(entry.getKey());
                return true;
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                if (!contains(entry2)) {
                    return false;
                }
                ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l).remove(entry2.getKey());
                return true;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.f2546i) {
            case 0:
                return ((r2) this.f2547l).size();
            case 1:
                return ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2547l).size();
            default:
                return ((t.e) this.f2547l).f21849m;
        }
    }
}
