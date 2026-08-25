package androidx.media3.datasource.cache;

import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    public static long a(ContentMetadata contentMetadata) {
        return contentMetadata.get(ContentMetadata.KEY_CONTENT_LENGTH, -1L);
    }

    public static Uri b(ContentMetadata contentMetadata) {
        String str = contentMetadata.get(ContentMetadata.KEY_REDIRECTED_URI, (String) null);
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }
}
