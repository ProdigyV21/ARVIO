package s5;

import c5.c;
import c5.d;
import com.google.firebase.components.f;
import com.google.firebase.components.s;
import com.google.firebase.components.u;
import java.util.concurrent.Executor;
import ka.k1;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f21640l = new b(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f21641m = new b(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f21642n = new b(2);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f21643o = new b(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21644i;

    public /* synthetic */ b(int i10) {
        this.f21644i = i10;
    }

    @Override // com.google.firebase.components.f
    public final Object f(u uVar) {
        switch (this.f21644i) {
            case 0:
                return new k1((Executor) uVar.d(new s(c5.a.class, Executor.class)));
            case 1:
                return new k1((Executor) uVar.d(new s(c.class, Executor.class)));
            case 2:
                return new k1((Executor) uVar.d(new s(c5.b.class, Executor.class)));
            default:
                return new k1((Executor) uVar.d(new s(d.class, Executor.class)));
        }
    }
}
