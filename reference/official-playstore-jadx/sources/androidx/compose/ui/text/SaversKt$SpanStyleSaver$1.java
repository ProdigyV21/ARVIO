package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/ui/text/SpanStyle;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$SpanStyleSaver$1 extends r implements p<SaverScope, SpanStyle, Object> {
    public static final SaversKt$SpanStyleSaver$1 INSTANCE = new SaversKt$SpanStyleSaver$1();

    public SaversKt$SpanStyleSaver$1() {
        super(2);
    }

    @Override // r7.p
    public final Object invoke(SaverScope saverScope, SpanStyle spanStyle) {
        Color colorM3462boximpl = Color.m3462boximpl(spanStyle.m5151getColor0d7_KjU());
        Color.Companion companion = Color.INSTANCE;
        Object objSave = SaversKt.save(colorM3462boximpl, SaversKt.getSaver(companion), saverScope);
        TextUnit textUnitM5855boximpl = TextUnit.m5855boximpl(spanStyle.getFontSize());
        TextUnit.Companion companion2 = TextUnit.INSTANCE;
        return t7.a.a(objSave, SaversKt.save(textUnitM5855boximpl, SaversKt.getSaver(companion2), saverScope), SaversKt.save(spanStyle.getFontWeight(), SaversKt.getSaver(FontWeight.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontStyle()), SaversKt.save(spanStyle.getFontSynthesis()), SaversKt.save(-1), SaversKt.save(spanStyle.getFontFeatureSettings()), SaversKt.save(TextUnit.m5855boximpl(spanStyle.getLetterSpacing()), SaversKt.getSaver(companion2), saverScope), SaversKt.save(spanStyle.getBaselineShift(), SaversKt.getSaver(BaselineShift.INSTANCE), saverScope), SaversKt.save(spanStyle.getTextGeometricTransform(), SaversKt.getSaver(TextGeometricTransform.INSTANCE), saverScope), SaversKt.save(spanStyle.getLocaleList(), SaversKt.getSaver(LocaleList.INSTANCE), saverScope), SaversKt.save(Color.m3462boximpl(spanStyle.getBackground()), SaversKt.getSaver(companion), saverScope), SaversKt.save(spanStyle.getBackground(), SaversKt.getSaver(TextDecoration.INSTANCE), saverScope), SaversKt.save(spanStyle.getShadow(), SaversKt.getSaver(Shadow.INSTANCE), saverScope));
    }
}
