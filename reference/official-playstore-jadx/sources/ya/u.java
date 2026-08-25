package ya;

import java.util.List;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final u f22996l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u f22997m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22998i;

    static {
        int i10 = 2;
        f22996l = new u(i10, 0);
        f22997m = new u(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(int i10, int i11) {
        super(i10);
        this.f22998i = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f22998i) {
            case 0:
                List list = (List) obj2;
                return b0.z((kotlin.reflect.d) obj, b0.D(fb.d.f15400a, list, true), new gb.x(list, 2));
            default:
                List list2 = (List) obj2;
                h hVarZ = b0.z((kotlin.reflect.d) obj, b0.D(fb.d.f15400a, list2, true), new gb.x(list2, 3));
                if (hVarZ != null) {
                    return a.a.Q(hVarZ);
                }
                return null;
        }
    }
}
