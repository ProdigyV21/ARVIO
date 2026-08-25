package com.arflix.tv.ui.components;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/components/SourceAddonTab;", "", TtmlNode.ATTR_ID, "", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLabel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SourceAddonTab {
    public static final int $stable = 0;
    private final String id;
    private final String label;

    public SourceAddonTab(String str, String str2) {
        this.id = str;
        this.label = str2;
    }

    public static /* synthetic */ SourceAddonTab copy$default(SourceAddonTab sourceAddonTab, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = sourceAddonTab.id;
        }
        if ((i10 & 2) != 0) {
            str2 = sourceAddonTab.label;
        }
        return sourceAddonTab.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final SourceAddonTab copy(String id, String label) {
        return new SourceAddonTab(id, label);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceAddonTab)) {
            return false;
        }
        SourceAddonTab sourceAddonTab = (SourceAddonTab) other;
        return kotlin.jvm.internal.p.a(this.id, sourceAddonTab.id) && kotlin.jvm.internal.p.a(this.label, sourceAddonTab.label);
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return this.label.hashCode() + (this.id.hashCode() * 31);
    }

    public String toString() {
        return androidx.fragment.app.a2.m("SourceAddonTab(id=", this.id, ", label=", this.label, ")");
    }
}
