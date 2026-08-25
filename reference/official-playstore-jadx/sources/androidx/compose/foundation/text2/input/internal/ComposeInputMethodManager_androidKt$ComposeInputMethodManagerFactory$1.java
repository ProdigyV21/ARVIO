package androidx.compose.foundation.text2.input.internal;

import android.os.Build;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManager;", "view", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ComposeInputMethodManager_androidKt$ComposeInputMethodManagerFactory$1 extends r implements l<View, ComposeInputMethodManager> {
    public static final ComposeInputMethodManager_androidKt$ComposeInputMethodManagerFactory$1 INSTANCE = new ComposeInputMethodManager_androidKt$ComposeInputMethodManagerFactory$1();

    public ComposeInputMethodManager_androidKt$ComposeInputMethodManagerFactory$1() {
        super(1);
    }

    @Override // r7.l
    public final ComposeInputMethodManager invoke(View view) {
        return Build.VERSION.SDK_INT >= 24 ? new ComposeInputMethodManagerImplApi24(view) : new ComposeInputMethodManagerImplApi21(view);
    }
}
