package b8;

import java.lang.reflect.Method;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f7243g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(Method method, int i10) {
        super(method, false, 6);
        this.f7243g = i10;
    }

    @Override // b8.o, b8.e
    public final Object call(Object[] objArr) {
        switch (this.f7243g) {
            case 0:
                b0.f(this, objArr);
                return e(objArr.length <= 1 ? new Object[0] : kotlin.collections.r.S(objArr, 1, objArr.length), objArr[0]);
            case 1:
                b0.f(this, objArr);
                d(kotlin.collections.r.Z(objArr));
                return e(objArr.length <= 1 ? new Object[0] : kotlin.collections.r.S(objArr, 1, objArr.length), null);
            default:
                b0.f(this, objArr);
                return e(objArr, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(Method method, boolean z, int i10) {
        super(method, z, i10);
        this.f7243g = 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(Method method) {
        super(method, true, 4);
        this.f7243g = 1;
    }
}
