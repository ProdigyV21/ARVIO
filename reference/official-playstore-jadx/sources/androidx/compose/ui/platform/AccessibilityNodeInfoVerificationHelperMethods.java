package androidx.compose.ui.platform;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/platform/AccessibilityNodeInfoVerificationHelperMethods;", "", "<init>", "()V", "Landroid/view/accessibility/AccessibilityNodeInfo;", "node", "", "", "data", "Lx6/t0;", "setAvailableExtraData", "(Landroid/view/accessibility/AccessibilityNodeInfo;Ljava/util/List;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AccessibilityNodeInfoVerificationHelperMethods {
    public static final AccessibilityNodeInfoVerificationHelperMethods INSTANCE = new AccessibilityNodeInfoVerificationHelperMethods();

    private AccessibilityNodeInfoVerificationHelperMethods() {
    }

    public final void setAvailableExtraData(AccessibilityNodeInfo node, List<String> data) {
        node.setAvailableExtraData(data);
    }
}
