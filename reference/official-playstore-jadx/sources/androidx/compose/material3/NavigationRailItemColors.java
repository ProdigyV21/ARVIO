package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJV\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0016\u0010\u0014J\u001a\u0010\u0019\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b!\u0010 R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\"\u0010 R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b#\u0010 R\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b$\u0010 R\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b%\u0010 R\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b&\u0010 R\u001a\u0010(\u001a\u00020\u00028@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b'\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Landroidx/compose/material3/NavigationRailItemColors;", "", "Landroidx/compose/ui/graphics/Color;", "selectedIconColor", "selectedTextColor", "selectedIndicatorColor", "unselectedIconColor", "unselectedTextColor", "disabledIconColor", "disabledTextColor", "<init>", "(JJJJJJJLkotlin/jvm/internal/h;)V", "copy-4JmcsL4", "(JJJJJJJ)Landroidx/compose/material3/NavigationRailItemColors;", "copy", "", "selected", "enabled", "Landroidx/compose/runtime/State;", "iconColor$material3_release", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "iconColor", "textColor$material3_release", "textColor", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getSelectedIconColor-0d7_KjU", "()J", "getSelectedTextColor-0d7_KjU", "getSelectedIndicatorColor-0d7_KjU", "getUnselectedIconColor-0d7_KjU", "getUnselectedTextColor-0d7_KjU", "getDisabledIconColor-0d7_KjU", "getDisabledTextColor-0d7_KjU", "getIndicatorColor-0d7_KjU$material3_release", "indicatorColor", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NavigationRailItemColors {
    public static final int $stable = 0;
    private final long disabledIconColor;
    private final long disabledTextColor;
    private final long selectedIconColor;
    private final long selectedIndicatorColor;
    private final long selectedTextColor;
    private final long unselectedIconColor;
    private final long unselectedTextColor;

    public /* synthetic */ NavigationRailItemColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16);
    }

    /* JADX INFO: renamed from: copy-4JmcsL4, reason: not valid java name */
    public final NavigationRailItemColors m1727copy4JmcsL4(long selectedIconColor, long selectedTextColor, long selectedIndicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor) {
        Color.Companion companion = Color.INSTANCE;
        return new NavigationRailItemColors(selectedIconColor != companion.m3508getUnspecified0d7_KjU() ? selectedIconColor : this.selectedIconColor, selectedTextColor != companion.m3508getUnspecified0d7_KjU() ? selectedTextColor : this.selectedTextColor, selectedIndicatorColor != companion.m3508getUnspecified0d7_KjU() ? selectedIndicatorColor : this.selectedIndicatorColor, unselectedIconColor != companion.m3508getUnspecified0d7_KjU() ? unselectedIconColor : this.unselectedIconColor, unselectedTextColor != companion.m3508getUnspecified0d7_KjU() ? unselectedTextColor : this.unselectedTextColor, disabledIconColor != companion.m3508getUnspecified0d7_KjU() ? disabledIconColor : this.disabledIconColor, disabledTextColor != companion.m3508getUnspecified0d7_KjU() ? disabledTextColor : this.disabledTextColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof NavigationRailItemColors)) {
            return false;
        }
        NavigationRailItemColors navigationRailItemColors = (NavigationRailItemColors) other;
        return Color.m3473equalsimpl0(this.selectedIconColor, navigationRailItemColors.selectedIconColor) && Color.m3473equalsimpl0(this.unselectedIconColor, navigationRailItemColors.unselectedIconColor) && Color.m3473equalsimpl0(this.selectedTextColor, navigationRailItemColors.selectedTextColor) && Color.m3473equalsimpl0(this.unselectedTextColor, navigationRailItemColors.unselectedTextColor) && Color.m3473equalsimpl0(this.selectedIndicatorColor, navigationRailItemColors.selectedIndicatorColor) && Color.m3473equalsimpl0(this.disabledIconColor, navigationRailItemColors.disabledIconColor) && Color.m3473equalsimpl0(this.disabledTextColor, navigationRailItemColors.disabledTextColor);
    }

    /* JADX INFO: renamed from: getDisabledIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIconColor() {
        return this.disabledIconColor;
    }

    /* JADX INFO: renamed from: getDisabledTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTextColor() {
        return this.disabledTextColor;
    }

    /* JADX INFO: renamed from: getIndicatorColor-0d7_KjU$material3_release, reason: not valid java name and from getter */
    public final long getSelectedIndicatorColor() {
        return this.selectedIndicatorColor;
    }

    /* JADX INFO: renamed from: getSelectedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedIconColor() {
        return this.selectedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedIndicatorColor-0d7_KjU, reason: not valid java name */
    public final long m1732getSelectedIndicatorColor0d7_KjU() {
        return this.selectedIndicatorColor;
    }

    /* JADX INFO: renamed from: getSelectedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedTextColor() {
        return this.selectedTextColor;
    }

    /* JADX INFO: renamed from: getUnselectedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedIconColor() {
        return this.unselectedIconColor;
    }

    /* JADX INFO: renamed from: getUnselectedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedTextColor() {
        return this.unselectedTextColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledTextColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.selectedIconColor) * 31, 31, this.unselectedIconColor), 31, this.selectedTextColor), 31, this.unselectedTextColor), 31, this.selectedIndicatorColor), 31, this.disabledIconColor);
    }

    public final State<Color> iconColor$material3_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(2131995553);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2131995553, i10, -1, "androidx.compose.material3.NavigationRailItemColors.iconColor (NavigationRail.kt:418)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(!z5 ? this.disabledIconColor : z ? this.selectedIconColor : this.unselectedIconColor, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    public final State<Color> textColor$material3_release(boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(-758555563);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-758555563, i10, -1, "androidx.compose.material3.NavigationRailItemColors.textColor (NavigationRail.kt:437)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = SingleValueAnimationKt.m69animateColorAsStateeuL9pac(!z5 ? this.disabledTextColor : z ? this.selectedTextColor : this.unselectedTextColor, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    private NavigationRailItemColors(long j10, long j11, long j12, long j13, long j14, long j15, long j16) {
        this.selectedIconColor = j10;
        this.selectedTextColor = j11;
        this.selectedIndicatorColor = j12;
        this.unselectedIconColor = j13;
        this.unselectedTextColor = j14;
        this.disabledIconColor = j15;
        this.disabledTextColor = j16;
    }
}
