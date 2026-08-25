package u4;

import android.os.Parcel;
import com.google.android.gms.common.api.internal.v0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c extends o4.b implements d {
    @Override // o4.b
    public final boolean zaa(int i10, Parcel parcel, Parcel parcel2, int i11) {
        switch (i10) {
            case 3:
                o4.c.b(parcel);
                break;
            case 4:
                o4.c.b(parcel);
                break;
            case 5:
            default:
                return false;
            case 6:
                o4.c.b(parcel);
                break;
            case 7:
                o4.c.b(parcel);
                break;
            case 8:
                g gVar = (g) o4.c.a(parcel, g.CREATOR);
                o4.c.b(parcel);
                ((v0) this).f0(gVar);
                break;
            case 9:
                o4.c.b(parcel);
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
