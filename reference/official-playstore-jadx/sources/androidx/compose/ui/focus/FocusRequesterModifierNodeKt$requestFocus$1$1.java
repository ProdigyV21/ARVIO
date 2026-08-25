package androidx.compose.ui.focus;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/focus/FocusTargetNode;", "invoke", "(Landroidx/compose/ui/focus/FocusTargetNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FocusRequesterModifierNodeKt$requestFocus$1$1 extends r implements l<FocusTargetNode, Boolean> {
    public static final FocusRequesterModifierNodeKt$requestFocus$1$1 INSTANCE = new FocusRequesterModifierNodeKt$requestFocus$1$1();

    public FocusRequesterModifierNodeKt$requestFocus$1$1() {
        super(1);
    }

    @Override // r7.l
    public final Boolean invoke(FocusTargetNode focusTargetNode) {
        return Boolean.valueOf(FocusTransactionsKt.requestFocus(focusTargetNode));
    }
}
