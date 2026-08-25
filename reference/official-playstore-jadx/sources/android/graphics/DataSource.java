package android.graphics;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcoil/decode/DataSource;", "", "(Ljava/lang/String;I)V", "MEMORY_CACHE", "MEMORY", "DISK", "NETWORK", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DataSource {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ DataSource[] $VALUES;
    public static final DataSource MEMORY_CACHE = new DataSource("MEMORY_CACHE", 0);
    public static final DataSource MEMORY = new DataSource("MEMORY", 1);
    public static final DataSource DISK = new DataSource("DISK", 2);
    public static final DataSource NETWORK = new DataSource("NETWORK", 3);

    private static final /* synthetic */ DataSource[] $values() {
        return new DataSource[]{MEMORY_CACHE, MEMORY, DISK, NETWORK};
    }

    static {
        DataSource[] dataSourceArr$values = $values();
        $VALUES = dataSourceArr$values;
        $ENTRIES = new b(dataSourceArr$values);
    }

    private DataSource(String str, int i10) {
    }

    public static a<DataSource> getEntries() {
        return $ENTRIES;
    }

    public static DataSource valueOf(String str) {
        return (DataSource) Enum.valueOf(DataSource.class, str);
    }

    public static DataSource[] values() {
        return (DataSource[]) $VALUES.clone();
    }
}
