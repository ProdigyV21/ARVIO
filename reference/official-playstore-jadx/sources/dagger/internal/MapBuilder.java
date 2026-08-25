package dagger.internal;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class MapBuilder<K, V> {
    private final Map<K, V> contributions;

    private MapBuilder(int i10) {
        this.contributions = DaggerCollections.newLinkedHashMapWithExpectedSize(i10);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int i10) {
        return new MapBuilder<>(i10);
    }

    public Map<K, V> build() {
        return this.contributions.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.contributions);
    }

    public MapBuilder<K, V> put(K k, V v2) {
        this.contributions.put(k, v2);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.contributions.putAll(map);
        return this;
    }
}
