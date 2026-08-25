package androidx.compose.ui.text;

import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u001d\u0010\u0006\u001a\u00020\u00058\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/text/Placeholder;", "", "Landroidx/compose/ui/unit/TextUnit;", "width", "height", "Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "placeholderVerticalAlign", "<init>", "(JJILkotlin/jvm/internal/h;)V", "copy-K8Q-__8", "(JJI)Landroidx/compose/ui/text/Placeholder;", "copy", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getWidth-XSAIIZE", "()J", "getHeight-XSAIIZE", "I", "getPlaceholderVerticalAlign-J6kI3mc", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Placeholder {
    public static final int $stable = 0;
    private final long height;
    private final int placeholderVerticalAlign;
    private final long width;

    public /* synthetic */ Placeholder(long j10, long j11, int i10, h hVar) {
        this(j10, j11, i10);
    }

    /* JADX INFO: renamed from: copy-K8Q-__8$default, reason: not valid java name */
    public static /* synthetic */ Placeholder m5111copyK8Q__8$default(Placeholder placeholder, long j10, long j11, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            j10 = placeholder.width;
        }
        long j12 = j10;
        if ((i11 & 2) != 0) {
            j11 = placeholder.height;
        }
        long j13 = j11;
        if ((i11 & 4) != 0) {
            i10 = placeholder.placeholderVerticalAlign;
        }
        return placeholder.m5112copyK8Q__8(j12, j13, i10);
    }

    /* JADX INFO: renamed from: copy-K8Q-__8, reason: not valid java name */
    public final Placeholder m5112copyK8Q__8(long width, long height, int placeholderVerticalAlign) {
        return new Placeholder(width, height, placeholderVerticalAlign, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Placeholder)) {
            return false;
        }
        Placeholder placeholder = (Placeholder) other;
        return TextUnit.m5862equalsimpl0(this.width, placeholder.width) && TextUnit.m5862equalsimpl0(this.height, placeholder.height) && PlaceholderVerticalAlign.m5119equalsimpl0(this.placeholderVerticalAlign, placeholder.placeholderVerticalAlign);
    }

    /* JADX INFO: renamed from: getHeight-XSAIIZE, reason: not valid java name and from getter */
    public final long getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: getPlaceholderVerticalAlign-J6kI3mc, reason: not valid java name and from getter */
    public final int getPlaceholderVerticalAlign() {
        return this.placeholderVerticalAlign;
    }

    /* JADX INFO: renamed from: getWidth-XSAIIZE, reason: not valid java name and from getter */
    public final long getWidth() {
        return this.width;
    }

    public int hashCode() {
        return PlaceholderVerticalAlign.m5120hashCodeimpl(this.placeholderVerticalAlign) + ((TextUnit.m5866hashCodeimpl(this.height) + (TextUnit.m5866hashCodeimpl(this.width) * 31)) * 31);
    }

    public String toString() {
        return "Placeholder(width=" + ((Object) TextUnit.m5872toStringimpl(this.width)) + ", height=" + ((Object) TextUnit.m5872toStringimpl(this.height)) + ", placeholderVerticalAlign=" + ((Object) PlaceholderVerticalAlign.m5121toStringimpl(this.placeholderVerticalAlign)) + ')';
    }

    private Placeholder(long j10, long j11, int i10) {
        this.width = j10;
        this.height = j11;
        this.placeholderVerticalAlign = i10;
        if (TextUnitKt.m5883isUnspecifiedR2X_6o(j10)) {
            throw new IllegalArgumentException("width cannot be TextUnit.Unspecified");
        }
        if (TextUnitKt.m5883isUnspecifiedR2X_6o(j11)) {
            throw new IllegalArgumentException("height cannot be TextUnit.Unspecified");
        }
    }
}
