package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "", TtmlNode.COMBINE_ALL, "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "tree", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "index", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "<init>", "(Ljava/util/List;Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;)V", "getAll", "()Ljava/util/List;", "getTree", "()Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "getIndex", "()Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EnrichedChannels {
    private static final EnrichedChannels Empty;
    private final List<EnrichedChannel> all;
    private final LiveCategoryIndex index;
    private final LiveCategoryTree tree;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels$Companion;", "", "<init>", "()V", "Empty", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "getEmpty", "()Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final EnrichedChannels getEmpty() {
            return EnrichedChannels.Empty;
        }

        private Companion() {
        }
    }

    static {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        Empty = new EnrichedChannels(zVar, new LiveCategoryTree(zVar, new LiveSection("global", "GLOBAL", zVar), new LiveSection("countries", "COUNTRIES", zVar), new LiveSection("adult", "ADULT", zVar), null, 16, null), null, 4, null);
    }

    public EnrichedChannels(List<EnrichedChannel> list, LiveCategoryTree liveCategoryTree, LiveCategoryIndex liveCategoryIndex) {
        this.all = list;
        this.tree = liveCategoryTree;
        this.index = liveCategoryIndex;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EnrichedChannels copy$default(EnrichedChannels enrichedChannels, List list, LiveCategoryTree liveCategoryTree, LiveCategoryIndex liveCategoryIndex, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = enrichedChannels.all;
        }
        if ((i10 & 2) != 0) {
            liveCategoryTree = enrichedChannels.tree;
        }
        if ((i10 & 4) != 0) {
            liveCategoryIndex = enrichedChannels.index;
        }
        return enrichedChannels.copy(list, liveCategoryTree, liveCategoryIndex);
    }

    public final List<EnrichedChannel> component1() {
        return this.all;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LiveCategoryTree getTree() {
        return this.tree;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final LiveCategoryIndex getIndex() {
        return this.index;
    }

    public final EnrichedChannels copy(List<EnrichedChannel> all, LiveCategoryTree tree, LiveCategoryIndex index) {
        return new EnrichedChannels(all, tree, index);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnrichedChannels)) {
            return false;
        }
        EnrichedChannels enrichedChannels = (EnrichedChannels) other;
        return kotlin.jvm.internal.p.a(this.all, enrichedChannels.all) && kotlin.jvm.internal.p.a(this.tree, enrichedChannels.tree) && kotlin.jvm.internal.p.a(this.index, enrichedChannels.index);
    }

    public final List<EnrichedChannel> getAll() {
        return this.all;
    }

    public final LiveCategoryIndex getIndex() {
        return this.index;
    }

    public final LiveCategoryTree getTree() {
        return this.tree;
    }

    public int hashCode() {
        return this.index.hashCode() + ((this.tree.hashCode() + (this.all.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "EnrichedChannels(all=" + this.all + ", tree=" + this.tree + ", index=" + this.index + ")";
    }

    public /* synthetic */ EnrichedChannels(List list, LiveCategoryTree liveCategoryTree, LiveCategoryIndex liveCategoryIndex, int i10, kotlin.jvm.internal.h hVar) {
        this(list, liveCategoryTree, (i10 & 4) != 0 ? LiveCategoryIndex.INSTANCE.getEmpty() : liveCategoryIndex);
    }
}
