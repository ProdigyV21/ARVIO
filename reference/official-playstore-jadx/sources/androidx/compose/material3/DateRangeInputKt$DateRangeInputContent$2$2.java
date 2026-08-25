package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangeInputKt$DateRangeInputContent$2$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ String $pattern;
    final /* synthetic */ String $startRangeText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeInputKt$DateRangeInputContent$2$2(String str, String str2) {
        super(2);
        this.$startRangeText = str;
        this.$pattern = str2;
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
            ComposerKt.traceEventStart(801434508, i10, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:77)");
        }
        String str = this.$startRangeText;
        Modifier.Companion companion = Modifier.INSTANCE;
        composer.startReplaceableGroup(-2126787323);
        boolean zChanged = composer.changed(this.$startRangeText) | composer.changed(this.$pattern);
        String str2 = this.$startRangeText;
        String str3 = this.$pattern;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DateRangeInputKt$DateRangeInputContent$2$2$1$1(str2, str3);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TextKt.m2153Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 131068);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
