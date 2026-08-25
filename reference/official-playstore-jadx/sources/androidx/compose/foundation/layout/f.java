package androidx.compose.foundation.layout;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static float a(SideCalculator sideCalculator, float f10, float f11) {
        float fMotionOf = sideCalculator.motionOf(f10, f11);
        if (fMotionOf > 0.0f) {
            return 0.0f;
        }
        return fMotionOf;
    }

    public static float b(SideCalculator sideCalculator, float f10, float f11) {
        float fMotionOf = sideCalculator.motionOf(f10, f11);
        if (fMotionOf < 0.0f) {
            return 0.0f;
        }
        return fMotionOf;
    }
}
