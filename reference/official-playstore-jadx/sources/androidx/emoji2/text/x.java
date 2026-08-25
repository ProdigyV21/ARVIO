package androidx.emoji2.text;

import android.content.Context;
import android.text.TextUtils;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class x implements v, l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2650a;

    public x(Context context) {
        this.f2650a = context.getApplicationContext();
    }

    @Override // androidx.emoji2.text.l
    public void a(m2.f0 f0Var) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new q(this, f0Var, threadPoolExecutor, 0));
    }

    @Override // androidx.emoji2.text.v
    public boolean b(CharSequence charSequence, int i10, int i11, i0 i0Var) {
        if (!TextUtils.equals(charSequence.subSequence(i10, i11), (String) this.f2650a)) {
            return true;
        }
        i0Var.f2618c = (i0Var.f2618c & 3) | 4;
        return false;
    }

    @Override // androidx.emoji2.text.v
    public Object getResult() {
        return this;
    }

    public x(String str) {
        this.f2650a = str;
    }
}
