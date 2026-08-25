package androidx.compose.runtime;

import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.collection.IntMap;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ComposerImpl$insertMovableContentGuarded$1$1$1$1 extends r implements r7.a<t0> {
    final /* synthetic */ ChangeList $offsetChanges;
    final /* synthetic */ SlotReader $reader;
    final /* synthetic */ MovableContentStateReference $to;
    final /* synthetic */ ComposerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerImpl$insertMovableContentGuarded$1$1$1$1(ComposerImpl composerImpl, ChangeList changeList, SlotReader slotReader, MovableContentStateReference movableContentStateReference) {
        super(0);
        this.this$0 = composerImpl;
        this.$offsetChanges = changeList;
        this.$reader = slotReader;
        this.$to = movableContentStateReference;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2950invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2950invoke() {
        ComposerChangeListWriter composerChangeListWriter = this.this$0.changeListWriter;
        ChangeList changeList = this.$offsetChanges;
        ComposerImpl composerImpl = this.this$0;
        SlotReader slotReader = this.$reader;
        MovableContentStateReference movableContentStateReference = this.$to;
        ChangeList changeList2 = composerChangeListWriter.getChangeList();
        try {
            composerChangeListWriter.setChangeList(changeList);
            SlotReader reader = composerImpl.getReader();
            int[] iArr = composerImpl.nodeCountOverrides;
            IntMap intMap = composerImpl.providerUpdates;
            composerImpl.nodeCountOverrides = null;
            composerImpl.providerUpdates = null;
            try {
                composerImpl.setReader$runtime_release(slotReader);
                ComposerChangeListWriter composerChangeListWriter2 = composerImpl.changeListWriter;
                boolean implicitRootStart = composerChangeListWriter2.getImplicitRootStart();
                try {
                    composerChangeListWriter2.setImplicitRootStart(false);
                    composerImpl.invokeMovableContentLambda(movableContentStateReference.getContent$runtime_release(), movableContentStateReference.getLocals(), movableContentStateReference.getParameter(), true);
                } finally {
                    composerChangeListWriter2.setImplicitRootStart(implicitRootStart);
                }
            } finally {
                composerImpl.setReader$runtime_release(reader);
                composerImpl.nodeCountOverrides = iArr;
                composerImpl.providerUpdates = intMap;
            }
        } finally {
            composerChangeListWriter.setChangeList(changeList2);
        }
    }
}
