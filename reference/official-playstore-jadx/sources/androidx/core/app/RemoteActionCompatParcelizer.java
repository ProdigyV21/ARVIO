package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(k2.d dVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        k2.f fVarH = remoteActionCompat.f1950a;
        boolean z = true;
        if (dVar.e(1)) {
            fVarH = dVar.h();
        }
        remoteActionCompat.f1950a = (IconCompat) fVarH;
        CharSequence charSequence = remoteActionCompat.f1951b;
        if (dVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((k2.e) dVar).f19466e);
        }
        remoteActionCompat.f1951b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f1952c;
        if (dVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((k2.e) dVar).f19466e);
        }
        remoteActionCompat.f1952c = charSequence2;
        remoteActionCompat.f1953d = (PendingIntent) dVar.g(remoteActionCompat.f1953d, 4);
        boolean z5 = remoteActionCompat.f1954e;
        if (dVar.e(5)) {
            z5 = ((k2.e) dVar).f19466e.readInt() != 0;
        }
        remoteActionCompat.f1954e = z5;
        boolean z10 = remoteActionCompat.f1955f;
        if (!dVar.e(6)) {
            z = z10;
        } else if (((k2.e) dVar).f19466e.readInt() == 0) {
            z = false;
        }
        remoteActionCompat.f1955f = z;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, k2.d dVar) {
        dVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f1950a;
        dVar.i(1);
        dVar.l(iconCompat);
        CharSequence charSequence = remoteActionCompat.f1951b;
        dVar.i(2);
        Parcel parcel = ((k2.e) dVar).f19466e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f1952c;
        dVar.i(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        dVar.k(remoteActionCompat.f1953d, 4);
        boolean z = remoteActionCompat.f1954e;
        dVar.i(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z5 = remoteActionCompat.f1955f;
        dVar.i(6);
        parcel.writeInt(z5 ? 1 : 0);
    }
}
