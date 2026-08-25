package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.layout.BeyondBoundsLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements BeyondBoundsLayout.BeyondBoundsScope {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f5384a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ kotlin.jvm.internal.k0 f5385b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5386c;

    public f(g gVar, kotlin.jvm.internal.k0 k0Var, int i10) {
        this.f5384a = gVar;
        this.f5385b = k0Var;
        this.f5386c = i10;
    }

    @Override // androidx.compose.ui.layout.BeyondBoundsLayout.BeyondBoundsScope
    public final boolean getHasMoreContent() {
        return this.f5384a.a((i2.h) this.f5385b.f19746i, this.f5386c);
    }
}
