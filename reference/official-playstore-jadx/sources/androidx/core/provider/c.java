package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ContentProviderClient f2137b;

    public c(Context context, Uri uri, int i10) {
        this.f2136a = i10;
        switch (i10) {
            case 1:
                this.f2137b = context.getContentResolver().acquireUnstableContentProviderClient(uri);
                break;
            default:
                this.f2137b = context.getContentResolver().acquireUnstableContentProviderClient(uri);
                break;
        }
    }

    @Override // androidx.core.provider.b
    public final Cursor a(Uri uri, String[] strArr, String[] strArr2) {
        switch (this.f2136a) {
            case 0:
                ContentProviderClient contentProviderClient = this.f2137b;
                if (contentProviderClient != null) {
                    try {
                    } catch (RemoteException e5) {
                        Log.w("FontsProvider", "Unable to query the content provider", e5);
                        return null;
                    }
                    break;
                }
                break;
            default:
                ContentProviderClient contentProviderClient2 = this.f2137b;
                if (contentProviderClient2 != null) {
                    try {
                    } catch (RemoteException e6) {
                        Log.w("FontsProvider", "Unable to query the content provider", e6);
                        return null;
                    }
                    break;
                }
                break;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.core.provider.b
    public final void close() throws Exception {
        switch (this.f2136a) {
            case 0:
                ContentProviderClient contentProviderClient = this.f2137b;
                if (contentProviderClient != null) {
                    contentProviderClient.release();
                }
                break;
            default:
                ContentProviderClient contentProviderClient2 = this.f2137b;
                if (contentProviderClient2 != 0) {
                    if (contentProviderClient2 instanceof AutoCloseable) {
                        contentProviderClient2.close();
                    } else if (!(contentProviderClient2 instanceof ExecutorService)) {
                        contentProviderClient2.release();
                    } else {
                        androidx.compose.ui.text.platform.extensions.a.m((ExecutorService) contentProviderClient2);
                    }
                }
                break;
        }
    }
}
