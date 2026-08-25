package androidx.compose.foundation.text2;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text2.input.ImeActionHandler;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class BasicSecureTextFieldKt$BasicSecureTextField$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ Brush $cursorBrush;
    final /* synthetic */ TextFieldDecorator $decorator;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ int $imeAction;
    final /* synthetic */ InputTransformation $inputTransformation;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ int $keyboardType;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ ImeActionHandler $onSubmit;
    final /* synthetic */ p<Density, a<TextLayoutResult>, t0> $onTextLayout;
    final /* synthetic */ l<String, t0> $onValueChange;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ int $textObfuscationMode;
    final /* synthetic */ TextStyle $textStyle;
    final /* synthetic */ String $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicSecureTextFieldKt$BasicSecureTextField$2(String str, l<? super String, t0> lVar, Modifier modifier, ImeActionHandler imeActionHandler, int i10, int i11, int i12, boolean z, InputTransformation inputTransformation, TextStyle textStyle, MutableInteractionSource mutableInteractionSource, Brush brush, p<? super Density, ? super a<TextLayoutResult>, t0> pVar, TextFieldDecorator textFieldDecorator, ScrollState scrollState, int i13, int i14, int i15) {
        super(2);
        this.$value = str;
        this.$onValueChange = lVar;
        this.$modifier = modifier;
        this.$onSubmit = imeActionHandler;
        this.$imeAction = i10;
        this.$textObfuscationMode = i11;
        this.$keyboardType = i12;
        this.$enabled = z;
        this.$inputTransformation = inputTransformation;
        this.$textStyle = textStyle;
        this.$interactionSource = mutableInteractionSource;
        this.$cursorBrush = brush;
        this.$onTextLayout = pVar;
        this.$decorator = textFieldDecorator;
        this.$scrollState = scrollState;
        this.$$changed = i13;
        this.$$changed1 = i14;
        this.$$default = i15;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        BasicSecureTextFieldKt.m1047BasicSecureTextFieldTLP4tmw(this.$value, this.$onValueChange, this.$modifier, this.$onSubmit, this.$imeAction, this.$textObfuscationMode, this.$keyboardType, this.$enabled, this.$inputTransformation, this.$textStyle, this.$interactionSource, this.$cursorBrush, this.$onTextLayout, this.$decorator, this.$scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
