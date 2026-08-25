package coil.transform;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcoil/transform/PixelOpacity;", "", "(Ljava/lang/String;I)V", "UNCHANGED", "TRANSLUCENT", "OPAQUE", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PixelOpacity {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ PixelOpacity[] $VALUES;
    public static final PixelOpacity UNCHANGED = new PixelOpacity("UNCHANGED", 0);
    public static final PixelOpacity TRANSLUCENT = new PixelOpacity("TRANSLUCENT", 1);
    public static final PixelOpacity OPAQUE = new PixelOpacity("OPAQUE", 2);

    private static final /* synthetic */ PixelOpacity[] $values() {
        return new PixelOpacity[]{UNCHANGED, TRANSLUCENT, OPAQUE};
    }

    static {
        PixelOpacity[] pixelOpacityArr$values = $values();
        $VALUES = pixelOpacityArr$values;
        $ENTRIES = new b(pixelOpacityArr$values);
    }

    private PixelOpacity(String str, int i10) {
    }

    public static a<PixelOpacity> getEntries() {
        return $ENTRIES;
    }

    public static PixelOpacity valueOf(String str) {
        return (PixelOpacity) Enum.valueOf(PixelOpacity.class, str);
    }

    public static PixelOpacity[] values() {
        return (PixelOpacity[]) $VALUES.clone();
    }
}
