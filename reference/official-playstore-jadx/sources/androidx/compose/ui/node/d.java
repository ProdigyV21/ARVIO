package androidx.compose.ui.node;

import androidx.compose.ui.text.android.LayoutIntrinsicsKt;
import androidx.media3.datasource.cache.CacheSpan;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import io.sentry.k1;
import java.io.File;
import java.util.Comparator;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1928i;

    public /* synthetic */ d(int i10) {
        this.f1928i = i10;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f1928i) {
            case 0:
                return LayoutNode.ZComparator$lambda$39((LayoutNode) obj, (LayoutNode) obj2);
            case 1:
                return LayoutIntrinsicsKt.minIntrinsicWidth$lambda$0((x) obj, (x) obj2);
            case 2:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i10 = 0; i10 < bArr.length; i10++) {
                    byte b10 = bArr[i10];
                    byte b11 = bArr2[i10];
                    if (b10 != b11) {
                        return b10 - b11;
                    }
                }
                return 0;
            case 3:
                return LeastRecentlyUsedCacheEvictor.compare((CacheSpan) obj, (CacheSpan) obj2);
            case 4:
                return HlsMediaPlaylist.Interstitial.lambda$new$0((HlsMediaPlaylist.ClientDefinedAttribute) obj, (HlsMediaPlaylist.ClientDefinedAttribute) obj2);
            case 5:
                return SlowMotionData.Segment.lambda$static$0((SlowMotionData.Segment) obj, (SlowMotionData.Segment) obj2);
            case 6:
                return CrashlyticsReportPersistence.lambda$static$0((File) obj, (File) obj2);
            case 7:
                return CrashlyticsReportPersistence.oldestEventFileFirst((File) obj, (File) obj2);
            case 8:
                k1 k1Var = (k1) obj;
                k1 k1Var2 = (k1) obj2;
                if (k1Var == k1Var2) {
                    return 0;
                }
                int iCompareTo = k1Var.r().compareTo(k1Var2.r());
                return iCompareTo != 0 ? iCompareTo : k1Var.o().f17436l.toString().compareTo(k1Var2.o().f17436l.toString());
            case 9:
                return Float.compare((((io.sentry.android.core.anr.a) obj).f16420b + 1.0f) * r5.f16424f * r5.f16419a, (((io.sentry.android.core.anr.a) obj2).f16420b + 1.0f) * r6.f16424f * r6.f16419a);
            default:
                return Long.compare(((File) obj).lastModified(), ((File) obj2).lastModified());
        }
    }
}
