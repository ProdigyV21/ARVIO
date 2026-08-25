package androidx.media3.session.legacy;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import y9.a;

/* JADX INFO: loaded from: classes3.dex */
public final class LegacyParcelableUtil {
    private LegacyParcelableUtil() {
    }

    public static <T extends Parcelable, U extends Parcelable> T convert(U u2, Parcelable.Creator<T> creator) {
        if (u2 == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            u2.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } finally {
            parcelObtain.recycle();
        }
    }

    public static <T extends Parcelable, U extends Parcelable> ArrayList<T> convertList(List<U> list, Parcelable.Creator<T> creator) {
        if (list == null) {
            return null;
        }
        a aVar = (ArrayList<T>) new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            aVar.add(convert(list.get(i10), creator));
        }
        return aVar;
    }
}
