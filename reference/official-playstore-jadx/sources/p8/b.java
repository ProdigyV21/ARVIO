package p8;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.c f21080a = new f9.c("javax.annotation.meta.TypeQualifierNickname");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.c f21081b = new f9.c("javax.annotation.meta.TypeQualifier");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f9.c f21082c = new f9.c("javax.annotation.meta.TypeQualifierDefault");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f9.c f21083d = new f9.c("kotlin.annotations.jvm.UnderMigration");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List f21084e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f21085f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final LinkedHashMap f21086g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Set f21087h;

    static {
        a aVar = a.VALUE_PARAMETER;
        List listE = t7.a.E(a.FIELD, a.METHOD_RETURN_TYPE, aVar, a.TYPE_PARAMETER_BOUNDS, a.TYPE_USE);
        f21084e = listE;
        f9.c cVar = c0.f21109c;
        x8.g gVar = x8.g.f22650m;
        Map mapT0 = kotlin.collections.h0.t0(new x6.x(cVar, new q(new x8.h(gVar, false), listE, false)), new x6.x(c0.f21112f, new q(new x8.h(gVar, false), listE, false)));
        f21085f = mapT0;
        f21086g = kotlin.collections.h0.v0(kotlin.collections.h0.t0(new x6.x(new f9.c("javax.annotation.ParametersAreNullableByDefault"), new q(new x8.h(x8.g.f22649l, false), Collections.singletonList(aVar))), new x6.x(new f9.c("javax.annotation.ParametersAreNonnullByDefault"), new q(new x8.h(gVar, false), Collections.singletonList(aVar)))), mapT0);
        f21087h = kotlin.collections.r.p0(new f9.c[]{c0.f21114h, c0.f21115i});
    }
}
