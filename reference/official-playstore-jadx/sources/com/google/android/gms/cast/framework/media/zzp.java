package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.MediaQueue;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzp extends MediaQueue.Callback {
    final /* synthetic */ MediaQueueRecyclerViewAdapter zza;

    public /* synthetic */ zzp(MediaQueueRecyclerViewAdapter mediaQueueRecyclerViewAdapter, byte[] bArr) {
        Objects.requireNonNull(mediaQueueRecyclerViewAdapter);
        this.zza = mediaQueueRecyclerViewAdapter;
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsInsertedInRange(int i10, int i11) {
        this.zza.notifyItemRangeInserted(i10, i11);
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsReloaded() {
        this.zza.notifyDataSetChanged();
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsRemovedAtIndexes(int[] iArr) {
        if (iArr.length > 1) {
            this.zza.notifyDataSetChanged();
            return;
        }
        for (int i10 : iArr) {
            this.zza.notifyItemRemoved(i10);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsReorderedAtIndexes(List<Integer> list, int i10) {
        this.zza.notifyDataSetChanged();
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsUpdatedAtIndexes(int[] iArr) {
        for (int i10 : iArr) {
            this.zza.notifyItemChanged(i10);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void mediaQueueChanged() {
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void mediaQueueWillChange() {
    }
}
