package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class p1 {
    public static com.google.common.util.concurrent.d1 a(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        return xc.d.s0(LibraryResult.ofError(-6));
    }

    public static com.google.common.util.concurrent.d1 b(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
        return xc.d.s0(LibraryResult.ofError(-6));
    }

    public static com.google.common.util.concurrent.d1 c(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams) {
        return xc.d.s0(LibraryResult.ofError(-6));
    }

    public static com.google.common.util.concurrent.d1 d(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        return xc.d.s0(LibraryResult.ofError(-6));
    }

    public static com.google.common.util.concurrent.d1 e(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        return xc.d.s0(LibraryResult.ofError(-6));
    }

    public static com.google.common.util.concurrent.d1 f(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        return Util.transformFutureAsync(callback.onGetItem(mediaLibrarySession, controllerInfo, str), new l(mediaLibrarySession, controllerInfo, str, libraryParams));
    }

    public static com.google.common.util.concurrent.d1 g(MediaLibraryService.MediaLibrarySession.Callback callback, MediaLibraryService.MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
        return xc.d.s0(LibraryResult.ofVoid());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ com.google.common.util.concurrent.d1 h(MediaSession.ControllerInfo controllerInfo, MediaLibraryService.MediaLibrarySession mediaLibrarySession, String str, MediaLibraryService.LibraryParams libraryParams, LibraryResult libraryResult) {
        V v2;
        Boolean bool;
        if (libraryResult.resultCode == 0 && (v2 = libraryResult.value) != 0 && (bool = ((MediaItem) v2).mediaMetadata.isBrowsable) != null && bool.booleanValue()) {
            if (controllerInfo.getControllerVersion() != 0) {
                mediaLibrarySession.notifyChildrenChanged(controllerInfo, str, Integer.MAX_VALUE, libraryParams);
            }
            return xc.d.s0(LibraryResult.ofVoid());
        }
        int i10 = libraryResult.resultCode;
        if (i10 == 0) {
            i10 = -3;
        }
        return xc.d.s0(LibraryResult.ofError(i10));
    }
}
