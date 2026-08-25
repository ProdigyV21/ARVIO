package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ State f5290i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b1 f5291l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(State state, b1 b1Var) {
        super(0);
        this.f5290i = state;
        this.f5291l = b1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        l lVar = (l) this.f5290i.getValue();
        b1 b1Var = this.f5291l;
        return new LazyGridItemProviderImpl(b1Var, lVar, new i2.r((x7.i) ((i2.l) b1Var.f5153a.f5236f).getValue(), lVar));
    }
}
