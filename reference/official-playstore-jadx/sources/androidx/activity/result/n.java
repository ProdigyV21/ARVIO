package androidx.activity.result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Parcelable {
    public static final Parcelable.Creator<n> CREATOR = new android.support.v4.media.d(6);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final IntentSender f1012i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Intent f1013l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1014m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1015n;

    public n(IntentSender intentSender, Intent intent, int i10, int i11) {
        this.f1012i = intentSender;
        this.f1013l = intent;
        this.f1014m = i10;
        this.f1015n = i11;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeParcelable(this.f1012i, i10);
        parcel.writeParcelable(this.f1013l, i10);
        parcel.writeInt(this.f1014m);
        parcel.writeInt(this.f1015n);
    }
}
