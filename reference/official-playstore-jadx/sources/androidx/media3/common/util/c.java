package androidx.media3.common.util;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3411i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3412l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f3411i = i10;
        this.f3412l = obj;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.f3411i) {
            case 0:
                return ((ListenerSet) this.f3412l).handleMessage(message);
            default:
                return ((StuckPlayerDetector) this.f3412l).handleMessage(message);
        }
    }
}
