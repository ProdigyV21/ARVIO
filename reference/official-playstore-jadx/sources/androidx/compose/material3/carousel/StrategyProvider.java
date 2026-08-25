package androidx.compose.material3.carousel;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/material3/carousel/StrategyProvider;", "", "createStrategy", "Landroidx/compose/material3/carousel/Strategy;", "density", "Landroidx/compose/ui/unit/Density;", "carouselMainAxisSize", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface StrategyProvider {
    Strategy createStrategy(Density density, float carouselMainAxisSize);
}
