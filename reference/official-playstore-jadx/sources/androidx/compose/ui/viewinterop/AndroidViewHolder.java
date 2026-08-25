package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerScope;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.platform.NestedScrollInteropConnectionKt;
import androidx.compose.ui.platform.WindowRecomposer_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.view.o0;
import androidx.core.view.q0;
import androidx.lifecycle.y;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import b2.g;
import d7.d;
import f4.f;
import f7.e;
import f7.j;
import io.ktor.http.LinkHeader;
import java.util.List;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u0000 ½\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002½\u0001B9\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001d\u0010\u001bJ\u001f\u0010 \u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0014¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0019¢\u0006\u0004\b\"\u0010\u001bJ7\u0010)\u001a\u00020\u00192\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tH\u0014¢\u0006\u0004\b)\u0010*J\u0011\u0010,\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u00192\u0006\u0010.\u001a\u00020#H\u0016¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0019H\u0014¢\u0006\u0004\b1\u0010\u001bJ\u000f\u00102\u001a\u00020\u0019H\u0014¢\u0006\u0004\b2\u0010\u001bJ%\u00108\u001a\u0004\u0018\u0001072\b\u00104\u001a\u0004\u0018\u0001032\b\u00106\u001a\u0004\u0018\u000105H\u0016¢\u0006\u0004\b8\u00109J\u001f\u0010<\u001a\u00020\u00192\u0006\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\rH\u0016¢\u0006\u0004\b<\u0010=J\r\u0010>\u001a\u00020\u0019¢\u0006\u0004\b>\u0010\u001bJ\u0017\u0010@\u001a\u00020\u00192\u0006\u0010?\u001a\u00020\tH\u0014¢\u0006\u0004\b@\u0010AJ\u0019\u0010D\u001a\u00020#2\b\u0010C\u001a\u0004\u0018\u00010BH\u0016¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020#H\u0016¢\u0006\u0004\bF\u0010GJ/\u0010J\u001a\u00020#2\u0006\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\r2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\tH\u0016¢\u0006\u0004\bL\u0010MJ/\u0010N\u001a\u00020\u00192\u0006\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\r2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016¢\u0006\u0004\bN\u0010OJ\u001f\u0010P\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\r2\u0006\u0010I\u001a\u00020\tH\u0016¢\u0006\u0004\bP\u0010QJG\u0010W\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\r2\u0006\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\t2\u0006\u0010U\u001a\u00020\t2\u0006\u0010I\u001a\u00020\t2\u0006\u0010V\u001a\u000203H\u0016¢\u0006\u0004\bW\u0010XJ?\u0010W\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\r2\u0006\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\t2\u0006\u0010U\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016¢\u0006\u0004\bW\u0010YJ7\u0010\\\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\r2\u0006\u0010Z\u001a\u00020\t2\u0006\u0010[\u001a\u00020\t2\u0006\u0010V\u001a\u0002032\u0006\u0010I\u001a\u00020\tH\u0016¢\u0006\u0004\b\\\u0010]J/\u0010a\u001a\u00020#2\u0006\u0010;\u001a\u00020\r2\u0006\u0010_\u001a\u00020^2\u0006\u0010`\u001a\u00020^2\u0006\u0010V\u001a\u00020#H\u0016¢\u0006\u0004\ba\u0010bJ'\u0010c\u001a\u00020#2\u0006\u0010;\u001a\u00020\r2\u0006\u0010_\u001a\u00020^2\u0006\u0010`\u001a\u00020^H\u0016¢\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020#H\u0016¢\u0006\u0004\be\u0010GJ'\u0010i\u001a\u00020\t2\u0006\u0010f\u001a\u00020\t2\u0006\u0010g\u001a\u00020\t2\u0006\u0010h\u001a\u00020\tH\u0002¢\u0006\u0004\bi\u0010jR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010kR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010lR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010m\u001a\u0004\bn\u0010\u0015R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010oR6\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00190p2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00190p8\u0006@DX\u0086\u000e¢\u0006\u0012\n\u0004\br\u0010s\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010yR6\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00190p2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00190p8\u0006@DX\u0086\u000e¢\u0006\u0012\n\u0004\b{\u0010s\u001a\u0004\b|\u0010u\"\u0004\b}\u0010wR7\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00190p2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00190p8\u0006@DX\u0086\u000e¢\u0006\u0013\n\u0004\b~\u0010s\u001a\u0004\b\u007f\u0010u\"\u0005\b\u0080\u0001\u0010wR3\u0010\u0082\u0001\u001a\u00030\u0081\u00012\u0007\u0010q\u001a\u00030\u0081\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001R9\u0010\u0089\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u0081\u0001\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0088\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R3\u0010\u0090\u0001\u001a\u00030\u008f\u00012\u0007\u0010q\u001a\u00030\u008f\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u0090\u0001\u0010\u0091\u0001\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R9\u0010\u0096\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u008f\u0001\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0088\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0096\u0001\u0010\u008a\u0001\u001a\u0006\b\u0097\u0001\u0010\u008c\u0001\"\u0006\b\u0098\u0001\u0010\u008e\u0001R7\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0099\u00012\t\u0010q\u001a\u0005\u0018\u00010\u0099\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R7\u0010¡\u0001\u001a\u0005\u0018\u00010 \u00012\t\u0010q\u001a\u0005\u0018\u00010 \u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b¡\u0001\u0010¢\u0001\u001a\u0006\b£\u0001\u0010¤\u0001\"\u0006\b¥\u0001\u0010¦\u0001R\u001c\u0010§\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190p8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b§\u0001\u0010sR\u001c\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190p8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b¨\u0001\u0010sR8\u0010©\u0001\u001a\u0011\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0088\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b©\u0001\u0010\u008a\u0001\u001a\u0006\bª\u0001\u0010\u008c\u0001\"\u0006\b«\u0001\u0010\u008e\u0001R\u0015\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b4\u0010¬\u0001R\u0018\u0010\u00ad\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u00ad\u0001\u0010kR\u0018\u0010®\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b®\u0001\u0010kR\u0018\u0010°\u0001\u001a\u00030¯\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0001\u0010±\u0001R\u0018\u0010²\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b²\u0001\u0010yR\u001d\u0010´\u0001\u001a\u00030³\u00018\u0006¢\u0006\u0010\n\u0006\b´\u0001\u0010µ\u0001\u001a\u0006\b¶\u0001\u0010·\u0001R\u0016\u0010¸\u0001\u001a\u00020#8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¸\u0001\u0010GR\u0018\u0010¼\u0001\u001a\u00030¹\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bº\u0001\u0010»\u0001¨\u0006¾\u0001"}, d2 = {"Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "Landroid/view/ViewGroup;", "Landroidx/core/view/o0;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "Landroidx/compose/ui/node/OwnerScope;", "Landroid/content/Context;", "context", "Landroidx/compose/runtime/CompositionContext;", "parentContext", "", "compositeKeyHash", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "dispatcher", "Landroid/view/View;", "view", "Landroidx/compose/ui/node/Owner;", "owner", "<init>", "(Landroid/content/Context;Landroidx/compose/runtime/CompositionContext;ILandroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Landroid/view/View;Landroidx/compose/ui/node/Owner;)V", "Landroidx/compose/ui/viewinterop/InteropView;", "getInteropView", "()Landroid/view/View;", "", "getAccessibilityClassName", "()Ljava/lang/CharSequence;", "Lx6/t0;", "onReuse", "()V", "onDeactivate", "onRelease", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "remeasure", "", "changed", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "onLayout", "(ZIIII)V", "Landroid/view/ViewGroup$LayoutParams;", "getLayoutParams", "()Landroid/view/ViewGroup$LayoutParams;", "disallowIntercept", "requestDisallowInterceptTouchEvent", "(Z)V", "onAttachedToWindow", "onDetachedFromWindow", "", "location", "Landroid/graphics/Rect;", "dirty", "Landroid/view/ViewParent;", "invalidateChildInParent", "([ILandroid/graphics/Rect;)Landroid/view/ViewParent;", "child", "target", "onDescendantInvalidated", "(Landroid/view/View;Landroid/view/View;)V", "invalidateOrDefer", "visibility", "onWindowVisibilityChanged", "(I)V", "Landroid/graphics/Region;", TtmlNode.TAG_REGION, "gatherTransparentRegion", "(Landroid/graphics/Region;)Z", "shouldDelayChildPressedState", "()Z", "axes", LinkHeader.Parameters.Type, "onStartNestedScroll", "(Landroid/view/View;Landroid/view/View;II)Z", "getNestedScrollAxes", "()I", "onNestedScrollAccepted", "(Landroid/view/View;Landroid/view/View;II)V", "onStopNestedScroll", "(Landroid/view/View;I)V", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "consumed", "onNestedScroll", "(Landroid/view/View;IIIII[I)V", "(Landroid/view/View;IIIII)V", "dx", "dy", "onNestedPreScroll", "(Landroid/view/View;II[II)V", "", "velocityX", "velocityY", "onNestedFling", "(Landroid/view/View;FFZ)Z", "onNestedPreFling", "(Landroid/view/View;FF)Z", "isNestedScrollingEnabled", "min", "max", "preferred", "obtainMeasureSpec", "(III)I", "I", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "Landroid/view/View;", "getView", "Landroidx/compose/ui/node/Owner;", "Lkotlin/Function0;", "value", "update", "Lr7/a;", "getUpdate", "()Lr7/a;", "setUpdate", "(Lr7/a;)V", "hasUpdateBlock", "Z", "<set-?>", "reset", "getReset", "setReset", "release", "getRelease", "setRelease", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "Lkotlin/Function1;", "onModifierChanged", "Lr7/l;", "getOnModifierChanged$ui_release", "()Lr7/l;", "setOnModifierChanged$ui_release", "(Lr7/l;)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "onDensityChanged", "getOnDensityChanged$ui_release", "setOnDensityChanged$ui_release", "Landroidx/lifecycle/y;", "lifecycleOwner", "Landroidx/lifecycle/y;", "getLifecycleOwner", "()Landroidx/lifecycle/y;", "setLifecycleOwner", "(Landroidx/lifecycle/y;)V", "Lb2/g;", "savedStateRegistryOwner", "Lb2/g;", "getSavedStateRegistryOwner", "()Lb2/g;", "setSavedStateRegistryOwner", "(Lb2/g;)V", "runUpdate", "runInvalidate", "onRequestDisallowInterceptTouchEvent", "getOnRequestDisallowInterceptTouchEvent$ui_release", "setOnRequestDisallowInterceptTouchEvent$ui_release", "[I", "lastWidthMeasureSpec", "lastHeightMeasureSpec", "Landroidx/core/view/q0;", "nestedScrollingParentHelper", "Landroidx/core/view/q0;", "isDrawing", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "isValidOwnerScope", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "snapshotObserver", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class AndroidViewHolder extends ViewGroup implements o0, ComposeNodeLifecycleCallback, OwnerScope {
    private final int compositeKeyHash;
    private Density density;
    private final NestedScrollDispatcher dispatcher;
    private boolean hasUpdateBlock;
    private boolean isDrawing;
    private int lastHeightMeasureSpec;
    private int lastWidthMeasureSpec;
    private final LayoutNode layoutNode;
    private y lifecycleOwner;
    private final int[] location;
    private Modifier modifier;
    private final q0 nestedScrollingParentHelper;
    private l<? super Density, t0> onDensityChanged;
    private l<? super Modifier, t0> onModifierChanged;
    private l<? super Boolean, t0> onRequestDisallowInterceptTouchEvent;
    private final Owner owner;
    private r7.a<t0> release;
    private r7.a<t0> reset;
    private final r7.a<t0> runInvalidate;
    private final r7.a<t0> runUpdate;
    private g savedStateRegistryOwner;
    private r7.a<t0> update;
    private final View view;
    public static final int $stable = 8;
    private static final l<AndroidViewHolder, t0> OnCommitAffectingUpdate = AndroidViewHolder$Companion$OnCommitAffectingUpdate$1.INSTANCE;

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedFling$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedFling$1", f = "AndroidViewHolder.android.kt", l = {560, 565}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ boolean $consumed;
        final /* synthetic */ long $viewVelocity;
        int label;
        final /* synthetic */ AndroidViewHolder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, AndroidViewHolder androidViewHolder, long j10, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$consumed = z;
            this.this$0 = androidViewHolder;
            this.$viewVelocity = j10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$consumed, this.this$0, this.$viewVelocity, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
        
            if (r3.m4413dispatchPostFlingRZ2iAVY(r4, r6, r9) == r0) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
        
            if (r10.m4413dispatchPostFlingRZ2iAVY(r2, r4, r9) == r0) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
        
            return r0;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                int r0 = r9.label
                r1 = 2
                r2 = 1
                if (r0 == 0) goto L1b
                if (r0 == r2) goto L16
                if (r0 != r1) goto Le
                k2.c.G(r10)
                goto L56
            Le:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L16:
                k2.c.G(r10)
                r6 = r9
                goto L56
            L1b:
                k2.c.G(r10)
                boolean r10 = r9.$consumed
                e7.a r0 = e7.a.f15033i
                if (r10 != 0) goto L3d
                androidx.compose.ui.viewinterop.AndroidViewHolder r10 = r9.this$0
                androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r3 = androidx.compose.ui.viewinterop.AndroidViewHolder.access$getDispatcher$p(r10)
                androidx.compose.ui.unit.Velocity$Companion r10 = androidx.compose.ui.unit.Velocity.INSTANCE
                long r4 = r10.m5920getZero9UxMQ8M()
                long r6 = r9.$viewVelocity
                r9.label = r2
                r8 = r9
                java.lang.Object r10 = r3.m4413dispatchPostFlingRZ2iAVY(r4, r6, r8)
                r6 = r8
                if (r10 != r0) goto L56
                goto L55
            L3d:
                r6 = r9
                androidx.compose.ui.viewinterop.AndroidViewHolder r10 = r6.this$0
                androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r10 = androidx.compose.ui.viewinterop.AndroidViewHolder.access$getDispatcher$p(r10)
                long r2 = r6.$viewVelocity
                androidx.compose.ui.unit.Velocity$Companion r4 = androidx.compose.ui.unit.Velocity.INSTANCE
                long r4 = r4.m5920getZero9UxMQ8M()
                r6.label = r1
                r1 = r10
                java.lang.Object r10 = r1.m4413dispatchPostFlingRZ2iAVY(r2, r4, r6)
                if (r10 != r0) goto L56
            L55:
                return r0
            L56:
                x6.t0 r10 = x6.t0.f22605a
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.AndroidViewHolder.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedPreFling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedPreFling$1", f = "AndroidViewHolder.android.kt", l = {578}, m = "invokeSuspend")
    public static final class C06261 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ long $toBeConsumed;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06261(long j10, d<? super C06261> dVar) {
            super(2, dVar);
            this.$toBeConsumed = j10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AndroidViewHolder.this.new C06261(this.$toBeConsumed, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                NestedScrollDispatcher nestedScrollDispatcher = AndroidViewHolder.this.dispatcher;
                long j10 = this.$toBeConsumed;
                this.label = 1;
                Object objM4415dispatchPreFlingQWom1Mo = nestedScrollDispatcher.m4415dispatchPreFlingQWom1Mo(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objM4415dispatchPreFlingQWom1Mo == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C06261) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidViewHolder(Context context, CompositionContext compositionContext, int i10, NestedScrollDispatcher nestedScrollDispatcher, View view, Owner owner) {
        super(context);
        this.compositeKeyHash = i10;
        this.dispatcher = nestedScrollDispatcher;
        this.view = view;
        this.owner = owner;
        if (compositionContext != null) {
            WindowRecomposer_androidKt.setCompositionContext(this, compositionContext);
        }
        setSaveFromParentEnabled(false);
        addView(view);
        this.update = AndroidViewHolder$update$1.INSTANCE;
        this.reset = AndroidViewHolder$reset$1.INSTANCE;
        this.release = AndroidViewHolder$release$1.INSTANCE;
        Modifier.Companion companion = Modifier.INSTANCE;
        this.modifier = companion;
        this.density = DensityKt.Density$default(1.0f, 0.0f, 2, null);
        this.runUpdate = new AndroidViewHolder$runUpdate$1(this);
        this.runInvalidate = new AndroidViewHolder$runInvalidate$1(this);
        this.location = new int[2];
        this.lastWidthMeasureSpec = Integer.MIN_VALUE;
        this.lastHeightMeasureSpec = Integer.MIN_VALUE;
        this.nestedScrollingParentHelper = new q0();
        Object[] objArr = 0 == true ? 1 : 0;
        final LayoutNode layoutNode = new LayoutNode(false, objArr, 3, null);
        layoutNode.setInteropViewFactoryHolder$ui_release(this);
        Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(DrawModifierKt.drawBehind(PointerInteropFilter_androidKt.pointerInteropFilter(SemanticsModifierKt.semantics(NestedScrollModifierKt.nestedScroll(companion, AndroidViewHolder_androidKt.NoOpScrollConnection, nestedScrollDispatcher), true, AndroidViewHolder$layoutNode$1$coreModifier$1.INSTANCE), this), new AndroidViewHolder$layoutNode$1$coreModifier$2(this, layoutNode, this)), new AndroidViewHolder$layoutNode$1$coreModifier$3(this, layoutNode));
        layoutNode.setCompositeKeyHash(i10);
        layoutNode.setModifier(this.modifier.then(modifierOnGloballyPositioned));
        this.onModifierChanged = new AndroidViewHolder$layoutNode$1$1(layoutNode, modifierOnGloballyPositioned);
        layoutNode.setDensity(this.density);
        this.onDensityChanged = new AndroidViewHolder$layoutNode$1$2(layoutNode);
        layoutNode.setOnAttach$ui_release(new AndroidViewHolder$layoutNode$1$3(this, layoutNode));
        layoutNode.setOnDetach$ui_release(new AndroidViewHolder$layoutNode$1$4(this));
        layoutNode.setMeasurePolicy(new MeasurePolicy() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5
            private final int intrinsicHeight(int width) {
                AndroidViewHolder androidViewHolder = this.$this_run;
                androidViewHolder.measure(androidViewHolder.obtainMeasureSpec(0, width, androidViewHolder.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
                return this.$this_run.getMeasuredHeight();
            }

            private final int intrinsicWidth(int height) {
                AndroidViewHolder androidViewHolder = this.$this_run;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                AndroidViewHolder androidViewHolder2 = this.$this_run;
                androidViewHolder.measure(iMakeMeasureSpec, androidViewHolder2.obtainMeasureSpec(0, height, androidViewHolder2.getLayoutParams().height));
                return this.$this_run.getMeasuredWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i11) {
                return intrinsicHeight(i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i11) {
                return intrinsicWidth(i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                if (this.$this_run.getChildCount() == 0) {
                    return MeasureScope.CC.q(measureScope, Constraints.m5648getMinWidthimpl(j10), Constraints.m5647getMinHeightimpl(j10), null, AndroidViewHolder$layoutNode$1$5$measure$1.INSTANCE, 4, null);
                }
                if (Constraints.m5648getMinWidthimpl(j10) != 0) {
                    this.$this_run.getChildAt(0).setMinimumWidth(Constraints.m5648getMinWidthimpl(j10));
                }
                if (Constraints.m5647getMinHeightimpl(j10) != 0) {
                    this.$this_run.getChildAt(0).setMinimumHeight(Constraints.m5647getMinHeightimpl(j10));
                }
                AndroidViewHolder androidViewHolder = this.$this_run;
                androidViewHolder.measure(androidViewHolder.obtainMeasureSpec(Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10), this.$this_run.getLayoutParams().width), this.$this_run.obtainMeasureSpec(Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10), this.$this_run.getLayoutParams().height));
                return MeasureScope.CC.q(measureScope, this.$this_run.getMeasuredWidth(), this.$this_run.getMeasuredHeight(), null, new AndroidViewHolder$layoutNode$1$5$measure$2(this.$this_run, layoutNode), 4, null);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i11) {
                return intrinsicHeight(i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i11) {
                return intrinsicWidth(i11);
            }
        });
        this.layoutNode = layoutNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        if (isAttachedToWindow()) {
            return this.owner.getSnapshotObserver();
        }
        throw new IllegalStateException("Expected AndroidViewHolder to be attached when observing reads.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int obtainMeasureSpec(int min, int max, int preferred) {
        return (preferred >= 0 || min == max) ? View.MeasureSpec.makeMeasureSpec(qb.d.n(preferred, min, max), 1073741824) : (preferred != -2 || max == Integer.MAX_VALUE) ? (preferred != -1 || max == Integer.MAX_VALUE) ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(max, 1073741824) : View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        if (region == null) {
            return true;
        }
        getLocationInWindow(this.location);
        int[] iArr = this.location;
        int i10 = iArr[0];
        region.op(i10, iArr[1], getWidth() + i10, getHeight() + this.location[1], Region.Op.DIFFERENCE);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return getClass().getName();
    }

    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: getInteropView, reason: from getter */
    public final View getView() {
        return this.view;
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    @Override // android.view.View
    public ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-1, -1) : layoutParams;
    }

    public final y getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        q0 q0Var = this.nestedScrollingParentHelper;
        return q0Var.f2298b | q0Var.f2297a;
    }

    public final l<Density, t0> getOnDensityChanged$ui_release() {
        return this.onDensityChanged;
    }

    public final l<Modifier, t0> getOnModifierChanged$ui_release() {
        return this.onModifierChanged;
    }

    public final l<Boolean, t0> getOnRequestDisallowInterceptTouchEvent$ui_release() {
        return this.onRequestDisallowInterceptTouchEvent;
    }

    public final r7.a<t0> getRelease() {
        return this.release;
    }

    public final r7.a<t0> getReset() {
        return this.reset;
    }

    public final g getSavedStateRegistryOwner() {
        return this.savedStateRegistryOwner;
    }

    public final r7.a<t0> getUpdate() {
        return this.update;
    }

    public final View getView() {
        return this.view;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
        super.invalidateChildInParent(location, dirty);
        invalidateOrDefer();
        return null;
    }

    public final void invalidateOrDefer() {
        if (!this.isDrawing) {
            this.layoutNode.invalidateLayer$ui_release();
        } else {
            this.view.postOnAnimation(new a(1, this.runInvalidate));
        }
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.view.isNestedScrollingEnabled();
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return isAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.runUpdate.invoke();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        this.reset.invoke();
        removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View child, View target) {
        super.onDescendantInvalidated(child, target);
        invalidateOrDefer();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getSnapshotObserver().clear$ui_release(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l10, int t2, int r4, int b10) {
        this.view.layout(0, 0, r4 - l10, b10 - t2);
    }

    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.view.getParent() != this) {
            setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
            return;
        }
        if (this.view.getVisibility() == 8) {
            setMeasuredDimension(0, 0);
            return;
        }
        this.view.measure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.view.getMeasuredWidth(), this.view.getMeasuredHeight());
        this.lastWidthMeasureSpec = widthMeasureSpec;
        this.lastHeightMeasureSpec = heightMeasureSpec;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        m0.p(this.dispatcher.getCoroutineScope(), null, 0, new AnonymousClass1(consumed, this, VelocityKt.Velocity(AndroidViewHolder_androidKt.toComposeVelocity(velocityX), AndroidViewHolder_androidKt.toComposeVelocity(velocityY)), null), 3);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        m0.p(this.dispatcher.getCoroutineScope(), null, 0, new C06261(VelocityKt.Velocity(AndroidViewHolder_androidKt.toComposeVelocity(velocityX), AndroidViewHolder_androidKt.toComposeVelocity(velocityY)), null), 3);
        return false;
    }

    @Override // androidx.core.view.n0
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        if (isNestedScrollingEnabled()) {
            long jM4416dispatchPreScrollOzD1aCk = this.dispatcher.m4416dispatchPreScrollOzD1aCk(OffsetKt.Offset(AndroidViewHolder_androidKt.toComposeOffset(dx), AndroidViewHolder_androidKt.toComposeOffset(dy)), AndroidViewHolder_androidKt.toNestedScrollSource(type));
            consumed[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3236getXimpl(jM4416dispatchPreScrollOzD1aCk));
            consumed[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3237getYimpl(jM4416dispatchPreScrollOzD1aCk));
        }
    }

    @Override // androidx.core.view.o0
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
        if (isNestedScrollingEnabled()) {
            long jM4414dispatchPostScrollDzOQY0M = this.dispatcher.m4414dispatchPostScrollDzOQY0M(OffsetKt.Offset(AndroidViewHolder_androidKt.toComposeOffset(dxConsumed), AndroidViewHolder_androidKt.toComposeOffset(dyConsumed)), OffsetKt.Offset(AndroidViewHolder_androidKt.toComposeOffset(dxUnconsumed), AndroidViewHolder_androidKt.toComposeOffset(dyUnconsumed)), AndroidViewHolder_androidKt.toNestedScrollSource(type));
            consumed[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3236getXimpl(jM4414dispatchPostScrollDzOQY0M));
            consumed[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3237getYimpl(jM4414dispatchPostScrollDzOQY0M));
        }
    }

    @Override // androidx.core.view.n0
    public void onNestedScrollAccepted(View child, View target, int axes, int type) {
        q0 q0Var = this.nestedScrollingParentHelper;
        if (type == 1) {
            q0Var.f2298b = axes;
        } else {
            q0Var.f2297a = axes;
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        this.release.invoke();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        if (this.view.getParent() != this) {
            addView(this.view);
        } else {
            this.reset.invoke();
        }
    }

    @Override // androidx.core.view.n0
    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        return ((axes & 2) == 0 && (axes & 1) == 0) ? false : true;
    }

    @Override // androidx.core.view.n0
    public void onStopNestedScroll(View target, int type) {
        q0 q0Var = this.nestedScrollingParentHelper;
        if (type == 1) {
            q0Var.f2298b = 0;
        } else {
            q0Var.f2297a = 0;
        }
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    public final void remeasure() {
        int i10;
        int i11 = this.lastWidthMeasureSpec;
        if (i11 == Integer.MIN_VALUE || (i10 = this.lastHeightMeasureSpec) == Integer.MIN_VALUE) {
            return;
        }
        measure(i11, i10);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        l<? super Boolean, t0> lVar = this.onRequestDisallowInterceptTouchEvent;
        if (lVar != null) {
            lVar.invoke(Boolean.valueOf(disallowIntercept));
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public final void setDensity(Density density) {
        if (density != this.density) {
            this.density = density;
            l<? super Density, t0> lVar = this.onDensityChanged;
            if (lVar != null) {
                lVar.invoke(density);
            }
        }
    }

    public final void setLifecycleOwner(y yVar) {
        if (yVar != this.lifecycleOwner) {
            this.lifecycleOwner = yVar;
            z0.i(this, yVar);
        }
    }

    public final void setModifier(Modifier modifier) {
        if (modifier != this.modifier) {
            this.modifier = modifier;
            l<? super Modifier, t0> lVar = this.onModifierChanged;
            if (lVar != null) {
                lVar.invoke(modifier);
            }
        }
    }

    public final void setOnDensityChanged$ui_release(l<? super Density, t0> lVar) {
        this.onDensityChanged = lVar;
    }

    public final void setOnModifierChanged$ui_release(l<? super Modifier, t0> lVar) {
        this.onModifierChanged = lVar;
    }

    public final void setOnRequestDisallowInterceptTouchEvent$ui_release(l<? super Boolean, t0> lVar) {
        this.onRequestDisallowInterceptTouchEvent = lVar;
    }

    public final void setRelease(r7.a<t0> aVar) {
        this.release = aVar;
    }

    public final void setReset(r7.a<t0> aVar) {
        this.reset = aVar;
    }

    public final void setSavedStateRegistryOwner(g gVar) {
        if (gVar != this.savedStateRegistryOwner) {
            this.savedStateRegistryOwner = gVar;
            f.w(this, gVar);
        }
    }

    public final void setUpdate(r7.a<t0> aVar) {
        this.update = aVar;
        this.hasUpdateBlock = true;
        this.runUpdate.invoke();
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // androidx.core.view.n0
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (isNestedScrollingEnabled()) {
            this.dispatcher.m4414dispatchPostScrollDzOQY0M(OffsetKt.Offset(AndroidViewHolder_androidKt.toComposeOffset(dxConsumed), AndroidViewHolder_androidKt.toComposeOffset(dyConsumed)), OffsetKt.Offset(AndroidViewHolder_androidKt.toComposeOffset(dxUnconsumed), AndroidViewHolder_androidKt.toComposeOffset(dyUnconsumed)), AndroidViewHolder_androidKt.toNestedScrollSource(type));
        }
    }
}
