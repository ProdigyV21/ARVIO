package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u000b\u0010\u0007\u001a&\u0010\r\u001a\u00020\f2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a*\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0010\u0010\u0007¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "Lx6/n;", "onDraw", "drawBehind", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/draw/CacheDrawScope;", "Landroidx/compose/ui/draw/DrawResult;", "onBuildDrawCache", "drawWithCache", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "CacheDrawModifierNode", "(Lr7/l;)Landroidx/compose/ui/draw/CacheDrawModifierNode;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawWithContent", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawModifierKt {
    public static final CacheDrawModifierNode CacheDrawModifierNode(l<? super CacheDrawScope, DrawResult> lVar) {
        return new CacheDrawModifierNodeImpl(new CacheDrawScope(), lVar);
    }

    public static final Modifier drawBehind(Modifier modifier, l<? super DrawScope, t0> lVar) {
        return modifier.then(new DrawBehindElement(lVar));
    }

    public static final Modifier drawWithCache(Modifier modifier, l<? super CacheDrawScope, DrawResult> lVar) {
        return modifier.then(new DrawWithCacheElement(lVar));
    }

    public static final Modifier drawWithContent(Modifier modifier, l<? super ContentDrawScope, t0> lVar) {
        return modifier.then(new DrawWithContentElement(lVar));
    }
}
