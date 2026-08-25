package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.material3.MenuKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.ts.PsExtractor;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0014\u0010\u0017\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007R\u0013\u0010#\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b$\u0010\u0007R\u0013\u0010%\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b&\u0010\u0007R\u0013\u0010'\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b(\u0010\u0007¨\u0006)"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveDims;", "", "<init>", "()V", "SidebarExpanded", "Landroidx/compose/ui/unit/Dp;", "getSidebarExpanded-D9Ej5fM", "()F", "F", "SidebarCollapsed", "getSidebarCollapsed-D9Ej5fM", "SidebarRowHeight", "getSidebarRowHeight-D9Ej5fM", "MiniPlayerWidth", "getMiniPlayerWidth-D9Ej5fM", "MiniPlayerHeight", "getMiniPlayerHeight-D9Ej5fM", "EpgChannelColWidth", "getEpgChannelColWidth-D9Ej5fM", "EpgRowHeight", "getEpgRowHeight-D9Ej5fM", "EpgHeaderHeight", "getEpgHeaderHeight-D9Ej5fM", "EpgPxPerMinute", "", "getEpgPxPerMinute", "()I", "EpgHalfHourWidth", "getEpgHalfHourWidth-D9Ej5fM", "PanelRadius", "getPanelRadius-D9Ej5fM", "CardRadius", "getCardRadius-D9Ej5fM", "CellRadius", "getCellRadius-D9Ej5fM", "VideoRadius", "getVideoRadius-D9Ej5fM", "FocusBorder", "getFocusBorder-D9Ej5fM", "ActiveIndicator", "getActiveIndicator-D9Ej5fM", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveDims {
    public static final int $stable = 0;
    private static final float EpgHeaderHeight;
    private static final float PanelRadius;
    private static final float SidebarRowHeight;
    private static final float VideoRadius;
    public static final LiveDims INSTANCE = new LiveDims();
    private static final float SidebarExpanded = Dp.m5678constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final float SidebarCollapsed = Dp.m5678constructorimpl(52);
    private static final float MiniPlayerWidth = Dp.m5678constructorimpl(300);
    private static final float MiniPlayerHeight = Dp.m5678constructorimpl(168);
    private static final float EpgChannelColWidth = Dp.m5678constructorimpl(220);
    private static final float EpgRowHeight = Dp.m5678constructorimpl(42);
    private static final int EpgPxPerMinute = 4;
    private static final float EpgHalfHourWidth = Dp.m5678constructorimpl(MenuKt.InTransitionDuration);
    private static final float CardRadius = Dp.m5678constructorimpl(10);
    private static final float CellRadius = Dp.m5678constructorimpl(6);
    private static final float FocusBorder = Dp.m5678constructorimpl(2);
    private static final float ActiveIndicator = Dp.m5678constructorimpl(3);

    static {
        float f10 = 26;
        SidebarRowHeight = Dp.m5678constructorimpl(f10);
        EpgHeaderHeight = Dp.m5678constructorimpl(f10);
        float f11 = 12;
        PanelRadius = Dp.m5678constructorimpl(f11);
        VideoRadius = Dp.m5678constructorimpl(f11);
    }

    private LiveDims() {
    }

    /* JADX INFO: renamed from: getActiveIndicator-D9Ej5fM, reason: not valid java name */
    public final float m6419getActiveIndicatorD9Ej5fM() {
        return ActiveIndicator;
    }

    /* JADX INFO: renamed from: getCardRadius-D9Ej5fM, reason: not valid java name */
    public final float m6420getCardRadiusD9Ej5fM() {
        return CardRadius;
    }

    /* JADX INFO: renamed from: getCellRadius-D9Ej5fM, reason: not valid java name */
    public final float m6421getCellRadiusD9Ej5fM() {
        return CellRadius;
    }

    /* JADX INFO: renamed from: getEpgChannelColWidth-D9Ej5fM, reason: not valid java name */
    public final float m6422getEpgChannelColWidthD9Ej5fM() {
        return EpgChannelColWidth;
    }

    /* JADX INFO: renamed from: getEpgHalfHourWidth-D9Ej5fM, reason: not valid java name */
    public final float m6423getEpgHalfHourWidthD9Ej5fM() {
        return EpgHalfHourWidth;
    }

    /* JADX INFO: renamed from: getEpgHeaderHeight-D9Ej5fM, reason: not valid java name */
    public final float m6424getEpgHeaderHeightD9Ej5fM() {
        return EpgHeaderHeight;
    }

    public final int getEpgPxPerMinute() {
        return EpgPxPerMinute;
    }

    /* JADX INFO: renamed from: getEpgRowHeight-D9Ej5fM, reason: not valid java name */
    public final float m6425getEpgRowHeightD9Ej5fM() {
        return EpgRowHeight;
    }

    /* JADX INFO: renamed from: getFocusBorder-D9Ej5fM, reason: not valid java name */
    public final float m6426getFocusBorderD9Ej5fM() {
        return FocusBorder;
    }

    /* JADX INFO: renamed from: getMiniPlayerHeight-D9Ej5fM, reason: not valid java name */
    public final float m6427getMiniPlayerHeightD9Ej5fM() {
        return MiniPlayerHeight;
    }

    /* JADX INFO: renamed from: getMiniPlayerWidth-D9Ej5fM, reason: not valid java name */
    public final float m6428getMiniPlayerWidthD9Ej5fM() {
        return MiniPlayerWidth;
    }

    /* JADX INFO: renamed from: getPanelRadius-D9Ej5fM, reason: not valid java name */
    public final float m6429getPanelRadiusD9Ej5fM() {
        return PanelRadius;
    }

    /* JADX INFO: renamed from: getSidebarCollapsed-D9Ej5fM, reason: not valid java name */
    public final float m6430getSidebarCollapsedD9Ej5fM() {
        return SidebarCollapsed;
    }

    /* JADX INFO: renamed from: getSidebarExpanded-D9Ej5fM, reason: not valid java name */
    public final float m6431getSidebarExpandedD9Ej5fM() {
        return SidebarExpanded;
    }

    /* JADX INFO: renamed from: getSidebarRowHeight-D9Ej5fM, reason: not valid java name */
    public final float m6432getSidebarRowHeightD9Ej5fM() {
        return SidebarRowHeight;
    }

    /* JADX INFO: renamed from: getVideoRadius-D9Ej5fM, reason: not valid java name */
    public final float m6433getVideoRadiusD9Ej5fM() {
        return VideoRadius;
    }
}
