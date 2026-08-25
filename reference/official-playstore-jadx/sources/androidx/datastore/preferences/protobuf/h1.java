package androidx.datastore.preferences.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 extends d implements i1, RandomAccess {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f2445l;

    static {
        new h1(10).f2415i = false;
    }

    public h1(int i10) {
        this(new ArrayList(i10));
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final Object J0(int i10) {
        return this.f2445l.get(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.e1
    public final e1 V(int i10) {
        ArrayList arrayList = this.f2445l;
        if (i10 < arrayList.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList2 = new ArrayList(i10);
        arrayList2.addAll(arrayList);
        return new h1(arrayList2);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        a();
        this.f2445l.add(i10, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // androidx.datastore.preferences.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.f2445l.size(), collection);
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final void b0(m mVar) {
        a();
        this.f2445l.add(mVar);
        ((AbstractList) this).modCount++;
    }

    @Override // androidx.datastore.preferences.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.f2445l.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        ArrayList arrayList = this.f2445l;
        Object obj = arrayList.get(i10);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof m)) {
            byte[] bArr = (byte[]) obj;
            String str = new String(bArr, f1.f2421a);
            if (s3.f2528a.i(0, bArr.length, bArr) == 0) {
                arrayList.set(i10, str);
            }
            return str;
        }
        m mVar = (m) obj;
        String str2 = mVar.size() == 0 ? "" : new String(mVar.f2473l, mVar.b(), mVar.size(), f1.f2421a);
        int iB = mVar.b();
        if (s3.f2528a.i(iB, mVar.size() + iB, mVar.f2473l) == 0) {
            arrayList.set(i10, str2);
        }
        return str2;
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final List i() {
        return Collections.unmodifiableList(this.f2445l);
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final i1 l() {
        return this.f2415i ? new k3(this) : this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        a();
        Object objRemove = this.f2445l.remove(i10);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (!(objRemove instanceof m)) {
            return new String((byte[]) objRemove, f1.f2421a);
        }
        m mVar = (m) objRemove;
        return mVar.size() == 0 ? "" : new String(mVar.f2473l, mVar.b(), mVar.size(), f1.f2421a);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        a();
        Object obj2 = this.f2445l.set(i10, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (!(obj2 instanceof m)) {
            return new String((byte[]) obj2, f1.f2421a);
        }
        m mVar = (m) obj2;
        return mVar.size() == 0 ? "" : new String(mVar.f2473l, mVar.b(), mVar.size(), f1.f2421a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f2445l.size();
    }

    public h1(ArrayList arrayList) {
        this.f2445l = arrayList;
    }

    @Override // androidx.datastore.preferences.protobuf.d, java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        a();
        if (collection instanceof i1) {
            collection = ((i1) collection).i();
        }
        boolean zAddAll = this.f2445l.addAll(i10, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }
}
