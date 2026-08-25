package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsets;", "consumedWindowInsets", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/WindowInsets;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ScaffoldKt$Scaffold$1$1 extends r implements l<WindowInsets, t0> {
    final /* synthetic */ WindowInsets $contentWindowInsets;
    final /* synthetic */ MutableWindowInsets $safeInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScaffoldKt$Scaffold$1$1(MutableWindowInsets mutableWindowInsets, WindowInsets windowInsets) {
        super(1);
        this.$safeInsets = mutableWindowInsets;
        this.$contentWindowInsets = windowInsets;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WindowInsets) obj);
        return t0.f22605a;
    }

    public final void invoke(WindowInsets windowInsets) {
        this.$safeInsets.setInsets(WindowInsetsKt.exclude(this.$contentWindowInsets, windowInsets));
    }
}
