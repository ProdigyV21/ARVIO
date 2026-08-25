package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.e;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.d3;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.g3;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.collect.y;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CueGroup {
    private static final g3 CUES_PRIORITY_COMPARATOR = new y(new e(15), d3.f13967l);
    public static final CueGroup EMPTY_TIME_ZERO;
    private static final String FIELD_CUES;
    private static final String FIELD_PRESENTATION_TIME_US;
    public final h1 cues;
    public final long presentationTimeUs;

    static {
        f1 f1Var = h1.f14020l;
        EMPTY_TIME_ZERO = new CueGroup(o3.f14078o, 0L);
        FIELD_CUES = Util.intToStringMaxRadix(0);
        FIELD_PRESENTATION_TIME_US = Util.intToStringMaxRadix(1);
    }

    public CueGroup(List<Cue> list, long j10) {
        this.cues = h1.u(CUES_PRIORITY_COMPARATOR, list);
        this.presentationTimeUs = j10;
    }

    private static h1 filterOutBitmapCues(List<Cue> list) {
        e1 e1VarK = h1.k();
        for (int i10 = 0; i10 < list.size(); i10++) {
            if (list.get(i10).bitmap == null) {
                e1VarK.c(list.get(i10));
            }
        }
        return e1VarK.f();
    }

    public static CueGroup fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_CUES);
        return new CueGroup(parcelableArrayList == null ? o3.f14078o : BundleCollectionUtil.fromBundleList(new e(16), parcelableArrayList), bundle.getLong(FIELD_PRESENTATION_TIME_US));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$static$0(Cue cue) {
        return Integer.valueOf(cue.zIndex);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FIELD_CUES, BundleCollectionUtil.toBundleArrayList(filterOutBitmapCues(this.cues), new e(17)));
        bundle.putLong(FIELD_PRESENTATION_TIME_US, this.presentationTimeUs);
        return bundle;
    }
}
