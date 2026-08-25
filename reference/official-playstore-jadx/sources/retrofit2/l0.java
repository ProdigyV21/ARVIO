package retrofit2;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.sessions.FirebaseSessionsRegistrar;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class l0 implements com.google.firebase.components.f, z2.d, z4.h, t5.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21520i;

    public /* synthetic */ l0(int i10) {
        this.f21520i = i10;
    }

    @Override // t5.d
    public String a(Context context) {
        switch (this.f21520i) {
            case 18:
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                return applicationInfo != null ? String.valueOf(applicationInfo.targetSdkVersion) : "";
            case 19:
                return FirebaseCommonRegistrar.a(context);
            case 20:
                return context.getPackageManager().hasSystemFeature("android.hardware.type.television") ? "tv" : context.getPackageManager().hasSystemFeature("android.hardware.type.watch") ? "watch" : context.getPackageManager().hasSystemFeature("android.hardware.type.automotive") ? TtmlNode.TEXT_EMPHASIS_AUTO : (Build.VERSION.SDK_INT < 26 || !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) ? "" : "embedded";
            default:
                String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                return installerPackageName != null ? FirebaseCommonRegistrar.b(installerPackageName) : "";
        }
    }

    @Override // z2.d
    public Object apply(Object obj) {
        String strX = w5.a0.f22381b.x((w5.z) obj);
        Log.d("EventGDTLogger", "Session Event: ".concat(strX));
        return strX.getBytes(kotlin.text.a.f19924a);
    }

    @Override // com.google.firebase.components.f
    public Object f(com.google.firebase.components.u uVar) {
        switch (this.f21520i) {
            case 2:
                Set setH = uVar.h(t5.a.class);
                t5.c cVar = t5.c.f21893b;
                if (cVar == null) {
                    synchronized (t5.c.class) {
                        try {
                            cVar = t5.c.f21893b;
                            if (cVar == null) {
                                cVar = new t5.c(0);
                                t5.c.f21893b = cVar;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return new t5.b(setH, cVar);
            case 3:
            case 4:
            default:
                return FirebaseSessionsRegistrar.m6619getComponents$lambda5(uVar);
            case 5:
                return FirebaseSessionsRegistrar.m6614getComponents$lambda0(uVar);
            case 6:
                return FirebaseSessionsRegistrar.m6615getComponents$lambda1(uVar);
            case 7:
                return FirebaseSessionsRegistrar.m6616getComponents$lambda2(uVar);
            case 8:
                return FirebaseSessionsRegistrar.m6617getComponents$lambda3(uVar);
            case 9:
                return FirebaseSessionsRegistrar.m6618getComponents$lambda4(uVar);
        }
    }

    public /* synthetic */ l0(kb.d dVar) {
        this.f21520i = 3;
    }
}
