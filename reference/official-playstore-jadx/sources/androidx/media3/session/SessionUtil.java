package androidx.media3.session;

import android.content.Context;
import android.os.RemoteException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
class SessionUtil {
    public static final int PACKAGE_CANT_CHECK = 2;
    public static final int PACKAGE_INVALID = 1;
    public static final int PACKAGE_VALID = 0;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PackageValidationResult {
    }

    private SessionUtil() {
    }

    public static int checkPackageValidity(Context context, String str, int i10) {
        if (str == null) {
            return 1;
        }
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i10);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return 2;
        }
        for (String str2 : packagesForUid) {
            if (str2.equals(str)) {
                return 0;
            }
        }
        return 1;
    }

    public static void disconnectIMediaController(IMediaController iMediaController) {
        if (iMediaController != null) {
            try {
                iMediaController.onDisconnected(0);
            } catch (RemoteException unused) {
            }
        }
    }
}
