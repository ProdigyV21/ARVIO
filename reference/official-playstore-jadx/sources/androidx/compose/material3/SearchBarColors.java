package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/SearchBarColors;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "dividerColor", "Landroidx/compose/material3/TextFieldColors;", "inputFieldColors", "<init>", "(JJLandroidx/compose/material3/TextFieldColors;Lkotlin/jvm/internal/h;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "J", "getContainerColor-0d7_KjU", "()J", "getDividerColor-0d7_KjU", "Landroidx/compose/material3/TextFieldColors;", "getInputFieldColors", "()Landroidx/compose/material3/TextFieldColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SearchBarColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long dividerColor;
    private final TextFieldColors inputFieldColors;

    public /* synthetic */ SearchBarColors(long j10, long j11, TextFieldColors textFieldColors, kotlin.jvm.internal.h hVar) {
        this(j10, j11, textFieldColors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!SearchBarColors.class.equals(other != null ? other.getClass() : null)) {
            return false;
        }
        SearchBarColors searchBarColors = (SearchBarColors) other;
        return Color.m3473equalsimpl0(this.containerColor, searchBarColors.containerColor) && Color.m3473equalsimpl0(this.dividerColor, searchBarColors.dividerColor) && p.a(this.inputFieldColors, searchBarColors.inputFieldColors);
    }

    /* JADX INFO: renamed from: getContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getContainerColor() {
        return this.containerColor;
    }

    /* JADX INFO: renamed from: getDividerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDividerColor() {
        return this.dividerColor;
    }

    public final TextFieldColors getInputFieldColors() {
        return this.inputFieldColors;
    }

    public int hashCode() {
        return this.inputFieldColors.hashCode() + androidx.compose.foundation.c.a(Color.m3479hashCodeimpl(this.containerColor) * 31, 31, this.dividerColor);
    }

    private SearchBarColors(long j10, long j11, TextFieldColors textFieldColors) {
        this.containerColor = j10;
        this.dividerColor = j11;
        this.inputFieldColors = textFieldColors;
    }
}
