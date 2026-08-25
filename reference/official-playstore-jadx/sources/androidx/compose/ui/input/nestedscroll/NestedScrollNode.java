package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalModifierNodeKt;
import androidx.compose.ui.modifier.b;
import androidx.compose.ui.node.DelegatableNode;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\"\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J*\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u001cH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ#\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u001cH\u0096@ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\u000bH\u0016¢\u0006\u0004\b#\u0010\u000fJ\u000f\u0010$\u001a\u00020\u000bH\u0016¢\u0006\u0004\b$\u0010\u000fJ!\u0010&\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0004\b%\u0010\tR\"\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u001a\u0010/\u001a\u00020.8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0016\u00105\u001a\u0004\u0018\u00010\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0016\u00107\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u0010)R\u0014\u0010;\u001a\u0002088BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/ui/node/DelegatableNode;", "Landroidx/compose/ui/Modifier$Node;", "connection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "dispatcher", "<init>", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "newDispatcher", "Lx6/t0;", "updateDispatcher", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "updateDispatcherFields", "()V", "resetDispatcherFields", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLd7/d;)Ljava/lang/Object;", "onPreFling", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "onAttach", "onDetach", "updateNode$ui_release", "updateNode", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setConnection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "resolvedDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "getParentModifierLocal", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "parentModifierLocal", "getParentConnection", "parentConnection", "Lka/k0;", "getNestedCoroutineScope", "()Lka/k0;", "nestedCoroutineScope", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NestedScrollNode extends Modifier.Node implements ModifierLocalModifierNode, NestedScrollConnection, DelegatableNode {
    public static final int $stable = 8;
    private NestedScrollConnection connection;
    private final ModifierLocalMap providedValues;
    private NestedScrollDispatcher resolvedDispatcher;

    /* JADX INFO: renamed from: androidx.compose.ui.input.nestedscroll.NestedScrollNode$updateDispatcherFields$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lka/k0;", "invoke", "()Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<k0> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final k0 invoke() {
            return NestedScrollNode.this.getNestedCoroutineScope();
        }
    }

    public NestedScrollNode(NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher) {
        this.connection = nestedScrollConnection;
        this.resolvedDispatcher = nestedScrollDispatcher == null ? new NestedScrollDispatcher() : nestedScrollDispatcher;
        this.providedValues = ModifierLocalModifierNodeKt.modifierLocalMapOf(new x(NestedScrollNodeKt.getModifierLocalNestedScroll(), this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final k0 getNestedCoroutineScope() {
        k0 nestedCoroutineScope;
        NestedScrollNode parentModifierLocal = getParentModifierLocal();
        if (parentModifierLocal != null && (nestedCoroutineScope = parentModifierLocal.getNestedCoroutineScope()) != null) {
            return nestedCoroutineScope;
        }
        k0 scope = this.resolvedDispatcher.getScope();
        if (scope != null) {
            return scope;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }

    private final NestedScrollConnection getParentConnection() {
        if (getIsAttached()) {
            return (NestedScrollConnection) b.a(this, NestedScrollNodeKt.getModifierLocalNestedScroll());
        }
        return null;
    }

    private final NestedScrollNode getParentModifierLocal() {
        if (getIsAttached()) {
            return (NestedScrollNode) b.a(this, NestedScrollNodeKt.getModifierLocalNestedScroll());
        }
        return null;
    }

    private final void resetDispatcherFields() {
        if (this.resolvedDispatcher.getModifierLocalNode() == this) {
            this.resolvedDispatcher.setModifierLocalNode$ui_release(null);
        }
    }

    private final void updateDispatcher(NestedScrollDispatcher newDispatcher) {
        resetDispatcherFields();
        if (newDispatcher == null) {
            this.resolvedDispatcher = new NestedScrollDispatcher();
        } else if (!newDispatcher.equals(this.resolvedDispatcher)) {
            this.resolvedDispatcher = newDispatcher;
        }
        if (getIsAttached()) {
            updateDispatcherFields();
        }
    }

    private final void updateDispatcherFields() {
        this.resolvedDispatcher.setModifierLocalNode$ui_release(this);
        this.resolvedDispatcher.setCalculateNestedScrollScope$ui_release(new AnonymousClass1());
        this.resolvedDispatcher.setScope$ui_release(getCoroutineScope());
    }

    public final NestedScrollConnection getConnection() {
        return this.connection;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.modifier.ModifierLocalReadScope
    public final /* synthetic */ Object getCurrent(ModifierLocal modifierLocal) {
        return b.a(this, modifierLocal);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public ModifierLocalMap getProvidedValues() {
        return this.providedValues;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDispatcherFields();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        resetDispatcherFields();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo368onPostFlingRZ2iAVY(long r13, long r15, d7.d<? super androidx.compose.ui.unit.Velocity> r17) {
        /*
            r12 = this;
            r0 = r17
            boolean r1 = r0 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r1 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L16
            int r2 = r2 - r3
            r1.label = r2
        L14:
            r7 = r1
            goto L1c
        L16:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r1 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            r1.<init>(r12, r0)
            goto L14
        L1c:
            java.lang.Object r0 = r7.result
            int r1 = r7.label
            r8 = 2
            r2 = 1
            e7.a r9 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r2) goto L38
            if (r1 != r8) goto L30
            long r1 = r7.J$0
            k2.c.G(r0)
            goto L82
        L30:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L38:
            long r1 = r7.J$1
            long r3 = r7.J$0
            java.lang.Object r5 = r7.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r5 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r5
            k2.c.G(r0)
            goto L5e
        L44:
            k2.c.G(r0)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r0 = r12.connection
            r7.L$0 = r12
            r7.J$0 = r13
            r5 = r15
            r7.J$1 = r5
            r7.label = r2
            r3 = r13
            r2 = r0
            java.lang.Object r0 = r2.mo368onPostFlingRZ2iAVY(r3, r5, r7)
            if (r0 != r9) goto L5b
            goto L80
        L5b:
            r5 = r12
            r3 = r13
            r1 = r15
        L5e:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r10 = r0.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r0 = r5.getParentConnection()
            if (r0 == 0) goto L8a
            long r3 = androidx.compose.ui.unit.Velocity.m5913plusAH228Gc(r3, r10)
            long r5 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r1, r10)
            r1 = 0
            r7.L$0 = r1
            r7.J$0 = r10
            r7.label = r8
            r2 = r0
            java.lang.Object r0 = r2.mo368onPostFlingRZ2iAVY(r3, r5, r7)
            if (r0 != r9) goto L81
        L80:
            return r9
        L81:
            r1 = r10
        L82:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r3 = r0.getPackedValue()
            r10 = r1
            goto L90
        L8a:
            androidx.compose.ui.unit.Velocity$Companion r0 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r3 = r0.m5920getZero9UxMQ8M()
        L90:
            long r0 = androidx.compose.ui.unit.Velocity.m5913plusAH228Gc(r10, r3)
            androidx.compose.ui.unit.Velocity r0 = androidx.compose.ui.unit.Velocity.m5900boximpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo368onPostFlingRZ2iAVY(long, long, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        long jMo369onPostScrollDzOQY0M = this.connection.mo369onPostScrollDzOQY0M(consumed, available, source);
        NestedScrollConnection parentConnection = getParentConnection();
        return Offset.m3241plusMKHz9U(jMo369onPostScrollDzOQY0M, parentConnection != null ? parentConnection.mo369onPostScrollDzOQY0M(Offset.m3241plusMKHz9U(consumed, jMo369onPostScrollDzOQY0M), Offset.m3240minusMKHz9U(available, jMo369onPostScrollDzOQY0M), source) : Offset.INSTANCE.m3252getZeroF1C5BW0());
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
    
        if (r12 != r4) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo370onPreFlingQWom1Mo(long r10, d7.d<? super androidx.compose.ui.unit.Velocity> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            if (r0 == 0) goto L13
            r0 = r12
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L34
            if (r1 != r2) goto L2c
            long r10 = r0.J$0
            k2.c.G(r12)
            goto L7b
        L2c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L34:
            long r10 = r0.J$0
            java.lang.Object r1 = r0.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r1 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r1
            k2.c.G(r12)
            goto L55
        L3e:
            k2.c.G(r12)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r12 = r9.getParentConnection()
            if (r12 == 0) goto L5f
            r0.L$0 = r9
            r0.J$0 = r10
            r0.label = r3
            java.lang.Object r12 = r12.mo370onPreFlingQWom1Mo(r10, r0)
            if (r12 != r4) goto L54
            goto L7a
        L54:
            r1 = r9
        L55:
            androidx.compose.ui.unit.Velocity r12 = (androidx.compose.ui.unit.Velocity) r12
            long r5 = r12.getPackedValue()
        L5b:
            r7 = r5
            r5 = r10
            r10 = r7
            goto L67
        L5f:
            androidx.compose.ui.unit.Velocity$Companion r12 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r5 = r12.m5920getZero9UxMQ8M()
            r1 = r9
            goto L5b
        L67:
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r12 = r1.connection
            long r5 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r5, r10)
            r1 = 0
            r0.L$0 = r1
            r0.J$0 = r10
            r0.label = r2
            java.lang.Object r12 = r12.mo370onPreFlingQWom1Mo(r5, r0)
            if (r12 != r4) goto L7b
        L7a:
            return r4
        L7b:
            androidx.compose.ui.unit.Velocity r12 = (androidx.compose.ui.unit.Velocity) r12
            long r0 = r12.getPackedValue()
            long r10 = androidx.compose.ui.unit.Velocity.m5913plusAH228Gc(r10, r0)
            androidx.compose.ui.unit.Velocity r10 = androidx.compose.ui.unit.Velocity.m5900boximpl(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo370onPreFlingQWom1Mo(long, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        NestedScrollConnection parentConnection = getParentConnection();
        long jMo371onPreScrollOzD1aCk = parentConnection != null ? parentConnection.mo371onPreScrollOzD1aCk(available, source) : Offset.INSTANCE.m3252getZeroF1C5BW0();
        return Offset.m3241plusMKHz9U(jMo371onPreScrollOzD1aCk, this.connection.mo371onPreScrollOzD1aCk(Offset.m3240minusMKHz9U(available, jMo371onPreScrollOzD1aCk), source));
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ void provide(ModifierLocal modifierLocal, Object obj) {
        b.c(this, modifierLocal, obj);
    }

    public final void setConnection(NestedScrollConnection nestedScrollConnection) {
        this.connection = nestedScrollConnection;
    }

    public final void updateNode$ui_release(NestedScrollConnection connection, NestedScrollDispatcher dispatcher) {
        this.connection = connection;
        updateDispatcher(dispatcher);
    }
}
