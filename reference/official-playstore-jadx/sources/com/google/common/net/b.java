package com.google.common.net;

import androidx.media3.common.MimeTypes;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.common.base.e;
import com.google.common.base.h;
import com.google.common.base.i;
import com.google.common.base.o;
import com.google.common.base.z;
import com.google.common.collect.a0;
import com.google.common.collect.a3;
import com.google.common.collect.b1;
import com.google.common.collect.d0;
import com.google.common.collect.e1;
import com.google.common.collect.f0;
import com.google.common.collect.h1;
import com.google.common.collect.i1;
import com.google.common.collect.k1;
import com.google.common.collect.o3;
import com.google.common.collect.q0;
import com.google.common.collect.v2;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final i1 f14167f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.common.base.a f14168g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashMap f14169h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f14170i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final z f14171j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i1 f14174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f14175d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f14176e;

    static {
        i1 i1Var;
        String strS = t7.a.S(StandardCharsets.UTF_8.name());
        a0.b(HttpAuthHeader.Parameters.Charset, strS);
        f0 f0VarB = f0.b();
        b1 b1VarM = (b1) f0VarB.get(HttpAuthHeader.Parameters.Charset);
        if (b1VarM == null) {
            b1VarM = h1.m(4);
            f0VarB.put(HttpAuthHeader.Parameters.Charset, b1VarM);
        }
        b1VarM.a(strS);
        Collection collectionEntrySet = f0VarB.entrySet();
        if (((AbstractCollection) collectionEntrySet).isEmpty()) {
            i1Var = q0.f14086q;
        } else {
            d0<Map.Entry> d0Var = (d0) collectionEntrySet;
            k1 k1Var = new k1(d0Var.f13962l.size());
            int i10 = 0;
            for (Map.Entry entry : d0Var) {
                Object key = entry.getKey();
                o3 o3VarF = ((e1) entry.getValue()).f();
                k1Var.c(key, o3VarF);
                i10 += o3VarF.f14080n;
            }
            i1Var = new i1(k1Var.a(true), i10);
        }
        f14167f = i1Var;
        com.google.common.base.b bVar = com.google.common.base.b.f13889n;
        com.google.common.base.b bVar2 = com.google.common.base.b.f13890o;
        bVar2.getClass();
        h hVar = new h(bVar2);
        bVar.getClass();
        f14168g = new com.google.common.base.a(new com.google.common.base.a(new com.google.common.base.a(bVar, hVar), new e(' ', 1)), i.b("()<>@,;:\\\"/[]?=").d());
        i.b("\"\\\r").d().getClass();
        i.b(" \t\r\n");
        f14169h = new HashMap();
        a("*", "*");
        a("text", "*");
        a("image", "*");
        a("audio", "*");
        a("video", "*");
        a(MimeTypes.BASE_TYPE_APPLICATION, "*");
        a("font", "*");
        b("text", "cache-manifest");
        b("text", "css");
        b("text", "csv");
        b("text", "html");
        b("text", "calendar");
        b("text", "markdown");
        b("text", "plain");
        b("text", "javascript");
        b("text", "tab-separated-values");
        b("text", "vcard");
        b("text", "vnd.wap.wml");
        b("text", "xml");
        b("text", "vtt");
        a("image", "bmp");
        a("image", "x-canon-crw");
        a("image", "gif");
        a("image", "vnd.microsoft.icon");
        a("image", "jpeg");
        a("image", "png");
        a("image", "vnd.adobe.photoshop");
        b("image", "svg+xml");
        a("image", "tiff");
        a("image", "webp");
        a("image", "heif");
        a("image", "jp2");
        a("audio", "mp4");
        a("audio", "mpeg");
        a("audio", "ogg");
        a("audio", "webm");
        a("audio", "l16");
        a("audio", "l24");
        a("audio", "basic");
        a("audio", HlsSegmentFormat.AAC);
        a("audio", "vorbis");
        a("audio", "x-ms-wma");
        a("audio", "x-ms-wax");
        a("audio", "vnd.rn-realaudio");
        a("audio", "vnd.wave");
        a("video", "mp4");
        a("video", "mpeg");
        a("video", "ogg");
        a("video", "quicktime");
        a("video", "webm");
        a("video", "x-ms-wmv");
        a("video", "x-flv");
        a("video", "3gpp");
        a("video", "3gpp2");
        b(MimeTypes.BASE_TYPE_APPLICATION, "xml");
        b(MimeTypes.BASE_TYPE_APPLICATION, "atom+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-bzip2");
        b(MimeTypes.BASE_TYPE_APPLICATION, "dart");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.apple.pkpass");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.ms-fontobject");
        a(MimeTypes.BASE_TYPE_APPLICATION, "epub+zip");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-www-form-urlencoded");
        a(MimeTypes.BASE_TYPE_APPLICATION, "pkcs12");
        a(MimeTypes.BASE_TYPE_APPLICATION, "binary");
        a(MimeTypes.BASE_TYPE_APPLICATION, "geo+json");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-gzip");
        a(MimeTypes.BASE_TYPE_APPLICATION, "hal+json");
        b(MimeTypes.BASE_TYPE_APPLICATION, "javascript");
        a(MimeTypes.BASE_TYPE_APPLICATION, "jose");
        a(MimeTypes.BASE_TYPE_APPLICATION, "jose+json");
        f14170i = b(MimeTypes.BASE_TYPE_APPLICATION, "json");
        a(MimeTypes.BASE_TYPE_APPLICATION, "jwt");
        b(MimeTypes.BASE_TYPE_APPLICATION, "manifest+json");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.google-earth.kml+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.google-earth.kmz");
        a(MimeTypes.BASE_TYPE_APPLICATION, "mbox");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-apple-aspen-config");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.ms-excel");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.ms-outlook");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.ms-powerpoint");
        a(MimeTypes.BASE_TYPE_APPLICATION, "msword");
        a(MimeTypes.BASE_TYPE_APPLICATION, "dash+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "wasm");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-nacl");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-pnacl");
        a(MimeTypes.BASE_TYPE_APPLICATION, "octet-stream");
        a(MimeTypes.BASE_TYPE_APPLICATION, "ogg");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.openxmlformats-officedocument.wordprocessingml.document");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.openxmlformats-officedocument.presentationml.presentation");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.oasis.opendocument.graphics");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.oasis.opendocument.presentation");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.oasis.opendocument.spreadsheet");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.oasis.opendocument.text");
        b(MimeTypes.BASE_TYPE_APPLICATION, "opensearchdescription+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "pdf");
        a(MimeTypes.BASE_TYPE_APPLICATION, "postscript");
        a(MimeTypes.BASE_TYPE_APPLICATION, "protobuf");
        b(MimeTypes.BASE_TYPE_APPLICATION, "rdf+xml");
        b(MimeTypes.BASE_TYPE_APPLICATION, "rtf");
        a(MimeTypes.BASE_TYPE_APPLICATION, "font-sfnt");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-shockwave-flash");
        a(MimeTypes.BASE_TYPE_APPLICATION, "vnd.sketchup.skp");
        b(MimeTypes.BASE_TYPE_APPLICATION, "soap+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "x-tar");
        a(MimeTypes.BASE_TYPE_APPLICATION, "font-woff");
        a(MimeTypes.BASE_TYPE_APPLICATION, "font-woff2");
        b(MimeTypes.BASE_TYPE_APPLICATION, "xhtml+xml");
        b(MimeTypes.BASE_TYPE_APPLICATION, "xrd+xml");
        a(MimeTypes.BASE_TYPE_APPLICATION, "zip");
        a("font", "collection");
        a("font", "otf");
        a("font", "sfnt");
        a("font", "ttf");
        a("font", "woff");
        a("font", "woff2");
        f14171j = new z(new o("; "), 2);
    }

    public b(String str, String str2, i1 i1Var) {
        this.f14172a = str;
        this.f14173b = str2;
        this.f14174c = i1Var;
    }

    public static void a(String str, String str2) {
        b bVar = new b(str, str2, q0.f14086q);
        f14169h.put(bVar, bVar);
    }

    public static b b(String str, String str2) {
        b bVar = new b(str, str2, f14167f);
        f14169h.put(bVar, bVar);
        StandardCharsets.UTF_8.getClass();
        return bVar;
    }

    public final v2 c() {
        return new v2(this.f14174c.a(), new a8.e(new androidx.media3.common.e(20), 10));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f14172a.equals(bVar.f14172a) && this.f14173b.equals(bVar.f14173b) && c().equals(bVar.c());
    }

    public final int hashCode() {
        int i10 = this.f14176e;
        if (i10 != 0) {
            return i10;
        }
        int iHashCode = Arrays.hashCode(new Object[]{this.f14172a, this.f14173b, c()});
        this.f14176e = iHashCode;
        return iHashCode;
    }

    public final String toString() {
        String str = this.f14175d;
        if (str != null) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f14172a);
        sb2.append('/');
        sb2.append(this.f14173b);
        i1 i1Var = this.f14174c;
        if (!i1Var.isEmpty()) {
            sb2.append("; ");
            Collection collectionEntries = new a3(i1Var, new a8.e(new androidx.media3.common.e(19), 10)).entries();
            z zVar = f14171j;
            zVar.getClass();
            try {
                zVar.b(sb2, collectionEntries.iterator());
            } catch (IOException e5) {
                throw new AssertionError(e5);
            }
        }
        String string = sb2.toString();
        this.f14175d = string;
        return string;
    }
}
