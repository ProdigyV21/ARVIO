package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/repository/ImportedProfileAvatar;", "", "version", "", "storagePath", "", "<init>", "(JLjava/lang/String;)V", "getVersion", "()J", "getStoragePath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ImportedProfileAvatar {
    public static final int $stable = 0;
    private final String storagePath;
    private final long version;

    public ImportedProfileAvatar(long j10, String str) {
        this.version = j10;
        this.storagePath = str;
    }

    public static /* synthetic */ ImportedProfileAvatar copy$default(ImportedProfileAvatar importedProfileAvatar, long j10, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = importedProfileAvatar.version;
        }
        if ((i10 & 2) != 0) {
            str = importedProfileAvatar.storagePath;
        }
        return importedProfileAvatar.copy(j10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStoragePath() {
        return this.storagePath;
    }

    public final ImportedProfileAvatar copy(long version, String storagePath) {
        return new ImportedProfileAvatar(version, storagePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImportedProfileAvatar)) {
            return false;
        }
        ImportedProfileAvatar importedProfileAvatar = (ImportedProfileAvatar) other;
        return this.version == importedProfileAvatar.version && kotlin.jvm.internal.p.a(this.storagePath, importedProfileAvatar.storagePath);
    }

    public final String getStoragePath() {
        return this.storagePath;
    }

    public final long getVersion() {
        return this.version;
    }

    public int hashCode() {
        long j10 = this.version;
        int i10 = ((int) (j10 ^ (j10 >>> 32))) * 31;
        String str = this.storagePath;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "ImportedProfileAvatar(version=" + this.version + ", storagePath=" + this.storagePath + ")";
    }
}
