package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a'\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "onInterceptKeyBeforeSoftKeyboard", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "onPreInterceptKeyBeforeSoftKeyboard", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SoftwareKeyboardInterceptionModifierKt {
    public static final Modifier onInterceptKeyBeforeSoftKeyboard(Modifier modifier, l<? super KeyEvent, Boolean> lVar) {
        return modifier.then(new SoftKeyboardInterceptionElement(lVar, null));
    }

    public static final Modifier onPreInterceptKeyBeforeSoftKeyboard(Modifier modifier, l<? super KeyEvent, Boolean> lVar) {
        return modifier.then(new SoftKeyboardInterceptionElement(null, lVar));
    }
}
