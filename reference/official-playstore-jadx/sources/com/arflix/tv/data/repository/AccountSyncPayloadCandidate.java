package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/repository/AccountSyncPayloadCandidate;", "", "source", "", "payload", "updatedAtMillis", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getSource", "()Ljava/lang/String;", "getPayload", "getUpdatedAtMillis", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class AccountSyncPayloadCandidate {
    private final String payload;
    private final String source;
    private final long updatedAtMillis;

    public AccountSyncPayloadCandidate(String str, String str2, long j10) {
        this.source = str;
        this.payload = str2;
        this.updatedAtMillis = j10;
    }

    public static /* synthetic */ AccountSyncPayloadCandidate copy$default(AccountSyncPayloadCandidate accountSyncPayloadCandidate, String str, String str2, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = accountSyncPayloadCandidate.source;
        }
        if ((i10 & 2) != 0) {
            str2 = accountSyncPayloadCandidate.payload;
        }
        if ((i10 & 4) != 0) {
            j10 = accountSyncPayloadCandidate.updatedAtMillis;
        }
        return accountSyncPayloadCandidate.copy(str, str2, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    public final AccountSyncPayloadCandidate copy(String source, String payload, long updatedAtMillis) {
        return new AccountSyncPayloadCandidate(source, payload, updatedAtMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccountSyncPayloadCandidate)) {
            return false;
        }
        AccountSyncPayloadCandidate accountSyncPayloadCandidate = (AccountSyncPayloadCandidate) other;
        return kotlin.jvm.internal.p.a(this.source, accountSyncPayloadCandidate.source) && kotlin.jvm.internal.p.a(this.payload, accountSyncPayloadCandidate.payload) && this.updatedAtMillis == accountSyncPayloadCandidate.updatedAtMillis;
    }

    public final String getPayload() {
        return this.payload;
    }

    public final String getSource() {
        return this.source;
    }

    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.source.hashCode() * 31, 31, this.payload);
        long j10 = this.updatedAtMillis;
        return iC + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return a0.c.j(this.updatedAtMillis, ")", a2.r("AccountSyncPayloadCandidate(source=", this.source, ", payload=", this.payload, ", updatedAtMillis="));
    }
}
