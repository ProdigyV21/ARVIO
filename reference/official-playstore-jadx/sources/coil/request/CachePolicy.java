package coil.request;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcoil/request/CachePolicy;", "", "readEnabled", "", "writeEnabled", "(Ljava/lang/String;IZZ)V", "getReadEnabled", "()Z", "getWriteEnabled", "ENABLED", "READ_ONLY", "WRITE_ONLY", "DISABLED", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CachePolicy {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CachePolicy[] $VALUES;
    private final boolean readEnabled;
    private final boolean writeEnabled;
    public static final CachePolicy ENABLED = new CachePolicy("ENABLED", 0, true, true);
    public static final CachePolicy READ_ONLY = new CachePolicy("READ_ONLY", 1, true, false);
    public static final CachePolicy WRITE_ONLY = new CachePolicy("WRITE_ONLY", 2, false, true);
    public static final CachePolicy DISABLED = new CachePolicy("DISABLED", 3, false, false);

    private static final /* synthetic */ CachePolicy[] $values() {
        return new CachePolicy[]{ENABLED, READ_ONLY, WRITE_ONLY, DISABLED};
    }

    static {
        CachePolicy[] cachePolicyArr$values = $values();
        $VALUES = cachePolicyArr$values;
        $ENTRIES = new g7.b(cachePolicyArr$values);
    }

    private CachePolicy(String str, int i10, boolean z, boolean z5) {
        this.readEnabled = z;
        this.writeEnabled = z5;
    }

    public static g7.a<CachePolicy> getEntries() {
        return $ENTRIES;
    }

    public static CachePolicy valueOf(String str) {
        return (CachePolicy) Enum.valueOf(CachePolicy.class, str);
    }

    public static CachePolicy[] values() {
        return (CachePolicy[]) $VALUES.clone();
    }

    public final boolean getReadEnabled() {
        return this.readEnabled;
    }

    public final boolean getWriteEnabled() {
        return this.writeEnabled;
    }
}
