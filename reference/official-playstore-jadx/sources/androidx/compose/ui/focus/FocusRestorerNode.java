package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rR*\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\bR \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u00128\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0015\u0012\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/focus/FocusRestorerNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Lkotlin/Function0;", "Landroidx/compose/ui/focus/FocusRequester;", "onRestoreFailed", "<init>", "(Lr7/a;)V", "Landroidx/compose/ui/focus/FocusProperties;", "focusProperties", "Lx6/t0;", "applyFocusProperties", "(Landroidx/compose/ui/focus/FocusProperties;)V", "Lr7/a;", "getOnRestoreFailed", "()Lr7/a;", "setOnRestoreFailed", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusDirection;", "onExit", "Lr7/l;", "onEnter", "getOnEnter$annotations", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusRestorerNode extends Modifier.Node implements FocusPropertiesModifierNode, FocusRequesterModifierNode {
    public static final int $stable = 8;
    private r7.a<FocusRequester> onRestoreFailed;
    private final l<FocusDirection, FocusRequester> onExit = new FocusRestorerNode$onExit$1(this);
    private final l<FocusDirection, FocusRequester> onEnter = new FocusRestorerNode$onEnter$1(this);

    public FocusRestorerNode(r7.a<FocusRequester> aVar) {
        this.onRestoreFailed = aVar;
    }

    private static /* synthetic */ void getOnEnter$annotations() {
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setEnter(this.onEnter);
        focusProperties.setExit(this.onExit);
    }

    public final r7.a<FocusRequester> getOnRestoreFailed() {
        return this.onRestoreFailed;
    }

    public final void setOnRestoreFailed(r7.a<FocusRequester> aVar) {
        this.onRestoreFailed = aVar;
    }
}
