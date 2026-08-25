package androidx.compose.foundation.text2;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.foundation.text2.input.ImeActionHandler;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.InputTransformationKt;
import androidx.compose.foundation.text2.input.TextFieldLineLimits;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BasicSecureTextFieldKt$BasicSecureTextField$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ CodepointTransformation $codepointTransformation;
    final /* synthetic */ Brush $cursorBrush;
    final /* synthetic */ TextFieldDecorator $decorator;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ int $imeAction;
    final /* synthetic */ InputTransformation $inputTransformation;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ int $keyboardType;
    final /* synthetic */ ImeActionHandler $onSubmit;
    final /* synthetic */ p<Density, a<TextLayoutResult>, t0> $onTextLayout;
    final /* synthetic */ boolean $revealLastTypedEnabled;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ SecureTextFieldController $secureTextFieldController;
    final /* synthetic */ Modifier $secureTextFieldModifier;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ TextStyle $textStyle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicSecureTextFieldKt$BasicSecureTextField$3(boolean z, InputTransformation inputTransformation, SecureTextFieldController secureTextFieldController, int i10, int i11, ImeActionHandler imeActionHandler, TextFieldState textFieldState, Modifier modifier, boolean z5, TextStyle textStyle, p<? super Density, ? super a<TextLayoutResult>, t0> pVar, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState) {
        super(2);
        this.$revealLastTypedEnabled = z;
        this.$inputTransformation = inputTransformation;
        this.$secureTextFieldController = secureTextFieldController;
        this.$keyboardType = i10;
        this.$imeAction = i11;
        this.$onSubmit = imeActionHandler;
        this.$state = textFieldState;
        this.$secureTextFieldModifier = modifier;
        this.$enabled = z5;
        this.$textStyle = textStyle;
        this.$onTextLayout = pVar;
        this.$interactionSource = mutableInteractionSource;
        this.$cursorBrush = brush;
        this.$codepointTransformation = codepointTransformation;
        this.$decorator = textFieldDecorator;
        this.$scrollState = scrollState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        KeyboardActions KeyboardActions;
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1415093334, i10, -1, "androidx.compose.foundation.text2.BasicSecureTextField.<anonymous> (BasicSecureTextField.kt:323)");
        }
        InputTransformation inputTransformationThenOrNull = this.$revealLastTypedEnabled ? InputTransformationKt.thenOrNull(this.$inputTransformation, this.$secureTextFieldController.getPasswordRevealFilter()) : this.$inputTransformation;
        TextFieldLineLimits.SingleLine singleLine = TextFieldLineLimits.SingleLine.INSTANCE;
        KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, this.$keyboardType, this.$imeAction, null, 17, null);
        ImeActionHandler imeActionHandler = this.$onSubmit;
        if (imeActionHandler == null || (KeyboardActions = BasicSecureTextFieldKt.KeyboardActions(new BasicSecureTextFieldKt$BasicSecureTextField$3$1$1(imeActionHandler))) == null) {
            KeyboardActions = KeyboardActions.INSTANCE.getDefault();
        }
        BasicTextField2Kt.BasicTextField2(this.$state, this.$secureTextFieldModifier, this.$enabled, false, inputTransformationThenOrNull, this.$textStyle, keyboardOptions, KeyboardActions, singleLine, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$codepointTransformation, this.$decorator, this.$scrollState, composer, 100666368, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
