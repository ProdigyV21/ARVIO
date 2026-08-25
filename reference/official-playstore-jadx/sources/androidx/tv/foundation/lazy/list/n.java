package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes3.dex */
public final class n extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ State f5450i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m0 f5451l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d0 f5452m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(State state, m0 m0Var, d0 d0Var) {
        super(0);
        this.f5450i = state;
        this.f5451l = m0Var;
        this.f5452m = d0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        a0 a0Var = (a0) this.f5450i.getValue();
        m0 m0Var = this.f5451l;
        return new LazyListItemProviderImpl(m0Var, a0Var, this.f5452m, new i2.r((x7.i) ((i2.l) m0Var.f5429c.f5236f).getValue(), a0Var));
    }
}
