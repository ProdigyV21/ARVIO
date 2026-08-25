package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.material3.ComposableSingletons$TabRowKt$lambda-4$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ComposableSingletons$TabRowKt$lambda4$1 extends r implements p<Composer, Integer, t0> {
    public static final ComposableSingletons$TabRowKt$lambda4$1 INSTANCE = new ComposableSingletons$TabRowKt$lambda4$1();

    public ComposableSingletons$TabRowKt$lambda4$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-764008582, i10, -1, "androidx.compose.material3.ComposableSingletons$TabRowKt.lambda-4.<anonymous> (TabRow.kt:691)");
        }
        DividerKt.m1534HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer, 0, 7);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
