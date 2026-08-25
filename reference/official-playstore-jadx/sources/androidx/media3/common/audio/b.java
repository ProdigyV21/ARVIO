package androidx.media3.common.audio;

import android.media.AudioManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements AudioManager.OnAudioFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AudioFocusManager f3364a;

    public /* synthetic */ b(AudioFocusManager audioFocusManager) {
        this.f3364a = audioFocusManager;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i10) {
        this.f3364a.handlePlatformAudioFocusChange(i10);
    }
}
