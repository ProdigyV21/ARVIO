package androidx.compose.runtime.collection;

import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import r7.l;
import t.d0;
import t.h0;
import t.i0;
import t.z;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\f\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u000f\u0010\u0010J;\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0011H\u0086\b¢\u0006\u0004\b\u0015\u0010\u0016J;\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00028\u00002!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u000e0\u0011H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u0005J\u001d\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ5\u0010\u001d\u001a\u00020\b2#\b\u0004\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u000e0\u0011H\u0086\b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010#\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\"0!¢\u0006\u0004\b#\u0010$R#\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0011\u0010-\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,¨\u0006."}, d2 = {"Landroidx/compose/runtime/collection/ScopeMap;", "", "Key", "Scope", "<init>", "()V", "key", "scope", "Lx6/t0;", "add", "(Ljava/lang/Object;Ljava/lang/Object;)V", "value", "set", "element", "", "contains", "(Ljava/lang/Object;)Z", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "block", "forEachScopeOf", "(Ljava/lang/Object;Lr7/l;)V", "anyScopeOf", "(Ljava/lang/Object;Lr7/l;)Z", "clear", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "predicate", "removeScopeIf", "(Lr7/l;)V", "removeScope", "(Ljava/lang/Object;)V", "", "", "asMap", "()Ljava/util/Map;", "Lt/z;", "map", "Lt/z;", "getMap", "()Lt/z;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScopeMap<Key, Scope> {
    public static final int $stable = 8;
    private final z map;

    public ScopeMap() {
        long[] jArr = i0.f21833a;
        this.map = new z();
    }

    public final void add(Key key, Scope scope) {
        z zVar = this.map;
        int iD = zVar.d(key);
        boolean z = iD < 0;
        Object obj = z ? null : zVar.f21883c[iD];
        if (obj != null) {
            if (obj instanceof d0) {
                ((d0) obj).d(scope);
            } else if (obj != scope) {
                d0 d0Var = new d0();
                d0Var.d(obj);
                d0Var.d(scope);
                scope = (Scope) d0Var;
            }
            scope = (Scope) obj;
        }
        if (!z) {
            zVar.f21883c[iD] = scope;
            return;
        }
        int i10 = ~iD;
        zVar.f21882b[i10] = key;
        zVar.f21883c[i10] = scope;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean anyScopeOf(Key r17, r7.l<? super Scope, java.lang.Boolean> r18) {
        /*
            r16 = this;
            r0 = r18
            t.z r1 = r16.getMap()
            r2 = r17
            java.lang.Object r1 = r1.e(r2)
            r2 = 0
            if (r1 == 0) goto L6f
            boolean r3 = r1 instanceof t.d0
            r4 = 1
            if (r3 == 0) goto L62
            t.d0 r1 = (t.d0) r1
            java.lang.Object[] r3 = r1.f21836b
            long[] r1 = r1.f21835a
            int r5 = r1.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L6f
            r6 = r2
        L20:
            r7 = r1[r6]
            long r9 = ~r7
            r11 = 7
            long r9 = r9 << r11
            long r9 = r9 & r7
            r11 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r11
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 == 0) goto L5d
            int r9 = r6 - r5
            int r9 = ~r9
            int r9 = r9 >>> 31
            r10 = 8
            int r9 = 8 - r9
            r11 = r2
        L3a:
            if (r11 >= r9) goto L5b
            r12 = 255(0xff, double:1.26E-321)
            long r12 = r12 & r7
            r14 = 128(0x80, double:6.3E-322)
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 >= 0) goto L57
            int r12 = r6 << 3
            int r12 = r12 + r11
            r12 = r3[r12]
            java.lang.Object r12 = r0.invoke(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L57
            return r4
        L57:
            long r7 = r7 >> r10
            int r11 = r11 + 1
            goto L3a
        L5b:
            if (r9 != r10) goto L6f
        L5d:
            if (r6 == r5) goto L6f
            int r6 = r6 + 1
            goto L20
        L62:
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L6f
            return r4
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScopeMap.anyScopeOf(java.lang.Object, r7.l):boolean");
    }

    public final Map<Key, Set<Scope>> asMap() {
        Collection h0Var;
        HashMap map = new HashMap();
        z zVar = this.map;
        Object[] objArr = zVar.f21882b;
        Object[] objArr2 = zVar.f21883c;
        long[] jArr = zVar.f21881a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i10 = 0;
            while (true) {
                long j10 = jArr[i10];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j10) < 128) {
                            int i13 = (i10 << 3) + i12;
                            Object obj = objArr[i13];
                            Object obj2 = objArr2[i13];
                            if (obj2 instanceof d0) {
                                h0Var = new h0((d0) obj2, 1);
                            } else {
                                Object[] objArr3 = {obj2};
                                LinkedHashSet linkedHashSet = new LinkedHashSet(kotlin.collections.i0.q0(1));
                                r.m0(objArr3, linkedHashSet);
                                h0Var = linkedHashSet;
                            }
                            map.put(obj, h0Var);
                        }
                        j10 >>= 8;
                    }
                    if (i11 != 8) {
                        break;
                    }
                }
                if (i10 == length) {
                    break;
                }
                i10++;
            }
        }
        return map;
    }

    public final void clear() {
        this.map.a();
    }

    public final boolean contains(Key element) {
        return this.map.b(element);
    }

    public final void forEachScopeOf(Key key, l<? super Scope, t0> block) {
        Object objE = getMap().e(key);
        if (objE == null) {
            return;
        }
        if (!(objE instanceof d0)) {
            block.invoke(objE);
            return;
        }
        d0 d0Var = (d0) objE;
        Object[] objArr = d0Var.f21836b;
        long[] jArr = d0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        block.invoke(objArr[(i10 << 3) + i12]);
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    public final z getMap() {
        return this.map;
    }

    public final int getSize() {
        return this.map.f21885e;
    }

    public final boolean remove(Key key, Scope scope) {
        Object objE = this.map.e(key);
        if (objE == null) {
            return false;
        }
        if (!(objE instanceof d0)) {
            if (!objE.equals(scope)) {
                return false;
            }
            this.map.h(key);
            return true;
        }
        d0 d0Var = (d0) objE;
        boolean zJ = d0Var.j(scope);
        if (zJ && d0Var.b()) {
            this.map.h(key);
        }
        return zJ;
    }

    public final void removeScope(Scope scope) {
        boolean zB;
        z zVar = this.map;
        long[] jArr = zVar.f21881a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        int i13 = (i10 << 3) + i12;
                        Object obj = zVar.f21882b[i13];
                        Object obj2 = zVar.f21883c[i13];
                        if (obj2 instanceof d0) {
                            d0 d0Var = (d0) obj2;
                            d0Var.j(scope);
                            zB = d0Var.b();
                        } else {
                            zB = obj2 == scope;
                        }
                        if (zB) {
                            zVar.i(i13);
                        }
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    public final void removeScopeIf(l<? super Scope, Boolean> predicate) {
        long[] jArr;
        long[] jArr2;
        long j10;
        char c10;
        long j11;
        int i10;
        boolean zBooleanValue;
        d0 d0Var;
        long[] jArr3;
        int i11;
        d0 d0Var2;
        z map = getMap();
        long[] jArr4 = map.f21881a;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i12 = 0;
        while (true) {
            long j12 = jArr4[i12];
            char c11 = 7;
            long j13 = -9187201950435737472L;
            if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i13 = 8;
                int i14 = 8 - ((~(i12 - length)) >>> 31);
                int i15 = 0;
                while (i15 < i14) {
                    if ((j12 & 255) < 128) {
                        int i16 = (i12 << 3) + i15;
                        c10 = c11;
                        Object obj = map.f21882b[i16];
                        Object obj2 = map.f21883c[i16];
                        j11 = j13;
                        if (obj2 instanceof d0) {
                            d0 d0Var3 = (d0) obj2;
                            Object[] objArr = d0Var3.f21836b;
                            long[] jArr5 = d0Var3.f21835a;
                            int length2 = jArr5.length - 2;
                            if (length2 >= 0) {
                                int i17 = i13;
                                d0 d0Var4 = d0Var3;
                                int i18 = 0;
                                while (true) {
                                    long j14 = jArr5[i18];
                                    j10 = j12;
                                    if ((((~j14) << c10) & j14 & j11) != j11) {
                                        int i19 = 8 - ((~(i18 - length2)) >>> 31);
                                        int i20 = 0;
                                        while (i20 < i19) {
                                            if ((j14 & 255) < 128) {
                                                jArr3 = jArr4;
                                                int i21 = (i18 << 3) + i20;
                                                i11 = i20;
                                                if (((Boolean) predicate.invoke(objArr[i21])).booleanValue()) {
                                                    d0Var2 = d0Var4;
                                                    d0Var2.k(i21);
                                                }
                                                j14 >>= i17;
                                                d0Var4 = d0Var2;
                                                i20 = i11 + 1;
                                                jArr4 = jArr3;
                                            } else {
                                                jArr3 = jArr4;
                                                i11 = i20;
                                            }
                                            d0Var2 = d0Var4;
                                            j14 >>= i17;
                                            d0Var4 = d0Var2;
                                            i20 = i11 + 1;
                                            jArr4 = jArr3;
                                        }
                                        jArr2 = jArr4;
                                        d0Var = d0Var4;
                                        if (i19 != i17) {
                                            break;
                                        }
                                    } else {
                                        jArr2 = jArr4;
                                        d0Var = d0Var4;
                                    }
                                    if (i18 == length2) {
                                        break;
                                    }
                                    i18++;
                                    d0Var4 = d0Var;
                                    j12 = j10;
                                    jArr4 = jArr2;
                                    i17 = 8;
                                }
                            } else {
                                jArr2 = jArr4;
                                j10 = j12;
                                d0Var = d0Var3;
                            }
                            zBooleanValue = d0Var.b();
                        } else {
                            jArr2 = jArr4;
                            j10 = j12;
                            zBooleanValue = ((Boolean) predicate.invoke(obj2)).booleanValue();
                        }
                        if (zBooleanValue) {
                            map.i(i16);
                        }
                        i10 = 8;
                    } else {
                        jArr2 = jArr4;
                        j10 = j12;
                        c10 = c11;
                        j11 = j13;
                        i10 = i13;
                    }
                    j12 = j10 >> i10;
                    i15++;
                    i13 = i10;
                    c11 = c10;
                    j13 = j11;
                    jArr4 = jArr2;
                }
                jArr = jArr4;
                if (i14 != i13) {
                    return;
                }
            } else {
                jArr = jArr4;
            }
            if (i12 == length) {
                return;
            }
            i12++;
            jArr4 = jArr;
        }
    }

    public final void set(Key key, Scope value) {
        this.map.k(key, value);
    }
}
