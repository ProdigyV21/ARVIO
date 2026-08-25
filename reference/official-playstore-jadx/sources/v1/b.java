package v1;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.activity.p;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f22118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f22119b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f22120c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final File f22121d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f22122e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f22123f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c[] f22124g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f22125h;

    public b(AssetManager assetManager, Executor executor, e eVar, String str, File file) {
        this.f22118a = executor;
        this.f22119b = eVar;
        this.f22122e = str;
        this.f22121d = file;
        int i10 = Build.VERSION.SDK_INT;
        byte[] bArr = null;
        if (i10 >= 24) {
            if (i10 < 31) {
                switch (i10) {
                    case 24:
                    case 25:
                        bArr = g.f22142h;
                        break;
                    case 26:
                        bArr = g.f22141g;
                        break;
                    case 27:
                        bArr = g.f22140f;
                        break;
                    case 28:
                    case 29:
                    case 30:
                        bArr = g.f22139e;
                        break;
                }
            } else {
                bArr = g.f22138d;
            }
        }
        this.f22120c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e5) {
            String message = e5.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            this.f22119b.b();
            return null;
        }
    }

    public final void b(int i10, Serializable serializable) {
        this.f22118a.execute(new p(i10, 4, this, serializable));
    }
}
