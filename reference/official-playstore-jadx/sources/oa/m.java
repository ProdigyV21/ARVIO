package oa;

import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ma.j f20839i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public byte[] f20840l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20841m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20842n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20843o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f20844p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ na.j[] f20845q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ kotlin.jvm.internal.r f20846r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ f7.j f20847s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ na.k f20848t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public m(d7.d dVar, na.k kVar, r7.a aVar, r7.q qVar, na.j[] jVarArr) {
        super(2, dVar);
        this.f20845q = jVarArr;
        this.f20846r = (kotlin.jvm.internal.r) aVar;
        this.f20847s = (f7.j) qVar;
        this.f20848t = kVar;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [kotlin.jvm.internal.r, r7.a] */
    /* JADX WARN: Type inference failed for: r4v0, types: [f7.j, r7.q] */
    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        m mVar = new m(dVar, this.f20848t, this.f20846r, this.f20847s, this.f20845q);
        mVar.f20844p = obj;
        return mVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f4, code lost:
    
        if (r13.invoke(r12, r10, r19) == r6) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b5 A[LOOP:0: B:28:0x00b5->B:51:?, LOOP_START, PHI: r7 r11
      0x00b5: PHI (r7v3 int) = (r7v2 int), (r7v4 int) binds: [B:25:0x00b0, B:51:?] A[DONT_GENERATE, DONT_INLINE]
      0x00b5: PHI (r11v4 kotlin.collections.d0) = (r11v3 kotlin.collections.d0), (r11v14 kotlin.collections.d0) binds: [B:25:0x00b0, B:51:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r11v7, types: [kotlin.jvm.internal.r, r7.a] */
    /* JADX WARN: Type inference failed for: r13v4, types: [f7.j, r7.q] */
    /* JADX WARN: Type inference failed for: r15v0, types: [ma.f] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [ma.j, ma.w] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r8v1, types: [ma.j, ma.w] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x011a -> B:8:0x0025). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: oa.m.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
