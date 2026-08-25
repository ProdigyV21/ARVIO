package androidx.compose.foundation.text2.input.internal.selection;

import a0.c;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0080\b\u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u0004HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\fJ;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0002HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001d\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\fR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010\u0011R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b%\u0010\f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "", "", "visible", "Landroidx/compose/ui/geometry/Offset;", "position", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "direction", "handlesCrossed", "<init>", "(ZJLandroidx/compose/ui/text/style/ResolvedTextDirection;ZLkotlin/jvm/internal/h;)V", "component1", "()Z", "component2-F1C5BW0", "()J", "component2", "component3", "()Landroidx/compose/ui/text/style/ResolvedTextDirection;", "component4", "copy-ubNVwUQ", "(ZJLandroidx/compose/ui/text/style/ResolvedTextDirection;Z)Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getVisible", "J", "getPosition-F1C5BW0", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getDirection", "getHandlesCrossed", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TextFieldHandleState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TextFieldHandleState Hidden = new TextFieldHandleState(false, Offset.INSTANCE.m3251getUnspecifiedF1C5BW0(), ResolvedTextDirection.Ltr, false, null);
    private final ResolvedTextDirection direction;
    private final boolean handlesCrossed;
    private final long position;
    private final boolean visible;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState$Companion;", "", "()V", "Hidden", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "getHidden", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final TextFieldHandleState getHidden() {
            return TextFieldHandleState.Hidden;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextFieldHandleState(boolean z, long j10, ResolvedTextDirection resolvedTextDirection, boolean z5, h hVar) {
        this(z, j10, resolvedTextDirection, z5);
    }

    /* JADX INFO: renamed from: copy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ TextFieldHandleState m1147copyubNVwUQ$default(TextFieldHandleState textFieldHandleState, boolean z, long j10, ResolvedTextDirection resolvedTextDirection, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = textFieldHandleState.visible;
        }
        if ((i10 & 2) != 0) {
            j10 = textFieldHandleState.position;
        }
        if ((i10 & 4) != 0) {
            resolvedTextDirection = textFieldHandleState.direction;
        }
        if ((i10 & 8) != 0) {
            z5 = textFieldHandleState.handlesCrossed;
        }
        return textFieldHandleState.m1149copyubNVwUQ(z, j10, resolvedTextDirection, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getVisible() {
        return this.visible;
    }

    /* JADX INFO: renamed from: component2-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ResolvedTextDirection getDirection() {
        return this.direction;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getHandlesCrossed() {
        return this.handlesCrossed;
    }

    /* JADX INFO: renamed from: copy-ubNVwUQ, reason: not valid java name */
    public final TextFieldHandleState m1149copyubNVwUQ(boolean visible, long position, ResolvedTextDirection direction, boolean handlesCrossed) {
        return new TextFieldHandleState(visible, position, direction, handlesCrossed, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextFieldHandleState)) {
            return false;
        }
        TextFieldHandleState textFieldHandleState = (TextFieldHandleState) other;
        return this.visible == textFieldHandleState.visible && Offset.m3233equalsimpl0(this.position, textFieldHandleState.position) && this.direction == textFieldHandleState.direction && this.handlesCrossed == textFieldHandleState.handlesCrossed;
    }

    public final ResolvedTextDirection getDirection() {
        return this.direction;
    }

    public final boolean getHandlesCrossed() {
        return this.handlesCrossed;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m1150getPositionF1C5BW0() {
        return this.position;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public int hashCode() {
        return ((this.direction.hashCode() + ((Offset.m3238hashCodeimpl(this.position) + ((this.visible ? 1231 : 1237) * 31)) * 31)) * 31) + (this.handlesCrossed ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TextFieldHandleState(visible=");
        sb2.append(this.visible);
        sb2.append(", position=");
        sb2.append((Object) Offset.m3244toStringimpl(this.position));
        sb2.append(", direction=");
        sb2.append(this.direction);
        sb2.append(", handlesCrossed=");
        return c.r(sb2, this.handlesCrossed, ')');
    }

    private TextFieldHandleState(boolean z, long j10, ResolvedTextDirection resolvedTextDirection, boolean z5) {
        this.visible = z;
        this.position = j10;
        this.direction = resolvedTextDirection;
        this.handlesCrossed = z5;
    }
}
