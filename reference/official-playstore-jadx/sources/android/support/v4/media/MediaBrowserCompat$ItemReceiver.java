package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.v;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$ItemReceiver extends android.support.v4.os.f {
    @Override // android.support.v4.os.f
    public final void onReceiveResult(int i10, Bundle bundle) {
        if (bundle != null) {
            bundle = v.e(bundle);
        }
        if (i10 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
            throw null;
        }
        Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
        if (parcelable != null && !(parcelable instanceof MediaBrowserCompat$MediaItem)) {
            throw null;
        }
        throw null;
    }
}
