package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends AbstractList implements RandomAccess, z {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p0 f19849l = new p0(new y());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f19850i;

    public y() {
        this.f19850i = new ArrayList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final g O(int i10) {
        g b0Var;
        ArrayList arrayList = this.f19850i;
        Object obj = arrayList.get(i10);
        if (obj instanceof g) {
            b0Var = (g) obj;
        } else if (obj instanceof String) {
            try {
                b0Var = new b0(((String) obj).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e5) {
                throw new RuntimeException("UTF-8 not supported?", e5);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            b0Var = new b0(bArr2);
        }
        if (b0Var != obj) {
            arrayList.set(i10, b0Var);
        }
        return b0Var;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        this.f19850i.add(i10, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.f19850i.size(), collection);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.f19850i.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        ArrayList arrayList = this.f19850i;
        Object obj = arrayList.get(i10);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            String strQ = gVar.q();
            if (gVar.g()) {
                arrayList.set(i10, strQ);
            }
            return strQ;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = x.f19848a;
        try {
            String str = new String(bArr, "UTF-8");
            if (l0.c(0, bArr.length, bArr) == 0) {
                arrayList.set(i10, str);
            }
            return str;
        } catch (UnsupportedEncodingException e5) {
            throw new RuntimeException("UTF-8 not supported?", e5);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final List i() {
        return Collections.unmodifiableList(this.f19850i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final p0 l() {
        return new p0(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        Object objRemove = this.f19850i.remove(i10);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (objRemove instanceof g) {
            return ((g) objRemove).q();
        }
        byte[] bArr = (byte[]) objRemove;
        byte[] bArr2 = x.f19848a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e5) {
            throw new RuntimeException("UTF-8 not supported?", e5);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        Object obj2 = this.f19850i.set(i10, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 instanceof g) {
            return ((g) obj2).q();
        }
        byte[] bArr = (byte[]) obj2;
        byte[] bArr2 = x.f19848a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e5) {
            throw new RuntimeException("UTF-8 not supported?", e5);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f19850i.size();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final void u0(b0 b0Var) {
        this.f19850i.add(b0Var);
        ((AbstractList) this).modCount++;
    }

    public y(z zVar) {
        this.f19850i = new ArrayList(zVar.size());
        addAll(zVar);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        if (collection instanceof z) {
            collection = ((z) collection).i();
        }
        boolean zAddAll = this.f19850i.addAll(i10, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }
}
