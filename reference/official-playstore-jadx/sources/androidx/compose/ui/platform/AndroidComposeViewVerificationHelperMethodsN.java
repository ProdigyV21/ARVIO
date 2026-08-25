package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.input.pointer.AndroidPointerIcon;
import androidx.compose.ui.input.pointer.AndroidPointerIconType;
import androidx.compose.ui.input.pointer.PointerIcon;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewVerificationHelperMethodsN;", "", "<init>", "()V", "Landroid/view/View;", "view", "Landroidx/compose/ui/input/pointer/PointerIcon;", "icon", "Lx6/t0;", "setPointerIcon", "(Landroid/view/View;Landroidx/compose/ui/input/pointer/PointerIcon;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AndroidComposeViewVerificationHelperMethodsN {
    public static final AndroidComposeViewVerificationHelperMethodsN INSTANCE = new AndroidComposeViewVerificationHelperMethodsN();

    private AndroidComposeViewVerificationHelperMethodsN() {
    }

    public final void setPointerIcon(View view, PointerIcon icon) {
        android.view.PointerIcon pointerIcon = icon instanceof AndroidPointerIcon ? ((AndroidPointerIcon) icon).getPointerIcon() : icon instanceof AndroidPointerIconType ? android.view.PointerIcon.getSystemIcon(view.getContext(), ((AndroidPointerIconType) icon).getType()) : android.view.PointerIcon.getSystemIcon(view.getContext(), 1000);
        if (kotlin.jvm.internal.p.a(view.getPointerIcon(), pointerIcon)) {
            return;
        }
        view.setPointerIcon(pointerIcon);
    }
}
