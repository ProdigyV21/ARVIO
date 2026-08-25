package androidx.compose.foundation.text2;

import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class SecureTextFieldController$passwordRevealFilter$1 extends m implements a<t0> {
    public SecureTextFieldController$passwordRevealFilter$1(Object obj) {
        super(0, 0, SecureTextFieldController.class, obj, "scheduleHide", "scheduleHide()V");
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1052invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1052invoke() {
        ((SecureTextFieldController) this.receiver).scheduleHide();
    }
}
