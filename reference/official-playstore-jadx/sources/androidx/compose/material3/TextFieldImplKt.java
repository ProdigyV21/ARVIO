package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.u;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a¢\u0002\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u00062\u0006\u0010\t\u001a\u00020\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a9\u0010%\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\u0011\u0010\"\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a#\u0010(\u001a\u00020&*\u00020&2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u0002H\u0000¢\u0006\u0004\b(\u0010)\u001a\u0019\u0010-\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010*H\u0000¢\u0006\u0004\b-\u0010.\u001a\u0019\u0010/\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010*H\u0000¢\u0006\u0004\b/\u0010.\"\u0014\u00100\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b0\u00101\"\u0014\u00102\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b2\u00101\"\u0014\u00103\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b3\u00101\"\u0014\u00104\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b4\u00101\"\u0014\u00105\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b5\u00101\"\u0014\u00106\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b6\u00101\"\u0014\u00107\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b7\u00101\"\u0014\u00108\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b8\u00101\"\u0014\u00109\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b9\u00101\"\u001a\u0010;\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0014\u0010?\u001a\u00020,8\u0000X\u0080T¢\u0006\u0006\n\u0004\b?\u0010@\"\u0014\u0010A\u001a\u00020,8\u0002X\u0082T¢\u0006\u0006\n\u0004\bA\u0010@\"\u0014\u0010B\u001a\u00020,8\u0002X\u0082T¢\u0006\u0006\n\u0004\bB\u0010@\"\u001a\u0010D\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u001a\u0010H\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bH\u0010E\u001a\u0004\bI\u0010G\"\u001a\u0010J\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bJ\u0010E\u001a\u0004\bK\u0010G\"\u001a\u0010L\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bL\u0010E\u001a\u0004\bM\u0010G\"\u001a\u0010N\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bN\u0010E\u001a\u0004\bO\u0010G\"\u001a\u0010P\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bP\u0010E\u001a\u0004\bQ\u0010G\"\u001a\u0010R\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bR\u0010E\u001a\u0004\bS\u0010G\"\u001a\u0010T\u001a\u00020&8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u001a\u0010\\\u001a\u0004\u0018\u00010Y*\u00020X8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006]"}, d2 = {"Landroidx/compose/material3/TextFieldType;", LinkHeader.Parameters.Type, "", "value", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "", "singleLine", "enabled", "isError", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "Landroidx/compose/material3/TextFieldColors;", "colors", TtmlNode.RUBY_CONTAINER, "CommonDecorationBox", "(Landroidx/compose/material3/TextFieldType;Ljava/lang/String;Lr7/p;Landroidx/compose/ui/text/input/VisualTransformation;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/Color;", "contentColor", "Landroidx/compose/ui/text/TextStyle;", "typography", "content", "Decoration-KTwxG1Y", "(JLandroidx/compose/ui/text/TextStyle;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Decoration", "Landroidx/compose/ui/Modifier;", "defaultErrorMessage", "defaultErrorSemantics", "(Landroidx/compose/ui/Modifier;ZLjava/lang/String;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/layout/Placeable;", "placeable", "", "widthOrZero", "(Landroidx/compose/ui/layout/Placeable;)I", "heightOrZero", "TextFieldId", "Ljava/lang/String;", "PlaceholderId", "LabelId", "LeadingId", "TrailingId", "PrefixId", "SuffixId", "SupportingId", "ContainerId", "Landroidx/compose/ui/unit/Constraints;", "ZeroConstraints", "J", "getZeroConstraints", "()J", "AnimationDuration", "I", "PlaceholderAnimationDuration", "PlaceholderAnimationDelayOrDuration", "Landroidx/compose/ui/unit/Dp;", "TextFieldPadding", "F", "getTextFieldPadding", "()F", "HorizontalIconPadding", "getHorizontalIconPadding", "SupportingTopPadding", "getSupportingTopPadding", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "MinTextLineHeight", "getMinTextLineHeight", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "layoutId", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldImplKt {
    public static final int AnimationDuration = 150;
    public static final String ContainerId = "Container";
    private static final Modifier IconDefaultSizeModifier;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final float MinFocusedLabelLineHeight;
    private static final float MinSupportingTextLineHeight;
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final String TextFieldId = "TextField";
    private static final float TextFieldPadding;
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float HorizontalIconPadding = Dp.m5678constructorimpl(12);
    private static final float SupportingTopPadding = Dp.m5678constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m5678constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m5678constructorimpl(24);

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldImplKt$CommonDecorationBox$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\n\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\b\u0010\t"}, d2 = {"", "labelProgress", "Landroidx/compose/ui/graphics/Color;", "labelTextStyleColor", "labelContentColor", "placeholderAlphaProgress", "prefixSuffixAlphaProgress", "Lx6/t0;", "invoke-eopBjH0", "(FJJFFLandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements u<Float, Color, Color, Float, Float, Composer, Integer, t0> {
        final /* synthetic */ TextStyle $bodyLarge;
        final /* synthetic */ TextStyle $bodySmall;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $shouldOverrideTextStyleColor;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $transformedText;
        final /* synthetic */ TextFieldType $type;

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldImplKt$CommonDecorationBox$3$WhenMappings */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TextFieldType.values().length];
                try {
                    iArr[TextFieldType.Filled.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TextFieldType.Outlined.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(p<? super Composer, ? super Integer, t0> pVar, TextFieldColors textFieldColors, boolean z, boolean z5, InteractionSource interactionSource, p<? super Composer, ? super Integer, t0> pVar2, String str, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, TextFieldType textFieldType, p<? super Composer, ? super Integer, t0> pVar8, boolean z10, PaddingValues paddingValues, boolean z11, TextStyle textStyle, TextStyle textStyle2, p<? super Composer, ? super Integer, t0> pVar9) {
            super(7);
            this.$label = pVar;
            this.$colors = textFieldColors;
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$placeholder = pVar2;
            this.$transformedText = str;
            this.$prefix = pVar3;
            this.$suffix = pVar4;
            this.$leadingIcon = pVar5;
            this.$trailingIcon = pVar6;
            this.$supportingText = pVar7;
            this.$type = textFieldType;
            this.$innerTextField = pVar8;
            this.$singleLine = z10;
            this.$contentPadding = paddingValues;
            this.$shouldOverrideTextStyleColor = z11;
            this.$bodyLarge = textStyle;
            this.$bodySmall = textStyle2;
            this.$container = pVar9;
        }

        @Override // r7.u
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
            m2144invokeeopBjH0(((Number) obj).floatValue(), ((Color) obj2).m3482unboximpl(), ((Color) obj3).m3482unboximpl(), ((Number) obj4).floatValue(), ((Number) obj5).floatValue(), (Composer) obj6, ((Number) obj7).intValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-eopBjH0, reason: not valid java name */
        public final void m2144invokeeopBjH0(float f10, long j10, long j11, float f11, float f12, Composer composer, int i10) {
            int i11;
            long j12;
            boolean z;
            float f13;
            ComposableLambda composableLambda;
            ComposableLambda composableLambda2;
            Composer composer2;
            ComposableLambda composableLambda3;
            if ((i10 & 6) == 0) {
                i11 = (composer.changed(f10) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= composer.changed(j10) ? 32 : 16;
            }
            if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                j12 = j11;
                i11 |= composer.changed(j12) ? 256 : 128;
            } else {
                j12 = j11;
            }
            if ((i10 & 3072) == 0) {
                i11 |= composer.changed(f11) ? 2048 : 1024;
            }
            if ((i10 & 24576) == 0) {
                i11 |= composer.changed(f12) ? 16384 : 8192;
            }
            int i12 = i11;
            if ((74899 & i12) == 74898 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1290853831, i12, -1, "androidx.compose.material3.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:115)");
            }
            p<Composer, Integer, t0> pVar = this.$label;
            if (pVar != null) {
                z = true;
                composableLambda = null;
                f13 = f10;
                composableLambda2 = ComposableLambdaKt.composableLambda(composer, -382297919, true, new TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1(f10, j12, pVar, this.$shouldOverrideTextStyleColor, j10));
            } else {
                z = true;
                f13 = f10;
                composableLambda = null;
                composableLambda2 = null;
            }
            ComposableLambda composableLambda4 = (this.$placeholder == null || this.$transformedText.length() != 0 || f11 <= 0.0f) ? composableLambda : ComposableLambdaKt.composableLambda(composer, -524658155, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1(f11, this.$colors.placeholderColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer, 0).getValue().m3482unboximpl(), this.$placeholder));
            long jM3482unboximpl = this.$colors.prefixColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer, 0).getValue().m3482unboximpl();
            p<Composer, Integer, t0> pVar2 = this.$prefix;
            if (pVar2 == null || f12 <= 0.0f) {
                composer2 = composer;
                composableLambda3 = composableLambda;
            } else {
                composer2 = composer;
                composableLambda3 = ComposableLambdaKt.composableLambda(composer2, 1824482619, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedPrefix$1(f12, jM3482unboximpl, this.$bodyLarge, pVar2));
            }
            long jM3482unboximpl2 = this.$colors.suffixColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer2, 0).getValue().m3482unboximpl();
            p<Composer, Integer, t0> pVar3 = this.$suffix;
            ComposableLambda composableLambda5 = (pVar3 == null || f12 <= 0.0f) ? composableLambda : ComposableLambdaKt.composableLambda(composer2, 907456412, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedSuffix$1(f12, jM3482unboximpl2, this.$bodyLarge, pVar3));
            Composer composer3 = composer2;
            long jM3482unboximpl3 = this.$colors.leadingIconColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer3, 0).getValue().m3482unboximpl();
            p<Composer, Integer, t0> pVar4 = this.$leadingIcon;
            ComposableLambda composableLambda6 = pVar4 != null ? ComposableLambdaKt.composableLambda(composer3, 90769583, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1(jM3482unboximpl3, pVar4)) : composableLambda;
            long jM3482unboximpl4 = this.$colors.trailingIconColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer, 0).getValue().m3482unboximpl();
            p<Composer, Integer, t0> pVar5 = this.$trailingIcon;
            ComposableLambda composableLambda7 = pVar5 != null ? ComposableLambdaKt.composableLambda(composer, 2077796155, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1(jM3482unboximpl4, pVar5)) : composableLambda;
            long jM3482unboximpl5 = this.$colors.supportingTextColor$material3_release(this.$enabled, this.$isError, this.$interactionSource, composer, 0).getValue().m3482unboximpl();
            p<Composer, Integer, t0> pVar6 = this.$supportingText;
            ComposableLambda composableLambda8 = pVar6 != null ? ComposableLambdaKt.composableLambda(composer, -1531019900, z, new TextFieldImplKt$CommonDecorationBox$3$decoratedSupporting$1$1(jM3482unboximpl5, this.$bodySmall, pVar6)) : null;
            int i13 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
            if (i13 == z) {
                ComposableLambda composableLambda9 = composableLambda5;
                composer.startReplaceableGroup(404042583);
                ComposableLambda composableLambda10 = ComposableLambdaKt.composableLambda(composer, -2124779163, true, new TextFieldImplKt$CommonDecorationBox$3$containerWithId$1(this.$container));
                TextFieldKt.TextFieldLayout(Modifier.INSTANCE, this.$innerTextField, composableLambda2, composableLambda4, composableLambda6, composableLambda7, composableLambda3, composableLambda9, this.$singleLine, f10, composableLambda10, composableLambda8, this.$contentPadding, composer, ((i12 << 27) & 1879048192) | 6, 6);
                composer.endReplaceableGroup();
            } else if (i13 != 2) {
                composer.startReplaceableGroup(404045277);
                composer.endReplaceableGroup();
            } else {
                Object objG = a0.c.g(404043576, composer, 404043645);
                Composer.Companion companion = Composer.INSTANCE;
                if (objG == companion.getEmpty()) {
                    objG = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m3293boximpl(Size.INSTANCE.m3314getZeroNHjbRc()), null, 2, null);
                    composer.updateRememberedValue(objG);
                }
                MutableState mutableState = (MutableState) objG;
                composer.endReplaceableGroup();
                ComposableLambda composableLambda11 = ComposableLambdaKt.composableLambda(composer, 1902535592, z, new TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1(mutableState, this.$contentPadding, this.$container));
                ComposableLambda composableLambda12 = composableLambda2;
                ComposableLambda composableLambda13 = composableLambda5;
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposableLambda composableLambda14 = composableLambda8;
                p<Composer, Integer, t0> pVar7 = this.$innerTextField;
                ComposableLambda composableLambda15 = composableLambda4;
                boolean z5 = this.$singleLine;
                composer.startReplaceableGroup(404044653);
                boolean z10 = (i12 & 14) == 4;
                Object objRememberedValue = composer.rememberedValue();
                if (z10 || objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new TextFieldImplKt$CommonDecorationBox$3$1$1(f13, mutableState);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                OutlinedTextFieldKt.OutlinedTextFieldLayout(companion2, pVar7, composableLambda15, composableLambda12, composableLambda6, composableLambda7, composableLambda3, composableLambda13, z5, f10, (l) objRememberedValue, composableLambda11, composableLambda14, this.$contentPadding, composer, ((i12 << 27) & 1879048192) | 6, 48);
                composer.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldImplKt$CommonDecorationBox$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ TextFieldType $type;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(TextFieldType textFieldType, String str, p<? super Composer, ? super Integer, t0> pVar, VisualTransformation visualTransformation, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, boolean z, boolean z5, boolean z10, InteractionSource interactionSource, PaddingValues paddingValues, TextFieldColors textFieldColors, p<? super Composer, ? super Integer, t0> pVar9, int i10, int i11, int i12) {
            super(2);
            this.$type = textFieldType;
            this.$value = str;
            this.$innerTextField = pVar;
            this.$visualTransformation = visualTransformation;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$prefix = pVar6;
            this.$suffix = pVar7;
            this.$supportingText = pVar8;
            this.$singleLine = z;
            this.$enabled = z5;
            this.$isError = z10;
            this.$interactionSource = interactionSource;
            this.$contentPadding = paddingValues;
            this.$colors = textFieldColors;
            this.$container = pVar9;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextFieldImplKt.CommonDecorationBox(this.$type, this.$value, this.$innerTextField, this.$visualTransformation, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$singleLine, this.$enabled, this.$isError, this.$interactionSource, this.$contentPadding, this.$colors, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldImplKt$defaultErrorSemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ String $defaultErrorMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(1);
            this.$defaultErrorMessage = str;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.error(semanticsPropertyReceiver, this.$defaultErrorMessage);
        }
    }

    static {
        float f10 = 16;
        TextFieldPadding = Dp.m5678constructorimpl(f10);
        MinFocusedLabelLineHeight = Dp.m5678constructorimpl(f10);
        MinSupportingTextLineHeight = Dp.m5678constructorimpl(f10);
        float f11 = 48;
        IconDefaultSizeModifier = SizeKt.m562defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11));
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:286:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CommonDecorationBox(androidx.compose.material3.TextFieldType r44, java.lang.String r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, androidx.compose.ui.text.input.VisualTransformation r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r50, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r51, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r52, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r53, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r54, boolean r55, boolean r56, boolean r57, androidx.compose.foundation.interaction.InteractionSource r58, androidx.compose.foundation.layout.PaddingValues r59, androidx.compose.material3.TextFieldColors r60, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r61, androidx.compose.runtime.Composer r62, int r63, int r64, int r65) {
        /*
            Method dump skipped, instruction units count: 1183
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.TextFieldType, java.lang.String, r7.p, androidx.compose.ui.text.input.VisualTransformation, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: Decoration-KTwxG1Y, reason: not valid java name */
    public static final void m2143DecorationKTwxG1Y(long j10, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        long j11;
        p<? super Composer, ? super Integer, t0> pVar2;
        TextStyle textStyle2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1520066345);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(j10) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i11 & 4) != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            j11 = j10;
            pVar2 = pVar;
            textStyle2 = textStyle;
        } else {
            if (i13 != 0) {
                textStyle = null;
            }
            TextStyle textStyle3 = textStyle;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1520066345, i12, -1, "androidx.compose.material3.Decoration (TextFieldImpl.kt:271)");
            }
            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1449369305, true, new TextFieldImplKt$Decoration$contentWithColor$1(j10, pVar));
            if (textStyle3 != null) {
                composerStartRestartGroup.startReplaceableGroup(1830467586);
                j11 = j10;
                ProvideContentColorTextStyleKt.m1782ProvideContentColorTextStyle3JVO9M(j11, textStyle3, pVar, composerStartRestartGroup, i12 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED);
                pVar2 = pVar;
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                j11 = j10;
                pVar2 = pVar;
                composerStartRestartGroup.startReplaceableGroup(1830467667);
                composableLambda.invoke(composerStartRestartGroup, 6);
                composerStartRestartGroup.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            textStyle2 = textStyle3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new TextFieldImplKt$Decoration$1(j11, textStyle2, pVar2, i10, i11));
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new AnonymousClass1(str), 1, null) : modifier;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }

    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }
}
