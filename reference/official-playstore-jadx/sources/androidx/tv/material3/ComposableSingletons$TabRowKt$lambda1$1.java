package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: renamed from: androidx.tv.material3.ComposableSingletons$TabRowKt$lambda-1$1, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
final class ComposableSingletons$TabRowKt$lambda1$1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ComposableSingletons$TabRowKt$lambda1$1 f5625i = new ComposableSingletons$TabRowKt$lambda1$1(2);

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1321551739, i10, -1, "androidx.tv.material3.ComposableSingletons$TabRowKt.lambda-1.<anonymous> (TabRow.kt:95)");
        }
        TabRowDefaults.f5765a.TabSeparator(composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
