package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\fR0\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R0\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/input/key/InterceptedKeyInputNode;", "Landroidx/compose/ui/input/key/SoftKeyboardInterceptionModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "onEvent", "onPreEvent", "<init>", "(Lr7/l;Lr7/l;)V", "event", "onInterceptKeyBeforeSoftKeyboard-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onInterceptKeyBeforeSoftKeyboard", "onPreInterceptKeyBeforeSoftKeyboard-ZmokQxo", "onPreInterceptKeyBeforeSoftKeyboard", "Lr7/l;", "getOnEvent", "()Lr7/l;", "setOnEvent", "(Lr7/l;)V", "getOnPreEvent", "setOnPreEvent", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class InterceptedKeyInputNode extends Modifier.Node implements SoftKeyboardInterceptionModifierNode {
    private l<? super KeyEvent, Boolean> onEvent;
    private l<? super KeyEvent, Boolean> onPreEvent;

    public InterceptedKeyInputNode(l<? super KeyEvent, Boolean> lVar, l<? super KeyEvent, Boolean> lVar2) {
        this.onEvent = lVar;
        this.onPreEvent = lVar2;
    }

    public final l<KeyEvent, Boolean> getOnEvent() {
        return this.onEvent;
    }

    public final l<KeyEvent, Boolean> getOnPreEvent() {
        return this.onPreEvent;
    }

    @Override // androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode
    /* JADX INFO: renamed from: onInterceptKeyBeforeSoftKeyboard-ZmokQxo, reason: not valid java name */
    public boolean mo4088onInterceptKeyBeforeSoftKeyboardZmokQxo(android.view.KeyEvent event) {
        l<? super KeyEvent, Boolean> lVar = this.onEvent;
        if (lVar != null) {
            return ((Boolean) lVar.invoke(KeyEvent.m4384boximpl(event))).booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode
    /* JADX INFO: renamed from: onPreInterceptKeyBeforeSoftKeyboard-ZmokQxo, reason: not valid java name */
    public boolean mo4089onPreInterceptKeyBeforeSoftKeyboardZmokQxo(android.view.KeyEvent event) {
        l<? super KeyEvent, Boolean> lVar = this.onPreEvent;
        if (lVar != null) {
            return ((Boolean) lVar.invoke(KeyEvent.m4384boximpl(event))).booleanValue();
        }
        return false;
    }

    public final void setOnEvent(l<? super KeyEvent, Boolean> lVar) {
        this.onEvent = lVar;
    }

    public final void setOnPreEvent(l<? super KeyEvent, Boolean> lVar) {
        this.onPreEvent = lVar;
    }
}
