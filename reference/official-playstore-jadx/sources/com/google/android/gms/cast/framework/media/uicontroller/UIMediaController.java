package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.a;
import androidx.fragment.app.b1;
import androidx.fragment.app.c0;
import androidx.fragment.app.h0;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.a2;
import com.google.android.gms.internal.cast.b2;
import com.google.android.gms.internal.cast.c2;
import com.google.android.gms.internal.cast.d2;
import com.google.android.gms.internal.cast.e2;
import com.google.android.gms.internal.cast.l1;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m1;
import com.google.android.gms.internal.cast.m5;
import com.google.android.gms.internal.cast.n1;
import com.google.android.gms.internal.cast.o1;
import com.google.android.gms.internal.cast.p1;
import com.google.android.gms.internal.cast.r1;
import com.google.android.gms.internal.cast.s1;
import com.google.android.gms.internal.cast.t1;
import com.google.android.gms.internal.cast.v1;
import com.google.android.gms.internal.cast.w1;
import com.google.android.gms.internal.cast.x1;
import com.google.android.gms.internal.cast.y1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes4.dex */
public class UIMediaController implements RemoteMediaClient.Listener, SessionManagerListener<CastSession> {
    private static final Logger zzb = new Logger("UIMediaController");
    private final Activity zzc;
    private final SessionManager zzd;
    private RemoteMediaClient.Listener zzg;
    private RemoteMediaClient zzh;
    private final Map zze = new HashMap();
    private final Set zzf = new HashSet();
    final zza zza = zza.zza();

    public UIMediaController(Activity activity) {
        this.zzc = activity;
        CastContext castContextZza = CastContext.zza(activity);
        l6.a(m5.UI_MEDIA_CONTROLLER);
        SessionManager sessionManager = castContextZza != null ? castContextZza.getSessionManager() : null;
        this.zzd = sessionManager;
        if (sessionManager != null) {
            sessionManager.addSessionManagerListener(this, CastSession.class);
            zzj(sessionManager.getCurrentCastSession());
        }
    }

    private final void zzg(int i10) {
        Iterator it = this.zzf.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else {
                ((e2) it.next()).b(true);
            }
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        zza zzaVar = this.zza;
        long jZzh = zzaVar.zzh() + ((long) i10);
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(jZzh);
        builder.setIsSeekToInfinite(remoteMediaClient.isLiveStream() && zzaVar.zzd(jZzh));
        remoteMediaClient.seek(builder.build());
    }

    private final void zzh() {
        Iterator it = this.zzf.iterator();
        while (it.hasNext()) {
            ((e2) it.next()).b(false);
        }
    }

