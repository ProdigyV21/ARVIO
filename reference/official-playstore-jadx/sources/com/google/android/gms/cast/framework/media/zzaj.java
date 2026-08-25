package com.google.android.gms.cast.framework.media;

import androidx.compose.foundation.c;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.t;
import j$.util.Objects;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzaj extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(RemoteMediaClient remoteMediaClient, int i10, int i11, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = i10;
        this.zzb = i11;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzd = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        MediaQueueItem queueItem;
        t.e("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzd;
        MediaQueue mediaQueue = remoteMediaClient.getMediaQueue();
        int i10 = this.zza;
        int iIndexOfItemWithId = mediaQueue.indexOfItemWithId(i10);
        int itemId = 0;
        if (iIndexOfItemWithId == -1) {
            MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
            t.i(mediaStatus);
            int i11 = 0;
            while (true) {
                if (i11 >= mediaStatus.getQueueItemCount()) {
                    iIndexOfItemWithId = -1;
                    break;
                }
                MediaQueueItem queueItem2 = mediaStatus.getQueueItem(i11);
                t.i(queueItem2);
                if (queueItem2.getItemId() == i10) {
                    iIndexOfItemWithId = i11;
                    break;
                }
                i11++;
            }
        }
        int i12 = this.zzb;
        if (i12 < 0) {
            Locale locale = Locale.ROOT;
            setResult(new zzbb(this, new Status(2001, c.o(i12, "Invalid request: Invalid newIndex ", "."), null, null)));
            return;
        }
        if (iIndexOfItemWithId == i12) {
            setResult(new zzbb(this, new Status(0, null, null, null)));
            return;
        }
        if (i12 > iIndexOfItemWithId) {
            i12++;
        }
        t.e("Must be called from the main thread.");
        int iItemIdAtIndex = remoteMediaClient.getMediaQueue().itemIdAtIndex(i12);
        if (iItemIdAtIndex != 0) {
            itemId = iItemIdAtIndex;
        } else {
            MediaStatus mediaStatus2 = remoteMediaClient.getMediaStatus();
            if (mediaStatus2 != null && (queueItem = mediaStatus2.getQueueItem(i12)) != null) {
                itemId = queueItem.getItemId();
            }
        }
        remoteMediaClient.zzt().zzG(zzb(), new int[]{i10}, itemId, this.zzc);
    }
}
