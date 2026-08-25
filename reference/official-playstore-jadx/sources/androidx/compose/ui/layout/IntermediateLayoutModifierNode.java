package androidx.compose.ui.layout;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0002BCBJ\u0012A\u0010\r\u001a=\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003¢\u0006\u0002\b\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J&\u0010\u0016\u001a\u00020\u000b*\u00020\u00132\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J4\u0010\u001c\u001a\u00020\u000b*\u00020\u00132\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ#\u0010#\u001a\u00020\u001f*\u00020\u001d2\u0006\u0010\b\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0004\b!\u0010\"J#\u0010&\u001a\u00020\u001f*\u00020\u001d2\u0006\u0010\b\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001fH\u0000¢\u0006\u0004\b%\u0010\"J#\u0010(\u001a\u00020\u001f*\u00020\u001d2\u0006\u0010\b\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0004\b'\u0010\"J#\u0010*\u001a\u00020\u001f*\u00020\u001d2\u0006\u0010\b\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001fH\u0000¢\u0006\u0004\b)\u0010\"R]\u0010\r\u001a=\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0003¢\u0006\u0002\b\f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\r\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010\u000fR\u0018\u00100\u001a\u00060/R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\"\u00109\u001a\u0002088\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b9\u0010;\"\u0004\b<\u0010=R\u001e\u0010\u0019\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0019\u0010>R\u001c\u0010@\u001a\b\u0018\u00010?R\u00020\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010A\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006D"}, d2 = {"Landroidx/compose/ui/layout/IntermediateLayoutModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/IntermediateMeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "Lx6/y;", ContentDisposition.Parameters.Name, "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "measureBlock", "<init>", "(Lr7/q;)V", "Lx6/t0;", "onAttach", "()V", "Landroidx/compose/ui/layout/MeasureScope;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/unit/IntSize;", "lookaheadSize", "lookaheadConstraints", "intermediateMeasure-Te-uZzU", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;JJJ)Landroidx/compose/ui/layout/MeasureResult;", "intermediateMeasure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntermediateIntrinsicWidth$ui_release", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "minIntermediateIntrinsicWidth", "width", "minIntermediateIntrinsicHeight$ui_release", "minIntermediateIntrinsicHeight", "maxIntermediateIntrinsicWidth$ui_release", "maxIntermediateIntrinsicWidth", "maxIntermediateIntrinsicHeight$ui_release", "maxIntermediateIntrinsicHeight", "Lr7/q;", "getMeasureBlock$ui_release", "()Lr7/q;", "setMeasureBlock$ui_release", "Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasureScopeImpl;", "intermediateMeasureScope", "Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasureScopeImpl;", "Landroidx/compose/ui/layout/LookaheadScopeImpl;", "localLookaheadScope", "Landroidx/compose/ui/layout/LookaheadScopeImpl;", "Landroidx/compose/ui/layout/LookaheadScope;", "closestLookaheadScope", "Landroidx/compose/ui/layout/LookaheadScope;", "", "isIntermediateChangeActive", "Z", "()Z", "setIntermediateChangeActive", "(Z)V", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasurablePlaceable;", "intermediateMeasurable", "Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasurablePlaceable;", "IntermediateMeasurablePlaceable", "IntermediateMeasureScopeImpl", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntermediateLayoutModifierNode extends Modifier.Node implements LayoutModifierNode {
    public static final int $stable = 8;
    private LookaheadScope closestLookaheadScope;
    private IntermediateMeasurablePlaceable intermediateMeasurable;
    private final IntermediateMeasureScopeImpl intermediateMeasureScope = new IntermediateMeasureScopeImpl();
    private boolean isIntermediateChangeActive;
    private final LookaheadScopeImpl localLookaheadScope;
    private Constraints lookaheadConstraints;
    private q<? super IntermediateMeasureScope, ? super Measurable, ? super Constraints, ? extends MeasureResult> measureBlock;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\n\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ=\u0010\u0016\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0019\u0010\u0013\u001a\u0015\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f¢\u0006\u0002\b\u0012H\u0014ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001f\u0010\u001eJ\u0017\u0010!\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H\u0016¢\u0006\u0004\b!\u0010\u001eJ\u0017\u0010\"\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H\u0016¢\u0006\u0004\b\"\u0010\u001eR\"\u0010\u0003\u001a\u00020\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010(\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0016\u00101\u001a\u0004\u0018\u00010.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasurablePlaceable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/Placeable;", "wrappedMeasurable", "<init>", "(Landroidx/compose/ui/layout/IntermediateLayoutModifierNode;Landroidx/compose/ui/layout/Measurable;)V", "Landroidx/compose/ui/unit/Constraints;", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "measure", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/t0;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLine", "", "get", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "height", "minIntrinsicWidth", "(I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/Measurable;", "getWrappedMeasurable", "()Landroidx/compose/ui/layout/Measurable;", "setWrappedMeasurable", "(Landroidx/compose/ui/layout/Measurable;)V", "wrappedPlaceable", "Landroidx/compose/ui/layout/Placeable;", "getWrappedPlaceable", "()Landroidx/compose/ui/layout/Placeable;", "setWrappedPlaceable", "(Landroidx/compose/ui/layout/Placeable;)V", "", "getParentData", "()Ljava/lang/Object;", "parentData", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class IntermediateMeasurablePlaceable extends Placeable implements Measurable {
        private Measurable wrappedMeasurable;
        private Placeable wrappedPlaceable;

        public IntermediateMeasurablePlaceable(Measurable measurable) {
            this.wrappedMeasurable = measurable;
        }

        @Override // androidx.compose.ui.layout.Measured
        public int get(AlignmentLine alignmentLine) {
            return this.wrappedPlaceable.get(alignmentLine);
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
        public Object getParentData() {
            return this.wrappedMeasurable.getParentData();
        }

        public final Measurable getWrappedMeasurable() {
            return this.wrappedMeasurable;
        }

        public final Placeable getWrappedPlaceable() {
            return this.wrappedPlaceable;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            return this.wrappedMeasurable.maxIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            return this.wrappedMeasurable.maxIntrinsicWidth(height);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        public Placeable mo4631measureBRTryo0(long constraints) {
            Placeable placeableMo4631measureBRTryo0;
            if (IntermediateLayoutModifierNode.this.getIsIntermediateChangeActive()) {
                placeableMo4631measureBRTryo0 = this.wrappedMeasurable.mo4631measureBRTryo0(constraints);
                m4668setMeasurementConstraintsBRTryo0(constraints);
                m4667setMeasuredSizeozmzZPI(IntSizeKt.IntSize(placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight()));
            } else {
                placeableMo4631measureBRTryo0 = this.wrappedMeasurable.mo4631measureBRTryo0(IntermediateLayoutModifierNode.this.lookaheadConstraints.getValue());
                IntermediateLayoutModifierNode intermediateLayoutModifierNode = IntermediateLayoutModifierNode.this;
                m4668setMeasurementConstraintsBRTryo0(intermediateLayoutModifierNode.lookaheadConstraints.getValue());
                m4667setMeasuredSizeozmzZPI(intermediateLayoutModifierNode.getIsIntermediateChangeActive() ? IntSizeKt.IntSize(placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight()) : intermediateLayoutModifierNode.intermediateMeasureScope.getLookaheadSize());
            }
            this.wrappedPlaceable = placeableMo4631measureBRTryo0;
            return this;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            return this.wrappedMeasurable.minIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            return this.wrappedMeasurable.minIntrinsicWidth(height);
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* JADX INFO: renamed from: placeAt-f8xVGno */
        public void mo4632placeAtf8xVGno(long position, float zIndex, r7.l<? super GraphicsLayerScope, t0> layerBlock) {
            float f10;
            t0 t0Var;
            if (!IntermediateLayoutModifierNode.this.getIsIntermediateChangeActive()) {
                position = IntOffset.INSTANCE.m5812getZeronOccac();
            }
            long j10 = position;
            Placeable.PlacementScope placementScope = IntermediateLayoutModifierNode.this.getNode().getCoordinator().getPlacementScope();
            if (layerBlock != null) {
                Placeable placeable = this.wrappedPlaceable;
                f10 = zIndex;
                if (placeable != null) {
                    placementScope.m4678placeWithLayeraW9wM(placeable, j10, f10, layerBlock);
                    t0Var = t0.f22605a;
                } else {
                    t0Var = null;
                }
                if (t0Var != null) {
                    return;
                }
            } else {
                f10 = zIndex;
            }
            Placeable placeable2 = this.wrappedPlaceable;
            if (placeable2 != null) {
                placementScope.m4673place70tqf50(placeable2, j10, f10);
            }
        }

        public final void setWrappedMeasurable(Measurable measurable) {
            this.wrappedMeasurable = measurable;
        }

        public final void setWrappedPlaceable(Placeable placeable) {
            this.wrappedPlaceable = placeable;
        }
    }

    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0006\u001a\u00020\u0005*\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007JL\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u000b2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R(\u0010\u0017\u001a\u00020\u00168\u0016@\u0016X\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001f\u001a\u00020\u0005*\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010!\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010&\u001a\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010*\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010,\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)R\u0014\u00100\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00061"}, d2 = {"Landroidx/compose/ui/layout/IntermediateLayoutModifierNode$IntermediateMeasureScopeImpl;", "Landroidx/compose/ui/layout/IntermediateMeasureScope;", "Lka/k0;", "<init>", "(Landroidx/compose/ui/layout/IntermediateLayoutModifierNode;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "toLookaheadCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/layout/LayoutCoordinates;", "", "width", "height", "", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLines", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placementBlock", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "(IILjava/util/Map;Lr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/unit/IntSize;", "lookaheadSize", "J", "getLookaheadSize-YbymL2g", "()J", "setLookaheadSize-ozmzZPI", "(J)V", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "lookaheadScopeCoordinates", "", "isLookingAhead", "()Z", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "", "getDensity", "()F", "density", "getFontScale", "fontScale", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class IntermediateMeasureScopeImpl implements IntermediateMeasureScope, k0 {
        private long lookaheadSize = IntSize.INSTANCE.m5849getZeroYbymL2g();

        public IntermediateMeasureScopeImpl() {
        }

        @Override // androidx.compose.ui.layout.IntermediateMeasureScope, ka.k0
        public d7.j getCoroutineContext() {
            return IntermediateLayoutModifierNode.this.getCoroutineScope().getCoroutineContext();
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return IntermediateLayoutModifierNode.this.getCoordinator().getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return IntermediateLayoutModifierNode.this.getCoordinator().getFontScale();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return IntermediateLayoutModifierNode.this.getCoordinator().getLayoutDirection();
        }

        @Override // androidx.compose.ui.layout.LookaheadScope
        public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope placementScope) {
            return IntermediateLayoutModifierNode.this.closestLookaheadScope.getLookaheadScopeCoordinates(placementScope);
        }

        @Override // androidx.compose.ui.layout.IntermediateMeasureScope
        /* JADX INFO: renamed from: getLookaheadSize-YbymL2g, reason: not valid java name and from getter */
        public long getLookaheadSize() {
            return this.lookaheadSize;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return false;
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final r7.l<? super Placeable.PlacementScope, t0> placementBlock) {
            if ((width & (-16777216)) != 0 || ((-16777216) & height) != 0) {
                throw new IllegalStateException(androidx.compose.foundation.c.s("Size(", width, " x ", height, ") is out of range. Each dimension must be between 0 and 16777215.").toString());
            }
            final IntermediateLayoutModifierNode intermediateLayoutModifierNode = IntermediateLayoutModifierNode.this;
            return new MeasureResult(width, height, alignmentLines, placementBlock, intermediateLayoutModifierNode) { // from class: androidx.compose.ui.layout.IntermediateLayoutModifierNode$IntermediateMeasureScopeImpl$layout$1
                final /* synthetic */ r7.l<Placeable.PlacementScope, t0> $placementBlock;
                private final Map<AlignmentLine, Integer> alignmentLines;
                private final int height;
                final /* synthetic */ IntermediateLayoutModifierNode this$0;
                private final int width;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.$placementBlock = placementBlock;
                    this.this$0 = intermediateLayoutModifierNode;
                    this.width = width;
                    this.height = height;
                    this.alignmentLines = alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return this.alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public int getHeight() {
                    return this.height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public int getWidth() {
                    return this.width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                    this.$placementBlock.invoke(this.this$0.getCoordinator().getPlacementScope());
                }
            };
        }

        @Override // androidx.compose.ui.layout.LookaheadScope
        /* JADX INFO: renamed from: localLookaheadPositionOf-dBAh8RU, reason: not valid java name */
        public final /* synthetic */ long mo4635localLookaheadPositionOfdBAh8RU(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2) {
            return f.a(this, layoutCoordinates, layoutCoordinates2);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public final /* synthetic */ int mo278roundToPxR2X_6o(long j10) {
            return androidx.compose.ui.unit.a.a(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public final /* synthetic */ int mo279roundToPx0680j_4(float f10) {
            return androidx.compose.ui.unit.a.b(this, f10);
        }

        /* JADX INFO: renamed from: setLookaheadSize-ozmzZPI, reason: not valid java name */
        public void m4636setLookaheadSizeozmzZPI(long j10) {
            this.lookaheadSize = j10;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
            return androidx.compose.ui.unit.b.a(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public final /* synthetic */ float mo281toDpu2uoSUM(float f10) {
            return androidx.compose.ui.unit.a.c(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public final /* synthetic */ long mo283toDpSizekrfVVM(long j10) {
            return androidx.compose.ui.unit.a.e(this, j10);
        }

        @Override // androidx.compose.ui.layout.LookaheadScope
        public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
            return IntermediateLayoutModifierNode.this.closestLookaheadScope.toLookaheadCoordinates(layoutCoordinates);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public final /* synthetic */ float mo284toPxR2X_6o(long j10) {
            return androidx.compose.ui.unit.a.f(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public final /* synthetic */ float mo285toPx0680j_4(float f10) {
            return androidx.compose.ui.unit.a.g(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        public final /* synthetic */ Rect toRect(DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(this, dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public final /* synthetic */ long mo286toSizeXkaWNTQ(long j10) {
            return androidx.compose.ui.unit.a.i(this, j10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public final /* synthetic */ long mo287toSp0xMU5do(float f10) {
            return androidx.compose.ui.unit.b.b(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public final /* synthetic */ long mo288toSpkPz2Gy4(float f10) {
            return androidx.compose.ui.unit.a.j(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public final /* synthetic */ float mo282toDpu2uoSUM(int i10) {
            return androidx.compose.ui.unit.a.d(this, i10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public final /* synthetic */ long mo289toSpkPz2Gy4(int i10) {
            return androidx.compose.ui.unit.a.k(this, i10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.IntermediateLayoutModifierNode$onAttach$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<LayoutCoordinates> {
        final /* synthetic */ LayoutNode $closestLookaheadRoot;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LayoutNode layoutNode) {
            super(0);
            this.$closestLookaheadRoot = layoutNode;
        }

        @Override // r7.a
        public final LayoutCoordinates invoke() {
            return this.$closestLookaheadRoot.getParent$ui_release().getInnerCoordinator$ui_release().getCoordinates();
        }
    }

    public IntermediateLayoutModifierNode(q<? super IntermediateMeasureScope, ? super Measurable, ? super Constraints, ? extends MeasureResult> qVar) {
        this.measureBlock = qVar;
        LookaheadScopeImpl lookaheadScopeImpl = new LookaheadScopeImpl(new IntermediateLayoutModifierNode$localLookaheadScope$1(this));
        this.localLookaheadScope = lookaheadScopeImpl;
        this.closestLookaheadScope = lookaheadScopeImpl;
        this.isIntermediateChangeActive = true;
    }

    public final q<IntermediateMeasureScope, Measurable, Constraints, MeasureResult> getMeasureBlock$ui_release() {
        return this.measureBlock;
    }

    /* JADX INFO: renamed from: intermediateMeasure-Te-uZzU, reason: not valid java name */
    public final MeasureResult m4633intermediateMeasureTeuZzU(MeasureScope measureScope, Measurable measurable, long j10, long j11, long j12) {
        this.intermediateMeasureScope.m4636setLookaheadSizeozmzZPI(j11);
        this.lookaheadConstraints = Constraints.m5634boximpl(j12);
        IntermediateMeasurablePlaceable intermediateMeasurablePlaceable = this.intermediateMeasurable;
        if (intermediateMeasurablePlaceable == null) {
            intermediateMeasurablePlaceable = new IntermediateMeasurablePlaceable(measurable);
        }
        this.intermediateMeasurable = intermediateMeasurablePlaceable;
        intermediateMeasurablePlaceable.setWrappedMeasurable(measurable);
        return (MeasureResult) this.measureBlock.invoke(this.intermediateMeasureScope, intermediateMeasurablePlaceable, Constraints.m5634boximpl(j10));
    }

    /* JADX INFO: renamed from: isIntermediateChangeActive, reason: from getter */
    public final boolean getIsIntermediateChangeActive() {
        return this.isIntermediateChangeActive;
    }

    public final int maxIntermediateIntrinsicHeight$ui_release(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.layout.IntermediateLayoutModifierNode$maxIntermediateIntrinsicHeight$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
            public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                return (MeasureResult) this.this$0.getMeasureBlock$ui_release().invoke(this.this$0.intermediateMeasureScope, measurable, Constraints.m5634boximpl(j10));
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public final int maxIntermediateIntrinsicWidth$ui_release(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.layout.IntermediateLayoutModifierNode$maxIntermediateIntrinsicWidth$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                return (MeasureResult) this.this$0.getMeasureBlock$ui_release().invoke(this.this$0.intermediateMeasureScope, measurable, Constraints.m5634boximpl(j10));
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.a(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.b(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new IntermediateLayoutModifierNode$measure$1$1(placeableMo4631measureBRTryo0), 4, null);
    }

    public final int minIntermediateIntrinsicHeight$ui_release(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.layout.IntermediateLayoutModifierNode$minIntermediateIntrinsicHeight$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                return (MeasureResult) this.this$0.getMeasureBlock$ui_release().invoke(this.this$0.intermediateMeasureScope, measurable, Constraints.m5634boximpl(j10));
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public final int minIntermediateIntrinsicWidth$ui_release(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.layout.IntermediateLayoutModifierNode$minIntermediateIntrinsicWidth$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                return (MeasureResult) this.this$0.getMeasureBlock$ui_release().invoke(this.this$0.intermediateMeasureScope, measurable, Constraints.m5634boximpl(j10));
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        LookaheadScopeImpl lookaheadScopeImpl;
        LookaheadScopeImpl lookaheadScopeImpl2;
        NodeChain nodes;
        LookaheadDelegate lookaheadDelegate;
        NodeCoordinator coordinator = getCoordinator();
        if (((coordinator == null || (lookaheadDelegate = coordinator.getLookaheadDelegate()) == null) ? null : lookaheadDelegate.getLookaheadLayoutCoordinates()) == null) {
            throw new IllegalStateException("could not fetch lookahead coordinates");
        }
        LayoutNode lookaheadRoot = DelegatableNodeKt.requireLayoutNode(this).getLookaheadRoot();
        if (lookaheadRoot == null || !lookaheadRoot.getIsVirtualLookaheadRoot()) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(512);
            if (!getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
            IntermediateLayoutModifierNode intermediateLayoutModifierNode = null;
            while (layoutNodeRequireLayoutNode != null) {
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = parent;
                            while (nodePop != null) {
                                if (nodePop instanceof IntermediateLayoutModifierNode) {
                                    intermediateLayoutModifierNode = (IntermediateLayoutModifierNode) nodePop;
                                } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i10 = 0;
                                    for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                nodePop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            }
            if (intermediateLayoutModifierNode == null || (lookaheadScopeImpl = intermediateLayoutModifierNode.localLookaheadScope) == null) {
                lookaheadScopeImpl = this.localLookaheadScope;
            }
            lookaheadScopeImpl2 = lookaheadScopeImpl;
        } else {
            lookaheadScopeImpl2 = new LookaheadScopeImpl(new AnonymousClass2(lookaheadRoot));
        }
        this.closestLookaheadScope = lookaheadScopeImpl2;
    }

    public final void setIntermediateChangeActive(boolean z) {
        this.isIntermediateChangeActive = z;
    }

    public final void setMeasureBlock$ui_release(q<? super IntermediateMeasureScope, ? super Measurable, ? super Constraints, ? extends MeasureResult> qVar) {
        this.measureBlock = qVar;
    }
}
