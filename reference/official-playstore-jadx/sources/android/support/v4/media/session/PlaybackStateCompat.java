package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new u(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f858i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f859l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f860m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f861n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f862o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f863p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final CharSequence f864q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final long f865r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f866s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final long f867t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Bundle f868u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public PlaybackState f869v;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface b {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface c {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface d {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface e {
    }

    public PlaybackStateCompat(int i10, long j10, long j11, float f10, long j12, int i11, CharSequence charSequence, long j13, ArrayList arrayList, long j14, Bundle bundle) {
        this.f858i = i10;
        this.f859l = j10;
        this.f860m = j11;
        this.f861n = f10;
        this.f862o = j12;
        this.f863p = i11;
        this.f864q = charSequence;
        this.f865r = j13;
        this.f866s = new ArrayList(arrayList);
        this.f867t = j14;
        this.f868u = bundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("PlaybackState {state=");
        sb2.append(this.f858i);
        sb2.append(", position=");
        sb2.append(this.f859l);
        sb2.append(", buffered position=");
        sb2.append(this.f860m);
        sb2.append(", speed=");
        sb2.append(this.f861n);
        sb2.append(", updated=");
        sb2.append(this.f865r);
        sb2.append(", actions=");
        sb2.append(this.f862o);
        sb2.append(", error code=");
        sb2.append(this.f863p);
        sb2.append(", error message=");
        sb2.append(this.f864q);
        sb2.append(", custom actions=");
        sb2.append(this.f866s);
        sb2.append(", active item id=");
        return a0.c.j(this.f867t, "}", sb2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f858i);
        parcel.writeLong(this.f859l);
        parcel.writeFloat(this.f861n);
        parcel.writeLong(this.f865r);
        parcel.writeLong(this.f860m);
        parcel.writeLong(this.f862o);
        TextUtils.writeToParcel(this.f864q, parcel, i10);
        parcel.writeTypedList(this.f866s);
        parcel.writeLong(this.f867t);
        parcel.writeBundle(this.f868u);
        parcel.writeInt(this.f863p);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new z();

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final String f870i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final CharSequence f871l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final int f872m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final Bundle f873n;

        public CustomAction(String str, String str2, int i10) {
            this.f870i = str;
            this.f871l = str2;
            this.f872m = i10;
            this.f873n = null;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.f871l) + ", mIcon=" + this.f872m + ", mExtras=" + this.f873n;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i10) {
            parcel.writeString(this.f870i);
            TextUtils.writeToParcel(this.f871l, parcel, i10);
            parcel.writeInt(this.f872m);
            parcel.writeBundle(this.f873n);
        }

        public CustomAction(Parcel parcel) {
            this.f870i = parcel.readString();
            this.f871l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f872m = parcel.readInt();
            this.f873n = parcel.readBundle(v.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f858i = parcel.readInt();
        this.f859l = parcel.readLong();
        this.f861n = parcel.readFloat();
        this.f865r = parcel.readLong();
        this.f860m = parcel.readLong();
        this.f862o = parcel.readLong();
        this.f864q = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f866s = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f867t = parcel.readLong();
        this.f868u = parcel.readBundle(v.class.getClassLoader());
        this.f863p = parcel.readInt();
    }
}
