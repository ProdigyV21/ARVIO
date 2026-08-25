package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.EMPTY_SET);
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;

    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> provider) {
            this.collectionProviders.add(provider);
            return this;
        }

        public Builder<T> addProvider(Provider<? extends T> provider) {
            this.individualProviders.add(provider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }

        private Builder(int i10, int i11) {
            this.individualProviders = DaggerCollections.presizedList(i10);
            this.collectionProviders = DaggerCollections.presizedList(i11);
        }

        @Deprecated
        public Builder<T> addCollectionProvider(javax.inject.Provider<? extends Collection<? extends T>> provider) {
            return addCollectionProvider((Provider) Providers.asDaggerProvider(provider));
        }

        @Deprecated
        public Builder<T> addProvider(javax.inject.Provider<? extends T> provider) {
            return addProvider((Provider) Providers.asDaggerProvider(provider));
        }
    }

    public static <T> Builder<T> builder(int i10, int i11) {
        return new Builder<>(i10, i11);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    private SetFactory(List<Provider<T>> list, List<Provider<Collection<T>>> list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    @Override // javax.inject.Provider
    public Set<T> get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int i10 = 0; i10 < size2; i10++) {
            Collection<T> collection = this.collectionProviders.get(i10).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSetNewHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int i11 = 0; i11 < size3; i11++) {
            hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(i11).get()));
        }
        int size4 = arrayList.size();
        for (int i12 = 0; i12 < size4; i12++) {
            Iterator it = ((Collection) arrayList.get(i12)).iterator();
            while (it.hasNext()) {
                hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(it.next()));
            }
        }
        return Collections.unmodifiableSet(hashSetNewHashSetWithExpectedSize);
    }
}
