package androidx.media3.exoplayer.upstream;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements CmcdConfiguration.Factory, Consumer {
    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((ExecutorService) obj).shutdown();
    }

    @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.Factory
    public CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem) {
        return d.a(mediaItem);
    }
}
