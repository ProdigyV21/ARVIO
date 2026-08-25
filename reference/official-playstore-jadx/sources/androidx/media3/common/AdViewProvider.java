package androidx.media3.common;

import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AdViewProvider {
    List<AdOverlayInfo> getAdOverlayInfos();

    ViewGroup getAdViewGroup();
}
