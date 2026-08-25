package androidx.tv.material3;

import androidx.compose.ui.state.ToggleableState;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6409a;

    static {
        int[] iArr = new int[ToggleableState.values().length];
        try {
            iArr[ToggleableState.On.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ToggleableState.Off.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ToggleableState.Indeterminate.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f6409a = iArr;
    }
}
