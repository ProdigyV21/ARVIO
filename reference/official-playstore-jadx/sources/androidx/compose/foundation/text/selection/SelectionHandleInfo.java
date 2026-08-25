package androidx.compose.foundation.text.selection;

import a0.c;
import androidx.compose.foundation.text.Handle;
import androidx.compose.ui.geometry.Offset;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0080\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0010\u001a\u00020\u0004HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001f\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\rR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b&\u0010\u0012R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b(\u0010\u0014\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionHandleInfo;", "", "Landroidx/compose/foundation/text/Handle;", "handle", "Landroidx/compose/ui/geometry/Offset;", "position", "Landroidx/compose/foundation/text/selection/SelectionHandleAnchor;", LinkHeader.Parameters.Anchor, "", "visible", "<init>", "(Landroidx/compose/foundation/text/Handle;JLandroidx/compose/foundation/text/selection/SelectionHandleAnchor;ZLkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/foundation/text/Handle;", "component2-F1C5BW0", "()J", "component2", "component3", "()Landroidx/compose/foundation/text/selection/SelectionHandleAnchor;", "component4", "()Z", "copy-ubNVwUQ", "(Landroidx/compose/foundation/text/Handle;JLandroidx/compose/foundation/text/selection/SelectionHandleAnchor;Z)Landroidx/compose/foundation/text/selection/SelectionHandleInfo;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/text/Handle;", "getHandle", "J", "getPosition-F1C5BW0", "Landroidx/compose/foundation/text/selection/SelectionHandleAnchor;", "getAnchor", "Z", "getVisible", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class SelectionHandleInfo {
    public static final int $stable = 0;
    private final SelectionHandleAnchor anchor;
    private final Handle handle;
    private final long position;
    private final boolean visible;

    public /* synthetic */ SelectionHandleInfo(Handle handle, long j10, SelectionHandleAnchor selectionHandleAnchor, boolean z, h hVar) {
        this(handle, j10, selectionHandleAnchor, z);
    }

    /* JADX INFO: renamed from: copy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ SelectionHandleInfo m970copyubNVwUQ$default(SelectionHandleInfo selectionHandleInfo, Handle handle, long j10, SelectionHandleAnchor selectionHandleAnchor, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            handle = selectionHandleInfo.handle;
        }
        if ((i10 & 2) != 0) {
            j10 = selectionHandleInfo.position;
        }
        if ((i10 & 4) != 0) {
            selectionHandleAnchor = selectionHandleInfo.anchor;
        }
        if ((i10 & 8) != 0) {
            z = selectionHandleInfo.visible;
        }
        return selectionHandleInfo.m972copyubNVwUQ(handle, j10, selectionHandleAnchor, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Handle getHandle() {
        return this.handle;
    }

    /* JADX INFO: renamed from: component2-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SelectionHandleAnchor getAnchor() {
        return this.anchor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getVisible() {
        return this.visible;
    }

    /* JADX INFO: renamed from: copy-ubNVwUQ, reason: not valid java name */
    public final SelectionHandleInfo m972copyubNVwUQ(Handle handle, long position, SelectionHandleAnchor anchor, boolean visible) {
        return new SelectionHandleInfo(handle, position, anchor, visible, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectionHandleInfo)) {
            return false;
        }
        SelectionHandleInfo selectionHandleInfo = (SelectionHandleInfo) other;
        return this.handle == selectionHandleInfo.handle && Offset.m3233equalsimpl0(this.position, selectionHandleInfo.position) && this.anchor == selectionHandleInfo.anchor && this.visible == selectionHandleInfo.visible;
    }

    public final SelectionHandleAnchor getAnchor() {
        return this.anchor;
    }

    public final Handle getHandle() {
        return this.handle;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m973getPositionF1C5BW0() {
        return this.position;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public int hashCode() {
        return ((this.anchor.hashCode() + ((Offset.m3238hashCodeimpl(this.position) + (this.handle.hashCode() * 31)) * 31)) * 31) + (this.visible ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SelectionHandleInfo(handle=");
        sb2.append(this.handle);
        sb2.append(", position=");
        sb2.append((Object) Offset.m3244toStringimpl(this.position));
        sb2.append(", anchor=");
        sb2.append(this.anchor);
        sb2.append(", visible=");
        return c.r(sb2, this.visible, ')');
    }

    private SelectionHandleInfo(Handle handle, long j10, SelectionHandleAnchor selectionHandleAnchor, boolean z) {
        this.handle = handle;
        this.position = j10;
        this.anchor = selectionHandleAnchor;
        this.visible = z;
    }
}
