package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"RadiansToDegrees", "", "degrees", "radians", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DegreesKt {
    private static final float RadiansToDegrees = 57.29578f;

    public static final float degrees(float f10) {
        return f10 * RadiansToDegrees;
    }
}
