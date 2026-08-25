package com.google.android.gms.cast.framework.media;

import androidx.recyclerview.widget.l0;
import androidx.recyclerview.widget.p1;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaQueueRecyclerViewAdapter<VH extends p1> extends l0 {
    private final MediaQueue zza;
    private final MediaQueue.Callback zzb;

    public MediaQueueRecyclerViewAdapter(MediaQueue mediaQueue) {
        this.zza = mediaQueue;
        zzp zzpVar = new zzp(this, null);
        this.zzb = zzpVar;
        mediaQueue.registerCallback(zzpVar);
    }

    public void dispose() {
        this.zza.unregisterCallback(this.zzb);
    }

    public MediaQueueItem getItem(int i10) {
        return this.zza.getItemAtIndex(i10);
    }

    @Override // androidx.recyclerview.widget.l0
    public int getItemCount() {
        return this.zza.getItemCount();
    }

    @Override // androidx.recyclerview.widget.l0
    public long getItemId(int i10) {
        return this.zza.itemIdAtIndex(i10);
    }

    public MediaQueue getMediaQueue() {
        return this.zza;
    }
}
