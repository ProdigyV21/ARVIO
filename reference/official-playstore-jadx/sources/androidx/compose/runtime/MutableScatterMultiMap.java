package androidx.compose.runtime;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import t.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083@\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\n2\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0014\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0018\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001d\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 \u0088\u0001\u0005\u0092\u0001\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u0004¨\u0006!"}, d2 = {"Landroidx/compose/runtime/MutableScatterMultiMap;", "K", "", "V", "Lt/z;", "map", "constructor-impl", "(Lt/z;)Lt/z;", "key", "value", "Lx6/t0;", "put-impl", "(Lt/z;Ljava/lang/Object;Ljava/lang/Object;)V", "put", "pop-impl", "(Lt/z;Ljava/lang/Object;)Ljava/lang/Object;", "pop", "", "toString-impl", "(Lt/z;)Ljava/lang/String;", "toString", "", "hashCode-impl", "(Lt/z;)I", "hashCode", "other", "", "equals-impl", "(Lt/z;Ljava/lang/Object;)Z", "equals", "Lt/z;", "getMap", "()Lt/z;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
final class MutableScatterMultiMap<K, V> {
    private final z map;

    private /* synthetic */ MutableScatterMultiMap(z zVar) {
        this.map = zVar;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ MutableScatterMultiMap m2965boximpl(z zVar) {
        return new MutableScatterMultiMap(zVar);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2967equalsimpl(z zVar, Object obj) {
        return (obj instanceof MutableScatterMultiMap) && p.a(zVar, ((MutableScatterMultiMap) obj).m2973unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2968equalsimpl0(z zVar, z zVar2) {
        return p.a(zVar, zVar2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2969hashCodeimpl(z zVar) {
        return zVar.hashCode();
    }

    /* JADX INFO: renamed from: pop-impl, reason: not valid java name */
    public static final V m2970popimpl(z zVar, K k) {
        V v2 = (V) zVar.e(k);
        if (v2 == null) {
            return null;
        }
        if (!q0.g(v2)) {
            zVar.h(k);
            return v2;
        }
        List listB = q0.b(v2);
        V v10 = (V) listB.remove(0);
        if (listB.isEmpty()) {
            zVar.h(k);
        }
        return v10;
    }

    /* JADX INFO: renamed from: put-impl, reason: not valid java name */
    public static final void m2971putimpl(z zVar, K k, V v2) {
        int iD = zVar.d(k);
        boolean z = iD < 0;
        Object obj = z ? null : zVar.f21883c[iD];
        if (obj != null) {
            if (q0.g(obj)) {
                List listB = q0.b(obj);
                listB.add(v2);
                v2 = (V) listB;
            } else {
                v2 = (V) t7.a.G(obj, v2);
            }
        }
        if (!z) {
            zVar.f21883c[iD] = v2;
            return;
        }
        int i10 = ~iD;
        zVar.f21882b[i10] = k;
        zVar.f21883c[i10] = v2;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2972toStringimpl(z zVar) {
        return "MutableScatterMultiMap(map=" + zVar + ')';
    }

    public boolean equals(Object obj) {
        return m2967equalsimpl(this.map, obj);
    }

    public final z getMap() {
        return this.map;
    }

    public int hashCode() {
        return m2969hashCodeimpl(this.map);
    }

    public String toString() {
        return m2972toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ z m2973unboximpl() {
        return this.map;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <K, V> z m2966constructorimpl(z zVar) {
        return zVar;
    }
}
