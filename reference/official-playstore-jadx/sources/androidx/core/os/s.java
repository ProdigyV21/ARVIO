package androidx.core.os;

import android.content.Context;
import android.os.UserManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s {
    public static boolean a(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
