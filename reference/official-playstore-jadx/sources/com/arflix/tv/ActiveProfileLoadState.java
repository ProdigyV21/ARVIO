package com.arflix.tv;

import com.arflix.tv.data.model.Profile;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/ActiveProfileLoadState;", "", "Loading", "Loaded", "Lcom/arflix/tv/ActiveProfileLoadState$Loaded;", "Lcom/arflix/tv/ActiveProfileLoadState$Loading;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
interface ActiveProfileLoadState {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/ActiveProfileLoadState$Loaded;", "Lcom/arflix/tv/ActiveProfileLoadState;", "profile", "Lcom/arflix/tv/data/model/Profile;", "<init>", "(Lcom/arflix/tv/data/model/Profile;)V", "getProfile", "()Lcom/arflix/tv/data/model/Profile;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Loaded implements ActiveProfileLoadState {
        public static final int $stable = 0;
        private final Profile profile;

        public Loaded(Profile profile) {
            this.profile = profile;
        }

        public static /* synthetic */ Loaded copy$default(Loaded loaded, Profile profile, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                profile = loaded.profile;
            }
            return loaded.copy(profile);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Profile getProfile() {
            return this.profile;
        }

        public final Loaded copy(Profile profile) {
            return new Loaded(profile);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Loaded) && p.a(this.profile, ((Loaded) other).profile);
        }

        public final Profile getProfile() {
            return this.profile;
        }

        public int hashCode() {
            Profile profile = this.profile;
            if (profile == null) {
                return 0;
            }
            return profile.hashCode();
        }

        public String toString() {
            return "Loaded(profile=" + this.profile + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ActiveProfileLoadState$Loading;", "Lcom/arflix/tv/ActiveProfileLoadState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Loading implements ActiveProfileLoadState {
        public static final int $stable = 0;
        public static final Loading INSTANCE = new Loading();

        private Loading() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Loading);
        }

        public int hashCode() {
            return 177492743;
        }

        public String toString() {
            return "Loading";
        }
    }
}
