package dagger.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class LazyClassKeyMap<V> implements Map<Class<?>, V> {
    private final Map<String, V> delegate;

    public static class MapFactory<V> implements Factory<Map<Class<?>, V>> {
        Factory<Map<String, V>> delegate;

        private MapFactory(Factory<Map<String, V>> factory) {
            this.delegate = factory;
        }

        public static <V> MapFactory<V> of(Factory<Map<String, V>> factory) {
            return new MapFactory<>(factory);
        }

        @Override // javax.inject.Provider
        public Map<Class<?>, V> get() {
            return LazyClassKeyMap.of(this.delegate.get());
        }
    }

    public static class MapProviderFactory<V> implements Factory<Map<Class<?>, Provider<V>>> {
        Factory<Map<String, Provider<V>>> delegate;

        private MapProviderFactory(Factory<Map<String, Provider<V>>> factory) {
            this.delegate = factory;
        }

        public static <V> MapProviderFactory<V> of(Factory<Map<String, Provider<V>>> factory) {
            return new MapProviderFactory<>(factory);
        }

        @Override // javax.inject.Provider
        public Map<Class<?>, Provider<V>> get() {
            return LazyClassKeyMap.of(this.delegate.get());
        }
    }

    private LazyClassKeyMap(Map<String, V> map) {
        this.delegate = map;
    }

    public static <V> Map<Class<?>, V> of(Map<String, V> map) {
        return new LazyClassKeyMap(map);
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        if (obj instanceof Class) {
            return this.delegate.containsKey(((Class) obj).getName());
        }
        throw new IllegalArgumentException("Key must be a class");
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.delegate.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<Class<?>, V>> entrySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of entrySet(). Consider @ClassKey instead.");
    }

    @Override // java.util.Map
    public V get(Object obj) {
        if (obj instanceof Class) {
            return this.delegate.get(((Class) obj).getName());
        }
        throw new IllegalArgumentException("Key must be a class");
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public Set<Class<?>> keySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of keySet(). Consider @ClassKey instead.");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Class<?>, ? extends V> map) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public int size() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.delegate.values();
    }

    @Override // java.util.Map
    public V put(Class<?> cls, V v2) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }
}
