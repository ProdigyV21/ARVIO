package androidx.compose.foundation.text;

import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/focus/FocusState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/focus/FocusState;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CoreTextFieldKt$CoreTextField$focusModifier$1 extends r implements l<FocusState, t0> {
    final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ ImeOptions $imeOptions;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ boolean $readOnly;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ TextInputService $textInputService;
    final /* synthetic */ TextFieldValue $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreTextFieldKt$CoreTextField$focusModifier$1(TextFieldState textFieldState, TextInputService textInputService, boolean z, boolean z5, TextFieldValue textFieldValue, ImeOptions imeOptions, OffsetMapping offsetMapping, TextFieldSelectionManager textFieldSelectionManager, k0 k0Var, BringIntoViewRequester bringIntoViewRequester) {
        super(1);
        this.$state = textFieldState;
        this.$textInputService = textInputService;
        this.$enabled = z;
        this.$readOnly = z5;
        this.$value = textFieldValue;
        this.$imeOptions = imeOptions;
        this.$offsetMapping = offsetMapping;
        this.$manager = textFieldSelectionManager;
        this.$coroutineScope = k0Var;
        this.$bringIntoViewRequester = bringIntoViewRequester;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FocusState) obj);
        return t0.f22605a;
    }

    public final void invoke(FocusState focusState) {
        TextLayoutResultProxy layoutResult;
        if (this.$state.getHasFocus() == focusState.isFocused()) {
            return;
        }
        this.$state.setHasFocus(focusState.isFocused());
        if (this.$textInputService != null) {
            if (this.$state.getHasFocus() && this.$enabled && !this.$readOnly) {
                CoreTextFieldKt.startInputSession(this.$textInputService, this.$state, this.$value, this.$imeOptions, this.$offsetMapping);
            } else {
                CoreTextFieldKt.endInputSession(this.$state);
            }
            if (focusState.isFocused() && (layoutResult = this.$state.getLayoutResult()) != null) {
                m0.p(this.$coroutineScope, null, 0, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1(this.$bringIntoViewRequester, this.$value, this.$state, layoutResult, this.$offsetMapping, null), 3);
            }
        }
        if (focusState.isFocused()) {
            return;
        }
        TextFieldSelectionManager.m1029deselect_kEHs6E$foundation_release$default(this.$manager, null, 1, null);
    }
}
