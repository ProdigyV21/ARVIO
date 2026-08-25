package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a1 extends View.BaseSavedState {
    public static final Parcelable.Creator<a1> CREATOR = new z0();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1505i;

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeByte(this.f1505i ? (byte) 1 : (byte) 0);
    }
}
