package androidx.compose.ui.text.input;

import android.view.inputmethod.InputConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0001\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "Landroid/view/inputmethod/InputConnection;", "Lx6/t0;", "disposeDelegate", "()V", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapperApi21;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface NullableInputConnectionWrapper extends InputConnection {
    void disposeDelegate();
}
