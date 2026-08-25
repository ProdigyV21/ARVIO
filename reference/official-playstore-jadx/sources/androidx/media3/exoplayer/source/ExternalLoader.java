package androidx.media3.exoplayer.source;

import android.net.Uri;
import com.google.common.util.concurrent.d1;

/* JADX INFO: loaded from: classes3.dex */
public interface ExternalLoader {

    public static final class LoadRequest {
        public final Uri uri;

        public LoadRequest(Uri uri) {
            this.uri = uri;
        }
    }

    d1<?> load(LoadRequest loadRequest);
}
