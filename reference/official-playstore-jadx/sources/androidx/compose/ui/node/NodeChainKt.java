package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.CombinedModifier;
import androidx.compose.ui.Modifier;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\b*\u0001\u0012\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0007*\u00020\u0006*\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u000e*\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0015\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016\"\u0014\u0010\u0017\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016\"\u0014\u0010\u0018\u001a\u00020\u00038\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/Modifier$Element;", "prev", LinkHeader.Rel.Next, "", "actionForModifiers", "(Landroidx/compose/ui/Modifier$Element;Landroidx/compose/ui/Modifier$Element;)I", "Landroidx/compose/ui/Modifier$Node;", "T", "Landroidx/compose/ui/node/ModifierNodeElement;", "node", "Lx6/t0;", "updateUnsafe", "(Landroidx/compose/ui/node/ModifierNodeElement;Landroidx/compose/ui/Modifier$Node;)V", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/runtime/collection/MutableVector;", "result", "fillVector", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/collection/MutableVector;)Landroidx/compose/runtime/collection/MutableVector;", "androidx/compose/ui/node/NodeChainKt$SentinelHead$1", "SentinelHead", "Landroidx/compose/ui/node/NodeChainKt$SentinelHead$1;", "ActionReplace", "I", "ActionUpdate", "ActionReuse", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NodeChainKt {
    private static final int ActionReplace = 0;
    private static final int ActionReuse = 2;
    private static final int ActionUpdate = 1;
    private static final NodeChainKt$SentinelHead$1 SentinelHead;

    /* JADX INFO: renamed from: androidx.compose.ui.node.NodeChainKt$fillVector$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "element", "Landroidx/compose/ui/Modifier$Element;", "invoke", "(Landroidx/compose/ui/Modifier$Element;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Modifier.Element, Boolean> {
        final /* synthetic */ MutableVector<Modifier.Element> $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableVector<Modifier.Element> mutableVector) {
            super(1);
            this.$result = mutableVector;
        }

        @Override // r7.l
        public final Boolean invoke(Modifier.Element element) {
            this.$result.add(element);
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.Modifier$Node, androidx.compose.ui.node.NodeChainKt$SentinelHead$1] */
    static {
        ?? r02 = new Modifier.Node() { // from class: androidx.compose.ui.node.NodeChainKt$SentinelHead$1
            public String toString() {
                return "<Head>";
            }
        };
        r02.setAggregateChildKindSet$ui_release(-1);
        SentinelHead = r02;
    }

    public static final int actionForModifiers(Modifier.Element element, Modifier.Element element2) {
        if (p.a(element, element2)) {
            return 2;
        }
        if (Actual_jvmKt.areObjectsOfSameType(element, element2)) {
            return 1;
        }
        return ((element instanceof ForceUpdateElement) && Actual_jvmKt.areObjectsOfSameType(((ForceUpdateElement) element).getOriginal(), element2)) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final MutableVector<Modifier.Element> fillVector(Modifier modifier, MutableVector<Modifier.Element> mutableVector) {
        int size = mutableVector.getSize();
        if (size < 16) {
            size = 16;
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier[size], 0);
        mutableVector2.add(modifier);
        AnonymousClass1 anonymousClass1 = null;
        while (mutableVector2.isNotEmpty()) {
            Modifier modifier2 = (Modifier) androidx.compose.material3.d.h(mutableVector2, 1);
            if (modifier2 instanceof CombinedModifier) {
                CombinedModifier combinedModifier = (CombinedModifier) modifier2;
                mutableVector2.add(combinedModifier.getInner());
                mutableVector2.add(combinedModifier.getOuter());
            } else if (modifier2 instanceof Modifier.Element) {
                mutableVector.add(modifier2);
            } else {
                if (anonymousClass1 == null) {
                    anonymousClass1 = new AnonymousClass1(mutableVector);
                }
                modifier2.all(anonymousClass1);
                anonymousClass1 = anonymousClass1;
            }
        }
        return mutableVector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Modifier.Node> void updateUnsafe(ModifierNodeElement<T> modifierNodeElement, Modifier.Node node) {
        modifierNodeElement.update(node);
    }
}
