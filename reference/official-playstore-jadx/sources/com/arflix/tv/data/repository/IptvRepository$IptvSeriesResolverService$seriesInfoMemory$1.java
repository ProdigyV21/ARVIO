package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import j$.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002*\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001j\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003`\u0005J$\u0010\u0006\u001a\u00020\u00072\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\tH\u0014¨\u0006\n"}, d2 = {"com/arflix/tv/data/repository/IptvRepository$IptvSeriesResolverService$seriesInfoMemory$1", "Ljava/util/LinkedHashMap;", "", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "Lkotlin/collections/LinkedHashMap;", "removeEldestEntry", "", "eldest", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvRepository$IptvSeriesResolverService$seriesInfoMemory$1 extends LinkedHashMap<String, List<? extends IptvRepository.XtreamSeriesEpisode>> implements Map {
    public IptvRepository$IptvSeriesResolverService$seriesInfoMemory$1() {
        super(50, 0.75f, true);
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

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof List) {
            return containsValue((List<IptvRepository.XtreamSeriesEpisode>) obj);
        }
        return false;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, List<IptvRepository.XtreamSeriesEpisode>>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public /* bridge */ Set<Map.Entry<String, List<IptvRepository.XtreamSeriesEpisode>>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map, j$.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof String) ? obj2 : getOrDefault((String) obj, (List<IptvRepository.XtreamSeriesEpisode>) obj2);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<List<IptvRepository.XtreamSeriesEpisode>> getValues() {
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
    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<String, List<? extends IptvRepository.XtreamSeriesEpisode>> eldest) {
        return size() > 50;
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
    public final /* bridge */ Collection<List<IptvRepository.XtreamSeriesEpisode>> values() {
        return getValues();
    }

    public /* bridge */ boolean containsKey(String str) {
        return super.containsKey((Object) str);
    }

    public /* bridge */ boolean containsValue(List<IptvRepository.XtreamSeriesEpisode> list) {
        return super.containsValue((Object) list);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ List<IptvRepository.XtreamSeriesEpisode> get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public final /* bridge */ List<IptvRepository.XtreamSeriesEpisode> getOrDefault(Object obj, List<IptvRepository.XtreamSeriesEpisode> list) {
        return !(obj instanceof String) ? list : getOrDefault((String) obj, list);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ List<IptvRepository.XtreamSeriesEpisode> remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }

    public /* bridge */ List<IptvRepository.XtreamSeriesEpisode> get(String str) {
        return (List) super.get((Object) str);
    }

    public /* bridge */ List<IptvRepository.XtreamSeriesEpisode> getOrDefault(String str, List<IptvRepository.XtreamSeriesEpisode> list) {
        return (List) Map.CC.$default$getOrDefault(this, str, list);
    }

    public /* bridge */ List<IptvRepository.XtreamSeriesEpisode> remove(String str) {
        return (List) super.remove((Object) str);
    }

    @Override // java.util.HashMap, java.util.Map, j$.util.Map
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof List)) {
            return remove((String) obj, (List<IptvRepository.XtreamSeriesEpisode>) obj2);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str, List<IptvRepository.XtreamSeriesEpisode> list) {
        return Map.CC.$default$remove(this, str, list);
    }
}
