package androidx.media3.session;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryResult<V> {
    public static final int RESULT_ERROR_BAD_VALUE = -3;
    public static final int RESULT_ERROR_INVALID_STATE = -2;
    public static final int RESULT_ERROR_IO = -5;
    public static final int RESULT_ERROR_NOT_SUPPORTED = -6;
    public static final int RESULT_ERROR_PERMISSION_DENIED = -4;
    public static final int RESULT_ERROR_SESSION_AUTHENTICATION_EXPIRED = -102;
    public static final int RESULT_ERROR_SESSION_CONCURRENT_STREAM_LIMIT = -104;
    public static final int RESULT_ERROR_SESSION_DISCONNECTED = -100;
    public static final int RESULT_ERROR_SESSION_NOT_AVAILABLE_IN_REGION = -106;
    public static final int RESULT_ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED = -105;
    public static final int RESULT_ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED = -103;
    public static final int RESULT_ERROR_SESSION_SETUP_REQUIRED = -108;
    public static final int RESULT_ERROR_SESSION_SKIP_LIMIT_REACHED = -107;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_INFO_SKIPPED = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final int VALUE_TYPE_ERROR = 4;
    private static final int VALUE_TYPE_ITEM = 2;
    private static final int VALUE_TYPE_ITEM_LIST = 3;
    private static final int VALUE_TYPE_VOID = 1;
    public final long completionTimeMs;
    public final MediaLibraryService.LibraryParams params;
    public final int resultCode;
    public final SessionError sessionError;
    public final V value;
    private final int valueType;
    private static final String FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(1);
    private static final String FIELD_PARAMS = Util.intToStringMaxRadix(2);
    private static final String FIELD_VALUE = Util.intToStringMaxRadix(3);
    private static final String FIELD_VALUE_TYPE = Util.intToStringMaxRadix(4);
    private static final String FIELD_SESSION_ERROR = Util.intToStringMaxRadix(5);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    private LibraryResult(int i10, long j10, MediaLibraryService.LibraryParams libraryParams, SessionError sessionError, V v2, int i11) {
        this.resultCode = i10;
        this.completionTimeMs = j10;
        this.params = libraryParams;
        this.sessionError = sessionError;
        this.value = v2;
        this.valueType = i11;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.session.LibraryResult<?> fromBundle(android.os.Bundle r10, java.lang.Integer r11) {
        /*
            java.lang.String r0 = androidx.media3.session.LibraryResult.FIELD_RESULT_CODE
            r1 = 0
            int r3 = r10.getInt(r0, r1)
            java.lang.String r0 = androidx.media3.session.LibraryResult.FIELD_COMPLETION_TIME_MS
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r10.getLong(r0, r4)
            java.lang.String r0 = androidx.media3.session.LibraryResult.FIELD_PARAMS
            android.os.Bundle r0 = r10.getBundle(r0)
            r2 = 0
            if (r0 != 0) goto L1c
            r6 = r2
            goto L21
        L1c:
            androidx.media3.session.MediaLibraryService$LibraryParams r0 = androidx.media3.session.MediaLibraryService.LibraryParams.fromBundle(r0)
            r6 = r0
        L21:
            java.lang.String r0 = androidx.media3.session.LibraryResult.FIELD_SESSION_ERROR
            android.os.Bundle r0 = r10.getBundle(r0)
            if (r0 == 0) goto L2f
            androidx.media3.session.SessionError r0 = androidx.media3.session.SessionError.fromBundle(r0)
        L2d:
            r7 = r0
            goto L3a
        L2f:
            if (r3 == 0) goto L39
            androidx.media3.session.SessionError r0 = new androidx.media3.session.SessionError
            java.lang.String r7 = "no error message provided"
            r0.<init>(r3, r7)
            goto L2d
        L39:
            r7 = r2
        L3a:
            java.lang.String r0 = androidx.media3.session.LibraryResult.FIELD_VALUE_TYPE
            int r9 = r10.getInt(r0)
            r0 = 1
            if (r9 == r0) goto L76
            r8 = 2
            if (r9 == r8) goto L78
            r8 = 3
            if (r9 == r8) goto L53
            r10 = 4
            if (r9 != r10) goto L4d
            goto L76
        L4d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L53:
            if (r11 == 0) goto L5b
            int r11 = r11.intValue()
            if (r11 != r8) goto L5c
        L5b:
            r1 = r0
        L5c:
            ac.b.s(r1)
            java.lang.String r11 = androidx.media3.session.LibraryResult.FIELD_VALUE
            android.os.IBinder r10 = r10.getBinder(r11)
            if (r10 != 0) goto L68
            goto L76
        L68:
            androidx.media3.session.f r11 = new androidx.media3.session.f
            r0 = 2
            r11.<init>(r0)
            com.google.common.collect.h1 r10 = androidx.media3.common.BundleListRetriever.getList(r10)
            com.google.common.collect.h1 r2 = androidx.media3.common.util.BundleCollectionUtil.fromBundleList(r11, r10)
        L76:
            r8 = r2
            goto L92
        L78:
            if (r11 == 0) goto L80
            int r11 = r11.intValue()
            if (r11 != r8) goto L81
        L80:
            r1 = r0
        L81:
            ac.b.s(r1)
            java.lang.String r11 = androidx.media3.session.LibraryResult.FIELD_VALUE
            android.os.Bundle r10 = r10.getBundle(r11)
            if (r10 != 0) goto L8d
            goto L76
        L8d:
            androidx.media3.common.MediaItem r2 = androidx.media3.common.MediaItem.fromBundle(r10)
            goto L76
        L92:
            androidx.media3.session.LibraryResult r2 = new androidx.media3.session.LibraryResult
            r2.<init>(r3, r4, r6, r7, r8, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.LibraryResult.fromBundle(android.os.Bundle, java.lang.Integer):androidx.media3.session.LibraryResult");
    }

    public static LibraryResult<MediaItem> fromItemBundle(Bundle bundle) {
        return fromBundle(bundle, 2);
    }

    public static LibraryResult<com.google.common.collect.h1> fromItemListBundle(Bundle bundle) {
        return fromBundle(bundle, 3);
    }

    public static LibraryResult<?> fromUnknownBundle(Bundle bundle) {
        return fromBundle(bundle, null);
    }

    public static LibraryResult<Void> fromVoidBundle(Bundle bundle) {
        return fromUnknownBundle(bundle);
    }

    public static <V> LibraryResult<V> ofError(int i10) {
        return ofError(new SessionError(i10, "no error message provided", Bundle.EMPTY));
    }

    public static LibraryResult<MediaItem> ofItem(MediaItem mediaItem, MediaLibraryService.LibraryParams libraryParams) {
        verifyMediaItem(mediaItem);
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, mediaItem, 2);
    }

    public static LibraryResult<com.google.common.collect.h1> ofItemList(List<MediaItem> list, MediaLibraryService.LibraryParams libraryParams) {
        Iterator<MediaItem> it = list.iterator();
        while (it.hasNext()) {
            verifyMediaItem(it.next());
        }
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, com.google.common.collect.h1.n(list), 3);
    }

    public static LibraryResult<Void> ofVoid() {
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), null, null, null, 1);
    }

    private static void verifyMediaItem(MediaItem mediaItem) {
        ac.b.k(!TextUtils.isEmpty(mediaItem.mediaId), "mediaId must not be empty");
        ac.b.k(mediaItem.mediaMetadata.isBrowsable != null, "mediaMetadata must specify isBrowsable");
        ac.b.k(mediaItem.mediaMetadata.isPlayable != null, "mediaMetadata must specify isPlayable");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r2 != 4) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle toBundle() {
        /*
            r6 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_RESULT_CODE
            int r2 = r6.resultCode
            r0.putInt(r1, r2)
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_COMPLETION_TIME_MS
            long r2 = r6.completionTimeMs
            r0.putLong(r1, r2)
            androidx.media3.session.MediaLibraryService$LibraryParams r1 = r6.params
            if (r1 == 0) goto L20
            java.lang.String r2 = androidx.media3.session.LibraryResult.FIELD_PARAMS
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L20:
            androidx.media3.session.SessionError r1 = r6.sessionError
            if (r1 == 0) goto L2d
            java.lang.String r2 = androidx.media3.session.LibraryResult.FIELD_SESSION_ERROR
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L2d:
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_VALUE_TYPE
            int r2 = r6.valueType
            r0.putInt(r1, r2)
            V r1 = r6.value
            if (r1 != 0) goto L39
            goto L47
        L39:
            int r2 = r6.valueType
            r3 = 1
            if (r2 == r3) goto L6d
            r3 = 2
            if (r2 == r3) goto L61
            r1 = 3
            if (r2 == r1) goto L48
            r1 = 4
            if (r2 == r1) goto L6d
        L47:
            return r0
        L48:
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_VALUE
            androidx.media3.common.BundleListRetriever r2 = new androidx.media3.common.BundleListRetriever
            V r3 = r6.value
            com.google.common.collect.h1 r3 = (com.google.common.collect.h1) r3
            androidx.media3.session.f r4 = new androidx.media3.session.f
            r5 = 1
            r4.<init>(r5)
            com.google.common.collect.h1 r3 = androidx.media3.common.util.BundleCollectionUtil.toBundleList(r3, r4)
            r2.<init>(r3)
            r0.putBinder(r1, r2)
            return r0
        L61:
            java.lang.String r2 = androidx.media3.session.LibraryResult.FIELD_VALUE
            androidx.media3.common.MediaItem r1 = (androidx.media3.common.MediaItem) r1
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
            return r0
        L6d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.LibraryResult.toBundle():android.os.Bundle");
    }

    public static <V> LibraryResult<V> ofError(int i10, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(i10, SystemClock.elapsedRealtime(), libraryParams, new SessionError(i10, "no error message provided", Bundle.EMPTY), null, 4);
    }

    public static LibraryResult<Void> ofVoid(MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, null, 1);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError) {
        return new LibraryResult<>(sessionError.code, SystemClock.elapsedRealtime(), null, sessionError, null, 4);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(sessionError.code, SystemClock.elapsedRealtime(), libraryParams, sessionError, null, 4);
    }
}
