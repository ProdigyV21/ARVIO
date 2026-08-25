package k4;

import android.os.IInterface;

/* JADX INFO: loaded from: classes4.dex */
public interface d extends IInterface {
    boolean getBooleanFlagValue(String str, boolean z, int i10);

    int getIntFlagValue(String str, int i10, int i11);

    long getLongFlagValue(String str, long j10, int i10);

    String getStringFlagValue(String str, String str2, int i10);

    void init(com.google.android.gms.dynamic.b bVar);
}
