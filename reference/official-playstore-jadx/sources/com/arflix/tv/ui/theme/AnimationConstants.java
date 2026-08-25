package com.arflix.tv.ui.theme;

import androidx.compose.animation.core.CubicBezierEasing;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010#\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010%\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0011\u0010'\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0011\u0010)\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u000e\u0010+\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/arflix/tv/ui/theme/AnimationConstants;", "", "<init>", "()V", "DURATION_FAST", "", "DURATION_NORMAL", "DURATION_EMPHASIS", "DURATION_SLOW", "DURATION_EXTRA_SLOW", "DURATION_KEN_BURNS", "DURATION_IMAGE_CROSSFADE", "DURATION_BACKDROP_DISSOLVE", "STAGGER_CARD", "STAGGER_SECTION", "SCALE_UNFOCUSED", "", "SCALE_FOCUSED", "SCALE_PRESSED", "SCALE_PULSE_MIN", "SCALE_PULSE_MAX", "SPRING_STIFFNESS_FOCUS", "SPRING_DAMPING_FOCUS", "SPRING_STIFFNESS_GENTLE", "SPRING_DAMPING_GENTLE", "SPRING_STIFFNESS_TIGHT", "SPRING_DAMPING_TIGHT", "SPRING_STIFFNESS_SCROLL", "SPRING_DAMPING_SCROLL", "EaseOut", "Landroidx/compose/animation/core/CubicBezierEasing;", "getEaseOut", "()Landroidx/compose/animation/core/CubicBezierEasing;", "FastOutSlowIn", "getFastOutSlowIn", "EaseInOut", "getEaseInOut", "Sharp", "getSharp", "Decelerate", "getDecelerate", "SmoothDecelerate", "getSmoothDecelerate", "ELEVATION_CARD_UNFOCUSED", "ELEVATION_CARD_FOCUSED", "ELEVATION_MODAL", "BORDER_FOCUS_WIDTH", "GLOW_RADIUS_FOCUS", "GLOW_RADIUS_AMBIENT", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AnimationConstants {
    public static final int $stable = 0;
    public static final int BORDER_FOCUS_WIDTH = 3;
    public static final int DURATION_BACKDROP_DISSOLVE = 200;
    public static final int DURATION_EMPHASIS = 300;
    public static final int DURATION_EXTRA_SLOW = 1000;
    public static final int DURATION_FAST = 150;
    public static final int DURATION_IMAGE_CROSSFADE = 250;
    public static final int DURATION_KEN_BURNS = 20000;
    public static final int DURATION_NORMAL = 200;
    public static final int DURATION_SLOW = 500;
    public static final int ELEVATION_CARD_FOCUSED = 32;
    public static final int ELEVATION_CARD_UNFOCUSED = 4;
    public static final int ELEVATION_MODAL = 48;
    public static final int GLOW_RADIUS_AMBIENT = 8;
    public static final int GLOW_RADIUS_FOCUS = 16;
    public static final float SCALE_FOCUSED = 1.05f;
    public static final float SCALE_PRESSED = 0.97f;
    public static final float SCALE_PULSE_MAX = 1.02f;
    public static final float SCALE_PULSE_MIN = 1.0f;
    public static final float SCALE_UNFOCUSED = 1.0f;
    public static final float SPRING_DAMPING_FOCUS = 0.75f;
    public static final float SPRING_DAMPING_GENTLE = 0.8f;
    public static final float SPRING_DAMPING_SCROLL = 0.9f;
    public static final float SPRING_DAMPING_TIGHT = 0.85f;
    public static final float SPRING_STIFFNESS_FOCUS = 400.0f;
    public static final float SPRING_STIFFNESS_GENTLE = 400.0f;
    public static final float SPRING_STIFFNESS_SCROLL = 300.0f;
    public static final float SPRING_STIFFNESS_TIGHT = 500.0f;
    public static final int STAGGER_CARD = 40;
    public static final int STAGGER_SECTION = 80;
    public static final AnimationConstants INSTANCE = new AnimationConstants();
    private static final CubicBezierEasing EaseOut = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing FastOutSlowIn = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing EaseInOut = new CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f);
    private static final CubicBezierEasing Sharp = new CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f);
    private static final CubicBezierEasing Decelerate = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing SmoothDecelerate = new CubicBezierEasing(0.1f, 0.0f, 0.3f, 1.0f);

    private AnimationConstants() {
    }

    public final CubicBezierEasing getDecelerate() {
        return Decelerate;
    }

    public final CubicBezierEasing getEaseInOut() {
        return EaseInOut;
    }

    public final CubicBezierEasing getEaseOut() {
        return EaseOut;
    }

    public final CubicBezierEasing getFastOutSlowIn() {
        return FastOutSlowIn;
    }

    public final CubicBezierEasing getSharp() {
        return Sharp;
    }

    public final CubicBezierEasing getSmoothDecelerate() {
        return SmoothDecelerate;
    }
}
