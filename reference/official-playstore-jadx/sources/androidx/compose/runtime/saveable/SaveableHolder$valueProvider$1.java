package androidx.compose.runtime.saveable;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaveableHolder$valueProvider$1 extends r implements a<Object> {
    final /* synthetic */ SaveableHolder<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SaveableHolder$valueProvider$1(SaveableHolder<T> saveableHolder) {
        super(0);
        this.this$0 = saveableHolder;
    }

    @Override // r7.a
    public final Object invoke() {
        Saver saver = ((SaveableHolder) this.this$0).saver;
        SaveableHolder<T> saveableHolder = this.this$0;
        Object obj = ((SaveableHolder) saveableHolder).value;
        if (obj != null) {
            return saver.save(saveableHolder, obj);
        }
        throw new IllegalArgumentException("Value should be initialized");
    }
}
