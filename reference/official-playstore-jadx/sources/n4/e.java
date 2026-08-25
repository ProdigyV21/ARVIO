package n4;

import android.os.BadParcelableException;
import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20492a = 0;

    static {
        e.class.getClassLoader();
    }

    public static void a(Parcel parcel) {
        int iDataAvail = parcel.dataAvail();
        if (iDataAvail > 0) {
            throw new BadParcelableException(a0.c.i(iDataAvail, "Parcel data not fully consumed, unread size: "));
        }
    }
}
