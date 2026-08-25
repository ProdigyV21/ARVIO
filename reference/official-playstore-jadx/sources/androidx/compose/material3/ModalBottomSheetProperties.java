package androidx.compose.material3;

import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetProperties;", "", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isFocusable", "", "shouldDismissOnBackPress", "(Landroidx/compose/ui/window/SecureFlagPolicy;ZZ)V", "()Z", "getSecurePolicy", "()Landroidx/compose/ui/window/SecureFlagPolicy;", "getShouldDismissOnBackPress", "equals", "other", "hashCode", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheetProperties {
    public static final int $stable = 0;
    private final boolean isFocusable;
    private final SecureFlagPolicy securePolicy;
    private final boolean shouldDismissOnBackPress;

    public ModalBottomSheetProperties(SecureFlagPolicy secureFlagPolicy, boolean z, boolean z5) {
        this.securePolicy = secureFlagPolicy;
        this.isFocusable = z;
        this.shouldDismissOnBackPress = z5;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModalBottomSheetProperties)) {
            return false;
        }
        ModalBottomSheetProperties modalBottomSheetProperties = (ModalBottomSheetProperties) other;
        return this.securePolicy == modalBottomSheetProperties.securePolicy && this.isFocusable == modalBottomSheetProperties.isFocusable && this.shouldDismissOnBackPress == modalBottomSheetProperties.shouldDismissOnBackPress;
    }

    public final SecureFlagPolicy getSecurePolicy() {
        return this.securePolicy;
    }

    public final boolean getShouldDismissOnBackPress() {
        return this.shouldDismissOnBackPress;
    }

    public int hashCode() {
        return (((this.securePolicy.hashCode() * 31) + (this.isFocusable ? 1231 : 1237)) * 31) + (this.shouldDismissOnBackPress ? 1231 : 1237);
    }

    /* JADX INFO: renamed from: isFocusable, reason: from getter */
    public final boolean getIsFocusable() {
        return this.isFocusable;
    }
}
