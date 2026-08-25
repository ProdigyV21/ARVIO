package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import t.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/runtime/MutableScatterMultiMap;", "", "Landroidx/compose/runtime/KeyInfo;", "invoke-SAeQiB4", "()Lt/z;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Pending$keyMap$2 extends r implements r7.a<MutableScatterMultiMap<Object, KeyInfo>> {
    final /* synthetic */ Pending this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Pending$keyMap$2(Pending pending) {
        super(0);
        this.this$0 = pending;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        return MutableScatterMultiMap.m2965boximpl(m2975invokeSAeQiB4());
    }

    /* JADX INFO: renamed from: invoke-SAeQiB4, reason: not valid java name */
    public final z m2975invokeSAeQiB4() {
        z zVarMultiMap = ComposerKt.multiMap(this.this$0.getKeyInfos().size());
        Pending pending = this.this$0;
        int size = pending.getKeyInfos().size();
        for (int i10 = 0; i10 < size; i10++) {
            KeyInfo keyInfo = pending.getKeyInfos().get(i10);
            MutableScatterMultiMap.m2971putimpl(zVarMultiMap, ComposerKt.getJoinedKey(keyInfo), keyInfo);
        }
        return zVarMultiMap;
    }
}
