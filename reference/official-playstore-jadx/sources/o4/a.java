package o4;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a implements IInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f20767c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final IBinder f20768d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f20769e;

    public /* synthetic */ a(IBinder iBinder, String str, int i10) {
        this.f20767c = i10;
        this.f20768d = iBinder;
        this.f20769e = str;
    }

    public Parcel a(int i10, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.f20768d.transact(i10, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e5) {
                parcelObtain.recycle();
                throw e5;
            }
        } finally {
            parcel.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        switch (this.f20767c) {
        }
        return this.f20768d;
    }

    public Parcel c() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.f20769e);
        return parcelObtain;
    }
}
