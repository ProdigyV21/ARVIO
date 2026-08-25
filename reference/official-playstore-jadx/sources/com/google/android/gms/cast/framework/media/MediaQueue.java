package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.util.SparseIntArray;
import androidx.loader.content.j;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.api.y;
import com.google.android.gms.common.internal.t;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueue {
    long zza;
    LruCache zzd;
    private final RemoteMediaClient zzh;
    private v zzl;
    private v zzm;
    private final Set zzn = Collections.synchronizedSet(new HashSet());
    private final Logger zzg = new Logger("MediaQueue");
    private final int zzi = Math.max(20, 1);
    List zzb = new ArrayList();
    final SparseIntArray zzc = new SparseIntArray();
    final List zze = new ArrayList();
    final Deque zzf = new ArrayDeque(20);
    private final Handler zzj = new j(Looper.getMainLooper(), 2);
    private final TimerTask zzk = new zzj(this);

    public static abstract class Callback {
        public void itemsInsertedInRange(int i10, int i11) {
        }

        public void itemsReloaded() {
        }

        public void itemsRemovedAtIndexes(int[] iArr) {
        }

        public void itemsReorderedAtIndexes(List<Integer> list, int i10) {
        }

        public void itemsUpdatedAtIndexes(int[] iArr) {
        }

        public void mediaQueueChanged() {
        }

        public void mediaQueueWillChange() {
        }
    }

    public MediaQueue(RemoteMediaClient remoteMediaClient, int i10, int i11) {
        this.zzh = remoteMediaClient;
        remoteMediaClient.registerCallback(new zzn(this));
        zzp(20);
        this.zza = zze();
        zzb();
    }

    private final void zzp(int i10) {
        this.zzd = new zzk(this, i10);
    }

    private final void zzq() {
        zzr();
        this.zzj.postDelayed(this.zzk, 500L);
    }

    private final void zzr() {
        this.zzj.removeCallbacks(this.zzk);
    }

    private final void zzs() {
        v vVar = this.zzm;
        if (vVar != null) {
            vVar.cancel();
            this.zzm = null;
        }
    }

    private final void zzt() {
        v vVar = this.zzl;
        if (vVar != null) {
            vVar.cancel();
            this.zzl = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzu, reason: merged with bridge method [inline-methods] */
    public final long zze() {
        MediaStatus mediaStatus = this.zzh.getMediaStatus();
        if (mediaStatus == null || mediaStatus.zzc()) {
            return 0L;
        }
        return mediaStatus.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzv, reason: merged with bridge method [inline-methods] */
    public final void zzh() {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).mediaQueueWillChange();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzw, reason: merged with bridge method [inline-methods] */
    public final void zzi() {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).mediaQueueChanged();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzx, reason: merged with bridge method [inline-methods] */
    public final void zzj() {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).itemsReloaded();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzy, reason: merged with bridge method [inline-methods] */
    public final void zzl(int[] iArr) {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).itemsUpdatedAtIndexes(iArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public v fetchMoreItemsRelativeToIndex(int i10, int i11, int i12) {
        t.e("Must be called from the main thread.");
        if (this.zza == 0) {
            return RemoteMediaClient.zzn(2100, "No active media session");
        }
        int iItemIdAtIndex = itemIdAtIndex(i10);
        return iItemIdAtIndex == 0 ? RemoteMediaClient.zzn(2001, "index out of bound") : this.zzh.zze(iItemIdAtIndex, i11, i12);
    }

    public MediaQueueItem getItemAtIndex(int i10) {
        t.e("Must be called from the main thread.");
        return getItemAtIndex(i10, true);
    }

    public int getItemCount() {
        t.e("Must be called from the main thread.");
        return this.zzb.size();
    }

    public int[] getItemIds() {
        t.e("Must be called from the main thread.");
        return CastUtils.zze(this.zzb);
    }

    public int indexOfItemWithId(int i10) {
        t.e("Must be called from the main thread.");
        return this.zzc.get(i10, -1);
    }

    public int itemIdAtIndex(int i10) {
        t.e("Must be called from the main thread.");
        if (i10 < 0 || i10 >= this.zzb.size()) {
            return 0;
        }
        return ((Integer) this.zzb.get(i10)).intValue();
    }

    public void registerCallback(Callback callback) {
        t.e("Must be called from the main thread.");
        this.zzn.add(callback);
    }

    public void setCacheCapacity(int i10) {
        t.e("Must be called from the main thread.");
        LruCache lruCache = this.zzd;
        ArrayList arrayList = new ArrayList();
        zzp(i10);
        int size = lruCache.size();
        for (Map.Entry entry : lruCache.snapshot().entrySet()) {
            if (size > i10) {
                int i11 = this.zzc.get(((Integer) entry.getKey()).intValue(), -1);
                if (i11 != -1) {
                    arrayList.add(Integer.valueOf(i11));
                }
            } else {
                this.zzd.put((Integer) entry.getKey(), (MediaQueueItem) entry.getValue());
            }
            size--;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList);
        zzh();
        zzl(CastUtils.zze(arrayList));
        zzi();
    }

    public void unregisterCallback(Callback callback) {
        t.e("Must be called from the main thread.");
        this.zzn.remove(callback);
    }

    public final void zza() {
        zzh();
        this.zzb.clear();
        this.zzc.clear();
        this.zzd.evictAll();
        this.zze.clear();
        zzr();
        this.zzf.clear();
        zzs();
        zzt();
        zzj();
        zzi();
    }

    public final void zzb() {
        t.e("Must be called from the main thread.");
        if (this.zza != 0 && this.zzm == null) {
            zzs();
            zzt();
            v vVarZzc = this.zzh.zzc();
            this.zzm = vVarZzc;
            vVarZzc.setResultCallback(new y() { // from class: com.google.android.gms.cast.framework.media.zzm
                @Override // com.google.android.gms.common.api.y
                public final /* synthetic */ void onResult(x xVar) {
                    this.zza.zzd((RemoteMediaClient.MediaChannelResult) xVar);
                }
            });
        }
    }

    public final void zzc(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
        Status status = mediaChannelResult.getStatus();
        int i10 = status.f12675i;
        if (i10 != 0) {
            this.zzg.w("Error fetching queue items, statusCode=" + i10 + ", statusMessage=" + status.f12676l, new Object[0]);
        }
        this.zzl = null;
        if (this.zzf.isEmpty()) {
            return;
        }
        zzq();
    }

    public final void zzd(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
        Status status = mediaChannelResult.getStatus();
        int i10 = status.f12675i;
        if (i10 != 0) {
            this.zzg.w("Error fetching queue item ids, statusCode=" + i10 + ", statusMessage=" + status.f12676l, new Object[0]);
        }
        this.zzm = null;
        if (this.zzf.isEmpty()) {
            return;
        }
        zzq();
    }

    public final /* synthetic */ void zzf() {
        Deque deque = this.zzf;
        if (deque.isEmpty() || this.zzl != null || this.zza == 0) {
            return;
        }
        v vVarZzd = this.zzh.zzd(CastUtils.zze(deque));
        this.zzl = vVarZzd;
        vVarZzd.setResultCallback(new y() { // from class: com.google.android.gms.cast.framework.media.zzl
            @Override // com.google.android.gms.common.api.y
            public final /* synthetic */ void onResult(x xVar) {
                this.zza.zzc((RemoteMediaClient.MediaChannelResult) xVar);
            }
        });
        deque.clear();
    }

    public final /* synthetic */ void zzg() {
        SparseIntArray sparseIntArray = this.zzc;
        sparseIntArray.clear();
        for (int i10 = 0; i10 < this.zzb.size(); i10++) {
            sparseIntArray.put(((Integer) this.zzb.get(i10)).intValue(), i10);
        }
    }

    public final /* synthetic */ void zzk(int i10, int i11) {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).itemsInsertedInRange(i10, i11);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzm(int[] iArr) {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).itemsRemovedAtIndexes(iArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzn(List list, int i10) {
        Set set = this.zzn;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).itemsReorderedAtIndexes(list, i10);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ Logger zzo() {
        return this.zzg;
    }

    public MediaQueueItem getItemAtIndex(int i10, boolean z) {
        t.e("Must be called from the main thread.");
        if (i10 < 0 || i10 >= this.zzb.size()) {
            return null;
        }
        Integer num = (Integer) this.zzb.get(i10);
        num.intValue();
        MediaQueueItem mediaQueueItem = (MediaQueueItem) this.zzd.get(num);
        if (mediaQueueItem == null && z) {
            Deque deque = this.zzf;
            if (!deque.contains(num)) {
                while (deque.size() >= this.zzi) {
                    deque.removeFirst();
                }
                deque.add(num);
                zzq();
            }
        }
        return mediaQueueItem;
    }
}
