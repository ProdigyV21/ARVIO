package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "", LinkHeader.Parameters.Title, "", "sourceRef", "serverName", "collectionName", "collectionType", "serverKind", "Lcom/arflix/tv/data/repository/HomeServerKind;", "connectionId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerKind;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getSourceRef", "getServerName", "getCollectionName", "getCollectionType", "getServerKind", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "getConnectionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerCatalogCandidate {
    public static final int $stable = 0;
    private final String collectionName;
    private final String collectionType;
    private final String connectionId;
    private final HomeServerKind serverKind;
    private final String serverName;
    private final String sourceRef;
    private final String title;

    public HomeServerCatalogCandidate(String str, String str2, String str3, String str4, String str5, HomeServerKind homeServerKind, String str6) {
        this.title = str;
        this.sourceRef = str2;
        this.serverName = str3;
        this.collectionName = str4;
        this.collectionType = str5;
        this.serverKind = homeServerKind;
        this.connectionId = str6;
    }

    public static /* synthetic */ HomeServerCatalogCandidate copy$default(HomeServerCatalogCandidate homeServerCatalogCandidate, String str, String str2, String str3, String str4, String str5, HomeServerKind homeServerKind, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = homeServerCatalogCandidate.title;
        }
        if ((i10 & 2) != 0) {
            str2 = homeServerCatalogCandidate.sourceRef;
        }
        if ((i10 & 4) != 0) {
            str3 = homeServerCatalogCandidate.serverName;
        }
        if ((i10 & 8) != 0) {
            str4 = homeServerCatalogCandidate.collectionName;
        }
        if ((i10 & 16) != 0) {
            str5 = homeServerCatalogCandidate.collectionType;
        }
        if ((i10 & 32) != 0) {
            homeServerKind = homeServerCatalogCandidate.serverKind;
        }
        if ((i10 & 64) != 0) {
            str6 = homeServerCatalogCandidate.connectionId;
        }
        HomeServerKind homeServerKind2 = homeServerKind;
        String str7 = str6;
        String str8 = str5;
        String str9 = str3;
        return homeServerCatalogCandidate.copy(str, str2, str9, str4, str8, homeServerKind2, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSourceRef() {
        return this.sourceRef;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getServerName() {
        return this.serverName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCollectionName() {
        return this.collectionName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCollectionType() {
        return this.collectionType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getConnectionId() {
        return this.connectionId;
    }

    public final HomeServerCatalogCandidate copy(String title, String sourceRef, String serverName, String collectionName, String collectionType, HomeServerKind serverKind, String connectionId) {
        return new HomeServerCatalogCandidate(title, sourceRef, serverName, collectionName, collectionType, serverKind, connectionId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerCatalogCandidate)) {
            return false;
        }
        HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) other;
        return kotlin.jvm.internal.p.a(this.title, homeServerCatalogCandidate.title) && kotlin.jvm.internal.p.a(this.sourceRef, homeServerCatalogCandidate.sourceRef) && kotlin.jvm.internal.p.a(this.serverName, homeServerCatalogCandidate.serverName) && kotlin.jvm.internal.p.a(this.collectionName, homeServerCatalogCandidate.collectionName) && kotlin.jvm.internal.p.a(this.collectionType, homeServerCatalogCandidate.collectionType) && this.serverKind == homeServerCatalogCandidate.serverKind && kotlin.jvm.internal.p.a(this.connectionId, homeServerCatalogCandidate.connectionId);
    }

    public final String getCollectionName() {
        return this.collectionName;
    }

    public final String getCollectionType() {
        return this.collectionType;
    }

    public final String getConnectionId() {
        return this.connectionId;
    }

    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final String getSourceRef() {
        return this.sourceRef;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.connectionId.hashCode() + ((this.serverKind.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.title.hashCode() * 31, 31, this.sourceRef), 31, this.serverName), 31, this.collectionName), 31, this.collectionType)) * 31);
    }

    public String toString() {
        String str = this.title;
        String str2 = this.sourceRef;
        String str3 = this.serverName;
        String str4 = this.collectionName;
        String str5 = this.collectionType;
        HomeServerKind homeServerKind = this.serverKind;
        String str6 = this.connectionId;
        StringBuilder sbR = a2.r("HomeServerCatalogCandidate(title=", str, ", sourceRef=", str2, ", serverName=");
        y.a.i(sbR, str3, ", collectionName=", str4, ", collectionType=");
        sbR.append(str5);
        sbR.append(", serverKind=");
        sbR.append(homeServerKind);
        sbR.append(", connectionId=");
        return a0.c.p(sbR, str6, ")");
    }

    public /* synthetic */ HomeServerCatalogCandidate(String str, String str2, String str3, String str4, String str5, HomeServerKind homeServerKind, String str6, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, str4, str5, (i10 & 32) != 0 ? HomeServerKind.UNKNOWN : homeServerKind, (i10 & 64) != 0 ? "" : str6);
    }
}
