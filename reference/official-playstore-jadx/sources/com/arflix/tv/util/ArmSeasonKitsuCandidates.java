package com.arflix.tv.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/util/ArmSeasonKitsuCandidates;", "", "kitsuIds", "", "", "explicitTmdbSeason", "", "<init>", "(Ljava/util/List;Z)V", "getKitsuIds", "()Ljava/util/List;", "getExplicitTmdbSeason", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArmSeasonKitsuCandidates {
    public static final int $stable = 0;
    private final boolean explicitTmdbSeason;
    private final List<Integer> kitsuIds;

    public ArmSeasonKitsuCandidates(List<Integer> list, boolean z) {
        this.kitsuIds = list;
        this.explicitTmdbSeason = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArmSeasonKitsuCandidates copy$default(ArmSeasonKitsuCandidates armSeasonKitsuCandidates, List list, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = armSeasonKitsuCandidates.kitsuIds;
        }
        if ((i10 & 2) != 0) {
            z = armSeasonKitsuCandidates.explicitTmdbSeason;
        }
        return armSeasonKitsuCandidates.copy(list, z);
    }

    public final List<Integer> component1() {
        return this.kitsuIds;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getExplicitTmdbSeason() {
        return this.explicitTmdbSeason;
    }

    public final ArmSeasonKitsuCandidates copy(List<Integer> kitsuIds, boolean explicitTmdbSeason) {
        return new ArmSeasonKitsuCandidates(kitsuIds, explicitTmdbSeason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArmSeasonKitsuCandidates)) {
            return false;
        }
        ArmSeasonKitsuCandidates armSeasonKitsuCandidates = (ArmSeasonKitsuCandidates) other;
        return p.a(this.kitsuIds, armSeasonKitsuCandidates.kitsuIds) && this.explicitTmdbSeason == armSeasonKitsuCandidates.explicitTmdbSeason;
    }

    public final boolean getExplicitTmdbSeason() {
        return this.explicitTmdbSeason;
    }

    public final List<Integer> getKitsuIds() {
        return this.kitsuIds;
    }

    public int hashCode() {
        return (this.kitsuIds.hashCode() * 31) + (this.explicitTmdbSeason ? 1231 : 1237);
    }

    public String toString() {
        return "ArmSeasonKitsuCandidates(kitsuIds=" + this.kitsuIds + ", explicitTmdbSeason=" + this.explicitTmdbSeason + ")";
    }
}
