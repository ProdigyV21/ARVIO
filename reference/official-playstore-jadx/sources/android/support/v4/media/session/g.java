package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import androidx.media3.session.legacy.MediaControllerCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class g implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaController f877a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f878b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f879c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f880d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MediaSessionCompat$Token f881e;

    public g(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.f881e = mediaSessionCompat$Token;
        MediaController mediaController = new MediaController(context, (MediaSession.Token) mediaSessionCompat$Token.f850l);
        this.f877a = mediaController;
        if (mediaSessionCompat$Token.a() == null) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver = new MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(null);
            mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver.f845i = new WeakReference(this);
            mediaController.sendCommand(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER, null, mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver);
        }
    }

    public final void a() {
        if (this.f881e.a() == null) {
            return;
        }
        ArrayList arrayList = this.f879c;
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            arrayList.clear();
        } else {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            this.f880d.put(null, new f());
            throw null;
        }
    }
}
