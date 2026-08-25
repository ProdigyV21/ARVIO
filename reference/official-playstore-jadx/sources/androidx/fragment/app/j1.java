package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class j1 implements Parcelable {
    public static final Parcelable.Creator<j1> CREATOR = new b(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f2747i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f2748l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f2749m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2750n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2751o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f2752p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f2753q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f2754r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f2755s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Bundle f2756t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f2757u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f2758v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Bundle f2759w;

    public j1(c0 c0Var) {
        this.f2747i = c0Var.getClass().getName();
        this.f2748l = c0Var.mWho;
        this.f2749m = c0Var.mFromLayout;
        this.f2750n = c0Var.mFragmentId;
        this.f2751o = c0Var.mContainerId;
        this.f2752p = c0Var.mTag;
        this.f2753q = c0Var.mRetainInstance;
        this.f2754r = c0Var.mRemoving;
        this.f2755s = c0Var.mDetached;
        this.f2756t = c0Var.mArguments;
        this.f2757u = c0Var.mHidden;
        this.f2758v = c0Var.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("FragmentState{");
        sb2.append(this.f2747i);
        sb2.append(" (");
        sb2.append(this.f2748l);
        sb2.append(")}:");
        if (this.f2749m) {
            sb2.append(" fromLayout");
        }
        int i10 = this.f2751o;
        if (i10 != 0) {
            sb2.append(" id=0x");
            sb2.append(Integer.toHexString(i10));
        }
        String str = this.f2752p;
        if (str != null && !str.isEmpty()) {
            sb2.append(" tag=");
            sb2.append(str);
        }
        if (this.f2753q) {
            sb2.append(" retainInstance");
        }
        if (this.f2754r) {
            sb2.append(" removing");
        }
        if (this.f2755s) {
            sb2.append(" detached");
        }
        if (this.f2757u) {
            sb2.append(" hidden");
        }
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f2747i);
        parcel.writeString(this.f2748l);
        parcel.writeInt(this.f2749m ? 1 : 0);
        parcel.writeInt(this.f2750n);
        parcel.writeInt(this.f2751o);
        parcel.writeString(this.f2752p);
        parcel.writeInt(this.f2753q ? 1 : 0);
        parcel.writeInt(this.f2754r ? 1 : 0);
        parcel.writeInt(this.f2755s ? 1 : 0);
        parcel.writeBundle(this.f2756t);
        parcel.writeInt(this.f2757u ? 1 : 0);
        parcel.writeBundle(this.f2759w);
        parcel.writeInt(this.f2758v);
    }

    public j1(Parcel parcel) {
        this.f2747i = parcel.readString();
        this.f2748l = parcel.readString();
        this.f2749m = parcel.readInt() != 0;
        this.f2750n = parcel.readInt();
        this.f2751o = parcel.readInt();
        this.f2752p = parcel.readString();
        this.f2753q = parcel.readInt() != 0;
        this.f2754r = parcel.readInt() != 0;
        this.f2755s = parcel.readInt() != 0;
        this.f2756t = parcel.readBundle();
        this.f2757u = parcel.readInt() != 0;
        this.f2759w = parcel.readBundle();
        this.f2758v = parcel.readInt();
    }
}
