package m8;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f20260l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f20261m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f20262n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f20263o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20264i;

    static {
        int i10 = 1;
        f20260l = new b(i10, 0);
        f20261m = new b(i10, 1);
        f20262n = new b(i10, 2);
        f20263o = new b(i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i10, int i11) {
        super(i10);
        this.f20264i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f20264i) {
            case 0:
                Type ownerType = ((ParameterizedType) obj).getOwnerType();
                if (ownerType instanceof ParameterizedType) {
                    return (ParameterizedType) ownerType;
                }
                return null;
            case 1:
                return kotlin.collections.r.G(((ParameterizedType) obj).getActualTypeArguments());
            case 2:
                return Boolean.valueOf(((Class) obj).getSimpleName().length() == 0);
            default:
                String simpleName = ((Class) obj).getSimpleName();
                if (!f9.f.k(simpleName)) {
                    simpleName = null;
                }
                if (simpleName != null) {
                    return f9.f.j(simpleName);
                }
                return null;
        }
    }
}
