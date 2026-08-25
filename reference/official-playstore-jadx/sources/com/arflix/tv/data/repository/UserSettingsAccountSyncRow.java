package com.arflix.tv.data.repository;

import cb.l1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083\b\u0018\u0000 #2\u00020\u0001:\u0002$#B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0004\u0010\nJ'\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u0015¨\u0006%"}, d2 = {"Lcom/arflix/tv/data/repository/UserSettingsAccountSyncRow;", "", "Ldb/b0;", "settings", "<init>", "(Ldb/b0;)V", "", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILdb/b0;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/UserSettingsAccountSyncRow;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ldb/b0;", "copy", "(Ldb/b0;)Lcom/arflix/tv/data/repository/UserSettingsAccountSyncRow;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ldb/b0;", "getSettings", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
final /* data */ class UserSettingsAccountSyncRow {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final db.b0 settings;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/UserSettingsAccountSyncRow$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/UserSettingsAccountSyncRow;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<UserSettingsAccountSyncRow> serializer() {
            return UserSettingsAccountSyncRow$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UserSettingsAccountSyncRow() {
        this((db.b0) null, 1, (kotlin.jvm.internal.h) (0 == true ? 1 : 0));
    }

    public static /* synthetic */ UserSettingsAccountSyncRow copy$default(UserSettingsAccountSyncRow userSettingsAccountSyncRow, db.b0 b0Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            b0Var = userSettingsAccountSyncRow.settings;
        }
        return userSettingsAccountSyncRow.copy(b0Var);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(UserSettingsAccountSyncRow self, bb.c output, ab.g serialDesc) {
        if (!output.C() && self.settings == null) {
            return;
        }
        output.m(serialDesc, 0, db.e0.f14890a, self.settings);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final db.b0 getSettings() {
        return this.settings;
    }

    public final UserSettingsAccountSyncRow copy(db.b0 settings) {
        return new UserSettingsAccountSyncRow(settings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UserSettingsAccountSyncRow) && kotlin.jvm.internal.p.a(this.settings, ((UserSettingsAccountSyncRow) other).settings);
    }

    public final db.b0 getSettings() {
        return this.settings;
    }

    public int hashCode() {
        db.b0 b0Var = this.settings;
        if (b0Var == null) {
            return 0;
        }
        return b0Var.f14881i.hashCode();
    }

    public String toString() {
        return "UserSettingsAccountSyncRow(settings=" + this.settings + ")";
    }

    public /* synthetic */ UserSettingsAccountSyncRow(int i10, db.b0 b0Var, l1 l1Var) {
        if ((i10 & 1) == 0) {
            this.settings = null;
        } else {
            this.settings = b0Var;
        }
    }

    public UserSettingsAccountSyncRow(db.b0 b0Var) {
        this.settings = b0Var;
    }

    public /* synthetic */ UserSettingsAccountSyncRow(db.b0 b0Var, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : b0Var);
    }
}
