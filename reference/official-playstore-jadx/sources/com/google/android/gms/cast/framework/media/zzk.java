package com.google.android.gms.cast.framework.media;

import android.util.LruCache;
import com.google.android.gms.common.internal.t;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzk extends LruCache {
    final /* synthetic */ MediaQueue zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzk(MediaQueue mediaQueue, int i10) {
        super(i10);
        Objects.requireNonNull(mediaQueue);
        this.zza = mediaQueue;
    }

    @Override // android.util.LruCache
    public final /* bridge */ /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        Integer num = (Integer) obj;
        if (z) {
            List list = this.zza.zze;
            t.i(list);
            list.add(num);
        }
    }
}
