package kotlin.collections;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j extends AbstractList implements List, s7.e {
    public abstract int getSize();

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i10) {
        return removeAt(i10);
    }

    public abstract Object removeAt(int i10);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
