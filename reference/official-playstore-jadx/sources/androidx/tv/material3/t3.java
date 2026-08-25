package androidx.tv.material3;

import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class t3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6424i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6425l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f6426m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f6427n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t3(boolean z, boolean z5, r7.a aVar, r7.a aVar2) {
        super(1);
        this.f6424i = z;
        this.f6425l = z5;
        this.f6426m = aVar;
        this.f6427n = aVar2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
        SemanticsPropertiesKt.setSelected(semanticsPropertyReceiver, this.f6424i);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new s3(2, this.f6426m), 1, null);
        SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, null, new s3(3, this.f6427n), 1, null);
        if (!this.f6425l) {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        return x6.t0.f22605a;
    }
}
