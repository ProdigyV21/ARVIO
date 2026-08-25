package io.sentry.android.replay.video;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import kotlin.jvm.internal.r;
import kotlin.text.o;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f17067i = new d(0);

    @Override // r7.a
    public final Object invoke() {
        boolean z = false;
        MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
        int length = codecInfos.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                break;
            }
            if (o.T(codecInfos[i10].getName(), "c2.exynos", false)) {
                z = true;
                break;
            }
            i10++;
        }
        return Boolean.valueOf(z);
    }
}
