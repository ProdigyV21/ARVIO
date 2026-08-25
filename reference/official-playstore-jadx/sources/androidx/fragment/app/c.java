package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new b(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f2690i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f2691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int[] f2692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int[] f2693n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2694o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f2695p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f2696q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f2697r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final CharSequence f2698s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f2699t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CharSequence f2700u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f2701v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f2702w;
    public final boolean x;

    public c(a aVar) {
        int size = aVar.f2805a.size();
        this.f2690i = new int[size * 6];
        if (!aVar.f2811g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f2691l = new ArrayList(size);
        this.f2692m = new int[size];
        this.f2693n = new int[size];
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            n1 n1Var = (n1) aVar.f2805a.get(i11);
            int i12 = i10 + 1;
            this.f2690i[i10] = n1Var.f2794a;
            ArrayList arrayList = this.f2691l;
            c0 c0Var = n1Var.f2795b;
            arrayList.add(c0Var != null ? c0Var.mWho : null);
            int[] iArr = this.f2690i;
            iArr[i12] = n1Var.f2796c ? 1 : 0;
            iArr[i10 + 2] = n1Var.f2797d;
            iArr[i10 + 3] = n1Var.f2798e;
            int i13 = i10 + 5;
            iArr[i10 + 4] = n1Var.f2799f;
            i10 += 6;
            iArr[i13] = n1Var.f2800g;
            this.f2692m[i11] = n1Var.f2801h.ordinal();
            this.f2693n[i11] = n1Var.f2802i.ordinal();
        }
        this.f2694o = aVar.f2810f;
        this.f2695p = aVar.f2812h;
        this.f2696q = aVar.f2662r;
        this.f2697r = aVar.f2813i;
        this.f2698s = aVar.f2814j;
        this.f2699t = aVar.k;
        this.f2700u = aVar.f2815l;
        this.f2701v = aVar.f2816m;
        this.f2702w = aVar.f2817n;
        this.x = aVar.f2818o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeIntArray(this.f2690i);
        parcel.writeStringList(this.f2691l);
        parcel.writeIntArray(this.f2692m);
        parcel.writeIntArray(this.f2693n);
        parcel.writeInt(this.f2694o);
        parcel.writeString(this.f2695p);
        parcel.writeInt(this.f2696q);
        parcel.writeInt(this.f2697r);
        TextUtils.writeToParcel(this.f2698s, parcel, 0);
        parcel.writeInt(this.f2699t);
        TextUtils.writeToParcel(this.f2700u, parcel, 0);
        parcel.writeStringList(this.f2701v);
        parcel.writeStringList(this.f2702w);
        parcel.writeInt(this.x ? 1 : 0);
    }

    public c(Parcel parcel) {
        this.f2690i = parcel.createIntArray();
        this.f2691l = parcel.createStringArrayList();
        this.f2692m = parcel.createIntArray();
        this.f2693n = parcel.createIntArray();
        this.f2694o = parcel.readInt();
        this.f2695p = parcel.readString();
        this.f2696q = parcel.readInt();
        this.f2697r = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f2698s = (CharSequence) creator.createFromParcel(parcel);
        this.f2699t = parcel.readInt();
        this.f2700u = (CharSequence) creator.createFromParcel(parcel);
        this.f2701v = parcel.createStringArrayList();
        this.f2702w = parcel.createStringArrayList();
        this.x = parcel.readInt() != 0;
    }
}
