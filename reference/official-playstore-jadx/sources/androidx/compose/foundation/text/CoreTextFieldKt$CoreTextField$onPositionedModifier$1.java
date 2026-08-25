package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CoreTextFieldKt$CoreTextField$onPositionedModifier$1 extends r implements l<LayoutCoordinates, t0> {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ TextFieldValue $value;
    final /* synthetic */ WindowInfo $windowInfo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreTextFieldKt$CoreTextField$onPositionedModifier$1(TextFieldState textFieldState, boolean z, WindowInfo windowInfo, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
        super(1);
        this.$state = textFieldState;
        this.$enabled = z;
        this.$windowInfo = windowInfo;
        this.$manager = textFieldSelectionManager;
        this.$value = textFieldValue;
        this.$offsetMapping = offsetMapping;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutCoordinates) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutCoordinates layoutCoordinates) {
        this.$state.setLayoutCoordinates(layoutCoordinates);
        TextLayoutResultProxy layoutResult = this.$state.getLayoutResult();
        if (layoutResult != null) {
            layoutResult.setInnerTextFieldCoordinates(layoutCoordinates);
        }
        if (this.$enabled) {
            if (this.$state.getHandleState() == HandleState.Selection) {
                if (this.$state.getShowFloatingToolbar() && CoreTextFieldKt.isWindowFocusedBehindFlag(this.$windowInfo)) {
                    this.$manager.showSelectionToolbar$foundation_release();
                } else {
                    this.$manager.hideSelectionToolbar$foundation_release();
                }
                this.$state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.$manager, true));
                this.$state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.$manager, false));
                this.$state.setShowCursorHandle(TextRange.m5186getCollapsedimpl(this.$value.getSelection()));
            } else if (this.$state.getHandleState() == HandleState.Cursor) {
                this.$state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.$manager, true));
            }
            CoreTextFieldKt.notifyFocusedRect(this.$state, this.$value, this.$offsetMapping);
            TextLayoutResultProxy layoutResult2 = this.$state.getLayoutResult();
            if (layoutResult2 != null) {
                TextFieldState textFieldState = this.$state;
                TextFieldValue textFieldValue = this.$value;
                OffsetMapping offsetMapping = this.$offsetMapping;
                TextInputSession inputSession = textFieldState.getInputSession();
                if (inputSession == null || !textFieldState.getHasFocus()) {
                    return;
                }
                TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping, layoutResult2);
            }
        }
    }
}
