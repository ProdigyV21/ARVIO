package androidx.core.content;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i {
    public static File[] a(Context context) {
        return context.getExternalMediaDirs();
    }
}
