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
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"com/arflix/tv/data/repository/IptvRepository$iptvMovieSourceMemory$1", "Ljava/util/LinkedHashMap;", "", "Lcom/arflix/tv/data/repository/IptvRepository$CachedIptvMovieSources;", "Lkotlin/collections/LinkedHashMap;", "removeEldestEntry", "", "eldest", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvRepository$iptvMovieSourceMemory$1 extends LinkedHashMap<String, IptvRepository.CachedIptvMovieSources> implements Map {
    public IptvRepository$iptvMovieSourceMemory$1() {
        super(256, 0.75f, true);
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

    public /* bridge */ boolean containsValue(IptvRepository.CachedIptvMovieSources cachedIptvMovieSources) {
        return super.containsValue((Object) cachedIptvMovieSources);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, IptvRepository.CachedIptvMovieSources>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ IptvRepository.CachedIptvMovieSources get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public /* bridge */ Set<Map.Entry<String, IptvRepository.CachedIptvMovieSources>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    public final /* bridge */ IptvRepository.CachedIptvMovieSources getOrDefault(Object obj, IptvRepository.CachedIptvMovieSources cachedIptvMovieSources) {
        return !(obj instanceof String) ? cachedIptvMovieSources : getOrDefault((String) obj, cachedIptvMovieSources);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<IptvRepository.CachedIptvMovieSources> getValues() {
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
    public final /* bridge */ IptvRepository.CachedIptvMovieSources remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<String, IptvRepository.CachedIptvMovieSources> eldest) {
        return size() > 256;
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
    public final /* bridge */ Collection<IptvRepository.CachedIptvMovieSources> values() {
        return getValues();
    }

    public /* bridge */ boolean containsKey(String str) {
        return super.containsKey((Object) str);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof IptvRepository.CachedIptvMovieSources) {
            return containsValue((IptvRepository.CachedIptvMovieSources) obj);
        }
        return false;
    }

    public /* bridge */ IptvRepository.CachedIptvMovieSources get(String str) {
        return (IptvRepository.CachedIptvMovieSources) super.get((Object) str);
    }

    public /* bridge */ IptvRepository.CachedIptvMovieSources getOrDefault(String str, IptvRepository.CachedIptvMovieSources cachedIptvMovieSources) {
        return (IptvRepository.CachedIptvMovieSources) Map.CC.$default$getOrDefault(this, str, cachedIptvMovieSources);
    }

    public /* bridge */ IptvRepository.CachedIptvMovieSources remove(String str) {
        return (IptvRepository.CachedIptvMovieSources) super.remove((Object) str);
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
        return !(obj instanceof String) ? obj2 : getOrDefault((String) obj, (IptvRepository.CachedIptvMovieSources) obj2);
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
        if ((obj instanceof String) && (obj2 instanceof IptvRepository.CachedIptvMovieSources)) {
            return remove((String) obj, (IptvRepository.CachedIptvMovieSources) obj2);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str, IptvRepository.CachedIptvMovieSources cachedIptvMovieSources) {
        return Map.CC.$default$remove(this, str, cachedIptvMovieSources);
    }
}
