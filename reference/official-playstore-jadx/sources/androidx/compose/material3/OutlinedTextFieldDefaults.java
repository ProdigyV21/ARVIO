package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J8\u0010\u001b\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u001cJÂ\u0003\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020\u001d2\b\b\u0002\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u001d2\b\b\u0002\u0010$\u001a\u00020\u001d2\b\b\u0002\u0010%\u001a\u00020\u001d2\b\b\u0002\u0010&\u001a\u00020\u001d2\b\b\u0002\u0010'\u001a\u00020\u001d2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010*\u001a\u00020\u001d2\b\b\u0002\u0010+\u001a\u00020\u001d2\b\b\u0002\u0010,\u001a\u00020\u001d2\b\b\u0002\u0010-\u001a\u00020\u001d2\b\b\u0002\u0010.\u001a\u00020\u001d2\b\b\u0002\u0010/\u001a\u00020\u001d2\b\b\u0002\u00100\u001a\u00020\u001d2\b\b\u0002\u00101\u001a\u00020\u001d2\b\b\u0002\u00102\u001a\u00020\u001d2\b\b\u0002\u00103\u001a\u00020\u001d2\b\b\u0002\u00104\u001a\u00020\u001d2\b\b\u0002\u00105\u001a\u00020\u001d2\b\b\u0002\u00106\u001a\u00020\u001d2\b\b\u0002\u00107\u001a\u00020\u001d2\b\b\u0002\u00108\u001a\u00020\u001d2\b\b\u0002\u00109\u001a\u00020\u001d2\b\b\u0002\u0010:\u001a\u00020\u001d2\b\b\u0002\u0010;\u001a\u00020\u001d2\b\b\u0002\u0010<\u001a\u00020\u001d2\b\b\u0002\u0010=\u001a\u00020\u001d2\b\b\u0002\u0010>\u001a\u00020\u001d2\b\b\u0002\u0010?\u001a\u00020\u001d2\b\b\u0002\u0010@\u001a\u00020\u001d2\b\b\u0002\u0010A\u001a\u00020\u001d2\b\b\u0002\u0010B\u001a\u00020\u001d2\b\b\u0002\u0010C\u001a\u00020\u001d2\b\b\u0002\u0010D\u001a\u00020\u001d2\b\b\u0002\u0010E\u001a\u00020\u001d2\b\b\u0002\u0010F\u001a\u00020\u001d2\b\b\u0002\u0010G\u001a\u00020\u001d2\b\b\u0002\u0010H\u001a\u00020\u001d2\b\b\u0002\u0010I\u001a\u00020\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\u009e\u0002\u0010\\\u001a\u00020\u00102\u0006\u0010M\u001a\u00020L2\u0011\u0010P\u001a\r\u0012\u0004\u0012\u00020\u00100N¢\u0006\u0002\bO2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010S\u001a\u00020R2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0015\b\u0002\u0010T\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010U\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010V\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010W\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010X\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010Y\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\u0015\b\u0002\u0010Z\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010N¢\u0006\u0002\bO2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\u00182\u0013\b\u0002\u0010[\u001a\r\u0012\u0004\u0012\u00020\u00100N¢\u0006\u0002\bOH\u0007¢\u0006\u0004\b\\\u0010]R\u001d\u0010^\u001a\u00020\r8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u0010aR\u001d\u0010b\u001a\u00020\r8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bb\u0010_\u001a\u0004\bc\u0010aR\u001d\u0010d\u001a\u00020\r8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bd\u0010_\u001a\u0004\be\u0010aR\u001d\u0010f\u001a\u00020\r8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bf\u0010_\u001a\u0004\bg\u0010aR\u0011\u0010\f\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0018\u0010m\u001a\u00020\t*\u00020j8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\bk\u0010l\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006n"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldDefaults;", "", "<init>", "()V", "", "enabled", "isError", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/material3/TextFieldColors;", "colors", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/unit/Dp;", "focusedBorderThickness", "unfocusedBorderThickness", "Lx6/t0;", "ContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "ContainerBox", TtmlNode.START, "top", TtmlNode.END, "bottom", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/ui/graphics/Color;", "focusedTextColor", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "selectionColors", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "", "value", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "singleLine", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", TtmlNode.RUBY_CONTAINER, "DecorationBox", "(Ljava/lang/String;Lr7/p;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "MinHeight", "F", "getMinHeight-D9Ej5fM", "()F", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "FocusedBorderThickness", "getFocusedBorderThickness-D9Ej5fM", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "Landroidx/compose/material3/ColorScheme;", "getDefaultOutlinedTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "defaultOutlinedTextFieldColors", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OutlinedTextFieldDefaults {
    public static final int $stable = 0;
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m5678constructorimpl(56);
    private static final float MinWidth = Dp.m5678constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m5678constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m5678constructorimpl(2);

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors) {
            super(2);
            this.$enabled = z;
            this.$isError = z5;
            this.$interactionSource = interactionSource;
            this.$colors = textFieldColors;
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
                ComposerKt.traceEventStart(-1448570018, i10, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1781)");
            }
            OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(this.$enabled, this.$isError, this.$interactionSource, this.$colors, null, 0.0f, 0.0f, composer, 12582912, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
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
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, p<? super Composer, ? super Integer, t0> pVar, boolean z, boolean z5, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, TextFieldColors textFieldColors, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar9, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$innerTextField = pVar;
            this.$enabled = z;
            this.$singleLine = z5;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = interactionSource;
            this.$isError = z10;
            this.$label = pVar2;
            this.$placeholder = pVar3;
            this.$leadingIcon = pVar4;
            this.$trailingIcon = pVar5;
            this.$prefix = pVar6;
            this.$suffix = pVar7;
            this.$supportingText = pVar8;
            this.$colors = textFieldColors;
            this.$contentPadding = paddingValues;
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
            OutlinedTextFieldDefaults.this.DecorationBox(this.$value, this.$innerTextField, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$colors, this.$contentPadding, this.$container, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    private OutlinedTextFieldDefaults() {
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1743contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 2) != 0) {
            f11 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 4) != 0) {
            f12 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i10 & 8) != 0) {
            f13 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m1746contentPaddinga9UjIt4(f10, f11, f12, f13);
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0100  */
    /* JADX INFO: renamed from: ContainerBox-nbWgWpA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1744ContainerBoxnbWgWpA(boolean r18, boolean r19, androidx.compose.foundation.interaction.InteractionSource r20, androidx.compose.material3.TextFieldColors r21, androidx.compose.ui.graphics.Shape r22, float r23, float r24, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldDefaults.m1744ContainerBoxnbWgWpA(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:259:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0125  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void DecorationBox(java.lang.String r37, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r38, boolean r39, boolean r40, androidx.compose.ui.text.input.VisualTransformation r41, androidx.compose.foundation.interaction.InteractionSource r42, boolean r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r50, androidx.compose.material3.TextFieldColors r51, androidx.compose.foundation.layout.PaddingValues r52, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r53, androidx.compose.runtime.Composer r54, int r55, int r56, int r57) {
        /*
            Method dump skipped, instruction units count: 1060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox(java.lang.String, r7.p, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public final TextFieldColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(-471651810);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471651810, i10, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1492)");
        }
        TextFieldColors defaultOutlinedTextFieldColors = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i10 << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultOutlinedTextFieldColors;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m1745colors0hiis_0(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, TextSelectionColors textSelectionColors, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, long j49, long j50, long j51, Composer composer, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        composer.startReplaceableGroup(1767617725);
        long jM3508getUnspecified0d7_KjU = (i15 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i15 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i15 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i15 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i15 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i15 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i15 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i15 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i15 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i15 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        TextSelectionColors textSelectionColors2 = (i15 & 1024) != 0 ? null : textSelectionColors;
        long jM3508getUnspecified0d7_KjU11 = (i15 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i15 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        long jM3508getUnspecified0d7_KjU13 = (i15 & 8192) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j22;
        long jM3508getUnspecified0d7_KjU14 = (i15 & 16384) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j23;
        long jM3508getUnspecified0d7_KjU15 = (32768 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j24;
        long jM3508getUnspecified0d7_KjU16 = (65536 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j25;
        long jM3508getUnspecified0d7_KjU17 = (131072 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j26;
        long jM3508getUnspecified0d7_KjU18 = (262144 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j27;
        long jM3508getUnspecified0d7_KjU19 = (524288 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j28;
        long jM3508getUnspecified0d7_KjU20 = (1048576 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j29;
        long jM3508getUnspecified0d7_KjU21 = (2097152 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j30;
        long jM3508getUnspecified0d7_KjU22 = (4194304 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j31;
        long jM3508getUnspecified0d7_KjU23 = (8388608 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j32;
        long jM3508getUnspecified0d7_KjU24 = (16777216 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j33;
        long jM3508getUnspecified0d7_KjU25 = (33554432 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j34;
        long jM3508getUnspecified0d7_KjU26 = (67108864 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j35;
        long jM3508getUnspecified0d7_KjU27 = (134217728 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j36;
        long jM3508getUnspecified0d7_KjU28 = (268435456 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j37;
        long jM3508getUnspecified0d7_KjU29 = (536870912 & i15) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j38;
        long jM3508getUnspecified0d7_KjU30 = (i15 & 1073741824) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j39;
        long jM3508getUnspecified0d7_KjU31 = (i16 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j40;
        long jM3508getUnspecified0d7_KjU32 = (i16 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j41;
        long jM3508getUnspecified0d7_KjU33 = (i16 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j42;
        long jM3508getUnspecified0d7_KjU34 = (i16 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j43;
        long jM3508getUnspecified0d7_KjU35 = (i16 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j44;
        long jM3508getUnspecified0d7_KjU36 = (i16 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j45;
        long jM3508getUnspecified0d7_KjU37 = (i16 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j46;
        long jM3508getUnspecified0d7_KjU38 = (i16 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j47;
        long jM3508getUnspecified0d7_KjU39 = (i16 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j48;
        long jM3508getUnspecified0d7_KjU40 = (i16 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j49;
        long jM3508getUnspecified0d7_KjU41 = (i16 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j50;
        long jM3508getUnspecified0d7_KjU42 = (i16 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j51;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767617725, i10, i11, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1593)");
        }
        TextFieldColors textFieldColorsM2070copyejIjP34 = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i14 >> 6) & 112).m2070copyejIjP34(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU7, jM3508getUnspecified0d7_KjU8, jM3508getUnspecified0d7_KjU9, jM3508getUnspecified0d7_KjU10, textSelectionColors2, jM3508getUnspecified0d7_KjU11, jM3508getUnspecified0d7_KjU12, jM3508getUnspecified0d7_KjU13, jM3508getUnspecified0d7_KjU14, jM3508getUnspecified0d7_KjU15, jM3508getUnspecified0d7_KjU16, jM3508getUnspecified0d7_KjU17, jM3508getUnspecified0d7_KjU18, jM3508getUnspecified0d7_KjU19, jM3508getUnspecified0d7_KjU20, jM3508getUnspecified0d7_KjU21, jM3508getUnspecified0d7_KjU22, jM3508getUnspecified0d7_KjU23, jM3508getUnspecified0d7_KjU24, jM3508getUnspecified0d7_KjU25, jM3508getUnspecified0d7_KjU26, jM3508getUnspecified0d7_KjU27, jM3508getUnspecified0d7_KjU28, jM3508getUnspecified0d7_KjU29, jM3508getUnspecified0d7_KjU30, jM3508getUnspecified0d7_KjU31, jM3508getUnspecified0d7_KjU32, jM3508getUnspecified0d7_KjU33, jM3508getUnspecified0d7_KjU34, jM3508getUnspecified0d7_KjU35, jM3508getUnspecified0d7_KjU36, jM3508getUnspecified0d7_KjU37, jM3508getUnspecified0d7_KjU38, jM3508getUnspecified0d7_KjU39, jM3508getUnspecified0d7_KjU40, jM3508getUnspecified0d7_KjU41, jM3508getUnspecified0d7_KjU42);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldColorsM2070copyejIjP34;
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1746contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m525PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColors(ColorScheme colorScheme, Composer composer, int i10) {
        composer.startReplaceableGroup(-292363577);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-292363577, i10, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-defaultOutlinedTextFieldColors> (TextFieldDefaults.kt:1641)");
        }
        TextFieldColors defaultOutlinedTextFieldColorsCached = colorScheme.getDefaultOutlinedTextFieldColorsCached();
        if (defaultOutlinedTextFieldColorsCached == null) {
            OutlinedTextFieldTokens outlinedTextFieldTokens = OutlinedTextFieldTokens.INSTANCE;
            long jFromToken = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusInputColor());
            long jFromToken2 = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputColor());
            long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            long jFromToken3 = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorInputColor());
            Color.Companion companion = Color.INSTANCE;
            TextFieldColors textFieldColors = new TextFieldColors(jFromToken, jFromToken2, jM3471copywmQWz5c$default, jFromToken3, companion.m3507getTransparent0d7_KjU(), companion.m3507getTransparent0d7_KjU(), companion.m3507getTransparent0d7_KjU(), companion.m3507getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getCaretColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorFocusCaretColor()), (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getOutlineColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledOutlineColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getLeadingIconColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getTrailingIconColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusLabelColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getLabelColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledLabelColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorLabelColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusSupportingColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getSupportingColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.getValue(outlinedTextFieldTokens.getDisabledSupportingColor(), composer, 6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorSupportingColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), null);
            colorScheme.setDefaultOutlinedTextFieldColorsCached$material3_release(textFieldColors);
            defaultOutlinedTextFieldColorsCached = textFieldColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultOutlinedTextFieldColorsCached;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1747getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1748getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1749getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getShape(Composer composer, int i10) {
        composer.startReplaceableGroup(-1066756961);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, i10, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:1409)");
        }
        Shape value = ShapesKt.getValue(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1750getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }
}
