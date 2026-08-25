package com.arflix.tv.data.model;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/model/AddonBehaviorHints;", "Ljava/io/Serializable;", "adult", "", "p2p", "configurable", "configurationRequired", "<init>", "(ZZZZ)V", "getAdult", "()Z", "getP2p", "getConfigurable", "getConfigurationRequired", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonBehaviorHints implements Serializable {
    public static final int $stable = 0;
    private final boolean adult;
    private final boolean configurable;
    private final boolean configurationRequired;
    private final boolean p2p;

    public AddonBehaviorHints() {
        this(false, false, false, false, 15, null);
    }

    public static /* synthetic */ AddonBehaviorHints copy$default(AddonBehaviorHints addonBehaviorHints, boolean z, boolean z5, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = addonBehaviorHints.adult;
        }
        if ((i10 & 2) != 0) {
            z5 = addonBehaviorHints.p2p;
        }
        if ((i10 & 4) != 0) {
            z10 = addonBehaviorHints.configurable;
        }
        if ((i10 & 8) != 0) {
            z11 = addonBehaviorHints.configurationRequired;
        }
        return addonBehaviorHints.copy(z, z5, z10, z11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAdult() {
        return this.adult;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getP2p() {
        return this.p2p;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getConfigurable() {
        return this.configurable;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getConfigurationRequired() {
        return this.configurationRequired;
    }

    public final AddonBehaviorHints copy(boolean adult, boolean p2p, boolean configurable, boolean configurationRequired) {
        return new AddonBehaviorHints(adult, p2p, configurable, configurationRequired);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonBehaviorHints)) {
            return false;
        }
        AddonBehaviorHints addonBehaviorHints = (AddonBehaviorHints) other;
        return this.adult == addonBehaviorHints.adult && this.p2p == addonBehaviorHints.p2p && this.configurable == addonBehaviorHints.configurable && this.configurationRequired == addonBehaviorHints.configurationRequired;
    }

    public final boolean getAdult() {
        return this.adult;
    }

    public final boolean getConfigurable() {
        return this.configurable;
    }

    public final boolean getConfigurationRequired() {
        return this.configurationRequired;
    }

    public final boolean getP2p() {
        return this.p2p;
    }

    public int hashCode() {
        return ((((((this.adult ? 1231 : 1237) * 31) + (this.p2p ? 1231 : 1237)) * 31) + (this.configurable ? 1231 : 1237)) * 31) + (this.configurationRequired ? 1231 : 1237);
    }

    public String toString() {
        return "AddonBehaviorHints(adult=" + this.adult + ", p2p=" + this.p2p + ", configurable=" + this.configurable + ", configurationRequired=" + this.configurationRequired + ")";
    }

    public AddonBehaviorHints(boolean z, boolean z5, boolean z10, boolean z11) {
        this.adult = z;
        this.p2p = z5;
        this.configurable = z10;
        this.configurationRequired = z11;
    }

    public /* synthetic */ AddonBehaviorHints(boolean z, boolean z5, boolean z10, boolean z11, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? false : z5, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? false : z11);
    }
}
