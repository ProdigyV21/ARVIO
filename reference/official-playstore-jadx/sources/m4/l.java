package m4;

import android.util.Base64;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SecureRandom f20243a = new SecureRandom();

    public static String a() {
        byte[] bArr = new byte[16];
        f20243a.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
