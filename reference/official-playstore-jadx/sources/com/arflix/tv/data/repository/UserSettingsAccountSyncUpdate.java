package com.arflix.tv.data.repository;

import cb.b1;
import cb.l1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t\b\u0083\b\u0018\u0000 '2\u00020\u0001:\u0002('B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\fJ'\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J$\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0017J\u0010\u0010\u001d\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b&\u0010\u0019¨\u0006)"}, d2 = {"Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;", "", "", "user_id", "Ldb/b0;", "settings", "<init>", "(Ljava/lang/String;Ldb/b0;)V", "", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ldb/b0;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Ldb/b0;", "copy", "(Ljava/lang/String;Ldb/b0;)Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUser_id", "Ldb/b0;", "getSettings", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
final /* data */ class UserSettingsAccountSyncUpdate {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final db.b0 settings;
    private final String user_id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/UserSettingsAccountSyncUpdate;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<UserSettingsAccountSyncUpdate> serializer() {
            return UserSettingsAccountSyncUpdate$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ UserSettingsAccountSyncUpdate(int i10, String str, db.b0 b0Var, l1 l1Var) {
        if (3 != (i10 & 3)) {
            b1.h(i10, 3, UserSettingsAccountSyncUpdate$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.user_id = str;
        this.settings = b0Var;
    }

    public static /* synthetic */ UserSettingsAccountSyncUpdate copy$default(UserSettingsAccountSyncUpdate userSettingsAccountSyncUpdate, String str, db.b0 b0Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = userSettingsAccountSyncUpdate.user_id;
        }
        if ((i10 & 2) != 0) {
            b0Var = userSettingsAccountSyncUpdate.settings;
        }
        return userSettingsAccountSyncUpdate.copy(str, b0Var);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(UserSettingsAccountSyncUpdate self, bb.c output, ab.g serialDesc) {
        output.x(serialDesc, 0, self.user_id);
        output.j(serialDesc, 1, db.e0.f14890a, self.settings);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUser_id() {
        return this.user_id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final db.b0 getSettings() {
        return this.settings;
    }

    public final UserSettingsAccountSyncUpdate copy(String user_id, db.b0 settings) {
        return new UserSettingsAccountSyncUpdate(user_id, settings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserSettingsAccountSyncUpdate)) {
            return false;
        }
        UserSettingsAccountSyncUpdate userSettingsAccountSyncUpdate = (UserSettingsAccountSyncUpdate) other;
        return kotlin.jvm.internal.p.a(this.user_id, userSettingsAccountSyncUpdate.user_id) && kotlin.jvm.internal.p.a(this.settings, userSettingsAccountSyncUpdate.settings);
    }

    public final db.b0 getSettings() {
        return this.settings;
    }

    public final String getUser_id() {
        return this.user_id;
    }

    public int hashCode() {
        return this.settings.f14881i.hashCode() + (this.user_id.hashCode() * 31);
    }

    public String toString() {
        return "UserSettingsAccountSyncUpdate(user_id=" + this.user_id + ", settings=" + this.settings + ")";
    }

    public UserSettingsAccountSyncUpdate(String str, db.b0 b0Var) {
        this.user_id = str;
        this.settings = b0Var;
    }
}
