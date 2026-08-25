package j$.util.concurrent;

import io.ktor.network.sockets.DatagramKt;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: classes2.dex */
public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable, u {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f18456g = Runtime.getRuntime().availableProcessors();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final j$.sun.misc.a f18457h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long f18458i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final long f18459j;
    public static final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final long f18460l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final long f18461m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int f18462n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int f18463o;
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 7249069246763182397L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile transient l[] f18464a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile transient l[] f18465b;
    private volatile transient long baseCount;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile transient c[] f18466c;
    private volatile transient int cellsBusy;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient i f18467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public transient s f18468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient e f18469f;
    private volatile transient int sizeCtl;
    private volatile transient int transferIndex;

    public static final int i(int i10) {
        return (i10 ^ (i10 >>> 16)) & Integer.MAX_VALUE;
    }

    static {
        Class cls = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{new ObjectStreamField("segments", n[].class), new ObjectStreamField("segmentMask", cls), new ObjectStreamField("segmentShift", cls)};
        j$.sun.misc.a aVar = j$.sun.misc.a.f18086b;
        f18457h = aVar;
        f18458i = aVar.h(ConcurrentHashMap.class, "sizeCtl");
        f18459j = aVar.h(ConcurrentHashMap.class, "transferIndex");
        k = aVar.h(ConcurrentHashMap.class, "baseCount");
        f18460l = aVar.h(ConcurrentHashMap.class, "cellsBusy");
        f18461m = aVar.h(c.class, "value");
        f18462n = aVar.a(l[].class);
        int iB = aVar.b(l[].class);
        if (((iB - 1) & iB) != 0) {
            throw new ExceptionInInitializerError("array index scale not a power of two");
        }
        f18463o = 31 - Integer.numberOfLeadingZeros(iB);
    }

    public static final int l(int i10) {
        int iNumberOfLeadingZeros = (-1) >>> Integer.numberOfLeadingZeros(i10 - 1);
        if (iNumberOfLeadingZeros < 0) {
            return 1;
        }
        if (iNumberOfLeadingZeros >= 1073741824) {
            return 1073741824;
        }
        return iNumberOfLeadingZeros + 1;
    }

    public static Class c(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls != String.class) {
            Type[] genericInterfaces = cls.getGenericInterfaces();
            if (genericInterfaces == null) {
                return null;
            }
            for (Type type : genericInterfaces) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (parameterizedType.getRawType() != Comparable.class || (actualTypeArguments = parameterizedType.getActualTypeArguments()) == null || actualTypeArguments.length != 1 || actualTypeArguments[0] != cls) {
                    }
                }
            }
            return null;
        }
        return cls;
    }

    public static final l k(l[] lVarArr, int i10) {
        return (l) f18457h.f(lVarArr, (((long) i10) << f18463o) + ((long) f18462n));
    }

    public static final boolean b(l[] lVarArr, int i10, l lVar) {
        j$.sun.misc.a aVar = f18457h;
        return j$.com.android.tools.r8.a.O(aVar.f18087a, lVarArr, (((long) i10) << f18463o) + ((long) f18462n), lVar);
    }

    public static final void h(l[] lVarArr, int i10, l lVar) {
        f18457h.j(lVarArr, (((long) i10) << f18463o) + ((long) f18462n), lVar);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i10) {
        this(i10, 0.75f, 1);
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> map) {
        this.sizeCtl = 16;
        putAll(map);
    }

    public ConcurrentHashMap(int i10, float f10, int i11) {
        if (f10 <= 0.0f || i10 < 0 || i11 <= 0) {
            throw new IllegalArgumentException();
        }
        long j10 = (long) (((double) ((i10 < i11 ? i11 : i10) / f10)) + 1.0d);
        this.sizeCtl = j10 >= 1073741824 ? 1073741824 : l((int) j10);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long j10 = j();
        if (j10 < 0) {
            return 0;
        }
        if (j10 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j10;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return j() <= 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int length;
        l lVarK;
        Object obj2;
        int i10 = i(obj.hashCode());
        l[] lVarArr = this.f18464a;
        if (lVarArr == null || (length = lVarArr.length) <= 0 || (lVarK = k(lVarArr, (length - 1) & i10)) == null) {
            return null;
        }
        int i11 = lVarK.f18488a;
        if (i11 == i10) {
            Object obj3 = lVarK.f18489b;
            if (obj3 == obj || (obj3 != null && obj.equals(obj3))) {
                return (V) lVarK.f18490c;
            }
        } else if (i11 < 0) {
            l lVarA = lVarK.a(obj, i10);
            if (lVarA != null) {
                return (V) lVarA.f18490c;
            }
            return null;
        }
        while (true) {
            lVarK = lVarK.f18491d;
            if (lVarK == null) {
                return null;
            }
            if (lVarK.f18488a == i10 && ((obj2 = lVarK.f18489b) == obj || (obj2 != null && obj.equals(obj2)))) {
                break;
            }
        }
        return (V) lVarK.f18490c;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        obj.getClass();
        l[] lVarArr = this.f18464a;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                Object obj2 = lVarA.f18490c;
                if (obj2 == obj) {
                    return true;
                }
                if (obj2 != null && obj.equals(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k10, V v2) {
        return (V) f(k10, v2, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x006a, code lost:
    
        r7 = r6.f18490c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006c, code lost:
    
        if (r11 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006e, code lost:
    
        r6.f18490c = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00a5, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object f(java.lang.Object r9, java.lang.Object r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto Lc2
            if (r10 == 0) goto Lc2
            int r1 = r9.hashCode()
            int r1 = i(r1)
            j$.util.concurrent.l[] r2 = r8.f18464a
            r3 = 0
        L10:
            if (r2 == 0) goto Lbc
            int r4 = r2.length
            if (r4 != 0) goto L17
            goto Lbc
        L17:
            int r4 = r4 + (-1)
            r4 = r4 & r1
            j$.util.concurrent.l r5 = k(r2, r4)
            if (r5 != 0) goto L2d
            j$.util.concurrent.l r5 = new j$.util.concurrent.l
            r5.<init>(r1, r9, r10)
            boolean r4 = b(r2, r4, r5)
            if (r4 == 0) goto L10
            goto Lb4
        L2d:
            int r6 = r5.f18488a
            r7 = -1
            if (r6 != r7) goto L37
            j$.util.concurrent.l[] r2 = r8.d(r2, r5)
            goto L10
        L37:
            if (r11 == 0) goto L4c
            if (r6 != r1) goto L4c
            java.lang.Object r7 = r5.f18489b
            if (r7 == r9) goto L47
            if (r7 == 0) goto L4c
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto L4c
        L47:
            java.lang.Object r7 = r5.f18490c
            if (r7 == 0) goto L4c
            return r7
        L4c:
            monitor-enter(r5)
            j$.util.concurrent.l r7 = k(r2, r4)     // Catch: java.lang.Throwable -> L68
            if (r7 != r5) goto La6
            if (r6 < 0) goto L81
            r3 = 1
            r6 = r5
        L57:
            int r7 = r6.f18488a     // Catch: java.lang.Throwable -> L68
            if (r7 != r1) goto L71
            java.lang.Object r7 = r6.f18489b     // Catch: java.lang.Throwable -> L68
            if (r7 == r9) goto L6a
            if (r7 == 0) goto L71
            boolean r7 = r9.equals(r7)     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L71
            goto L6a
        L68:
            r9 = move-exception
            goto Lba
        L6a:
            java.lang.Object r7 = r6.f18490c     // Catch: java.lang.Throwable -> L68
            if (r11 != 0) goto La7
            r6.f18490c = r10     // Catch: java.lang.Throwable -> L68
            goto La7
        L71:
            j$.util.concurrent.l r7 = r6.f18491d     // Catch: java.lang.Throwable -> L68
            if (r7 != 0) goto L7d
            j$.util.concurrent.l r7 = new j$.util.concurrent.l     // Catch: java.lang.Throwable -> L68
            r7.<init>(r1, r9, r10)     // Catch: java.lang.Throwable -> L68
            r6.f18491d = r7     // Catch: java.lang.Throwable -> L68
            goto La6
        L7d:
            int r3 = r3 + 1
            r6 = r7
            goto L57
        L81:
            boolean r6 = r5 instanceof j$.util.concurrent.q     // Catch: java.lang.Throwable -> L68
            if (r6 == 0) goto L99
            r3 = r5
            j$.util.concurrent.q r3 = (j$.util.concurrent.q) r3     // Catch: java.lang.Throwable -> L68
            j$.util.concurrent.r r3 = r3.e(r1, r9, r10)     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L96
            java.lang.Object r6 = r3.f18490c     // Catch: java.lang.Throwable -> L68
            if (r11 != 0) goto L94
            r3.f18490c = r10     // Catch: java.lang.Throwable -> L68
        L94:
            r7 = r6
            goto L97
        L96:
            r7 = r0
        L97:
            r3 = 2
            goto La7
        L99:
            boolean r6 = r5 instanceof j$.util.concurrent.m     // Catch: java.lang.Throwable -> L68
            if (r6 != 0) goto L9e
            goto La6
        L9e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L68
            java.lang.String r10 = "Recursive update"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L68
            throw r9     // Catch: java.lang.Throwable -> L68
        La6:
            r7 = r0
        La7:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L10
            r9 = 8
            if (r3 < r9) goto Lb1
            r8.n(r2, r4)
        Lb1:
            if (r7 == 0) goto Lb4
            return r7
        Lb4:
            r9 = 1
            r8.a(r9, r3)
            return r0
        Lba:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L68
            throw r9
        Lbc:
            j$.util.concurrent.l[] r2 = r8.e()
            goto L10
        Lc2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.f(java.lang.Object, java.lang.Object, boolean):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        o(map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            f(entry.getKey(), entry.getValue(), false);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        return (V) g(obj, null, null);
    }

    public final Object g(Object obj, Object obj2, Object obj3) {
        int length;
        int i10;
        l lVarK;
        boolean z;
        Object obj4;
        r rVarB;
        Object obj5;
        int i11 = i(obj.hashCode());
        l[] lVarArrD = this.f18464a;
        while (true) {
            if (lVarArrD == null || (length = lVarArrD.length) == 0 || (lVarK = k(lVarArrD, (i10 = (length - 1) & i11))) == null) {
                break;
            }
            int i12 = lVarK.f18488a;
            if (i12 == -1) {
                lVarArrD = d(lVarArrD, lVarK);
            } else {
                synchronized (lVarK) {
                    try {
                        if (k(lVarArrD, i10) == lVarK) {
                            z = true;
                            if (i12 >= 0) {
                                l lVar = null;
                                l lVar2 = lVarK;
                                while (true) {
                                    if (lVar2.f18488a != i11 || ((obj5 = lVar2.f18489b) != obj && (obj5 == null || !obj.equals(obj5)))) {
                                        l lVar3 = lVar2.f18491d;
                                        if (lVar3 == null) {
                                            break;
                                        }
                                        lVar = lVar2;
                                        lVar2 = lVar3;
                                    }
                                }
                                obj4 = lVar2.f18490c;
                                if (obj3 != null && obj3 != obj4 && (obj4 == null || !obj3.equals(obj4))) {
                                    obj4 = null;
                                } else if (obj2 != null) {
                                    lVar2.f18490c = obj2;
                                } else if (lVar != null) {
                                    lVar.f18491d = lVar2.f18491d;
                                } else {
                                    h(lVarArrD, i10, lVar2.f18491d);
                                }
                            } else if (lVarK instanceof q) {
                                q qVar = (q) lVarK;
                                r rVar = qVar.f18506e;
                                if (rVar != null && (rVarB = rVar.b(i11, obj, null)) != null) {
                                    obj4 = rVarB.f18490c;
                                    if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                        if (obj2 != null) {
                                            rVarB.f18490c = obj2;
                                        } else if (qVar.f(rVarB)) {
                                            h(lVarArrD, i10, p(qVar.f18507f));
                                        }
                                    }
                                }
                                obj4 = null;
                            } else if (lVarK instanceof m) {
                                throw new IllegalStateException("Recursive update");
                            }
                        }
                        z = false;
                        obj4 = null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    if (obj4 != null) {
                        if (obj2 == null) {
                            a(-1L, -1);
                        }
                        return obj4;
                    }
                }
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        l lVarK;
        l lVar;
        l[] lVarArrD = this.f18464a;
        long j10 = 0;
        loop0: while (true) {
            int i10 = 0;
            while (lVarArrD != null && i10 < lVarArrD.length) {
                lVarK = k(lVarArrD, i10);
                if (lVarK == null) {
                    i10++;
                } else {
                    int i11 = lVarK.f18488a;
                    if (i11 == -1) {
                        break;
                    }
                    synchronized (lVarK) {
                        try {
                            if (k(lVarArrD, i10) == lVarK) {
                                if (i11 >= 0) {
                                    lVar = lVarK;
                                } else {
                                    lVar = lVarK instanceof q ? ((q) lVarK).f18507f : null;
                                }
                                while (lVar != null) {
                                    j10--;
                                    lVar = lVar.f18491d;
                                }
                                h(lVarArrD, i10, null);
                                i10++;
                            }
                        } finally {
                        }
                    }
                }
            }
            lVarArrD = d(lVarArrD, lVarK);
        }
        if (j10 != 0) {
            a(j10, -1);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        i iVar = this.f18467d;
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this);
        this.f18467d = iVar2;
        return iVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        s sVar = this.f18468e;
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s(this);
        this.f18468e = sVar2;
        return sVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        e eVar = this.f18469f;
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e(this);
        this.f18469f = eVar2;
        return eVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        l[] lVarArr = this.f18464a;
        int iHashCode = 0;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                iHashCode += lVarA.f18490c.hashCode() ^ lVarA.f18489b.hashCode();
            }
        }
        return iHashCode;
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        l[] lVarArr = this.f18464a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        p pVar = new p(lVarArr, length, 0, length);
        StringBuilder sb2 = new StringBuilder("{");
        l lVarA = pVar.a();
        if (lVarA != null) {
            while (true) {
                Object obj = lVarA.f18489b;
                Object obj2 = lVarA.f18490c;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb2.append(obj);
                sb2.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb2.append(obj2);
                lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                sb2.append(", ");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        V value;
        V v2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        l[] lVarArr = this.f18464a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        p pVar = new p(lVarArr, length, 0, length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA != null) {
                Object obj2 = lVarA.f18490c;
                Object obj3 = map.get(lVarA.f18489b);
                if (obj3 == null || (obj3 != obj2 && !obj3.equals(obj2))) {
                    break;
                }
            } else {
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    K key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (v2 = get(key)) == null || (value != v2 && !value.equals(v2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        int i10 = 0;
        int i11 = 1;
        while (i11 < 16) {
            i10++;
            i11 <<= 1;
        }
        int i12 = 32 - i10;
        int i13 = i11 - 1;
        n[] nVarArr = new n[16];
        for (int i14 = 0; i14 < 16; i14++) {
            nVarArr[i14] = new n();
        }
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("segments", nVarArr);
        putFieldPutFields.put("segmentShift", i12);
        putFieldPutFields.put("segmentMask", i13);
        objectOutputStream.writeFields();
        l[] lVarArr = this.f18464a;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                objectOutputStream.writeObject(lVarA.f18489b);
                objectOutputStream.writeObject(lVarA.f18490c);
            }
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.writeObject(null);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        long j10;
        long j11;
        Object obj;
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        long j12 = 0;
        long j13 = 0;
        l lVar = null;
        while (true) {
            Object object = objectInputStream.readObject();
            Object object2 = objectInputStream.readObject();
            j10 = 1;
            if (object == null || object2 == null) {
                break;
            }
            j13++;
            lVar = new l(i(object.hashCode()), object, object2, lVar);
        }
        if (j13 == 0) {
            this.sizeCtl = 0;
            return;
        }
        long j14 = (long) (((double) (j13 / 0.75f)) + 1.0d);
        int iL = j14 >= 1073741824 ? 1073741824 : l((int) j14);
        l[] lVarArr = new l[iL];
        int i10 = iL - 1;
        while (lVar != null) {
            l lVar2 = lVar.f18491d;
            int i11 = lVar.f18488a;
            int i12 = i11 & i10;
            l lVarK = k(lVarArr, i12);
            boolean z = true;
            if (lVarK == null) {
                j11 = j10;
            } else {
                Object obj2 = lVar.f18489b;
                if (lVarK.f18488a < 0) {
                    if (((q) lVarK).e(i11, obj2, lVar.f18490c) == null) {
                        j12 += j10;
                    }
                    j11 = j10;
                } else {
                    j11 = j10;
                    int i13 = 0;
                    for (l lVar3 = lVarK; lVar3 != null; lVar3 = lVar3.f18491d) {
                        if (lVar3.f18488a == i11 && ((obj = lVar3.f18489b) == obj2 || (obj != null && obj2.equals(obj)))) {
                            z = false;
                            break;
                        }
                        i13++;
                    }
                    if (z && i13 >= 8) {
                        j12 += j11;
                        lVar.f18491d = lVarK;
                        l lVar4 = lVar;
                        r rVar = null;
                        r rVar2 = null;
                        while (lVar4 != null) {
                            r rVar3 = new r(lVar4.f18488a, lVar4.f18489b, lVar4.f18490c, null, null);
                            rVar3.f18512h = rVar2;
                            if (rVar2 == null) {
                                rVar = rVar3;
                            } else {
                                rVar2.f18491d = rVar3;
                            }
                            lVar4 = lVar4.f18491d;
                            rVar2 = rVar3;
                        }
                        h(lVarArr, i12, new q(rVar));
                    }
                }
                z = false;
            }
            if (z) {
                j12 += j11;
                lVar.f18491d = lVarK;
                h(lVarArr, i12, lVar);
            }
            lVar = lVar2;
            j10 = j11;
        }
        this.f18464a = lVarArr;
        this.sizeCtl = iL - (iL >>> 2);
        this.baseCount = j12;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public V putIfAbsent(K k10, V v2) {
        return (V) f(k10, v2, true);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public boolean remove(Object obj, Object obj2) {
        obj.getClass();
        return (obj2 == null || g(obj, null, obj2) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        if (obj == null || obj2 == null || obj3 == null) {
            throw null;
        }
        return g(obj, obj3, obj2) != null;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final Object replace(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            throw null;
        }
        return g(obj, obj2, null);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        V v2 = get(obj);
        return v2 == null ? obj2 : v2;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        l[] lVarArr = this.f18464a;
        if (lVarArr == null) {
            return;
        }
        p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA == null) {
                return;
            } else {
                biConsumer.accept(lVarA.f18489b, lVarA.f18490c);
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        l[] lVarArr = this.f18464a;
        if (lVarArr == null) {
            return;
        }
        p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA == null) {
                return;
            }
            Object obj = lVarA.f18490c;
            Object obj2 = lVarA.f18489b;
            do {
                Object objApply = biFunction.apply(obj2, obj);
                objApply.getClass();
                if (g(obj2, objApply, obj) == null) {
                    obj = get(obj2);
                }
            } while (obj != null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x008c, code lost:
    
        r5 = r5.f18490c;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object computeIfAbsent(java.lang.Object r12, java.util.function.Function r13) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.computeIfAbsent(java.lang.Object, java.util.function.Function):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00aa, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object computeIfPresent(java.lang.Object r14, java.util.function.BiFunction r15) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto Lbd
            if (r15 == 0) goto Lbd
            int r1 = r14.hashCode()
            int r1 = i(r1)
            j$.util.concurrent.l[] r2 = r13.f18464a
            r3 = 0
            r5 = r0
            r4 = r3
        L12:
            if (r2 == 0) goto Lb7
            int r6 = r2.length
            if (r6 != 0) goto L19
            goto Lb7
        L19:
            int r6 = r6 + (-1)
            r6 = r6 & r1
            j$.util.concurrent.l r7 = k(r2, r6)
            if (r7 != 0) goto L24
            goto Lae
        L24:
            int r8 = r7.f18488a
            r9 = -1
            if (r8 != r9) goto L2e
            j$.util.concurrent.l[] r2 = r13.d(r2, r7)
            goto L12
        L2e:
            monitor-enter(r7)
            j$.util.concurrent.l r10 = k(r2, r6)     // Catch: java.lang.Throwable -> L4b
            if (r10 != r7) goto Lab
            if (r8 < 0) goto L70
            r4 = 1
            r10 = r0
            r8 = r7
        L3a:
            int r11 = r8.f18488a     // Catch: java.lang.Throwable -> L4b
            if (r11 != r1) goto L65
            java.lang.Object r11 = r8.f18489b     // Catch: java.lang.Throwable -> L4b
            if (r11 == r14) goto L4e
            if (r11 == 0) goto L65
            boolean r11 = r14.equals(r11)     // Catch: java.lang.Throwable -> L4b
            if (r11 == 0) goto L65
            goto L4e
        L4b:
            r14 = move-exception
            goto Lb5
        L4e:
            java.lang.Object r5 = r8.f18490c     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r5 = r15.apply(r14, r5)     // Catch: java.lang.Throwable -> L4b
            if (r5 == 0) goto L59
            r8.f18490c = r5     // Catch: java.lang.Throwable -> L4b
            goto Lab
        L59:
            j$.util.concurrent.l r3 = r8.f18491d     // Catch: java.lang.Throwable -> L4b
            if (r10 == 0) goto L60
            r10.f18491d = r3     // Catch: java.lang.Throwable -> L4b
            goto L63
        L60:
            h(r2, r6, r3)     // Catch: java.lang.Throwable -> L4b
        L63:
            r3 = r9
            goto Lab
        L65:
            j$.util.concurrent.l r10 = r8.f18491d     // Catch: java.lang.Throwable -> L4b
            if (r10 != 0) goto L6a
            goto Lab
        L6a:
            int r4 = r4 + 1
            r12 = r10
            r10 = r8
            r8 = r12
            goto L3a
        L70:
            boolean r8 = r7 instanceof j$.util.concurrent.q     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9e
            r4 = r7
            j$.util.concurrent.q r4 = (j$.util.concurrent.q) r4     // Catch: java.lang.Throwable -> L4b
            j$.util.concurrent.r r8 = r4.f18506e     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9c
            j$.util.concurrent.r r8 = r8.b(r1, r14, r0)     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9c
            java.lang.Object r5 = r8.f18490c     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r5 = r15.apply(r14, r5)     // Catch: java.lang.Throwable -> L4b
            if (r5 == 0) goto L8c
            r8.f18490c = r5     // Catch: java.lang.Throwable -> L4b
            goto L9c
        L8c:
            boolean r3 = r4.f(r8)     // Catch: java.lang.Throwable -> L4b
            if (r3 == 0) goto L9b
            j$.util.concurrent.r r3 = r4.f18507f     // Catch: java.lang.Throwable -> L4b
            j$.util.concurrent.l r3 = p(r3)     // Catch: java.lang.Throwable -> L4b
            h(r2, r6, r3)     // Catch: java.lang.Throwable -> L4b
        L9b:
            r3 = r9
        L9c:
            r4 = 2
            goto Lab
        L9e:
            boolean r6 = r7 instanceof j$.util.concurrent.m     // Catch: java.lang.Throwable -> L4b
            if (r6 != 0) goto La3
            goto Lab
        La3:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L4b
            java.lang.String r15 = "Recursive update"
            r14.<init>(r15)     // Catch: java.lang.Throwable -> L4b
            throw r14     // Catch: java.lang.Throwable -> L4b
        Lab:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L12
        Lae:
            if (r3 == 0) goto Lb4
            long r14 = (long) r3
            r13.a(r14, r4)
        Lb4:
            return r5
        Lb5:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4b
            throw r14
        Lb7:
            j$.util.concurrent.l[] r2 = r13.e()
            goto L12
        Lbd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.computeIfPresent(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final Object compute(Object obj, BiFunction biFunction) {
        l lVar;
        Object objApply;
        Object obj2;
        if (obj == null || biFunction == null) {
            throw null;
        }
        int i10 = i(obj.hashCode());
        l[] lVarArrE = this.f18464a;
        int i11 = 0;
        Object objApply2 = null;
        int i12 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i13 = (length - 1) & i10;
                    l lVarK = k(lVarArrE, i13);
                    if (lVarK == null) {
                        m mVar = new m();
                        synchronized (mVar) {
                            try {
                                if (b(lVarArrE, i13, mVar)) {
                                    try {
                                        objApply2 = biFunction.apply(obj, null);
                                        if (objApply2 != null) {
                                            lVar = new l(i10, obj, objApply2);
                                            i12 = 1;
                                        } else {
                                            lVar = null;
                                        }
                                        h(lVarArrE, i13, lVar);
                                        i11 = 1;
                                    } catch (Throwable th) {
                                        h(lVarArrE, i13, null);
                                        throw th;
                                    }
                                }
                            } finally {
                            }
                        }
                        if (i11 != 0) {
                        }
                    } else {
                        int i14 = lVarK.f18488a;
                        if (i14 == -1) {
                            lVarArrE = d(lVarArrE, lVarK);
                        } else {
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArrE, i13) == lVarK) {
                                        if (i14 >= 0) {
                                            l lVar2 = null;
                                            l lVar3 = lVarK;
                                            i11 = 1;
                                            while (true) {
                                                if (lVar3.f18488a == i10 && ((obj2 = lVar3.f18489b) == obj || (obj2 != null && obj.equals(obj2)))) {
                                                    break;
                                                }
                                                l lVar4 = lVar3.f18491d;
                                                if (lVar4 == null) {
                                                    objApply = biFunction.apply(obj, null);
                                                    if (objApply != null) {
                                                        if (lVar3.f18491d != null) {
                                                            throw new IllegalStateException("Recursive update");
                                                        }
                                                        lVar3.f18491d = new l(i10, obj, objApply);
                                                        i12 = 1;
                                                    }
                                                } else {
                                                    i11++;
                                                    lVar2 = lVar3;
                                                    lVar3 = lVar4;
                                                }
                                            }
                                            Object objApply3 = biFunction.apply(obj, lVar3.f18490c);
                                            if (objApply3 != null) {
                                                lVar3.f18490c = objApply3;
                                                objApply2 = objApply3;
                                            } else {
                                                l lVar5 = lVar3.f18491d;
                                                if (lVar2 != null) {
                                                    lVar2.f18491d = lVar5;
                                                } else {
                                                    h(lVarArrE, i13, lVar5);
                                                }
                                                objApply2 = objApply3;
                                                i12 = -1;
                                            }
                                        } else if (lVarK instanceof q) {
                                            q qVar = (q) lVarK;
                                            r rVar = qVar.f18506e;
                                            r rVarB = rVar != null ? rVar.b(i10, obj, null) : null;
                                            objApply = biFunction.apply(obj, rVarB == null ? null : rVarB.f18490c);
                                            if (objApply != null) {
                                                if (rVarB != null) {
                                                    rVarB.f18490c = objApply;
                                                } else {
                                                    qVar.e(i10, obj, objApply);
                                                    i12 = 1;
                                                }
                                            } else if (rVarB != null) {
                                                if (qVar.f(rVarB)) {
                                                    h(lVarArrE, i13, p(qVar.f18507f));
                                                }
                                                i12 = -1;
                                            }
                                            i11 = 1;
                                            objApply2 = objApply;
                                        } else if (lVarK instanceof m) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                } finally {
                                }
                            }
                            if (i11 != 0) {
                                if (i11 >= 8) {
                                    n(lVarArrE, i13);
                                }
                            }
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        if (i12 != 0) {
            a(i12, i11);
        }
        return objApply2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x00dd, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object merge(java.lang.Object r18, java.lang.Object r19, java.util.function.BiFunction r20) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    public Enumeration<K> keys() {
        l[] lVarArr = this.f18464a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        return new h(lVarArr, length, length, this, 0);
    }

    public final l[] e() {
        while (true) {
            l[] lVarArr = this.f18464a;
            if (lVarArr != null && lVarArr.length != 0) {
                return lVarArr;
            }
            int i10 = this.sizeCtl;
            if (i10 < 0) {
                Thread.yield();
            } else if (f18457h.c(this, f18458i, i10, -1)) {
                try {
                    l[] lVarArr2 = this.f18464a;
                    if (lVarArr2 == null || lVarArr2.length == 0) {
                        int i11 = i10 > 0 ? i10 : 16;
                        l[] lVarArr3 = new l[i11];
                        this.f18464a = lVarArr3;
                        i10 = i11 - (i11 >>> 2);
                        lVarArr2 = lVarArr3;
                    }
                    this.sizeCtl = i10;
                    return lVarArr2;
                } catch (Throwable th) {
                    this.sizeCtl = i10;
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:97:0x0140, code lost:
    
        if (r1.f18466c != r6) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0142, code lost:
    
        r1.f18466c = (j$.util.concurrent.c[]) java.util.Arrays.copyOf(r6, r7 << 1);
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(long r25, int r27) {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.a(long, int):void");
    }

    public final l[] d(l[] lVarArr, l lVar) {
        int i10;
        if (lVar instanceof g) {
            l[] lVarArr2 = ((g) lVar).f18482e;
            int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(lVarArr.length) | 32768;
            while (lVarArr2 == this.f18465b && this.f18464a == lVarArr && (i10 = this.sizeCtl) < 0 && (i10 >>> 16) == iNumberOfLeadingZeros && i10 != iNumberOfLeadingZeros + 1 && i10 != DatagramKt.MAX_DATAGRAM_SIZE + iNumberOfLeadingZeros && this.transferIndex > 0) {
                if (f18457h.c(this, f18458i, i10, i10 + 1)) {
                    m(lVarArr, lVarArr2);
                    break;
                }
            }
            return lVarArr2;
        }
        return this.f18464a;
    }

    public final void o(int i10) {
        int length;
        int iL = i10 >= 536870912 ? 1073741824 : l(i10 + (i10 >>> 1) + 1);
        while (true) {
            int i11 = this.sizeCtl;
            if (i11 < 0) {
                break;
            }
            l[] lVarArr = this.f18464a;
            if (lVarArr == null || (length = lVarArr.length) == 0) {
                int i12 = i11 > iL ? i11 : iL;
                if (f18457h.c(this, f18458i, i11, -1)) {
                    try {
                        if (this.f18464a == lVarArr) {
                            this.f18464a = new l[i12];
                            i11 = i12 - (i12 >>> 2);
                        }
                    } finally {
                        this.sizeCtl = i11;
                    }
                } else {
                    continue;
                }
            } else if (iL <= i11 || length >= 1073741824) {
                break;
            } else if (lVarArr == this.f18464a) {
                if (f18457h.c(this, f18458i, i11, ((Integer.numberOfLeadingZeros(length) | 32768) << 16) + 2)) {
                    m(lVarArr, null);
                }
            }
        }
    }

    public final void m(l[] lVarArr, l[] lVarArr2) {
        l[] lVarArr3;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z;
        char c10;
        int i14;
        int i15;
        l qVar;
        l qVar2;
        l lVar;
        int i16;
        ConcurrentHashMap<K, V> concurrentHashMap = this;
        int length = lVarArr.length;
        int i17 = f18456g;
        boolean z5 = true;
        int i18 = i17 > 1 ? (length >>> 3) / i17 : length;
        char c11 = 16;
        int i19 = i18 < 16 ? 16 : i18;
        if (lVarArr2 == null) {
            try {
                l[] lVarArr4 = new l[length << 1];
                concurrentHashMap.f18465b = lVarArr4;
                concurrentHashMap.transferIndex = length;
                lVarArr3 = lVarArr4;
            } catch (Throwable unused) {
                concurrentHashMap.sizeCtl = Integer.MAX_VALUE;
                return;
            }
        } else {
            lVarArr3 = lVarArr2;
        }
        int length2 = lVarArr3.length;
        g gVar = new g(lVarArr3);
        boolean zB = true;
        int i20 = 0;
        int i21 = 0;
        boolean z10 = false;
        while (true) {
            if (zB) {
                int i22 = i20 - 1;
                if (i22 >= i21 || z10) {
                    i21 = i21;
                    i20 = i22;
                    zB = false;
                } else {
                    int i23 = concurrentHashMap.transferIndex;
                    if (i23 <= 0) {
                        i20 = -1;
                    } else {
                        j$.sun.misc.a aVar = f18457h;
                        int i24 = i21;
                        long j10 = f18459j;
                        if (i23 > i19) {
                            i11 = i24;
                            i12 = i23 - i19;
                            i10 = i22;
                        } else {
                            i10 = i22;
                            i11 = i24;
                            i12 = 0;
                        }
                        boolean zC = aVar.c(concurrentHashMap, j10, i23, i12);
                        i21 = i12;
                        if (zC) {
                            i20 = i23 - 1;
                        } else {
                            i21 = i11;
                            i20 = i10;
                        }
                    }
                    zB = false;
                }
            } else {
                int i25 = i21;
                r rVar = null;
                l lVar2 = null;
                if (i20 < 0 || i20 >= length || (i15 = i20 + length) >= length2) {
                    i13 = length;
                    z = z5;
                    c10 = c11;
                    i14 = i19;
                    if (z10) {
                        concurrentHashMap.f18465b = null;
                        concurrentHashMap.f18464a = lVarArr3;
                        concurrentHashMap.sizeCtl = (i13 << 1) - (i13 >>> 1);
                        return;
                    }
                    int i26 = i20;
                    j$.sun.misc.a aVar2 = f18457h;
                    long j11 = f18458i;
                    int i27 = concurrentHashMap.sizeCtl;
                    if (!aVar2.c(concurrentHashMap, j11, i27, i27 - 1)) {
                        i20 = i26;
                    } else {
                        if (i27 - 2 != ((Integer.numberOfLeadingZeros(i13) | 32768) << 16)) {
                            return;
                        }
                        zB = z;
                        z10 = zB;
                        i20 = i13;
                    }
                } else {
                    l lVarK = k(lVarArr, i20);
                    if (lVarK == null) {
                        zB = b(lVarArr, i20, gVar);
                        i13 = length;
                        z = z5;
                        c10 = c11;
                        i14 = i19;
                    } else {
                        z = z5;
                        int i28 = lVarK.f18488a;
                        if (i28 == -1) {
                            i13 = length;
                            c10 = c11;
                            i14 = i19;
                            zB = z;
                        } else {
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArr, i20) == lVarK) {
                                        if (i28 >= 0) {
                                            int i29 = i28 & length;
                                            l lVar3 = lVarK.f18491d;
                                            l lVar4 = lVarK;
                                            while (lVar3 != null) {
                                                char c12 = c11;
                                                int i30 = lVar3.f18488a & length;
                                                if (i30 != i29) {
                                                    lVar4 = lVar3;
                                                    i29 = i30;
                                                }
                                                lVar3 = lVar3.f18491d;
                                                c11 = c12;
                                            }
                                            c10 = c11;
                                            if (i29 == 0) {
                                                lVar = null;
                                                lVar2 = lVar4;
                                            } else {
                                                lVar = lVar4;
                                            }
                                            l lVar5 = lVarK;
                                            while (lVar5 != lVar4) {
                                                int i31 = lVar5.f18488a;
                                                Object obj = lVar5.f18489b;
                                                int i32 = length;
                                                Object obj2 = lVar5.f18490c;
                                                if ((i31 & i32) == 0) {
                                                    i16 = i19;
                                                    lVar2 = new l(i31, obj, obj2, lVar2);
                                                } else {
                                                    i16 = i19;
                                                    lVar = new l(i31, obj, obj2, lVar);
                                                }
                                                lVar5 = lVar5.f18491d;
                                                length = i32;
                                                i19 = i16;
                                            }
                                            i13 = length;
                                            i14 = i19;
                                            h(lVarArr3, i20, lVar2);
                                            h(lVarArr3, i15, lVar);
                                            h(lVarArr, i20, gVar);
                                        } else {
                                            i13 = length;
                                            c10 = c11;
                                            i14 = i19;
                                            if (lVarK instanceof q) {
                                                q qVar3 = (q) lVarK;
                                                r rVar2 = null;
                                                r rVar3 = null;
                                                l lVar6 = qVar3.f18507f;
                                                int i33 = 0;
                                                int i34 = 0;
                                                r rVar4 = null;
                                                while (lVar6 != null) {
                                                    q qVar4 = qVar3;
                                                    int i35 = lVar6.f18488a;
                                                    r rVar5 = new r(i35, lVar6.f18489b, lVar6.f18490c, null, null);
                                                    if ((i35 & i13) == 0) {
                                                        rVar5.f18512h = rVar3;
                                                        if (rVar3 == null) {
                                                            rVar = rVar5;
                                                        } else {
                                                            rVar3.f18491d = rVar5;
                                                        }
                                                        i33++;
                                                        rVar3 = rVar5;
                                                    } else {
                                                        rVar5.f18512h = rVar2;
                                                        if (rVar2 == null) {
                                                            rVar4 = rVar5;
                                                        } else {
                                                            rVar2.f18491d = rVar5;
                                                        }
                                                        i34++;
                                                        rVar2 = rVar5;
                                                    }
                                                    lVar6 = lVar6.f18491d;
                                                    qVar3 = qVar4;
                                                }
                                                q qVar5 = qVar3;
                                                if (i33 <= 6) {
                                                    qVar = p(rVar);
                                                } else {
                                                    qVar = i34 != 0 ? new q(rVar) : qVar5;
                                                }
                                                if (i34 <= 6) {
                                                    qVar2 = p(rVar4);
                                                } else {
                                                    qVar2 = i33 != 0 ? new q(rVar4) : qVar5;
                                                }
                                                h(lVarArr3, i20, qVar);
                                                h(lVarArr3, i15, qVar2);
                                                h(lVarArr, i20, gVar);
                                            }
                                        }
                                        zB = z;
                                    } else {
                                        i13 = length;
                                        c10 = c11;
                                        i14 = i19;
                                    }
                                } finally {
                                }
                            }
                        }
                    }
                }
                concurrentHashMap = this;
                i21 = i25;
                z5 = z;
                c11 = c10;
                length = i13;
                i19 = i14;
            }
        }
    }

    public final long j() {
        c[] cVarArr = this.f18466c;
        long j10 = this.baseCount;
        if (cVarArr != null) {
            for (c cVar : cVarArr) {
                if (cVar != null) {
                    j10 += cVar.value;
                }
            }
        }
        return j10;
    }

    public final void n(l[] lVarArr, int i10) {
        int length = lVarArr.length;
        if (length < 64) {
            o(length << 1);
            return;
        }
        l lVarK = k(lVarArr, i10);
        if (lVarK == null || lVarK.f18488a < 0) {
            return;
        }
        synchronized (lVarK) {
            try {
                if (k(lVarArr, i10) == lVarK) {
                    r rVar = null;
                    r rVar2 = null;
                    l lVar = lVarK;
                    while (lVar != null) {
                        r rVar3 = new r(lVar.f18488a, lVar.f18489b, lVar.f18490c, null, null);
                        rVar3.f18512h = rVar2;
                        if (rVar2 == null) {
                            rVar = rVar3;
                        } else {
                            rVar2.f18491d = rVar3;
                        }
                        lVar = lVar.f18491d;
                        rVar2 = rVar3;
                    }
                    h(lVarArr, i10, new q(rVar));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static l p(r rVar) {
        l lVar = null;
        l lVar2 = null;
        for (l lVar3 = rVar; lVar3 != null; lVar3 = lVar3.f18491d) {
            l lVar4 = new l(lVar3.f18488a, lVar3.f18489b, lVar3.f18490c);
            if (lVar2 == null) {
                lVar = lVar4;
            } else {
                lVar2.f18491d = lVar4;
            }
            lVar2 = lVar4;
        }
        return lVar;
    }
}
