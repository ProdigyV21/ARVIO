package com.arflix.tv.ui.skin;

import androidx.compose.animation.core.Easing;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioMotionTokens;", "", "focusDurationMillis", "", "focusEasing", "Landroidx/compose/animation/core/Easing;", "screenTransitionMillis", "heroFadeMillis", "<init>", "(ILandroidx/compose/animation/core/Easing;II)V", "getFocusDurationMillis", "()I", "getFocusEasing", "()Landroidx/compose/animation/core/Easing;", "getScreenTransitionMillis", "getHeroFadeMillis", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioMotionTokens {
    public static final int $stable = 0;
    private final int focusDurationMillis;
    private final Easing focusEasing;
    private final int heroFadeMillis;
    private final int screenTransitionMillis;

    public ArvioMotionTokens(int i10, Easing easing, int i11, int i12) {
        this.focusDurationMillis = i10;
        this.focusEasing = easing;
        this.screenTransitionMillis = i11;
        this.heroFadeMillis = i12;
    }

    public static /* synthetic */ ArvioMotionTokens copy$default(ArvioMotionTokens arvioMotionTokens, int i10, Easing easing, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = arvioMotionTokens.focusDurationMillis;
        }
        if ((i13 & 2) != 0) {
            easing = arvioMotionTokens.focusEasing;
        }
        if ((i13 & 4) != 0) {
            i11 = arvioMotionTokens.screenTransitionMillis;
        }
        if ((i13 & 8) != 0) {
            i12 = arvioMotionTokens.heroFadeMillis;
        }
        return arvioMotionTokens.copy(i10, easing, i11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getFocusDurationMillis() {
        return this.focusDurationMillis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Easing getFocusEasing() {
        return this.focusEasing;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getScreenTransitionMillis() {
        return this.screenTransitionMillis;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getHeroFadeMillis() {
        return this.heroFadeMillis;
    }

    public final ArvioMotionTokens copy(int focusDurationMillis, Easing focusEasing, int screenTransitionMillis, int heroFadeMillis) {
        return new ArvioMotionTokens(focusDurationMillis, focusEasing, screenTransitionMillis, heroFadeMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioMotionTokens)) {
            return false;
        }
        ArvioMotionTokens arvioMotionTokens = (ArvioMotionTokens) other;
        return this.focusDurationMillis == arvioMotionTokens.focusDurationMillis && p.a(this.focusEasing, arvioMotionTokens.focusEasing) && this.screenTransitionMillis == arvioMotionTokens.screenTransitionMillis && this.heroFadeMillis == arvioMotionTokens.heroFadeMillis;
    }

    public final int getFocusDurationMillis() {
        return this.focusDurationMillis;
    }

    public final Easing getFocusEasing() {
        return this.focusEasing;
    }

    public final int getHeroFadeMillis() {
        return this.heroFadeMillis;
    }

    public final int getScreenTransitionMillis() {
        return this.screenTransitionMillis;
    }

    public int hashCode() {
        return ((((this.focusEasing.hashCode() + (this.focusDurationMillis * 31)) * 31) + this.screenTransitionMillis) * 31) + this.heroFadeMillis;
    }

    public String toString() {
        return "ArvioMotionTokens(focusDurationMillis=" + this.focusDurationMillis + ", focusEasing=" + this.focusEasing + ", screenTransitionMillis=" + this.screenTransitionMillis + ", heroFadeMillis=" + this.heroFadeMillis + ")";
    }
}
