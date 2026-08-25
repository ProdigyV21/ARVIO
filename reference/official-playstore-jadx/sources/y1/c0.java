package y1;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 extends Binder implements u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MultiInstanceInvalidationService f22821c;

    public c0(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.f22821c = multiInstanceInvalidationService;
        attachInterface(this, "androidx.room.IMultiInstanceInvalidationService");
    }

    public final void a(String[] strArr, int i10) {
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f22821c;
        synchronized (multiInstanceInvalidationService.f5134m) {
            String str = (String) multiInstanceInvalidationService.f5133l.get(Integer.valueOf(i10));
            if (str == null) {
                Log.w("ROOM", "Remote invalidation client ID not registered");
                return;
            }
            int iBeginBroadcast = multiInstanceInvalidationService.f5134m.beginBroadcast();
            for (int i11 = 0; i11 < iBeginBroadcast; i11++) {
                try {
                    Integer num = (Integer) multiInstanceInvalidationService.f5134m.getBroadcastCookie(i11);
                    int iIntValue = num.intValue();
                    String str2 = (String) multiInstanceInvalidationService.f5133l.get(num);
                    if (i10 != iIntValue && str.equals(str2)) {
                        try {
                            ((t) multiInstanceInvalidationService.f5134m.getBroadcastItem(i11)).j(strArr);
                        } catch (RemoteException e5) {
                            Log.w("ROOM", "Error invoking a remote callback", e5);
                        }
                    }
                } finally {
                    multiInstanceInvalidationService.f5134m.finishBroadcast();
                }
            }
        }
    }

    public final int c(t tVar, String str) {
        int i10 = 0;
        if (str == null) {
            return 0;
        }
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f22821c;
        synchronized (multiInstanceInvalidationService.f5134m) {
            try {
                int i11 = multiInstanceInvalidationService.f5132i + 1;
                multiInstanceInvalidationService.f5132i = i11;
                if (multiInstanceInvalidationService.f5134m.register(tVar, Integer.valueOf(i11))) {
                    multiInstanceInvalidationService.f5133l.put(Integer.valueOf(i11), str);
                    i10 = i11;
                } else {
                    multiInstanceInvalidationService.f5132i--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i10;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 >= 1 && i10 <= 16777215) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
        }
        if (i10 == 1598968902) {
            parcel2.writeString("androidx.room.IMultiInstanceInvalidationService");
            return true;
        }
        t tVar = null;
        t tVar2 = null;
        if (i10 == 1) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof t)) {
                    s sVar = new s();
                    sVar.f22860c = strongBinder;
                    tVar = sVar;
                } else {
                    tVar = (t) iInterfaceQueryLocalInterface;
                }
            }
            int iC = c(tVar, parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(iC);
            return true;
        }
        if (i10 != 2) {
            if (i10 != 3) {
                return super.onTransact(i10, parcel, parcel2, i11);
            }
            a(parcel.createStringArray(), parcel.readInt());
            return true;
        }
        IBinder strongBinder2 = parcel.readStrongBinder();
        if (strongBinder2 != null) {
            IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
            if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof t)) {
                s sVar2 = new s();
                sVar2.f22860c = strongBinder2;
                tVar2 = sVar2;
            } else {
                tVar2 = (t) iInterfaceQueryLocalInterface2;
            }
        }
        int i12 = parcel.readInt();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f22821c;
        synchronized (multiInstanceInvalidationService.f5134m) {
            multiInstanceInvalidationService.f5134m.unregister(tVar2);
        }
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
