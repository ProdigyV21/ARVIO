package eb;

import cb.a2;
import cb.d2;
import cb.g2;
import cb.x1;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f15148a = kotlin.collections.r.p0(new ab.g[]{a2.f7460b, d2.f7488b, x1.f7587b, g2.f7507b});

    public static final boolean a(ab.g gVar) {
        return gVar.isInline() && f15148a.contains(gVar);
    }
}
