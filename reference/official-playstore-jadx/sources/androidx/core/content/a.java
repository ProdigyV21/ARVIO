package androidx.core.content;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    public static File a(Context context) {
        return context.getCodeCacheDir();
    }

    public static Drawable b(Context context, int i10) {
        return context.getDrawable(i10);
    }

    public static File c(Context context) {
        return context.getNoBackupFilesDir();
    }
}
