package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: renamed from: coil.compose.ComposableSingletons$SubcomposeAsyncImageKt$lambda-1$1, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcoil/compose/SubcomposeAsyncImageScope;", "Lx6/t0;", "invoke", "(Lcoil/compose/SubcomposeAsyncImageScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class ComposableSingletons$SubcomposeAsyncImageKt$lambda1$1 extends r implements q<SubcomposeAsyncImageScope, Composer, Integer, t0> {
    public static final ComposableSingletons$SubcomposeAsyncImageKt$lambda1$1 INSTANCE = new ComposableSingletons$SubcomposeAsyncImageKt$lambda1$1();

    public ComposableSingletons$SubcomposeAsyncImageKt$lambda1$1() {
        super(3);
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((SubcomposeAsyncImageScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(SubcomposeAsyncImageScope subcomposeAsyncImageScope, Composer composer, int i10) {
        int i11;
        if ((i10 & 14) == 0) {
            i11 = (composer.changed(subcomposeAsyncImageScope) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 91) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1692951203, i11, -1, "coil.compose.ComposableSingletons$SubcomposeAsyncImageKt.lambda-1.<anonymous> (SubcomposeAsyncImage.kt:238)");
        }
        SubcomposeAsyncImageKt.SubcomposeAsyncImageContent(subcomposeAsyncImageScope, null, null, null, null, null, 0.0f, null, composer, i11 & 14, 127);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
