package io.sentry;

import java.io.Closeable;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class l2 implements d0, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17431i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k5 f17432l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k5 f17433m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile m0 f17434n = null;

    public l2(y6 y6Var) {
        this.f17431i = y6Var;
        u uVar = new u(y6Var, 2);
        this.f17433m = new k5(uVar);
        this.f17432l = new k5(uVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f17434n != null) {
            this.f17434n.f17467f.shutdown();
        }
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, j0 j0Var) {
        if (a7Var.f17991r == null) {
            a7Var.f17991r = "java";
        }
        if (o(a7Var, j0Var)) {
            l(a7Var);
            io.sentry.protocol.t tVar = this.f17431i.getSessionReplay().f17225v;
            if (tVar != null) {
                a7Var.f17986m = tVar;
            }
        }
        return a7Var;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.sentry.d0
    public final j5 j(j5 j5Var, j0 j0Var) {
        ArrayList arrayList;
        if (j5Var.f17991r == null) {
            j5Var.f17991r = "java";
        }
        Throwable th = j5Var.f17993t;
        if (th != null) {
            AtomicInteger atomicInteger = new AtomicInteger(-1);
            HashSet hashSet = new HashSet();
            ArrayDeque arrayDeque = new ArrayDeque();
            this.f17433m.a(th, atomicInteger, hashSet, arrayDeque, null);
            j5Var.D = new f2(new ArrayList(arrayDeque));
        }
        io.sentry.protocol.d dVar = j5Var.x;
        y6 y6Var = this.f17431i;
        io.sentry.protocol.d dVarA = io.sentry.protocol.d.a(dVar, y6Var);
        if (dVarA != null) {
            j5Var.x = dVarA;
        }
        Map mapA = y6Var.getModulesLoader().a();
        if (mapA != null) {
            AbstractMap abstractMap = j5Var.I;
            if (abstractMap == null) {
                j5Var.I = new HashMap(mapA);
            } else {
                abstractMap.putAll(mapA);
            }
        }
        if (o(j5Var, j0Var)) {
            l(j5Var);
            if (j5Var.e() == null) {
                ArrayList<io.sentry.protocol.u> arrayListD = j5Var.d();
                if (arrayListD == null || arrayListD.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = null;
                    for (io.sentry.protocol.u uVar : arrayListD) {
                        if (uVar.f17705p != null && uVar.f17703n != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(uVar.f17703n);
                        }
                    }
                }
                boolean zIsAttachThreads = y6Var.isAttachThreads();
                boolean zF = false;
                k5 k5Var = this.f17432l;
                if (zIsAttachThreads || io.sentry.hints.a.class.isInstance(j0Var.b("sentry:typeCheckHint"))) {
                    Object objB = j0Var.b("sentry:typeCheckHint");
                    boolean zIsAttachStacktrace = y6Var.isAttachStacktrace();
                    if (objB instanceof io.sentry.hints.a) {
                        zF = ((io.sentry.hints.a) objB).f();
                        zIsAttachStacktrace = true;
                    }
                    j5Var.C = new f2(k5Var.b(Thread.getAllStackTraces(), arrayList, zF, zIsAttachStacktrace));
                } else if (y6Var.isAttachStacktrace() && ((arrayListD == null || arrayListD.isEmpty()) && !io.sentry.hints.e.class.isInstance(j0Var.b("sentry:typeCheckHint")))) {
                    boolean zIsAttachStacktrace2 = y6Var.isAttachStacktrace();
                    HashMap map = new HashMap();
                    Thread threadCurrentThread = Thread.currentThread();
                    map.put(threadCurrentThread, threadCurrentThread.getStackTrace());
                    j5Var.C = new f2(k5Var.b(map, null, false, zIsAttachStacktrace2));
                    return j5Var;
                }
            }
        }
        return j5Var;
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, j0 j0Var) {
        if (e0Var.f17991r == null) {
            e0Var.f17991r = "java";
        }
        io.sentry.protocol.d dVarA = io.sentry.protocol.d.a(e0Var.x, this.f17431i);
        if (dVarA != null) {
            e0Var.x = dVarA;
        }
        if (o(e0Var, j0Var)) {
            l(e0Var);
        }
        return e0Var;
    }

    public final void l(v4 v4Var) {
        if (v4Var.f17989p == null) {
            v4Var.f17989p = this.f17431i.getRelease();
        }
        if (v4Var.f17990q == null) {
            v4Var.f17990q = this.f17431i.getEnvironment();
        }
        if (v4Var.f17994u == null) {
            v4Var.f17994u = this.f17431i.getServerName();
        }
        if (this.f17431i.isAttachServerName() && v4Var.f17994u == null) {
            if (this.f17434n == null) {
                if (m0.f17460i == null) {
                    io.sentry.util.a aVarA = m0.f17461j.a();
                    try {
                        if (m0.f17460i == null) {
                            m0.f17460i = new m0();
                        }
                        aVarA.close();
                    } catch (Throwable th) {
                        try {
                            aVarA.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                this.f17434n = m0.f17460i;
            }
            if (this.f17434n != null) {
                m0 m0Var = this.f17434n;
                if (m0Var.f17464c < System.currentTimeMillis() && m0Var.f17465d.compareAndSet(false, true)) {
                    m0Var.a();
                }
                v4Var.f17994u = m0Var.f17463b;
            }
        }
        if (v4Var.f17995v == null) {
            v4Var.f17995v = this.f17431i.getDist();
        }
        if (v4Var.f17986m == null) {
            v4Var.f17986m = this.f17431i.getSdkVersion();
        }
        y6 y6Var = this.f17431i;
        if (v4Var.f17988o == null) {
            v4Var.c(new HashMap(y6Var.getTags()));
        } else {
            for (Map.Entry<String, String> entry : y6Var.getTags().entrySet()) {
                if (!v4Var.f17988o.containsKey(entry.getKey())) {
                    v4Var.b(entry.getKey(), entry.getValue());
                }
            }
        }
        io.sentry.protocol.i0 i0Var = v4Var.f17992s;
        if (i0Var == null) {
            i0Var = new io.sentry.protocol.i0();
            v4Var.f17992s = i0Var;
        }
        if (i0Var.f17600n == null && this.f17431i.isSendDefaultPii()) {
            i0Var.f17600n = "{{auto}}";
        }
    }

    public final boolean o(v4 v4Var, j0 j0Var) {
        if (qb.l.B(j0Var)) {
            return true;
        }
        this.f17431i.getLogger().q(w5.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", v4Var.f17984i);
        return false;
    }
}
