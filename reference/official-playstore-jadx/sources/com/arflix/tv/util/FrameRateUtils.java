package com.arflix.tv.util;

import android.app.Activity;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.c0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002@AB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J-\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0014\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ-\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0019¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010\u001f\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010$\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\b¢\u0006\u0004\b$\u0010%J \u0010&\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bH\u0086@¢\u0006\u0004\b&\u0010'J\u0015\u0010)\u001a\u00020(2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020(¢\u0006\u0004\b+\u0010\u0003R\u0014\u0010-\u001a\u00020,8\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00100R\u0014\u00103\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b3\u00100R\u0014\u00104\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u00100R\u0014\u00105\u001a\u00020,8\u0002X\u0082T¢\u0006\u0006\n\u0004\b5\u0010.R\u0014\u00107\u001a\u0002068\u0002X\u0082T¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u00109\u001a\u00020,8\u0002X\u0082T¢\u0006\u0006\n\u0004\b9\u0010.R\u0018\u0010:\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R \u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020=0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?¨\u0006B"}, d2 = {"Lcom/arflix/tv/util/FrameRateUtils;", "", "<init>", "()V", "", "sourceUrl", "detectionCacheKey", "(Ljava/lang/String;)Ljava/lang/String;", "", "refreshRate", "target", "", "matchesTarget", "(FF)Z", "", "Landroid/view/Display$Mode;", "modes", "pickBestMode", "(Ljava/util/List;F)Landroid/view/Display$Mode;", "activeMode", "fps", "chooseBestMode", "(Landroid/view/Display$Mode;Ljava/util/List;F)Landroid/view/Display$Mode;", "snapToStandardRate", "(F)F", "", "headers", "Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "detectFrameRate", "(Ljava/lang/String;Ljava/util/Map;)Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "detectFrameRateCached", "getCachedFrameRate", "(Ljava/lang/String;)Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "Landroid/app/Activity;", "activity", "frameRate", "applyFrameRateMode", "(Landroid/app/Activity;F)Z", "matchFrameRateAndWait", "(Landroid/app/Activity;FLd7/d;)Ljava/lang/Object;", "Lx6/t0;", "restoreOriginalMode", "(Landroid/app/Activity;)V", "clearOriginalMode", "", "SWITCH_TIMEOUT_MS", "J", "REFRESH_MATCH_TOLERANCE_HZ", "F", "NTSC_FILM_FPS", "CINEMA_24_FPS", "MIN_VALID_FPS", "MAX_VALID_FPS", "POLL_INTERVAL_MS", "", "STABLE_POLLS_REQUIRED", "I", "DETECTION_CACHE_TTL_MS", "originalModeId", "Ljava/lang/Integer;", "j$/util/concurrent/ConcurrentHashMap", "Lcom/arflix/tv/util/FrameRateUtils$CachedDetection;", "detectionCache", "Lj$/util/concurrent/ConcurrentHashMap;", "FrameRateDetection", "CachedDetection", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FrameRateUtils {
    private static final float CINEMA_24_FPS = 24.0f;
    private static final long DETECTION_CACHE_TTL_MS = 900000;
    private static final float MAX_VALID_FPS = 120.0f;
    private static final float MIN_VALID_FPS = 10.0f;
    private static final float NTSC_FILM_FPS = 23.976025f;
    private static final long POLL_INTERVAL_MS = 60;
    private static final float REFRESH_MATCH_TOLERANCE_HZ = 0.08f;
    private static final int STABLE_POLLS_REQUIRED = 2;
    private static final long SWITCH_TIMEOUT_MS = 4000;
    private static Integer originalModeId;
    public static final FrameRateUtils INSTANCE = new FrameRateUtils();
    private static final ConcurrentHashMap<String, CachedDetection> detectionCache = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/util/FrameRateUtils$CachedDetection;", "", "detection", "Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "createdAtMs", "", "<init>", "(Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;J)V", "getDetection", "()Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "getCreatedAtMs", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CachedDetection {
        private final long createdAtMs;
        private final FrameRateDetection detection;

        public CachedDetection(FrameRateDetection frameRateDetection, long j10) {
            this.detection = frameRateDetection;
            this.createdAtMs = j10;
        }

        public static /* synthetic */ CachedDetection copy$default(CachedDetection cachedDetection, FrameRateDetection frameRateDetection, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                frameRateDetection = cachedDetection.detection;
            }
            if ((i10 & 2) != 0) {
                j10 = cachedDetection.createdAtMs;
            }
            return cachedDetection.copy(frameRateDetection, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FrameRateDetection getDetection() {
            return this.detection;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getCreatedAtMs() {
            return this.createdAtMs;
        }

        public final CachedDetection copy(FrameRateDetection detection, long createdAtMs) {
            return new CachedDetection(detection, createdAtMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedDetection)) {
                return false;
            }
            CachedDetection cachedDetection = (CachedDetection) other;
            return p.a(this.detection, cachedDetection.detection) && this.createdAtMs == cachedDetection.createdAtMs;
        }

        public final long getCreatedAtMs() {
            return this.createdAtMs;
        }

        public final FrameRateDetection getDetection() {
            return this.detection;
        }

        public int hashCode() {
            int iHashCode = this.detection.hashCode() * 31;
            long j10 = this.createdAtMs;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "CachedDetection(detection=" + this.detection + ", createdAtMs=" + this.createdAtMs + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/FrameRateUtils$FrameRateDetection;", "", "raw", "", "snapped", "<init>", "(FF)V", "getRaw", "()F", "getSnapped", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class FrameRateDetection {
        public static final int $stable = 0;
        private final float raw;
        private final float snapped;

        public FrameRateDetection(float f10, float f11) {
            this.raw = f10;
            this.snapped = f11;
        }

        public static /* synthetic */ FrameRateDetection copy$default(FrameRateDetection frameRateDetection, float f10, float f11, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                f10 = frameRateDetection.raw;
            }
            if ((i10 & 2) != 0) {
                f11 = frameRateDetection.snapped;
            }
            return frameRateDetection.copy(f10, f11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getRaw() {
            return this.raw;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getSnapped() {
            return this.snapped;
        }

        public final FrameRateDetection copy(float raw, float snapped) {
            return new FrameRateDetection(raw, snapped);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FrameRateDetection)) {
                return false;
            }
            FrameRateDetection frameRateDetection = (FrameRateDetection) other;
            return Float.compare(this.raw, frameRateDetection.raw) == 0 && Float.compare(this.snapped, frameRateDetection.snapped) == 0;
        }

        public final float getRaw() {
            return this.raw;
        }

        public final float getSnapped() {
            return this.snapped;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.snapped) + (Float.floatToIntBits(this.raw) * 31);
        }

        public String toString() {
            return "FrameRateDetection(raw=" + this.raw + ", snapped=" + this.snapped + ")";
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.FrameRateUtils$matchFrameRateAndWait$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.util.FrameRateUtils", f = "FrameRateUtils.kt", l = {239, 268, 277}, m = "matchFrameRateAndWait", v = 2)
    public static final class AnonymousClass1 extends c {
        float F$0;
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FrameRateUtils.this.matchFrameRateAndWait(null, 0.0f, this);
        }
    }

    private FrameRateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Display.Mode chooseBestMode(Display.Mode activeMode, List<Display.Mode> modes, float fps) {
        Object obj;
        Display.Mode modePickBestMode = pickBestMode(modes, fps);
        Display.Mode modePickBestMode2 = pickBestMode(modes, 2.0f * fps);
        Display.Mode modePickBestMode3 = pickBestMode(modes, 2.5f * fps);
        Iterator<T> it = modes.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                Display.Mode mode = (Display.Mode) next;
                float refreshRate = mode.getRefreshRate() / fps;
                int iM = t7.a.M(refreshRate);
                float refreshRate2 = iM < 1 ? (fps - mode.getRefreshRate()) / fps : Math.abs((refreshRate / iM) - 1.0f);
                do {
                    Object next2 = it.next();
                    Display.Mode mode2 = (Display.Mode) next2;
                    float refreshRate3 = mode2.getRefreshRate() / fps;
                    int iM2 = t7.a.M(refreshRate3);
                    float refreshRate4 = iM2 < 1 ? (fps - mode2.getRefreshRate()) / fps : Math.abs((refreshRate3 / iM2) - 1.0f);
                    if (Float.compare(refreshRate2, refreshRate4) > 0) {
                        next = next2;
                        refreshRate2 = refreshRate4;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        Display.Mode mode3 = (Display.Mode) obj;
        return modePickBestMode == null ? modePickBestMode2 == null ? modePickBestMode3 == null ? mode3 == null ? activeMode : mode3 : modePickBestMode3 : modePickBestMode2 : modePickBestMode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FrameRateDetection detectFrameRate$default(FrameRateUtils frameRateUtils, String str, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = a0.f19683i;
        }
        return frameRateUtils.detectFrameRate(str, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FrameRateDetection detectFrameRateCached$default(FrameRateUtils frameRateUtils, String str, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = a0.f19683i;
        }
        return frameRateUtils.detectFrameRateCached(str, map);
    }

    private final String detectionCacheKey(String sourceUrl) {
        Object c0Var;
        try {
            c0Var = Uri.parse(sourceUrl);
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
        Uri uri = (Uri) c0Var;
        if (uri == null) {
            return o.G0('?', sourceUrl, sourceUrl);
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            scheme = "";
        }
        String host = uri.getHost();
        if (host == null) {
            host = "";
        }
        String path = uri.getPath();
        String str = path != null ? path : "";
        if (o.h0(scheme) || o.h0(host)) {
            return o.G0('?', sourceUrl, sourceUrl);
        }
        return scheme + "://" + host + str;
    }

    private final boolean matchesTarget(float refreshRate, float target) {
        return Math.abs(refreshRate - target) <= Math.max(0.08f, 0.003f * target);
    }

    private final Display.Mode pickBestMode(List<Display.Mode> modes, float target) {
        Object next;
        if (target <= 0.0f) {
            return null;
        }
        Iterator<T> it = modes.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                float fAbs = Math.abs(((Display.Mode) next).getRefreshRate() - target);
                do {
                    Object next2 = it.next();
                    float fAbs2 = Math.abs(((Display.Mode) next2).getRefreshRate() - target);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        next = next2;
                        fAbs = fAbs2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Display.Mode mode = (Display.Mode) next;
        if (mode != null && matchesTarget(mode.getRefreshRate(), target)) {
            return mode;
        }
        return null;
    }

    public final boolean applyFrameRateMode(Activity activity, float frameRate) {
        Display display;
        if (frameRate <= 0.0f) {
            return false;
        }
        try {
            Window window = activity.getWindow();
            if (window == null || (display = window.getDecorView().getDisplay()) == null) {
                return false;
            }
            Display.Mode mode = display.getMode();
            Display.Mode[] supportedModes = display.getSupportedModes();
            ArrayList arrayList = new ArrayList();
            for (Display.Mode mode2 : supportedModes) {
                if (mode2.getPhysicalWidth() == mode.getPhysicalWidth() && mode2.getPhysicalHeight() == mode.getPhysicalHeight()) {
                    arrayList.add(mode2);
                }
            }
            if (arrayList.size() <= 1) {
                return false;
            }
            Display.Mode modeChooseBestMode = chooseBestMode(mode, arrayList, frameRate);
            if (modeChooseBestMode.getModeId() == mode.getModeId()) {
                return false;
            }
            if (originalModeId == null) {
                originalModeId = Integer.valueOf(mode.getModeId());
            }
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.preferredDisplayModeId = modeChooseBestMode.getModeId();
            window.setAttributes(attributes);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final void clearOriginalMode() {
        originalModeId = null;
    }

    public final FrameRateDetection detectFrameRate(String sourceUrl, Map<String, String> headers) {
        int i10;
        MediaFormat trackFormat;
        Object c0Var;
        Float f10;
        long jLongValue;
        String strG0 = o.G0('?', sourceUrl, sourceUrl);
        Locale locale = Locale.ROOT;
        String lowerCase = strG0.toLowerCase(locale);
        if (u.K(lowerCase, ".m3u8", false) || o.T(lowerCase, "/hls", false) || u.K(lowerCase, ".mpd", false)) {
            return null;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            try {
                String scheme = Uri.parse(sourceUrl).getScheme();
                String lowerCase2 = scheme != null ? scheme.toLowerCase(locale) : null;
                if (!p.a(lowerCase2, "http") && !p.a(lowerCase2, "https")) {
                    try {
                        mediaExtractor.release();
                    } catch (Throwable unused) {
                    }
                    return null;
                }
                mediaExtractor.setDataSource(sourceUrl, headers);
                int trackCount = mediaExtractor.getTrackCount();
                int i11 = 0;
                while (true) {
                    i10 = 1;
                    if (i11 >= trackCount) {
                        trackFormat = null;
                        break;
                    }
                    trackFormat = mediaExtractor.getTrackFormat(i11);
                    String string = trackFormat.getString("mime");
                    if (string != null && u.P(string, "video/", false)) {
                        mediaExtractor.selectTrack(i11);
                        break;
                    }
                    i11++;
                }
                if (trackFormat == null) {
                    try {
                        mediaExtractor.release();
                    } catch (Throwable unused2) {
                    }
                    return null;
                }
                if (!trackFormat.containsKey("frame-rate")) {
                    trackFormat = null;
                }
                if (trackFormat != null) {
                    try {
                        c0Var = Float.valueOf(trackFormat.getFloat("frame-rate"));
                    } catch (Throwable th) {
                        c0Var = new c0(th);
                    }
                    if (c0Var instanceof c0) {
                        c0Var = null;
                    }
                    f10 = (Float) c0Var;
                } else {
                    f10 = null;
                }
                if (f10 != null) {
                    float fFloatValue = f10.floatValue();
                    if (MIN_VALID_FPS <= fFloatValue && fFloatValue <= MAX_VALID_FPS) {
                        FrameRateDetection frameRateDetection = new FrameRateDetection(f10.floatValue(), snapToStandardRate(f10.floatValue()));
                        try {
                            mediaExtractor.release();
                        } catch (Throwable unused3) {
                        }
                        return frameRateDetection;
                    }
                }
                ArrayList arrayList = new ArrayList(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
                do {
                    jLongValue = 0;
                    if (arrayList.size() >= 350) {
                        break;
                    }
                    long sampleTime = mediaExtractor.getSampleTime();
                    if (sampleTime < 0) {
                        break;
                    }
                    arrayList.add(Long.valueOf(sampleTime));
                } while (mediaExtractor.advance());
                if (arrayList.size() >= 34) {
                    int size = arrayList.size();
                    for (int i12 = 4; i12 < size; i12++) {
                        jLongValue += ((Number) arrayList.get(i12)).longValue() - ((Number) arrayList.get(i12 - 1)).longValue();
                    }
                    int size2 = arrayList.size() - 4;
                    if (size2 >= 1) {
                        i10 = size2;
                    }
                    float f11 = jLongValue / i10;
                    if (f11 > 0.0f) {
                        float f12 = 1000000.0f / f11;
                        if (MIN_VALID_FPS <= f12 && f12 <= MAX_VALID_FPS) {
                            FrameRateDetection frameRateDetection2 = new FrameRateDetection(f12, snapToStandardRate(f12));
                            try {
                                mediaExtractor.release();
                            } catch (Throwable unused4) {
                            }
                            return frameRateDetection2;
                        }
                    }
                }
                mediaExtractor.release();
                return null;
            } catch (Throwable unused5) {
                return null;
            }
        } catch (Exception unused6) {
            mediaExtractor.release();
            return null;
        } catch (Throwable th2) {
            try {
                mediaExtractor.release();
            } catch (Throwable unused7) {
            }
            throw th2;
        }
    }

    public final FrameRateDetection detectFrameRateCached(String sourceUrl, Map<String, String> headers) {
        String strDetectionCacheKey = detectionCacheKey(sourceUrl);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap<String, CachedDetection> concurrentHashMap = detectionCache;
        CachedDetection cachedDetection = concurrentHashMap.get(strDetectionCacheKey);
        if (cachedDetection != null) {
            if (jCurrentTimeMillis - cachedDetection.getCreatedAtMs() <= DETECTION_CACHE_TTL_MS) {
                return cachedDetection.getDetection();
            }
            concurrentHashMap.remove(strDetectionCacheKey);
        }
        FrameRateDetection frameRateDetectionDetectFrameRate = detectFrameRate(sourceUrl, headers);
        if (frameRateDetectionDetectFrameRate == null) {
            return null;
        }
        concurrentHashMap.put(strDetectionCacheKey, new CachedDetection(frameRateDetectionDetectFrameRate, jCurrentTimeMillis));
        return frameRateDetectionDetectFrameRate;
    }

    public final FrameRateDetection getCachedFrameRate(String sourceUrl) {
        String strDetectionCacheKey = detectionCacheKey(sourceUrl);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap<String, CachedDetection> concurrentHashMap = detectionCache;
        CachedDetection cachedDetection = concurrentHashMap.get(strDetectionCacheKey);
        if (cachedDetection != null) {
            if (jCurrentTimeMillis - cachedDetection.getCreatedAtMs() <= DETECTION_CACHE_TTL_MS) {
                return cachedDetection.getDetection();
            }
            concurrentHashMap.remove(strDetectionCacheKey);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0094, code lost:
    
        if (r3 == r11) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0112, code lost:
    
        if (ka.s0.a(com.arflix.tv.util.FrameRateUtils.POLL_INTERVAL_MS, r4) == r11) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0112 -> B:14:0x0047). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object matchFrameRateAndWait(android.app.Activity r22, float r23, d7.d<? super java.lang.Boolean> r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.FrameRateUtils.matchFrameRateAndWait(android.app.Activity, float, d7.d):java.lang.Object");
    }

    public final void restoreOriginalMode(Activity activity) {
        Integer num = originalModeId;
        if (num != null) {
            int iIntValue = num.intValue();
            try {
                Window window = activity.getWindow();
                if (window == null) {
                    return;
                }
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.preferredDisplayModeId = iIntValue;
                window.setAttributes(attributes);
                originalModeId = null;
            } catch (Exception unused) {
            }
        }
    }

    public final float snapToStandardRate(float fps) {
        if (fps <= 0.0f) {
            return fps;
        }
        if (23.9f <= fps && fps <= 23.988f) {
            return NTSC_FILM_FPS;
        }
        if (23.988f <= fps && fps <= 24.1f) {
            return 24.0f;
        }
        if (24.9f <= fps && fps <= 25.1f) {
            return 25.0f;
        }
        if (29.9f <= fps && fps <= 29.985f) {
            return 29.97003f;
        }
        if (29.985f <= fps && fps <= 30.1f) {
            return 30.0f;
        }
        if (49.9f <= fps && fps <= 50.1f) {
            return 50.0f;
        }
        if (59.9f <= fps && fps <= 59.97f) {
            return 59.94006f;
        }
        if (59.97f > fps || fps > 60.1f) {
            return fps;
        }
        return 60.0f;
    }
}
