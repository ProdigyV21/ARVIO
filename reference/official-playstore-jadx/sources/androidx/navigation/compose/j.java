package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.tv.material3.TabRowDefaults;
import androidx.tv.material3.l0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4576i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4577l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4578m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Object obj, int i10, int i11) {
        super(2);
        this.f4576i = i11;
        this.f4578m = obj;
        this.f4577l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4576i) {
            case 0:
                ((Number) obj2).intValue();
                DialogHostKt.DialogHost((p) this.f4578m, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4577l | 1));
                return t0.f22605a;
            case 1:
                float fFloatValue = ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                l0 l0Var = (l0) this.f4578m;
                int i10 = this.f4577l;
                if (fFloatValue > 0.0f) {
                    l0Var.a(i10);
                } else if (fFloatValue < 0.0f) {
                    l0Var.b(i10);
                }
                return Boolean.valueOf(!(fFloatValue == 0.0f));
            default:
                ((Number) obj2).intValue();
                ((TabRowDefaults) this.f4578m).TabSeparator((Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4577l | 1));
                return t0.f22605a;
        }
    }
}
