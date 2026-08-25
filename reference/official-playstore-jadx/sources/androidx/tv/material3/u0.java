package androidx.tv.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.text.TextStyle;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.p f6431i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TextStyle f6432l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f6433m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f6434n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.p f6435o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f6436p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f6437q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6438r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(r7.p pVar, TextStyle textStyle, r7.p pVar2, r7.p pVar3, r7.p pVar4, float f10, PaddingValues paddingValues, int i10) {
        super(2);
        this.f6431i = pVar;
        this.f6432l = textStyle;
        this.f6433m = pVar2;
        this.f6434n = pVar3;
        this.f6435o = pVar4;
        this.f6436p = f10;
        this.f6437q = paddingValues;
        this.f6438r = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        ChipKt.m5983ChipContentHYR8e34(this.f6431i, this.f6432l, this.f6433m, this.f6434n, this.f6435o, this.f6436p, this.f6437q, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6438r | 1));
        return x6.t0.f22605a;
    }
}
