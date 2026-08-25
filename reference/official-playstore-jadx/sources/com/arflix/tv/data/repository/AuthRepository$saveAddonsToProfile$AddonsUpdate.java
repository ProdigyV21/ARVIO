package com.arflix.tv.data.repository;

import cb.b1;
import cb.l1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\u008b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate", "", "addons", "", "<init>", "(Ljava/lang/String;)V", "getAddons", "()Ljava/lang/String;", "component1", "copy", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate;", "equals", "", "other", "hashCode", "", "toString", "$serializer", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
public final /* data */ class AuthRepository$saveAddonsToProfile$AddonsUpdate {
    static final Companion Companion = new Companion(null);
    private final String addons;

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\u0000\u0005\b\u008a\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate.Companion", "", "<init>", "()V", "Lya/h;", "com/arflix/tv/data/repository/AuthRepository$saveAddonsToProfile$AddonsUpdate", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<AuthRepository$saveAddonsToProfile$AddonsUpdate> serializer() {
            return AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AuthRepository$saveAddonsToProfile$AddonsUpdate(int i10, String str, l1 l1Var) {
        if (1 == (i10 & 1)) {
            this.addons = str;
        } else {
            b1.h(i10, 1, AuthRepository$saveAddonsToProfile$AddonsUpdate$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ AuthRepository$saveAddonsToProfile$AddonsUpdate copy$default(AuthRepository$saveAddonsToProfile$AddonsUpdate authRepository$saveAddonsToProfile$AddonsUpdate, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = authRepository$saveAddonsToProfile$AddonsUpdate.addons;
        }
        return authRepository$saveAddonsToProfile$AddonsUpdate.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    public final AuthRepository$saveAddonsToProfile$AddonsUpdate copy(String addons) {
        return new AuthRepository$saveAddonsToProfile$AddonsUpdate(addons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AuthRepository$saveAddonsToProfile$AddonsUpdate) && kotlin.jvm.internal.p.a(this.addons, ((AuthRepository$saveAddonsToProfile$AddonsUpdate) other).addons);
    }

    public final String getAddons() {
        return this.addons;
    }

    public int hashCode() {
        return this.addons.hashCode();
    }

    public String toString() {
        return a0.c.l("AddonsUpdate(addons=", this.addons, ")");
    }

    public AuthRepository$saveAddonsToProfile$AddonsUpdate(String str) {
        this.addons = str;
    }
}
