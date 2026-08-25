package androidx.tv.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: renamed from: androidx.tv.material3.ComposableSingletons$CarouselKt$lambda-1$1, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "isActive", "Lx6/t0;", "invoke", "(ZLandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
final class ComposableSingletons$CarouselKt$lambda1$1 extends kotlin.jvm.internal.r implements r7.q<Boolean, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ComposableSingletons$CarouselKt$lambda1$1 f5624i = new ComposableSingletons$CarouselKt$lambda1$1(3);

    @Override // r7.q
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke(((Boolean) obj).booleanValue(), (Composer) obj2, ((Number) obj3).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(boolean z, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= composer.changed(z) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1740339971, i10, -1, "androidx.tv.material3.ComposableSingletons$CarouselKt.lambda-1.<anonymous> (Carousel.kt:495)");
        }
        long jM3509getWhite0d7_KjU = Color.INSTANCE.m3509getWhite0d7_KjU();
        long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(jM3509getWhite0d7_KjU, 0.3f, 0.0f, 0.0f, 0.0f, 14, null);
        Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(8));
        if (!z) {
            jM3509getWhite0d7_KjU = jM3471copywmQWz5c$default;
        }
        BoxKt.Box(BackgroundKt.m178backgroundbw27NRU(modifierM578size3ABfNKs, jM3509getWhite0d7_KjU, RoundedCornerShapeKt.getCircleShape()), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
