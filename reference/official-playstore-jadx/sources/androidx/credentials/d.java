package androidx.credentials;

import android.app.slice.SliceItem;
import android.credentials.GetCredentialException;
import android.service.credentials.Action;
import android.service.credentials.BeginGetCredentialOption;
import android.view.SurfaceView;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Player;
import androidx.media3.common.audio.DefaultGainProvider;
import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements DebugViewProvider, ListenerSet.Event, DefaultGainProvider.FadeProvider {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2357i;

    public /* synthetic */ d(int i10) {
        this.f2357i = i10;
    }

    public static /* bridge */ /* synthetic */ SliceItem a(Object obj) {
        return (SliceItem) obj;
    }

    public static /* bridge */ /* synthetic */ GetCredentialException b(Object obj) {
        return (GetCredentialException) obj;
    }

    public static /* bridge */ /* synthetic */ Action c(Object obj) {
        return (Action) obj;
    }

    public static /* bridge */ /* synthetic */ BeginGetCredentialOption d(Object obj) {
        return (BeginGetCredentialOption) obj;
    }

    @Override // androidx.media3.common.DebugViewProvider
    public SurfaceView getDebugPreviewSurfaceView(int i10, int i11) {
        return androidx.media3.common.b.a(i10, i11);
    }

    @Override // androidx.media3.common.audio.DefaultGainProvider.FadeProvider
    public float getGainFactorAt(long j10, long j11) {
        switch (this.f2357i) {
            case 26:
                return DefaultGainProvider.lambda$static$0(j10, j11);
            case 27:
                return DefaultGainProvider.lambda$static$1(j10, j11);
            case 28:
                return DefaultGainProvider.lambda$static$2(j10, j11);
            default:
                return DefaultGainProvider.lambda$static$3(j10, j11);
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((Player.Listener) obj).onRenderedFirstFrame();
    }
}
