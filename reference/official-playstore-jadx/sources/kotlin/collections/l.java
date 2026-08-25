package kotlin.collections;

import java.util.AbstractSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l extends AbstractSet implements Set, s7.h {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @x6.p
    public abstract boolean add(Object obj);

    public abstract int getSize();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }
}
