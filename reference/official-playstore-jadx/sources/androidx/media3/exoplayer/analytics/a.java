package androidx.media3.exoplayer.analytics;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a {
    public static void C(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void Y(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void i0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void v(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void x(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void y(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void z(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime) {
    }

    public static void A(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void A0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, float f10) {
    }

    public static void B(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    public static void D(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void F(AnalyticsListener analyticsListener, Player player, AnalyticsListener.Events events) {
    }

    public static void G(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z) {
    }

    public static void H(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z) {
    }

    public static void N(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z) {
    }

    public static void O(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, long j10) {
    }

    public static void Q(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    public static void R(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Metadata metadata) {
    }

    public static void T(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
    }

    public static void U(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void V(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void W(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
    }

    public static void X(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
    }

    public static void a(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
    }

    public static void a0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    public static void b(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    public static void b0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void e(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str) {
    }

    public static void f(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    public static void f0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void g(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    public static void g0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, long j10) {
    }

    public static void h0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, long j10) {
    }

    public static void i(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, long j10) {
    }

    public static void j(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void j0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z) {
    }

    public static void k(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    public static void k0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z) {
    }

    public static void l(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    public static void m(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    public static void m0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10) {
    }

    public static void n0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
    }

    public static void o(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Player.Commands commands) {
    }

    public static void o0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Tracks tracks) {
    }

    public static void p0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    public static void q(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
    }

    public static void q0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    public static void r(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, List list) {
    }

    public static void s(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
    }

    public static void t0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str) {
    }

    public static void u(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    public static void u0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    public static void v0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    public static void w(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, KeyRequestInfo keyRequestInfo) {
    }

    public static void z0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
    }

    public static void E(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, long j10) {
    }

    public static void I(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void J(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void L(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void P(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i10) {
    }

    public static void S(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z, int i10) {
    }

    public static void Z(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, boolean z, int i10) {
    }

    public static void c(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str, long j10) {
    }

    public static void d0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Object obj, long j10) {
    }

    public static void h(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    public static void l0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, int i11) {
    }

    public static void r0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str, long j10) {
    }

    public static void t(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, boolean z) {
    }

    public static void w0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, long j10, int i10) {
    }

    public static void x0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    public static void M(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
    }

    public static void c0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
    }

    public static void d(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str, long j10, long j11) {
    }

    public static void e0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, int i11, boolean z) {
    }

    public static void n(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, long j10, long j11) {
    }

    public static void p(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, long j10, long j11) {
    }

    public static void s0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, String str, long j10, long j11) {
    }

    public static void K(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    public static void y0(AnalyticsListener analyticsListener, AnalyticsListener.EventTime eventTime, int i10, int i11, int i12, float f10) {
    }
}
