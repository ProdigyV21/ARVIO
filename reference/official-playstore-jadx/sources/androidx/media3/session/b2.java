package androidx.media3.session;

import android.content.Intent;
import android.os.Bundle;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b2 {
    public static com.google.common.util.concurrent.d1 a(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((MediaItem) it.next()).localConfiguration == null) {
                return xc.d.r0(new UnsupportedOperationException());
            }
        }
        return xc.d.s0(list);
    }

    public static MediaSession.ConnectionResult b(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo) {
        return new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession).build();
    }

    public static com.google.common.util.concurrent.d1 c(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        return xc.d.s0(new SessionResult(-6));
    }

    public static com.google.common.util.concurrent.d1 d(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, MediaSession.ProgressReporter progressReporter) {
        return callback.onCustomCommand(mediaSession, controllerInfo, sessionCommand, bundle);
    }

    public static boolean f(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, Intent intent) {
        return false;
    }

    public static com.google.common.util.concurrent.d1 g(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo) {
        return xc.d.r0(new UnsupportedOperationException());
    }

    public static com.google.common.util.concurrent.d1 h(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, boolean z) {
        return callback.onPlaybackResumption(mediaSession, controllerInfo);
    }

    public static int i(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, int i10) {
        return 0;
    }

    public static com.google.common.util.concurrent.d1 l(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, List list, final int i10, final long j10) {
        return Util.transformFutureAsync(callback.onAddMediaItems(mediaSession, controllerInfo, list), new com.google.common.util.concurrent.w() { // from class: androidx.media3.session.a2
            @Override // com.google.common.util.concurrent.w
            public final com.google.common.util.concurrent.d1 apply(Object obj) {
                return xc.d.s0(new MediaSession.MediaItemsWithStartPosition((List) obj, i10, j10));
            }
        });
    }

    public static com.google.common.util.concurrent.d1 m(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, Rating rating) {
        return xc.d.s0(new SessionResult(-6));
    }

    public static com.google.common.util.concurrent.d1 n(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, String str, Rating rating) {
        return xc.d.s0(new SessionResult(-6));
    }

    public static void e(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo) {
    }

    public static void k(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo) {
    }

    public static void j(MediaSession.Callback callback, MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
    }
}
