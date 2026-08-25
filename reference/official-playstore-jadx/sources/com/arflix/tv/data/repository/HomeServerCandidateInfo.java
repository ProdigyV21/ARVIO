package com.arflix.tv.data.repository;

import io.ktor.http.LinkHeader;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J:\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;", "", LinkHeader.Parameters.Title, "", "productionYear", "", "providerIds", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)V", "getTitle", "()Ljava/lang/String;", "getProductionYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProviderIds", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerCandidateInfo {
    public static final int $stable = 0;
    private final Integer productionYear;
    private final Map<String, String> providerIds;
    private final String title;

    public HomeServerCandidateInfo(String str, Integer num, Map<String, String> map) {
        this.title = str;
        this.productionYear = num;
        this.providerIds = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeServerCandidateInfo copy$default(HomeServerCandidateInfo homeServerCandidateInfo, String str, Integer num, Map map, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = homeServerCandidateInfo.title;
        }
        if ((i10 & 2) != 0) {
            num = homeServerCandidateInfo.productionYear;
        }
        if ((i10 & 4) != 0) {
            map = homeServerCandidateInfo.providerIds;
        }
        return homeServerCandidateInfo.copy(str, num, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getProductionYear() {
        return this.productionYear;
    }

    public final Map<String, String> component3() {
        return this.providerIds;
    }

    public final HomeServerCandidateInfo copy(String title, Integer productionYear, Map<String, String> providerIds) {
        return new HomeServerCandidateInfo(title, productionYear, providerIds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerCandidateInfo)) {
            return false;
        }
        HomeServerCandidateInfo homeServerCandidateInfo = (HomeServerCandidateInfo) other;
        return kotlin.jvm.internal.p.a(this.title, homeServerCandidateInfo.title) && kotlin.jvm.internal.p.a(this.productionYear, homeServerCandidateInfo.productionYear) && kotlin.jvm.internal.p.a(this.providerIds, homeServerCandidateInfo.providerIds);
    }

    public final Integer getProductionYear() {
        return this.productionYear;
    }

    public final Map<String, String> getProviderIds() {
        return this.providerIds;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        Integer num = this.productionYear;
        return this.providerIds.hashCode() + ((iHashCode + (num == null ? 0 : num.hashCode())) * 31);
    }

    public String toString() {
        return "HomeServerCandidateInfo(title=" + this.title + ", productionYear=" + this.productionYear + ", providerIds=" + this.providerIds + ")";
    }
}
