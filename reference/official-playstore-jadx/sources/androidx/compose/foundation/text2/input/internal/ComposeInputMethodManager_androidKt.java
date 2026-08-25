package androidx.compose.foundation.text2.input.internal;

import android.view.View;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a/\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0005H\u0001¢\u0006\u0004\b\u0007\u0010\b\"\"\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroid/view/View;", "view", "Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManager;", "ComposeInputMethodManager", "(Landroid/view/View;)Landroidx/compose/foundation/text2/input/internal/ComposeInputMethodManager;", "Lkotlin/Function1;", "factory", "overrideComposeInputMethodManagerFactoryForTests", "(Lr7/l;)Lr7/l;", "ComposeInputMethodManagerFactory", "Lr7/l;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComposeInputMethodManager_androidKt {
    private static l<? super View, ? extends ComposeInputMethodManager> ComposeInputMethodManagerFactory = ComposeInputMethodManager_androidKt$ComposeInputMethodManagerFactory$1.INSTANCE;

    public static final ComposeInputMethodManager ComposeInputMethodManager(View view) {
        return (ComposeInputMethodManager) ComposeInputMethodManagerFactory.invoke(view);
    }

    public static final l<View, ComposeInputMethodManager> overrideComposeInputMethodManagerFactoryForTests(l<? super View, ? extends ComposeInputMethodManager> lVar) {
        l lVar2 = ComposeInputMethodManagerFactory;
        ComposeInputMethodManagerFactory = lVar;
        return lVar2;
    }
}
