package io.github.jan.supabase.gotrue;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class SetupPlatformKt$addLifecycleCallbacks$1$onStop$1 extends r implements a<String> {
    public static final SetupPlatformKt$addLifecycleCallbacks$1$onStop$1 INSTANCE = new SetupPlatformKt$addLifecycleCallbacks$1$onStop$1();

    public SetupPlatformKt$addLifecycleCallbacks$1$onStop$1() {
        super(0);
    }

    @Override // r7.a
    public final String invoke() {
        return "Cancelling auto refresh because app is switching to the background";
    }
}
