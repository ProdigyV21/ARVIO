package androidx.tv.material3;

import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class e4 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f5946i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e4(boolean z) {
        super(1);
        this.f5946i = z;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
        SemanticsPropertiesKt.setSelected(semanticsPropertyReceiver, this.f5946i);
        SemanticsPropertiesKt.m5039setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5029getTabo7Vup1c());
        return x6.t0.f22605a;
    }
}
