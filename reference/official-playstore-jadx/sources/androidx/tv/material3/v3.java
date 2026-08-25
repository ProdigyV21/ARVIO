package androidx.tv.material3;

import androidx.compose.animation.core.Animatable;

/* JADX INFO: loaded from: classes3.dex */
public final class v3 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Animatable f6467i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f6468l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v3(Animatable animatable, float f10) {
        super(0);
        this.f6467i = animatable;
        this.f6468l = f10;
    }

    @Override // r7.a
    public final Object invoke() {
        Animatable.updateBounds$default(this.f6467i, Float.valueOf(this.f6468l), null, 2, null);
        return x6.t0.f22605a;
    }
}
