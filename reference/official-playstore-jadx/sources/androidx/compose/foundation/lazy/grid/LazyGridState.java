package androidx.compose.foundation.lazy.grid;

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
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.b;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 ¸\u00012\u00020\u0001:\u0002¸\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\f\u0010\u0006JA\u0010\u0016\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e2'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0010¢\u0006\u0002\b\u0014H\u0096@¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0000¢\u0006\u0004\b\u001d\u0010\u001bJ$\u0010\u001f\u001a\u00020\t2\b\b\u0001\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0086@¢\u0006\u0004\b\u001f\u0010\u000bJ!\u0010&\u001a\u00020\t2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"H\u0000¢\u0006\u0004\b$\u0010%J\u001f\u0010,\u001a\u00020\u00022\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u0002H\u0000¢\u0006\u0004\b*\u0010+J!\u0010/\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010.\u001a\u00020-H\u0002¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\t2\u0006\u00101\u001a\u00020-H\u0002¢\u0006\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020 078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u001a\u0010;\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R$\u0010@\u001a\u00020\u00182\u0006\u0010?\u001a\u00020\u00188\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR+\u0010J\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u00028@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\"\u0010L\u001a\u00020K8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\"\u0010R\u001a\u00020\"8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR$\u0010Z\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u00028\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010GR\"\u0010]\u001a\u00020\"8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b]\u0010S\u001a\u0004\b^\u0010U\"\u0004\b_\u0010WR\u0016\u0010`\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010[R\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020b0a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010SR$\u0010g\u001a\u0004\u0018\u00010f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010n\u001a\u00020m8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bn\u0010o\u001a\u0004\bp\u0010qR\u001a\u0010s\u001a\u00020r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u0089\u0001\u0010\u0083\u0001\u001a/\u0012\u0013\u0012\u00110\u0002¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020}0|0{0w23\u0010?\u001a/\u0012\u0013\u0012\u00110\u0002¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020}0|0{0w8@@@X\u0080\u008e\u0002¢\u0006\u0015\n\u0004\b~\u00109\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R \u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R \u0010\u0092\u0001\u001a\u00030\u0091\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R%\u0010\u0097\u0001\u001a\u00030\u0096\u00018\u0000X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u000f\n\u0005\b\u0097\u0001\u00109\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R/\u0010\u009d\u0001\u001a\u00020\"2\u0006\u0010?\u001a\u00020\"8V@RX\u0096\u008e\u0002¢\u0006\u0015\n\u0005\b\u009a\u0001\u00109\u001a\u0005\b\u009b\u0001\u0010U\"\u0005\b\u009c\u0001\u0010WR/\u0010¡\u0001\u001a\u00020\"2\u0006\u0010?\u001a\u00020\"8V@RX\u0096\u008e\u0002¢\u0006\u0015\n\u0005\b\u009e\u0001\u00109\u001a\u0005\b\u009f\u0001\u0010U\"\u0005\b \u0001\u0010WR \u0010£\u0001\u001a\u00030¢\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b£\u0001\u0010¤\u0001\u001a\u0006\b¥\u0001\u0010¦\u0001R\u0012\u0010\u0003\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b§\u0001\u0010GR\u0012\u0010\u0004\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b¨\u0001\u0010GR\u0013\u0010.\u001a\u00020-8F¢\u0006\b\u001a\u0006\b©\u0001\u0010ª\u0001R\u0015\u0010®\u0001\u001a\u00030«\u00018F¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001R!\u0010´\u0001\u001a\u00030¯\u00018@X\u0080\u0084\u0002¢\u0006\u0010\u001a\u0006\b°\u0001\u0010±\u0001*\u0006\b²\u0001\u0010³\u0001R\u0016\u0010µ\u0001\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bµ\u0001\u0010UR\u0016\u0010·\u0001\u001a\u00020\u00028BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¶\u0001\u0010G\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006¹\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "<init>", "(II)V", "index", "scrollOffset", "Lx6/t0;", "scrollToItem", "(IILd7/d;)Ljava/lang/Object;", "snapToItemIndexInternal$foundation_release", "snapToItemIndexInternal", "Landroidx/compose/foundation/MutatePriority;", "scrollPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Ld7/d;", "", "Lx6/n;", "block", "scroll", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "", "delta", "dispatchRawDelta", "(F)F", "distance", "onScroll$foundation_release", "onScroll", "animateScrollToItem", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "result", "", "visibleItemsStayedTheSame", "applyMeasureResult$foundation_release", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;Z)V", "applyMeasureResult", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "itemProvider", "firstItemIndex", "updateScrollPositionIfTheFirstItemWasMoved$foundation_release", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;I)I", "updateScrollPositionIfTheFirstItemWasMoved", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "layoutInfo", "notifyPrefetch", "(FLandroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)V", "info", "cancelPrefetchIfVisibleItemsChanged", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)V", "Landroidx/compose/foundation/lazy/grid/LazyGridScrollPosition;", "scrollPosition", "Landroidx/compose/foundation/lazy/grid/LazyGridScrollPosition;", "Landroidx/compose/runtime/MutableState;", "layoutInfoState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation_release", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "<set-?>", "scrollToBeConsumed", "F", "getScrollToBeConsumed$foundation_release", "()F", "slotsPerLine$delegate", "Landroidx/compose/runtime/MutableIntState;", "getSlotsPerLine$foundation_release", "()I", "setSlotsPerLine$foundation_release", "(I)V", "slotsPerLine", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "isVertical", "Z", "isVertical$foundation_release", "()Z", "setVertical$foundation_release", "(Z)V", "scrollableState", "Landroidx/compose/foundation/gestures/ScrollableState;", "numMeasurePasses", "I", "getNumMeasurePasses$foundation_release", "prefetchingEnabled", "getPrefetchingEnabled$foundation_release", "setPrefetchingEnabled$foundation_release", "lineToPrefetch", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "currentLinePrefetchHandles", "Landroidx/compose/runtime/collection/MutableVector;", "wasScrollingForward", "Landroidx/compose/ui/layout/Remeasurement;", "remeasurement", "Landroidx/compose/ui/layout/Remeasurement;", "getRemeasurement$foundation_release", "()Landroidx/compose/ui/layout/Remeasurement;", "setRemeasurement$foundation_release", "(Landroidx/compose/ui/layout/Remeasurement;)V", "Landroidx/compose/ui/layout/RemeasurementModifier;", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation_release", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "awaitLayoutModifier", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "getAwaitLayoutModifier$foundation_release", "()Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "line", "", "Lx6/x;", "Landroidx/compose/ui/unit/Constraints;", "prefetchInfoRetriever$delegate", "getPrefetchInfoRetriever$foundation_release", "()Lr7/l;", "setPrefetchInfoRetriever$foundation_release", "(Lr7/l;)V", "prefetchInfoRetriever", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "getPlacementAnimator$foundation_release", "()Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "getBeyondBoundsInfo$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "Landroidx/compose/foundation/lazy/grid/LazyGridAnimateScrollScope;", "animateScrollScope", "Landroidx/compose/foundation/lazy/grid/LazyGridAnimateScrollScope;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "pinnedItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "getPinnedItems$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "getPlacementScopeInvalidator-zYiylxw$foundation_release", "()Landroidx/compose/runtime/MutableState;", "canScrollForward$delegate", "getCanScrollForward", "setCanScrollForward", "canScrollForward", "canScrollBackward$delegate", "getCanScrollBackward", "setCanScrollBackward", "canScrollBackward", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getPrefetchState$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getFirstVisibleItemIndex", "getFirstVisibleItemScrollOffset", "getLayoutInfo", "()Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Lx7/i;", "getNearestRange$foundation_release", "()Lx7/i;", "getNearestRange$foundation_release$delegate", "(Landroidx/compose/foundation/lazy/grid/LazyGridState;)Ljava/lang/Object;", "nearestRange", "isScrollInProgress", "getNumOfItemsToTeleport", "numOfItemsToTeleport", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridState implements ScrollableState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<LazyGridState, ?> Saver = ListSaverKt.listSaver(LazyGridState$Companion$Saver$1.INSTANCE, LazyGridState$Companion$Saver$2.INSTANCE);
    private final LazyGridAnimateScrollScope animateScrollScope;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollBackward;

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollForward;
    private final MutableVector<LazyLayoutPrefetchState.PrefetchHandle> currentLinePrefetchHandles;
    private Density density;
    private final MutableInteractionSource internalInteractionSource;
    private boolean isVertical;
    private final MutableState<LazyGridMeasureResult> layoutInfoState;
    private int lineToPrefetch;
    private int numMeasurePasses;
    private final LazyLayoutPinnedItemList pinnedItems;
    private final LazyGridItemPlacementAnimator placementAnimator;
    private final MutableState<t0> placementScopeInvalidator;

    /* JADX INFO: renamed from: prefetchInfoRetriever$delegate, reason: from kotlin metadata */
    private final MutableState prefetchInfoRetriever;
    private final LazyLayoutPrefetchState prefetchState;
    private boolean prefetchingEnabled;
    private Remeasurement remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final LazyGridScrollPosition scrollPosition;
    private float scrollToBeConsumed;
    private final ScrollableState scrollableState;

    /* JADX INFO: renamed from: slotsPerLine$delegate, reason: from kotlin metadata */
    private final MutableIntState slotsPerLine;
    private boolean wasScrollingForward;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<LazyGridState, ?> getSaver() {
            return LazyGridState.Saver;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.lazy.grid.LazyGridState", f = "LazyGridState.kt", l = {289, 290}, m = "scroll")
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
            return LazyGridState.this.scroll(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridState$scrollToItem$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.grid.LazyGridState$scrollToItem$2", f = "LazyGridState.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ScrollScope, d<? super t0>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ int $scrollOffset;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, int i11, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$index = i10;
            this.$scrollOffset = i11;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return LazyGridState.this.new AnonymousClass2(this.$index, this.$scrollOffset, dVar);
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
            LazyGridState.this.snapToItemIndexInternal$foundation_release(this.$index, this.$scrollOffset);
            return t0.f22605a;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LazyGridState() {
        int i10 = 0;
        this(i10, i10, 3, null);
    }

    public static /* synthetic */ Object animateScrollToItem$default(LazyGridState lazyGridState, int i10, int i11, d dVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        return lazyGridState.animateScrollToItem(i10, i11, dVar);
    }

    public static /* synthetic */ void applyMeasureResult$foundation_release$default(LazyGridState lazyGridState, LazyGridMeasureResult lazyGridMeasureResult, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        lazyGridState.applyMeasureResult$foundation_release(lazyGridMeasureResult, z);
    }

    private final void cancelPrefetchIfVisibleItemsChanged(LazyGridLayoutInfo info) {
        int row;
        if (this.lineToPrefetch == -1 || info.getVisibleItemsInfo().isEmpty()) {
            return;
        }
        if (this.wasScrollingForward) {
            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) x.w0(info.getVisibleItemsInfo());
            row = (this.isVertical ? lazyGridItemInfo.getRow() : lazyGridItemInfo.getColumn()) + 1;
        } else {
            LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) x.m0(info.getVisibleItemsInfo());
            row = (this.isVertical ? lazyGridItemInfo2.getRow() : lazyGridItemInfo2.getColumn()) - 1;
        }
        if (this.lineToPrefetch != row) {
            this.lineToPrefetch = -1;
            MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector = this.currentLinePrefetchHandles;
            int size = mutableVector.getSize();
            if (size > 0) {
                LazyLayoutPrefetchState.PrefetchHandle[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    content[i10].cancel();
                    i10++;
                } while (i10 < size);
            }
            this.currentLinePrefetchHandles.clear();
        }
    }

    private final int getNumOfItemsToTeleport() {
        return getSlotsPerLine$foundation_release() * 100;
    }

    private final void notifyPrefetch(float delta, LazyGridLayoutInfo layoutInfo) {
        int row;
        int index;
        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector;
        int size;
        LazyLayoutPrefetchState lazyLayoutPrefetchState = this.prefetchState;
        if (this.prefetchingEnabled && !layoutInfo.getVisibleItemsInfo().isEmpty()) {
            boolean z = delta < 0.0f;
            if (z) {
                LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) x.w0(layoutInfo.getVisibleItemsInfo());
                row = (this.isVertical ? lazyGridItemInfo.getRow() : lazyGridItemInfo.getColumn()) + 1;
                index = ((LazyGridItemInfo) x.w0(layoutInfo.getVisibleItemsInfo())).getIndex() + 1;
            } else {
                LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) x.m0(layoutInfo.getVisibleItemsInfo());
                row = (this.isVertical ? lazyGridItemInfo2.getRow() : lazyGridItemInfo2.getColumn()) - 1;
                index = ((LazyGridItemInfo) x.m0(layoutInfo.getVisibleItemsInfo())).getIndex() - 1;
            }
            if (row == this.lineToPrefetch || index < 0 || index >= layoutInfo.getTotalItemsCount()) {
                return;
            }
            if (this.wasScrollingForward != z && (size = (mutableVector = this.currentLinePrefetchHandles).getSize()) > 0) {
                LazyLayoutPrefetchState.PrefetchHandle[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    content[i10].cancel();
                    i10++;
                } while (i10 < size);
            }
            this.wasScrollingForward = z;
            this.lineToPrefetch = row;
            this.currentLinePrefetchHandles.clear();
            List list = (List) getPrefetchInfoRetriever$foundation_release().invoke(Integer.valueOf(row));
            int size2 = list.size();
            for (int i11 = 0; i11 < size2; i11++) {
                x6.x xVar = (x6.x) list.get(i11);
                this.currentLinePrefetchHandles.add(lazyLayoutPrefetchState.m692schedulePrefetch0kLqBqw(((Number) xVar.f22608i).intValue(), ((Constraints) xVar.f22609l).getValue()));
            }
        }
    }

    public static /* synthetic */ void notifyPrefetch$default(LazyGridState lazyGridState, float f10, LazyGridLayoutInfo lazyGridLayoutInfo, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lazyGridLayoutInfo = lazyGridState.layoutInfoState.getValue();
        }
        lazyGridState.notifyPrefetch(f10, lazyGridLayoutInfo);
    }

    public static /* synthetic */ Object scrollToItem$default(LazyGridState lazyGridState, int i10, int i11, d dVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        return lazyGridState.scrollToItem(i10, i11, dVar);
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

    public final void applyMeasureResult$foundation_release(LazyGridMeasureResult result, boolean visibleItemsStayedTheSame) {
        this.scrollToBeConsumed -= result.getConsumedScroll();
        this.layoutInfoState.setValue(result);
        if (visibleItemsStayedTheSame) {
            this.scrollPosition.updateScrollOffset(result.getFirstVisibleLineScrollOffset());
        } else {
            this.scrollPosition.updateFromMeasureResult(result);
            cancelPrefetchIfVisibleItemsChanged(result);
        }
        setCanScrollBackward(result.getCanScrollBackward());
        setCanScrollForward(result.getCanScrollForward());
        this.numMeasurePasses++;
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
        return this.internalInteractionSource;
    }

    /* JADX INFO: renamed from: getInternalInteractionSource$foundation_release, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    public final LazyGridLayoutInfo getLayoutInfo() {
        return this.layoutInfoState.getValue();
    }

    public final i getNearestRange$foundation_release() {
        return this.scrollPosition.getNearestRangeState().getValue();
    }

    /* JADX INFO: renamed from: getNumMeasurePasses$foundation_release, reason: from getter */
    public final int getNumMeasurePasses() {
        return this.numMeasurePasses;
    }

    /* JADX INFO: renamed from: getPinnedItems$foundation_release, reason: from getter */
    public final LazyLayoutPinnedItemList getPinnedItems() {
        return this.pinnedItems;
    }

    /* JADX INFO: renamed from: getPlacementAnimator$foundation_release, reason: from getter */
    public final LazyGridItemPlacementAnimator getPlacementAnimator() {
        return this.placementAnimator;
    }

    /* JADX INFO: renamed from: getPlacementScopeInvalidator-zYiylxw$foundation_release, reason: not valid java name */
    public final MutableState<t0> m672getPlacementScopeInvalidatorzYiylxw$foundation_release() {
        return this.placementScopeInvalidator;
    }

    public final l<Integer, List<x6.x>> getPrefetchInfoRetriever$foundation_release() {
        return (l) this.prefetchInfoRetriever.getValue();
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

    /* JADX INFO: renamed from: getScrollToBeConsumed$foundation_release, reason: from getter */
    public final float getScrollToBeConsumed() {
        return this.scrollToBeConsumed;
    }

    public final int getSlotsPerLine$foundation_release() {
        return this.slotsPerLine.getIntValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    /* JADX INFO: renamed from: isVertical$foundation_release, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final float onScroll$foundation_release(float distance) {
        if ((distance < 0.0f && !getCanScrollForward()) || (distance > 0.0f && !getCanScrollBackward())) {
            return 0.0f;
        }
        if (Math.abs(this.scrollToBeConsumed) > 0.5f) {
            throw new IllegalStateException(("entered drag with non-zero pending scroll: " + this.scrollToBeConsumed).toString());
        }
        float f10 = this.scrollToBeConsumed + distance;
        this.scrollToBeConsumed = f10;
        if (Math.abs(f10) > 0.5f) {
            LazyGridMeasureResult value = this.layoutInfoState.getValue();
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
            boolean r0 = r8 instanceof androidx.compose.foundation.lazy.grid.LazyGridState.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1 r0 = (androidx.compose.foundation.lazy.grid.LazyGridState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1 r0 = new androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1
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
            androidx.compose.foundation.lazy.grid.LazyGridState r1 = (androidx.compose.foundation.lazy.grid.LazyGridState) r1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridState.scroll(androidx.compose.foundation.MutatePriority, r7.p, d7.d):java.lang.Object");
    }

    public final Object scrollToItem(int i10, int i11, d<? super t0> dVar) {
        Object objE = f.e(this, null, new AnonymousClass2(i10, i11, null), dVar, 1, null);
        return objE == e7.a.f15033i ? objE : t0.f22605a;
    }

    public final void setDensity$foundation_release(Density density) {
        this.density = density;
    }

    public final void setPrefetchInfoRetriever$foundation_release(l<? super Integer, ? extends List<x6.x>> lVar) {
        this.prefetchInfoRetriever.setValue(lVar);
    }

    public final void setPrefetchingEnabled$foundation_release(boolean z) {
        this.prefetchingEnabled = z;
    }

    public final void setRemeasurement$foundation_release(Remeasurement remeasurement) {
        this.remeasurement = remeasurement;
    }

    public final void setSlotsPerLine$foundation_release(int i10) {
        this.slotsPerLine.setIntValue(i10);
    }

    public final void setVertical$foundation_release(boolean z) {
        this.isVertical = z;
    }

    public final void snapToItemIndexInternal$foundation_release(int index, int scrollOffset) {
        this.scrollPosition.requestPosition(index, scrollOffset);
        this.placementAnimator.reset();
        Remeasurement remeasurement = this.remeasurement;
        if (remeasurement != null) {
            remeasurement.forceRemeasure();
        }
    }

    public final int updateScrollPositionIfTheFirstItemWasMoved$foundation_release(LazyGridItemProvider itemProvider, int firstItemIndex) {
        return this.scrollPosition.updateScrollPositionIfTheFirstItemWasMoved(itemProvider, firstItemIndex);
    }

    public LazyGridState(int i10, int i11) {
        LazyGridScrollPosition lazyGridScrollPosition = new LazyGridScrollPosition(i10, i11);
        this.scrollPosition = lazyGridScrollPosition;
        this.layoutInfoState = SnapshotStateKt.mutableStateOf(LazyGridStateKt.EmptyLazyGridLayoutInfo, SnapshotStateKt.neverEqualPolicy());
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.slotsPerLine = SnapshotIntStateKt.mutableIntStateOf(0);
        this.density = DensityKt.Density(1.0f, 1.0f);
        this.isVertical = true;
        this.scrollableState = ScrollableStateKt.ScrollableState(new LazyGridState$scrollableState$1(this));
        this.prefetchingEnabled = true;
        this.lineToPrefetch = -1;
        this.currentLinePrefetchHandles = new MutableVector<>(new LazyLayoutPrefetchState.PrefetchHandle[16], 0);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$remeasurementModifier$1
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
                this.this$0.setRemeasurement$foundation_release(remeasurement);
            }

            @Override // androidx.compose.ui.Modifier
            public final /* synthetic */ Modifier then(Modifier modifier) {
                return androidx.compose.ui.a.a(this, modifier);
            }
        };
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.prefetchInfoRetriever = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(LazyGridState$prefetchInfoRetriever$2.INSTANCE, null, 2, null);
        this.placementAnimator = new LazyGridItemPlacementAnimator();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.animateScrollScope = new LazyGridAnimateScrollScope(this);
        this.pinnedItems = new LazyLayoutPinnedItemList();
        lazyGridScrollPosition.getNearestRangeState();
        this.placementScopeInvalidator = ObservableScopeInvalidator.m698constructorimpl$default(null, 1, null);
        Boolean bool = Boolean.FALSE;
        this.canScrollForward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.canScrollBackward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.prefetchState = new LazyLayoutPrefetchState();
    }

    public /* synthetic */ LazyGridState(int i10, int i11, int i12, h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11);
    }
}
