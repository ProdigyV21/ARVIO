package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/MdbScrobbleClearResponse;", "", "action", "", "deleted", "", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "getAction", "()Ljava/lang/String;", "getDeleted", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/arflix/tv/data/api/MdbScrobbleClearResponse;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbScrobbleClearResponse {
    public static final int $stable = 0;
    private final String action;
    private final Boolean deleted;

    /* JADX WARN: Multi-variable type inference failed */
    public MdbScrobbleClearResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ MdbScrobbleClearResponse copy$default(MdbScrobbleClearResponse mdbScrobbleClearResponse, String str, Boolean bool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbScrobbleClearResponse.action;
        }
        if ((i10 & 2) != 0) {
            bool = mdbScrobbleClearResponse.deleted;
        }
        return mdbScrobbleClearResponse.copy(str, bool);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getDeleted() {
        return this.deleted;
    }

    public final MdbScrobbleClearResponse copy(String action, Boolean deleted) {
        return new MdbScrobbleClearResponse(action, deleted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbScrobbleClearResponse)) {
            return false;
        }
        MdbScrobbleClearResponse mdbScrobbleClearResponse = (MdbScrobbleClearResponse) other;
        return p.a(this.action, mdbScrobbleClearResponse.action) && p.a(this.deleted, mdbScrobbleClearResponse.deleted);
    }

    public final String getAction() {
        return this.action;
    }

    public final Boolean getDeleted() {
        return this.deleted;
    }

    public int hashCode() {
        String str = this.action;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Boolean bool = this.deleted;
        return iHashCode + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "MdbScrobbleClearResponse(action=" + this.action + ", deleted=" + this.deleted + ")";
    }

    public MdbScrobbleClearResponse(String str, Boolean bool) {
        this.action = str;
        this.deleted = bool;
    }

    public /* synthetic */ MdbScrobbleClearResponse(String str, Boolean bool, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : bool);
    }
}
