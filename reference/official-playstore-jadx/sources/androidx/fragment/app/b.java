package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2665a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f2665a) {
            case 0:
                return new c(parcel);
            case 1:
                return new d(parcel);
            case 2:
                x0 x0Var = new x0();
                x0Var.f2865i = parcel.readString();
                x0Var.f2866l = parcel.readInt();
                return x0Var;
            case 3:
                e1 e1Var = new e1();
                e1Var.f2719o = null;
                e1Var.f2720p = new ArrayList();
                e1Var.f2721q = new ArrayList();
                e1Var.f2715i = parcel.createStringArrayList();
                e1Var.f2716l = parcel.createStringArrayList();
                e1Var.f2717m = (c[]) parcel.createTypedArray(c.CREATOR);
                e1Var.f2718n = parcel.readInt();
                e1Var.f2719o = parcel.readString();
                e1Var.f2720p = parcel.createStringArrayList();
                e1Var.f2721q = parcel.createTypedArrayList(d.CREATOR);
                e1Var.f2722r = parcel.createTypedArrayList(x0.CREATOR);
                return e1Var;
            default:
                return new j1(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i10) {
        switch (this.f2665a) {
            case 0:
                return new c[i10];
            case 1:
                return new d[i10];
            case 2:
                return new x0[i10];
            case 3:
                return new e1[i10];
            default:
                return new j1[i10];
        }
    }
}
