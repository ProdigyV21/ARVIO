package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.model.MediaType;
import d7.d;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ Object a(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToWatchlist");
        }
        if ((i11 & 4) != 0) {
            z = false;
        }
        return remoteSyncProvider.addToWatchlist(mediaType, i10, z, dVar);
    }

    public static /* synthetic */ Object b(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, Integer num, Integer num2, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: dismissContinueWatching");
        }
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return remoteSyncProvider.dismissContinueWatching(mediaType, i10, num, num2, dVar);
    }

    public static /* synthetic */ Object c(RemoteSyncProvider remoteSyncProvider, boolean z, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContinueWatching");
        }
        if ((i10 & 1) != 0) {
            z = false;
        }
        return remoteSyncProvider.getContinueWatching(z, dVar);
    }

    public static /* synthetic */ Object d(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromWatchlist");
        }
        if ((i11 & 4) != 0) {
            z = false;
        }
        return remoteSyncProvider.removeFromWatchlist(mediaType, i10, z, dVar);
    }

    public static /* synthetic */ Object e(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobblePause");
        }
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncProvider.scrobblePause(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object f(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobbleProgress");
        }
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncProvider.scrobbleProgress(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object g(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobbleStart");
        }
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncProvider.scrobbleStart(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object h(RemoteSyncProvider remoteSyncProvider, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobbleStop");
        }
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncProvider.scrobbleStop(mediaType, i10, f10, num, num2, z, dVar);
    }
}
