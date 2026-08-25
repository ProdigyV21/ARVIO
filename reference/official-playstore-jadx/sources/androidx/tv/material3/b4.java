package androidx.tv.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class b4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ BoxScope f5849i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f5850l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f5851m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ SwitchColors f5852n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ State f5853o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.p f5854p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InteractionSource f5855q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Shape f5856r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ float f5857s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ float f5858t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ float f5859u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f5860v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f5861w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b4(BoxScope boxScope, boolean z, boolean z5, SwitchColors switchColors, State state, r7.p pVar, InteractionSource interactionSource, Shape shape, float f10, float f11, float f12, int i10, int i11) {
        super(2);
        this.f5849i = boxScope;
        this.f5850l = z;
        this.f5851m = z5;
        this.f5852n = switchColors;
        this.f5853o = state;
        this.f5854p = pVar;
        this.f5855q = interactionSource;
        this.f5856r = shape;
        this.f5857s = f10;
        this.f5858t = f11;
        this.f5859u = f12;
        this.f5860v = i10;
        this.f5861w = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        SwitchKt.m6013SwitchImpl0DmnUew(this.f5849i, this.f5850l, this.f5851m, this.f5852n, this.f5853o, this.f5854p, this.f5855q, this.f5856r, this.f5857s, this.f5858t, this.f5859u, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5860v | 1), RecomposeScopeImplKt.updateChangedFlags(this.f5861w));
        return x6.t0.f22605a;
    }
}
