package androidx.compose.ui.text.input;

import android.os.Build;
import android.view.inputmethod.InputConnection;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroid/view/inputmethod/InputConnection;", "delegate", "Lkotlin/Function0;", "Lx6/t0;", "onConnectionClosed", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "NullableInputConnectionWrapper", "(Landroid/view/inputmethod/InputConnection;Lr7/a;)Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NullableInputConnectionWrapper_androidKt {
    public static final NullableInputConnectionWrapper NullableInputConnectionWrapper(InputConnection inputConnection, r7.a<t0> aVar) {
        int i10 = Build.VERSION.SDK_INT;
        return i10 >= 25 ? new NullableInputConnectionWrapperApi25(inputConnection, aVar) : i10 >= 24 ? new NullableInputConnectionWrapperApi24(inputConnection, aVar) : new NullableInputConnectionWrapperApi21(inputConnection, aVar);
    }
}
