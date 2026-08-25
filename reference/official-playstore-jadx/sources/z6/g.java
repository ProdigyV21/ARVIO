package z6;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.l;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23237i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f f23238l;

    public /* synthetic */ g(f fVar, int i10) {
        this.f23237i = i10;
        this.f23238l = fVar;
    }

    @Override // kotlin.collections.l, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.f23237i) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        switch (this.f23237i) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f23237i) {
            case 0:
                this.f23238l.clear();
                break;
            default:
                this.f23238l.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.f23237i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                f fVar = this.f23238l;
                fVar.getClass();
                int i10 = fVar.i(entry.getKey());
                if (i10 < 0) {
                    return false;
                }
                return p.a(fVar.f23225l[i10], entry.getValue());
            default:
                return this.f23238l.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.f23237i) {
            case 0:
                f fVar = this.f23238l;
                fVar.getClass();
                for (Object obj : collection) {
                    if (obj == null) {
                        return false;
                    }
                    try {
                        Map.Entry entry = (Map.Entry) obj;
                        int i10 = fVar.i(entry.getKey());
                        if (!(i10 < 0 ? false : p.a(fVar.f23225l[i10], entry.getValue()))) {
                            return false;
                        }
                    } catch (ClassCastException unused) {
                        return false;
                    }
                }
                return true;
            default:
                return super.containsAll(collection);
        }
    }

    @Override // kotlin.collections.l
    public final int getSize() {
        switch (this.f23237i) {
        }
        return this.f23238l.f23232s;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.f23237i) {
        }
        return this.f23238l.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f23237i) {
            case 0:
                f fVar = this.f23238l;
                fVar.getClass();
                return new d(fVar, 0);
            default:
                f fVar2 = this.f23238l;
                fVar2.getClass();
                return new d(fVar2, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.f23237i) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    f fVar = this.f23238l;
                    fVar.e();
                    int i10 = fVar.i(entry.getKey());
                    if (i10 >= 0 && p.a(fVar.f23225l[i10], entry.getValue())) {
                        fVar.m(i10);
                    }
                    break;
                }
                break;
            default:
                f fVar2 = this.f23238l;
                fVar2.e();
                int i11 = fVar2.i(obj);
                if (i11 >= 0) {
                    fVar2.m(i11);
                    break;
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        switch (this.f23237i) {
            case 0:
                this.f23238l.e();
                break;
            default:
                this.f23238l.e();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        switch (this.f23237i) {
            case 0:
                this.f23238l.e();
                break;
            default:
                this.f23238l.e();
                break;
        }
        return super.retainAll(collection);
    }
}
