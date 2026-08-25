package com.arflix.tv.cast;

import android.content.Context;
import com.arflix.tv.MainActivity;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/cast/CastOptionsProvider;", "Lcom/google/android/gms/cast/framework/OptionsProvider;", "<init>", "()V", "getCastOptions", "Lcom/google/android/gms/cast/framework/CastOptions;", "context", "Landroid/content/Context;", "getAdditionalSessionProviders", "", "Lcom/google/android/gms/cast/framework/SessionProvider;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CastOptionsProvider implements OptionsProvider {
    public static final int $stable = 0;

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public List<SessionProvider> getAdditionalSessionProviders(Context context) {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public CastOptions getCastOptions(Context context) {
        return new CastOptions.Builder().setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID).setCastMediaOptions(new CastMediaOptions.Builder().setNotificationOptions(new NotificationOptions.Builder().setTargetActivityClassName(MainActivity.class.getName()).setActions(t7.a.E(MediaIntentReceiver.ACTION_REWIND, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_FORWARD, MediaIntentReceiver.ACTION_STOP_CASTING), new int[]{1, 3}).setSkipStepMs(10000L).build()).build()).build();
    }
}
