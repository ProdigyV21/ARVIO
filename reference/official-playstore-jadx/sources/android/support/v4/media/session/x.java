package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static Bundle a(PlaybackState playbackState) {
        return playbackState.getExtras();
    }

    public static void b(PlaybackState.Builder builder, Bundle bundle) {
        builder.setExtras(bundle);
    }
}
