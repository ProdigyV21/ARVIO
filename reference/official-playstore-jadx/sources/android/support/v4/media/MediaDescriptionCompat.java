package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new d(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f829i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final CharSequence f830l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f831m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CharSequence f832n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Bitmap f833o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Uri f834p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Bundle f835q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Uri f836r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public MediaDescription f837s;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f829i = str;
        this.f830l = charSequence;
        this.f831m = charSequence2;
        this.f832n = charSequence3;
        this.f833o = bitmap;
        this.f834p = uri;
        this.f835q = bundle;
        this.f836r = uri2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f830l) + ", " + ((Object) this.f831m) + ", " + ((Object) this.f832n);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        MediaDescription mediaDescriptionA = this.f837s;
        if (mediaDescriptionA == null) {
            MediaDescription.Builder builderB = e.b();
            e.n(builderB, this.f829i);
            e.p(builderB, this.f830l);
            e.o(builderB, this.f831m);
            e.j(builderB, this.f832n);
            e.l(builderB, this.f833o);
            e.m(builderB, this.f834p);
            e.k(builderB, this.f835q);
            f.b(builderB, this.f836r);
            mediaDescriptionA = e.a(builderB);
            this.f837s = mediaDescriptionA;
        }
        mediaDescriptionA.writeToParcel(parcel, i10);
    }
}
