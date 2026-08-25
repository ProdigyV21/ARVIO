package android.graphics;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcoil/decode/ExifOrientationPolicy;", "", "(Ljava/lang/String;I)V", "IGNORE", "RESPECT_PERFORMANCE", "RESPECT_ALL", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ExifOrientationPolicy {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ ExifOrientationPolicy[] $VALUES;
    public static final ExifOrientationPolicy IGNORE = new ExifOrientationPolicy("IGNORE", 0);
    public static final ExifOrientationPolicy RESPECT_PERFORMANCE = new ExifOrientationPolicy("RESPECT_PERFORMANCE", 1);
    public static final ExifOrientationPolicy RESPECT_ALL = new ExifOrientationPolicy("RESPECT_ALL", 2);

    private static final /* synthetic */ ExifOrientationPolicy[] $values() {
        return new ExifOrientationPolicy[]{IGNORE, RESPECT_PERFORMANCE, RESPECT_ALL};
    }

    static {
        ExifOrientationPolicy[] exifOrientationPolicyArr$values = $values();
        $VALUES = exifOrientationPolicyArr$values;
        $ENTRIES = new b(exifOrientationPolicyArr$values);
    }

    private ExifOrientationPolicy(String str, int i10) {
    }

    public static a<ExifOrientationPolicy> getEntries() {
        return $ENTRIES;
    }

    public static ExifOrientationPolicy valueOf(String str) {
        return (ExifOrientationPolicy) Enum.valueOf(ExifOrientationPolicy.class, str);
    }

    public static ExifOrientationPolicy[] values() {
        return (ExifOrientationPolicy[]) $VALUES.clone();
    }
}
