package com.caverock.androidsvg;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f12344a;

    static {
        HashMap map = new HashMap(13);
        f12344a = map;
        Integer numValueOf = Integer.valueOf(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        map.put("normal", numValueOf);
        map.put(TtmlNode.BOLD, 700);
        androidx.fragment.app.a2.E(1, map, "bolder", -1, "lighter");
        androidx.fragment.app.a2.E(100, map, "100", 200, "200");
        map.put("300", 300);
        map.put("400", numValueOf);
        androidx.fragment.app.a2.E(500, map, "500", MediaError.DetailedErrorCode.TEXT_UNKNOWN, "600");
        map.put("700", 700);
        map.put("800", 800);
        map.put("900", 900);
    }
}
