package androidx.navigation.compose;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;

/* JADX INFO: loaded from: classes3.dex */
public final class u extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final u f4607l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u f4608m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final u f4609n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final u f4610o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final u f4611p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4612i;

    static {
        int i10 = 1;
        f4607l = new u(i10, 0);
        f4608m = new u(i10, 1);
        f4609n = new u(i10, 2);
        f4610o = new u(i10, 3);
        f4611p = new u(i10, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(int i10, int i11) {
        super(i10);
        this.f4612i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4612i) {
            case 0:
                return ((androidx.navigation.i) obj).f4685p;
            case 1:
                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(700, 0, null, 6, null), 0.0f, 2, null);
            case 2:
                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(700, 0, null, 6, null), 0.0f, 2, null);
            case 3:
                return EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(700, 0, null, 6, null), 0.0f, 2, null);
            default:
                return EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(700, 0, null, 6, null), 0.0f, 2, null);
        }
    }
}
