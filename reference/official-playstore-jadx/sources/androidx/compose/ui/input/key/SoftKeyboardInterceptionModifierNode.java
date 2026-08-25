package androidx.compose.ui.input.key;

import androidx.compose.ui.node.DelegatableNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0007ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/key/SoftKeyboardInterceptionModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "onInterceptKeyBeforeSoftKeyboard", "", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "onInterceptKeyBeforeSoftKeyboard-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onPreInterceptKeyBeforeSoftKeyboard", "onPreInterceptKeyBeforeSoftKeyboard-ZmokQxo", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SoftKeyboardInterceptionModifierNode extends DelegatableNode {
    /* JADX INFO: renamed from: onInterceptKeyBeforeSoftKeyboard-ZmokQxo */
    boolean mo4088onInterceptKeyBeforeSoftKeyboardZmokQxo(android.view.KeyEvent event);

    /* JADX INFO: renamed from: onPreInterceptKeyBeforeSoftKeyboard-ZmokQxo */
    boolean mo4089onPreInterceptKeyBeforeSoftKeyboardZmokQxo(android.view.KeyEvent event);
}
