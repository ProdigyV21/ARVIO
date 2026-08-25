package t;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class r {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final u.b lock;
    private final u.c map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public r(int i10) {
        this.maxSize = i10;
        if (i10 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.map = new u.c();
        this.lock = new u.b();
    }

    public final int a(Object obj, Object obj2) {
        int iSizeOf = sizeOf(obj, obj2);
        if (iSizeOf >= 0) {
            return iSizeOf;
        }
        throw new IllegalStateException(("Negative size: " + obj + '=' + obj2).toString());
    }

    public Object create(Object obj) {
        return null;
    }

    public final int createCount() {
        int i10;
        synchronized (this.lock) {
            i10 = this.createCount;
        }
        return i10;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final int evictionCount() {
        int i10;
        synchronized (this.lock) {
            i10 = this.evictionCount;
        }
        return i10;
    }

    public final Object get(Object obj) {
        Object objPut;
        synchronized (this.lock) {
            Object obj2 = this.map.f22011a.get(obj);
            if (obj2 != null) {
                this.hitCount++;
                return obj2;
            }
            this.missCount++;
            Object objCreate = create(obj);
            if (objCreate == null) {
                return null;
            }
            synchronized (this.lock) {
                try {
                    this.createCount++;
                    objPut = this.map.f22011a.put(obj, objCreate);
                    if (objPut != null) {
                        this.map.f22011a.put(obj, objPut);
                    } else {
                        this.size += a(obj, objCreate);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (objPut != null) {
                entryRemoved(false, obj, objCreate, objPut);
                return objPut;
            }
            trimToSize(this.maxSize);
            return objCreate;
        }
    }

    public final int hitCount() {
        int i10;
        synchronized (this.lock) {
            i10 = this.hitCount;
        }
        return i10;
    }

    public final int maxSize() {
        int i10;
        synchronized (this.lock) {
            i10 = this.maxSize;
        }
        return i10;
    }

    public final int missCount() {
        int i10;
        synchronized (this.lock) {
            i10 = this.missCount;
        }
        return i10;
    }

    public final Object put(Object obj, Object obj2) {
        Object objPut;
        synchronized (this.lock) {
            this.putCount++;
            this.size += a(obj, obj2);
            objPut = this.map.f22011a.put(obj, obj2);
            if (objPut != null) {
                this.size -= a(obj, objPut);
            }
        }
        if (objPut != null) {
            entryRemoved(false, obj, objPut, obj2);
        }
        trimToSize(this.maxSize);
        return objPut;
    }

    public final int putCount() {
        int i10;
        synchronized (this.lock) {
            i10 = this.putCount;
        }
        return i10;
    }

    public final Object remove(Object obj) {
        Object objRemove;
        synchronized (this.lock) {
            objRemove = this.map.f22011a.remove(obj);
            if (objRemove != null) {
                this.size -= a(obj, objRemove);
            }
        }
        if (objRemove != null) {
            entryRemoved(false, obj, objRemove, null);
        }
        return objRemove;
    }

    public void resize(int i10) {
        if (i10 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this.lock) {
            this.maxSize = i10;
        }
        trimToSize(i10);
    }

    public final int size() {
        int i10;
        synchronized (this.lock) {
            i10 = this.size;
        }
        return i10;
    }

    public int sizeOf(Object obj, Object obj2) {
        return 1;
    }

    public final Map<Object, Object> snapshot() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (this.lock) {
            for (Map.Entry entry : this.map.f22011a.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    public String toString() {
        String str;
        synchronized (this.lock) {
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

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L0:
            u.b r0 = r5.lock
            monitor-enter(r0)
            int r1 = r5.size     // Catch: java.lang.Throwable -> L16
            if (r1 < 0) goto L61
            u.c r1 = r5.map     // Catch: java.lang.Throwable -> L16
            java.util.LinkedHashMap r1 = r1.f22011a     // Catch: java.lang.Throwable -> L16
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto L18
            int r1 = r5.size     // Catch: java.lang.Throwable -> L16
            if (r1 != 0) goto L61
            goto L18
        L16:
            r6 = move-exception
            goto L69
        L18:
            int r1 = r5.size     // Catch: java.lang.Throwable -> L16
            if (r1 <= r6) goto L5f
            u.c r1 = r5.map     // Catch: java.lang.Throwable -> L16
            java.util.LinkedHashMap r1 = r1.f22011a     // Catch: java.lang.Throwable -> L16
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto L27
            goto L5f
        L27:
            u.c r1 = r5.map     // Catch: java.lang.Throwable -> L16
            java.util.LinkedHashMap r1 = r1.f22011a     // Catch: java.lang.Throwable -> L16
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L16
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L16
            java.lang.Object r1 = kotlin.collections.x.n0(r1)     // Catch: java.lang.Throwable -> L16
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L16
            if (r1 != 0) goto L3b
            monitor-exit(r0)
            return
        L3b:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L16
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L16
            u.c r3 = r5.map     // Catch: java.lang.Throwable -> L16
            java.util.LinkedHashMap r3 = r3.f22011a     // Catch: java.lang.Throwable -> L16
            r3.remove(r2)     // Catch: java.lang.Throwable -> L16
            int r3 = r5.size     // Catch: java.lang.Throwable -> L16
            int r4 = r5.a(r2, r1)     // Catch: java.lang.Throwable -> L16
            int r3 = r3 - r4
            r5.size = r3     // Catch: java.lang.Throwable -> L16
            int r3 = r5.evictionCount     // Catch: java.lang.Throwable -> L16
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch: java.lang.Throwable -> L16
            monitor-exit(r0)
            r0 = 0
            r5.entryRemoved(r4, r2, r1, r0)
            goto L0
        L5f:
            monitor-exit(r0)
            return
        L61:
            java.lang.String r6 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L16
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L16
            throw r1     // Catch: java.lang.Throwable -> L16
        L69:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: t.r.trimToSize(int):void");
    }

    public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
    }
}
