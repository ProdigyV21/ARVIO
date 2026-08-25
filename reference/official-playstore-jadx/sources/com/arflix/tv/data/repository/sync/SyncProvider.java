package com.arflix.tv.data.repository.sync;

import g7.b;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SyncProvider;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "TRAKT", "MDBLIST", "SIMKL", "toStorage", "", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncProvider {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SyncProvider[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final SyncProvider NONE = new SyncProvider("NONE", 0);
    public static final SyncProvider TRAKT = new SyncProvider("TRAKT", 1);
    public static final SyncProvider MDBLIST = new SyncProvider("MDBLIST", 2);
    public static final SyncProvider SIMKL = new SyncProvider("SIMKL", 3);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SyncProvider$Companion;", "", "<init>", "()V", "fromStorage", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "value", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final SyncProvider fromStorage(String value) {
            String lowerCase = value != null ? value.toLowerCase(Locale.ROOT) : null;
            if (lowerCase != null) {
                int iHashCode = lowerCase.hashCode();
                if (iHashCode != 109441144) {
                    if (iHashCode != 110621260) {
                        if (iHashCode == 910395593 && lowerCase.equals("mdblist")) {
                            return SyncProvider.MDBLIST;
                        }
                    } else if (lowerCase.equals("trakt")) {
                        return SyncProvider.TRAKT;
                    }
                } else if (lowerCase.equals("simkl")) {
                    return SyncProvider.SIMKL;
                }
            }
            return SyncProvider.NONE;
        }

        private Companion() {
        }
    }

    private static final /* synthetic */ SyncProvider[] $values() {
        return new SyncProvider[]{NONE, TRAKT, MDBLIST, SIMKL};
    }

    static {
        SyncProvider[] syncProviderArr$values = $values();
        $VALUES = syncProviderArr$values;
        $ENTRIES = new b(syncProviderArr$values);
        INSTANCE = new Companion(null);
    }

    private SyncProvider(String str, int i10) {
    }

    public static g7.a<SyncProvider> getEntries() {
        return $ENTRIES;
    }

    public static SyncProvider valueOf(String str) {
        return (SyncProvider) Enum.valueOf(SyncProvider.class, str);
    }

    public static SyncProvider[] values() {
        return (SyncProvider[]) $VALUES.clone();
    }

    public final String toStorage() {
        return name().toLowerCase(Locale.ROOT);
    }
}
