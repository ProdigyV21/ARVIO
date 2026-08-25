package h8;

import d8.p;
import k9.w;
import kotlin.collections.h0;
import kotlin.collections.z;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.f f15858a = f9.f.j("message");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.f f15859b = f9.f.j("replaceWith");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f9.f f15860c = f9.f.j("level");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f9.f f15861d = f9.f.j("expression");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f9.f f15862e = f9.f.j("imports");

    public static j a(d8.k kVar, String str, String str2, int i10) {
        if ((i10 & 2) != 0) {
            str2 = "";
        }
        return new j(kVar, p.f14744m, h0.t0(new x(f15858a, new w(str)), new x(f15859b, new k9.a(new j(kVar, p.f14746o, h0.t0(new x(f15861d, new w(str2)), new x(f15862e, new k9.b(z.f19728i, new ab.h(kVar, 15))))))), new x(f15860c, new k9.i(f9.b.j(p.f14745n), f9.f.j("WARNING")))));
    }
}
