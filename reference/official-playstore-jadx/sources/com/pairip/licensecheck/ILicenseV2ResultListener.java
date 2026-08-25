package com.pairip.licensecheck;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ILicenseV2ResultListener extends IInterface {
    public static final String DESCRIPTOR = "com.android.vending.licensing.ILicenseV2ResultListener";

    void verifyLicense(int responseCode, Bundle responsePayload) throws RemoteException;

    public static abstract class Stub extends Binder implements ILicenseV2ResultListener {
        static final int TRANSACTION_VERIFY_LICENSE = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ILicenseV2ResultListener.DESCRIPTOR);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(ILicenseV2ResultListener.DESCRIPTOR);
            }
            if (code == 1) {
                verifyLicense(data.readInt(), (Bundle) readTypedObject(data, Bundle.CREATOR));
            } else if (code == 1598968902) {
                reply.writeString(ILicenseV2ResultListener.DESCRIPTOR);
            } else {
                return super.onTransact(code, data, reply, flags);
            }
            return true;
        }

        private static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> c10) {
            if (parcel.readInt() != 0) {
                return c10.createFromParcel(parcel);
            }
            return null;
        }
    }
}
