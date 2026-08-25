package j$.util;

import j$.util.Map;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements java.util.Map, Serializable, Map {
    private static final long serialVersionUID = 1978198479659022715L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final java.util.Map f18558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f18559b = this;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient java.util.Set f18560c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient java.util.Set f18561d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public transient java.util.Collection f18562e;

    public g(java.util.Map map) {
        this.f18558a = (java.util.Map) Objects.requireNonNull(map);
    }

    @Override // java.util.Map
    public final int size() {
        int size;
        synchronized (this.f18559b) {
            size = this.f18558a.size();
        }
        return size;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.f18559b) {
            zIsEmpty = this.f18558a.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        boolean zContainsKey;
        synchronized (this.f18559b) {
            zContainsKey = this.f18558a.containsKey(obj);
        }
        return zContainsKey;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        boolean zContainsValue;
        synchronized (this.f18559b) {
            zContainsValue = this.f18558a.containsValue(obj);
        }
        return zContainsValue;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        synchronized (this.f18559b) {
            obj2 = this.f18558a.get(obj);
        }
        return obj2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        Object objPut;
        synchronized (this.f18559b) {
            objPut = this.f18558a.put(obj, obj2);
        }
        return objPut;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        Object objRemove;
        synchronized (this.f18559b) {
            objRemove = this.f18558a.remove(obj);
        }
        return objRemove;
    }

    @Override // java.util.Map
    public final void putAll(java.util.Map map) {
        synchronized (this.f18559b) {
            this.f18558a.putAll(map);
        }
    }

    @Override // java.util.Map
    public final void clear() {
        synchronized (this.f18559b) {
            this.f18558a.clear();
        }
    }

    public static java.util.Set a(java.util.Set set, g gVar) {
        Constructor constructor = DesugarCollections.f18431e;
        if (constructor == null) {
            return Collections.synchronizedSet(set);
        }
        try {
            try {
                return (java.util.Set) constructor.newInstance(set, gVar);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e5) {
                e = e5;
                throw new Error("Unable to instantiate a synchronized list.", e);
            }
        } catch (IllegalAccessException e6) {
            e = e6;
            throw new Error("Unable to instantiate a synchronized list.", e);
        } catch (InvocationTargetException e10) {
            e = e10;
            throw new Error("Unable to instantiate a synchronized list.", e);
        }
    }

    @Override // java.util.Map
    public final java.util.Set keySet() {
        java.util.Set set;
        synchronized (this.f18559b) {
            try {
                if (this.f18560c == null) {
                    this.f18560c = a(this.f18558a.keySet(), this.f18559b);
                }
                set = this.f18560c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return set;
    }

    @Override // java.util.Map
    public final java.util.Set entrySet() {
        java.util.Set set;
        synchronized (this.f18559b) {
            try {
                if (this.f18561d == null) {
                    this.f18561d = a(this.f18558a.entrySet(), this.f18559b);
                }
                set = this.f18561d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return set;
    }

    @Override // java.util.Map
    public final java.util.Collection values() {
        java.util.Collection collection;
        java.util.Collection collectionSynchronizedCollection;
        synchronized (this.f18559b) {
            try {
                if (this.f18562e == null) {
                    java.util.Collection collectionValues = this.f18558a.values();
                    g gVar = this.f18559b;
                    Constructor constructor = DesugarCollections.f18430d;
                    if (constructor == null) {
                        collectionSynchronizedCollection = Collections.synchronizedCollection(collectionValues);
                    } else {
                        try {
                            collectionSynchronizedCollection = (java.util.Collection) constructor.newInstance(collectionValues, gVar);
                        } catch (IllegalAccessException e5) {
                            e = e5;
                            throw new Error("Unable to instantiate a synchronized list.", e);
                        } catch (InstantiationException e6) {
                            e = e6;
                            throw new Error("Unable to instantiate a synchronized list.", e);
                        } catch (InvocationTargetException e10) {
                            e = e10;
                            throw new Error("Unable to instantiate a synchronized list.", e);
                        }
                    }
                    this.f18562e = collectionSynchronizedCollection;
                }
                collection = this.f18562e;
            } finally {
            }
        }
        return collection;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        boolean zEquals;
        if (this == obj) {
            return true;
        }
        synchronized (this.f18559b) {
            zEquals = this.f18558a.equals(obj);
        }
        return zEquals;
    }

    @Override // java.util.Map
    public final int hashCode() {
        int iHashCode;
        synchronized (this.f18559b) {
            iHashCode = this.f18558a.hashCode();
        }
        return iHashCode;
    }

    public final String toString() {
        String string;
        synchronized (this.f18559b) {
            string = this.f18558a.toString();
        }
        return string;
    }

    @Override // java.util.Map, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object orDefault;
        synchronized (this.f18559b) {
            orDefault = Map.EL.getOrDefault(this.f18558a, obj, obj2);
        }
        return orDefault;
    }

    @Override // java.util.Map, j$.util.Map
    public final void forEach(BiConsumer biConsumer) {
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            if (map instanceof Map) {
                ((Map) map).forEach(biConsumer);
            } else if (map instanceof ConcurrentMap) {
                j$.com.android.tools.r8.a.h((ConcurrentMap) map, biConsumer);
            } else {
                Map.CC.$default$forEach(map, biConsumer);
            }
        }
    }

    @Override // java.util.Map, j$.util.Map
    public final void replaceAll(BiFunction biFunction) {
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            if (map instanceof Map) {
                ((Map) map).replaceAll(biFunction);
            } else if (map instanceof ConcurrentMap) {
                ConcurrentMap concurrentMap = (ConcurrentMap) map;
                Objects.requireNonNull(biFunction);
                j$.util.concurrent.t tVar = new j$.util.concurrent.t(0, concurrentMap, biFunction);
                if (concurrentMap instanceof j$.util.concurrent.u) {
                    ((j$.util.concurrent.u) concurrentMap).forEach(tVar);
                } else {
                    j$.com.android.tools.r8.a.h(concurrentMap, tVar);
                }
            } else {
                Map.CC.$default$replaceAll(map, biFunction);
            }
        }
    }

    @Override // java.util.Map, j$.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        Object objPutIfAbsent;
        synchronized (this.f18559b) {
            objPutIfAbsent = Map.EL.putIfAbsent(this.f18558a, obj, obj2);
        }
        return objPutIfAbsent;
    }

    @Override // java.util.Map, j$.util.Map
    public final boolean remove(Object obj, Object obj2) {
        boolean zRemove;
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            zRemove = map instanceof Map ? ((Map) map).remove(obj, obj2) : Map.CC.$default$remove(map, obj, obj2);
        }
        return zRemove;
    }

    @Override // java.util.Map, j$.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        boolean zReplace;
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            zReplace = map instanceof Map ? ((Map) map).replace(obj, obj2, obj3) : Map.CC.$default$replace(map, obj, obj2, obj3);
        }
        return zReplace;
    }

    @Override // java.util.Map, j$.util.Map
    public final Object replace(Object obj, Object obj2) {
        Object objReplace;
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            objReplace = map instanceof Map ? ((Map) map).replace(obj, obj2) : Map.CC.$default$replace(map, obj, obj2);
        }
        return objReplace;
    }

    @Override // java.util.Map, j$.util.Map
    public final Object computeIfAbsent(Object obj, Function function) {
        Object obj$default$computeIfAbsent;
        Object objApply;
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            if (map instanceof Map) {
                obj$default$computeIfAbsent = ((Map) map).computeIfAbsent(obj, function);
            } else if (map instanceof ConcurrentMap) {
                ConcurrentMap concurrentMap = (ConcurrentMap) map;
                Objects.requireNonNull(function);
                Object obj2 = concurrentMap.get(obj);
                if (obj2 != null || (objApply = function.apply(obj)) == null) {
                    obj$default$computeIfAbsent = obj2;
                } else {
                    obj$default$computeIfAbsent = concurrentMap.putIfAbsent(obj, objApply);
                    if (obj$default$computeIfAbsent == null) {
                        obj$default$computeIfAbsent = objApply;
                    }
                }
            } else {
                obj$default$computeIfAbsent = Map.CC.$default$computeIfAbsent(map, obj, function);
            }
        }
        return obj$default$computeIfAbsent;
    }

    @Override // java.util.Map, j$.util.Map
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        Object obj$default$computeIfPresent;
        Object objApply;
        synchronized (this.f18559b) {
            java.util.Map map = this.f18558a;
            if (map instanceof Map) {
                obj$default$computeIfPresent = ((Map) map).computeIfPresent(obj, biFunction);
            } else if (map instanceof ConcurrentMap) {
                ConcurrentMap concurrentMap = (ConcurrentMap) map;
                Objects.requireNonNull(biFunction);
                while (true) {
                    Object obj2 = concurrentMap.get(obj);
                    if (obj2 == null) {
                        obj$default$computeIfPresent = null;
                        break;
                    }
                    objApply = biFunction.apply(obj, obj2);
                    if (objApply == null) {
                        if (concurrentMap.remove(obj, obj2)) {
                            break;
                        }
                    } else if (concurrentMap.replace(obj, obj2, objApply)) {
                        break;
                    }
                }
                obj$default$computeIfPresent = objApply;
            } else {
                obj$default$computeIfPresent = Map.CC.$default$computeIfPresent(map, obj, biFunction);
            }
        }
        return obj$default$computeIfPresent;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
    
        r5 = r3;
     */
    @Override // java.util.Map, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object compute(java.lang.Object r5, java.util.function.BiFunction r6) {
        /*
            r4 = this;
            j$.util.g r0 = r4.f18559b
            monitor-enter(r0)
            java.util.Map r1 = r4.f18558a     // Catch: java.lang.Throwable -> L41
            boolean r2 = r1 instanceof j$.util.Map     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L10
            j$.util.Map r1 = (j$.util.Map) r1     // Catch: java.lang.Throwable -> L41
            java.lang.Object r5 = r1.compute(r5, r6)     // Catch: java.lang.Throwable -> L41
            goto L3f
        L10:
            boolean r2 = r1 instanceof java.util.concurrent.ConcurrentMap     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L3b
            java.util.concurrent.ConcurrentMap r1 = (java.util.concurrent.ConcurrentMap) r1     // Catch: java.lang.Throwable -> L41
        L16:
            java.lang.Object r2 = r1.get(r5)     // Catch: java.lang.Throwable -> L41
        L1a:
            java.lang.Object r3 = r6.apply(r5, r2)     // Catch: java.lang.Throwable -> L41
            if (r3 == 0) goto L31
            if (r2 == 0) goto L29
            boolean r2 = r1.replace(r5, r2, r3)     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L16
            goto L2f
        L29:
            java.lang.Object r2 = r1.putIfAbsent(r5, r3)     // Catch: java.lang.Throwable -> L41
            if (r2 != 0) goto L1a
        L2f:
            r5 = r3
            goto L3f
        L31:
            if (r2 == 0) goto L39
            boolean r2 = r1.remove(r5, r2)     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L16
        L39:
            r5 = 0
            goto L3f
        L3b:
            java.lang.Object r5 = j$.util.Map.CC.$default$compute(r1, r5, r6)     // Catch: java.lang.Throwable -> L41
        L3f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L41
            return r5
        L41:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L41
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.g.compute(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
    
        r3 = r7.apply(r2, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
    
        if (r3 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        if (r1.replace(r5, r2, r3) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        r6 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
    
        if (r1.remove(r5, r2) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
    
        r6 = null;
     */
    @Override // java.util.Map, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object merge(java.lang.Object r5, java.lang.Object r6, java.util.function.BiFunction r7) {
        /*
            r4 = this;
            j$.util.g r0 = r4.f18559b
            monitor-enter(r0)
            java.util.Map r1 = r4.f18558a     // Catch: java.lang.Throwable -> L45
            boolean r2 = r1 instanceof j$.util.Map     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L10
            j$.util.Map r1 = (j$.util.Map) r1     // Catch: java.lang.Throwable -> L45
            java.lang.Object r6 = r1.merge(r5, r6, r7)     // Catch: java.lang.Throwable -> L45
            goto L43
        L10:
            boolean r2 = r1 instanceof java.util.concurrent.ConcurrentMap     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L3f
            java.util.concurrent.ConcurrentMap r1 = (java.util.concurrent.ConcurrentMap) r1     // Catch: java.lang.Throwable -> L45
            j$.util.Objects.requireNonNull(r7)     // Catch: java.lang.Throwable -> L45
            j$.util.Objects.requireNonNull(r6)     // Catch: java.lang.Throwable -> L45
        L1c:
            java.lang.Object r2 = r1.get(r5)     // Catch: java.lang.Throwable -> L45
        L20:
            if (r2 == 0) goto L38
            java.lang.Object r3 = r7.apply(r2, r6)     // Catch: java.lang.Throwable -> L45
            if (r3 == 0) goto L30
            boolean r2 = r1.replace(r5, r2, r3)     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L1c
            r6 = r3
            goto L43
        L30:
            boolean r2 = r1.remove(r5, r2)     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L1c
            r6 = 0
            goto L43
        L38:
            java.lang.Object r2 = r1.putIfAbsent(r5, r6)     // Catch: java.lang.Throwable -> L45
            if (r2 != 0) goto L20
            goto L43
        L3f:
            java.lang.Object r6 = j$.util.Map.CC.$default$merge(r1, r5, r6, r7)     // Catch: java.lang.Throwable -> L45
        L43:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            return r6
        L45:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.g.merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        synchronized (this.f18559b) {
            objectOutputStream.defaultWriteObject();
        }
    }
}
