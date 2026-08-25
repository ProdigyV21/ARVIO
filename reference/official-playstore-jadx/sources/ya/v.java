package ya;

import cb.b1;
import cb.i1;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final v f22999l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final v f23000m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23001i;

    static {
        int i10 = 1;
        f22999l = new v(i10, 0);
        f23000m = new v(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(int i10, int i11) {
        super(i10);
        this.f23001i = i11;
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.util.Map] */
    @Override // r7.l
    public final Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
        switch (this.f23001i) {
            case 0:
                kotlin.reflect.d dVar = (kotlin.reflect.d) obj;
                h hVarD = b1.d(dVar, new h[0]);
                return hVarD == null ? (h) i1.f7517a.get(dVar) : hVarD;
            default:
                kotlin.reflect.d dVar2 = (kotlin.reflect.d) obj;
                h hVarD2 = b1.d(dVar2, new h[0]);
                if (hVarD2 == null) {
                    hVarD2 = (h) i1.f7517a.get(dVar2);
                }
                if (hVarD2 != null) {
                    return a.a.Q(hVarD2);
                }
                return null;
        }
    }
}
