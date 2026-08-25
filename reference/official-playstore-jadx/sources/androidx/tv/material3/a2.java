package androidx.tv.material3;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class a2 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5808i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f5809l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a2(boolean z, int i10) {
        super(1);
        this.f5808i = i10;
        this.f5809l = z;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f5808i) {
            case 0:
                SemanticsPropertiesKt.setSelected((SemanticsPropertyReceiver) obj, this.f5809l);
                break;
            default:
                ((GraphicsLayerScope) obj).setAlpha(!this.f5809l ? 0.8f : 1.0f);
                break;
        }
        return x6.t0.f22605a;
    }
}
