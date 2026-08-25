package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class x3 extends x0.b {
    public static final Parcelable.Creator<x3> CREATOR = new w3(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1834m;

    public x3(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f1834m = ((Boolean) parcel.readValue(null)).booleanValue();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SearchView.SavedState{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" isIconified=");
        return a0.c.m("}", this.f1834m, sb2);
    }

    @Override // x0.b, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeValue(Boolean.valueOf(this.f1834m));
    }
}
