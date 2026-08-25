package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.MetadataRetrieverInternal;
import androidx.media3.exoplayer.source.TrackGroupArray;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r0 implements MetadataRetrieverInternal.RetrievalTask.OnPreparedListener, MetadataRetrieverInternal.RetrievalTask.OnFailureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MetadataRetrieverInternal f3738a;

    public /* synthetic */ r0(MetadataRetrieverInternal metadataRetrieverInternal) {
        this.f3738a = metadataRetrieverInternal;
    }

    @Override // androidx.media3.exoplayer.MetadataRetrieverInternal.RetrievalTask.OnFailureListener
    public void onFailure(Exception exc) {
        this.f3738a.lambda$startPreparation$2(exc);
    }

    @Override // androidx.media3.exoplayer.MetadataRetrieverInternal.RetrievalTask.OnPreparedListener
    public void onPrepared(TrackGroupArray trackGroupArray, Timeline timeline) {
        this.f3738a.lambda$startPreparation$1(trackGroupArray, timeline);
    }
}
