package androidx.compose.foundation.text;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aî\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a23\b\u0002\u0010!\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u001dH\u0007¢\u0006\u0004\b\"\u0010#\u001aî\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020$2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a23\b\u0002\u0010!\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u001dH\u0007¢\u0006\u0004\b\"\u0010%\u001aä\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a23\b\u0002\u0010!\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u001dH\u0007¢\u0006\u0004\b\"\u0010&\u001aä\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020$2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a23\b\u0002\u0010!\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u001dH\u0007¢\u0006\u0004\b\"\u0010'¨\u0006*²\u0006\u000e\u0010(\u001a\u00020$8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010)\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "readOnly", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "singleLine", "", "maxLines", "minLines", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Landroidx/compose/ui/text/TextLayoutResult;", "onTextLayout", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lx6/y;", ContentDisposition.Parameters.Name, "innerTextField", "decorationBox", "BasicTextField", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lr7/l;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lr7/l;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lr7/l;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lr7/l;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "textFieldValueState", "lastTextValue", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTextFieldKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<TextLayoutResult, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(TextLayoutResult textLayoutResult) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextLayoutResult) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$10, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements l<TextLayoutResult, t0> {
        public static final AnonymousClass10 INSTANCE = new AnonymousClass10();

        public AnonymousClass10() {
            super(1);
        }

        public final void invoke(TextLayoutResult textLayoutResult) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextLayoutResult) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$12, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass12 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass12(String str, l<? super String, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10, int i10, VisualTransformation visualTransformation, l<? super TextLayoutResult, t0> lVar2, MutableInteractionSource mutableInteractionSource, Brush brush, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, int i11, int i12, int i13) {
            super(2);
            this.$value = str;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z10;
            this.$maxLines = i10;
            this.$visualTransformation = visualTransformation;
            this.$onTextLayout = lVar2;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$decorationBox = qVar;
            this.$$changed = i11;
            this.$$changed1 = i12;
            this.$$default = i13;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$visualTransformation, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$decorationBox, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$13, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass13 extends r implements l<TextLayoutResult, t0> {
        public static final AnonymousClass13 INSTANCE = new AnonymousClass13();

        public AnonymousClass13() {
            super(1);
        }

        public final void invoke(TextLayoutResult textLayoutResult) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextLayoutResult) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$15, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass15 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass15(TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10, int i10, VisualTransformation visualTransformation, l<? super TextLayoutResult, t0> lVar2, MutableInteractionSource mutableInteractionSource, Brush brush, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, int i11, int i12, int i13) {
            super(2);
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z10;
            this.$maxLines = i10;
            this.$visualTransformation = visualTransformation;
            this.$onTextLayout = lVar2;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$decorationBox = qVar;
            this.$$changed = i11;
            this.$$changed1 = i12;
            this.$$default = i13;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$visualTransformation, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$decorationBox, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(String str, l<? super String, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10, int i10, int i11, VisualTransformation visualTransformation, l<? super TextLayoutResult, t0> lVar2, MutableInteractionSource mutableInteractionSource, Brush brush, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, int i12, int i13, int i14) {
            super(2);
            this.$value = str;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z10;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$visualTransformation = visualTransformation;
            this.$onTextLayout = lVar2;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$decorationBox = qVar;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$default = i14;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$decorationBox, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements l<TextLayoutResult, t0> {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public AnonymousClass6() {
            super(1);
        }

        public final void invoke(TextLayoutResult textLayoutResult) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextLayoutResult) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$9, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10, int i10, int i11, VisualTransformation visualTransformation, l<? super TextLayoutResult, t0> lVar2, MutableInteractionSource mutableInteractionSource, Brush brush, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, int i12, int i13, int i14) {
            super(2);
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z10;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$visualTransformation = visualTransformation;
            this.$onTextLayout = lVar2;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$decorationBox = qVar;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$default = i14;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$decorationBox, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:275:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(java.lang.String r37, r7.l<? super java.lang.String, x6.t0> r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, boolean r45, int r46, int r47, androidx.compose.ui.text.input.VisualTransformation r48, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r49, androidx.compose.foundation.interaction.MutableInteractionSource r50, androidx.compose.ui.graphics.Brush r51, r7.q<? super r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r52, androidx.compose.runtime.Composer r53, int r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 1218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, r7.l, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue BasicTextField$lambda$2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BasicTextField$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:263:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(androidx.compose.ui.text.input.TextFieldValue r36, r7.l<? super androidx.compose.ui.text.input.TextFieldValue, x6.t0> r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.KeyboardActions r43, boolean r44, int r45, int r46, androidx.compose.ui.text.input.VisualTransformation r47, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, r7.q<? super r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r51, androidx.compose.runtime.Composer r52, int r53, int r54, int r55) {
        /*
            Method dump skipped, instruction units count: 1060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, r7.l, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:223:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012b  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void BasicTextField(java.lang.String r36, r7.l r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.KeyboardActions r43, boolean r44, int r45, androidx.compose.ui.text.input.VisualTransformation r46, r7.l r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, androidx.compose.ui.graphics.Brush r49, r7.q r50, androidx.compose.runtime.Composer r51, int r52, int r53, int r54) {
        /*
            Method dump skipped, instruction units count: 838
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, r7.l, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:223:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012b  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void BasicTextField(androidx.compose.ui.text.input.TextFieldValue r36, r7.l r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.KeyboardActions r43, boolean r44, int r45, androidx.compose.ui.text.input.VisualTransformation r46, r7.l r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, androidx.compose.ui.graphics.Brush r49, r7.q r50, androidx.compose.runtime.Composer r51, int r52, int r53, int r54) {
        /*
            Method dump skipped, instruction units count: 838
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, r7.l, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
