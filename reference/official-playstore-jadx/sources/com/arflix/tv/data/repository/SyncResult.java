package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/SyncResult;", "", "<init>", "()V", "Success", "Error", "Lcom/arflix/tv/data/repository/SyncResult$Error;", "Lcom/arflix/tv/data/repository/SyncResult$Success;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SyncResult {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/repository/SyncResult$Error;", "Lcom/arflix/tv/data/repository/SyncResult;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends SyncResult {
        public static final int $stable = 0;
        private final String message;

        public Error(String str) {
            super(null);
            this.message = str;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = error.message;
            }
            return error.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Error copy(String message) {
            return new Error(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && kotlin.jvm.internal.p.a(this.message, ((Error) other).message);
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return a0.c.l("Error(message=", this.message, ")");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/repository/SyncResult$Success;", "Lcom/arflix/tv/data/repository/SyncResult;", "moviesSynced", "", "episodesSynced", "<init>", "(II)V", "getMoviesSynced", "()I", "getEpisodesSynced", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Success extends SyncResult {
        public static final int $stable = 0;
        private final int episodesSynced;
        private final int moviesSynced;

        public Success(int i10, int i11) {
            super(null);
            this.moviesSynced = i10;
            this.episodesSynced = i11;
        }

        public static /* synthetic */ Success copy$default(Success success, int i10, int i11, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                i10 = success.moviesSynced;
            }
            if ((i12 & 2) != 0) {
                i11 = success.episodesSynced;
            }
            return success.copy(i10, i11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMoviesSynced() {
            return this.moviesSynced;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getEpisodesSynced() {
            return this.episodesSynced;
        }

        public final Success copy(int moviesSynced, int episodesSynced) {
            return new Success(moviesSynced, episodesSynced);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Success)) {
                return false;
            }
            Success success = (Success) other;
            return this.moviesSynced == success.moviesSynced && this.episodesSynced == success.episodesSynced;
        }

        public final int getEpisodesSynced() {
            return this.episodesSynced;
        }

        public final int getMoviesSynced() {
            return this.moviesSynced;
        }

        public int hashCode() {
            return (this.moviesSynced * 31) + this.episodesSynced;
        }

        public String toString() {
            return androidx.compose.foundation.c.s("Success(moviesSynced=", this.moviesSynced, ", episodesSynced=", this.episodesSynced, ")");
        }
    }

    public /* synthetic */ SyncResult(kotlin.jvm.internal.h hVar) {
        this();
    }

    private SyncResult() {
    }
}
