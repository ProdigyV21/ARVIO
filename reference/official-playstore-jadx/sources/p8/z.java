package p8;

/* JADX INFO: loaded from: classes5.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f21193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f21194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f21195c;

    public z(f0 f0Var, f0 f0Var2) {
        this.f21193a = f0Var;
        this.f21194b = f0Var2;
        f0 f0Var3 = f0.IGNORE;
        this.f21195c = f0Var == f0Var3 && f0Var2 == f0Var3;
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
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        if (this.f21193a != zVar.f21193a || this.f21194b != zVar.f21194b) {
            return false;
        }
        kotlin.collections.a0 a0Var = kotlin.collections.a0.f19683i;
        return a0Var.equals(a0Var);
    }

    public final int hashCode() {
        int iHashCode = this.f21193a.hashCode() * 31;
        f0 f0Var = this.f21194b;
        return (iHashCode + (f0Var == null ? 0 : f0Var.hashCode())) * 31;
    }

    public final String toString() {
        return "Jsr305Settings(globalLevel=" + this.f21193a + ", migrationLevel=" + this.f21194b + ", userDefinedLevelForSpecificAnnotation=" + kotlin.collections.a0.f19683i + ')';
    }
}
