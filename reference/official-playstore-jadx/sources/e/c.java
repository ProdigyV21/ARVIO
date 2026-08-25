package e;

import java.util.UUID;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
public final class c extends r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f14935l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f14936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f14937n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final c f14938o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final c f14939p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14940i;

    static {
        int i10 = 0;
        f14935l = new c(i10, 0);
        f14936m = new c(i10, 1);
        f14937n = new c(i10, 2);
        f14938o = new c(i10, 3);
        f14939p = new c(i10, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(int i10, int i11) {
        super(i10);
        this.f14940i = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14940i) {
            case 0:
                return UUID.randomUUID().toString();
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
            default:
                return Boolean.TRUE;
        }
    }
}
