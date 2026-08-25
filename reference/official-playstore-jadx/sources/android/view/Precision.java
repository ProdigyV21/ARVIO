package android.view;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcoil/size/Precision;", "", "(Ljava/lang/String;I)V", "EXACT", "INEXACT", "AUTOMATIC", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Precision {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ Precision[] $VALUES;
    public static final Precision EXACT = new Precision("EXACT", 0);
    public static final Precision INEXACT = new Precision("INEXACT", 1);
    public static final Precision AUTOMATIC = new Precision("AUTOMATIC", 2);

    private static final /* synthetic */ Precision[] $values() {
        return new Precision[]{EXACT, INEXACT, AUTOMATIC};
    }

    static {
        Precision[] precisionArr$values = $values();
        $VALUES = precisionArr$values;
        $ENTRIES = new b(precisionArr$values);
    }

    private Precision(String str, int i10) {
    }

    public static a<Precision> getEntries() {
        return $ENTRIES;
    }

    public static Precision valueOf(String str) {
        return (Precision) Enum.valueOf(Precision.class, str);
    }

    public static Precision[] values() {
        return (Precision[]) $VALUES.clone();
    }
}
