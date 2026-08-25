package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/tv/foundation/lazy/list/c0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/list/c0;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LazyDslKt$items$8 extends kotlin.jvm.internal.r implements r7.r<c0, Integer, Composer, Integer, t0> {
    @Override // r7.r
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((c0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(c0 c0Var, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 6) == 0) {
            i12 = (composer.changed(c0Var) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 147) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(877401278, i12, -1, "androidx.tv.foundation.lazy.list.items.<anonymous> (LazyDsl.kt:189)");
            }
            throw null;
        }
    }
}
