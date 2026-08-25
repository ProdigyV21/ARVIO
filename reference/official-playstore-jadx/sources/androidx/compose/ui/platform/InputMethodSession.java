package androidx.compose.ui.platform;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper;
import androidx.compose.ui.text.input.NullableInputConnectionWrapper_androidKt;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/platform/InputMethodSession;", "", "Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;", "request", "Lkotlin/Function0;", "Lx6/t0;", "onConnectionClosed", "<init>", "(Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;Lr7/a;)V", "Landroid/view/inputmethod/EditorInfo;", "outAttrs", "Landroid/view/inputmethod/InputConnection;", "createInputConnection", "(Landroid/view/inputmethod/EditorInfo;)Landroid/view/inputmethod/InputConnection;", "dispose", "()V", "Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;", "Lr7/a;", "lock", "Ljava/lang/Object;", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "connection", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "", "disposed", "Z", "isActive", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class InputMethodSession {
    private NullableInputConnectionWrapper connection;
    private boolean disposed;
    private final Object lock = new Object();
    private final r7.a<t0> onConnectionClosed;
    private final PlatformTextInputMethodRequest request;

    public InputMethodSession(PlatformTextInputMethodRequest platformTextInputMethodRequest, r7.a<t0> aVar) {
        this.request = platformTextInputMethodRequest;
        this.onConnectionClosed = aVar;
    }

    public final InputConnection createInputConnection(EditorInfo outAttrs) {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return null;
                }
                NullableInputConnectionWrapper nullableInputConnectionWrapper = this.connection;
                if (nullableInputConnectionWrapper != null) {
                    nullableInputConnectionWrapper.disposeDelegate();
                }
                NullableInputConnectionWrapper NullableInputConnectionWrapper = NullableInputConnectionWrapper_androidKt.NullableInputConnectionWrapper(this.request.createInputConnection(outAttrs), this.onConnectionClosed);
                this.connection = NullableInputConnectionWrapper;
                return NullableInputConnectionWrapper;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void dispose() {
        synchronized (this.lock) {
            try {
                this.disposed = true;
                NullableInputConnectionWrapper nullableInputConnectionWrapper = this.connection;
                if (nullableInputConnectionWrapper != null) {
                    nullableInputConnectionWrapper.disposeDelegate();
                }
                this.connection = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isActive() {
        return !this.disposed;
    }
}
