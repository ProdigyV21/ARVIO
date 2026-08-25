package z4;

import com.google.firebase.components.s;
import com.google.firebase.components.u;
import java.util.concurrent.Executor;
import ka.k1;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements com.google.firebase.components.f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i f23194l = new i(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f23195m = new i(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f23196n = new i(2);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final i f23197o = new i(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23198i;

    public /* synthetic */ i(int i10) {
        this.f23198i = i10;
    }

    @Override // com.google.firebase.components.f
    public final Object f(u uVar) {
        switch (this.f23198i) {
            case 0:
                return new k1((Executor) uVar.d(new s(c5.a.class, Executor.class)));
            case 1:
                return new k1((Executor) uVar.d(new s(c5.c.class, Executor.class)));
            case 2:
                return new k1((Executor) uVar.d(new s(c5.b.class, Executor.class)));
            default:
                return new k1((Executor) uVar.d(new s(c5.d.class, Executor.class)));
        }
    }
}
