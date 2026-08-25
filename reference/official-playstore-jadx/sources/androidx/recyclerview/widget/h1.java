package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 implements Parcelable.ClassLoaderCreator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4939a;

    public /* synthetic */ h1(int i10) {
        this.f4939a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f4939a) {
            case 0:
                return new i1(parcel, null);
            default:
                if (parcel.readParcelable(null) == null) {
                    return x0.b.f22548l;
                }
                throw new IllegalStateException("superState must be null");
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i10) {
        switch (this.f4939a) {
            case 0:
                return new i1[i10];
            default:
                return new x0.b[i10];
        }
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.f4939a) {
            case 0:
                return new i1(parcel, classLoader);
            default:
                if (parcel.readParcelable(classLoader) == null) {
                    return x0.b.f22548l;
                }
                throw new IllegalStateException("superState must be null");
        }
    }
}
