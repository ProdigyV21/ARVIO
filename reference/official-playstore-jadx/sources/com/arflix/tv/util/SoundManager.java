package com.arflix.tv.util;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u000b\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0010\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0011\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0012\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0013\u0010\u000eJ\u0015\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010\u001a\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010 ¨\u0006!"}, d2 = {"Lcom/arflix/tv/util/SoundManager;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/View;", "view", "", "effectType", "Lx6/t0;", "playSoundSafely", "(Landroid/view/View;I)V", "playMove", "(Landroid/view/View;)V", "playMoveUp", "playMoveLeft", "playMoveRight", "playSelect", "playBack", "playSoundEffect", "(I)V", "", "enabled", "setEnabled", "(Z)V", "isEnabled", "()Z", "Landroid/content/Context;", "Landroid/media/AudioManager;", "audioManager", "Landroid/media/AudioManager;", "Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SoundManager {
    public static final int $stable = 8;
    private final AudioManager audioManager;
    private final Context context;
    private boolean isEnabled;

    @Inject
    public SoundManager(@ApplicationContext Context context) {
        this.context = context;
        Object systemService = context.getSystemService("audio");
        this.audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        this.isEnabled = true;
    }

    private final void playSoundSafely(View view, int effectType) {
        if (this.isEnabled && view != null) {
            try {
                if (view.isEnabled()) {
                    view.playSoundEffect(effectType);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final void playBack(View view) {
        playSoundSafely(view, 2);
    }

    public final void playMove(View view) {
        playSoundSafely(view, 4);
    }

    public final void playMoveLeft(View view) {
        playSoundSafely(view, 1);
    }

    public final void playMoveRight(View view) {
        playSoundSafely(view, 3);
    }

    public final void playMoveUp(View view) {
        playSoundSafely(view, 2);
    }

    public final void playSelect(View view) {
        playSoundSafely(view, 0);
    }

    public final void playSoundEffect(int effectType) {
        if (this.isEnabled) {
            try {
                AudioManager audioManager = this.audioManager;
                if (audioManager != null) {
                    audioManager.playSoundEffect(effectType, 1.0f);
                }
            } catch (Exception unused) {
            }
        }
    }

    public final void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
