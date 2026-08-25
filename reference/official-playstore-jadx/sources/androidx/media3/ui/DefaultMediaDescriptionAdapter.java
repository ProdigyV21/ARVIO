package androidx.media3.ui;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import androidx.media3.common.Player;
import androidx.media3.ui.PlayerNotificationManager;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultMediaDescriptionAdapter implements PlayerNotificationManager.MediaDescriptionAdapter {
    private final PendingIntent pendingIntent;

    public DefaultMediaDescriptionAdapter(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
    public PendingIntent createCurrentContentIntent(Player player) {
        return this.pendingIntent;
    }

    @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
    public CharSequence getCurrentContentText(Player player) {
        if (!player.isCommandAvailable(18)) {
            return null;
        }
        CharSequence charSequence = player.getMediaMetadata().artist;
        return !TextUtils.isEmpty(charSequence) ? charSequence : player.getMediaMetadata().albumArtist;
    }

    @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
    public CharSequence getCurrentContentTitle(Player player) {
        if (!player.isCommandAvailable(18)) {
            return "";
        }
        CharSequence charSequence = player.getMediaMetadata().displayTitle;
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        CharSequence charSequence2 = player.getMediaMetadata().title;
        return charSequence2 != null ? charSequence2 : "";
    }

    @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
    public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback bitmapCallback) {
        byte[] bArr;
        if (player.isCommandAvailable(18) && (bArr = player.getMediaMetadata().artworkData) != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
    public final /* synthetic */ CharSequence getCurrentSubText(Player player) {
        return j.a(this, player);
    }
}
