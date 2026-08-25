package androidx.compose.ui.window;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidDialog_androidKt$Dialog$dialogId$1 extends r implements r7.a<UUID> {
    public static final AndroidDialog_androidKt$Dialog$dialogId$1 INSTANCE = new AndroidDialog_androidKt$Dialog$dialogId$1();

    public AndroidDialog_androidKt$Dialog$dialogId$1() {
        super(0);
    }

    @Override // r7.a
    public final UUID invoke() {
        return UUID.randomUUID();
    }
}
