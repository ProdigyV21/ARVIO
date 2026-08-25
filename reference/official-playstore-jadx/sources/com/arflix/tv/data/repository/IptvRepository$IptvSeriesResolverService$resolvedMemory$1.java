package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import j$.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"com/arflix/tv/data/repository/IptvRepository$IptvSeriesResolverService$resolvedMemory$1", "Ljava/util/LinkedHashMap;", "", "Lcom/arflix/tv/data/repository/IptvRepository$ResolverCachedResolvedEpisode;", "Lkotlin/collections/LinkedHashMap;", "removeEldestEntry", "", "eldest", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvRepository$IptvSeriesResolverService$resolvedMemory$1 extends LinkedHashMap<String, IptvRepository.ResolverCachedResolvedEpisode> implements Map {
    public IptvRepository$IptvSeriesResolverService$resolvedMemory$1() {
        super(512, 0.75f, true);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean containsValue(IptvRepository.ResolverCachedResolvedEpisode resolverCachedResolvedEpisode) {
        return super.containsValue((Object) resolverCachedResolvedEpisode);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, IptvRepository.ResolverCachedResolvedEpisode>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ IptvRepository.ResolverCachedResolvedEpisode get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public /* bridge */ Set<Map.Entry<String, IptvRepository.ResolverCachedResolvedEpisode>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    public final /* bridge */ IptvRepository.ResolverCachedResolvedEpisode getOrDefault(Object obj, IptvRepository.ResolverCachedResolvedEpisode resolverCachedResolvedEpisode) {
        return !(obj instanceof String) ? resolverCachedResolvedEpisode : getOrDefault((String) obj, resolverCachedResolvedEpisode);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<IptvRepository.ResolverCachedResolvedEpisode> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ IptvRepository.ResolverCachedResolvedEpisode remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<String, IptvRepository.ResolverCachedResolvedEpisode> eldest) {
        return size() > 512;
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<IptvRepository.ResolverCachedResolvedEpisode> values() {
        return getValues();
    }

    public /* bridge */ boolean containsKey(String str) {
        return super.containsKey((Object) str);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof IptvRepository.ResolverCachedResolvedEpisode) {
            return containsValue((IptvRepository.ResolverCachedResolvedEpisode) obj);
        }
        return false;
    }

    public /* bridge */ IptvRepository.ResolverCachedResolvedEpisode get(String str) {
        return (IptvRepository.ResolverCachedResolvedEpisode) super.get((Object) str);
    }

    public /* bridge */ IptvRepository.ResolverCachedResolvedEpisode getOrDefault(String str, IptvRepository.ResolverCachedResolvedEpisode resolverCachedResolvedEpisode) {
        return (IptvRepository.ResolverCachedResolvedEpisode) Map.CC.$default$getOrDefault(this, str, resolverCachedResolvedEpisode);
    }

    public /* bridge */ IptvRepository.ResolverCachedResolvedEpisode remove(String str) {
        return (IptvRepository.ResolverCachedResolvedEpisode) super.remove((Object) str);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof String) ? obj2 : getOrDefault((String) obj, (IptvRepository.ResolverCachedResolvedEpisode) obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof IptvRepository.ResolverCachedResolvedEpisode)) {
            return remove((String) obj, (IptvRepository.ResolverCachedResolvedEpisode) obj2);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str, IptvRepository.ResolverCachedResolvedEpisode resolverCachedResolvedEpisode) {
        return Map.CC.$default$remove(this, str, resolverCachedResolvedEpisode);
    }
}
