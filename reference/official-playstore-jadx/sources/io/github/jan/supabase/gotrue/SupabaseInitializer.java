package io.github.jan.supabase.gotrue;

import android.content.Context;
import f2.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\t0\bH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/github/jan/supabase/gotrue/SupabaseInitializer;", "Lf2/b;", "Landroid/content/Context;", "<init>", "()V", "context", "create", "(Landroid/content/Context;)Landroid/content/Context;", "", "Ljava/lang/Class;", "dependencies", "()Ljava/util/List;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SupabaseInitializer implements b<Context> {
    @Override // f2.b
    public List<Class<? extends b<?>>> dependencies() {
        return z.f19728i;
    }

    @Override // f2.b
    public Context create(Context context) {
        Context applicationContext = context.getApplicationContext();
        SetupPlatformKt.appContext = applicationContext;
        return applicationContext;
    }
}
