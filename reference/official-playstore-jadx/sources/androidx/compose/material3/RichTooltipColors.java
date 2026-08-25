package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ8\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0018\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/RichTooltipColors;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "titleContentColor", "actionContentColor", "<init>", "(JJJJLkotlin/jvm/internal/h;)V", "copy-jRlVdoo", "(JJJJ)Landroidx/compose/material3/RichTooltipColors;", "copy", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getContainerColor-0d7_KjU", "()J", "getContentColor-0d7_KjU", "getTitleContentColor-0d7_KjU", "getActionContentColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RichTooltipColors {
    public static final int $stable = 0;
    private final long actionContentColor;
    private final long containerColor;
    private final long contentColor;
    private final long titleContentColor;

    public /* synthetic */ RichTooltipColors(long j10, long j11, long j12, long j13, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13);
    }

    /* JADX INFO: renamed from: copy-jRlVdoo$default, reason: not valid java name */
    public static /* synthetic */ RichTooltipColors m1791copyjRlVdoo$default(RichTooltipColors richTooltipColors, long j10, long j11, long j12, long j13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = richTooltipColors.containerColor;
        }
        long j14 = j10;
        if ((i10 & 2) != 0) {
            j11 = richTooltipColors.contentColor;
        }
        long j15 = j11;
        if ((i10 & 4) != 0) {
            j12 = richTooltipColors.titleContentColor;
        }
        return richTooltipColors.m1792copyjRlVdoo(j14, j15, j12, (i10 & 8) != 0 ? richTooltipColors.actionContentColor : j13);
    }

    /* JADX INFO: renamed from: copy-jRlVdoo, reason: not valid java name */
    public final RichTooltipColors m1792copyjRlVdoo(long containerColor, long contentColor, long titleContentColor, long actionContentColor) {
        Color.Companion companion = Color.INSTANCE;
        if (containerColor == companion.m3508getUnspecified0d7_KjU()) {
            containerColor = this.containerColor;
        }
        return new RichTooltipColors(containerColor, contentColor != companion.m3508getUnspecified0d7_KjU() ? contentColor : this.contentColor, titleContentColor != companion.m3508getUnspecified0d7_KjU() ? titleContentColor : this.titleContentColor, actionContentColor != companion.m3508getUnspecified0d7_KjU() ? actionContentColor : this.actionContentColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RichTooltipColors)) {
            return false;
        }
        RichTooltipColors richTooltipColors = (RichTooltipColors) other;
        return Color.m3473equalsimpl0(this.containerColor, richTooltipColors.containerColor) && Color.m3473equalsimpl0(this.contentColor, richTooltipColors.contentColor) && Color.m3473equalsimpl0(this.titleContentColor, richTooltipColors.titleContentColor) && Color.m3473equalsimpl0(this.actionContentColor, richTooltipColors.actionContentColor);
    }

    /* JADX INFO: renamed from: getActionContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActionContentColor() {
        return this.actionContentColor;
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContentColor() {
        return this.contentColor;
    }

    /* JADX INFO: renamed from: getTitleContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTitleContentColor() {
        return this.titleContentColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.actionContentColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.containerColor) * 31, 31, this.contentColor), 31, this.titleContentColor);
    }

    private RichTooltipColors(long j10, long j11, long j12, long j13) {
        this.containerColor = j10;
        this.contentColor = j11;
        this.titleContentColor = j12;
        this.actionContentColor = j13;
    }
}
