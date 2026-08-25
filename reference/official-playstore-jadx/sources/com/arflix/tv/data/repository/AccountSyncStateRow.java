package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import cb.b1;
import cb.l1;
import cb.r1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t\b\u0083\b\u0018\u0000 '2\u00020\u0001:\u0002('B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\fJ'\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0017J2\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0017J\u0010\u0010\u001d\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0017R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010#\u001a\u0004\b%\u0010\u0017R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b&\u0010\u0017¨\u0006)"}, d2 = {"Lcom/arflix/tv/data/repository/AccountSyncStateRow;", "", "", "user_id", "payload", "updated_at", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/AccountSyncStateRow;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/AccountSyncStateRow;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUser_id", "getPayload", "getUpdated_at", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
final /* data */ class AccountSyncStateRow {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String payload;
    private final String updated_at;
    private final String user_id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/AccountSyncStateRow$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/AccountSyncStateRow;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<AccountSyncStateRow> serializer() {
            return AccountSyncStateRow$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AccountSyncStateRow(int i10, String str, String str2, String str3, l1 l1Var) {
        if (1 != (i10 & 1)) {
            b1.h(i10, 1, AccountSyncStateRow$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.user_id = str;
        if ((i10 & 2) == 0) {
            this.payload = null;
        } else {
            this.payload = str2;
        }
        if ((i10 & 4) == 0) {
            this.updated_at = null;
        } else {
            this.updated_at = str3;
        }
    }

    public static /* synthetic */ AccountSyncStateRow copy$default(AccountSyncStateRow accountSyncStateRow, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = accountSyncStateRow.user_id;
        }
        if ((i10 & 2) != 0) {
            str2 = accountSyncStateRow.payload;
        }
        if ((i10 & 4) != 0) {
            str3 = accountSyncStateRow.updated_at;
        }
        return accountSyncStateRow.copy(str, str2, str3);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(AccountSyncStateRow self, bb.c output, ab.g serialDesc) {
        output.x(serialDesc, 0, self.user_id);
        if (output.C() || self.payload != null) {
            output.m(serialDesc, 1, r1.f7553a, self.payload);
        }
        if (!output.C() && self.updated_at == null) {
            return;
        }
        output.m(serialDesc, 2, r1.f7553a, self.updated_at);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUser_id() {
        return this.user_id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUpdated_at() {
        return this.updated_at;
    }

    public final AccountSyncStateRow copy(String user_id, String payload, String updated_at) {
        return new AccountSyncStateRow(user_id, payload, updated_at);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccountSyncStateRow)) {
            return false;
        }
        AccountSyncStateRow accountSyncStateRow = (AccountSyncStateRow) other;
        return kotlin.jvm.internal.p.a(this.user_id, accountSyncStateRow.user_id) && kotlin.jvm.internal.p.a(this.payload, accountSyncStateRow.payload) && kotlin.jvm.internal.p.a(this.updated_at, accountSyncStateRow.updated_at);
    }

    public final String getPayload() {
        return this.payload;
    }

    public final String getUpdated_at() {
        return this.updated_at;
    }

    public final String getUser_id() {
        return this.user_id;
    }

    public int hashCode() {
        int iHashCode = this.user_id.hashCode() * 31;
        String str = this.payload;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.updated_at;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.user_id;
        String str2 = this.payload;
        return a0.c.p(a2.r("AccountSyncStateRow(user_id=", str, ", payload=", str2, ", updated_at="), this.updated_at, ")");
    }

    public AccountSyncStateRow(String str, String str2, String str3) {
        this.user_id = str;
        this.payload = str2;
        this.updated_at = str3;
    }

    public /* synthetic */ AccountSyncStateRow(String str, String str2, String str3, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3);
    }
}
