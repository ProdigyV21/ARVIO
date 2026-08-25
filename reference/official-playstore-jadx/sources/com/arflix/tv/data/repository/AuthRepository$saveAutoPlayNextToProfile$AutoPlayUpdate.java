package com.arflix.tv.data.repository;

import cb.b1;
import cb.l1;
import com.arflix.tv.util.Constants;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\u008b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate", "", Constants.PREF_AUTO_PLAY_NEXT, "", "<init>", "(Z)V", "getAuto_play_next", "()Z", "component1", "copy", "(Z)Lcom/arflix/tv/data/repository/AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate;", "equals", "other", "hashCode", "", "toString", "", "$serializer", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
public final /* data */ class AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate {
    static final Companion Companion = new Companion(null);
    private final boolean auto_play_next;

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\u0000\u0005\b\u008a\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"com/arflix/tv/data/repository/AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate.Companion", "", "<init>", "()V", "Lya/h;", "com/arflix/tv/data/repository/AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate> serializer() {
            return AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate(int i10, boolean z, l1 l1Var) {
        if (1 == (i10 & 1)) {
            this.auto_play_next = z;
        } else {
            b1.h(i10, 1, AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate copy$default(AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate authRepository$saveAutoPlayNextToProfile$AutoPlayUpdate, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = authRepository$saveAutoPlayNextToProfile$AutoPlayUpdate.auto_play_next;
        }
        return authRepository$saveAutoPlayNextToProfile$AutoPlayUpdate.copy(z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAuto_play_next() {
        return this.auto_play_next;
    }

    public final AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate copy(boolean auto_play_next) {
        return new AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate(auto_play_next);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate) && this.auto_play_next == ((AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate) other).auto_play_next;
    }

    public final boolean getAuto_play_next() {
        return this.auto_play_next;
    }

    public int hashCode() {
        return this.auto_play_next ? 1231 : 1237;
    }

    public String toString() {
        return "AutoPlayUpdate(auto_play_next=" + this.auto_play_next + ")";
    }

    public AuthRepository$saveAutoPlayNextToProfile$AutoPlayUpdate(boolean z) {
        this.auto_play_next = z;
    }
}
