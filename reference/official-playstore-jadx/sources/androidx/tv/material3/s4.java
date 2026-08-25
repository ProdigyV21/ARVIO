package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;

/* JADX INFO: loaded from: classes3.dex */
public final class s4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ WideButtonDefaults f6391i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6392l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6393m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6394n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s4(WideButtonDefaults wideButtonDefaults, boolean z, MutableInteractionSource mutableInteractionSource, int i10) {
        super(2);
        this.f6391i = wideButtonDefaults;
        this.f6392l = z;
        this.f6393m = mutableInteractionSource;
        this.f6394n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f6394n | 1);
        this.f6391i.Background(this.f6392l, this.f6393m, (Composer) obj, iUpdateChangedFlags);
        return x6.t0.f22605a;
    }
}
