package androidx.compose.runtime.changelist;

import a0.c;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.OffsetApplier;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.IntRef;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import q7.g;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:$\"#$%&'()*+,-./0123456789:;<=>?@ABCDEB\u001d\b\u0004\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J/\u0010\u000f\u001a\u00020\u000e*\u00020\u00072\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u0019\u001a\u00020\u00132\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0015J\u000f\u0010\u001a\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010!\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b \u0010\u001b\u0082\u0001\"FGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefg\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006h"}, d2 = {"Landroidx/compose/runtime/changelist/Operation;", "", "", "ints", "objects", "<init>", "(II)V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "objectParamName-31yXWZQ", "objectParamName", "toString", "()Ljava/lang/String;", "I", "getInts", "()I", "getObjects", "getName", ContentDisposition.Parameters.Name, "AdvanceSlotsBy", "AppendValue", "ApplyChangeList", "CopyNodesToNewAnchorLocation", "CopySlotTableToAnchorLocation", "DeactivateCurrentGroup", "DetermineMovableContentNodeIndex", "Downs", "EndCompositionScope", "EndCurrentGroup", "EndMovableContentPlacement", "EnsureGroupStarted", "EnsureRootGroupStarted", "InsertNodeFixup", "InsertSlots", "InsertSlotsWithFixups", "IntParameter", "MoveCurrentGroup", "MoveNode", "ObjectParameter", "PostInsertNodeFixup", "ReleaseMovableGroupAtCurrent", "Remember", "RemoveCurrentGroup", "RemoveNode", "ResetSlots", "SideEffect", "SkipToEndOfCurrentGroup", "TestOperation", "TrimParentValues", "UpdateAnchoredValue", "UpdateAuxData", "UpdateNode", "UpdateValue", "Ups", "UseCurrentNode", "Landroidx/compose/runtime/changelist/Operation$AdvanceSlotsBy;", "Landroidx/compose/runtime/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/changelist/Operation$CopySlotTableToAnchorLocation;", "Landroidx/compose/runtime/changelist/Operation$DeactivateCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/changelist/Operation$Downs;", "Landroidx/compose/runtime/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/changelist/Operation$EndCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/changelist/Operation$EnsureGroupStarted;", "Landroidx/compose/runtime/changelist/Operation$EnsureRootGroupStarted;", "Landroidx/compose/runtime/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/changelist/Operation$MoveCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/changelist/Operation$ReleaseMovableGroupAtCurrent;", "Landroidx/compose/runtime/changelist/Operation$Remember;", "Landroidx/compose/runtime/changelist/Operation$RemoveCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/changelist/Operation$SkipToEndOfCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/changelist/Operation$TrimParentValues;", "Landroidx/compose/runtime/changelist/Operation$UpdateAnchoredValue;", "Landroidx/compose/runtime/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/changelist/Operation$Ups;", "Landroidx/compose/runtime/changelist/Operation$UseCurrentNode;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Operation {
    public static final int $stable = 0;
    private final int ints;
    private final int objects;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$AdvanceSlotsBy;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getDistance-jn0FJLE", "()I", "Distance", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AdvanceSlotsBy extends Operation {
        public static final int $stable = 0;
        public static final AdvanceSlotsBy INSTANCE = new AdvanceSlotsBy();

        private AdvanceSlotsBy() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.advanceBy(operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getDistance-jn0FJLE, reason: not valid java name */
        public final int m3005getDistancejn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "distance" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "()I", "Anchor", "", "getValue-HpuvwBQ", "Value", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AppendValue extends Operation {
        public static final int $stable = 0;
        public static final AppendValue INSTANCE = new AppendValue();

        private AppendValue() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            Object objMo3068getObject31yXWZQ = operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            if (objMo3068getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo3068getObject31yXWZQ).getWrapped());
            }
            slotWriter.appendSlot(anchor, objMo3068getObject31yXWZQ);
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3006getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getValue-HpuvwBQ, reason: not valid java name */
        public final int m3007getValueHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? LinkHeader.Parameters.Anchor : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "value" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/changelist/ChangeList;", "getChanges-HpuvwBQ", "()I", "Changes", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-HpuvwBQ", "EffectiveNodeIndex", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ApplyChangeList extends Operation {
        public static final int $stable = 0;
        public static final ApplyChangeList INSTANCE = new ApplyChangeList();

        private ApplyChangeList() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            IntRef intRef = (IntRef) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            int element = intRef != null ? intRef.getElement() : 0;
            ChangeList changeList = (ChangeList) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            if (element > 0) {
                applier = new OffsetApplier(applier, element);
            }
            changeList.executeAndFlushAllPendingChanges(applier, slotWriter, rememberManager);
        }

        /* JADX INFO: renamed from: getChanges-HpuvwBQ, reason: not valid java name */
        public final int m3008getChangesHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-HpuvwBQ, reason: not valid java name */
        public final int m3009getEffectiveNodeIndexHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "changes" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "effectiveNodeIndex" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R&\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-HpuvwBQ", "()I", "EffectiveNodeIndex", "", "", "getNodes-HpuvwBQ", "Nodes", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class CopyNodesToNewAnchorLocation extends Operation {
        public static final int $stable = 0;
        public static final CopyNodesToNewAnchorLocation INSTANCE = new CopyNodesToNewAnchorLocation();

        private CopyNodesToNewAnchorLocation() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int element = ((IntRef) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0))).getElement();
            List list = (List) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                Object obj = list.get(i10);
                int i11 = element + i10;
                applier.insertBottomUp(i11, obj);
                applier.insertTopDown(i11, obj);
            }
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-HpuvwBQ, reason: not valid java name */
        public final int m3010getEffectiveNodeIndexHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getNodes-HpuvwBQ, reason: not valid java name */
        public final int m3011getNodesHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "effectiveNodeIndex" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "nodes" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R \u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016R\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/runtime/changelist/Operation$CopySlotTableToAnchorLocation;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/MovableContentState;", "getResolvedState-HpuvwBQ", "()I", "ResolvedState", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-HpuvwBQ", "ParentCompositionContext", "Landroidx/compose/runtime/MovableContentStateReference;", "getFrom-HpuvwBQ", "From", "getTo-HpuvwBQ", "To", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class CopySlotTableToAnchorLocation extends Operation {
        public static final int $stable = 0;
        public static final CopySlotTableToAnchorLocation INSTANCE = new CopySlotTableToAnchorLocation();

        private CopySlotTableToAnchorLocation() {
            super(0, 4, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            MovableContentStateReference movableContentStateReference = (MovableContentStateReference) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(2));
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(3));
            CompositionContext compositionContext = (CompositionContext) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            MovableContentState movableContentStateMovableContentStateResolve$runtime_release = (MovableContentState) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            if (movableContentStateMovableContentStateResolve$runtime_release == null && (movableContentStateMovableContentStateResolve$runtime_release = compositionContext.movableContentStateResolve$runtime_release(movableContentStateReference)) == null) {
                ComposerKt.composeRuntimeError("Could not resolve state for movable content");
                throw new KotlinNothingValueException();
            }
            RecomposeScopeImpl.INSTANCE.adoptAnchoredScopes$runtime_release(slotWriter, slotWriter.moveIntoGroupFrom(1, movableContentStateMovableContentStateResolve$runtime_release.getSlotTable(), 2), (RecomposeScopeOwner) movableContentStateReference2.getComposition());
        }

        /* JADX INFO: renamed from: getFrom-HpuvwBQ, reason: not valid java name */
        public final int m3012getFromHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(2);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-HpuvwBQ, reason: not valid java name */
        public final int m3013getParentCompositionContextHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getResolvedState-HpuvwBQ, reason: not valid java name */
        public final int m3014getResolvedStateHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getTo-HpuvwBQ, reason: not valid java name */
        public final int m3015getToHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "resolvedState" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "resolvedCompositionContext" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(2)) ? "from" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(3)) ? "to" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$DeactivateCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DeactivateCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final DeactivateCurrentGroup INSTANCE = new DeactivateCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private DeactivateCurrentGroup() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ComposerKt.deactivateCurrentGroup(slotWriter, rememberManager);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndexOut-HpuvwBQ", "()I", "EffectiveNodeIndexOut", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "Anchor", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DetermineMovableContentNodeIndex extends Operation {
        public static final int $stable = 0;
        public static final DetermineMovableContentNodeIndex INSTANCE = new DetermineMovableContentNodeIndex();

        private DetermineMovableContentNodeIndex() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((IntRef) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0))).setElement(OperationKt.positionToInsert(slotWriter, (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1)), applier));
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3016getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndexOut-HpuvwBQ, reason: not valid java name */
        public final int m3017getEffectiveNodeIndexOutHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "effectiveNodeIndexOut" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? LinkHeader.Parameters.Anchor : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R&\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$Downs;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "", "getNodes-HpuvwBQ", "()I", "Nodes", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Downs extends Operation {
        public static final int $stable = 0;
        public static final Downs INSTANCE = new Downs();

        /* JADX WARN: Illegal instructions before constructor call */
        private Downs() {
            int i10 = 1;
            super(0, i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            for (Object obj : (Object[]) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0))) {
                applier.down(obj);
            }
        }

        /* JADX INFO: renamed from: getNodes-HpuvwBQ, reason: not valid java name */
        public final int m3018getNodesHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "nodes" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R*\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00110\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "getAction-HpuvwBQ", "()I", "Action", "getComposition-HpuvwBQ", "Composition", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EndCompositionScope extends Operation {
        public static final int $stable = 0;
        public static final EndCompositionScope INSTANCE = new EndCompositionScope();

        private EndCompositionScope() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((l) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0))).invoke((Composition) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1)));
        }

        /* JADX INFO: renamed from: getAction-HpuvwBQ, reason: not valid java name */
        public final int m3019getActionHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getComposition-HpuvwBQ, reason: not valid java name */
        public final int m3020getCompositionHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? LinkHeader.Parameters.Anchor : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "composition" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$EndCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EndCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final EndCurrentGroup INSTANCE = new EndCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndCurrentGroup() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.endGroup();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EndMovableContentPlacement extends Operation {
        public static final int $stable = 0;
        public static final EndMovableContentPlacement INSTANCE = new EndMovableContentPlacement();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndMovableContentPlacement() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            OperationKt.positionToParentOf(slotWriter, applier, 0);
            slotWriter.endGroup();
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$EnsureGroupStarted;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "()I", "Anchor", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EnsureGroupStarted extends Operation {
        public static final int $stable = 0;
        public static final EnsureGroupStarted INSTANCE = new EnsureGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureGroupStarted() {
            int i10 = 1;
            super(0, i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.ensureStarted((Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3021getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? LinkHeader.Parameters.Anchor : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$EnsureRootGroupStarted;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EnsureRootGroupStarted extends Operation {
        public static final int $stable = 0;
        public static final EnsureRootGroupStarted INSTANCE = new EnsureRootGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureRootGroupStarted() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.ensureStarted(0);
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\f\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\bJ/\u0010\u0015\u001a\u00020\u0014*\u00020\r2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R&\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00170\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001d\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006!"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "objectParamName-31yXWZQ", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Lkotlin/Function0;", "", "getFactory-HpuvwBQ", "()I", "Factory", "getInsertIndex-jn0FJLE", "InsertIndex", "Landroidx/compose/runtime/Anchor;", "getGroupAnchor-HpuvwBQ", "GroupAnchor", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final InsertNodeFixup INSTANCE = new InsertNodeFixup();

        private InsertNodeFixup() {
            super(1, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Object objInvoke = ((a) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0))).invoke();
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            slotWriter.updateNode(anchor, objInvoke);
            applier.insertTopDown(iMo3067getIntw8GmfQM, objInvoke);
            applier.down(objInvoke);
        }

        /* JADX INFO: renamed from: getFactory-HpuvwBQ, reason: not valid java name */
        public final int m3022getFactoryHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getGroupAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3023getGroupAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getInsertIndex-jn0FJLE, reason: not valid java name */
        public final int m3024getInsertIndexjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "insertIndex" : super.mo3003intParamNamew8GmfQM(parameter);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "factory" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "groupAnchor" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "()I", "Anchor", "Landroidx/compose/runtime/SlotTable;", "getFromSlotTable-HpuvwBQ", "FromSlotTable", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InsertSlots extends Operation {
        public static final int $stable = 0;
        public static final InsertSlots INSTANCE = new InsertSlots();

        private InsertSlots() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            SlotTable slotTable = (SlotTable) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            slotWriter.beginInsert();
            slotWriter.moveFrom(slotTable, anchor.toIndexFor(slotTable), false);
            slotWriter.endInsert();
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3025getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getFromSlotTable-HpuvwBQ, reason: not valid java name */
        public final int m3026getFromSlotTableHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? LinkHeader.Parameters.Anchor : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "from" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "()I", "Anchor", "Landroidx/compose/runtime/SlotTable;", "getFromSlotTable-HpuvwBQ", "FromSlotTable", "Landroidx/compose/runtime/changelist/FixupList;", "getFixups-HpuvwBQ", "Fixups", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InsertSlotsWithFixups extends Operation {
        public static final int $stable = 0;
        public static final InsertSlotsWithFixups INSTANCE = new InsertSlotsWithFixups();

        private InsertSlotsWithFixups() {
            super(0, 3, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            SlotTable slotTable = (SlotTable) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            FixupList fixupList = (FixupList) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(2));
            SlotWriter slotWriterOpenWriter = slotTable.openWriter();
            try {
                fixupList.executeAndFlushAllPendingFixups(applier, slotWriterOpenWriter, rememberManager);
                slotWriterOpenWriter.close(true);
                slotWriter.beginInsert();
                slotWriter.moveFrom(slotTable, anchor.toIndexFor(slotTable), false);
                slotWriter.endInsert();
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3027getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getFixups-HpuvwBQ, reason: not valid java name */
        public final int m3028getFixupsHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(2);
        }

        /* JADX INFO: renamed from: getFromSlotTable-HpuvwBQ, reason: not valid java name */
        public final int m3029getFromSlotTableHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? LinkHeader.Parameters.Anchor : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "from" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(2)) ? "fixups" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$IntParameter;", "", "offset", "", "constructor-impl", "(I)I", "getOffset", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @g
    public static final class IntParameter {
        private final int offset;

        private /* synthetic */ IntParameter(int i10) {
            this.offset = i10;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ IntParameter m3030boximpl(int i10) {
            return new IntParameter(i10);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m3031constructorimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m3032equalsimpl(int i10, Object obj) {
            return (obj instanceof IntParameter) && i10 == ((IntParameter) obj).m3036unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3033equalsimpl0(int i10, int i11) {
            return i10 == i11;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m3034hashCodeimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m3035toStringimpl(int i10) {
            return c.k("IntParameter(offset=", i10, ')');
        }

        public boolean equals(Object obj) {
            return m3032equalsimpl(this.offset, obj);
        }

        public final int getOffset() {
            return this.offset;
        }

        public int hashCode() {
            return m3034hashCodeimpl(this.offset);
        }

        public String toString() {
            return m3035toStringimpl(this.offset);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m3036unboximpl() {
            return this.offset;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$MoveCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getOffset-jn0FJLE", "()I", "Offset", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MoveCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final MoveCurrentGroup INSTANCE = new MoveCurrentGroup();

        private MoveCurrentGroup() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.moveGroup(operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getOffset-jn0FJLE, reason: not valid java name */
        public final int m3037getOffsetjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "offset" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0018\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0018\u0010\u001a\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getFrom-jn0FJLE", "()I", "From", "getTo-jn0FJLE", "To", "getCount-jn0FJLE", "Count", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MoveNode extends Operation {
        public static final int $stable = 0;
        public static final MoveNode INSTANCE = new MoveNode();

        private MoveNode() {
            super(3, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            applier.move(operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0)), operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(1)), operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(2)));
        }

        /* JADX INFO: renamed from: getCount-jn0FJLE, reason: not valid java name */
        public final int m3038getCountjn0FJLE() {
            return IntParameter.m3031constructorimpl(2);
        }

        /* JADX INFO: renamed from: getFrom-jn0FJLE, reason: not valid java name */
        public final int m3039getFromjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        /* JADX INFO: renamed from: getTo-jn0FJLE, reason: not valid java name */
        public final int m3040getTojn0FJLE() {
            return IntParameter.m3031constructorimpl(1);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "from" : IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(1)) ? "to" : IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(2)) ? "count" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0088\u0001\u0003¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "T", "", "offset", "", "constructor-impl", "(I)I", "getOffset", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @g
    public static final class ObjectParameter<T> {
        private final int offset;

        private /* synthetic */ ObjectParameter(int i10) {
            this.offset = i10;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ObjectParameter m3041boximpl(int i10) {
            return new ObjectParameter(i10);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static <T> int m3042constructorimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m3043equalsimpl(int i10, Object obj) {
            return (obj instanceof ObjectParameter) && i10 == ((ObjectParameter) obj).m3047unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3044equalsimpl0(int i10, int i11) {
            return i10 == i11;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m3045hashCodeimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m3046toStringimpl(int i10) {
            return c.k("ObjectParameter(offset=", i10, ')');
        }

        public boolean equals(Object obj) {
            return m3043equalsimpl(this.offset, obj);
        }

        public final int getOffset() {
            return this.offset;
        }

        public int hashCode() {
            return m3045hashCodeimpl(this.offset);
        }

        public String toString() {
            return m3046toStringimpl(this.offset);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m3047unboximpl() {
            return this.offset;
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\f\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\bJ/\u0010\u0015\u001a\u00020\u0014*\u00020\r2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0019\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "objectParamName-31yXWZQ", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getInsertIndex-jn0FJLE", "()I", "InsertIndex", "Landroidx/compose/runtime/Anchor;", "getGroupAnchor-HpuvwBQ", "GroupAnchor", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PostInsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final PostInsertNodeFixup INSTANCE = new PostInsertNodeFixup();

        /* JADX WARN: Illegal instructions before constructor call */
        private PostInsertNodeFixup() {
            int i10 = 1;
            super(i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            applier.up();
            applier.insertBottomUp(iMo3067getIntw8GmfQM, slotWriter.node(anchor));
        }

        /* JADX INFO: renamed from: getGroupAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3048getGroupAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getInsertIndex-jn0FJLE, reason: not valid java name */
        public final int m3049getInsertIndexjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "insertIndex" : super.mo3003intParamNamew8GmfQM(parameter);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "groupAnchor" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$ReleaseMovableGroupAtCurrent;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/ControlledComposition;", "getComposition-HpuvwBQ", "()I", "Composition", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-HpuvwBQ", "ParentCompositionContext", "Landroidx/compose/runtime/MovableContentStateReference;", "getReference-HpuvwBQ", "Reference", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ReleaseMovableGroupAtCurrent extends Operation {
        public static final int $stable = 0;
        public static final ReleaseMovableGroupAtCurrent INSTANCE = new ReleaseMovableGroupAtCurrent();

        private ReleaseMovableGroupAtCurrent() {
            super(0, 3, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            OperationKt.releaseMovableGroupAtCurrent((ControlledComposition) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)), (CompositionContext) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1)), (MovableContentStateReference) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(2)), slotWriter);
        }

        /* JADX INFO: renamed from: getComposition-HpuvwBQ, reason: not valid java name */
        public final int m3050getCompositionHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-HpuvwBQ, reason: not valid java name */
        public final int m3051getParentCompositionContextHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getReference-HpuvwBQ, reason: not valid java name */
        public final int m3052getReferenceHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(2);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "composition" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "parentCompositionContext" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(2)) ? "reference" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$Remember;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/RememberObserver;", "getValue-HpuvwBQ", "()I", "Value", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Remember extends Operation {
        public static final int $stable = 0;
        public static final Remember INSTANCE = new Remember();

        /* JADX WARN: Illegal instructions before constructor call */
        private Remember() {
            int i10 = 1;
            super(0, i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            rememberManager.remembering((RememberObserver) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getValue-HpuvwBQ, reason: not valid java name */
        public final int m3053getValueHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "value" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$RemoveCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RemoveCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final RemoveCurrentGroup INSTANCE = new RemoveCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveCurrentGroup() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ComposerKt.removeCurrentGroup(slotWriter, rememberManager);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0018\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getRemoveIndex-jn0FJLE", "()I", "RemoveIndex", "getCount-jn0FJLE", "Count", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RemoveNode extends Operation {
        public static final int $stable = 0;
        public static final RemoveNode INSTANCE = new RemoveNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveNode() {
            int i10 = 2;
            super(i10, 0, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            applier.remove(operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0)), operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(1)));
        }

        /* JADX INFO: renamed from: getCount-jn0FJLE, reason: not valid java name */
        public final int m3054getCountjn0FJLE() {
            return IntParameter.m3031constructorimpl(1);
        }

        /* JADX INFO: renamed from: getRemoveIndex-jn0FJLE, reason: not valid java name */
        public final int m3055getRemoveIndexjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "removeIndex" : IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(1)) ? "count" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ResetSlots extends Operation {
        public static final int $stable = 0;
        public static final ResetSlots INSTANCE = new ResetSlots();

        /* JADX WARN: Illegal instructions before constructor call */
        private ResetSlots() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.reset();
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Lkotlin/Function0;", "getEffect-HpuvwBQ", "()I", "Effect", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SideEffect extends Operation {
        public static final int $stable = 0;
        public static final SideEffect INSTANCE = new SideEffect();

        /* JADX WARN: Illegal instructions before constructor call */
        private SideEffect() {
            int i10 = 1;
            super(0, i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            rememberManager.sideEffect((a) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getEffect-HpuvwBQ, reason: not valid java name */
        public final int m3056getEffectHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "effect" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$SkipToEndOfCurrentGroup;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SkipToEndOfCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final SkipToEndOfCurrentGroup INSTANCE = new SkipToEndOfCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private SkipToEndOfCurrentGroup() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.skipToGroupEnd();
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012$\b\u0002\u0010\n\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0004\b\u000b\u0010\fJ/\u0010\u0011\u001a\u00020\t*\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R3\u0010\n\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR%\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001f0\u00198\u0006¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001e¨\u0006#"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/changelist/Operation;", "", "ints", "objects", "Lkotlin/Function3;", "Landroidx/compose/runtime/Applier;", "Landroidx/compose/runtime/SlotWriter;", "Landroidx/compose/runtime/RememberManager;", "Lx6/t0;", "block", "<init>", "(IILr7/q;)V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "applier", "slots", "rememberManager", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "toString", "()Ljava/lang/String;", "Lr7/q;", "getBlock", "()Lr7/q;", "", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "intParams", "Ljava/util/List;", "getIntParams", "()Ljava/util/List;", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "", "objParams", "getObjParams", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TestOperation extends Operation {
        public static final int $stable = 8;
        private final q<Applier<?>, SlotWriter, RememberManager, t0> block;
        private final List<IntParameter> intParams;
        private final List<ObjectParameter<Object>> objParams;

        /* JADX INFO: renamed from: androidx.compose.runtime.changelist.Operation$TestOperation$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u00062\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Landroidx/compose/runtime/Applier;", "<anonymous parameter 0>", "Landroidx/compose/runtime/SlotWriter;", "<anonymous parameter 1>", "Landroidx/compose/runtime/RememberManager;", "<anonymous parameter 2>", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements q<Applier<?>, SlotWriter, RememberManager, t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(3);
            }

            public final void invoke(Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((Applier<?>) obj, (SlotWriter) obj2, (RememberManager) obj3);
                return t0.f22605a;
            }
        }

        public TestOperation() {
            this(0, 0, null, 7, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            this.block.invoke(applier, slotWriter, rememberManager);
        }

        public final q<Applier<?>, SlotWriter, RememberManager, t0> getBlock() {
            return this.block;
        }

        public final List<IntParameter> getIntParams() {
            return this.intParams;
        }

        public final List<ObjectParameter<Object>> getObjParams() {
            return this.objParams;
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public String toString() {
            return "TestOperation(ints = " + getInts() + ", objects = " + getObjects() + ")@" + System.identityHashCode(this);
        }

        public /* synthetic */ TestOperation(int i10, int i11, q qVar, int i12, h hVar) {
            this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11, (i12 & 4) != 0 ? AnonymousClass1.INSTANCE : qVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public TestOperation(int i10, int i11, q<? super Applier<?>, ? super SlotWriter, ? super RememberManager, t0> qVar) {
            super(i10, i11, null);
            this.block = qVar;
            ArrayList arrayList = new ArrayList(i10);
            for (int i12 = 0; i12 < i10; i12++) {
                arrayList.add(IntParameter.m3030boximpl(IntParameter.m3031constructorimpl(i12)));
            }
            this.intParams = arrayList;
            ArrayList arrayList2 = new ArrayList(i11);
            for (int i13 = 0; i13 < i11; i13++) {
                arrayList2.add(ObjectParameter.m3041boximpl(ObjectParameter.m3042constructorimpl(i13)));
            }
            this.objParams = arrayList2;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$TrimParentValues;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getCount-jn0FJLE", "()I", "Count", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TrimParentValues extends Operation {
        public static final int $stable = 0;
        public static final TrimParentValues INSTANCE = new TrimParentValues();

        private TrimParentValues() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            int slotsSize = slotWriter.getSlotsSize();
            int parent = slotWriter.getParent();
            int iSlotsStartIndex$runtime_release = slotWriter.slotsStartIndex$runtime_release(parent);
            int iSlotsEndIndex$runtime_release = slotWriter.slotsEndIndex$runtime_release(parent);
            for (int iMax = Math.max(iSlotsStartIndex$runtime_release, iSlotsEndIndex$runtime_release - iMo3067getIntw8GmfQM); iMax < iSlotsEndIndex$runtime_release; iMax++) {
                Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(iMax)];
                if (obj instanceof RememberObserverHolder) {
                    rememberManager.forgetting(((RememberObserverHolder) obj).getWrapped(), slotsSize - iMax, -1, -1);
                } else if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).release();
                }
            }
            slotWriter.trimTailSlots(iMo3067getIntw8GmfQM);
        }

        /* JADX INFO: renamed from: getCount-jn0FJLE, reason: not valid java name */
        public final int m3057getCountjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "count" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\f\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\bJ/\u0010\u0015\u001a\u00020\u0014*\u00020\r2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019R\u0018\u0010\u001f\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/runtime/changelist/Operation$UpdateAnchoredValue;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "objectParamName-31yXWZQ", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "getValue-HpuvwBQ", "()I", "Value", "Landroidx/compose/runtime/Anchor;", "getAnchor-HpuvwBQ", "Anchor", "getGroupSlotIndex-jn0FJLE", "GroupSlotIndex", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UpdateAnchoredValue extends Operation {
        public static final int $stable = 0;
        public static final UpdateAnchoredValue INSTANCE = new UpdateAnchoredValue();

        private UpdateAnchoredValue() {
            super(1, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iAnchorIndex;
            int slotsSize;
            Object objMo3068getObject31yXWZQ = operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            Anchor anchor = (Anchor) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1));
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            if (objMo3068getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo3068getObject31yXWZQ).getWrapped());
            }
            int iAnchorIndex2 = slotWriter.anchorIndex(anchor);
            Object obj = slotWriter.set(iAnchorIndex2, iMo3067getIntw8GmfQM, objMo3068getObject31yXWZQ);
            if (!(obj instanceof RememberObserverHolder)) {
                if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).release();
                    return;
                }
                return;
            }
            int slotsSize2 = slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(iAnchorIndex2, iMo3067getIntw8GmfQM);
            RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
            Anchor after = rememberObserverHolder.getAfter();
            if (after == null || !after.getValid()) {
                iAnchorIndex = -1;
                slotsSize = -1;
            } else {
                iAnchorIndex = slotWriter.anchorIndex(after);
                slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
            }
            rememberManager.forgetting(rememberObserverHolder.getWrapped(), slotsSize2, iAnchorIndex, slotsSize);
        }

        /* JADX INFO: renamed from: getAnchor-HpuvwBQ, reason: not valid java name */
        public final int m3058getAnchorHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getGroupSlotIndex-jn0FJLE, reason: not valid java name */
        public final int m3059getGroupSlotIndexjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        /* JADX INFO: renamed from: getValue-HpuvwBQ, reason: not valid java name */
        public final int m3060getValueHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "groupSlotIndex" : super.mo3003intParamNamew8GmfQM(parameter);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "value" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? LinkHeader.Parameters.Anchor : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R \u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "getData-HpuvwBQ", "()I", "Data", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UpdateAuxData extends Operation {
        public static final int $stable = 0;
        public static final UpdateAuxData INSTANCE = new UpdateAuxData();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateAuxData() {
            int i10 = 1;
            super(0, i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            slotWriter.updateAux(operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getData-HpuvwBQ, reason: not valid java name */
        public final int m3061getDataHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "data" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R \u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R9\u0010\u001b\u001a#\u0012\u001f\u0012\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0004\u0012\u00020\u00110\u0018¢\u0006\u0002\b\u00190\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "parameter", "", "objectParamName-31yXWZQ", "(I)Ljava/lang/String;", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "getValue-HpuvwBQ", "()I", "Value", "Lkotlin/Function2;", "Lx6/n;", "getBlock-HpuvwBQ", "Block", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UpdateNode extends Operation {
        public static final int $stable = 0;
        public static final UpdateNode INSTANCE = new UpdateNode();

        private UpdateNode() {
            super(0, 2, 1, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((p) operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(1))).invoke(applier.getCurrent(), operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0)));
        }

        /* JADX INFO: renamed from: getBlock-HpuvwBQ, reason: not valid java name */
        public final int m3062getBlockHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(1);
        }

        /* JADX INFO: renamed from: getValue-HpuvwBQ, reason: not valid java name */
        public final int m3063getValueHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "value" : ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(1)) ? "block" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\f\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\bJ/\u0010\u0015\u001a\u00020\u0014*\u00020\r2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\n8Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001c\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "objectParamName-31yXWZQ", "objectParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "getValue-HpuvwBQ", "()I", "Value", "getGroupSlotIndex-jn0FJLE", "GroupSlotIndex", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UpdateValue extends Operation {
        public static final int $stable = 0;
        public static final UpdateValue INSTANCE = new UpdateValue();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateValue() {
            int i10 = 1;
            super(i10, i10, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            Object objMo3068getObject31yXWZQ = operationArgContainer.mo3068getObject31yXWZQ(ObjectParameter.m3042constructorimpl(0));
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            if (objMo3068getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberManager.remembering(((RememberObserverHolder) objMo3068getObject31yXWZQ).getWrapped());
            }
            Object obj = slotWriter.set(iMo3067getIntw8GmfQM, objMo3068getObject31yXWZQ);
            if (obj instanceof RememberObserverHolder) {
                rememberManager.forgetting(((RememberObserverHolder) obj).getWrapped(), slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(slotWriter.getCurrentGroup(), iMo3067getIntw8GmfQM), -1, -1);
            } else if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }

        /* JADX INFO: renamed from: getGroupSlotIndex-jn0FJLE, reason: not valid java name */
        public final int m3064getGroupSlotIndexjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        /* JADX INFO: renamed from: getValue-HpuvwBQ, reason: not valid java name */
        public final int m3065getValueHpuvwBQ() {
            return ObjectParameter.m3042constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "groupSlotIndex" : super.mo3003intParamNamew8GmfQM(parameter);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-31yXWZQ */
        public String mo3004objectParamName31yXWZQ(int parameter) {
            return ObjectParameter.m3044equalsimpl0(parameter, ObjectParameter.m3042constructorimpl(0)) ? "value" : super.mo3004objectParamName31yXWZQ(parameter);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0012\u001a\u00020\u0011*\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$Ups;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "intParamName-w8GmfQM", "(I)Ljava/lang/String;", "intParamName", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "getCount-jn0FJLE", "()I", "Count", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Ups extends Operation {
        public static final int $stable = 0;
        public static final Ups INSTANCE = new Ups();

        private Ups() {
            super(1, 0, 2, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            int iMo3067getIntw8GmfQM = operationArgContainer.mo3067getIntw8GmfQM(IntParameter.m3031constructorimpl(0));
            for (int i10 = 0; i10 < iMo3067getIntw8GmfQM; i10++) {
                applier.up();
            }
        }

        /* JADX INFO: renamed from: getCount-jn0FJLE, reason: not valid java name */
        public final int m3066getCountjn0FJLE() {
            return IntParameter.m3031constructorimpl(0);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* JADX INFO: renamed from: intParamName-w8GmfQM */
        public String mo3003intParamNamew8GmfQM(int parameter) {
            return IntParameter.m3033equalsimpl0(parameter, IntParameter.m3031constructorimpl(0)) ? "count" : super.mo3003intParamNamew8GmfQM(parameter);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b*\u00020\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/changelist/Operation$UseCurrentNode;", "Landroidx/compose/runtime/changelist/Operation;", "<init>", "()V", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "Lx6/t0;", "execute", "(Landroidx/compose/runtime/changelist/OperationArgContainer;Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UseCurrentNode extends Operation {
        public static final int $stable = 0;
        public static final UseCurrentNode INSTANCE = new UseCurrentNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private UseCurrentNode() {
            int i10 = 0;
            super(i10, i10, 3, null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager) {
            ((ComposeNodeLifecycleCallback) applier.getCurrent()).onReuse();
        }
    }

    public /* synthetic */ Operation(int i10, int i11, h hVar) {
        this(i10, i11);
    }

    public abstract void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager);

    public final int getInts() {
        return this.ints;
    }

    public final String getName() {
        String strR = l0.f19747a.b(getClass()).r();
        return strR == null ? "" : strR;
    }

    public final int getObjects() {
        return this.objects;
    }

    /* JADX INFO: renamed from: intParamName-w8GmfQM, reason: not valid java name */
    public String mo3003intParamNamew8GmfQM(int parameter) {
        return c.k("IntParameter(", parameter, ')');
    }

    /* JADX INFO: renamed from: objectParamName-31yXWZQ, reason: not valid java name */
    public String mo3004objectParamName31yXWZQ(int parameter) {
        return c.k("ObjectParameter(", parameter, ')');
    }

    public String toString() {
        return getName();
    }

    private Operation(int i10, int i11) {
        this.ints = i10;
        this.objects = i11;
    }

    public /* synthetic */ Operation(int i10, int i11, int i12, h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11, null);
    }
}
