package androidx.compose.material3;

import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetDefaults;", "", "()V", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isFocusable", "", "shouldDismissOnBackPress", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheetDefaults {
    public static final int $stable = 0;
    public static final ModalBottomSheetDefaults INSTANCE = new ModalBottomSheetDefaults();

    private ModalBottomSheetDefaults() {
    }

    public static /* synthetic */ ModalBottomSheetProperties properties$default(ModalBottomSheetDefaults modalBottomSheetDefaults, SecureFlagPolicy secureFlagPolicy, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            secureFlagPolicy = SecureFlagPolicy.Inherit;
        }
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            z5 = true;
        }
        return modalBottomSheetDefaults.properties(secureFlagPolicy, z, z5);
    }

    public final ModalBottomSheetProperties properties(SecureFlagPolicy securePolicy, boolean isFocusable, boolean shouldDismissOnBackPress) {
        return new ModalBottomSheetProperties(securePolicy, isFocusable, shouldDismissOnBackPress);
    }
}
