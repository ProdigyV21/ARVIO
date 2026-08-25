package e;

import androidx.activity.d0;
import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes.dex */
public final class e extends d0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ State f14946d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(boolean z, State state) {
        super(z);
        this.f14946d = state;
    }

    @Override // androidx.activity.d0
    public final void b() {
        ((r7.a) this.f14946d.getValue()).invoke();
    }
}
