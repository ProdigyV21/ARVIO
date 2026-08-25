package coil.compose;

import a0.c;
import android.view.Dimension;
import android.view.Dimensions;
import android.view.Size;
import android.view.SizeResolver;
import android.view.SizeResolvers;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÛ\u0001\u0010 \u001a\u00020\u000e2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u009d\u0001\u0010 \u001a\u00020\u000e2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0014\b\u0002\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0\f2\u0016\b\u0002\u0010#\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001aK\u0010'\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0001¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010+\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\u0016H\u0001¢\u0006\u0004\b+\u0010,\u001a\u001d\u0010\u0003\u001a\u00020\u0006*\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0003¢\u0006\u0004\b\u0003\u0010-\u001a\u001b\u00102\u001a\u0004\u0018\u00010/*\u00020.H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00063"}, d2 = {"", "model", "", "contentDescription", "Lcoil/ImageLoader;", "imageLoader", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/painter/Painter;", "placeholder", "error", "fallback", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Success;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Error;", "onError", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "AsyncImage-Q4Kwu38", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "AsyncImage", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "AsyncImage-MvsnxeU", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "painter", "Content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;I)V", "Lcoil/request/ImageRequest;", "request", "updateRequest", "(Lcoil/request/ImageRequest;Landroidx/compose/ui/layout/ContentScale;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Constraints;", "Lcoil/size/Size;", "toSizeOrNull-BRTryo0", "(J)Lcoil/size/Size;", "toSizeOrNull", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AsyncImageKt {

    /* JADX INFO: renamed from: coil.compose.AsyncImageKt$Content$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ Alignment $alignment;
        final /* synthetic */ float $alpha;
        final /* synthetic */ ColorFilter $colorFilter;
        final /* synthetic */ String $contentDescription;
        final /* synthetic */ ContentScale $contentScale;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Painter $painter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Modifier modifier, Painter painter, String str, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$painter = painter;
            this.$contentDescription = str;
            this.$alignment = alignment;
            this.$contentScale = contentScale;
            this.$alpha = f10;
            this.$colorFilter = colorFilter;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AsyncImageKt.Content(this.$modifier, this.$painter, this.$contentDescription, this.$alignment, this.$contentScale, this.$alpha, this.$colorFilter, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: coil.compose.AsyncImageKt$contentDescription$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C06411 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ String $contentDescription;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06411(String str) {
            super(1);
            this.$contentDescription = str;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, this.$contentDescription);
            SemanticsPropertiesKt.m5039setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5026getImageo7Vup1c());
        }
    }

    /* JADX INFO: renamed from: AsyncImage-MvsnxeU, reason: not valid java name */
    public static final void m6026AsyncImageMvsnxeU(Object obj, String str, ImageLoader imageLoader, Modifier modifier, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12, int i13) {
        int iM3947getDefaultFilterQualityfv9h1I;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2030202961);
        Modifier modifier2 = (i13 & 8) != 0 ? Modifier.INSTANCE : modifier;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform = (i13 & 16) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : lVar;
        l<? super AsyncImagePainter.State, t0> lVar3 = (i13 & 32) != 0 ? null : lVar2;
        Alignment center = (i13 & 64) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 128) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 256) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 512) != 0 ? null : colorFilter;
        if ((i13 & 1024) != 0) {
            i14 = i12 & (-15);
            iM3947getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        } else {
            iM3947getDefaultFilterQualityfv9h1I = i10;
            i14 = i12;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2030202961, i11, i14, "coil.compose.AsyncImage (AsyncImage.kt:130)");
        }
        ImageRequest imageRequestUpdateRequest = updateRequest(UtilsKt.requestOf(obj, composerStartRestartGroup, 8), fit, composerStartRestartGroup, 8 | ((i11 >> 18) & 112));
        int i15 = i11 >> 6;
        int i16 = i11 >> 9;
        int i17 = i16 & 57344;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar4 = defaultTransform;
        l<? super AsyncImagePainter.State, t0> lVar5 = lVar3;
        ContentScale contentScale2 = fit;
        int i18 = iM3947getDefaultFilterQualityfv9h1I;
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(imageRequestUpdateRequest, imageLoader, lVar4, lVar5, contentScale2, i18, composerStartRestartGroup, ((i14 << 15) & 458752) | (i15 & 7168) | (i15 & 896) | 72 | i17, 0);
        SizeResolver sizeResolver = imageRequestUpdateRequest.getSizeResolver();
        Content(sizeResolver instanceof ConstraintsSizeResolver ? modifier2.then((Modifier) sizeResolver) : modifier2, asyncImagePainterM6035rememberAsyncImagePainter5jETZwI, str, center, fit, f11, colorFilter2, composerStartRestartGroup, (i16 & 7168) | ((i11 << 3) & 896) | i17 | (i16 & 458752) | (3670016 & i16));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new AsyncImageKt$AsyncImage$1(obj, str, imageLoader, modifier2, defaultTransform, lVar3, center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, i11, i12, i13));
    }

    /* JADX INFO: renamed from: AsyncImage-Q4Kwu38, reason: not valid java name */
    public static final void m6027AsyncImageQ4Kwu38(Object obj, String str, ImageLoader imageLoader, Modifier modifier, Painter painter, Painter painter2, Painter painter3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12, int i13) {
        composer.startReplaceableGroup(-245964807);
        Modifier modifier2 = (i13 & 8) != 0 ? Modifier.INSTANCE : modifier;
        Painter painter4 = (i13 & 16) != 0 ? null : painter;
        Painter painter5 = (i13 & 32) != 0 ? null : painter2;
        Painter painter6 = (i13 & 64) != 0 ? painter5 : painter3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i13 & 128) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i13 & 256) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i13 & 512) != 0 ? null : lVar3;
        Alignment center = (i13 & 1024) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 2048) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 4096) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 8192) != 0 ? null : colorFilter;
        int iM3947getDefaultFilterQualityfv9h1I = (i13 & 16384) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-245964807, i11, i12, "coil.compose.AsyncImage (AsyncImage.kt:80)");
        }
        int i14 = i12 << 18;
        m6026AsyncImageMvsnxeU(obj, str, imageLoader, modifier2, UtilsKt.transformOf(painter4, painter5, painter6), UtilsKt.onStateOf(lVar4, lVar5, lVar6), center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, composer, (i11 & 7168) | (i11 & 112) | 520 | (3670016 & i14) | (29360128 & i14) | (234881024 & i14) | (i14 & 1879048192), (i12 >> 12) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final void Content(Modifier modifier, Painter painter, String str, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(10290533);
        if (ComposerKt.isTraceInProgress()) {
            i11 = i10;
            ComposerKt.traceEventStart(10290533, i11, -1, "coil.compose.Content (AsyncImage.kt:164)");
        } else {
            i11 = i10;
        }
        Modifier modifierThen = ClipKt.clipToBounds(contentDescription(modifier, str)).then(new ContentPainterModifier(painter, alignment, contentScale, f10, colorFilter));
        AnonymousClass1 anonymousClass1 = new MeasurePolicy() { // from class: coil.compose.AsyncImageKt.Content.1

            /* JADX INFO: renamed from: coil.compose.AsyncImageKt$Content$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
            public static final class C01271 extends r implements l<Placeable.PlacementScope, t0> {
                public static final C01271 INSTANCE = new C01271();

                public C01271() {
                    super(1);
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return t0.f22605a;
                }
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                return g.a(this, intrinsicMeasureScope, list, i12);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                return g.b(this, intrinsicMeasureScope, list, i12);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                return MeasureScope.CC.q(measureScope, Constraints.m5648getMinWidthimpl(j10), Constraints.m5647getMinHeightimpl(j10), null, C01271.INSTANCE, 4, null);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                return g.c(this, intrinsicMeasureScope, list, i12);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                return g.d(this, intrinsicMeasureScope, list, i12);
            }
        };
        composerStartRestartGroup.startReplaceableGroup(544976794);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        a<ComposeUiNode> constructor = companion.getConstructor();
        composerStartRestartGroup.startReplaceableGroup(1405779621);
        if (composerStartRestartGroup.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(new AsyncImageKt$Content$$inlined$Layout$1(constructor));
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
        Updater.m2998setimpl(composerM2991constructorimpl, anonymousClass1, companion.getSetMeasurePolicy());
        Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        Updater.m2998setimpl(composerM2991constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
        }
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(modifier, painter, str, alignment, contentScale, f10, colorFilter, i11));
    }

    private static final Modifier contentDescription(Modifier modifier, String str) {
        return str != null ? SemanticsModifierKt.semantics$default(modifier, false, new C06411(str), 1, null) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toSizeOrNull-BRTryo0, reason: not valid java name */
    public static final Size m6029toSizeOrNullBRTryo0(long j10) {
        if (Constraints.m5650isZeroimpl(j10)) {
            return null;
        }
        return new Size(Constraints.m5642getHasBoundedWidthimpl(j10) ? Dimensions.Dimension(Constraints.m5646getMaxWidthimpl(j10)) : Dimension.Undefined.INSTANCE, Constraints.m5641getHasBoundedHeightimpl(j10) ? Dimensions.Dimension(Constraints.m5645getMaxHeightimpl(j10)) : Dimension.Undefined.INSTANCE);
    }

    public static final ImageRequest updateRequest(ImageRequest imageRequest, ContentScale contentScale, Composer composer, int i10) {
        SizeResolver sizeResolverCreate;
        composer.startReplaceableGroup(402368983);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(402368983, i10, -1, "coil.compose.updateRequest (AsyncImage.kt:183)");
        }
        if (imageRequest.getDefined().getSizeResolver() == null) {
            if (kotlin.jvm.internal.p.a(contentScale, ContentScale.INSTANCE.getNone())) {
                sizeResolverCreate = SizeResolvers.create(Size.ORIGINAL);
            } else {
                composer.startReplaceableGroup(-492369756);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new ConstraintsSizeResolver();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                sizeResolverCreate = (SizeResolver) objRememberedValue;
            }
            imageRequest = ImageRequest.newBuilder$default(imageRequest, null, 1, null).size(sizeResolverCreate).build();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return imageRequest;
    }
}
