package u0;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import i4.s0;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends View.BaseSavedState {
    public static final Parcelable.Creator<k> CREATOR = new s0(8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22035i;

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("HorizontalScrollView.SavedState{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" scrollPosition=");
        return androidx.compose.material3.d.j(this.f22035i, "}", sb2);
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeInt(this.f22035i);
    }
}
