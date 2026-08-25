package p8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.c f21180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.c[] f21181b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final fi.iki.elonen.f f21182c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f21183d;

    static {
        f9.c cVar = new f9.c("org.jspecify.nullness");
        f9.c cVar2 = new f9.c("org.jspecify.annotations");
        f21180a = cVar2;
        f9.c cVar3 = new f9.c("io.reactivex.rxjava3.annotations");
        f9.c cVar4 = new f9.c("org.checkerframework.checker.nullness.compatqual");
        String strB = cVar3.b();
        f21181b = new f9.c[]{new f9.c(strB.concat(".Nullable")), new f9.c(strB.concat(".NonNull"))};
        f9.c cVar5 = new f9.c("org.jetbrains.annotations");
        v vVar = v.f21184d;
        x6.x xVar = new x6.x(cVar5, vVar);
        x6.x xVar2 = new x6.x(new f9.c("androidx.annotation"), vVar);
        x6.x xVar3 = new x6.x(new f9.c("android.support.annotation"), vVar);
        x6.x xVar4 = new x6.x(new f9.c("android.annotation"), vVar);
        x6.x xVar5 = new x6.x(new f9.c("com.android.annotations"), vVar);
        x6.x xVar6 = new x6.x(new f9.c("org.eclipse.jdt.annotation"), vVar);
        x6.x xVar7 = new x6.x(new f9.c("org.checkerframework.checker.nullness.qual"), vVar);
        x6.x xVar8 = new x6.x(cVar4, vVar);
        x6.x xVar9 = new x6.x(new f9.c("javax.annotation"), vVar);
        x6.x xVar10 = new x6.x(new f9.c("edu.umd.cs.findbugs.annotations"), vVar);
        x6.x xVar11 = new x6.x(new f9.c("io.reactivex.annotations"), vVar);
        f9.c cVar6 = new f9.c("androidx.annotation.RecentlyNullable");
        f0 f0Var = f0.WARN;
        x6.x xVar12 = new x6.x(cVar6, new v(f0Var, 4));
        x6.x xVar13 = new x6.x(new f9.c("androidx.annotation.RecentlyNonNull"), new v(f0Var, 4));
        x6.x xVar14 = new x6.x(new f9.c("lombok"), vVar);
        x6.r rVar = new x6.r(2, 0, 0);
        f0 f0Var2 = f0.STRICT;
        f21182c = new fi.iki.elonen.f(kotlin.collections.h0.t0(xVar, xVar2, xVar3, xVar4, xVar5, xVar6, xVar7, xVar8, xVar9, xVar10, xVar11, xVar12, xVar13, xVar14, new x6.x(cVar, new v(f0Var, rVar, f0Var2)), new x6.x(cVar2, new v(f0Var, new x6.r(2, 0, 0), f0Var2)), new x6.x(cVar3, new v(f0Var, new x6.r(1, 8, 0), f0Var2))));
        f21183d = new v(f0Var, 4);
    }
}
