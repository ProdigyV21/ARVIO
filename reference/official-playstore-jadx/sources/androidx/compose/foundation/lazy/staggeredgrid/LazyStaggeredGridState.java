package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.gestures.f;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.b;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 È\u00012\u00020\u0001:\u0002È\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\nJA\u0010\u0014\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2'\u0010\u0013\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012H\u0096@¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J\"\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\u001a\u0010\u0019J#\u0010\u001d\u001a\u00020\u0010*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010#\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0002H\u0000¢\u0006\u0004\b!\u0010\"J\u0017\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J!\u0010.\u001a\u00020\u00102\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020*H\u0000¢\u0006\u0004\b,\u0010-J\u0017\u00100\u001a\u00020$2\u0006\u0010/\u001a\u00020$H\u0002¢\u0006\u0004\b0\u0010'J!\u00103\u001a\u00020\u00102\u0006\u0010%\u001a\u00020$2\b\b\u0002\u00102\u001a\u000201H\u0002¢\u0006\u0004\b3\u00104J\u001d\u00107\u001a\u00020\u00102\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u000705H\u0002¢\u0006\u0004\b7\u00108J\u0017\u00109\u001a\u00020\u00102\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0004\b9\u0010:J\u001f\u0010=\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u0007H\u0002¢\u0006\u0004\b=\u0010>R\u001a\u0010@\u001a\u00020?8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020(0D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010H\u001a\u00020G8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR+\u0010R\u001a\u00020*2\u0006\u0010L\u001a\u00020*8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010F\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR+\u0010V\u001a\u00020*2\u0006\u0010L\u001a\u00020*8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010F\u001a\u0004\bT\u0010O\"\u0004\bU\u0010QR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR(\u0010[\u001a\u0004\u0018\u00010Z2\b\u0010L\u001a\u0004\u0018\u00010Z8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R\u001a\u0010`\u001a\u00020_8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b`\u0010a\u001a\u0004\bb\u0010cR\u001a\u0010e\u001a\u00020d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\be\u0010f\u001a\u0004\bg\u0010hR\u001a\u0010j\u001a\u00020i8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bj\u0010k\u001a\u0004\bl\u0010mR\"\u0010n\u001a\u00020*8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bn\u0010o\u001a\u0004\bp\u0010O\"\u0004\bq\u0010QR\u001a\u0010s\u001a\u00020r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u0014\u0010w\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010xR$\u0010y\u001a\u00020$2\u0006\u0010L\u001a\u00020$8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\by\u0010z\u001a\u0004\b{\u0010|R%\u0010}\u001a\u00020\u00078\u0000@\u0000X\u0080\u000e¢\u0006\u0015\n\u0004\b}\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R&\u0010\u0083\u0001\u001a\u00020*8\u0000@\u0000X\u0080\u000e¢\u0006\u0015\n\u0005\b\u0083\u0001\u0010o\u001a\u0005\b\u0084\u0001\u0010O\"\u0005\b\u0085\u0001\u0010QR,\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0087\u0001\u0010\u0088\u0001\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R,\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008d\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u008e\u0001\u0010\u008f\u0001\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0018\u0010\u0094\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0094\u0001\u0010~R%\u0010\u0097\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R*\u0010\u009a\u0001\u001a\u00030\u0099\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R \u0010¡\u0001\u001a\u00030 \u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b¡\u0001\u0010¢\u0001\u001a\u0006\b£\u0001\u0010¤\u0001R \u0010¦\u0001\u001a\u00030¥\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¨\u0001\u0010©\u0001R \u0010«\u0001\u001a\u00030ª\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b«\u0001\u0010¬\u0001\u001a\u0006\b\u00ad\u0001\u0010®\u0001R%\u0010°\u0001\u001a\u00030¯\u00018\u0000X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u000f\n\u0005\b°\u0001\u0010F\u001a\u0006\b±\u0001\u0010²\u0001R\u0014\u0010´\u0001\u001a\u00020\u00078F¢\u0006\b\u001a\u0006\b³\u0001\u0010\u0080\u0001R\u0014\u0010¶\u0001\u001a\u00020\u00078F¢\u0006\b\u001a\u0006\bµ\u0001\u0010\u0080\u0001R\u0014\u0010¹\u0001\u001a\u0002018F¢\u0006\b\u001a\u0006\b·\u0001\u0010¸\u0001R\u0016\u0010<\u001a\u00020\u00078@X\u0080\u0004¢\u0006\b\u001a\u0006\bº\u0001\u0010\u0080\u0001R\u0015\u0010¾\u0001\u001a\u00030»\u00018F¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R!\u0010Ä\u0001\u001a\u00030¿\u00018@X\u0080\u0084\u0002¢\u0006\u0010\u001a\u0006\bÀ\u0001\u0010Á\u0001*\u0006\bÂ\u0001\u0010Ã\u0001R\u0016\u0010Å\u0001\u001a\u00020*8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010OR\u0017\u0010Ç\u0001\u001a\u00020\u00078BX\u0082\u0004¢\u0006\b\u001a\u0006\bÆ\u0001\u0010\u0080\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006É\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "", "initialFirstVisibleItems", "initialFirstVisibleOffsets", "<init>", "([I[I)V", "", "initialFirstVisibleItemIndex", "initialFirstVisibleItemOffset", "(II)V", "Landroidx/compose/foundation/MutatePriority;", "scrollPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "block", "scroll", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "index", "scrollOffset", "scrollToItem", "(IILd7/d;)Ljava/lang/Object;", "animateScrollToItem", "snapToItemInternal$foundation_release", "(Landroidx/compose/foundation/gestures/ScrollScope;II)V", "snapToItemInternal", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProvider", "firstItemIndex", "updateScrollPositionIfTheFirstItemWasMoved$foundation_release", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;[I)[I", "updateScrollPositionIfTheFirstItemWasMoved", "", "delta", "dispatchRawDelta", "(F)F", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "result", "", "visibleItemsStayedTheSame", "applyMeasureResult$foundation_release", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;Z)V", "applyMeasureResult", "distance", "onScroll", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "info", "notifyPrefetch", "(FLandroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;)V", "", "prefetchHandlesUsed", "clearLeftoverPrefetchHandles", "(Ljava/util/Set;)V", "cancelPrefetchIfVisibleItemsChanged", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;)V", "itemIndex", "laneCount", "fillNearestIndices", "(II)[I", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScrollPosition;", "scrollPosition", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScrollPosition;", "getScrollPosition$foundation_release", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScrollPosition;", "Landroidx/compose/runtime/MutableState;", "layoutInfoState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "laneInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "getLaneInfo$foundation_release", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "<set-?>", "canScrollForward$delegate", "getCanScrollForward", "()Z", "setCanScrollForward", "(Z)V", "canScrollForward", "canScrollBackward$delegate", "getCanScrollBackward", "setCanScrollBackward", "canScrollBackward", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridAnimateScrollScope;", "animateScrollScope", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridAnimateScrollScope;", "Landroidx/compose/ui/layout/Remeasurement;", "remeasurement", "Landroidx/compose/ui/layout/Remeasurement;", "getRemeasurement$foundation_release", "()Landroidx/compose/ui/layout/Remeasurement;", "Landroidx/compose/ui/layout/RemeasurementModifier;", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation_release", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "awaitLayoutModifier", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "getAwaitLayoutModifier$foundation_release", "()Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "getBeyondBoundsInfo$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "prefetchingEnabled", "Z", "getPrefetchingEnabled$foundation_release", "setPrefetchingEnabled$foundation_release", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getPrefetchState$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "scrollableState", "Landroidx/compose/foundation/gestures/ScrollableState;", "scrollToBeConsumed", "F", "getScrollToBeConsumed$foundation_release", "()F", "measurePassCount", "I", "getMeasurePassCount$foundation_release", "()I", "setMeasurePassCount$foundation_release", "(I)V", "isVertical", "isVertical$foundation_release", "setVertical$foundation_release", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "getSlots$foundation_release", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "setSlots$foundation_release", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;)V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "spanProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "getSpanProvider$foundation_release", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "setSpanProvider$foundation_release", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;)V", "prefetchBaseIndex", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "currentItemPrefetchHandles", "Ljava/util/Map;", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "mutableInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getMutableInteractionSource$foundation_release", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "pinnedItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "getPinnedItems$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "placementAnimator", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "getPlacementAnimator$foundation_release", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "getPlacementScopeInvalidator-zYiylxw$foundation_release", "()Landroidx/compose/runtime/MutableState;", "getFirstVisibleItemIndex", "firstVisibleItemIndex", "getFirstVisibleItemScrollOffset", "firstVisibleItemScrollOffset", "getLayoutInfo", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "layoutInfo", "getLaneCount$foundation_release", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Lx7/i;", "getNearestRange$foundation_release", "()Lx7/i;", "getNearestRange$foundation_release$delegate", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;)Ljava/lang/Object;", "nearestRange", "isScrollInProgress", "getNumOfItemsToTeleport", "numOfItemsToTeleport", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridState implements ScrollableState {
    private final LazyStaggeredGridAnimateScrollScope animateScrollScope;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollBackward;

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollForward;
    private final Map<Integer, LazyLayoutPrefetchState.PrefetchHandle> currentItemPrefetchHandles;
    private Density density;
    private boolean isVertical;
    private final LazyStaggeredGridLaneInfo laneInfo;
    private final MutableState<LazyStaggeredGridMeasureResult> layoutInfoState;
    private int measurePassCount;
    private final MutableInteractionSource mutableInteractionSource;
    private final LazyLayoutPinnedItemList pinnedItems;
    private final LazyStaggeredGridItemPlacementAnimator placementAnimator;
    private final MutableState<t0> placementScopeInvalidator;
    private int prefetchBaseIndex;
    private final LazyLayoutPrefetchState prefetchState;
    private boolean prefetchingEnabled;
    private Remeasurement remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final LazyStaggeredGridScrollPosition scrollPosition;
    private float scrollToBeConsumed;
    private final ScrollableState scrollableState;
    private LazyStaggeredGridSlots slots;
    private LazyStaggeredGridSpanProvider spanProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Saver<LazyStaggeredGridState, Object> Saver = ListSaverKt.listSaver(LazyStaggeredGridState$Companion$Saver$1.INSTANCE, LazyStaggeredGridState$Companion$Saver$2.INSTANCE);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<LazyStaggeredGridState, Object> getSaver() {
            return LazyStaggeredGridState.Saver;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scroll$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState", f = "LazyStaggeredGridState.kt", l = {227, 228}, m = "scroll")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LazyStaggeredGridState.this.scroll(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scrollToItem$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scrollToItem$2", f = "LazyStaggeredGridState.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ScrollScope, d<? super t0>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ int $scrollOffset;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, int i11, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$index = i10;
            this.$scrollOffset = i11;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = LazyStaggeredGridState.this.new AnonymousClass2(this.$index, this.$scrollOffset, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            LazyStaggeredGridState.this.snapToItemInternal$foundation_release((ScrollScope) this.L$0, this.$index, this.$scrollOffset);
            return t0.f22605a;
        }
    }

    public /* synthetic */ LazyStaggeredGridState(int[] iArr, int[] iArr2, h hVar) {
        this(iArr, iArr2);
    }

    public static /* synthetic */ Object animateScrollToItem$default(LazyStaggeredGridState lazyStaggeredGridState, int i10, int i11, d dVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        return lazyStaggeredGridState.animateScrollToItem(i10, i11, dVar);
    }

    public static /* synthetic */ void applyMeasureResult$foundation_release$default(LazyStaggeredGridState lazyStaggeredGridState, LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResult, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        lazyStaggeredGridState.applyMeasureResult$foundation_release(lazyStaggeredGridMeasureResult, z);
    }

    private final void cancelPrefetchIfVisibleItemsChanged(LazyStaggeredGridLayoutInfo info) {
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = info.getVisibleItemsInfo();
        if (this.prefetchBaseIndex == -1 || visibleItemsInfo.isEmpty()) {
            return;
        }
        int index = ((LazyStaggeredGridItemInfo) x.m0(visibleItemsInfo)).getIndex();
        int index2 = ((LazyStaggeredGridItemInfo) x.w0(visibleItemsInfo)).getIndex();
        int i10 = this.prefetchBaseIndex;
        if (index > i10 || i10 > index2) {
            this.prefetchBaseIndex = -1;
            Iterator<T> it = this.currentItemPrefetchHandles.values().iterator();
            while (it.hasNext()) {
                ((LazyLayoutPrefetchState.PrefetchHandle) it.next()).cancel();
            }
            this.currentItemPrefetchHandles.clear();
        }
    }

    private final void clearLeftoverPrefetchHandles(Set<Integer> prefetchHandlesUsed) {
        Iterator<Map.Entry<Integer, LazyLayoutPrefetchState.PrefetchHandle>> it = this.currentItemPrefetchHandles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, LazyLayoutPrefetchState.PrefetchHandle> next = it.next();
            if (!prefetchHandlesUsed.contains(next.getKey())) {
                next.getValue().cancel();
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] fillNearestIndices(int itemIndex, int laneCount) {
        int[] iArr = new int[laneCount];
        LazyStaggeredGridSpanProvider lazyStaggeredGridSpanProvider = this.spanProvider;
        int iMin = 0;
        if (lazyStaggeredGridSpanProvider != null && lazyStaggeredGridSpanProvider.isFullSpan(itemIndex)) {
            r.U(iArr, itemIndex, 0, 6);
            return iArr;
        }
        this.laneInfo.ensureValidIndex(itemIndex + laneCount);
        int lane = this.laneInfo.getLane(itemIndex);
        if (lane != -2 && lane != -1) {
            if (lane < 0) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.o(lane, "Expected positive lane number, got ", " instead.").toString());
            }
            iMin = Math.min(lane, laneCount);
        }
        int i10 = iMin - 1;
        int iFindPreviousItemIndex = itemIndex;
        while (true) {
            if (-1 >= i10) {
                break;
            }
            iFindPreviousItemIndex = this.laneInfo.findPreviousItemIndex(iFindPreviousItemIndex, i10);
            iArr[i10] = iFindPreviousItemIndex;
            if (iFindPreviousItemIndex == -1) {
                r.U(iArr, -1, i10, 2);
                break;
            }
            i10--;
        }
        iArr[iMin] = itemIndex;
        for (int i11 = iMin + 1; i11 < laneCount; i11++) {
            itemIndex = this.laneInfo.findNextItemIndex(itemIndex, i11);
            iArr[i11] = itemIndex;
        }
        return iArr;
    }

    private final int getNumOfItemsToTeleport() {
        return getLaneCount$foundation_release() * 100;
    }

    private final void notifyPrefetch(float delta, LazyStaggeredGridLayoutInfo info) {
        int i10;
        if (!this.prefetchingEnabled || info.getVisibleItemsInfo().isEmpty()) {
            return;
        }
        boolean z = delta < 0.0f;
        int index = z ? ((LazyStaggeredGridItemInfo) x.w0(info.getVisibleItemsInfo())).getIndex() : ((LazyStaggeredGridItemInfo) x.m0(info.getVisibleItemsInfo())).getIndex();
        if (index == this.prefetchBaseIndex) {
            return;
        }
        this.prefetchBaseIndex = index;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int laneCount$foundation_release = getLaneCount$foundation_release();
        for (int i11 = 0; i11 < laneCount$foundation_release; i11++) {
            index = z ? this.laneInfo.findNextItemIndex(index, i11) : this.laneInfo.findPreviousItemIndex(index, i11);
            if (index < 0 || index >= info.getTotalItemsCount() || linkedHashSet.contains(Integer.valueOf(index))) {
                break;
            }
            linkedHashSet.add(Integer.valueOf(index));
            if (!this.currentItemPrefetchHandles.containsKey(Integer.valueOf(index))) {
                LazyStaggeredGridSpanProvider lazyStaggeredGridSpanProvider = this.spanProvider;
                boolean z5 = lazyStaggeredGridSpanProvider != null && lazyStaggeredGridSpanProvider.isFullSpan(index);
                int i12 = z5 ? 0 : i11;
                int laneCount$foundation_release2 = z5 ? getLaneCount$foundation_release() : 1;
                LazyStaggeredGridSlots lazyStaggeredGridSlots = this.slots;
                if (lazyStaggeredGridSlots == null) {
                    i10 = 0;
                } else if (laneCount$foundation_release2 == 1) {
                    i10 = lazyStaggeredGridSlots.getSizes()[i12];
                } else {
                    int i13 = lazyStaggeredGridSlots.getPositions()[i12];
                    int i14 = (i12 + laneCount$foundation_release2) - 1;
                    i10 = (lazyStaggeredGridSlots.getPositions()[i14] + lazyStaggeredGridSlots.getSizes()[i14]) - i13;
                }
                this.currentItemPrefetchHandles.put(Integer.valueOf(index), this.prefetchState.m692schedulePrefetch0kLqBqw(index, this.isVertical ? Constraints.INSTANCE.m5656fixedWidthOenEA2s(i10) : Constraints.INSTANCE.m5655fixedHeightOenEA2s(i10)));
            }
        }
        clearLeftoverPrefetchHandles(linkedHashSet);
    }

    public static /* synthetic */ void notifyPrefetch$default(LazyStaggeredGridState lazyStaggeredGridState, float f10, LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lazyStaggeredGridLayoutInfo = lazyStaggeredGridState.layoutInfoState.getValue();
        }
        lazyStaggeredGridState.notifyPrefetch(f10, lazyStaggeredGridLayoutInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float onScroll(float distance) {
        if ((distance < 0.0f && !getCanScrollForward()) || (distance > 0.0f && !getCanScrollBackward())) {
            return 0.0f;
        }
        if (Math.abs(this.scrollToBeConsumed) > 0.5f) {
            throw new IllegalStateException(("entered drag with non-zero pending scroll: " + this.scrollToBeConsumed).toString());
        }
        float f10 = this.scrollToBeConsumed + distance;
        this.scrollToBeConsumed = f10;
        if (Math.abs(f10) > 0.5f) {
            LazyStaggeredGridMeasureResult value = this.layoutInfoState.getValue();
            float f11 = this.scrollToBeConsumed;
            if (value.tryToApplyScrollWithoutRemeasure(t7.a.M(f11))) {
                applyMeasureResult$foundation_release(value, true);
                ObservableScopeInvalidator.m702invalidateScopeimpl(this.placementScopeInvalidator);
                notifyPrefetch(f11 - this.scrollToBeConsumed, value);
            } else {
                Remeasurement remeasurement = this.remeasurement;
                if (remeasurement != null) {
                    remeasurement.forceRemeasure();
                }
                notifyPrefetch$default(this, f11 - this.scrollToBeConsumed, null, 2, null);
            }
        }
        if (Math.abs(this.scrollToBeConsumed) <= 0.5f) {
            return distance;
        }
        float f12 = distance - this.scrollToBeConsumed;
        this.scrollToBeConsumed = 0.0f;
        return f12;
    }

    public static /* synthetic */ Object scrollToItem$default(LazyStaggeredGridState lazyStaggeredGridState, int i10, int i11, d dVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        return lazyStaggeredGridState.scrollToItem(i10, i11, dVar);
    }

    private void setCanScrollBackward(boolean z) {
        this.canScrollBackward.setValue(Boolean.valueOf(z));
    }

    private void setCanScrollForward(boolean z) {
        this.canScrollForward.setValue(Boolean.valueOf(z));
    }

    public final Object animateScrollToItem(int i10, int i11, d<? super t0> dVar) {
        Object objAnimateScrollToItem = LazyAnimateScrollKt.animateScrollToItem(this.animateScrollScope, i10, i11, getNumOfItemsToTeleport(), this.density, dVar);
        return objAnimateScrollToItem == e7.a.f15033i ? objAnimateScrollToItem : t0.f22605a;
    }

    public final void applyMeasureResult$foundation_release(LazyStaggeredGridMeasureResult result, boolean visibleItemsStayedTheSame) {
        this.scrollToBeConsumed -= result.getConsumedScroll();
        this.layoutInfoState.setValue(result);
        if (visibleItemsStayedTheSame) {
            this.scrollPosition.updateScrollOffset(result.getFirstVisibleItemScrollOffsets());
        } else {
            this.scrollPosition.updateFromMeasureResult(result);
            cancelPrefetchIfVisibleItemsChanged(result);
        }
        setCanScrollBackward(result.getCanScrollBackward());
        setCanScrollForward(result.getCanScrollForward());
        this.measurePassCount++;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.scrollableState.dispatchRawDelta(delta);
    }

    /* JADX INFO: renamed from: getAwaitLayoutModifier$foundation_release, reason: from getter */
    public final AwaitFirstLayoutModifier getAwaitLayoutModifier() {
        return this.awaitLayoutModifier;
    }

    /* JADX INFO: renamed from: getBeyondBoundsInfo$foundation_release, reason: from getter */
    public final LazyLayoutBeyondBoundsInfo getBeyondBoundsInfo() {
        return this.beyondBoundsInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final int getFirstVisibleItemIndex() {
        return this.scrollPosition.getIndex();
    }

    public final int getFirstVisibleItemScrollOffset() {
        return this.scrollPosition.getScrollOffset();
    }

    public final InteractionSource getInteractionSource() {
        return this.mutableInteractionSource;
    }

    public final int getLaneCount$foundation_release() {
        int[] sizes;
        LazyStaggeredGridSlots lazyStaggeredGridSlots = this.slots;
        if (lazyStaggeredGridSlots == null || (sizes = lazyStaggeredGridSlots.getSizes()) == null) {
            return 0;
        }
        return sizes.length;
    }

    /* JADX INFO: renamed from: getLaneInfo$foundation_release, reason: from getter */
    public final LazyStaggeredGridLaneInfo getLaneInfo() {
        return this.laneInfo;
    }

    public final LazyStaggeredGridLayoutInfo getLayoutInfo() {
        return this.layoutInfoState.getValue();
    }

    /* JADX INFO: renamed from: getMeasurePassCount$foundation_release, reason: from getter */
    public final int getMeasurePassCount() {
        return this.measurePassCount;
    }

    /* JADX INFO: renamed from: getMutableInteractionSource$foundation_release, reason: from getter */
    public final MutableInteractionSource getMutableInteractionSource() {
        return this.mutableInteractionSource;
    }

    public final i getNearestRange$foundation_release() {
        return this.scrollPosition.getNearestRangeState().getValue();
    }

    /* JADX INFO: renamed from: getPinnedItems$foundation_release, reason: from getter */
    public final LazyLayoutPinnedItemList getPinnedItems() {
        return this.pinnedItems;
    }

    /* JADX INFO: renamed from: getPlacementAnimator$foundation_release, reason: from getter */
    public final LazyStaggeredGridItemPlacementAnimator getPlacementAnimator() {
        return this.placementAnimator;
    }

    /* JADX INFO: renamed from: getPlacementScopeInvalidator-zYiylxw$foundation_release, reason: not valid java name */
    public final MutableState<t0> m735getPlacementScopeInvalidatorzYiylxw$foundation_release() {
        return this.placementScopeInvalidator;
    }

    /* JADX INFO: renamed from: getPrefetchState$foundation_release, reason: from getter */
    public final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    /* JADX INFO: renamed from: getPrefetchingEnabled$foundation_release, reason: from getter */
    public final boolean getPrefetchingEnabled() {
        return this.prefetchingEnabled;
    }

    /* JADX INFO: renamed from: getRemeasurement$foundation_release, reason: from getter */
    public final Remeasurement getRemeasurement() {
        return this.remeasurement;
    }

    /* JADX INFO: renamed from: getRemeasurementModifier$foundation_release, reason: from getter */
    public final RemeasurementModifier getRemeasurementModifier() {
        return this.remeasurementModifier;
    }

    /* JADX INFO: renamed from: getScrollPosition$foundation_release, reason: from getter */
    public final LazyStaggeredGridScrollPosition getScrollPosition() {
        return this.scrollPosition;
    }

    /* JADX INFO: renamed from: getScrollToBeConsumed$foundation_release, reason: from getter */
    public final float getScrollToBeConsumed() {
        return this.scrollToBeConsumed;
    }

    /* JADX INFO: renamed from: getSlots$foundation_release, reason: from getter */
    public final LazyStaggeredGridSlots getSlots() {
        return this.slots;
    }

    /* JADX INFO: renamed from: getSpanProvider$foundation_release, reason: from getter */
    public final LazyStaggeredGridSpanProvider getSpanProvider() {
        return this.spanProvider;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    /* JADX INFO: renamed from: isVertical$foundation_release, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0067, code lost:
    
        if (r8.scroll(r6, r7, r0) == r4) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object scroll(androidx.compose.foundation.MutatePriority r6, r7.p<? super androidx.compose.foundation.gestures.ScrollScope, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scroll$1 r0 = (androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scroll$1 r0 = new androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$scroll$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L43
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L6a
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$2
            r7 = r6
            r7.p r7 = (r7.p) r7
            java.lang.Object r6 = r0.L$1
            androidx.compose.foundation.MutatePriority r6 = (androidx.compose.foundation.MutatePriority) r6
            java.lang.Object r1 = r0.L$0
            androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState r1 = (androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState) r1
            k2.c.G(r8)
            goto L58
        L43:
            k2.c.G(r8)
            androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier r8 = r5.awaitLayoutModifier
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r8.waitForFirstLayout(r0)
            if (r8 != r4) goto L57
            goto L69
        L57:
            r1 = r5
        L58:
            androidx.compose.foundation.gestures.ScrollableState r8 = r1.scrollableState
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r1
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r6 = r8.scroll(r6, r7, r0)
            if (r6 != r4) goto L6a
        L69:
            return r4
        L6a:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState.scroll(androidx.compose.foundation.MutatePriority, r7.p, d7.d):java.lang.Object");
    }

    public final Object scrollToItem(int i10, int i11, d<? super t0> dVar) {
        Object objE = f.e(this, null, new AnonymousClass2(i10, i11, null), dVar, 1, null);
        return objE == e7.a.f15033i ? objE : t0.f22605a;
    }

    public final void setDensity$foundation_release(Density density) {
        this.density = density;
    }

    public final void setMeasurePassCount$foundation_release(int i10) {
        this.measurePassCount = i10;
    }

    public final void setPrefetchingEnabled$foundation_release(boolean z) {
        this.prefetchingEnabled = z;
    }

    public final void setSlots$foundation_release(LazyStaggeredGridSlots lazyStaggeredGridSlots) {
        this.slots = lazyStaggeredGridSlots;
    }

    public final void setSpanProvider$foundation_release(LazyStaggeredGridSpanProvider lazyStaggeredGridSpanProvider) {
        this.spanProvider = lazyStaggeredGridSpanProvider;
    }

    public final void setVertical$foundation_release(boolean z) {
        this.isVertical = z;
    }

    public final void snapToItemInternal$foundation_release(ScrollScope scrollScope, int i10, int i11) {
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfoFindVisibleItem = LazyStaggeredGridMeasureResultKt.findVisibleItem(getLayoutInfo(), i10);
        if (lazyStaggeredGridItemInfoFindVisibleItem != null) {
            boolean z = this.isVertical;
            long offset = lazyStaggeredGridItemInfoFindVisibleItem.getOffset();
            scrollScope.scrollBy((z ? IntOffset.m5803getYimpl(offset) : IntOffset.m5802getXimpl(offset)) + i11);
        } else {
            this.scrollPosition.requestPosition(i10, i11);
            Remeasurement remeasurement = this.remeasurement;
            if (remeasurement != null) {
                remeasurement.forceRemeasure();
            }
        }
    }

    public final int[] updateScrollPositionIfTheFirstItemWasMoved$foundation_release(LazyLayoutItemProvider itemProvider, int[] firstItemIndex) {
        return this.scrollPosition.updateScrollPositionIfTheFirstItemWasMoved(itemProvider, firstItemIndex);
    }

    private LazyStaggeredGridState(int[] iArr, int[] iArr2) {
        LazyStaggeredGridScrollPosition lazyStaggeredGridScrollPosition = new LazyStaggeredGridScrollPosition(iArr, iArr2, new LazyStaggeredGridState$scrollPosition$1(this));
        this.scrollPosition = lazyStaggeredGridScrollPosition;
        this.layoutInfoState = SnapshotStateKt.mutableStateOf(LazyStaggeredGridMeasureResultKt.getEmptyLazyStaggeredGridLayoutInfo(), SnapshotStateKt.neverEqualPolicy());
        this.laneInfo = new LazyStaggeredGridLaneInfo();
        Boolean bool = Boolean.FALSE;
        this.canScrollForward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.canScrollBackward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.animateScrollScope = new LazyStaggeredGridAnimateScrollScope(this);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState$remeasurementModifier$1
            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public final /* synthetic */ boolean all(l lVar) {
                return b.a(this, lVar);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public final /* synthetic */ boolean any(l lVar) {
                return b.b(this, lVar);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public final /* synthetic */ Object foldIn(Object obj, p pVar) {
                return b.c(this, obj, pVar);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public final /* synthetic */ Object foldOut(Object obj, p pVar) {
                return b.d(this, obj, pVar);
            }

            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public void onRemeasurementAvailable(Remeasurement remeasurement) {
                this.this$0.remeasurement = remeasurement;
            }

            @Override // androidx.compose.ui.Modifier
            public final /* synthetic */ Modifier then(Modifier modifier) {
                return androidx.compose.ui.a.a(this, modifier);
            }
        };
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.prefetchingEnabled = true;
        this.prefetchState = new LazyLayoutPrefetchState();
        this.scrollableState = ScrollableStateKt.ScrollableState(new LazyStaggeredGridState$scrollableState$1(this));
        this.prefetchBaseIndex = -1;
        this.currentItemPrefetchHandles = new LinkedHashMap();
        this.density = DensityKt.Density(1.0f, 1.0f);
        this.mutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.pinnedItems = new LazyLayoutPinnedItemList();
        this.placementAnimator = new LazyStaggeredGridItemPlacementAnimator();
        lazyStaggeredGridScrollPosition.getNearestRangeState();
        this.placementScopeInvalidator = ObservableScopeInvalidator.m698constructorimpl$default(null, 1, null);
    }

    public /* synthetic */ LazyStaggeredGridState(int i10, int i11, int i12, h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11);
    }

    public LazyStaggeredGridState(int i10, int i11) {
        this(new int[]{i10}, new int[]{i11});
    }
}
