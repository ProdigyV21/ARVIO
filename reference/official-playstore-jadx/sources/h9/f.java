package h9;

import java.io.IOException;
import v9.r0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15900i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h f15901l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(h hVar, int i10) {
        super(1);
        this.f15900i = i10;
        this.f15901l = hVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) throws IOException {
        switch (this.f15900i) {
            case 0:
                r0 r0Var = (r0) obj;
                if (r0Var.b()) {
                    return "*";
                }
                String strV = this.f15901l.V(r0Var.getType());
                if (r0Var.c() == 1) {
                    return strV;
                }
                return v.f.B(r0Var.c()) + ' ' + strV;
            default:
                return this.f15901l.V((w) obj);
        }
    }
}
