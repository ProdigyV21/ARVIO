package c2;

import android.os.Parcel;
import com.google.android.gms.common.api.internal.t;
import com.google.android.gms.tasks.i;
import n4.h;
import n4.k;
import n4.n;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements f, t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7378i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f7379l;

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) {
        n nVar = (n) obj;
        i iVar = (i) obj2;
        switch (this.f7378i) {
            case 3:
                k kVar = new k(iVar);
                h hVar = (h) nVar.getService();
                String str = this.f7379l;
                Parcel parcelA = hVar.a();
                parcelA.writeString(str);
                int i10 = n4.e.f20492a;
                parcelA.writeStrongBinder(kVar);
                hVar.c(5, parcelA);
                break;
            default:
                h hVar2 = (h) nVar.getService();
                n4.a aVar = new n4.a(0, iVar);
                String str2 = this.f7379l;
                Parcel parcelA2 = hVar2.a();
                parcelA2.writeString(str2);
                int i11 = n4.e.f20492a;
                parcelA2.writeStrongBinder(aVar);
                hVar2.c(2, parcelA2);
                break;
        }
    }

    @Override // c2.f
    public String i() {
        return this.f7379l;
    }

    public String toString() {
        switch (this.f7378i) {
            case 2:
                return this.f7379l;
            case 5:
                return androidx.compose.foundation.c.u(new StringBuilder("<"), this.f7379l, '>');
            default:
                return super.toString();
        }
    }

    public /* synthetic */ a(com.google.android.gms.common.api.n nVar, String str, int i10) {
        this.f7378i = i10;
        this.f7379l = str;
    }

    public /* synthetic */ a(String str, int i10) {
        this.f7378i = i10;
        this.f7379l = str;
    }

    @Override // c2.f
    public void j(e eVar) {
    }
}
