package dagger.hilt.android;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.c0;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.Contexts;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.n;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0007¢\u0006\u0004\b\t\u0010\nJ+\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0007¢\u0006\u0004\b\r\u0010\u000eJ+\u0010\u0011\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J+\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J \u0010\t\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\b¢\u0006\u0004\b\t\u0010\u0017J \u0010\r\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\f\u001a\u00020\u000bH\u0086\b¢\u0006\u0004\b\r\u0010\u0018J \u0010\u0011\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0086\b¢\u0006\u0004\b\u0011\u0010\u0019J \u0010\u0015\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0086\b¢\u0006\u0004\b\u0015\u0010\u001a¨\u0006\u001b"}, d2 = {"Ldagger/hilt/android/EntryPointAccessors;", "", "<init>", "()V", "T", "Landroid/content/Context;", "context", "Ljava/lang/Class;", "entryPoint", "fromApplication", "(Landroid/content/Context;Ljava/lang/Class;)Ljava/lang/Object;", "Landroid/app/Activity;", "activity", "fromActivity", "(Landroid/app/Activity;Ljava/lang/Class;)Ljava/lang/Object;", "Landroidx/fragment/app/c0;", "fragment", "fromFragment", "(Landroidx/fragment/app/c0;Ljava/lang/Class;)Ljava/lang/Object;", "Landroid/view/View;", "view", "fromView", "(Landroid/view/View;Ljava/lang/Class;)Ljava/lang/Object;", "(Landroid/content/Context;)Ljava/lang/Object;", "(Landroid/app/Activity;)Ljava/lang/Object;", "(Landroidx/fragment/app/c0;)Ljava/lang/Object;", "(Landroid/view/View;)Ljava/lang/Object;", "hilt-android_main_java_dagger_hilt_android-entry_point_accessors_internal_kt"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EntryPointAccessors {
    public static final EntryPointAccessors INSTANCE = new EntryPointAccessors();

    private EntryPointAccessors() {
    }

    @n
    public static final <T> T fromActivity(Activity activity, Class<T> entryPoint) {
        return (T) EntryPoints.get(activity, entryPoint);
    }

    @n
    public static final <T> T fromApplication(Context context, Class<T> entryPoint) {
        return (T) EntryPoints.get(Contexts.getApplication(context.getApplicationContext()), entryPoint);
    }

    @n
    public static final <T> T fromFragment(c0 fragment, Class<T> entryPoint) {
        return (T) EntryPoints.get(fragment, entryPoint);
    }

    @n
    public static final <T> T fromView(View view, Class<T> entryPoint) {
        return (T) EntryPoints.get(view, entryPoint);
    }

    public final <T> T fromActivity(Activity activity) {
        p.h();
        throw null;
    }

    public final <T> T fromApplication(Context context) {
        p.h();
        throw null;
    }

    public final <T> T fromFragment(c0 fragment) {
        p.h();
        throw null;
    }

    public final <T> T fromView(View view) {
        p.h();
        throw null;
    }
}
