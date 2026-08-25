package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.e;
import androidx.media3.common.util.BundleCollectionUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class CueDecoder {
    static final String BUNDLE_FIELD_CUES = "c";
    static final String BUNDLE_FIELD_DURATION_US = "d";

    public CuesWithTiming decode(long j10, byte[] bArr, int i10, int i11) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, i10, i11);
        parcelObtain.setDataPosition(0);
        Bundle bundle = parcelObtain.readBundle(Bundle.class.getClassLoader());
        parcelObtain.recycle();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(BUNDLE_FIELD_CUES);
        parcelableArrayList.getClass();
        return new CuesWithTiming(BundleCollectionUtil.fromBundleList(new e(16), parcelableArrayList), j10, bundle.getLong("d"));
    }
}
