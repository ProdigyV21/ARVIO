package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJL\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b#\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/material3/MenuItemColors;", "", "Landroidx/compose/ui/graphics/Color;", "textColor", "leadingIconColor", "trailingIconColor", "disabledTextColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "<init>", "(JJJJJJLkotlin/jvm/internal/h;)V", "copy-tNS2XkQ", "(JJJJJJ)Landroidx/compose/material3/MenuItemColors;", "copy", "", "enabled", "Landroidx/compose/runtime/State;", "textColor$material3_release", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "leadingIconColor-XeAY9LY$material3_release", "(ZLandroidx/compose/runtime/Composer;I)J", "trailingIconColor-XeAY9LY$material3_release", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getTextColor-0d7_KjU", "()J", "getLeadingIconColor-0d7_KjU", "getTrailingIconColor-0d7_KjU", "getDisabledTextColor-0d7_KjU", "getDisabledLeadingIconColor-0d7_KjU", "getDisabledTrailingIconColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MenuItemColors {
    public static final int $stable = 0;
    private final long disabledLeadingIconColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long leadingIconColor;
    private final long textColor;
    private final long trailingIconColor;

    public /* synthetic */ MenuItemColors(long j10, long j11, long j12, long j13, long j14, long j15, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15);
    }

    /* JADX INFO: renamed from: copy-tNS2XkQ$default, reason: not valid java name */
    public static /* synthetic */ MenuItemColors m1673copytNS2XkQ$default(MenuItemColors menuItemColors, long j10, long j11, long j12, long j13, long j14, long j15, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = menuItemColors.textColor;
        }
        return menuItemColors.m1674copytNS2XkQ(j10, (i10 & 2) != 0 ? menuItemColors.leadingIconColor : j11, (i10 & 4) != 0 ? menuItemColors.trailingIconColor : j12, (i10 & 8) != 0 ? menuItemColors.disabledTextColor : j13, (i10 & 16) != 0 ? menuItemColors.disabledLeadingIconColor : j14, (i10 & 32) != 0 ? menuItemColors.disabledTrailingIconColor : j15);
    }

    /* JADX INFO: renamed from: copy-tNS2XkQ, reason: not valid java name */
    public final MenuItemColors m1674copytNS2XkQ(long textColor, long leadingIconColor, long trailingIconColor, long disabledTextColor, long disabledLeadingIconColor, long disabledTrailingIconColor) {
        Color.Companion companion = Color.INSTANCE;
        return new MenuItemColors(textColor != companion.m3508getUnspecified0d7_KjU() ? textColor : this.textColor, leadingIconColor != companion.m3508getUnspecified0d7_KjU() ? leadingIconColor : this.leadingIconColor, trailingIconColor != companion.m3508getUnspecified0d7_KjU() ? trailingIconColor : this.trailingIconColor, disabledTextColor != companion.m3508getUnspecified0d7_KjU() ? disabledTextColor : this.disabledTextColor, disabledLeadingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledLeadingIconColor : this.disabledLeadingIconColor, disabledTrailingIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledTrailingIconColor : this.disabledTrailingIconColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof MenuItemColors)) {
            return false;
        }
        MenuItemColors menuItemColors = (MenuItemColors) other;
        return Color.m3473equalsimpl0(this.textColor, menuItemColors.textColor) && Color.m3473equalsimpl0(this.leadingIconColor, menuItemColors.leadingIconColor) && Color.m3473equalsimpl0(this.trailingIconColor, menuItemColors.trailingIconColor) && Color.m3473equalsimpl0(this.disabledTextColor, menuItemColors.disabledTextColor) && Color.m3473equalsimpl0(this.disabledLeadingIconColor, menuItemColors.disabledLeadingIconColor) && Color.m3473equalsimpl0(this.disabledTrailingIconColor, menuItemColors.disabledTrailingIconColor);
    }

    /* JADX INFO: renamed from: getDisabledLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLeadingIconColor() {
        return this.disabledLeadingIconColor;
    }

    /* JADX INFO: renamed from: getDisabledTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTextColor() {
        return this.disabledTextColor;
    }

    /* JADX INFO: renamed from: getDisabledTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTrailingIconColor() {
        return this.disabledTrailingIconColor;
    }

    /* JADX INFO: renamed from: getLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getLeadingIconColor() {
        return this.leadingIconColor;
    }

    /* JADX INFO: renamed from: getTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTextColor() {
        return this.textColor;
    }

    /* JADX INFO: renamed from: getTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTrailingIconColor() {
        return this.trailingIconColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledTrailingIconColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.textColor) * 31, 31, this.leadingIconColor), 31, this.trailingIconColor), 31, this.disabledTextColor), 31, this.disabledLeadingIconColor);
    }

    /* JADX INFO: renamed from: leadingIconColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m1681leadingIconColorXeAY9LY$material3_release(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-395881771);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-395881771, i10, -1, "androidx.compose.material3.MenuItemColors.leadingIconColor (Menu.kt:189)");
        }
        long j10 = z ? this.leadingIconColor : this.disabledLeadingIconColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return j10;
    }

    public final State<Color> textColor$material3_release(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-1023108655);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1023108655, i10, -1, "androidx.compose.material3.MenuItemColors.textColor (Menu.kt:178)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.textColor : this.disabledTextColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    /* JADX INFO: renamed from: trailingIconColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m1682trailingIconColorXeAY9LY$material3_release(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-892832569);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-892832569, i10, -1, "androidx.compose.material3.MenuItemColors.trailingIconColor (Menu.kt:198)");
        }
        long j10 = z ? this.trailingIconColor : this.disabledTrailingIconColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return j10;
    }

    private MenuItemColors(long j10, long j11, long j12, long j13, long j14, long j15) {
        this.textColor = j10;
        this.leadingIconColor = j11;
        this.trailingIconColor = j12;
        this.disabledTextColor = j13;
        this.disabledLeadingIconColor = j14;
        this.disabledTrailingIconColor = j15;
    }
}
