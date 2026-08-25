package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u0012\u0010\u0011J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u0013\u0010\u0011J\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u0014\u0010\u0011J\u001a\u0010\u0017\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b#\u0010\u001eR\u001d\u0010\t\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b$\u0010\u001eR\u001d\u0010\n\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b%\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Landroidx/compose/material3/DefaultDrawerItemsColor;", "Landroidx/compose/material3/NavigationDrawerItemColors;", "Landroidx/compose/ui/graphics/Color;", "selectedIconColor", "unselectedIconColor", "selectedTextColor", "unselectedTextColor", "selectedContainerColor", "unselectedContainerColor", "selectedBadgeColor", "unselectedBadgeColor", "<init>", "(JJJJJJJJLkotlin/jvm/internal/h;)V", "", "selected", "Landroidx/compose/runtime/State;", "iconColor", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "textColor", "containerColor", "badgeColor", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getSelectedIconColor-0d7_KjU", "()J", "getUnselectedIconColor-0d7_KjU", "getSelectedTextColor-0d7_KjU", "getUnselectedTextColor-0d7_KjU", "getSelectedContainerColor-0d7_KjU", "getUnselectedContainerColor-0d7_KjU", "getSelectedBadgeColor-0d7_KjU", "getUnselectedBadgeColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultDrawerItemsColor implements NavigationDrawerItemColors {
    private final long selectedBadgeColor;
    private final long selectedContainerColor;
    private final long selectedIconColor;
    private final long selectedTextColor;
    private final long unselectedBadgeColor;
    private final long unselectedContainerColor;
    private final long unselectedIconColor;
    private final long unselectedTextColor;

    public /* synthetic */ DefaultDrawerItemsColor(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13, j14, j15, j16, j17);
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public State<Color> badgeColor(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-561675044);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-561675044, i10, -1, "androidx.compose.material3.DefaultDrawerItemsColor.badgeColor (NavigationDrawer.kt:876)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.selectedBadgeColor : this.unselectedBadgeColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public State<Color> containerColor(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(-433512770);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-433512770, i10, -1, "androidx.compose.material3.DefaultDrawerItemsColor.containerColor (NavigationDrawer.kt:869)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.selectedContainerColor : this.unselectedContainerColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultDrawerItemsColor)) {
            return false;
        }
        DefaultDrawerItemsColor defaultDrawerItemsColor = (DefaultDrawerItemsColor) other;
        if (Color.m3473equalsimpl0(this.selectedIconColor, defaultDrawerItemsColor.selectedIconColor) && Color.m3473equalsimpl0(this.unselectedIconColor, defaultDrawerItemsColor.unselectedIconColor) && Color.m3473equalsimpl0(this.selectedTextColor, defaultDrawerItemsColor.selectedTextColor) && Color.m3473equalsimpl0(this.unselectedTextColor, defaultDrawerItemsColor.unselectedTextColor) && Color.m3473equalsimpl0(this.selectedContainerColor, defaultDrawerItemsColor.selectedContainerColor) && Color.m3473equalsimpl0(this.unselectedContainerColor, defaultDrawerItemsColor.unselectedContainerColor) && Color.m3473equalsimpl0(this.selectedBadgeColor, defaultDrawerItemsColor.selectedBadgeColor)) {
            return Color.m3473equalsimpl0(this.unselectedBadgeColor, defaultDrawerItemsColor.unselectedBadgeColor);
        }
        return false;
    }

    /* JADX INFO: renamed from: getSelectedBadgeColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedBadgeColor() {
        return this.selectedBadgeColor;
    }

    /* JADX INFO: renamed from: getSelectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedContainerColor() {
        return this.selectedContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedIconColor() {
        return this.selectedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedTextColor() {
        return this.selectedTextColor;
    }

    /* JADX INFO: renamed from: getUnselectedBadgeColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedBadgeColor() {
        return this.unselectedBadgeColor;
    }

    /* JADX INFO: renamed from: getUnselectedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedContainerColor() {
        return this.unselectedContainerColor;
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
        return Color.m3479hashCodeimpl(this.unselectedBadgeColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.selectedIconColor) * 31, 31, this.unselectedIconColor), 31, this.selectedTextColor), 31, this.unselectedTextColor), 31, this.selectedContainerColor), 31, this.unselectedContainerColor), 31, this.selectedBadgeColor);
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public State<Color> iconColor(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1141354218);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1141354218, i10, -1, "androidx.compose.material3.DefaultDrawerItemsColor.iconColor (NavigationDrawer.kt:859)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.selectedIconColor : this.unselectedIconColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public State<Color> textColor(boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1275109558);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1275109558, i10, -1, "androidx.compose.material3.DefaultDrawerItemsColor.textColor (NavigationDrawer.kt:864)");
        }
        State<Color> stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(z ? this.selectedTextColor : this.unselectedTextColor), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateRememberUpdatedState;
    }

    private DefaultDrawerItemsColor(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17) {
        this.selectedIconColor = j10;
        this.unselectedIconColor = j11;
        this.selectedTextColor = j12;
        this.unselectedTextColor = j13;
        this.selectedContainerColor = j14;
        this.unselectedContainerColor = j15;
        this.selectedBadgeColor = j16;
        this.unselectedBadgeColor = j17;
    }
}
