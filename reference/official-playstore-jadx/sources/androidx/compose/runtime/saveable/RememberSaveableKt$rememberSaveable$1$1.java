package androidx.compose.runtime.saveable;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "T", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RememberSaveableKt$rememberSaveable$1$1 extends r implements a<t0> {
    final /* synthetic */ String $finalKey;
    final /* synthetic */ SaveableHolder<T> $holder;
    final /* synthetic */ Object[] $inputs;
    final /* synthetic */ SaveableStateRegistry $registry;
    final /* synthetic */ Saver<T, ? extends Object> $saver;
    final /* synthetic */ T $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RememberSaveableKt$rememberSaveable$1$1(SaveableHolder<T> saveableHolder, Saver<T, ? extends Object> saver, SaveableStateRegistry saveableStateRegistry, String str, T t2, Object[] objArr) {
        super(0);
        this.$holder = saveableHolder;
        this.$saver = saver;
        this.$registry = saveableStateRegistry;
        this.$finalKey = str;
        this.$value = t2;
        this.$inputs = objArr;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m3085invoke();
        return t0.f22605a;
    }

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
    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m3085invoke() {
        this.$holder.update(this.$saver, this.$registry, this.$finalKey, this.$value, this.$inputs);
    }
}
