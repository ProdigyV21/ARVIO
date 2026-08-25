package p8;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashMap f21104c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f21105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f21106b = new ConcurrentHashMap();

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (a aVar : a.values()) {
            String str = aVar.f21077i;
            if (linkedHashMap.get(str) == null) {
                linkedHashMap.put(str, aVar);
            }
        }
        f21104c = linkedHashMap;
    }

    public c(x xVar) {
        this.f21105a = xVar;
    }

    public static ArrayList a(Object obj, boolean z) {
        Map mapA = ((h8.b) obj).a();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : mapA.entrySet()) {
            kotlin.collections.x.b0(arrayList, (!z || kotlin.jvm.internal.p.a((f9.f) entry.getKey(), b0.f21089b)) ? i((k9.g) entry.getValue()) : kotlin.collections.z.f19728i);
        }
        return arrayList;
    }

    public static Object c(Object obj, f9.c cVar) {
        for (Object obj2 : d(obj)) {
            if (kotlin.jvm.internal.p.a(((h8.b) obj2).c(), cVar)) {
                return obj2;
            }
        }
        return null;
    }

    public static Iterable d(Object obj) {
        h8.h annotations;
        g8.f fVarD = m9.d.d((h8.b) obj);
        return (fVarD == null || (annotations = fVarD.getAnnotations()) == null) ? kotlin.collections.z.f19728i : annotations;
    }

    public static boolean e(Object obj, f9.c cVar) {
        Iterable iterableD = d(obj);
        if ((iterableD instanceof Collection) && ((Collection) iterableD).isEmpty()) {
            return false;
        }
        Iterator it = iterableD.iterator();
        while (it.hasNext()) {
            if (kotlin.jvm.internal.p.a(((h8.b) it.next()).c(), cVar)) {
                return true;
            }
        }
        return false;
    }

    public static List i(k9.g gVar) {
        if (!(gVar instanceof k9.b)) {
            return gVar instanceof k9.i ? Collections.singletonList(((k9.i) gVar).f19532c.f()) : kotlin.collections.z.f19728i;
        }
        Iterable iterable = (Iterable) ((k9.b) gVar).f19530a;
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(arrayList, i((k9.g) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x011e  */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Object, java.util.Map] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final p8.y b(p8.y r14, h8.h r15) {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.c.b(p8.y, h8.h):p8.y");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0089, code lost:
    
        if (r10.equals("ALWAYS") != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0092, code lost:
    
        if (r10.equals("UNKNOWN") == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009b, code lost:
    
        if (r10.equals("NEVER") == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a4, code lost:
    
        if (r10.equals("MAYBE") == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c9, code lost:
    
        if (r0.equals(p8.c0.f21118m) != false) goto L57;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final x8.h f(java.lang.Object r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.c.f(java.lang.Object, boolean):x8.h");
    }

    public final f0 g(Object obj) {
        String str;
        x xVar = this.f21105a;
        z zVar = xVar.f21190a;
        ((h8.b) obj).c();
        Object objC = c(obj, b.f21083d);
        if (objC == null || (str = (String) kotlin.collections.x.n0(a(objC, false))) == null) {
            return null;
        }
        f0 f0Var = xVar.f21190a.f21194b;
        if (f0Var != null) {
            return f0Var;
        }
        int iHashCode = str.hashCode();
        if (iHashCode == -2137067054) {
            if (str.equals("IGNORE")) {
                return f0.IGNORE;
            }
            return null;
        }
        if (iHashCode == -1838656823) {
            if (str.equals("STRICT")) {
                return f0.STRICT;
            }
            return null;
        }
        if (iHashCode == 2656902 && str.equals("WARN")) {
            return f0.WARN;
        }
        return null;
    }

    public final Object h(Object obj) {
        Object objH;
        if (!this.f21105a.f21190a.f21195c) {
            h8.b bVar = (h8.b) obj;
            if (kotlin.collections.x.f0(b.f21087h, bVar.c()) || e(obj, b.f21081b)) {
                return obj;
            }
            if (e(obj, b.f21080a)) {
                g8.f fVarD = m9.d.d(bVar);
                ConcurrentHashMap concurrentHashMap = this.f21106b;
                Object obj2 = concurrentHashMap.get(fVarD);
                if (obj2 != null) {
                    return obj2;
                }
                Iterator it = d(obj).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        objH = null;
                        break;
                    }
                    objH = h(it.next());
                    if (objH != null) {
                        break;
                    }
                }
                if (objH != null) {
                    Object objPutIfAbsent = concurrentHashMap.putIfAbsent(fVarD, objH);
                    return objPutIfAbsent == null ? objH : objPutIfAbsent;
                }
            }
        }
        return null;
    }
}
