package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import com.squareup.moshi.w;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import qb.d;
import r7.p;
import r7.x;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J*\u0010\u0019\u001a\u0004\u0018\u00010\u00132\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0017\"\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0018\u0010 \u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u001e\u0010&\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/compose/runtime/internal/ComposableLambdaNImpl;", "Landroidx/compose/runtime/internal/ComposableLambdaN;", "", "key", "", "tracked", "arity", "<init>", "(IZI)V", "Lx6/t0;", "trackWrite", "()V", "Landroidx/compose/runtime/Composer;", "composer", "trackRead", "(Landroidx/compose/runtime/Composer;)V", "params", "realParamCount", "(I)I", "", "block", "update", "(Ljava/lang/Object;)V", "", "args", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "I", "getKey", "()I", "Z", "getArity", "_block", "Ljava/lang/Object;", "Landroidx/compose/runtime/RecomposeScope;", "scope", "Landroidx/compose/runtime/RecomposeScope;", "", "scopes", "Ljava/util/List;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ComposableLambdaNImpl implements ComposableLambdaN {
    public static final int $stable = 0;
    private Object _block;
    private final int arity;
    private final int key;
    private RecomposeScope scope;
    private List<RecomposeScope> scopes;
    private final boolean tracked;

    /* JADX INFO: renamed from: androidx.compose.runtime.internal.ComposableLambdaNImpl$invoke$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/runtime/Composer;", "nc", "", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ Object[] $args;
        final /* synthetic */ int $realParams;
        final /* synthetic */ ComposableLambdaNImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object[] objArr, int i10, ComposableLambdaNImpl composableLambdaNImpl) {
            super(2);
            this.$args = objArr;
            this.$realParams = i10;
            this.this$0 = composableLambdaNImpl;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            Object[] objArr = this.$args;
            i iVarX = d.X(0, this.$realParams);
            Object[] array = (iVarX.isEmpty() ? z.f19728i : Arrays.asList(kotlin.collections.r.S(objArr, iVarX.f22619i, iVarX.f22620l + 1))).toArray(new Object[0]);
            int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(((Integer) this.$args[this.$realParams + 1]).intValue());
            int length = (this.$args.length - this.$realParams) - 2;
            Object[] objArr2 = new Object[length];
            for (int i11 = 0; i11 < length; i11++) {
                objArr2[i11] = Integer.valueOf(RecomposeScopeImplKt.updateChangedFlags(((Integer) this.$args[this.$realParams + 2 + i11]).intValue()));
            }
            ComposableLambdaNImpl composableLambdaNImpl = this.this$0;
            w wVar = new w(4);
            wVar.b(array);
            wVar.a(composer);
            wVar.a(Integer.valueOf(iUpdateChangedFlags | 1));
            wVar.b(objArr2);
            ArrayList arrayList = wVar.f14489a;
            composableLambdaNImpl.invoke(arrayList.toArray(new Object[arrayList.size()]));
        }
    }

    public ComposableLambdaNImpl(int i10, boolean z, int i11) {
        this.key = i10;
        this.tracked = z;
        this.arity = i11;
    }

    private final int realParamCount(int params) {
        int i10 = params - 2;
        for (int i11 = 1; i11 * 10 < i10; i11++) {
            i10--;
        }
        return i10;
    }

    private final void trackRead(Composer composer) {
        RecomposeScope recomposeScope;
        if (!this.tracked || (recomposeScope = composer.getRecomposeScope()) == null) {
            return;
        }
        composer.recordUsed(recomposeScope);
        if (ComposableLambdaKt.replacableWith(this.scope, recomposeScope)) {
            this.scope = recomposeScope;
            return;
        }
        List<RecomposeScope> list = this.scopes;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.scopes = arrayList;
            arrayList.add(recomposeScope);
            return;
        }
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (ComposableLambdaKt.replacableWith(list.get(i10), recomposeScope)) {
                list.set(i10, recomposeScope);
                return;
            }
        }
        list.add(recomposeScope);
    }

    private final void trackWrite() {
        if (this.tracked) {
            RecomposeScope recomposeScope = this.scope;
            if (recomposeScope != null) {
                recomposeScope.invalidate();
                this.scope = null;
            }
            List<RecomposeScope> list = this.scopes;
            if (list != null) {
                int size = list.size();
                for (int i10 = 0; i10 < size; i10++) {
                    list.get(i10).invalidate();
                }
                list.clear();
            }
        }
    }

    @Override // androidx.compose.runtime.internal.ComposableLambdaN, kotlin.jvm.internal.k
    public int getArity() {
        return this.arity;
    }

    public final int getKey() {
        return this.key;
    }

    @Override // androidx.compose.runtime.internal.ComposableLambdaN, r7.x
    public Object invoke(Object... args) {
        int iRealParamCount = realParamCount(args.length);
        Composer composer = (Composer) args[iRealParamCount];
        i iVarX = d.X(0, args.length - 1);
        Object[] array = (iVarX.isEmpty() ? z.f19728i : Arrays.asList(kotlin.collections.r.S(args, iVarX.f22619i, iVarX.f22620l + 1))).toArray(new Object[0]);
        int iIntValue = ((Integer) args[args.length - 1]).intValue();
        Composer composerStartRestartGroup = composer.startRestartGroup(this.key);
        trackRead(composerStartRestartGroup);
        int iDifferentBits = iIntValue | (composerStartRestartGroup.changed(this) ? ComposableLambdaKt.differentBits(iRealParamCount) : ComposableLambdaKt.sameBits(iRealParamCount));
        x xVar = (x) this._block;
        w wVar = new w(2);
        wVar.b(array);
        wVar.a(Integer.valueOf(iDifferentBits));
        ArrayList arrayList = wVar.f14489a;
        Object objInvoke = xVar.invoke(arrayList.toArray(new Object[arrayList.size()]));
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(args, iRealParamCount, this));
        }
        return objInvoke;
    }

    public final void update(Object block) {
        if (kotlin.jvm.internal.p.a(block, this._block)) {
            return;
        }
        boolean z = this._block == null;
        this._block = (x) block;
        if (z) {
            return;
        }
        trackWrite();
    }
}
