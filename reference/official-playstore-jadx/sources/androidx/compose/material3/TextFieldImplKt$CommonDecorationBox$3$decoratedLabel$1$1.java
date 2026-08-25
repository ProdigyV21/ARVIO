package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $it;
    final /* synthetic */ long $labelContentColor;
    final /* synthetic */ float $labelProgress;
    final /* synthetic */ long $labelTextStyleColor;
    final /* synthetic */ boolean $shouldOverrideTextStyleColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1(float f10, long j10, p<? super Composer, ? super Integer, t0> pVar, boolean z, long j11) {
        super(2);
        this.$labelProgress = f10;
        this.$labelContentColor = j10;
        this.$it = pVar;
        this.$shouldOverrideTextStyleColor = z;
        this.$labelTextStyleColor = j11;
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
            ComposerKt.traceEventStart(-382297919, i10, -1, "androidx.compose.material3.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:117)");
        }
        MaterialTheme materialTheme = MaterialTheme.INSTANCE;
        TextStyle textStyleLerp = TextStyleKt.lerp(materialTheme.getTypography(composer, 6).getBodyLarge(), materialTheme.getTypography(composer, 6).getBodySmall(), this.$labelProgress);
        boolean z = this.$shouldOverrideTextStyleColor;
        long j10 = this.$labelTextStyleColor;
        if (z) {
            textStyleLerp = TextStyle.m5205copyp1EtxEg$default(textStyleLerp, j10, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
        }
        TextFieldImplKt.m2143DecorationKTwxG1Y(this.$labelContentColor, textStyleLerp, this.$it, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
