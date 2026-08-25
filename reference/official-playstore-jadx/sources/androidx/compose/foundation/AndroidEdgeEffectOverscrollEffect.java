package androidx.compose.foundation;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J6\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ?\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00112\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001c\u001a\u00020\u0017*\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b\u001f\u0010 J'\u0010&\u001a\u00020\u001e*\u00020\u001b2\u0006\u0010\"\u001a\u00020!2\n\u0010%\u001a\u00060#j\u0002`$H\u0002¢\u0006\u0004\b&\u0010'J'\u0010)\u001a\u00020\u001e*\u00020\u001b2\u0006\u0010(\u001a\u00020!2\n\u0010%\u001a\u00060#j\u0002`$H\u0002¢\u0006\u0004\b)\u0010'J'\u0010+\u001a\u00020\u001e*\u00020\u001b2\u0006\u0010*\u001a\u00020!2\n\u0010%\u001a\u00060#j\u0002`$H\u0002¢\u0006\u0004\b+\u0010'J'\u0010-\u001a\u00020\u001e*\u00020\u001b2\u0006\u0010,\u001a\u00020!2\n\u0010%\u001a\u00060#j\u0002`$H\u0002¢\u0006\u0004\b-\u0010'J\u000f\u0010.\u001a\u00020\u0017H\u0002¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0017H\u0002¢\u0006\u0004\b0\u0010/J\u001a\u00103\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102J\"\u00109\u001a\u0002062\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b7\u00108J\"\u0010;\u001a\u0002062\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b:\u00108J\"\u0010=\u001a\u0002062\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b<\u00108J\"\u0010?\u001a\u0002062\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b>\u00108R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010@R\u001e\u0010A\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010DR\u0014\u0010F\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010DR\u0014\u0010G\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010DR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020!0H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010DR\u0014\u0010L\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010DR\u0014\u0010M\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010DR\u0014\u0010N\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010DR\u0016\u0010P\u001a\u00020O8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010QR+\u0010Y\u001a\u00020O2\u0006\u0010R\u001a\u00020O8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR(\u0010Z\u001a\u00020\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\bZ\u0010[\u0012\u0004\b_\u0010/\u001a\u0004\b\\\u0010 \"\u0004\b]\u0010^R\u0016\u0010`\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010[R\u001c\u0010b\u001a\u00020a8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bb\u0010cR \u0010e\u001a\u000e\u0012\u0004\u0012\u00020d\u0012\u0004\u0012\u00020\u00170\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u001e\u0010h\u001a\u0004\u0018\u00010g8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bh\u0010iR\u001a\u0010k\u001a\u00020j8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bm\u0010nR\u0014\u0010o\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006p"}, d2 = {"Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "Landroid/content/Context;", "context", "Landroidx/compose/foundation/OverscrollConfiguration;", "overscrollConfig", "<init>", "(Landroid/content/Context;Landroidx/compose/foundation/OverscrollConfiguration;)V", "Landroidx/compose/ui/geometry/Offset;", "delta", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "Lkotlin/Function1;", "performScroll", "applyToScroll-Rhakbz0", "(JILr7/l;)J", "applyToScroll", "Landroidx/compose/ui/unit/Velocity;", "velocity", "Lkotlin/Function2;", "Ld7/d;", "", "performFling", "Lx6/t0;", "applyToFling-BMRW4eQ", "(JLr7/p;Ld7/d;)Ljava/lang/Object;", "applyToFling", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawOverscroll", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "", "stopOverscrollAnimation", "()Z", "Landroid/widget/EdgeEffect;", TtmlNode.LEFT, "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "canvas", "drawLeft", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroid/widget/EdgeEffect;Landroid/graphics/Canvas;)Z", "top", "drawTop", TtmlNode.RIGHT, "drawRight", "bottom", "drawBottom", "invalidateOverscroll", "()V", "animateToRelease", "releaseOppositeOverscroll-k-4lQ0M", "(J)Z", "releaseOppositeOverscroll", "scroll", "displacement", "", "pullTop-0a9Yr6o", "(JJ)F", "pullTop", "pullBottom-0a9Yr6o", "pullBottom", "pullLeft-0a9Yr6o", "pullLeft", "pullRight-0a9Yr6o", "pullRight", "Landroidx/compose/foundation/OverscrollConfiguration;", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "topEffect", "Landroid/widget/EdgeEffect;", "bottomEffect", "leftEffect", "rightEffect", "", "allEffects", "Ljava/util/List;", "topEffectNegation", "bottomEffectNegation", "leftEffectNegation", "rightEffectNegation", "", "consumeCount", "I", "<set-?>", "invalidateCount$delegate", "Landroidx/compose/runtime/MutableIntState;", "getInvalidateCount", "()I", "setInvalidateCount", "(I)V", "invalidateCount", "invalidationEnabled", "Z", "getInvalidationEnabled$foundation_release", "setInvalidationEnabled$foundation_release", "(Z)V", "getInvalidationEnabled$foundation_release$annotations", "scrollCycleInProgress", "Landroidx/compose/ui/geometry/Size;", "containerSize", "J", "Landroidx/compose/ui/unit/IntSize;", "onNewSize", "Lr7/l;", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "Landroidx/compose/ui/Modifier;", "effectModifier", "Landroidx/compose/ui/Modifier;", "getEffectModifier", "()Landroidx/compose/ui/Modifier;", "isInProgress", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    public static final int $stable = 0;
    private final List<EdgeEffect> allEffects;
    private final EdgeEffect bottomEffect;
    private final EdgeEffect bottomEffectNegation;
    private int consumeCount;
    private long containerSize;
    private final Modifier effectModifier;

    /* JADX INFO: renamed from: invalidateCount$delegate, reason: from kotlin metadata */
    private final MutableIntState invalidateCount;
    private boolean invalidationEnabled;
    private final EdgeEffect leftEffect;
    private final EdgeEffect leftEffectNegation;
    private final l<IntSize, t0> onNewSize;
    private final OverscrollConfiguration overscrollConfig;
    private PointerId pointerId;
    private Offset pointerPosition;
    private final EdgeEffect rightEffect;
    private final EdgeEffect rightEffectNegation;
    private boolean scrollCycleInProgress;
    private final EdgeEffect topEffect;
    private final EdgeEffect topEffectNegation;

    public AndroidEdgeEffectOverscrollEffect(Context context, OverscrollConfiguration overscrollConfiguration) {
        this.overscrollConfig = overscrollConfiguration;
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        EdgeEffect edgeEffectCreate = edgeEffectCompat.create(context, null);
        this.topEffect = edgeEffectCreate;
        EdgeEffect edgeEffectCreate2 = edgeEffectCompat.create(context, null);
        this.bottomEffect = edgeEffectCreate2;
        EdgeEffect edgeEffectCreate3 = edgeEffectCompat.create(context, null);
        this.leftEffect = edgeEffectCreate3;
        EdgeEffect edgeEffectCreate4 = edgeEffectCompat.create(context, null);
        this.rightEffect = edgeEffectCreate4;
        List<EdgeEffect> listE = t7.a.E(edgeEffectCreate3, edgeEffectCreate, edgeEffectCreate4, edgeEffectCreate2);
        this.allEffects = listE;
        this.topEffectNegation = edgeEffectCompat.create(context, null);
        this.bottomEffectNegation = edgeEffectCompat.create(context, null);
        this.leftEffectNegation = edgeEffectCompat.create(context, null);
        this.rightEffectNegation = edgeEffectCompat.create(context, null);
        int size = listE.size();
        for (int i10 = 0; i10 < size; i10++) {
            listE.get(i10).setColor(ColorKt.m3526toArgb8_81llA(this.overscrollConfig.getGlowColor()));
        }
        this.consumeCount = -1;
        this.invalidateCount = SnapshotIntStateKt.mutableIntStateOf(0);
        this.invalidationEnabled = true;
        this.containerSize = Size.INSTANCE.m3314getZeroNHjbRc();
        AndroidEdgeEffectOverscrollEffect$onNewSize$1 androidEdgeEffectOverscrollEffect$onNewSize$1 = new AndroidEdgeEffectOverscrollEffect$onNewSize$1(this);
        this.onNewSize = androidEdgeEffectOverscrollEffect$onNewSize$1;
        this.effectModifier = OnRemeasuredModifierKt.onSizeChanged(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE.then(AndroidOverscroll_androidKt.StretchOverscrollNonClippingLayer), t0.f22605a, new AndroidEdgeEffectOverscrollEffect$effectModifier$1(this, null)), androidEdgeEffectOverscrollEffect$onNewSize$1).then(new DrawOverscrollModifier(this, InspectableValueKt.isDebugInspectorInfoEnabled() ? new AndroidEdgeEffectOverscrollEffect$special$$inlined$debugInspectorInfo$1(this) : InspectableValueKt.getNoInspectorInfo()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateToRelease() {
        List<EdgeEffect> list = this.allEffects;
        int size = list.size();
        boolean z = false;
        for (int i10 = 0; i10 < size; i10++) {
            EdgeEffect edgeEffect = list.get(i10);
            edgeEffect.onRelease();
            z = edgeEffect.isFinished() || z;
        }
        if (z) {
            invalidateOverscroll();
        }
    }

    private final boolean drawBottom(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(180.0f);
        canvas.translate(-Size.m3305getWidthimpl(this.containerSize), (-Size.m3302getHeightimpl(this.containerSize)) + drawScope.mo285toPx0680j_4(this.overscrollConfig.getDrawPadding().getBottom()));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    private final boolean drawLeft(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(270.0f);
        canvas.translate(-Size.m3302getHeightimpl(this.containerSize), drawScope.mo285toPx0680j_4(this.overscrollConfig.getDrawPadding().mo480calculateLeftPaddingu2uoSUM(drawScope.getLayoutDirection())));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    private final boolean drawRight(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        int iM = t7.a.M(Size.m3305getWidthimpl(this.containerSize));
        float fMo481calculateRightPaddingu2uoSUM = this.overscrollConfig.getDrawPadding().mo481calculateRightPaddingu2uoSUM(drawScope.getLayoutDirection());
        canvas.rotate(90.0f);
        canvas.translate(0.0f, drawScope.mo285toPx0680j_4(fMo481calculateRightPaddingu2uoSUM) + (-iM));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    private final boolean drawTop(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.translate(0.0f, drawScope.mo285toPx0680j_4(this.overscrollConfig.getDrawPadding().getTop()));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    private final int getInvalidateCount() {
        return this.invalidateCount.getIntValue();
    }

    public static /* synthetic */ void getInvalidationEnabled$foundation_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateOverscroll() {
        if (this.invalidationEnabled && this.consumeCount == getInvalidateCount()) {
            setInvalidateCount(getInvalidateCount() + 1);
        }
    }

    /* JADX INFO: renamed from: pullBottom-0a9Yr6o, reason: not valid java name */
    private final float m154pullBottom0a9Yr6o(long scroll, long displacement) {
        float fM3236getXimpl = Offset.m3236getXimpl(displacement) / Size.m3305getWidthimpl(this.containerSize);
        float fM3237getYimpl = Offset.m3237getYimpl(scroll) / Size.m3302getHeightimpl(this.containerSize);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(this.bottomEffect) == 0.0f ? Size.m3302getHeightimpl(this.containerSize) * (-edgeEffectCompat.onPullDistanceCompat(this.bottomEffect, -fM3237getYimpl, 1 - fM3236getXimpl)) : Offset.m3237getYimpl(scroll);
    }

    /* JADX INFO: renamed from: pullLeft-0a9Yr6o, reason: not valid java name */
    private final float m155pullLeft0a9Yr6o(long scroll, long displacement) {
        float fM3237getYimpl = Offset.m3237getYimpl(displacement) / Size.m3302getHeightimpl(this.containerSize);
        float fM3236getXimpl = Offset.m3236getXimpl(scroll) / Size.m3305getWidthimpl(this.containerSize);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(this.leftEffect) == 0.0f ? Size.m3305getWidthimpl(this.containerSize) * edgeEffectCompat.onPullDistanceCompat(this.leftEffect, fM3236getXimpl, 1 - fM3237getYimpl) : Offset.m3236getXimpl(scroll);
    }

    /* JADX INFO: renamed from: pullRight-0a9Yr6o, reason: not valid java name */
    private final float m156pullRight0a9Yr6o(long scroll, long displacement) {
        float fM3237getYimpl = Offset.m3237getYimpl(displacement) / Size.m3302getHeightimpl(this.containerSize);
        float fM3236getXimpl = Offset.m3236getXimpl(scroll) / Size.m3305getWidthimpl(this.containerSize);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(this.rightEffect) == 0.0f ? Size.m3305getWidthimpl(this.containerSize) * (-edgeEffectCompat.onPullDistanceCompat(this.rightEffect, -fM3236getXimpl, fM3237getYimpl)) : Offset.m3236getXimpl(scroll);
    }

    /* JADX INFO: renamed from: pullTop-0a9Yr6o, reason: not valid java name */
    private final float m157pullTop0a9Yr6o(long scroll, long displacement) {
        float fM3236getXimpl = Offset.m3236getXimpl(displacement) / Size.m3305getWidthimpl(this.containerSize);
        float fM3237getYimpl = Offset.m3237getYimpl(scroll) / Size.m3302getHeightimpl(this.containerSize);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(this.topEffect) == 0.0f ? Size.m3302getHeightimpl(this.containerSize) * edgeEffectCompat.onPullDistanceCompat(this.topEffect, fM3237getYimpl, fM3236getXimpl) : Offset.m3237getYimpl(scroll);
    }

    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m158releaseOppositeOverscrollk4lQ0M(long delta) {
        boolean zIsFinished;
        if (this.leftEffect.isFinished() || Offset.m3236getXimpl(delta) >= 0.0f) {
            zIsFinished = false;
        } else {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.leftEffect, Offset.m3236getXimpl(delta));
            zIsFinished = this.leftEffect.isFinished();
        }
        if (!this.rightEffect.isFinished() && Offset.m3236getXimpl(delta) > 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.rightEffect, Offset.m3236getXimpl(delta));
            zIsFinished = zIsFinished || this.rightEffect.isFinished();
        }
        if (!this.topEffect.isFinished() && Offset.m3237getYimpl(delta) < 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.topEffect, Offset.m3237getYimpl(delta));
            zIsFinished = zIsFinished || this.topEffect.isFinished();
        }
        if (this.bottomEffect.isFinished() || Offset.m3237getYimpl(delta) <= 0.0f) {
            return zIsFinished;
        }
        EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.bottomEffect, Offset.m3237getYimpl(delta));
        return zIsFinished || this.bottomEffect.isFinished();
    }

    private final void setInvalidateCount(int i10) {
        this.invalidateCount.setIntValue(i10);
    }

    private final boolean stopOverscrollAnimation() {
        boolean z;
        long jM3315getCenteruvyYCjk = SizeKt.m3315getCenteruvyYCjk(this.containerSize);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        if (edgeEffectCompat.getDistanceCompat(this.leftEffect) == 0.0f) {
            z = false;
        } else {
            m155pullLeft0a9Yr6o(Offset.INSTANCE.m3252getZeroF1C5BW0(), jM3315getCenteruvyYCjk);
            z = true;
        }
        if (edgeEffectCompat.getDistanceCompat(this.rightEffect) != 0.0f) {
            m156pullRight0a9Yr6o(Offset.INSTANCE.m3252getZeroF1C5BW0(), jM3315getCenteruvyYCjk);
            z = true;
        }
        if (edgeEffectCompat.getDistanceCompat(this.topEffect) != 0.0f) {
            m157pullTop0a9Yr6o(Offset.INSTANCE.m3252getZeroF1C5BW0(), jM3315getCenteruvyYCjk);
            z = true;
        }
        if (edgeEffectCompat.getDistanceCompat(this.bottomEffect) == 0.0f) {
            return z;
        }
        m154pullBottom0a9Yr6o(Offset.INSTANCE.m3252getZeroF1C5BW0(), jM3315getCenteruvyYCjk);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo159applyToFlingBMRW4eQ(long r11, r7.p<? super androidx.compose.ui.unit.Velocity, ? super d7.d<? super androidx.compose.ui.unit.Velocity>, ? extends java.lang.Object> r13, d7.d<? super x6.t0> r14) {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo159applyToFlingBMRW4eQ(long, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013a A[ADDED_TO_REGION] */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long mo160applyToScrollRhakbz0(long r18, int r20, r7.l<? super androidx.compose.ui.geometry.Offset, androidx.compose.ui.geometry.Offset> r21) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo160applyToScrollRhakbz0(long, int, r7.l):long");
    }

    public final void drawOverscroll(DrawScope drawScope) {
        boolean zDrawLeft;
        if (Size.m3307isEmptyimpl(this.containerSize)) {
            return;
        }
        androidx.compose.ui.graphics.Canvas canvas = drawScope.getDrawContext().getCanvas();
        this.consumeCount = getInvalidateCount();
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        if (edgeEffectCompat.getDistanceCompat(this.leftEffectNegation) != 0.0f) {
            drawRight(drawScope, this.leftEffectNegation, nativeCanvas);
            this.leftEffectNegation.finish();
        }
        if (this.leftEffect.isFinished()) {
            zDrawLeft = false;
        } else {
            zDrawLeft = drawLeft(drawScope, this.leftEffect, nativeCanvas);
            edgeEffectCompat.onPullDistanceCompat(this.leftEffectNegation, edgeEffectCompat.getDistanceCompat(this.leftEffect), 0.0f);
        }
        if (edgeEffectCompat.getDistanceCompat(this.topEffectNegation) != 0.0f) {
            drawBottom(drawScope, this.topEffectNegation, nativeCanvas);
            this.topEffectNegation.finish();
        }
        if (!this.topEffect.isFinished()) {
            zDrawLeft = drawTop(drawScope, this.topEffect, nativeCanvas) || zDrawLeft;
            edgeEffectCompat.onPullDistanceCompat(this.topEffectNegation, edgeEffectCompat.getDistanceCompat(this.topEffect), 0.0f);
        }
        if (edgeEffectCompat.getDistanceCompat(this.rightEffectNegation) != 0.0f) {
            drawLeft(drawScope, this.rightEffectNegation, nativeCanvas);
            this.rightEffectNegation.finish();
        }
        if (!this.rightEffect.isFinished()) {
            zDrawLeft = drawRight(drawScope, this.rightEffect, nativeCanvas) || zDrawLeft;
            edgeEffectCompat.onPullDistanceCompat(this.rightEffectNegation, edgeEffectCompat.getDistanceCompat(this.rightEffect), 0.0f);
        }
        if (edgeEffectCompat.getDistanceCompat(this.bottomEffectNegation) != 0.0f) {
            drawTop(drawScope, this.bottomEffectNegation, nativeCanvas);
            this.bottomEffectNegation.finish();
        }
        if (!this.bottomEffect.isFinished()) {
            boolean z = drawBottom(drawScope, this.bottomEffect, nativeCanvas) || zDrawLeft;
            edgeEffectCompat.onPullDistanceCompat(this.bottomEffectNegation, edgeEffectCompat.getDistanceCompat(this.bottomEffect), 0.0f);
            zDrawLeft = z;
        }
        if (zDrawLeft) {
            invalidateOverscroll();
        }
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public Modifier getEffectModifier() {
        return this.effectModifier;
    }

    /* JADX INFO: renamed from: getInvalidationEnabled$foundation_release, reason: from getter */
    public final boolean getInvalidationEnabled() {
        return this.invalidationEnabled;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        List<EdgeEffect> list = this.allEffects;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(list.get(i10)) == 0.0f)) {
                return true;
            }
        }
        return false;
    }

    public final void setInvalidationEnabled$foundation_release(boolean z) {
        this.invalidationEnabled = z;
    }
}
