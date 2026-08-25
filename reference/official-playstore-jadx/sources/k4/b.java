package k4;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements d, IInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final IBinder f19512c;

    public b(IBinder iBinder) {
        this.f19512c = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f19512c;
    }
}
