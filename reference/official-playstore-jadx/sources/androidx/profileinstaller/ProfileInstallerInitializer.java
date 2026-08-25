package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import android.view.Choreographer;
import f2.b;
import java.util.Collections;
import java.util.List;
import v1.i;

/* JADX INFO: loaded from: classes3.dex */
public class ProfileInstallerInitializer implements b<i> {
    @Override // f2.b
    public final Object create(Context context) {
        if (Build.VERSION.SDK_INT < 24) {
            return new i();
        }
        Choreographer.getInstance().postFrameCallback(new androidx.compose.ui.text.input.b(this, context.getApplicationContext()));
        return new i();
    }

    @Override // f2.b
    public final List dependencies() {
        return Collections.EMPTY_LIST;
    }
}
