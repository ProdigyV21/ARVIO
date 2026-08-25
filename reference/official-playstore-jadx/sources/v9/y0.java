package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final y0 f22262b = new y0(v0.f22256a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v0 f22263a;

    public y0(v0 v0Var) {
        this.f22263a = v0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r13) {
        /*
            Method dump skipped, instruction units count: 660
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.y0.a(int):void");
    }

    public static int b(int i10, int i11) {
        if (i10 == 0) {
            a(38);
            throw null;
        }
        if (i11 == 0) {
            a(39);
            throw null;
        }
        if (i10 == 1) {
            if (i11 == 0) {
                a(40);
                throw null;
            }
        } else {
            if (i11 == 1) {
                if (i10 != 0) {
                    return i10;
                }
                a(41);
                throw null;
            }
            if (i10 != i11) {
                throw new AssertionError("Variance conflict: type parameter variance '" + v.f.B(i10) + "' and projection kind '" + v.f.B(i11) + "' cannot be combined");
            }
            if (i11 == 0) {
                a(42);
                throw null;
            }
        }
        return i11;
    }

    public static int c(int i10, int i11) {
        if (i10 == 2 && i11 == 3) {
            return 3;
        }
        return (i10 == 3 && i11 == 2) ? 2 : 1;
    }

    public static y0 d(w wVar) {
        if (wVar == null) {
            a(6);
            throw null;
        }
        return new y0(p0.f22240b.f(wVar.I0(), wVar.G0()));
    }

    public static y0 e(v0 v0Var, v0 v0Var2) {
        if (v0Var == null) {
            a(3);
            throw null;
        }
        if (v0Var2 == null) {
            a(4);
            throw null;
        }
        if (v0Var.e()) {
            v0Var = v0Var2;
        } else if (!v0Var2.e()) {
            v0Var = new p(v0Var, v0Var2);
        }
        return new y0(v0Var);
    }

    public static String h(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (ea.o.i(th)) {
                throw th;
            }
            return "[Exception while computing toString(): " + th + "]";
        }
    }

    public final v0 f() {
        v0 v0Var = this.f22263a;
        if (v0Var != null) {
            return v0Var;
        }
        a(8);
        throw null;
    }

    public final w g(int i10, w wVar) {
        if (wVar == null) {
            a(9);
            throw null;
        }
        if (i10 == 0) {
            a(10);
            throw null;
        }
        if (this.f22263a.e()) {
            return wVar;
        }
        try {
            w type = j(new t0(i10, wVar), null, 0).getType();
            if (type != null) {
                return type;
            }
            a(12);
            throw null;
        } catch (x0 e5) {
            return x9.k.b(x9.j.f22700u, e5.getMessage());
        }
    }

    public final w i(int i10, w wVar) {
        if (wVar == null) {
            a(14);
            throw null;
        }
        if (i10 == 0) {
            a(15);
            throw null;
        }
        r0 t0Var = new t0(i10, f().f(i10, wVar));
        v0 v0Var = this.f22263a;
        if (!v0Var.e()) {
            try {
                t0Var = j(t0Var, null, 0);
            } catch (x0 unused) {
                t0Var = null;
            }
        }
        if (v0Var.a() || v0Var.b()) {
            boolean zB = v0Var.b();
            if (t0Var == null) {
                t0Var = null;
            } else if (!t0Var.b()) {
                w type = t0Var.getType();
                if (b1.c(type, aa.b.f773i, null)) {
                    int iC = t0Var.c();
                    if (iC == 3) {
                        t0Var = new t0(iC, (w) qb.l.b(type).f772b);
                    } else if (zB) {
                        t0Var = new t0(iC, (w) qb.l.b(type).f771a);
                    } else {
                        aa.c cVar = new aa.c();
                        y0 y0Var = new y0(cVar);
                        if (!cVar.e()) {
                            try {
                                t0Var = y0Var.j(t0Var, null, 0);
                            } catch (x0 unused2) {
                                t0Var = null;
                            }
                        }
                    }
                }
            }
        }
        if (t0Var == null) {
            return null;
        }
        return t0Var.getType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x012d  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final v9.r0 j(v9.r0 r17, g8.b1 r18, int r19) throws v9.x0 {
        /*
            Method dump skipped, instruction units count: 772
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.y0.j(v9.r0, g8.b1, int):v9.r0");
    }
}