    private final void zzi(int i10, boolean z) {
        if (z) {
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((e2) it.next()).a(this.zza.zzh() + ((long) i10));
            }
        }
    }

    private final void zzj(Session session) {
        if (isActive() || session == null || !session.isConnected()) {
            return;
        }
        CastSession castSession = (CastSession) session;
        RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
        this.zzh = remoteMediaClient;
        if (remoteMediaClient != null) {
            remoteMediaClient.addListener(this);
            zza zzaVar = this.zza;
            t.i(zzaVar);
            zzaVar.zza = castSession.getRemoteMediaClient();
            Iterator it = this.zze.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((List) it.next()).iterator();
                while (it2.hasNext()) {
                    ((UIController) it2.next()).onSessionConnected(castSession);
                }
            }
            zzm();
        }
    }

    private final void zzk() {
        if (isActive()) {
            this.zza.zza = null;
            Iterator it = this.zze.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((List) it.next()).iterator();
                while (it2.hasNext()) {
                    ((UIController) it2.next()).onSessionEnded();
                }
            }
            t.i(this.zzh);
            this.zzh.removeListener(this);
            this.zzh = null;
        }
    }

    private final void zzl(View view, UIController uIController) {
        SessionManager sessionManager = this.zzd;
        if (sessionManager == null) {
            return;
        }
        Map map = this.zze;
        List arrayList = (List) map.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(view, arrayList);
        }
        arrayList.add(uIController);
        if (isActive()) {
            CastSession currentCastSession = sessionManager.getCurrentCastSession();
            t.i(currentCastSession);
            uIController.onSessionConnected(currentCastSession);
            zzm();
        }
    }

    private final void zzm() {
        Iterator it = this.zze.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                ((UIController) it2.next()).onMediaStatusUpdated();
            }
        }
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i10, int i11) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new r1(imageView, this.zzc, new ImageHints(i10, 0, 0), i11, null, null));
    }

    @Deprecated
    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, int i10, int i11) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new o1(imageView, this.zzc, new ImageHints(i10, 0, 0), i11));
    }

    public void bindImageViewToMuteToggle(ImageView imageView) {
        t.e("Must be called from the main thread.");
        imageView.setOnClickListener(new zzb(this));
        zzl(imageView, new v1(imageView, this.zzc));
    }

    public void bindImageViewToPlayPauseToggle(ImageView imageView, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        t.e("Must be called from the main thread.");
        l6.a(m5.PAUSE_CONTROLLER);
        imageView.setOnClickListener(new zzc(this));
        zzl(imageView, new w1(imageView, this.zzc, drawable, drawable2, drawable3, view, z));
    }

    public void bindProgressBar(ProgressBar progressBar) {
        bindProgressBar(progressBar, 1000L);
    }

    public void bindSeekBar(SeekBar seekBar) {
        bindSeekBar(seekBar, 1000L);
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, String str) {
        t.e("Must be called from the main thread.");
        bindTextViewToMetadataOfCurrentItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, String str) {
        t.e("Must be called from the main thread.");
        bindTextViewToMetadataOfPreloadedItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToSmartSubtitle(TextView textView) {
        t.e("Must be called from the main thread.");
        zzl(textView, new s1(textView, 2));
    }

    public void bindTextViewToStreamDuration(TextView textView) {
        t.e("Must be called from the main thread.");
        zzl(textView, new c2(textView, this.zzc.getString(R.string.cast_invalid_stream_duration_text), null));
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z) {
        bindTextViewToStreamPosition(textView, z, 1000L);
    }

    public void bindViewToClosedCaption(View view) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zzk(this));
        zzl(view, new m1(this.zzc, view));
    }

    public void bindViewToForward(View view, long j10) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zzf(this, j10));
        zzl(view, new n1(view, this.zza));
    }

    public void bindViewToLaunchExpandedController(View view) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zzj(this));
        zzl(view, new s1(view));
    }

    public void bindViewToLoadingIndicator(View view) {
        t.e("Must be called from the main thread.");
        zzl(view, new s1(view, 1));
    }

    public void bindViewToRewind(View view, long j10) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zzg(this, j10));
        zzl(view, new y1(view, this.zza));
    }

    public void bindViewToSkipNext(View view, int i10) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zzd(this));
        zzl(view, new b2(view, i10, 0));
    }

    public void bindViewToSkipPrev(View view, int i10) {
        t.e("Must be called from the main thread.");
        view.setOnClickListener(new zze(this));
        zzl(view, new b2(view, i10, 1));
    }

    public void bindViewToUIController(View view, UIController uIController) {
        t.e("Must be called from the main thread.");
        zzl(view, uIController);
    }

    public void bindViewVisibilityToMediaSession(View view, int i10) {
        t.e("Must be called from the main thread.");
        zzl(view, new b2(view, i10, 3, false));
    }

    public void bindViewVisibilityToPreloadingEvent(View view, int i10) {
        t.e("Must be called from the main thread.");
        zzl(view, new b2(view, i10, 2, false));
    }

    public void dispose() {
        t.e("Must be called from the main thread.");
        zzk();
        this.zze.clear();
        SessionManager sessionManager = this.zzd;
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(this, CastSession.class);
        }
        this.zzg = null;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        t.e("Must be called from the main thread.");
        return this.zzh;
    }

    @EnsuresNonNullIf(expression = {"remoteMediaClient"}, result = true)
    public boolean isActive() {
        t.e("Must be called from the main thread.");
        return this.zzh != null;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onAdBreakStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onAdBreakStatusUpdated();
        }
    }

    public void onClosedCaptionClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        Activity activity = this.zzc;
        if (activity instanceof h0) {
            TracksChooserDialogFragment tracksChooserDialogFragmentNewInstance = TracksChooserDialogFragment.newInstance();
            h0 h0Var = (h0) activity;
            b1 supportFragmentManager = h0Var.getSupportFragmentManager();
            supportFragmentManager.getClass();
            a aVar = new a(supportFragmentManager);
            c0 c0VarB = h0Var.getSupportFragmentManager().B("TRACKS_CHOOSER_DIALOG_TAG");
            if (c0VarB != null) {
                aVar.g(c0VarB);
            }
            tracksChooserDialogFragmentNewInstance.show(aVar, "TRACKS_CHOOSER_DIALOG_TAG");
        }
    }

    public void onForwardClicked(View view, long j10) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (!remoteMediaClient.zzj()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() + j10);
            return;
        }
        long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition() + j10;
        zza zzaVar = this.zza;
        remoteMediaClient.seek(Math.min(approximateStreamPosition, zzaVar.zzh() + ((long) zzaVar.zzg())));
    }

    public void onLaunchExpandedControllerClicked(View view) {
        Activity activity = this.zzc;
        CastMediaOptions castMediaOptions = CastContext.getSharedInstance(activity).getCastOptions().getCastMediaOptions();
        if (castMediaOptions == null || TextUtils.isEmpty(castMediaOptions.getExpandedControllerActivityClassName())) {
            return;
        }
        ComponentName componentName = new ComponentName(activity.getApplicationContext(), castMediaOptions.getExpandedControllerActivityClassName());
        Intent intent = new Intent();
        intent.setComponent(componentName);
        activity.startActivity(intent);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onMetadataUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onMetadataUpdated();
        }
    }

    public void onMuteToggleClicked(ImageView imageView) {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzc.getApplicationContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return;
        }
        try {
            currentCastSession.setMute(!currentCastSession.isMute());
        } catch (IOException | IllegalArgumentException e5) {
            zzb.e("Unable to call CastSession.setMute(boolean).", e5);
        }
    }

    public void onPlayPauseToggleClicked(ImageView imageView) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.togglePlayback();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onPreloadStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onQueueStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onQueueStatusUpdated();
        }
    }

    public void onRewindClicked(View view, long j10) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (!remoteMediaClient.zzj()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() - j10);
            return;
        }
        long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition() - j10;
        zza zzaVar = this.zza;
        remoteMediaClient.seek(Math.max(approximateStreamPosition, zzaVar.zzh() + ((long) zzaVar.zzf())));
    }

    public void onSeekBarProgressChanged(SeekBar seekBar, int i10, boolean z) {
        zzi(i10, z);
    }

    public void onSeekBarStartTrackingTouch(SeekBar seekBar) {
        Map map = this.zze;
        if (map.containsKey(seekBar)) {
            for (UIController uIController : (List) map.get(seekBar)) {
                if (uIController instanceof a2) {
                    ((a2) uIController).f13161d = false;
                }
            }
        }
        zzh();
    }

    public void onSeekBarStopTrackingTouch(SeekBar seekBar) {
        Map map = this.zze;
        if (map.containsKey(seekBar)) {
            for (UIController uIController : (List) map.get(seekBar)) {
                if (uIController instanceof a2) {
                    ((a2) uIController).f13161d = true;
                }
            }
        }
        zzg(seekBar.getProgress());
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onSendingRemoteMediaRequest() {
        Iterator it = this.zze.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                ((UIController) it2.next()).onSendingRemoteMediaRequest();
            }
        }
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onSendingRemoteMediaRequest();
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnded(CastSession castSession, int i10) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnding(CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumeFailed(CastSession castSession, int i10) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumed(CastSession castSession, boolean z) {
        zzj(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResuming(CastSession castSession, String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStartFailed(CastSession castSession, int i10) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(CastSession castSession, String str) {
        zzj(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarting(CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionSuspended(CastSession castSession, int i10) {
    }

    public void onSkipNextClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queueNext(null);
    }

    public void onSkipPrevClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queuePrev(null);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener listener = this.zzg;
        if (listener != null) {
            listener.onStatusUpdated();
        }
    }

    public void setPostRemoteMediaClientListener(RemoteMediaClient.Listener listener) {
        t.e("Must be called from the main thread.");
        this.zzg = listener;
    }

    public final void zza(ImageView imageView, ImageHints imageHints, View view, p1 p1Var) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new r1(imageView, this.zzc, imageHints, 0, view, p1Var));
    }

    public final void zzb(e2 e2Var) {
        this.zzf.add(e2Var);
    }

    public final void zzc(CastSeekBar castSeekBar) {
        zzg(castSeekBar.getProgress());
    }

    public final void zzd(CastSeekBar castSeekBar) {
        zzh();
    }

    public final void zze(CastSeekBar castSeekBar, int i10, boolean z) {
        zzi(i10, z);
    }

    public final zza zzf() {
        return this.zza;
    }

    public void bindProgressBar(ProgressBar progressBar, long j10) {
        t.e("Must be called from the main thread.");
        zzl(progressBar, new x1(progressBar, j10));
    }

    public void bindSeekBar(SeekBar seekBar, long j10) {
        l6.a(m5.SEEK_CONTROLLER);
        t.e("Must be called from the main thread.");
        seekBar.setOnSeekBarChangeListener(new zzi(this, seekBar));
        zzl(seekBar, new a2(seekBar, j10, this.zza));
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z, long j10) {
        t.e("Must be called from the main thread.");
        d2 d2Var = new d2(textView, j10, this.zzc.getString(R.string.cast_invalid_stream_position_text));
        if (z) {
            this.zzf.add(d2Var);
        }
        zzl(textView, d2Var);
    }

    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, ImageHints imageHints, int i10) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new o1(imageView, this.zzc, imageHints, i10));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, List<String> list) {
        t.e("Must be called from the main thread.");
        zzl(textView, new t1(textView, list, 1));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, List<String> list) {
        t.e("Must be called from the main thread.");
        zzl(textView, new t1(textView, list, 0));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i10, View view) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new r1(imageView, this.zzc, new ImageHints(i10, 0, 0), 0, view, null));
    }

    public void bindTextViewToStreamDuration(TextView textView, View view) {
        t.e("Must be called from the main thread.");
        zzl(textView, new c2(textView, this.zzc.getString(R.string.cast_invalid_stream_duration_text), view));
    }

    public void bindSeekBar(CastSeekBar castSeekBar) {
        bindSeekBar(castSeekBar, 1000L);
    }

    public void bindSeekBar(CastSeekBar castSeekBar, long j10) {
        t.e("Must be called from the main thread.");
        l6.a(m5.SEEK_CONTROLLER);
        castSeekBar.zzd = new zzh(this);
        zzl(castSeekBar, new l1(castSeekBar, j10, this.zza));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, int i10) {
        t.e("Must be called from the main thread.");
        zzl(imageView, new r1(imageView, this.zzc, imageHints, i10, null, null));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, View view) {
        zza(imageView, imageHints, view, null);
    }
}
