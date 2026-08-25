package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import k2.d;
import k2.e;

/* JADX INFO: loaded from: classes3.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(d dVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f2090a = dVar.f(iconCompat.f2090a, 1);
        byte[] bArr = iconCompat.f2092c;
        if (dVar.e(2)) {
            Parcel parcel = ((e) dVar).f19466e;
            int i10 = parcel.readInt();
            if (i10 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i10];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f2092c = bArr;
        iconCompat.f2093d = dVar.g(iconCompat.f2093d, 3);
        iconCompat.f2094e = dVar.f(iconCompat.f2094e, 4);
        iconCompat.f2095f = dVar.f(iconCompat.f2095f, 5);
        iconCompat.f2096g = (ColorStateList) dVar.g(iconCompat.f2096g, 6);
        String string = iconCompat.f2098i;
        if (dVar.e(7)) {
            string = ((e) dVar).f19466e.readString();
        }
        iconCompat.f2098i = string;
        String string2 = iconCompat.f2099j;
        if (dVar.e(8)) {
            string2 = ((e) dVar).f19466e.readString();
        }
        iconCompat.f2099j = string2;
        iconCompat.f2097h = PorterDuff.Mode.valueOf(iconCompat.f2098i);
        switch (iconCompat.f2090a) {
            case -1:
                Parcelable parcelable = iconCompat.f2093d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f2091b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f2093d;
                if (parcelable2 != null) {
                    iconCompat.f2091b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.f2092c;
                iconCompat.f2091b = bArr3;
                iconCompat.f2090a = 3;
                iconCompat.f2094e = 0;
                iconCompat.f2095f = bArr3.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.f2092c, Charset.forName("UTF-16"));
                iconCompat.f2091b = str;
                if (iconCompat.f2090a == 2 && iconCompat.f2099j == null) {
                    iconCompat.f2099j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f2091b = iconCompat.f2092c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, d dVar) {
        dVar.getClass();
        iconCompat.f2098i = iconCompat.f2097h.name();
        switch (iconCompat.f2090a) {
            case -1:
                iconCompat.f2093d = (Parcelable) iconCompat.f2091b;
                break;
            case 1:
            case 5:
                iconCompat.f2093d = (Parcelable) iconCompat.f2091b;
                break;
            case 2:
                iconCompat.f2092c = ((String) iconCompat.f2091b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f2092c = (byte[]) iconCompat.f2091b;
                break;
            case 4:
            case 6:
                iconCompat.f2092c = iconCompat.f2091b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i10 = iconCompat.f2090a;
        if (-1 != i10) {
            dVar.j(i10, 1);
        }
        byte[] bArr = iconCompat.f2092c;
        if (bArr != null) {
            dVar.i(2);
            Parcel parcel = ((e) dVar).f19466e;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f2093d;
        if (parcelable != null) {
            dVar.k(parcelable, 3);
        }
        int i11 = iconCompat.f2094e;
        if (i11 != 0) {
            dVar.j(i11, 4);
        }
        int i12 = iconCompat.f2095f;
        if (i12 != 0) {
            dVar.j(i12, 5);
        }
        ColorStateList colorStateList = iconCompat.f2096g;
        if (colorStateList != null) {
            dVar.k(colorStateList, 6);
        }
        String str = iconCompat.f2098i;
        if (str != null) {
            dVar.i(7);
            ((e) dVar).f19466e.writeString(str);
        }
        String str2 = iconCompat.f2099j;
        if (str2 != null) {
            dVar.i(8);
            ((e) dVar).f19466e.writeString(str2);
        }
    }
}
