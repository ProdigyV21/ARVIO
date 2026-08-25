package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaQueueArrayAdapter extends ArrayAdapter<MediaQueueItem> {
    private final MediaQueue zza;
    private final MediaQueue.Callback zzb;

    public MediaQueueArrayAdapter(MediaQueue mediaQueue, Context context, int i10) {
        super(context, i10);
        this.zza = mediaQueue;
        zzo zzoVar = new zzo(this, null);
        this.zzb = zzoVar;
        mediaQueue.registerCallback(zzoVar);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    public void dispose() {
        this.zza.unregisterCallback(this.zzb);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.zza.getItemCount();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public MediaQueueItem getItem(int i10) {
        return this.zza.getItemAtIndex(i10);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i10) {
        return this.zza.itemIdAtIndex(i10);
    }

    public MediaQueue getMediaQueue() {
        return this.zza;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return this.zza.getItemCount() == 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i10) {
        return this.zza.getItemAtIndex(i10, false) != null;
    }
}
