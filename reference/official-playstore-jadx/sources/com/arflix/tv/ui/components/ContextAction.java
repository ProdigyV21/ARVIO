package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0016\u0010\fJ\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010\u000fR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010\u0011¨\u0006%"}, d2 = {"Lcom/arflix/tv/ui/components/ContextAction;", "", "", TtmlNode.ATTR_ID, "label", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLkotlin/jvm/internal/h;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component4-0d7_KjU", "()J", "component4", "copy-g2O1Hgs", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;J)Lcom/arflix/tv/ui/components/ContextAction;", "copy", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getLabel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getIcon", "J", "getColor-0d7_KjU", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ContextAction {
    public static final int $stable = 0;
    private final long color;
    private final ImageVector icon;
    private final String id;
    private final String label;

    public /* synthetic */ ContextAction(String str, String str2, ImageVector imageVector, long j10, kotlin.jvm.internal.h hVar) {
        this(str, str2, imageVector, j10);
    }

    /* JADX INFO: renamed from: copy-g2O1Hgs$default, reason: not valid java name */
    public static /* synthetic */ ContextAction m6155copyg2O1Hgs$default(ContextAction contextAction, String str, String str2, ImageVector imageVector, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = contextAction.id;
        }
        if ((i10 & 2) != 0) {
            str2 = contextAction.label;
        }
        if ((i10 & 4) != 0) {
            imageVector = contextAction.icon;
        }
        if ((i10 & 8) != 0) {
            j10 = contextAction.color;
        }
        ImageVector imageVector2 = imageVector;
        return contextAction.m6157copyg2O1Hgs(str, str2, imageVector2, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: copy-g2O1Hgs, reason: not valid java name */
    public final ContextAction m6157copyg2O1Hgs(String id, String label, ImageVector icon, long color) {
        return new ContextAction(id, label, icon, color, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextAction)) {
            return false;
        }
        ContextAction contextAction = (ContextAction) other;
        return kotlin.jvm.internal.p.a(this.id, contextAction.id) && kotlin.jvm.internal.p.a(this.label, contextAction.label) && kotlin.jvm.internal.p.a(this.icon, contextAction.icon) && Color.m3473equalsimpl0(this.color, contextAction.color);
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m6158getColor0d7_KjU() {
        return this.color;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.color) + ((this.icon.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.label)) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.label;
        ImageVector imageVector = this.icon;
        String strM3480toStringimpl = Color.m3480toStringimpl(this.color);
        StringBuilder sbR = androidx.fragment.app.a2.r("ContextAction(id=", str, ", label=", str2, ", icon=");
        sbR.append(imageVector);
        sbR.append(", color=");
        sbR.append(strM3480toStringimpl);
        sbR.append(")");
        return sbR.toString();
    }

    private ContextAction(String str, String str2, ImageVector imageVector, long j10) {
        this.id = str;
        this.label = str2;
        this.icon = imageVector;
        this.color = j10;
    }

    public /* synthetic */ ContextAction(String str, String str2, ImageVector imageVector, long j10, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, imageVector, (i10 & 8) != 0 ? ColorKt.getTextPrimary() : j10, null);
    }
}
