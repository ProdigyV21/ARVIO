package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import cb.l1;
import cb.r1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\u008b\b\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$getAddonsFromProfileFresh$AddonsProjection", "", "addons", "", "email", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAddons", "()Ljava/lang/String;", "getEmail", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/AuthRepository$getAddonsFromProfileFresh$AddonsProjection;", "equals", "", "other", "hashCode", "", "toString", "$serializer", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
public final /* data */ class AuthRepository$getAddonsFromProfileFresh$AddonsProjection {
    static final Companion Companion = new Companion(null);
    private final String addons;
    private final String email;

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\u0000\u0005\b\u008a\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$getAddonsFromProfileFresh$AddonsProjection.Companion", "", "<init>", "()V", "Lya/h;", "com/arflix/tv/data/repository/AuthRepository$getAddonsFromProfileFresh$AddonsProjection", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<AuthRepository$getAddonsFromProfileFresh$AddonsProjection> serializer() {
            return AuthRepository$getAddonsFromProfileFresh$AddonsProjection$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AuthRepository$getAddonsFromProfileFresh$AddonsProjection(int i10, String str, String str2, l1 l1Var) {
        if ((i10 & 1) == 0) {
            this.addons = null;
        } else {
            this.addons = str;
        }
        if ((i10 & 2) == 0) {
            this.email = null;
        } else {
            this.email = str2;
        }
    }

    public static /* synthetic */ AuthRepository$getAddonsFromProfileFresh$AddonsProjection copy$default(AuthRepository$getAddonsFromProfileFresh$AddonsProjection authRepository$getAddonsFromProfileFresh$AddonsProjection, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = authRepository$getAddonsFromProfileFresh$AddonsProjection.addons;
        }
        if ((i10 & 2) != 0) {
            str2 = authRepository$getAddonsFromProfileFresh$AddonsProjection.email;
        }
        return authRepository$getAddonsFromProfileFresh$AddonsProjection.copy(str, str2);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(AuthRepository$getAddonsFromProfileFresh$AddonsProjection authRepository$getAddonsFromProfileFresh$AddonsProjection, bb.c cVar, ab.g gVar) {
        if (cVar.C() || authRepository$getAddonsFromProfileFresh$AddonsProjection.addons != null) {
            cVar.m(gVar, 0, r1.f7553a, authRepository$getAddonsFromProfileFresh$AddonsProjection.addons);
        }
        if (!cVar.C() && authRepository$getAddonsFromProfileFresh$AddonsProjection.email == null) {
            return;
        }
        cVar.m(gVar, 1, r1.f7553a, authRepository$getAddonsFromProfileFresh$AddonsProjection.email);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAddons() {
        return this.addons;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final AuthRepository$getAddonsFromProfileFresh$AddonsProjection copy(String addons, String email) {
        return new AuthRepository$getAddonsFromProfileFresh$AddonsProjection(addons, email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthRepository$getAddonsFromProfileFresh$AddonsProjection)) {
            return false;
        }
        AuthRepository$getAddonsFromProfileFresh$AddonsProjection authRepository$getAddonsFromProfileFresh$AddonsProjection = (AuthRepository$getAddonsFromProfileFresh$AddonsProjection) other;
        return kotlin.jvm.internal.p.a(this.addons, authRepository$getAddonsFromProfileFresh$AddonsProjection.addons) && kotlin.jvm.internal.p.a(this.email, authRepository$getAddonsFromProfileFresh$AddonsProjection.email);
    }

    public final String getAddons() {
        return this.addons;
    }

    public final String getEmail() {
        return this.email;
    }

    public int hashCode() {
        String str = this.addons;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.email;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return a2.m("AddonsProjection(addons=", this.addons, ", email=", this.email, ")");
    }

    public AuthRepository$getAddonsFromProfileFresh$AddonsProjection(String str, String str2) {
        this.addons = str;
        this.email = str2;
    }

    public /* synthetic */ AuthRepository$getAddonsFromProfileFresh$AddonsProjection(String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2);
    }
}
