package io.github.jan.supabase.collections;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.ktor.http.ContentDisposition;
import ja.a;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.h0;
import s7.g;
import x6.x;
import z6.f;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0004\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B3\u0012*\u0010\u0006\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0014\u001a\u00020\t2\u0014\u0010\u0013\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u0017\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u0011J\u0017\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001c\u0010\u001bR&\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00120\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR&\u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010!0 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0014\u0010*\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lio/github/jan/supabase/collections/AtomicMutableMap;", "K", "V", "", "", "Lx6/x;", "pairs", "<init>", "([Lx6/x;)V", "Lx6/t0;", "clear", "()V", "", "isEmpty", "()Z", "key", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "from", "putAll", "(Ljava/util/Map;)V", "value", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "get", "containsValue", "(Ljava/lang/Object;)Z", "containsKey", "Lja/a;", "map", "Lja/a;", "", "", "getEntries", "()Ljava/util/Set;", "entries", "getKeys", UserMetadata.KEYDATA_FILENAME, "", "getSize", "()I", ContentDisposition.Parameters.Size, "", "getValues", "()Ljava/util/Collection;", "values", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SupabaseInternal
public final class AtomicMutableMap<K, V> implements Map<K, V>, g {
    private final a map;

    public AtomicMutableMap(x... xVarArr) {
        this.map = new a(h0.t0((x[]) Arrays.copyOf(xVarArr, xVarArr.length)));
    }

    @Override // java.util.Map
    public void clear() {
        Object objB;
        a aVar = this.map;
        do {
            objB = aVar.b();
        } while (!aVar.a(objB, a0.f19683i));
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return ((Map) this.map.b()).containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return ((Map) this.map.b()).containsValue(value);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public V get(Object key) {
        return (V) ((Map) this.map.b()).get(key);
    }

    public Set<Map.Entry<K, V>> getEntries() {
        return new LinkedHashMap((Map) this.map.b()).entrySet();
    }

    public Set<K> getKeys() {
        return new LinkedHashMap((Map) this.map.b()).keySet();
    }

    public int getSize() {
        return ((Map) this.map.b()).size();
    }

    public Collection<V> getValues() {
        return new LinkedHashMap((Map) this.map.b()).values();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return ((Map) this.map.b()).isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        Object objB;
        f fVar;
        V v2;
        a aVar = this.map;
        do {
            objB = aVar.b();
            fVar = new f();
            fVar.putAll((Map) objB);
            v2 = (V) fVar.put(key, value);
        } while (!aVar.a(objB, fVar.c()));
        return v2;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> from) {
        Object objB;
        f fVar;
        a aVar = this.map;
        do {
            objB = aVar.b();
            fVar = new f();
            fVar.putAll((Map) objB);
            fVar.putAll(from);
        } while (!aVar.a(objB, fVar.c()));
    }

    @Override // java.util.Map
    public V remove(Object key) {
        Object objB;
        f fVar;
        V v2;
        a aVar = this.map;
        do {
            objB = aVar.b();
            fVar = new f();
            fVar.putAll((Map) objB);
            v2 = (V) fVar.remove(key);
        } while (!aVar.a(objB, fVar.c()));
        return v2;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }
}
