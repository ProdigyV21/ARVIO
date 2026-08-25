package androidx.compose.ui.platform;

import androidx.compose.ui.input.InputMode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/input/InputMode;", "invoke-iuPiT84", "(I)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeView$_inputModeManager$1 extends kotlin.jvm.internal.r implements r7.l<InputMode, Boolean> {
    final /* synthetic */ AndroidComposeView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidComposeView$_inputModeManager$1(AndroidComposeView androidComposeView) {
        super(1);
        this.this$0 = androidComposeView;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m4925invokeiuPiT84(((InputMode) obj).getValue());
    }

    /* JADX INFO: renamed from: invoke-iuPiT84, reason: not valid java name */
    public final Boolean m4925invokeiuPiT84(int i10) {
        InputMode.Companion companion = InputMode.INSTANCE;
        return Boolean.valueOf(InputMode.m4079equalsimpl0(i10, companion.m4084getTouchaOaMEAU()) ? this.this$0.isInTouchMode() : InputMode.m4079equalsimpl0(i10, companion.m4083getKeyboardaOaMEAU()) ? this.this$0.isInTouchMode() ? this.this$0.requestFocusFromTouch() : true : false);
    }
}
