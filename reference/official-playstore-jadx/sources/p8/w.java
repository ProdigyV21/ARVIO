package p8;

import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class w extends kotlin.jvm.internal.l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final w f21188i = new w(1);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "getDefaultReportLevelForAnnotation";
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        return l0.f19747a.c(u.class, "compiler.common.jvm");
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "getDefaultReportLevelForAnnotation(Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/load/java/ReportLevel;";
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        f9.c cVar = (f9.c) obj;
        f9.c cVar2 = u.f21180a;
        e0.f21134h.getClass();
        fi.iki.elonen.f fVar = d0.f21125b;
        x6.r rVar = new x6.r(1, 7, 20);
        f0 f0Var = (f0) ((u9.m) fVar.f15417l).invoke(cVar);
        if (f0Var != null) {
            return f0Var;
        }
        v vVar = (v) ((u9.m) u.f21182c.f15417l).invoke(cVar);
        if (vVar == null) {
            return f0.IGNORE;
        }
        x6.r rVar2 = vVar.f21186b;
        return (rVar2 == null || rVar2.f22602n - rVar.f22602n > 0) ? vVar.f21185a : vVar.f21187c;
    }
}
