package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TooltipKt$TooltipBox$scope$1$1$drawCaret$1 extends r implements l<CacheDrawScope, DrawResult> {
    final /* synthetic */ MutableState<LayoutCoordinates> $anchorBounds$delegate;
    final /* synthetic */ p<CacheDrawScope, LayoutCoordinates, DrawResult> $draw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TooltipKt$TooltipBox$scope$1$1$drawCaret$1(p<? super CacheDrawScope, ? super LayoutCoordinates, DrawResult> pVar, MutableState<LayoutCoordinates> mutableState) {
        super(1);
        this.$draw = pVar;
        this.$anchorBounds$delegate = mutableState;
    }

    @Override // r7.l
    public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
        return (DrawResult) this.$draw.invoke(cacheDrawScope, TooltipKt.TooltipBox$lambda$1(this.$anchorBounds$delegate));
    }
}
