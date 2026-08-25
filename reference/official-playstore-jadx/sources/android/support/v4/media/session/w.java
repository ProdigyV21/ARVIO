package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {
    public static void a(PlaybackState.Builder builder, PlaybackState.CustomAction customAction) {
        builder.addCustomAction(customAction);
    }

    public static PlaybackState.CustomAction b(PlaybackState.CustomAction.Builder builder) {
        return builder.build();
    }

    public static PlaybackState c(PlaybackState.Builder builder) {
        return builder.build();
    }

    public static PlaybackState.Builder d() {
        return new PlaybackState.Builder();
    }

    public static PlaybackState.CustomAction.Builder e(String str, CharSequence charSequence, int i10) {
        return new PlaybackState.CustomAction.Builder(str, charSequence, i10);
    }

    public static String f(PlaybackState.CustomAction customAction) {
        return customAction.getAction();
    }

    public static long g(PlaybackState playbackState) {
        return playbackState.getActions();
    }

    public static long h(PlaybackState playbackState) {
        return playbackState.getActiveQueueItemId();
    }

    public static long i(PlaybackState playbackState) {
        return playbackState.getBufferedPosition();
    }

    public static List<PlaybackState.CustomAction> j(PlaybackState playbackState) {
        return playbackState.getCustomActions();
    }

    public static CharSequence k(PlaybackState playbackState) {
        return playbackState.getErrorMessage();
    }

    public static Bundle l(PlaybackState.CustomAction customAction) {
        return customAction.getExtras();
    }

    public static int m(PlaybackState.CustomAction customAction) {
        return customAction.getIcon();
    }

    public static long n(PlaybackState playbackState) {
        return playbackState.getLastPositionUpdateTime();
    }

    public static CharSequence o(PlaybackState.CustomAction customAction) {
        return customAction.getName();
    }

    public static float p(PlaybackState playbackState) {
        return playbackState.getPlaybackSpeed();
    }

    public static long q(PlaybackState playbackState) {
        return playbackState.getPosition();
    }

    public static int r(PlaybackState playbackState) {
        return playbackState.getState();
    }

    public static void s(PlaybackState.Builder builder, long j10) {
        builder.setActions(j10);
    }

    public static void t(PlaybackState.Builder builder, long j10) {
        builder.setActiveQueueItemId(j10);
    }

    public static void u(PlaybackState.Builder builder, long j10) {
        builder.setBufferedPosition(j10);
    }

    public static void v(PlaybackState.Builder builder, CharSequence charSequence) {
        builder.setErrorMessage(charSequence);
    }

    public static void w(PlaybackState.CustomAction.Builder builder, Bundle bundle) {
        builder.setExtras(bundle);
    }

    public static void x(PlaybackState.Builder builder, int i10, long j10, float f10, long j11) {
        builder.setState(i10, j10, f10, j11);
    }
}
