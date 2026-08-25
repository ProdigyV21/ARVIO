package k2;

import android.os.Parcel;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f19465d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f19466e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f19467f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f19468g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f19469h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19470i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f19471j;
    public int k;

    public e(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new t.e(0), new t.e(0), new t.e(0));
    }

    @Override // k2.d
    public final e a() {
        Parcel parcel = this.f19466e;
        int iDataPosition = parcel.dataPosition();
        int i10 = this.f19471j;
        if (i10 == this.f19467f) {
            i10 = this.f19468g;
        }
        return new e(parcel, iDataPosition, i10, a0.c.p(new StringBuilder(), this.f19469h, "  "), this.f19462a, this.f19463b, this.f19464c);
    }

    @Override // k2.d
    public final boolean e(int i10) {
        while (this.f19471j < this.f19468g) {
            int i11 = this.k;
            if (i11 == i10) {
                return true;
            }
            if (String.valueOf(i11).compareTo(String.valueOf(i10)) > 0) {
                return false;
            }
            int i12 = this.f19471j;
            Parcel parcel = this.f19466e;
            parcel.setDataPosition(i12);
            int i13 = parcel.readInt();
            this.k = parcel.readInt();
            this.f19471j += i13;
        }
        return this.k == i10;
    }

    @Override // k2.d
    public final void i(int i10) {
        int i11 = this.f19470i;
        SparseIntArray sparseIntArray = this.f19465d;
        Parcel parcel = this.f19466e;
        if (i11 >= 0) {
            int i12 = sparseIntArray.get(i11);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i12);
            parcel.writeInt(iDataPosition - i12);
            parcel.setDataPosition(iDataPosition);
        }
        this.f19470i = i10;
        sparseIntArray.put(i10, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i10);
    }

    public e(Parcel parcel, int i10, int i11, String str, t.e eVar, t.e eVar2, t.e eVar3) {
        super(eVar, eVar2, eVar3);
        this.f19465d = new SparseIntArray();
        this.f19470i = -1;
        this.k = -1;
        this.f19466e = parcel;
        this.f19467f = i10;
        this.f19468g = i11;
        this.f19471j = i10;
        this.f19469h = str;
    }
}
