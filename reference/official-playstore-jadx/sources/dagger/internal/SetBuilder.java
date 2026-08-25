package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class SetBuilder<T> {
    private static final String SET_CONTRIBUTIONS_CANNOT_BE_NULL = "Set contributions cannot be null";
    private final List<T> contributions;

    private SetBuilder(int i10) {
        this.contributions = new ArrayList(i10);
    }

    public static <T> SetBuilder<T> newSetBuilder(int i10) {
        return new SetBuilder<>(i10);
    }

    public SetBuilder<T> add(T t2) {
        this.contributions.add((T) Preconditions.checkNotNull(t2, SET_CONTRIBUTIONS_CANNOT_BE_NULL));
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), SET_CONTRIBUTIONS_CANNOT_BE_NULL);
        }
        this.contributions.addAll(collection);
        return this;
    }

    public Set<T> build() {
        return this.contributions.isEmpty() ? Collections.EMPTY_SET : this.contributions.size() == 1 ? Collections.singleton(this.contributions.get(0)) : Collections.unmodifiableSet(new HashSet(this.contributions));
    }
}
