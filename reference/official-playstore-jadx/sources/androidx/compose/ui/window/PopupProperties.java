package androidx.compose.ui.window;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001BC\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nBM\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/window/PopupProperties;", "", "focusable", "", "dismissOnBackPress", "dismissOnClickOutside", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "excludeFromSystemGesture", "clippingEnabled", "(ZZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZ)V", "usePlatformDefaultWidth", "(ZZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZZ)V", "getClippingEnabled", "()Z", "getDismissOnBackPress", "getDismissOnClickOutside", "getExcludeFromSystemGesture", "getFocusable", "getSecurePolicy", "()Landroidx/compose/ui/window/SecureFlagPolicy;", "getUsePlatformDefaultWidth", "equals", "other", "hashCode", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PopupProperties {
    public static final int $stable = 0;
    private final boolean clippingEnabled;
    private final boolean dismissOnBackPress;
    private final boolean dismissOnClickOutside;
    private final boolean excludeFromSystemGesture;
    private final boolean focusable;
    private final SecureFlagPolicy securePolicy;
    private final boolean usePlatformDefaultWidth;

    public PopupProperties() {
        this(false, false, false, null, false, false, false, 127, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PopupProperties)) {
            return false;
        }
        PopupProperties popupProperties = (PopupProperties) other;
        return this.focusable == popupProperties.focusable && this.dismissOnBackPress == popupProperties.dismissOnBackPress && this.dismissOnClickOutside == popupProperties.dismissOnClickOutside && this.securePolicy == popupProperties.securePolicy && this.excludeFromSystemGesture == popupProperties.excludeFromSystemGesture && this.clippingEnabled == popupProperties.clippingEnabled && this.usePlatformDefaultWidth == popupProperties.usePlatformDefaultWidth;
    }

    public final boolean getClippingEnabled() {
        return this.clippingEnabled;
    }

    public final boolean getDismissOnBackPress() {
        return this.dismissOnBackPress;
    }

    public final boolean getDismissOnClickOutside() {
        return this.dismissOnClickOutside;
    }

    public final boolean getExcludeFromSystemGesture() {
        return this.excludeFromSystemGesture;
    }

    public final boolean getFocusable() {
        return this.focusable;
    }

    public final SecureFlagPolicy getSecurePolicy() {
        return this.securePolicy;
    }

    public final boolean getUsePlatformDefaultWidth() {
        return this.usePlatformDefaultWidth;
    }

    public int hashCode() {
        boolean z = this.dismissOnBackPress;
        return ((((((this.securePolicy.hashCode() + ((((((((z ? 1231 : 1237) * 31) + (this.focusable ? 1231 : 1237)) * 31) + (z ? 1231 : 1237)) * 31) + (this.dismissOnClickOutside ? 1231 : 1237)) * 31)) * 31) + (this.excludeFromSystemGesture ? 1231 : 1237)) * 31) + (this.clippingEnabled ? 1231 : 1237)) * 31) + (this.usePlatformDefaultWidth ? 1231 : 1237);
    }

    public PopupProperties(boolean z, boolean z5, boolean z10, SecureFlagPolicy secureFlagPolicy, boolean z11, boolean z12, boolean z13) {
        this.focusable = z;
        this.dismissOnBackPress = z5;
        this.dismissOnClickOutside = z10;
        this.securePolicy = secureFlagPolicy;
        this.excludeFromSystemGesture = z11;
        this.clippingEnabled = z12;
        this.usePlatformDefaultWidth = z13;
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z5, boolean z10, SecureFlagPolicy secureFlagPolicy, boolean z11, boolean z12, boolean z13, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? true : z5, (i10 & 4) != 0 ? true : z10, (i10 & 8) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i10 & 16) != 0 ? true : z11, (i10 & 32) != 0 ? true : z12, (i10 & 64) != 0 ? false : z13);
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ PopupProperties(boolean r2, boolean r3, boolean r4, androidx.compose.ui.window.SecureFlagPolicy r5, boolean r6, boolean r7, int r8, kotlin.jvm.internal.h r9) {
        /*
            r1 = this;
            r9 = r8 & 1
            if (r9 == 0) goto L5
            r2 = 0
        L5:
            r9 = r8 & 2
            r0 = 1
            if (r9 == 0) goto Lb
            r3 = r0
        Lb:
            r9 = r8 & 4
            if (r9 == 0) goto L10
            r4 = r0
        L10:
            r9 = r8 & 8
            if (r9 == 0) goto L16
            androidx.compose.ui.window.SecureFlagPolicy r5 = androidx.compose.ui.window.SecureFlagPolicy.Inherit
        L16:
            r9 = r8 & 16
            if (r9 == 0) goto L1b
            r6 = r0
        L1b:
            r8 = r8 & 32
            if (r8 == 0) goto L27
            r9 = r0
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            goto L2e
        L27:
            r9 = r7
            r8 = r6
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
        L2e:
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.PopupProperties.<init>(boolean, boolean, boolean, androidx.compose.ui.window.SecureFlagPolicy, boolean, boolean, int, kotlin.jvm.internal.h):void");
    }

    public PopupProperties(boolean z, boolean z5, boolean z10, SecureFlagPolicy secureFlagPolicy, boolean z11, boolean z12) {
        this(z, z5, z10, secureFlagPolicy, z11, z12, false);
    }
}
