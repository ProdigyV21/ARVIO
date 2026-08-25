package androidx.compose.material3;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import na.j;
import na.k;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0004\u0010\u000bR \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/MappedInteractionSource;", "Landroidx/compose/foundation/interaction/InteractionSource;", "underlyingInteractionSource", "Landroidx/compose/ui/geometry/Offset;", "delta", "<init>", "(Landroidx/compose/foundation/interaction/InteractionSource;JLkotlin/jvm/internal/h;)V", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "press", "mapPress", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)Landroidx/compose/foundation/interaction/PressInteraction$Press;", "J", "", "mappedPresses", "Ljava/util/Map;", "Lna/j;", "Landroidx/compose/foundation/interaction/Interaction;", "interactions", "Lna/j;", "getInteractions", "()Lna/j;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MappedInteractionSource implements InteractionSource {
    public static final int $stable = 0;
    private final long delta;
    private final j<Interaction> interactions;
    private final Map<PressInteraction.Press, PressInteraction.Press> mappedPresses;

    public /* synthetic */ MappedInteractionSource(InteractionSource interactionSource, long j10, kotlin.jvm.internal.h hVar) {
        this(interactionSource, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PressInteraction.Press mapPress(PressInteraction.Press press) {
        return new PressInteraction.Press(Offset.m3240minusMKHz9U(press.getPressPosition(), this.delta), null);
    }

    @Override // androidx.compose.foundation.interaction.InteractionSource
    public j<Interaction> getInteractions() {
        return this.interactions;
    }

    private MappedInteractionSource(InteractionSource interactionSource, long j10) {
        this.delta = j10;
        this.mappedPresses = new LinkedHashMap();
        final j<Interaction> interactions = interactionSource.getInteractions();
        this.interactions = new j<Interaction>() { // from class: androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1

            /* JADX INFO: renamed from: androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ MappedInteractionSource this$0;

                /* JADX INFO: renamed from: androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @f7.e(c = "androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2", f = "MappedInteractionSource.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends f7.c {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(k kVar, MappedInteractionSource mappedInteractionSource) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = mappedInteractionSource;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2$1 r0 = (androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2$1 r0 = new androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L2e
                        if (r1 != r2) goto L26
                        k2.c.G(r6)
                        goto L95
                    L26:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2e:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        androidx.compose.foundation.interaction.Interaction r5 = (androidx.compose.foundation.interaction.Interaction) r5
                        boolean r1 = r5 instanceof androidx.compose.foundation.interaction.PressInteraction.Press
                        if (r1 == 0) goto L4d
                        androidx.compose.material3.MappedInteractionSource r1 = r4.this$0
                        r3 = r5
                        androidx.compose.foundation.interaction.PressInteraction$Press r3 = (androidx.compose.foundation.interaction.PressInteraction.Press) r3
                        androidx.compose.foundation.interaction.PressInteraction$Press r1 = androidx.compose.material3.MappedInteractionSource.access$mapPress(r1, r3)
                        androidx.compose.material3.MappedInteractionSource r3 = r4.this$0
                        java.util.Map r3 = androidx.compose.material3.MappedInteractionSource.access$getMappedPresses$p(r3)
                        r3.put(r5, r1)
                        r5 = r1
                        goto L8a
                    L4d:
                        boolean r1 = r5 instanceof androidx.compose.foundation.interaction.PressInteraction.Cancel
                        if (r1 == 0) goto L6c
                        androidx.compose.material3.MappedInteractionSource r1 = r4.this$0
                        java.util.Map r1 = androidx.compose.material3.MappedInteractionSource.access$getMappedPresses$p(r1)
                        androidx.compose.foundation.interaction.PressInteraction$Cancel r5 = (androidx.compose.foundation.interaction.PressInteraction.Cancel) r5
                        androidx.compose.foundation.interaction.PressInteraction$Press r3 = r5.getPress()
                        java.lang.Object r1 = r1.remove(r3)
                        androidx.compose.foundation.interaction.PressInteraction$Press r1 = (androidx.compose.foundation.interaction.PressInteraction.Press) r1
                        if (r1 != 0) goto L66
                        goto L8a
                    L66:
                        androidx.compose.foundation.interaction.PressInteraction$Cancel r5 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                        r5.<init>(r1)
                        goto L8a
                    L6c:
                        boolean r1 = r5 instanceof androidx.compose.foundation.interaction.PressInteraction.Release
                        if (r1 == 0) goto L8a
                        androidx.compose.material3.MappedInteractionSource r1 = r4.this$0
                        java.util.Map r1 = androidx.compose.material3.MappedInteractionSource.access$getMappedPresses$p(r1)
                        androidx.compose.foundation.interaction.PressInteraction$Release r5 = (androidx.compose.foundation.interaction.PressInteraction.Release) r5
                        androidx.compose.foundation.interaction.PressInteraction$Press r3 = r5.getPress()
                        java.lang.Object r1 = r1.remove(r3)
                        androidx.compose.foundation.interaction.PressInteraction$Press r1 = (androidx.compose.foundation.interaction.PressInteraction.Press) r1
                        if (r1 != 0) goto L85
                        goto L8a
                    L85:
                        androidx.compose.foundation.interaction.PressInteraction$Release r5 = new androidx.compose.foundation.interaction.PressInteraction$Release
                        r5.<init>(r1)
                    L8a:
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L95
                        return r6
                    L95:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MappedInteractionSource$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d7.d dVar) {
                Object objCollect = interactions.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }
}
