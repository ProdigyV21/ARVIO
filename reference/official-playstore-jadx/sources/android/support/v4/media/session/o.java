package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.Handler;
import android.os.RemoteCallbackList;
import android.support.v4.media.MediaMetadataCompat;

/* JADX INFO: loaded from: classes.dex */
public class o implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSession f886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f887b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MediaSessionCompat$Token f888c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f889d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RemoteCallbackList f890e = new RemoteCallbackList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PlaybackStateCompat f891f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MediaMetadataCompat f892g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public l f893h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public androidx.media.d f894i;

    public o(Context context) {
        MediaSession mediaSessionB = b(context);
        this.f886a = mediaSessionB;
        n nVar = new n(this);
        this.f887b = nVar;
        this.f888c = new MediaSessionCompat$Token(mediaSessionB.getSessionToken(), nVar);
        mediaSessionB.setFlags(3);
    }

    @Override // android.support.v4.media.session.m
    public void a(androidx.media.d dVar) {
        synchronized (this.f889d) {
            this.f894i = dVar;
        }
    }

    public MediaSession b(Context context) {
        return new MediaSession(context, "CastMediaSession");
    }

    public final void c(l lVar, Handler handler) {
        synchronized (this.f889d) {
            try {
                this.f893h = lVar;
                this.f886a.setCallback(lVar == null ? null : lVar.mCallbackFwk, handler);
                if (lVar != null) {
                    lVar.setSessionImpl(this, handler);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.support.v4.media.session.m
    public final l getCallback() {
        l lVar;
        synchronized (this.f889d) {
            lVar = this.f893h;
        }
        return lVar;
    }

    @Override // android.support.v4.media.session.m
    public androidx.media.d getCurrentControllerInfo() {
        androidx.media.d dVar;
        synchronized (this.f889d) {
            dVar = this.f894i;
        }
        return dVar;
    }

    @Override // android.support.v4.media.session.m
    public final PlaybackStateCompat getPlaybackState() {
        return this.f891f;
    }
}
