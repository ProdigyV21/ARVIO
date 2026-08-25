package q8;

import d8.p;
import g8.b0;
import g8.e1;
import kotlin.jvm.internal.r;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f21342i = new d(1);

    @Override // r7.l
    public final Object invoke(Object obj) {
        e1 e1VarL = f4.f.l(c.f21339b, ((b0) obj).j().i(p.f14751t));
        w type = e1VarL != null ? e1VarL.getType() : null;
        if (type == null) {
            return x9.k.b(x9.j.M, new String[0]);
        }
        return type;
    }
}
