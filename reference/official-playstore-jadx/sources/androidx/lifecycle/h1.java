package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i1 f3249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g1 f3250b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o1.c f3251c;

    public h1(i1 i1Var, g1 g1Var, o1.c cVar) {
        this.f3249a = i1Var;
        this.f3250b = g1Var;
        this.f3251c = cVar;
    }

    public final d1 a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return b(cls, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

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
    public final d1 b(Class cls, String str) {
        d1 d1VarCreate;
        i1 i1Var = this.f3249a;
        d1 d1Var = (d1) i1Var.f3252a.get(str);
        boolean zIsInstance = cls.isInstance(d1Var);
        g1 g1Var = this.f3250b;
        if (zIsInstance) {
            b1 b1Var = g1Var instanceof b1 ? (b1) g1Var : null;
            if (b1Var != null) {
                b1Var.b(d1Var);
            }
            return d1Var;
        }
        o1.d dVar = new o1.d(this.f3251c);
        dVar.b(e1.f3226b, str);
        try {
            d1VarCreate = g1Var.create(cls, dVar);
        } catch (AbstractMethodError unused) {
            d1VarCreate = g1Var.create(cls);
        }
        d1 d1Var2 = (d1) i1Var.f3252a.put(str, d1VarCreate);
        if (d1Var2 != null) {
            d1Var2.onCleared();
        }
        return d1VarCreate;
    }

    public /* synthetic */ h1(i1 i1Var, g1 g1Var, int i10) {
        this(i1Var, g1Var, o1.a.f20721b);
    }
}
