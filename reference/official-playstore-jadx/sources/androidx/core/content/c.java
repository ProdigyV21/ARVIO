package androidx.core.content;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    public static Context a(Context context) {
        return context.createDeviceProtectedStorageContext();
    }

    public static File b(Context context) {
        return context.getDataDir();
    }

    public static boolean c(Context context) {
        return context.isDeviceProtectedStorage();
    }
}
