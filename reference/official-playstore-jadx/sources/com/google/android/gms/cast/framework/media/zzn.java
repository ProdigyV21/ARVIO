package com.google.android.gms.cast.framework.media;

import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzn extends RemoteMediaClient.Callback {
    final /* synthetic */ MediaQueue zza;

    public zzn(MediaQueue mediaQueue) {
        Objects.requireNonNull(mediaQueue);
        this.zza = mediaQueue;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        MediaQueue mediaQueue = this.zza;
        long jZze = mediaQueue.zze();
        if (jZze != mediaQueue.zza) {
            mediaQueue.zza = jZze;
            mediaQueue.zza();
            if (mediaQueue.zza != 0) {
                mediaQueue.zzb();
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzb(int[] iArr) {
        MediaQueue mediaQueue = this.zza;
        List listZzf = CastUtils.zzf(iArr);
        if (mediaQueue.zzb.equals(listZzf)) {
            return;
        }
        mediaQueue.zzh();
        mediaQueue.zzd.evictAll();
        mediaQueue.zze.clear();
        mediaQueue.zzb = listZzf;
        mediaQueue.zzg();
        mediaQueue.zzj();
        mediaQueue.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzc(int[] iArr, int i10) {
        int size;
        if (i10 == 0) {
            size = this.zza.zzb.size();
        } else {
            MediaQueue mediaQueue = this.zza;
            size = mediaQueue.zzc.get(i10, -1);
            if (size == -1) {
                mediaQueue.zzb();
                return;
            }
        }
        int length = iArr.length;
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzh();
        mediaQueue2.zzb.addAll(size, CastUtils.zzf(iArr));
        mediaQueue2.zzg();
        mediaQueue2.zzk(size, length);
        mediaQueue2.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzd(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i10 : iArr) {
            MediaQueue mediaQueue = this.zza;
            mediaQueue.zzd.remove(Integer.valueOf(i10));
            int i11 = mediaQueue.zzc.get(i10, -1);
            if (i11 == -1) {
                mediaQueue.zzb();
                return;
            }
            arrayList.add(Integer.valueOf(i11));
        }
        Collections.sort(arrayList);
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzh();
        mediaQueue2.zzl(CastUtils.zze(arrayList));
        mediaQueue2.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zze(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i10 : iArr) {
            MediaQueue mediaQueue = this.zza;
            mediaQueue.zzd.remove(Integer.valueOf(i10));
            SparseIntArray sparseIntArray = mediaQueue.zzc;
            int i11 = sparseIntArray.get(i10, -1);
            if (i11 == -1) {
                mediaQueue.zzb();
                return;
            } else {
                sparseIntArray.delete(i10);
                arrayList.add(Integer.valueOf(i11));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList);
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzh();
        mediaQueue2.zzb.removeAll(CastUtils.zzf(iArr));
        mediaQueue2.zzg();
        mediaQueue2.zzm(CastUtils.zze(arrayList));
        mediaQueue2.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzf(MediaQueueItem[] mediaQueueItemArr) {
        HashSet hashSet = new HashSet();
        MediaQueue mediaQueue = this.zza;
        List list = mediaQueue.zze;
        list.clear();
        for (MediaQueueItem mediaQueueItem : mediaQueueItemArr) {
            int itemId = mediaQueueItem.getItemId();
            mediaQueue.zzd.put(Integer.valueOf(itemId), mediaQueueItem);
            int i10 = mediaQueue.zzc.get(itemId, -1);
            if (i10 == -1) {
                mediaQueue.zzb();
                return;
            }
            hashSet.add(Integer.valueOf(i10));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int i11 = mediaQueue.zzc.get(((Integer) it.next()).intValue(), -1);
            if (i11 != -1) {
                hashSet.add(Integer.valueOf(i11));
            }
        }
        list.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        mediaQueue.zzh();
        mediaQueue.zzl(CastUtils.zze(arrayList));
        mediaQueue.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzg(List list, List list2, int i10) {
        int size;
        ArrayList arrayList = new ArrayList();
        if (i10 == 0) {
            size = this.zza.zzb.size();
        } else if (list2.isEmpty()) {
            this.zza.zzo().w("Received a Queue Reordered message with an empty reordered items IDs list.", new Object[0]);
            size = -1;
        } else {
            SparseIntArray sparseIntArray = this.zza.zzc;
            size = sparseIntArray.get(i10, -1);
            if (size == -1) {
                size = sparseIntArray.get(((Integer) list2.get(0)).intValue(), -1);
            }
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            MediaQueue mediaQueue = this.zza;
            int i11 = mediaQueue.zzc.get(iIntValue, -1);
            if (i11 == -1) {
                mediaQueue.zzb();
                return;
            }
            arrayList.add(Integer.valueOf(i11));
        }
        MediaQueue mediaQueue2 = this.zza;
        mediaQueue2.zzh();
        mediaQueue2.zzb = list;
        mediaQueue2.zzg();
        mediaQueue2.zzn(arrayList, size);
        mediaQueue2.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzh() {
        this.zza.zzb();
    }
}
