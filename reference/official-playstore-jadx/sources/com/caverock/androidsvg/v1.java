package com.caverock.androidsvg;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes4.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w0 f12469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f12470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap f12471c;

    /* JADX WARN: Multi-variable type inference failed */
    public static b1 b(z0 z0Var, String str) {
        b1 b1VarB;
        b1 b1Var = (b1) z0Var;
        if (str.equals(b1Var.f12261c)) {
            return b1Var;
        }
        for (Object obj : z0Var.getChildren()) {
            if (obj instanceof b1) {
                b1 b1Var2 = (b1) obj;
                if (str.equals(b1Var2.f12261c)) {
                    return b1Var2;
                }
                if ((obj instanceof z0) && (b1VarB = b((z0) obj, str)) != null) {
                    return b1VarB;
                }
            }
        }
        return null;
    }

    public static v1 c(InputStream inputStream) {
        n2 n2Var = new n2();
        n2Var.f12383a = null;
        n2Var.f12384b = null;
        n2Var.f12385c = false;
        n2Var.f12387e = false;
        n2Var.f12388f = null;
        n2Var.f12389g = null;
        n2Var.f12390h = false;
        n2Var.f12391i = null;
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        try {
            inputStream.mark(3);
            int i10 = inputStream.read() + (inputStream.read() << 8);
            inputStream.reset();
            if (i10 == 35615) {
                inputStream = new BufferedInputStream(new GZIPInputStream(inputStream));
            }
        } catch (IOException unused) {
        }
        try {
            inputStream.mark(4096);
            n2Var.B(inputStream);
            return n2Var.f12383a;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused2) {
                Log.e("SVGParser", "Exception thrown closing input stream");
            }
        }
    }

    public final u a() {
        int i10;
        float fC;
        int i11;
        w0 w0Var = this.f12469a;
        g0 g0Var = w0Var.f12475r;
        g0 g0Var2 = w0Var.f12476s;
        if (g0Var == null || g0Var.g() || (i10 = g0Var.f12318l) == 9 || i10 == 2 || i10 == 3) {
            return new u(-1.0f, -1.0f, -1.0f, -1.0f);
        }
        float fC2 = g0Var.c();
        if (g0Var2 == null) {
            u uVar = this.f12469a.f12335o;
            fC = uVar != null ? (uVar.f12450d * fC2) / uVar.f12449c : fC2;
        } else {
            if (g0Var2.g() || (i11 = g0Var2.f12318l) == 9 || i11 == 2 || i11 == 3) {
                return new u(-1.0f, -1.0f, -1.0f, -1.0f);
            }
            fC = g0Var2.c();
        }
        return new u(0.0f, 0.0f, fC2, fC);
    }

    public final b1 d(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1).replace("\\\"", "\"");
        } else if (str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1).replace("\\'", "'");
        }
        String strReplace = str.replace("\\\n", "").replace("\\A", "\n");
        if (strReplace.length() <= 1 || !strReplace.startsWith("#")) {
            return null;
        }
        String strSubstring = strReplace.substring(1);
        HashMap map = this.f12471c;
        if (strSubstring == null || strSubstring.length() == 0) {
            return null;
        }
        if (strSubstring.equals(this.f12469a.f12261c)) {
            return this.f12469a;
        }
        if (map.containsKey(strSubstring)) {
            return (b1) map.get(strSubstring);
        }
        b1 b1VarB = b(this.f12469a, strSubstring);
        map.put(strSubstring, b1VarB);
        return b1VarB;
    }
}
