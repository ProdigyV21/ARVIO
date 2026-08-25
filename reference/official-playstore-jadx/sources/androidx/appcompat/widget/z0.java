package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class z0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        a1 a1Var = new a1(parcel);
        a1Var.f1505i = parcel.readByte() != 0;
        return a1Var;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i10) {
        return new a1[i10];
    }
}
