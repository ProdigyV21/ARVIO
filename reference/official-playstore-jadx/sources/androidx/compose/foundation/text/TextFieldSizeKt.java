package androidx.compose.foundation.text;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004²\u0006\n\u0010\u0005\u001a\u00020\u0006X\u008a\u0084\u0002"}, d2 = {"textFieldMinSize", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "foundation_release", "typeface", ""}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSizeKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ TextStyle $style;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00521 extends r implements q<MeasureScope, Measurable, Constraints, MeasureResult> {
            final /* synthetic */ TextFieldSize $minSizeState;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00531 extends r implements l<Placeable.PlacementScope, t0> {
                final /* synthetic */ Placeable $measured;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00531(Placeable placeable) {
                    super(1);
                    this.$measured = placeable;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return t0.f22605a;
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, this.$measured, 0, 0, 0.0f, 4, null);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00521(TextFieldSize textFieldSize) {
                super(3);
                this.$minSizeState = textFieldSize;
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m901invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m901invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                SizeKt.m563defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, 0.0f, 3, null);
                long minSize = this.$minSizeState.getMinSize();
                Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, d.n(IntSize.m5844getWidthimpl(minSize), Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10)), 0, d.n(IntSize.m5843getHeightimpl(minSize), Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10)), 0, 10, null));
                return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new C00531(placeableMo4631measureBRTryo0), 4, null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TextStyle textStyle) {
            super(3);
            this.$style = textStyle;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(1582736677);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1582736677, i10, -1, "androidx.compose.foundation.text.textFieldMinSize.<anonymous> (TextFieldSize.kt:38)");
            }
            Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
            FontFamily.Resolver resolver = (FontFamily.Resolver) composer.consume(CompositionLocalsKt.getLocalFontFamilyResolver());
            LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
            TextStyle textStyle = this.$style;
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
            TextStyle textStyle3 = this.$style;
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new TextFieldSize(layoutDirection, density, resolver, textStyle3, state.getValue());
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            TextFieldSize textFieldSize = (TextFieldSize) objRememberedValue3;
            textFieldSize.update(layoutDirection, density, resolver, textStyle2, state.getValue());
            Modifier modifierLayout = LayoutModifierKt.layout(Modifier.INSTANCE, new C00521(textFieldSize));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierLayout;
        }
    }

    public static final Modifier textFieldMinSize(Modifier modifier, TextStyle textStyle) {
        return ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(textStyle), 1, null);
    }
}
