package com.arflix.tv.domain.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/domain/model/RepositoryType;", "", "<init>", "(Ljava/lang/String;I)V", "NUVIO_JS", "EXTERNAL_DEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RepositoryType {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ RepositoryType[] $VALUES;
    public static final RepositoryType NUVIO_JS = new RepositoryType("NUVIO_JS", 0);
    public static final RepositoryType EXTERNAL_DEX = new RepositoryType("EXTERNAL_DEX", 1);

    private static final /* synthetic */ RepositoryType[] $values() {
        return new RepositoryType[]{NUVIO_JS, EXTERNAL_DEX};
    }

    static {
        RepositoryType[] repositoryTypeArr$values = $values();
        $VALUES = repositoryTypeArr$values;
        $ENTRIES = new b(repositoryTypeArr$values);
    }

    private RepositoryType(String str, int i10) {
    }

    public static a<RepositoryType> getEntries() {
        return $ENTRIES;
    }

    public static RepositoryType valueOf(String str) {
        return (RepositoryType) Enum.valueOf(RepositoryType.class, str);
    }

    public static RepositoryType[] values() {
        return (RepositoryType[]) $VALUES.clone();
    }
}
