package androidx.compose.ui.platform;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/Api29Impl;", "", "()V", "getRecommendedTimeoutMillis", "", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "originalTimeout", "uiContentFlags", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Api29Impl {
    public static final int $stable = 0;
    public static final Api29Impl INSTANCE = new Api29Impl();

    private Api29Impl() {
    }

    public final int getRecommendedTimeoutMillis(android.view.accessibility.AccessibilityManager accessibilityManager, int originalTimeout, int uiContentFlags) {
        return accessibilityManager.getRecommendedTimeoutMillis(originalTimeout, uiContentFlags);
    }
}
