package androidx.compose.ui.focus;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/focus/FocusRequester;", "it", "Landroidx/compose/ui/focus/FocusDirection;", "invoke-3ESFkO8", "(I)Landroidx/compose/ui/focus/FocusRequester;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FocusRestorerNode$onEnter$1 extends r implements l<FocusDirection, FocusRequester> {
    final /* synthetic */ FocusRestorerNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusRestorerNode$onEnter$1(FocusRestorerNode focusRestorerNode) {
        super(1);
        this.this$0 = focusRestorerNode;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m3178invoke3ESFkO8(((FocusDirection) obj).getValue());
    }

    /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final FocusRequester m3178invoke3ESFkO8(int i10) {
        FocusRequester focusRequester;
        if (FocusRequesterModifierNodeKt.restoreFocusedChild(this.this$0)) {
            return FocusRequester.INSTANCE.getCancel();
        }
        r7.a<FocusRequester> onRestoreFailed = this.this$0.getOnRestoreFailed();
        return (onRestoreFailed == null || (focusRequester = (FocusRequester) onRestoreFailed.invoke()) == null) ? FocusRequester.INSTANCE.getDefault() : focusRequester;
    }
}
