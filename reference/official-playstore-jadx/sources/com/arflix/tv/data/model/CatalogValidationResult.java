package com.arflix.tv.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/model/CatalogValidationResult;", "", "isValid", "", "normalizedUrl", "", "sourceType", "Lcom/arflix/tv/data/model/CatalogSourceType;", "error", "<init>", "(ZLjava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;Ljava/lang/String;)V", "()Z", "getNormalizedUrl", "()Ljava/lang/String;", "getSourceType", "()Lcom/arflix/tv/data/model/CatalogSourceType;", "getError", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CatalogValidationResult {
    public static final int $stable = 0;
    private final String error;
    private final boolean isValid;
    private final String normalizedUrl;
    private final CatalogSourceType sourceType;

    public CatalogValidationResult(boolean z, String str, CatalogSourceType catalogSourceType, String str2) {
        this.isValid = z;
        this.normalizedUrl = str;
        this.sourceType = catalogSourceType;
        this.error = str2;
    }

    public static /* synthetic */ CatalogValidationResult copy$default(CatalogValidationResult catalogValidationResult, boolean z, String str, CatalogSourceType catalogSourceType, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = catalogValidationResult.isValid;
        }
        if ((i10 & 2) != 0) {
            str = catalogValidationResult.normalizedUrl;
        }
        if ((i10 & 4) != 0) {
            catalogSourceType = catalogValidationResult.sourceType;
        }
        if ((i10 & 8) != 0) {
            str2 = catalogValidationResult.error;
        }
        return catalogValidationResult.copy(z, str, catalogSourceType, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsValid() {
        return this.isValid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNormalizedUrl() {
        return this.normalizedUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CatalogSourceType getSourceType() {
        return this.sourceType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final CatalogValidationResult copy(boolean isValid, String normalizedUrl, CatalogSourceType sourceType, String error) {
        return new CatalogValidationResult(isValid, normalizedUrl, sourceType, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogValidationResult)) {
            return false;
        }
        CatalogValidationResult catalogValidationResult = (CatalogValidationResult) other;
        return this.isValid == catalogValidationResult.isValid && p.a(this.normalizedUrl, catalogValidationResult.normalizedUrl) && this.sourceType == catalogValidationResult.sourceType && p.a(this.error, catalogValidationResult.error);
    }

    public final String getError() {
        return this.error;
    }

    public final String getNormalizedUrl() {
        return this.normalizedUrl;
    }

    public final CatalogSourceType getSourceType() {
        return this.sourceType;
    }

    public int hashCode() {
        int i10 = (this.isValid ? 1231 : 1237) * 31;
        String str = this.normalizedUrl;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        CatalogSourceType catalogSourceType = this.sourceType;
        int iHashCode2 = (iHashCode + (catalogSourceType == null ? 0 : catalogSourceType.hashCode())) * 31;
        String str2 = this.error;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isValid() {
        return this.isValid;
    }

    public String toString() {
        return "CatalogValidationResult(isValid=" + this.isValid + ", normalizedUrl=" + this.normalizedUrl + ", sourceType=" + this.sourceType + ", error=" + this.error + ")";
    }

    public /* synthetic */ CatalogValidationResult(boolean z, String str, CatalogSourceType catalogSourceType, String str2, int i10, h hVar) {
        this(z, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : catalogSourceType, (i10 & 8) != 0 ? null : str2);
    }
}
