package io.ktor.util.collections;

import io.ktor.http.ContentDisposition;
import io.ktor.util.InternalAPI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.collections.a0;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0006\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ \u0010\r\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u000f\u0010\u000bJ8\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00002!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00010\u0010¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/util/collections/CopyOnWriteHashMap;", "", "K", "V", "<init>", "()V", "key", "value", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lx6/t0;", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "producer", "computeIfAbsent", "(Ljava/lang/Object;Lr7/l;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CopyOnWriteHashMap<K, V> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater current$FU = AtomicReferenceFieldUpdater.newUpdater(CopyOnWriteHashMap.class, Object.class, "current");
    private volatile /* synthetic */ Object current = a0.f19683i;

    public final V computeIfAbsent(K key, l<? super K, ? extends V> producer) {
        while (true) {
            Map map = (Map) this.current;
            V v2 = (V) map.get(key);
            if (v2 != null) {
                return v2;
            }
            HashMap map2 = new HashMap(map);
            V v10 = (V) producer.invoke(key);
            map2.put(key, v10);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = current$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, map, map2)) {
                if (atomicReferenceFieldUpdater.get(this) != map) {
                    break;
                }
            }
            return v10;
        }
    }

    public final V get(K key) {
        return (V) ((Map) this.current).get(key);
    }

    public final V put(K key, V value) {
        while (true) {
            Map map = (Map) this.current;
            if (map.get(key) == value) {
                return value;
            }
            HashMap map2 = new HashMap(map);
            V v2 = (V) map2.put(key, value);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = current$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, map, map2)) {
                if (atomicReferenceFieldUpdater.get(this) != map) {
                    break;
                }
            }
            return v2;
        }
    }

    public final V remove(K key) {
        while (true) {
            Map map = (Map) this.current;
            if (map.get(key) == null) {
                return null;
            }
            HashMap map2 = new HashMap(map);
            V v2 = (V) map2.remove(key);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = current$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, map, map2)) {
                if (atomicReferenceFieldUpdater.get(this) != map) {
                    break;
                }
            }
            return v2;
        }
    }

    public final void set(K key, V value) {
        put(key, value);
    }
}
