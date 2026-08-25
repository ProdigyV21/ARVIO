package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.gestures.f;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.b;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000e\u001a\u00020\n*\u00020\r2\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0011\u001a\u00020\n*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0014\u0010\u0007J2\u0010\u0018\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00042\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0016H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010!\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001a2'\u0010 \u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001c¢\u0006\u0002\b\u001fH\u0096@¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0016¢\u0006\u0004\b$\u0010%J!\u0010,\u001a\u00020\n2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020(H\u0000¢\u0006\u0004\b*\u0010+J\u0015\u0010-\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b-\u0010.J!\u00103\u001a\u00020\u00022\u0006\u00100\u001a\u00020/2\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0002¢\u0006\u0004\b4\u0010%J\u0010\u00105\u001a\u00020\nH\u0082@¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\n2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b7\u00108J\u0013\u00109\u001a\u00020\u0002*\u00020\u0002H\u0002¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0004H\u0002¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020(H\u0002¢\u0006\u0004\b>\u0010?J\u001f\u0010B\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010A\u001a\u00020@H\u0002¢\u0006\u0004\bB\u0010CJ\u0017\u0010D\u001a\u00020\n2\u0006\u0010A\u001a\u00020@H\u0002¢\u0006\u0004\bD\u0010ER1\u0010N\u001a\u00020F2\u0006\u0010G\u001a\u00020F8@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR+\u0010U\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00048@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010XR+\u0010Z\u001a\u00020(2\u0006\u0010G\u001a\u00020(8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010I\u001a\u0004\bZ\u0010?\"\u0004\b[\u0010\\R\u001a\u0010^\u001a\u00020]8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u0010aR$\u0010b\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\bd\u0010eR$\u0010f\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bf\u0010c\u001a\u0004\bg\u0010eR\u001e\u0010h\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028\u0002@BX\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010cR\u0016\u0010i\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bk\u0010jR\u0014\u0010l\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010mR$\u0010n\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bn\u0010c\u001a\u0004\bo\u0010eR\"\u0010p\u001a\u00020(8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010?\"\u0004\bs\u0010\\R\u0016\u0010t\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bt\u0010cR\u0018\u0010v\u001a\u0004\u0018\u00010u8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010qR\u001c\u0010z\u001a\b\u0012\u0004\u0012\u00020&0y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010IR$\u0010|\u001a\u00020{8\u0000@\u0000X\u0080\u000e¢\u0006\u0014\n\u0004\b|\u0010}\u001a\u0004\b~\u0010\u007f\"\u0006\b\u0080\u0001\u0010\u0081\u0001R \u0010\u0083\u0001\u001a\u00030\u0082\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R1\u0010\u008c\u0001\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0017\n\u0006\b\u0087\u0001\u0010\u0088\u0001\u001a\u0005\b\u0089\u0001\u0010e\"\u0006\b\u008a\u0001\u0010\u008b\u0001R1\u0010\u0090\u0001\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0017\n\u0006\b\u008d\u0001\u0010\u0088\u0001\u001a\u0005\b\u008e\u0001\u0010e\"\u0006\b\u008f\u0001\u0010\u008b\u0001R\u001f\u0010\u0094\u0001\u001a\u00020\u00028FX\u0086\u0084\u0002¢\u0006\u000f\n\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u0005\b\u0093\u0001\u0010eR\u001e\u0010\u0010\u001a\u00020\u00028FX\u0086\u0084\u0002¢\u0006\u000f\n\u0006\b\u0095\u0001\u0010\u0092\u0001\u001a\u0005\b\u0096\u0001\u0010eR \u0010\u0098\u0001\u001a\u00030\u0097\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0098\u0001\u0010\u0099\u0001\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R \u0010\u009d\u0001\u001a\u00030\u009c\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u009d\u0001\u0010\u009e\u0001\u001a\u0006\b\u009f\u0001\u0010 \u0001R \u0010¢\u0001\u001a\u00030¡\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b¢\u0001\u0010£\u0001\u001a\u0006\b¤\u0001\u0010¥\u0001R7\u0010¬\u0001\u001a\u0005\u0018\u00010¦\u00012\t\u0010G\u001a\u0005\u0018\u00010¦\u00018@@BX\u0080\u008e\u0002¢\u0006\u0017\n\u0005\b§\u0001\u0010I\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R \u0010®\u0001\u001a\u00030\u00ad\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b®\u0001\u0010¯\u0001\u001a\u0006\b°\u0001\u0010±\u0001R.\u0010³\u0001\u001a\u00030²\u00018\u0000@\u0000X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0016\n\u0006\b³\u0001\u0010´\u0001\u001a\u0005\bµ\u0001\u0010K\"\u0005\b¶\u0001\u0010MR \u0010¸\u0001\u001a\u00030·\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b¸\u0001\u0010¹\u0001\u001a\u0006\bº\u0001\u0010»\u0001R%\u0010½\u0001\u001a\u00030¼\u00018\u0000X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u000f\n\u0005\b½\u0001\u0010I\u001a\u0006\b¾\u0001\u0010¿\u0001R/\u0010Ã\u0001\u001a\u00020(2\u0006\u0010G\u001a\u00020(8F@BX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÀ\u0001\u0010I\u001a\u0005\bÁ\u0001\u0010?\"\u0005\bÂ\u0001\u0010\\R/\u0010Ç\u0001\u001a\u00020(2\u0006\u0010G\u001a\u00020(8F@BX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\bÄ\u0001\u0010I\u001a\u0005\bÅ\u0001\u0010?\"\u0005\bÆ\u0001\u0010\\R\u0016\u0010É\u0001\u001a\u00020\u00028&X¦\u0004¢\u0006\u0007\u001a\u0005\bÈ\u0001\u0010eR\u0014\u0010Ì\u0001\u001a\u00020@8F¢\u0006\b\u001a\u0006\bÊ\u0001\u0010Ë\u0001R\u0016\u0010Î\u0001\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÍ\u0001\u0010eR\u0016\u0010Ð\u0001\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÏ\u0001\u0010eR\u0016\u0010Ò\u0001\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÑ\u0001\u0010eR\u0016\u0010Ô\u0001\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÓ\u0001\u0010RR\u0015\u0010Ø\u0001\u001a\u00030Õ\u00018F¢\u0006\b\u001a\u0006\bÖ\u0001\u0010×\u0001R\u0012\u0010\u0003\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\bÙ\u0001\u0010eR\u0012\u0010\u0005\u001a\u00020\u00048F¢\u0006\u0007\u001a\u0005\bÚ\u0001\u0010RR!\u0010à\u0001\u001a\u00030Û\u00018@X\u0080\u0084\u0002¢\u0006\u0010\u001a\u0006\bÜ\u0001\u0010Ý\u0001*\u0006\bÞ\u0001\u0010ß\u0001R\u0016\u0010á\u0001\u001a\u00020(8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bá\u0001\u0010?\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006â\u0001"}, d2 = {"Landroidx/compose/foundation/pager/PagerState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "", "currentPage", "", "currentPageOffsetFraction", "<init>", "(IF)V", "page", "pageOffsetFraction", "Lx6/t0;", "scrollToPage", "(IFLd7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/gestures/ScrollScope;", "updateCurrentPage", "(Landroidx/compose/foundation/gestures/ScrollScope;IF)V", "targetPage", "updateTargetPage", "(Landroidx/compose/foundation/gestures/ScrollScope;I)V", "offsetFraction", "snapToItem$foundation_release", "snapToItem", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "animateScrollToPage", "(IFLandroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/MutatePriority;", "scrollPriority", "Lkotlin/Function2;", "Ld7/d;", "", "Lx6/n;", "block", "scroll", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "delta", "dispatchRawDelta", "(F)F", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "result", "", "visibleItemsStayedTheSame", "applyMeasureResult$foundation_release", "(Landroidx/compose/foundation/pager/PagerMeasureResult;Z)V", "applyMeasureResult", "getOffsetFractionForPage", "(I)F", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "itemProvider", "matchScrollPositionWithKey$foundation_release", "(Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;I)I", "matchScrollPositionWithKey", "performScroll", "awaitScrollDependencies", "(Ld7/d;)Ljava/lang/Object;", "tryRunPrefetch", "(Landroidx/compose/foundation/pager/PagerMeasureResult;)V", "coerceInPageRange", "(I)I", "scrollDelta", "isGestureActionMatchesScroll", "(F)Z", "isNotGestureAction", "()Z", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "info", "notifyPrefetch", "(FLandroidx/compose/foundation/pager/PagerLayoutInfo;)V", "cancelPrefetchIfVisibleItemsChanged", "(Landroidx/compose/foundation/pager/PagerLayoutInfo;)V", "Landroidx/compose/ui/geometry/Offset;", "<set-?>", "upDownDifference$delegate", "Landroidx/compose/runtime/MutableState;", "getUpDownDifference-F1C5BW0$foundation_release", "()J", "setUpDownDifference-k-4lQ0M$foundation_release", "(J)V", "upDownDifference", "snapRemainingScrollOffset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getSnapRemainingScrollOffset$foundation_release", "()F", "setSnapRemainingScrollOffset$foundation_release", "(F)V", "snapRemainingScrollOffset", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "animatedScrollScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "isScrollingForward$delegate", "isScrollingForward", "setScrollingForward", "(Z)V", "Landroidx/compose/foundation/pager/PagerScrollPosition;", "scrollPosition", "Landroidx/compose/foundation/pager/PagerScrollPosition;", "getScrollPosition$foundation_release", "()Landroidx/compose/foundation/pager/PagerScrollPosition;", "firstVisiblePage", "I", "getFirstVisiblePage$foundation_release", "()I", "firstVisiblePageOffset", "getFirstVisiblePageOffset$foundation_release", "maxScrollOffset", "accumulator", "F", "previousPassDelta", "scrollableState", "Landroidx/compose/foundation/gestures/ScrollableState;", "numMeasurePasses", "getNumMeasurePasses$foundation_release", "prefetchingEnabled", "Z", "getPrefetchingEnabled$foundation_release", "setPrefetchingEnabled$foundation_release", "indexToPrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "wasPrefetchingForward", "Landroidx/compose/runtime/MutableState;", "pagerLayoutInfoState", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation_release", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "programmaticScrollTargetPage$delegate", "Landroidx/compose/runtime/MutableIntState;", "getProgrammaticScrollTargetPage", "setProgrammaticScrollTargetPage", "(I)V", "programmaticScrollTargetPage", "settledPageState$delegate", "getSettledPageState", "setSettledPageState", "settledPageState", "settledPage$delegate", "Landroidx/compose/runtime/State;", "getSettledPage", "settledPage", "targetPage$delegate", "getTargetPage", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getPrefetchState$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "getBeyondBoundsInfo$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "awaitLayoutModifier", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "getAwaitLayoutModifier$foundation_release", "()Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "Landroidx/compose/ui/layout/Remeasurement;", "remeasurement$delegate", "getRemeasurement$foundation_release", "()Landroidx/compose/ui/layout/Remeasurement;", "setRemeasurement", "(Landroidx/compose/ui/layout/Remeasurement;)V", "remeasurement", "Landroidx/compose/ui/layout/RemeasurementModifier;", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation_release", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "Landroidx/compose/ui/unit/Constraints;", "premeasureConstraints", "J", "getPremeasureConstraints-msEJaDk$foundation_release", "setPremeasureConstraints-BRTryo0$foundation_release", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "pinnedPages", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "getPinnedPages$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "getPlacementScopeInvalidator-zYiylxw$foundation_release", "()Landroidx/compose/runtime/MutableState;", "canScrollForward$delegate", "getCanScrollForward", "setCanScrollForward", "canScrollForward", "canScrollBackward$delegate", "getCanScrollBackward", "setCanScrollBackward", "canScrollBackward", "getPageCount", "pageCount", "getLayoutInfo", "()Landroidx/compose/foundation/pager/PagerLayoutInfo;", "layoutInfo", "getPageSpacing$foundation_release", "pageSpacing", "getPageSize$foundation_release", "pageSize", "getPageSizeWithSpacing$foundation_release", "pageSizeWithSpacing", "getPositionThresholdFraction$foundation_release", "positionThresholdFraction", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "getCurrentPage", "getCurrentPageOffsetFraction", "Lx7/i;", "getNearestRange$foundation_release", "()Lx7/i;", "getNearestRange$foundation_release$delegate", "(Landroidx/compose/foundation/pager/PagerState;)Ljava/lang/Object;", "nearestRange", "isScrollInProgress", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class PagerState implements ScrollableState {
    public static final int $stable = 0;
    private float accumulator;
    private final LazyLayoutAnimateScrollScope animatedScrollScope;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollBackward;

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollForward;
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private Density density;
    private int firstVisiblePage;
    private int firstVisiblePageOffset;
    private int indexToPrefetch;
    private final MutableInteractionSource internalInteractionSource;

    /* JADX INFO: renamed from: isScrollingForward$delegate, reason: from kotlin metadata */
    private final MutableState isScrollingForward;
    private int maxScrollOffset;
    private int numMeasurePasses;
    private MutableState<PagerMeasureResult> pagerLayoutInfoState;
    private final LazyLayoutPinnedItemList pinnedPages;
    private final MutableState<t0> placementScopeInvalidator;
    private final LazyLayoutPrefetchState prefetchState;
    private boolean prefetchingEnabled;
    private long premeasureConstraints;
    private float previousPassDelta;

    /* JADX INFO: renamed from: programmaticScrollTargetPage$delegate, reason: from kotlin metadata */
    private final MutableIntState programmaticScrollTargetPage;

    /* JADX INFO: renamed from: remeasurement$delegate, reason: from kotlin metadata */
    private final MutableState remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final PagerScrollPosition scrollPosition;
    private final ScrollableState scrollableState;

    /* JADX INFO: renamed from: settledPage$delegate, reason: from kotlin metadata */
    private final State settledPage;

    /* JADX INFO: renamed from: settledPageState$delegate, reason: from kotlin metadata */
    private final MutableIntState settledPageState;

    /* JADX INFO: renamed from: snapRemainingScrollOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState snapRemainingScrollOffset;

    /* JADX INFO: renamed from: targetPage$delegate, reason: from kotlin metadata */
    private final State targetPage;

    /* JADX INFO: renamed from: upDownDifference$delegate, reason: from kotlin metadata */
    private final MutableState upDownDifference;
    private boolean wasPrefetchingForward;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$animateScrollToPage$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", l = {508, 517}, m = "animateScrollToPage")
    public static final class AnonymousClass1 extends c {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.this.animateScrollToPage(0, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scroll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", l = {570, 575}, m = "scroll$suspendImpl")
    public static final class C03761 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C03761(d<? super C03761> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.scroll$suspendImpl(PagerState.this, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scrollToPage$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.pager.PagerState$scrollToPage$2", f = "PagerState.kt", l = {438}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ScrollScope, d<? super t0>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ float $pageOffsetFraction;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, int i10, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$pageOffsetFraction = f10;
            this.$page = i10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return PagerState.this.new AnonymousClass2(this.$pageOffsetFraction, this.$page, dVar);
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PagerState pagerState = PagerState.this;
                this.label = 1;
                Object objAwaitScrollDependencies = pagerState.awaitScrollDependencies(this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitScrollDependencies == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            float f10 = this.$pageOffsetFraction;
            double d4 = f10;
            if (-0.5d <= d4 && d4 <= 0.5d) {
                PagerState.this.snapToItem$foundation_release(PagerState.this.coerceInPageRange(this.$page), this.$pageOffsetFraction);
                return t0.f22605a;
            }
            throw new IllegalArgumentException(("pageOffsetFraction " + f10 + " is not within the range -0.5 to 0.5").toString());
        }
    }

    public PagerState() {
        this(0, 0.0f, 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollToPage$default(PagerState pagerState, int i10, float f10, AnimationSpec animationSpec, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animateScrollToPage");
        }
        if ((i11 & 2) != 0) {
            f10 = 0.0f;
        }
        if ((i11 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return pagerState.animateScrollToPage(i10, f10, animationSpec, dVar);
    }

    public static /* synthetic */ void applyMeasureResult$foundation_release$default(PagerState pagerState, PagerMeasureResult pagerMeasureResult, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: applyMeasureResult");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        pagerState.applyMeasureResult$foundation_release(pagerMeasureResult, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitScrollDependencies(d<? super t0> dVar) {
        Object objWaitForFirstLayout = this.awaitLayoutModifier.waitForFirstLayout(dVar);
        return objWaitForFirstLayout == e7.a.f15033i ? objWaitForFirstLayout : t0.f22605a;
    }

    private final void cancelPrefetchIfVisibleItemsChanged(PagerLayoutInfo info) {
        int index;
        if (this.indexToPrefetch == -1 || info.getVisiblePagesInfo().isEmpty()) {
            return;
        }
        if (this.wasPrefetchingForward) {
            index = info.getBeyondBoundsPageCount() + ((PageInfo) x.w0(info.getVisiblePagesInfo())).getIndex() + 1;
        } else {
            index = (((PageInfo) x.m0(info.getVisiblePagesInfo())).getIndex() - info.getBeyondBoundsPageCount()) - 1;
        }
        if (this.indexToPrefetch != index) {
            this.indexToPrefetch = -1;
            LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
            if (prefetchHandle != null) {
                prefetchHandle.cancel();
            }
            this.currentPrefetchHandle = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int coerceInPageRange(int i10) {
        if (getPageCount() > 0) {
            return qb.d.n(i10, 0, getPageCount() - 1);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getProgrammaticScrollTargetPage() {
        return this.programmaticScrollTargetPage.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSettledPageState() {
        return this.settledPageState.getIntValue();
    }

    private final boolean isGestureActionMatchesScroll(float scrollDelta) {
        if (getLayoutInfo().getOrientation() == Orientation.Vertical) {
            if (Math.signum(scrollDelta) == Math.signum(-Offset.m3237getYimpl(m765getUpDownDifferenceF1C5BW0$foundation_release()))) {
                return true;
            }
        } else if (Math.signum(scrollDelta) == Math.signum(-Offset.m3236getXimpl(m765getUpDownDifferenceF1C5BW0$foundation_release()))) {
            return true;
        }
        return isNotGestureAction();
    }

    private final boolean isNotGestureAction() {
        return ((int) Offset.m3236getXimpl(m765getUpDownDifferenceF1C5BW0$foundation_release())) == 0 && ((int) Offset.m3237getYimpl(m765getUpDownDifferenceF1C5BW0$foundation_release())) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isScrollingForward() {
        return ((Boolean) this.isScrollingForward.getValue()).booleanValue();
    }

    public static /* synthetic */ int matchScrollPositionWithKey$foundation_release$default(PagerState pagerState, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: matchScrollPositionWithKey");
        }
        if ((i11 & 2) != 0) {
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    int currentPage = pagerState.scrollPosition.getCurrentPage();
                    snapshotCreateNonObservableSnapshot.dispose();
                    i10 = currentPage;
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.dispose();
                throw th;
            }
        }
        return pagerState.matchScrollPositionWithKey$foundation_release(pagerLazyLayoutItemProvider, i10);
    }

    private final void notifyPrefetch(float delta, PagerLayoutInfo info) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        if (this.prefetchingEnabled && !info.getVisiblePagesInfo().isEmpty()) {
            boolean z = delta > 0.0f;
            int beyondBoundsPageCount = z ? info.getBeyondBoundsPageCount() + ((PageInfo) x.w0(info.getVisiblePagesInfo())).getIndex() + 1 : (((PageInfo) x.m0(info.getVisiblePagesInfo())).getIndex() - info.getBeyondBoundsPageCount()) - 1;
            if (beyondBoundsPageCount == this.indexToPrefetch || beyondBoundsPageCount < 0 || beyondBoundsPageCount >= getPageCount()) {
                return;
            }
            if (this.wasPrefetchingForward != z && (prefetchHandle = this.currentPrefetchHandle) != null) {
                prefetchHandle.cancel();
            }
            this.wasPrefetchingForward = z;
            this.indexToPrefetch = beyondBoundsPageCount;
            this.currentPrefetchHandle = this.prefetchState.m692schedulePrefetch0kLqBqw(beyondBoundsPageCount, this.premeasureConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float performScroll(float delta) {
        float fCurrentScrollOffset = this.scrollPosition.currentScrollOffset();
        float f10 = fCurrentScrollOffset + delta + this.accumulator;
        float fM = qb.d.m(f10, 0.0f, this.maxScrollOffset);
        boolean z = f10 == fM;
        float f11 = fM - fCurrentScrollOffset;
        this.previousPassDelta = f11;
        if (Math.abs(f11) != 0.0f) {
            setScrollingForward(f11 > 0.0f);
        }
        int iM = t7.a.M(f11);
        PagerMeasureResult value = this.pagerLayoutInfoState.getValue();
        if (value.tryToApplyScrollWithoutRemeasure(-iM)) {
            applyMeasureResult$foundation_release(value, true);
            ObservableScopeInvalidator.m702invalidateScopeimpl(this.placementScopeInvalidator);
        } else {
            this.scrollPosition.applyScrollDelta(iM);
            Remeasurement remeasurement$foundation_release = getRemeasurement$foundation_release();
            if (remeasurement$foundation_release != null) {
                remeasurement$foundation_release.forceRemeasure();
            }
        }
        this.accumulator = f11 - iM;
        return !z ? f11 : delta;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
    
        if (r8.scroll(r6, r7, r0) == r4) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object scroll$suspendImpl(androidx.compose.foundation.pager.PagerState r5, androidx.compose.foundation.MutatePriority r6, r7.p<? super androidx.compose.foundation.gestures.ScrollScope, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.pager.PagerState.C03761
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.pager.PagerState$scroll$1 r0 = (androidx.compose.foundation.pager.PagerState.C03761) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.pager.PagerState$scroll$1 r0 = new androidx.compose.foundation.pager.PagerState$scroll$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L48
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.pager.PagerState r5 = (androidx.compose.foundation.pager.PagerState) r5
            k2.c.G(r8)
            goto L79
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            java.lang.Object r5 = r0.L$2
            r7 = r5
            r7.p r7 = (r7.p) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            androidx.compose.foundation.MutatePriority r6 = (androidx.compose.foundation.MutatePriority) r6
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.pager.PagerState r5 = (androidx.compose.foundation.pager.PagerState) r5
            k2.c.G(r8)
            goto L5a
        L48:
            k2.c.G(r8)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r5.awaitScrollDependencies(r0)
            if (r8 != r4) goto L5a
            goto L78
        L5a:
            boolean r8 = r5.isScrollInProgress()
            if (r8 != 0) goto L67
            int r8 = r5.getCurrentPage()
            r5.setSettledPageState(r8)
        L67:
            androidx.compose.foundation.gestures.ScrollableState r8 = r5.scrollableState
            r0.L$0 = r5
            r1 = 0
            r0.L$1 = r1
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r6 = r8.scroll(r6, r7, r0)
            if (r6 != r4) goto L79
        L78:
            return r4
        L79:
            r6 = -1
            r5.setProgrammaticScrollTargetPage(r6)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState.scroll$suspendImpl(androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.MutatePriority, r7.p, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object scrollToPage$default(PagerState pagerState, int i10, float f10, d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrollToPage");
        }
        if ((i11 & 2) != 0) {
            f10 = 0.0f;
        }
        return pagerState.scrollToPage(i10, f10, dVar);
    }

    private final void setCanScrollBackward(boolean z) {
        this.canScrollBackward.setValue(Boolean.valueOf(z));
    }

    private final void setCanScrollForward(boolean z) {
        this.canScrollForward.setValue(Boolean.valueOf(z));
    }

    private final void setProgrammaticScrollTargetPage(int i10) {
        this.programmaticScrollTargetPage.setIntValue(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemeasurement(Remeasurement remeasurement) {
        this.remeasurement.setValue(remeasurement);
    }

    private final void setScrollingForward(boolean z) {
        this.isScrollingForward.setValue(Boolean.valueOf(z));
    }

    private final void setSettledPageState(int i10) {
        this.settledPageState.setIntValue(i10);
    }

    private final void tryRunPrefetch(PagerMeasureResult result) {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                if (Math.abs(this.previousPassDelta) > 0.5f && this.prefetchingEnabled && isGestureActionMatchesScroll(this.previousPassDelta)) {
                    notifyPrefetch(this.previousPassDelta, result);
                }
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            snapshotCreateNonObservableSnapshot.dispose();
        }
    }

    public static /* synthetic */ void updateCurrentPage$default(PagerState pagerState, ScrollScope scrollScope, int i10, float f10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateCurrentPage");
        }
        if ((i11 & 2) != 0) {
            f10 = 0.0f;
        }
        pagerState.updateCurrentPage(scrollScope, i10, f10);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateScrollToPage(int r19, float r20, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r21, d7.d<? super x6.t0> r22) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState.animateScrollToPage(int, float, androidx.compose.animation.core.AnimationSpec, d7.d):java.lang.Object");
    }

    public final void applyMeasureResult$foundation_release(PagerMeasureResult result, boolean visibleItemsStayedTheSame) {
        if (visibleItemsStayedTheSame) {
            this.scrollPosition.updateCurrentPageOffsetFraction(result.getCurrentPageOffsetFraction());
        } else {
            this.scrollPosition.updateFromMeasureResult(result);
            cancelPrefetchIfVisibleItemsChanged(result);
        }
        this.pagerLayoutInfoState.setValue(result);
        setCanScrollForward(result.getCanScrollForward());
        setCanScrollBackward(result.getCanScrollBackward());
        this.numMeasurePasses++;
        MeasuredPage firstVisiblePage = result.getFirstVisiblePage();
        if (firstVisiblePage != null) {
            this.firstVisiblePage = firstVisiblePage.getIndex();
        }
        this.firstVisiblePageOffset = result.getFirstVisiblePageScrollOffset();
        tryRunPrefetch(result);
        this.maxScrollOffset = PagerStateKt.calculateNewMaxScrollOffset(result, getPageCount());
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
    public final boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward.getValue()).booleanValue();
    }

    public final int getCurrentPage() {
        return this.scrollPosition.getCurrentPage();
    }

    public final float getCurrentPageOffsetFraction() {
        return this.scrollPosition.getCurrentPageOffsetFraction();
    }

    /* JADX INFO: renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getFirstVisiblePage$foundation_release, reason: from getter */
    public final int getFirstVisiblePage() {
        return this.firstVisiblePage;
    }

    /* JADX INFO: renamed from: getFirstVisiblePageOffset$foundation_release, reason: from getter */
    public final int getFirstVisiblePageOffset() {
        return this.firstVisiblePageOffset;
    }

    public final InteractionSource getInteractionSource() {
        return this.internalInteractionSource;
    }

    /* JADX INFO: renamed from: getInternalInteractionSource$foundation_release, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    public final PagerLayoutInfo getLayoutInfo() {
        return this.pagerLayoutInfoState.getValue();
    }

    public final i getNearestRange$foundation_release() {
        return this.scrollPosition.getNearestRangeState().getValue();
    }

    /* JADX INFO: renamed from: getNumMeasurePasses$foundation_release, reason: from getter */
    public final int getNumMeasurePasses() {
        return this.numMeasurePasses;
    }

    public final float getOffsetFractionForPage(int page) {
        if (page >= 0 && page <= getPageCount()) {
            return getCurrentPageOffsetFraction() + (getCurrentPage() - page);
        }
        StringBuilder sbS = a0.c.s(page, "page ", " is not within the range 0 to ");
        sbS.append(getPageCount());
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    public abstract int getPageCount();

    public final int getPageSize$foundation_release() {
        return this.pagerLayoutInfoState.getValue().getPageSize();
    }

    public final int getPageSizeWithSpacing$foundation_release() {
        return getPageSpacing$foundation_release() + getPageSize$foundation_release();
    }

    public final int getPageSpacing$foundation_release() {
        return this.pagerLayoutInfoState.getValue().getPageSpacing();
    }

    /* JADX INFO: renamed from: getPinnedPages$foundation_release, reason: from getter */
    public final LazyLayoutPinnedItemList getPinnedPages() {
        return this.pinnedPages;
    }

    /* JADX INFO: renamed from: getPlacementScopeInvalidator-zYiylxw$foundation_release, reason: not valid java name */
    public final MutableState<t0> m763getPlacementScopeInvalidatorzYiylxw$foundation_release() {
        return this.placementScopeInvalidator;
    }

    public final float getPositionThresholdFraction$foundation_release() {
        return Math.min(this.density.mo285toPx0680j_4(PagerStateKt.getDefaultPositionThreshold()), getPageSize$foundation_release() / 2.0f) / getPageSize$foundation_release();
    }

    /* JADX INFO: renamed from: getPrefetchState$foundation_release, reason: from getter */
    public final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    /* JADX INFO: renamed from: getPrefetchingEnabled$foundation_release, reason: from getter */
    public final boolean getPrefetchingEnabled() {
        return this.prefetchingEnabled;
    }

    /* JADX INFO: renamed from: getPremeasureConstraints-msEJaDk$foundation_release, reason: not valid java name and from getter */
    public final long getPremeasureConstraints() {
        return this.premeasureConstraints;
    }

    public final Remeasurement getRemeasurement$foundation_release() {
        return (Remeasurement) this.remeasurement.getValue();
    }

    /* JADX INFO: renamed from: getRemeasurementModifier$foundation_release, reason: from getter */
    public final RemeasurementModifier getRemeasurementModifier() {
        return this.remeasurementModifier;
    }

    /* JADX INFO: renamed from: getScrollPosition$foundation_release, reason: from getter */
    public final PagerScrollPosition getScrollPosition() {
        return this.scrollPosition;
    }

    public final int getSettledPage() {
        return ((Number) this.settledPage.getValue()).intValue();
    }

    public final float getSnapRemainingScrollOffset$foundation_release() {
        return this.snapRemainingScrollOffset.getFloatValue();
    }

    public final int getTargetPage() {
        return ((Number) this.targetPage.getValue()).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getUpDownDifference-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m765getUpDownDifferenceF1C5BW0$foundation_release() {
        return ((Offset) this.upDownDifference.getValue()).getPackedValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    public final int matchScrollPositionWithKey$foundation_release(PagerLazyLayoutItemProvider itemProvider, int currentPage) {
        return this.scrollPosition.matchPageWithKey(itemProvider, currentPage);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, p<? super ScrollScope, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        return scroll$suspendImpl(this, mutatePriority, pVar, dVar);
    }

    public final Object scrollToPage(int i10, float f10, d<? super t0> dVar) {
        Object objE = f.e(this, null, new AnonymousClass2(f10, i10, null), dVar, 1, null);
        return objE == e7.a.f15033i ? objE : t0.f22605a;
    }

    public final void setDensity$foundation_release(Density density) {
        this.density = density;
    }

    public final void setPrefetchingEnabled$foundation_release(boolean z) {
        this.prefetchingEnabled = z;
    }

    /* JADX INFO: renamed from: setPremeasureConstraints-BRTryo0$foundation_release, reason: not valid java name */
    public final void m766setPremeasureConstraintsBRTryo0$foundation_release(long j10) {
        this.premeasureConstraints = j10;
    }

    public final void setSnapRemainingScrollOffset$foundation_release(float f10) {
        this.snapRemainingScrollOffset.setFloatValue(f10);
    }

    /* JADX INFO: renamed from: setUpDownDifference-k-4lQ0M$foundation_release, reason: not valid java name */
    public final void m767setUpDownDifferencek4lQ0M$foundation_release(long j10) {
        this.upDownDifference.setValue(Offset.m3225boximpl(j10));
    }

    public final void snapToItem$foundation_release(int page, float offsetFraction) {
        this.scrollPosition.requestPosition(page, offsetFraction);
        Remeasurement remeasurement$foundation_release = getRemeasurement$foundation_release();
        if (remeasurement$foundation_release != null) {
            remeasurement$foundation_release.forceRemeasure();
        }
    }

    public final void updateCurrentPage(ScrollScope scrollScope, int i10, float f10) {
        this.animatedScrollScope.snapToItem(scrollScope, i10, (int) (f10 * getPageSizeWithSpacing$foundation_release()));
    }

    public final void updateTargetPage(ScrollScope scrollScope, int i10) {
        setProgrammaticScrollTargetPage(coerceInPageRange(i10));
    }

    public PagerState(int i10, float f10) {
        double d4 = f10;
        if (-0.5d > d4 || d4 > 0.5d) {
            throw new IllegalArgumentException(("initialPageOffsetFraction " + f10 + " is not within the range -0.5 to 0.5").toString());
        }
        this.upDownDifference = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(Offset.INSTANCE.m3252getZeroF1C5BW0()), null, 2, null);
        this.snapRemainingScrollOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.animatedScrollScope = PagerLazyAnimateScrollScopeKt.PagerLazyAnimateScrollScope(this);
        Boolean bool = Boolean.FALSE;
        this.isScrollingForward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        PagerScrollPosition pagerScrollPosition = new PagerScrollPosition(i10, f10, this);
        this.scrollPosition = pagerScrollPosition;
        this.firstVisiblePage = i10;
        this.maxScrollOffset = Integer.MAX_VALUE;
        this.scrollableState = ScrollableStateKt.ScrollableState(new PagerState$scrollableState$1(this));
        this.prefetchingEnabled = true;
        this.indexToPrefetch = -1;
        this.pagerLayoutInfoState = SnapshotStateKt.mutableStateOf(PagerStateKt.getEmptyLayoutInfo(), SnapshotStateKt.neverEqualPolicy());
        this.density = PagerStateKt.UnitDensity;
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.programmaticScrollTargetPage = SnapshotIntStateKt.mutableIntStateOf(-1);
        this.settledPageState = SnapshotIntStateKt.mutableIntStateOf(i10);
        this.settledPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new PagerState$settledPage$2(this));
        this.targetPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new PagerState$targetPage$2(this));
        this.prefetchState = new LazyLayoutPrefetchState();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.remeasurement = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.pager.PagerState$remeasurementModifier$1
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
                this.this$0.setRemeasurement(remeasurement);
            }

            @Override // androidx.compose.ui.Modifier
            public final /* synthetic */ Modifier then(Modifier modifier) {
                return androidx.compose.ui.a.a(this, modifier);
            }
        };
        this.premeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
        this.pinnedPages = new LazyLayoutPinnedItemList();
        pagerScrollPosition.getNearestRangeState();
        this.placementScopeInvalidator = ObservableScopeInvalidator.m698constructorimpl$default(null, 1, null);
        this.canScrollForward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.canScrollBackward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
    }

    public /* synthetic */ PagerState(int i10, float f10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? 0.0f : f10);
    }
}
