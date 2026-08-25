package androidx.media3.exoplayer.offline;

import android.os.Handler;
import android.os.Message;
import androidx.media3.exoplayer.offline.DownloadHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3720i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3721l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f3720i = i10;
        this.f3721l = obj;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.f3720i) {
            case 0:
                return ((DownloadHelper.MediaPreparer) this.f3721l).handleDownloadHelperCallbackMessage(message);
            default:
                return ((DownloadManager) this.f3721l).handleMainMessage(message);
        }
    }
}
