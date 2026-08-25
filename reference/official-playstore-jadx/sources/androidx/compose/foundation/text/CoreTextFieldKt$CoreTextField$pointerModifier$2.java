package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CoreTextFieldKt$CoreTextField$pointerModifier$2 extends r implements l<Offset, t0> {
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ boolean $readOnly;
    final /* synthetic */ TextFieldState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreTextFieldKt$CoreTextField$pointerModifier$2(TextFieldState textFieldState, FocusRequester focusRequester, boolean z, TextFieldSelectionManager textFieldSelectionManager, OffsetMapping offsetMapping) {
        super(1);
        this.$state = textFieldState;
        this.$focusRequester = focusRequester;
        this.$readOnly = z;
        this.$manager = textFieldSelectionManager;
        this.$offsetMapping = offsetMapping;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m822invokek4lQ0M(((Offset) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
    public final void m822invokek4lQ0M(long j10) {
        CoreTextFieldKt.tapToFocus(this.$state, this.$focusRequester, !this.$readOnly);
        if (this.$state.getHasFocus()) {
            if (this.$state.getHandleState() == HandleState.Selection) {
                this.$manager.m1033deselect_kEHs6E$foundation_release(Offset.m3225boximpl(j10));
                return;
            }
            TextLayoutResultProxy layoutResult = this.$state.getLayoutResult();
            if (layoutResult != null) {
                TextFieldState textFieldState = this.$state;
                TextFieldDelegate.INSTANCE.m883setCursorOffsetULxng0E$foundation_release(j10, layoutResult, textFieldState.getProcessor(), this.$offsetMapping, textFieldState.getOnValueChange());
                if (textFieldState.getTextDelegate().getText().length() > 0) {
                    textFieldState.setHandleState(HandleState.Cursor);
                }
            }
        }
    }
}
