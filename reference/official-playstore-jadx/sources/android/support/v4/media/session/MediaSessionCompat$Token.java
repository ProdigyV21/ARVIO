package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new u(2);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f850l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f851m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f849i = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k2.f f852n = null;

    public MediaSessionCompat$Token(Object obj, n nVar) {
        this.f850l = obj;
        this.f851m = nVar;
    }

    public final d a() {
        d dVar;
        synchronized (this.f849i) {
            dVar = this.f851m;
        }
        return dVar;
    }

    public final void b(d dVar) {
        synchronized (this.f849i) {
            this.f851m = dVar;
        }
    }

    public final void c(k2.f fVar) {
        synchronized (this.f849i) {
            this.f852n = fVar;
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        Object obj2 = ((MediaSessionCompat$Token) obj).f850l;
        Object obj3 = this.f850l;
        if (obj3 == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f850l;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeParcelable((Parcelable) this.f850l, i10);
    }
}
