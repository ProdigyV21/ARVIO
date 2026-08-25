package dagger.internal;

import dagger.Lazy;
import dagger.internal.AbstractMapFactory;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {

    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, Provider<V>> {
        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }

        private Builder(int i10) {
            super(i10);
        }

        @Override // dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K k, Provider<V> provider) {
            super.put((Object) k, (Provider) provider);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(Provider<Map<K, Provider<V>>> provider) {
            super.putAll((Provider) provider);
            return this;
        }

        @Deprecated
        public Builder<K, V> put(K k, javax.inject.Provider<V> provider) {
            return put((Object) k, (Provider) Providers.asDaggerProvider(provider));
        }

        @Deprecated
        public Builder<K, V> putAll(final javax.inject.Provider<Map<K, javax.inject.Provider<V>>> provider) {
            return putAll((Provider) new Provider<Map<K, Provider<V>>>() { // from class: dagger.internal.MapProviderFactory.Builder.1
                @Override // javax.inject.Provider
                public Map<K, Provider<V>> get() {
                    Map map = (Map) provider.get();
                    if (map.isEmpty()) {
                        return Collections.EMPTY_MAP;
                    }
                    LinkedHashMap linkedHashMapNewLinkedHashMapWithExpectedSize = DaggerCollections.newLinkedHashMapWithExpectedSize(map.size());
                    for (Map.Entry<K, V> entry : map.entrySet()) {
                        linkedHashMapNewLinkedHashMapWithExpectedSize.put(entry.getKey(), Providers.asDaggerProvider((javax.inject.Provider) entry.getValue()));
                    }
                    return Collections.unmodifiableMap(linkedHashMapNewLinkedHashMapWithExpectedSize);
                }
            });
        }
    }

    public static <K, V> Builder<K, V> builder(int i10) {
        return new Builder<>(i10);
    }

    private MapProviderFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    @Override // javax.inject.Provider
    public Map<K, Provider<V>> get() {
        return contributingMap();
    }
}
