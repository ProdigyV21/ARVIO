package androidx.compose.foundation.text;

import androidx.compose.foundation.c;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a/\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\t\u0010\n\"\u0014\u0010\u000b\u001a\u00020\u00038\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f²\u0006\f\u0010\u000e\u001a\u00020\r8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "", "minLines", "maxLines", "heightInLines", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;II)Landroidx/compose/ui/Modifier;", "Lx6/t0;", "validateMinMaxLines", "(II)V", "DefaultMinLines", "I", "", "typeface", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeightInLinesModifierKt {
    public static final int DefaultMinLines = 1;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.HeightInLinesModifierKt$heightInLines$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ TextStyle $textStyle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, int i11, TextStyle textStyle) {
            super(3);
            this.$minLines = i10;
            this.$maxLines = i11;
            this.$textStyle = textStyle;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(408240218);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408240218, i10, -1, "androidx.compose.foundation.text.heightInLines.<anonymous> (HeightInLinesModifier.kt:59)");
            }
            HeightInLinesModifierKt.validateMinMaxLines(this.$minLines, this.$maxLines);
            if (this.$minLines == 1 && this.$maxLines == Integer.MAX_VALUE) {
                Modifier.Companion companion = Modifier.INSTANCE;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return companion;
            }
            Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
            FontFamily.Resolver resolver = (FontFamily.Resolver) composer.consume(CompositionLocalsKt.getLocalFontFamilyResolver());
            LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
            TextStyle textStyle = this.$textStyle;
            composer.startReplaceableGroup(511388516);
            boolean zChanged = composer.changed(textStyle) | composer.changed(layoutDirection);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = TextStyleKt.resolveDefaults(textStyle, layoutDirection);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            TextStyle textStyle2 = (TextStyle) objRememberedValue;
            composer.startReplaceableGroup(511388516);
            boolean zChanged2 = composer.changed(resolver) | composer.changed(textStyle2);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                FontFamily fontFamily = textStyle2.getFontFamily();
                FontWeight fontWeight = textStyle2.getFontWeight();
                if (fontWeight == null) {
                    fontWeight = FontWeight.INSTANCE.getNormal();
                }
                FontStyle fontStyleM5224getFontStyle4Lr2A7w = textStyle2.m5224getFontStyle4Lr2A7w();
                int iM5305unboximpl = fontStyleM5224getFontStyle4Lr2A7w != null ? fontStyleM5224getFontStyle4Lr2A7w.m5305unboximpl() : FontStyle.INSTANCE.m5309getNormal_LCdwA();
                FontSynthesis fontSynthesisM5225getFontSynthesisZQGJjVo = textStyle2.m5225getFontSynthesisZQGJjVo();
                objRememberedValue2 = resolver.mo5277resolveDPcqOEQ(fontFamily, fontWeight, iM5305unboximpl, fontSynthesisM5225getFontSynthesisZQGJjVo != null ? fontSynthesisM5225getFontSynthesisZQGJjVo.getValue() : FontSynthesis.INSTANCE.m5319getAllGVVA2EU());
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            State state = (State) objRememberedValue2;
            int i11 = 0;
            Object[] objArr = {density, resolver, this.$textStyle, layoutDirection, state.getValue()};
            composer.startReplaceableGroup(-568225417);
            int i12 = 0;
            boolean zChanged3 = false;
            while (i12 < 5) {
                zChanged3 |= composer.changed(objArr[i12]);
                i12++;
                i11 = i11;
            }
            int i13 = i11;
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = Integer.valueOf(IntSize.m5843getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle2, density, resolver, TextFieldDelegateKt.getEmptyTextReplacement(), 1)));
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            int iIntValue = ((Number) objRememberedValue3).intValue();
            Object obj = this.$textStyle;
            Object value = state.getValue();
            Object[] objArr2 = new Object[5];
            objArr2[i13] = density;
            objArr2[1] = resolver;
            objArr2[2] = obj;
            objArr2[3] = layoutDirection;
            objArr2[4] = value;
            composer.startReplaceableGroup(-568225417);
            int i14 = i13;
            int i15 = i14;
            while (i14 < 5) {
                i15 |= composer.changed(objArr2[i14]) ? 1 : 0;
                i14++;
            }
            Object objRememberedValue4 = composer.rememberedValue();
            if (i15 != 0 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = Integer.valueOf(IntSize.m5843getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle2, density, resolver, TextFieldDelegateKt.getEmptyTextReplacement() + '\n' + TextFieldDelegateKt.getEmptyTextReplacement(), 2)));
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceableGroup();
            int iIntValue2 = ((Number) objRememberedValue4).intValue() - iIntValue;
            int i16 = this.$minLines;
            Integer numValueOf = i16 == 1 ? null : Integer.valueOf(((i16 - 1) * iIntValue2) + iIntValue);
            int i17 = this.$maxLines;
            Integer numValueOf2 = i17 != Integer.MAX_VALUE ? Integer.valueOf(((i17 - 1) * iIntValue2) + iIntValue) : null;
            Modifier modifierM565heightInVpY3zN4 = SizeKt.m565heightInVpY3zN4(Modifier.INSTANCE, numValueOf != null ? density.mo282toDpu2uoSUM(numValueOf.intValue()) : Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM(), numValueOf2 != null ? density.mo282toDpu2uoSUM(numValueOf2.intValue()) : Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM());
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierM565heightInVpY3zN4;
        }
    }

    public static final Modifier heightInLines(Modifier modifier, TextStyle textStyle, int i10, int i11) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new HeightInLinesModifierKt$heightInLines$$inlined$debugInspectorInfo$1(i10, i11, textStyle) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(i10, i11, textStyle));
    }

    public static /* synthetic */ Modifier heightInLines$default(Modifier modifier, TextStyle textStyle, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 1;
        }
        if ((i12 & 4) != 0) {
            i11 = Integer.MAX_VALUE;
        }
        return heightInLines(modifier, textStyle, i10, i11);
    }

    public static final void validateMinMaxLines(int i10, int i11) {
        if (i10 <= 0 || i11 <= 0) {
            throw new IllegalArgumentException(c.s("both minLines ", i10, " and maxLines ", i11, " must be greater than zero").toString());
        }
        if (i10 > i11) {
            throw new IllegalArgumentException(c.n(i10, i11, "minLines ", " must be less than or equal to maxLines ").toString());
        }
    }
}
