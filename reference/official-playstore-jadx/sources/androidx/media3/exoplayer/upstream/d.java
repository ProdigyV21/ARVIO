package androidx.media3.exoplayer.upstream;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.google.common.collect.i1;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class d {
    public static /* synthetic */ CmcdConfiguration a(MediaItem mediaItem) {
        String string = UUID.randomUUID().toString();
        String str = mediaItem.mediaId;
        if (str == null) {
            str = "";
        }
        return new CmcdConfiguration(string, str, new CmcdConfiguration.RequestConfig() { // from class: androidx.media3.exoplayer.upstream.CmcdConfiguration.Factory.1
            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public final /* synthetic */ i1 getCustomData() {
                return e.a(this);
            }

            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public final /* synthetic */ int getRequestedMaximumThroughputKbps(int i10) {
                return e.b(this, i10);
            }

            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public final /* synthetic */ boolean isKeyAllowed(String str2) {
                return e.c(this, str2);
            }
        });
    }
}
