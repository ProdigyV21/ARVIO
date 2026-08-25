package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ8\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0004\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/ButtonColors;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "disabledContainerColor", "disabledContentColor", "<init>", "(JJJJLkotlin/jvm/internal/h;)V", "copy-jRlVdoo", "(JJJJ)Landroidx/compose/material3/ButtonColors;", "copy", "", "enabled", "containerColor-vNxB06k$material3_release", "(Z)J", "contentColor-vNxB06k$material3_release", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getContainerColor-0d7_KjU", "()J", "getContentColor-0d7_KjU", "getDisabledContainerColor-0d7_KjU", "getDisabledContentColor-0d7_KjU", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ButtonColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long contentColor;
    private final long disabledContainerColor;
    private final long disabledContentColor;

    public /* synthetic */ ButtonColors(long j10, long j11, long j12, long j13, kotlin.jvm.internal.h hVar) {
        this(j10, j11, j12, j13);
    }

    /* JADX INFO: renamed from: copy-jRlVdoo$default, reason: not valid java name */
    public static /* synthetic */ ButtonColors m1258copyjRlVdoo$default(ButtonColors buttonColors, long j10, long j11, long j12, long j13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = buttonColors.containerColor;
        }
        long j14 = j10;
        if ((i10 & 2) != 0) {
            j11 = buttonColors.contentColor;
        }
        long j15 = j11;
        if ((i10 & 4) != 0) {
            j12 = buttonColors.disabledContainerColor;
        }
        return buttonColors.m1261copyjRlVdoo(j14, j15, j12, (i10 & 8) != 0 ? buttonColors.disabledContentColor : j13);
    }

    /* JADX INFO: renamed from: containerColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1259containerColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.containerColor : this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: contentColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m1260contentColorvNxB06k$material3_release(boolean enabled) {
        return enabled ? this.contentColor : this.disabledContentColor;
    }

    /* JADX INFO: renamed from: copy-jRlVdoo, reason: not valid java name */
    public final ButtonColors m1261copyjRlVdoo(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor) {
        Color.Companion companion = Color.INSTANCE;
        if (containerColor == companion.m3508getUnspecified0d7_KjU()) {
            containerColor = this.containerColor;
        }
        return new ButtonColors(containerColor, contentColor != companion.m3508getUnspecified0d7_KjU() ? contentColor : this.contentColor, disabledContainerColor != companion.m3508getUnspecified0d7_KjU() ? disabledContainerColor : this.disabledContainerColor, disabledContentColor != companion.m3508getUnspecified0d7_KjU() ? disabledContentColor : this.disabledContentColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof ButtonColors)) {
            return false;
        }
        ButtonColors buttonColors = (ButtonColors) other;
        return Color.m3473equalsimpl0(this.containerColor, buttonColors.containerColor) && Color.m3473equalsimpl0(this.contentColor, buttonColors.contentColor) && Color.m3473equalsimpl0(this.disabledContainerColor, buttonColors.disabledContainerColor) && Color.m3473equalsimpl0(this.disabledContentColor, buttonColors.disabledContentColor);
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContentColor() {
        return this.contentColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledContainerColor() {
        return this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledContentColor() {
        return this.disabledContentColor;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.disabledContentColor) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.containerColor) * 31, 31, this.contentColor), 31, this.disabledContainerColor);
    }

    private ButtonColors(long j10, long j11, long j12, long j13) {
        this.containerColor = j10;
        this.contentColor = j11;
        this.disabledContainerColor = j12;
        this.disabledContentColor = j13;
    }
}
