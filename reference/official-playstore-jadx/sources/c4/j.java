package c4;

import android.os.StrictMode;

/* JADX INFO: loaded from: classes4.dex */
public abstract class j {
    public static StrictMode.VmPolicy.Builder a(StrictMode.VmPolicy.Builder builder) {
        return builder.permitUnsafeIntentLaunch();
    }
}
