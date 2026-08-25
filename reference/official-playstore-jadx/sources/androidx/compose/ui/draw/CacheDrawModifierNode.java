package androidx.compose.ui.draw;

import androidx.compose.ui.node.DrawModifierNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0001\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/draw/CacheDrawModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Lx6/t0;", "invalidateDrawCache", "()V", "Landroidx/compose/ui/draw/CacheDrawModifierNodeImpl;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface CacheDrawModifierNode extends DrawModifierNode {
    void invalidateDrawCache();
}
