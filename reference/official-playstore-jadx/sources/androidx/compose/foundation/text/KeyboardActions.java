package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBµ\u0001\u0012\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005\u0012\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005\u0012\u001b\b\u0002\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005\u0012\u001b\b\u0002\u0010\t\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005\u0012\u001b\b\u0002\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005\u0012\u001b\b\u0002\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u0005¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R*\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R*\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0018\u0010\u0017R*\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R*\u0010\t\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017R*\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017R*\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0015\u001a\u0004\b\u001c\u0010\u0017¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActions;", "", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "Lx6/n;", "onDone", "onGo", "onNext", "onPrevious", "onSearch", "onSend", "<init>", "(Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lr7/l;", "getOnDone", "()Lr7/l;", "getOnGo", "getOnNext", "getOnPrevious", "getOnSearch", "getOnSend", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyboardActions {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KeyboardActions Default = new KeyboardActions(null, null, null, null, null, null, 63, null);
    private final l<KeyboardActionScope, t0> onDone;
    private final l<KeyboardActionScope, t0> onGo;
    private final l<KeyboardActionScope, t0> onNext;
    private final l<KeyboardActionScope, t0> onPrevious;
    private final l<KeyboardActionScope, t0> onSearch;
    private final l<KeyboardActionScope, t0> onSend;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActions$Companion;", "", "()V", "Default", "Landroidx/compose/foundation/text/KeyboardActions;", "getDefault$annotations", "getDefault", "()Landroidx/compose/foundation/text/KeyboardActions;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getDefault$annotations() {
        }

        public final KeyboardActions getDefault() {
            return KeyboardActions.Default;
        }

        private Companion() {
        }
    }

    public KeyboardActions() {
        this(null, null, null, null, null, null, 63, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeyboardActions)) {
            return false;
        }
        KeyboardActions keyboardActions = (KeyboardActions) other;
        return p.a(this.onDone, keyboardActions.onDone) && p.a(this.onGo, keyboardActions.onGo) && p.a(this.onNext, keyboardActions.onNext) && p.a(this.onPrevious, keyboardActions.onPrevious) && p.a(this.onSearch, keyboardActions.onSearch) && p.a(this.onSend, keyboardActions.onSend);
    }

    public final l<KeyboardActionScope, t0> getOnDone() {
        return this.onDone;
    }

    public final l<KeyboardActionScope, t0> getOnGo() {
        return this.onGo;
    }

    public final l<KeyboardActionScope, t0> getOnNext() {
        return this.onNext;
    }

    public final l<KeyboardActionScope, t0> getOnPrevious() {
        return this.onPrevious;
    }

    public final l<KeyboardActionScope, t0> getOnSearch() {
        return this.onSearch;
    }

    public final l<KeyboardActionScope, t0> getOnSend() {
        return this.onSend;
    }

    public int hashCode() {
        l<KeyboardActionScope, t0> lVar = this.onDone;
        int iHashCode = (lVar != null ? lVar.hashCode() : 0) * 31;
        l<KeyboardActionScope, t0> lVar2 = this.onGo;
        int iHashCode2 = (iHashCode + (lVar2 != null ? lVar2.hashCode() : 0)) * 31;
        l<KeyboardActionScope, t0> lVar3 = this.onNext;
        int iHashCode3 = (iHashCode2 + (lVar3 != null ? lVar3.hashCode() : 0)) * 31;
        l<KeyboardActionScope, t0> lVar4 = this.onPrevious;
        int iHashCode4 = (iHashCode3 + (lVar4 != null ? lVar4.hashCode() : 0)) * 31;
        l<KeyboardActionScope, t0> lVar5 = this.onSearch;
        int iHashCode5 = (iHashCode4 + (lVar5 != null ? lVar5.hashCode() : 0)) * 31;
        l<KeyboardActionScope, t0> lVar6 = this.onSend;
        return iHashCode5 + (lVar6 != null ? lVar6.hashCode() : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KeyboardActions(l<? super KeyboardActionScope, t0> lVar, l<? super KeyboardActionScope, t0> lVar2, l<? super KeyboardActionScope, t0> lVar3, l<? super KeyboardActionScope, t0> lVar4, l<? super KeyboardActionScope, t0> lVar5, l<? super KeyboardActionScope, t0> lVar6) {
        this.onDone = lVar;
        this.onGo = lVar2;
        this.onNext = lVar3;
        this.onPrevious = lVar4;
        this.onSearch = lVar5;
        this.onSend = lVar6;
    }

    public /* synthetic */ KeyboardActions(l lVar, l lVar2, l lVar3, l lVar4, l lVar5, l lVar6, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : lVar, (i10 & 2) != 0 ? null : lVar2, (i10 & 4) != 0 ? null : lVar3, (i10 & 8) != 0 ? null : lVar4, (i10 & 16) != 0 ? null : lVar5, (i10 & 32) != 0 ? null : lVar6);
    }
}
