package androidx.compose.ui.text.caches;

import androidx.compose.ui.text.platform.Synchronization_jvmKt;
import androidx.compose.ui.text.platform.SynchronizedObject;
import io.ktor.http.ContentDisposition;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\r\u0010\u0007J\u0017\u0010\u000e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0007J\u0017\u0010\u0013\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\u0013\u0010\u000fJ1\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00012\b\u0010\u0017\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001a\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001a\u0010\u000fJ\u001f\u0010\u001b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0014¢\u0006\u0004\b\u001b\u0010\u000bJ\r\u0010\u001c\u001a\u00020\f¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b\u001f\u0010\u001eJ\r\u0010 \u001a\u00020\u0004¢\u0006\u0004\b \u0010\u001eJ\r\u0010!\u001a\u00020\u0004¢\u0006\u0004\b!\u0010\u001eJ\r\u0010\"\u001a\u00020\u0004¢\u0006\u0004\b\"\u0010\u001eJ\r\u0010#\u001a\u00020\u0004¢\u0006\u0004\b#\u0010\u001eJ\u0019\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010$¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J$\u0010/\u001a\u00028\u0002\"\u0004\b\u0002\u0010*2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00020+H\u0080\b¢\u0006\u0004\b-\u0010.R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R0\u00105\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000103j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`48\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R$\u00109\u001a\u0012\u0012\u0004\u0012\u00028\u000007j\b\u0012\u0004\u0012\u00028\u0000`88\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R$\u0010<\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00048G@BX\u0086\u000e¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b<\u0010\u001eR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010=R\u0016\u0010\"\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010=R\u0016\u0010!\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010=R\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010=R\u0016\u0010\u001f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010=R\u0016\u0010 \u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010=¨\u0006>"}, d2 = {"Landroidx/compose/ui/text/caches/LruCache;", "K", "V", "", "", "maxSize", "<init>", "(I)V", "key", "value", "safeSizeOf", "(Ljava/lang/Object;Ljava/lang/Object;)I", "Lx6/t0;", "resize", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "trimToSize", "remove", "", "evicted", "oldValue", "newValue", "entryRemoved", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "create", "sizeOf", "evictAll", "()V", "()I", "hitCount", "missCount", "createCount", "putCount", "evictionCount", "", "snapshot", "()Ljava/util/Map;", "", "toString", "()Ljava/lang/String;", "R", "Lkotlin/Function0;", "block", "synchronizedValue$ui_text_release", "(Lr7/a;)Ljava/lang/Object;", "synchronizedValue", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "monitor", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "map", "Ljava/util/HashMap;", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "keySet", "Ljava/util/LinkedHashSet;", "<set-?>", ContentDisposition.Parameters.Size, "I", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class LruCache<K, V> {
    public static final int $stable = 8;
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashSet<K> keySet;
    private final HashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private final SynchronizedObject monitor = Synchronization_jvmKt.createSynchronizedObject();
    private int putCount;
    private int size;

    public LruCache(int i10) {
        if (i10 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i10;
        this.map = new HashMap<>(0, 0.75f);
        this.keySet = new LinkedHashSet<>();
    }

    private final int safeSizeOf(K key, V value) {
        int iSizeOf = sizeOf(key, value);
        if (iSizeOf >= 0) {
            return iSizeOf;
        }
        throw new IllegalStateException(("Negative size: " + key + '=' + value).toString());
    }

    public V create(K key) {
        return null;
    }

    public final int createCount() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.createCount;
        }
        return i10;
    }

    public void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final int evictionCount() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.evictionCount;
        }
        return i10;
    }

    public final V get(K key) {
        synchronized (this.monitor) {
            V v2 = this.map.get(key);
            if (v2 != null) {
                this.keySet.remove(key);
                this.keySet.add(key);
                this.hitCount++;
                return v2;
            }
            this.missCount++;
            V vCreate = create(key);
            if (vCreate == null) {
                return null;
            }
            synchronized (this.monitor) {
                try {
                    this.createCount++;
                    V vPut = this.map.put(key, vCreate);
                    this.keySet.remove(key);
                    this.keySet.add(key);
                    if (vPut != null) {
                        this.map.put(key, vPut);
                        v2 = vPut;
                    } else {
                        this.size = size() + safeSizeOf(key, vCreate);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (v2 != null) {
                entryRemoved(false, key, vCreate, v2);
                return v2;
            }
            trimToSize(this.maxSize);
            return vCreate;
        }
    }

    public final int hitCount() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.hitCount;
        }
        return i10;
    }

    public final int maxSize() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.maxSize;
        }
        return i10;
    }

    public final int missCount() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.missCount;
        }
        return i10;
    }

    public final V put(K key, V value) {
        V vPut;
        if (key == null || value == null) {
            throw null;
        }
        synchronized (this.monitor) {
            try {
                this.putCount++;
                this.size = size() + safeSizeOf(key, value);
                vPut = this.map.put(key, value);
                if (vPut != null) {
                    this.size = size() - safeSizeOf(key, vPut);
                }
                if (this.keySet.contains(key)) {
                    this.keySet.remove(key);
                }
                this.keySet.add(key);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (vPut != null) {
            entryRemoved(false, key, vPut, value);
        }
        trimToSize(this.maxSize);
        return vPut;
    }

    public final int putCount() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.putCount;
        }
        return i10;
    }

    public final V remove(K key) {
        V vRemove;
        key.getClass();
        synchronized (this.monitor) {
            vRemove = this.map.remove(key);
            this.keySet.remove(key);
            if (vRemove != null) {
                this.size = size() - safeSizeOf(key, vRemove);
            }
        }
        if (vRemove != null) {
            entryRemoved(false, key, vRemove, null);
        }
        return vRemove;
    }

    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this.monitor) {
            this.maxSize = maxSize;
        }
        trimToSize(maxSize);
    }

    public final int size() {
        int i10;
        synchronized (this.monitor) {
            i10 = this.size;
        }
        return i10;
    }

    public int sizeOf(K key, V value) {
        return 1;
    }

    public final Map<K, V> snapshot() {
        LinkedHashMap linkedHashMap;
        synchronized (this.monitor) {
            linkedHashMap = new LinkedHashMap();
            for (K k : this.keySet) {
                linkedHashMap.put(k, this.map.get(k));
            }
        }
        return linkedHashMap;
    }

    public final <R> R synchronizedValue$ui_text_release(a<? extends R> block) {
        R r4;
        synchronized (this.monitor) {
            r4 = (R) block.invoke();
        }
        return r4;
    }

    public String toString() {
        String str;
        synchronized (this.monitor) {
            try {
                int i10 = this.hitCount;
                int i11 = this.missCount + i10;
                str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + (i11 != 0 ? (i10 * 100) / i11 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0086, code lost:
    
        throw new java.lang.IllegalStateException("map/keySet size inconsistency");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r8) {
        /*
            r7 = this;
        L0:
            androidx.compose.ui.text.platform.SynchronizedObject r0 = r7.monitor
            monitor-enter(r0)
            int r1 = r7.size()     // Catch: java.lang.Throwable -> L18
            if (r1 < 0) goto L7e
            java.util.HashMap<K, V> r1 = r7.map     // Catch: java.lang.Throwable -> L18
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L1a
            int r1 = r7.size()     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L7e
            goto L1a
        L18:
            r8 = move-exception
            goto L87
        L1a:
            java.util.HashMap<K, V> r1 = r7.map     // Catch: java.lang.Throwable -> L18
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashSet<K> r2 = r7.keySet     // Catch: java.lang.Throwable -> L18
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r1 != r2) goto L7e
            int r1 = r7.size()     // Catch: java.lang.Throwable -> L18
            r2 = 1
            r3 = 0
            if (r1 <= r8) goto L72
            java.util.HashMap<K, V> r1 = r7.map     // Catch: java.lang.Throwable -> L18
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L72
            java.util.LinkedHashSet<K> r1 = r7.keySet     // Catch: java.lang.Throwable -> L18
            java.lang.Object r1 = kotlin.collections.x.l0(r1)     // Catch: java.lang.Throwable -> L18
            java.util.HashMap<K, V> r4 = r7.map     // Catch: java.lang.Throwable -> L18
            java.lang.Object r4 = r4.get(r1)     // Catch: java.lang.Throwable -> L18
            if (r4 == 0) goto L69
            java.util.HashMap<K, V> r5 = r7.map     // Catch: java.lang.Throwable -> L18
            java.util.Map r5 = kotlin.jvm.internal.q0.c(r5)     // Catch: java.lang.Throwable -> L18
            r5.remove(r1)     // Catch: java.lang.Throwable -> L18
            java.util.LinkedHashSet<K> r5 = r7.keySet     // Catch: java.lang.Throwable -> L18
            java.util.Collection r5 = kotlin.jvm.internal.q0.a(r5)     // Catch: java.lang.Throwable -> L18
            r5.remove(r1)     // Catch: java.lang.Throwable -> L18
            int r5 = r7.size()     // Catch: java.lang.Throwable -> L18
            int r6 = r7.safeSizeOf(r1, r4)     // Catch: java.lang.Throwable -> L18
            int r5 = r5 - r6
            r7.size = r5     // Catch: java.lang.Throwable -> L18
            int r5 = r7.evictionCount     // Catch: java.lang.Throwable -> L18
            int r5 = r5 + r2
            r7.evictionCount = r5     // Catch: java.lang.Throwable -> L18
            goto L74
        L69:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L18
            java.lang.String r1 = "inconsistent state"
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L18
            throw r8     // Catch: java.lang.Throwable -> L18
        L72:
            r1 = r3
            r4 = r1
        L74:
            monitor-exit(r0)
            if (r1 != 0) goto L7a
            if (r4 != 0) goto L7a
            return
        L7a:
            r7.entryRemoved(r2, r1, r4, r3)
            goto L0
        L7e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L18
            java.lang.String r1 = "map/keySet size inconsistency"
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L18
            throw r8     // Catch: java.lang.Throwable -> L18
        L87:
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.caches.LruCache.trimToSize(int):void");
    }
}
