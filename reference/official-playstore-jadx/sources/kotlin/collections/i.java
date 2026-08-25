package kotlin.collections;

import java.util.AbstractCollection;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i extends AbstractCollection implements Collection, s7.b {
    public abstract int getSize();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }
}
