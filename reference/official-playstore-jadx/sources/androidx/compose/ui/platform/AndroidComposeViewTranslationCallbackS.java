package androidx.compose.ui.platform;

import android.view.View;
import android.view.translation.ViewTranslationCallback;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewTranslationCallbackS;", "", "<init>", "()V", "Landroid/view/View;", "view", "Landroid/view/translation/ViewTranslationCallback;", "translationCallback", "Lx6/t0;", "setViewTranslationCallback", "(Landroid/view/View;Landroid/view/translation/ViewTranslationCallback;)V", "clearViewTranslationCallback", "(Landroid/view/View;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeViewTranslationCallbackS {
    public static final int $stable = 0;
    public static final AndroidComposeViewTranslationCallbackS INSTANCE = new AndroidComposeViewTranslationCallbackS();

    private AndroidComposeViewTranslationCallbackS() {
    }

    public final void clearViewTranslationCallback(View view) {
        view.clearViewTranslationCallback();
    }

    public final void setViewTranslationCallback(View view, ViewTranslationCallback translationCallback) {
        view.setViewTranslationCallback(translationCallback);
    }
}
