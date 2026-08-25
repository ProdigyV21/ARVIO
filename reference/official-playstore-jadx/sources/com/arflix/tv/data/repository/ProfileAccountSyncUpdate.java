package com.arflix.tv.data.repository;

import cb.b1;
import cb.l1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083\b\u0018\u0000 !2\u00020\u0001:\u0002\"!B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0004\u0010\nJ'\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0015J\u0010\u0010\u0019\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u0015¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileAccountSyncUpdate;", "", "", "addons", "<init>", "(Ljava/lang/String;)V", "", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/ProfileAccountSyncUpdate;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/ProfileAccountSyncUpdate;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getAddons", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
final /* data */ class ProfileAccountSyncUpdate {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String addons;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileAccountSyncUpdate$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/ProfileAccountSyncUpdate;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<ProfileAccountSyncUpdate> serializer() {
            return ProfileAccountSyncUpdate$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ ProfileAccountSyncUpdate(int i10, String str, l1 l1Var) {
        if (1 == (i10 & 1)) {
            this.addons = str;
        } else {
            b1.h(i10, 1, ProfileAccountSyncUpdate$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ ProfileAccountSyncUpdate copy$default(ProfileAccountSyncUpdate profileAccountSyncUpdate, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = profileAccountSyncUpdate.addons;
        }
        return profileAccountSyncUpdate.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    public final ProfileAccountSyncUpdate copy(String addons) {
        return new ProfileAccountSyncUpdate(addons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ProfileAccountSyncUpdate) && kotlin.jvm.internal.p.a(this.addons, ((ProfileAccountSyncUpdate) other).addons);
    }

    public final String getAddons() {
        return this.addons;
    }

    public int hashCode() {
        return this.addons.hashCode();
    }

    public String toString() {
        return a0.c.l("ProfileAccountSyncUpdate(addons=", this.addons, ")");
    }

    public ProfileAccountSyncUpdate(String str) {
        this.addons = str;
    }
}
