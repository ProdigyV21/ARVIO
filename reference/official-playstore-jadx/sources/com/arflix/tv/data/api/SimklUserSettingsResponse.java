package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/api/SimklUserSettingsResponse;", "", "user", "Lcom/arflix/tv/data/api/SimklUser;", "<init>", "(Lcom/arflix/tv/data/api/SimklUser;)V", "getUser", "()Lcom/arflix/tv/data/api/SimklUser;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklUserSettingsResponse {
    public static final int $stable = 0;

    @SerializedName("user")
    private final SimklUser user;

    /* JADX WARN: Multi-variable type inference failed */
    public SimklUserSettingsResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ SimklUserSettingsResponse copy$default(SimklUserSettingsResponse simklUserSettingsResponse, SimklUser simklUser, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            simklUser = simklUserSettingsResponse.user;
        }
        return simklUserSettingsResponse.copy(simklUser);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SimklUser getUser() {
        return this.user;
    }

    public final SimklUserSettingsResponse copy(SimklUser user) {
        return new SimklUserSettingsResponse(user);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SimklUserSettingsResponse) && p.a(this.user, ((SimklUserSettingsResponse) other).user);
    }

    public final SimklUser getUser() {
        return this.user;
    }

    public int hashCode() {
        SimklUser simklUser = this.user;
        if (simklUser == null) {
            return 0;
        }
        return simklUser.hashCode();
    }

    public String toString() {
        return "SimklUserSettingsResponse(user=" + this.user + ")";
    }

    public SimklUserSettingsResponse(SimklUser simklUser) {
        this.user = simklUser;
    }

    public /* synthetic */ SimklUserSettingsResponse(SimklUser simklUser, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : simklUser);
    }
}
