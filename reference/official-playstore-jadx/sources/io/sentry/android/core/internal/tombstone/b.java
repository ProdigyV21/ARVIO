package io.sentry.android.core.internal.tombstone;

import io.sentry.protocol.DebugImage;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f16574b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f16575c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Serializable f16576d;

    public DebugImage a() {
        long j10 = this.f16573a;
        String str = (String) this.f16576d;
        if (str.isEmpty()) {
            return null;
        }
        DebugImage debugImage = new DebugImage();
        debugImage.setCodeId(str);
        debugImage.setCodeFile((String) this.f16575c);
        String strF = t7.a.f(str);
        if (strF != null) {
            str = strF;
        }
        debugImage.setDebugId(str);
        debugImage.setImageAddr(String.format("0x%x", Long.valueOf(j10)));
        debugImage.setImageSize(this.f16574b - j10);
        debugImage.setType("elf");
        return debugImage;
    }
}
