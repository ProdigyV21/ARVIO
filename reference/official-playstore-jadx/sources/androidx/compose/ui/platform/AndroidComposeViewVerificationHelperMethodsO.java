package androidx.compose.ui.platform;

import android.view.View;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0007\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewVerificationHelperMethodsO;", "", "<init>", "()V", "Landroid/view/View;", "view", "", "focusable", "", "defaultFocusHighlightEnabled", "Lx6/t0;", "(Landroid/view/View;IZ)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AndroidComposeViewVerificationHelperMethodsO {
    public static final AndroidComposeViewVerificationHelperMethodsO INSTANCE = new AndroidComposeViewVerificationHelperMethodsO();

    private AndroidComposeViewVerificationHelperMethodsO() {
    }

    public final void focusable(View view, int focusable, boolean defaultFocusHighlightEnabled) {
        view.setFocusable(focusable);
        view.setDefaultFocusHighlightEnabled(defaultFocusHighlightEnabled);
    }
}
