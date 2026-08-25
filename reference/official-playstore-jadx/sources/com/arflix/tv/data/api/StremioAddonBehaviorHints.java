package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/StremioAddonBehaviorHints;", "", "adult", "", "p2p", "configurable", "configurationRequired", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getAdult", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getP2p", "getConfigurable", "getConfigurationRequired", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/arflix/tv/data/api/StremioAddonBehaviorHints;", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioAddonBehaviorHints {
    public static final int $stable = 0;
    private final Boolean adult;
    private final Boolean configurable;
    private final Boolean configurationRequired;
    private final Boolean p2p;

    public StremioAddonBehaviorHints() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ StremioAddonBehaviorHints copy$default(StremioAddonBehaviorHints stremioAddonBehaviorHints, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            bool = stremioAddonBehaviorHints.adult;
        }
        if ((i10 & 2) != 0) {
            bool2 = stremioAddonBehaviorHints.p2p;
        }
        if ((i10 & 4) != 0) {
            bool3 = stremioAddonBehaviorHints.configurable;
        }
        if ((i10 & 8) != 0) {
            bool4 = stremioAddonBehaviorHints.configurationRequired;
        }
        return stremioAddonBehaviorHints.copy(bool, bool2, bool3, bool4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Boolean getAdult() {
        return this.adult;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getP2p() {
        return this.p2p;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Boolean getConfigurable() {
        return this.configurable;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Boolean getConfigurationRequired() {
        return this.configurationRequired;
    }

    public final StremioAddonBehaviorHints copy(Boolean adult, Boolean p2p, Boolean configurable, Boolean configurationRequired) {
        return new StremioAddonBehaviorHints(adult, p2p, configurable, configurationRequired);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioAddonBehaviorHints)) {
            return false;
        }
        StremioAddonBehaviorHints stremioAddonBehaviorHints = (StremioAddonBehaviorHints) other;
        return p.a(this.adult, stremioAddonBehaviorHints.adult) && p.a(this.p2p, stremioAddonBehaviorHints.p2p) && p.a(this.configurable, stremioAddonBehaviorHints.configurable) && p.a(this.configurationRequired, stremioAddonBehaviorHints.configurationRequired);
    }

    public final Boolean getAdult() {
        return this.adult;
    }

    public final Boolean getConfigurable() {
        return this.configurable;
    }

    public final Boolean getConfigurationRequired() {
        return this.configurationRequired;
    }

    public final Boolean getP2p() {
        return this.p2p;
    }

    public int hashCode() {
        Boolean bool = this.adult;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.p2p;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.configurable;
        int iHashCode3 = (iHashCode2 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.configurationRequired;
        return iHashCode3 + (bool4 != null ? bool4.hashCode() : 0);
    }

    public String toString() {
        return "StremioAddonBehaviorHints(adult=" + this.adult + ", p2p=" + this.p2p + ", configurable=" + this.configurable + ", configurationRequired=" + this.configurationRequired + ")";
    }

    public StremioAddonBehaviorHints(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        this.adult = bool;
        this.p2p = bool2;
        this.configurable = bool3;
        this.configurationRequired = bool4;
    }

    public /* synthetic */ StremioAddonBehaviorHints(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : bool, (i10 & 2) != 0 ? null : bool2, (i10 & 4) != 0 ? null : bool3, (i10 & 8) != 0 ? null : bool4);
    }
}
