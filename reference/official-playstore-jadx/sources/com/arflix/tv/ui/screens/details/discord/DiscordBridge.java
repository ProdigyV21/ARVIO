package com.arflix.tv.ui.screens.details.discord;

import android.util.Log;
import com.arflix.tv.BuildConfig;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0010\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001)B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0083 ¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0083 ¢\u0006\u0004\b\f\u0010\rJH\u0010\u0015\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0083 ¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\bH\u0083 ¢\u0006\u0004\b\u0017\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\bH\u0083 ¢\u0006\u0004\b\u0018\u0010\u0003J\u0010\u0010\u0019\u001a\u00020\bH\u0083 ¢\u0006\u0004\b\u0019\u0010\u0003J\u001d\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u001eJE\u0010\u001f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u001f\u0010\u0016J\r\u0010 \u001a\u00020\b¢\u0006\u0004\b \u0010\u0003J\r\u0010!\u001a\u00020\b¢\u0006\u0004\b!\u0010\u0003J\r\u0010\"\u001a\u00020\b¢\u0006\u0004\b\"\u0010\u0003R\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordBridge;", "", "<init>", "()V", "", "clientId", "Lcom/arflix/tv/ui/screens/details/discord/DiscordBridge$Callback;", "callback", "Lx6/t0;", "nativeInit", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/details/discord/DiscordBridge$Callback;)V", "accessToken", "nativeConnect", "(Ljava/lang/String;)V", "details", "state", "", "startTime", "endTime", "largeImage", "largeText", "nativeUpdateActivity", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "nativeClearActivity", "nativeDisconnect", "nativeTick", "", "init", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/details/discord/DiscordBridge$Callback;)Z", "connect", "(Ljava/lang/String;)Z", "updateActivity", "clearActivity", "disconnect", "tick", "TAG", "Ljava/lang/String;", "nativeLoaded", "Z", "isAvailable", "()Z", "Callback", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DiscordBridge {
    public static final int $stable;
    public static final DiscordBridge INSTANCE = new DiscordBridge();
    private static final String TAG = "DiscordBridge";
    private static boolean nativeLoaded;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/ui/screens/details/discord/DiscordBridge$Callback;", "", "", "status", "error", "errorDetail", "Lx6/t0;", "onStatusChanged", "(III)V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Callback {
        void onStatusChanged(int status, int error, int errorDetail);
    }

    static {
        boolean z;
        if (BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE.booleanValue()) {
            try {
                System.loadLibrary("arvio_native");
                Log.i(TAG, "Successfully loaded arvio_native library.");
                z = true;
            } catch (UnsatisfiedLinkError e5) {
                Log.e(TAG, "Failed to load arvio_native library: " + e5.getMessage());
                z = false;
            }
            nativeLoaded = z;
        }
        $stable = 8;
    }

    private DiscordBridge() {
    }

    @n
    private static final native void nativeClearActivity();

    @n
    private static final native void nativeConnect(String accessToken);

    @n
    private static final native void nativeDisconnect();

    @n
    private static final native void nativeInit(String clientId, Callback callback);

    @n
    private static final native void nativeTick();

    @n
    private static final native void nativeUpdateActivity(String details, String state, long startTime, long endTime, String largeImage, String largeText);

    public final void clearActivity() {
        if (isAvailable()) {
            try {
                nativeClearActivity();
            } catch (UnsatisfiedLinkError e5) {
                Log.e(TAG, "nativeClearActivity not linked: " + e5.getMessage());
            }
        }
    }

    public final boolean connect(String accessToken) {
        if (!isAvailable()) {
            return false;
        }
        try {
            nativeConnect(accessToken);
            return true;
        } catch (UnsatisfiedLinkError e5) {
            Log.e(TAG, "nativeConnect not linked: " + e5.getMessage());
            return false;
        }
    }

    public final void disconnect() {
        if (isAvailable()) {
            try {
                nativeDisconnect();
            } catch (UnsatisfiedLinkError e5) {
                Log.e(TAG, "nativeDisconnect not linked: " + e5.getMessage());
            }
        }
    }

    public final boolean init(String clientId, Callback callback) {
        if (!isAvailable()) {
            return false;
        }
        try {
            nativeInit(clientId, callback);
            return true;
        } catch (UnsatisfiedLinkError e5) {
            Log.e(TAG, "nativeInit not linked: " + e5.getMessage());
            return false;
        }
    }

    public final boolean isAvailable() {
        return BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE.booleanValue() && nativeLoaded;
    }

    public final void tick() {
        if (isAvailable()) {
            try {
                nativeTick();
            } catch (UnsatisfiedLinkError unused) {
            }
        }
    }

    public final void updateActivity(String details, String state, long startTime, long endTime, String largeImage, String largeText) {
        if (isAvailable()) {
            try {
                nativeUpdateActivity(details, state, startTime, endTime, largeImage, largeText);
            } catch (UnsatisfiedLinkError e5) {
                Log.e(TAG, "nativeUpdateActivity not linked: " + e5.getMessage());
            }
        }
    }
}
