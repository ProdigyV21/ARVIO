package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.util.Log;
import android.util.Xml;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.jsoup.parser.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public final class n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v1 f12383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z0 f12384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f12385c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f12386d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f12387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public l2 f12388f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public StringBuilder f12389g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f12390h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public StringBuilder f12391i;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0591  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void D(com.caverock.androidsvg.v0 r16, java.lang.String r17, java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 1952
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.n2.D(com.caverock.androidsvg.v0, java.lang.String, java.lang.String):void");
    }

    public static int b(float f10) {
        if (f10 < 0.0f) {
            return 0;
        }
        if (f10 > 255.0f) {
            return 255;
        }
        return Math.round(f10);
    }

    public static int d(float f10, float f11, float f12) {
        float f13 = f10 % 360.0f;
        if (f10 < 0.0f) {
            f13 += 360.0f;
        }
        float f14 = f13 / 60.0f;
        float f15 = f11 / 100.0f;
        float f16 = f12 / 100.0f;
        if (f15 < 0.0f) {
            f15 = 0.0f;
        } else if (f15 > 1.0f) {
            f15 = 1.0f;
        }
        float f17 = f16 >= 0.0f ? f16 > 1.0f ? 1.0f : f16 : 0.0f;
        float f18 = f17 <= 0.5f ? (f15 + 1.0f) * f17 : (f17 + f15) - (f15 * f17);
        float f19 = (f17 * 2.0f) - f18;
        return b(e(f19, f18, f14 - 2.0f) * 256.0f) | (b(e(f19, f18, f14 + 2.0f) * 256.0f) << 16) | (b(e(f19, f18, f14) * 256.0f) << 8);
    }

    public static float e(float f10, float f11, float f12) {
        if (f12 < 0.0f) {
            f12 += 6.0f;
        }
        if (f12 >= 6.0f) {
            f12 -= 6.0f;
        }
        if (f12 < 1.0f) {
            return ((f11 - f10) * f12) + f10;
        }
        if (f12 < 3.0f) {
            return f11;
        }
        if (f12 >= 4.0f) {
            return f10;
        }
        return ((4.0f - f12) * (f11 - f10)) + f10;
    }

    public static void f(x0 x0Var, Attributes attributes) {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String strTrim = attributes.getValue(i10).trim();
            int iG = com.arflix.tv.data.repository.g.g(attributes, i10);
            if (iG != 73) {
                switch (iG) {
                    case 52:
                        n0 n0Var = new n0(strTrim);
                        HashSet hashSet = new HashSet();
                        while (!n0Var.j()) {
                            String strQ = n0Var.q();
                            if (strQ.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                                hashSet.add(strQ.substring(35));
                            } else {
                                hashSet.add("UNSUPPORTED");
                            }
                            n0Var.v();
                        }
                        x0Var.d(hashSet);
                        break;
                    case 53:
                        x0Var.i(strTrim);
                        break;
                    case 54:
                        n0 n0Var2 = new n0(strTrim);
                        HashSet hashSet2 = new HashSet();
                        while (!n0Var2.j()) {
                            hashSet2.add(n0Var2.q());
                            n0Var2.v();
                        }
                        x0Var.j(hashSet2);
                        break;
                    case 55:
                        ArrayList arrayListQ = q(strTrim);
                        x0Var.h(arrayListQ != null ? new HashSet(arrayListQ) : new HashSet(0));
                        break;
                }
            } else {
                n0 n0Var3 = new n0(strTrim);
                HashSet hashSet3 = new HashSet();
                while (!n0Var3.j()) {
                    String strQ2 = n0Var3.q();
                    int iIndexOf = strQ2.indexOf(45);
                    if (iIndexOf != -1) {
                        strQ2 = strQ2.substring(0, iIndexOf);
                    }
                    hashSet3.add(new Locale(strQ2, "", "").getLanguage());
                    n0Var3.v();
                }
                x0Var.f(hashSet3);
            }
        }
    }

    public static void g(b1 b1Var, Attributes attributes) throws SVGParseException {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String qName = attributes.getQName(i10);
            if (qName.equals(TtmlNode.ATTR_ID) || qName.equals("xml:id")) {
                b1Var.f12261c = attributes.getValue(i10).trim();
                return;
            }
            if (qName.equals("xml:space")) {
                String strTrim = attributes.getValue(i10).trim();
                if ("default".equals(strTrim)) {
                    b1Var.f12262d = Boolean.FALSE;
                    return;
                } else {
                    if (!"preserve".equals(strTrim)) {
                        throw new SVGParseException(androidx.compose.material3.d.C("Invalid value for \"xml:space\" attribute: ", strTrim));
                    }
                    b1Var.f12262d = Boolean.TRUE;
                    return;
                }
            }
        }
    }

    public static void h(b0 b0Var, Attributes attributes) throws SVGParseException {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String strTrim = attributes.getValue(i10).trim();
            int iG = com.arflix.tv.data.repository.g.g(attributes, i10);
            if (iG == 23) {
                b0Var.f12259j = z(strTrim);
            } else if (iG != 24) {
                if (iG != 26) {
                    if (iG != 60) {
                        continue;
                    } else {
                        try {
                            b0Var.k = com.arflix.tv.data.repository.g.y(strTrim);
                        } catch (IllegalArgumentException unused) {
                            throw new SVGParseException(a0.c.l("Invalid spreadMethod attribute. \"", strTrim, "\" is not a valid value."));
                        }
                    }
                } else if ("".equals(attributes.getURI(i10)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i10))) {
                    b0Var.f12260l = strTrim;
                }
            } else if ("objectBoundingBox".equals(strTrim)) {
                b0Var.f12258i = Boolean.FALSE;
            } else {
                if (!"userSpaceOnUse".equals(strTrim)) {
                    throw new SVGParseException("Invalid value for attribute gradientUnits");
                }
                b0Var.f12258i = Boolean.TRUE;
            }
        }
    }

    public static void i(q0 q0Var, Attributes attributes, String str) throws SVGParseException {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            if (k2.a(attributes.getLocalName(i10)) == k2.f12356l) {
                n0 n0Var = new n0(attributes.getValue(i10));
                ArrayList arrayList = new ArrayList();
                n0Var.v();
                while (!n0Var.j()) {
                    float fN = n0Var.n();
                    if (Float.isNaN(fN)) {
                        throw new SVGParseException(a0.c.l("Invalid <", str, "> points attribute. Non-coordinate content found in list."));
                    }
                    n0Var.u();
                    float fN2 = n0Var.n();
                    if (Float.isNaN(fN2)) {
                        throw new SVGParseException(a0.c.l("Invalid <", str, "> points attribute. There should be an even number of coordinates."));
                    }
                    n0Var.u();
                    arrayList.add(Float.valueOf(fN));
                    arrayList.add(Float.valueOf(fN2));
                }
                q0Var.f12412o = new float[arrayList.size()];
                Iterator it = arrayList.iterator();
                int i11 = 0;
                while (it.hasNext()) {
                    q0Var.f12412o[i11] = ((Float) it.next()).floatValue();
                    i11++;
                }
            }
        }
    }

    public static void j(b1 b1Var, Attributes attributes) {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String strTrim = attributes.getValue(i10).trim();
            if (strTrim.length() != 0) {
                int iG = com.arflix.tv.data.repository.g.g(attributes, i10);
                if (iG == 0) {
                    c cVar = new c(strTrim);
                    ArrayList arrayList = null;
                    while (!cVar.j()) {
                        String strQ = cVar.q();
                        if (strQ != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(strQ);
                            cVar.v();
                        }
                    }
                    b1Var.f12265g = arrayList;
                } else if (iG != 72) {
                    if (b1Var.f12263e == null) {
                        b1Var.f12263e = new v0();
                    }
                    D(b1Var.f12263e, attributes.getLocalName(i10), attributes.getValue(i10).trim());
                } else {
                    n0 n0Var = new n0(strTrim.replaceAll("/\\*.*?\\*/", ""));
                    while (true) {
                        String strR = n0Var.r(':', false);
                        n0Var.v();
                        if (!n0Var.g(':')) {
                            break;
                        }
                        n0Var.v();
                        String strR2 = n0Var.r(';', true);
                        if (strR2 == null) {
                            break;
                        }
                        n0Var.v();
                        if (n0Var.j() || n0Var.g(';')) {
                            if (b1Var.f12264f == null) {
                                b1Var.f12264f = new v0();
                            }
                            D(b1Var.f12264f, strR, strR2);
                            n0Var.v();
                        }
                    }
                }
            }
        }
    }

    public static void k(q1 q1Var, Attributes attributes) {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String strTrim = attributes.getValue(i10).trim();
            int iG = com.arflix.tv.data.repository.g.g(attributes, i10);
            if (iG == 9) {
                q1Var.f12415p = t(strTrim);
            } else if (iG == 10) {
                q1Var.f12416q = t(strTrim);
            } else if (iG == 82) {
                q1Var.f12413n = t(strTrim);
            } else if (iG == 83) {
                q1Var.f12414o = t(strTrim);
            }
        }
    }

    public static void l(e0 e0Var, Attributes attributes) {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            if (k2.a(attributes.getLocalName(i10)) == k2.f12357m) {
                e0Var.k(z(attributes.getValue(i10)));
            }
        }
    }

    public static void m(h1 h1Var, Attributes attributes) throws SVGParseException {
        for (int i10 = 0; i10 < attributes.getLength(); i10++) {
            String strTrim = attributes.getValue(i10).trim();
            int iG = com.arflix.tv.data.repository.g.g(attributes, i10);
            if (iG == 48) {
                x(h1Var, strTrim);
            } else if (iG != 80) {
                continue;
            } else {
                n0 n0Var = new n0(strTrim);
                n0Var.v();
                float fN = n0Var.n();
                n0Var.u();
                float fN2 = n0Var.n();
                n0Var.u();
                float fN3 = n0Var.n();
                n0Var.u();
                float fN4 = n0Var.n();
                if (Float.isNaN(fN) || Float.isNaN(fN2) || Float.isNaN(fN3) || Float.isNaN(fN4)) {
                    throw new SVGParseException("Invalid viewBox definition - should have four numbers");
                }
                if (fN3 < 0.0f) {
                    throw new SVGParseException("Invalid viewBox. width cannot be negative");
                }
                if (fN4 < 0.0f) {
                    throw new SVGParseException("Invalid viewBox. height cannot be negative");
                }
                h1Var.f12335o = new u(fN, fN2, fN3, fN4);
            }
        }
    }

    public static x n(String str) throws SVGParseException {
        long j10;
        int i10;
        if (str.charAt(0) == '#') {
            int length = str.length();
            q qVar = null;
            if (1 < length) {
                long j11 = 0;
                int i11 = 1;
                while (i11 < length) {
                    char cCharAt = str.charAt(i11);
                    if (cCharAt < '0' || cCharAt > '9') {
                        if (cCharAt >= 'A' && cCharAt <= 'F') {
                            j10 = j11 * 16;
                            i10 = cCharAt - 'A';
                        } else {
                            if (cCharAt < 'a' || cCharAt > 'f') {
                                break;
                            }
                            j10 = j11 * 16;
                            i10 = cCharAt - 'a';
                        }
                        j11 = j10 + ((long) i10) + 10;
                    } else {
                        j11 = (j11 * 16) + ((long) (cCharAt - '0'));
                    }
                    if (j11 > 4294967295L) {
                        break;
                    }
                    i11++;
                }
                if (i11 != 1) {
                    qVar = new q(j11, i11);
                }
            }
            if (qVar == null) {
                throw new SVGParseException("Bad hex colour value: ".concat(str));
            }
            long j12 = qVar.f12411b;
            int i12 = qVar.f12410a;
            if (i12 == 4) {
                int i13 = (int) j12;
                int i14 = i13 & 3840;
                int i15 = i13 & PsExtractor.VIDEO_STREAM_MASK;
                int i16 = i13 & 15;
                return new x(i16 | (i14 << 8) | (-16777216) | (i14 << 12) | (i15 << 8) | (i15 << 4) | (i16 << 4));
            }
            if (i12 != 5) {
                if (i12 == 7) {
                    return new x(((int) j12) | (-16777216));
                }
                if (i12 != 9) {
                    throw new SVGParseException("Bad hex colour value: ".concat(str));
                }
                int i17 = (int) j12;
                return new x((i17 >>> 8) | (i17 << 24));
            }
            int i18 = (int) j12;
            int i19 = 61440 & i18;
            int i20 = i18 & 3840;
            int i21 = i18 & PsExtractor.VIDEO_STREAM_MASK;
            int i22 = i18 & 15;
            return new x((i22 << 24) | (i22 << 28) | (i19 << 8) | (i19 << 4) | (i20 << 4) | i20 | i21 | (i21 >> 4));
        }
        String lowerCase = str.toLowerCase(Locale.US);
        boolean zStartsWith = lowerCase.startsWith("rgba(");
        if (zStartsWith || lowerCase.startsWith("rgb(")) {
            n0 n0Var = new n0(str.substring(zStartsWith ? 5 : 4));
            n0Var.v();
            float fN = n0Var.n();
            if (!Float.isNaN(fN) && n0Var.g('%')) {
                fN = (fN * 256.0f) / 100.0f;
            }
            float f10 = n0Var.f(fN);
            if (!Float.isNaN(f10) && n0Var.g('%')) {
                f10 = (f10 * 256.0f) / 100.0f;
            }
            float f11 = n0Var.f(f10);
            if (!Float.isNaN(f11) && n0Var.g('%')) {
                f11 = (f11 * 256.0f) / 100.0f;
            }
            if (!zStartsWith) {
                n0Var.v();
                if (Float.isNaN(f11) || !n0Var.g(')')) {
                    throw new SVGParseException("Bad rgb() colour value: ".concat(str));
                }
                return new x((b(fN) << 16) | (-16777216) | (b(f10) << 8) | b(f11));
            }
            float f12 = n0Var.f(f11);
            n0Var.v();
            if (Float.isNaN(f12) || !n0Var.g(')')) {
                throw new SVGParseException("Bad rgba() colour value: ".concat(str));
            }
            return new x((b(f12 * 256.0f) << 24) | (b(fN) << 16) | (b(f10) << 8) | b(f11));
        }
        boolean zStartsWith2 = lowerCase.startsWith("hsla(");
        if (!zStartsWith2 && !lowerCase.startsWith("hsl(")) {
            Integer num = (Integer) g2.f12324a.get(lowerCase);
            if (num != null) {
                return new x(num.intValue());
            }
            throw new SVGParseException("Invalid colour keyword: ".concat(lowerCase));
        }
        n0 n0Var2 = new n0(str.substring(zStartsWith2 ? 5 : 4));
        n0Var2.v();
        float fN2 = n0Var2.n();
        float f13 = n0Var2.f(fN2);
        if (!Float.isNaN(f13)) {
            n0Var2.g('%');
        }
        float f14 = n0Var2.f(f13);
        if (!Float.isNaN(f14)) {
            n0Var2.g('%');
        }
        if (!zStartsWith2) {
            n0Var2.v();
            if (Float.isNaN(f14) || !n0Var2.g(')')) {
                throw new SVGParseException("Bad hsl() colour value: ".concat(str));
            }
            return new x(d(fN2, f13, f14) | (-16777216));
        }
        float f15 = n0Var2.f(f14);
        n0Var2.v();
        if (Float.isNaN(f15) || !n0Var2.g(')')) {
            throw new SVGParseException("Bad hsla() colour value: ".concat(str));
        }
        return new x((b(f15 * 256.0f) << 24) | d(fN2, f13, f14));
    }

    public static float o(int i10, String str) throws SVGParseException {
        float fA = new r().a(0, i10, str);
        if (Float.isNaN(fA)) {
            throw new SVGParseException(androidx.compose.material3.d.C("Invalid float value: ", str));
        }
        return fA;
    }

    public static float p(String str) throws SVGParseException {
        int length = str.length();
        if (length != 0) {
            return o(length, str);
        }
        throw new SVGParseException("Invalid float value (empty string)");
    }

    public static ArrayList q(String str) {
        n0 n0Var = new n0(str);
        ArrayList arrayList = null;
        do {
            String strP = n0Var.p();
            if (strP == null) {
                strP = n0Var.r(',', true);
            }
            if (strP == null) {
                return arrayList;
            }
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(strP);
            n0Var.u();
        } while (!n0Var.j());
        return arrayList;
    }

    public static String r(String str) {
        if (!str.equals("none") && str.startsWith("url(")) {
            return str.endsWith(")") ? str.substring(4, str.length() - 1).trim() : str.substring(4).trim();
        }
        return null;
    }

    public static g0 s(String str) throws SVGParseException {
        int iZ;
        if (str.length() == 0) {
            throw new SVGParseException("Invalid length value (empty string)");
        }
        int length = str.length();
        char cCharAt = str.charAt(length - 1);
        if (cCharAt == '%') {
            length--;
            iZ = 9;
        } else if (length > 2 && Character.isLetter(cCharAt) && Character.isLetter(str.charAt(length - 2))) {
            length -= 2;
            try {
                iZ = com.arflix.tv.data.repository.g.z(str.substring(length).toLowerCase(Locale.US));
            } catch (IllegalArgumentException unused) {
                throw new SVGParseException("Invalid length unit specifier: ".concat(str));
            }
        } else {
            iZ = 1;
        }
        try {
            return new g0(o(length, str), iZ);
        } catch (NumberFormatException e5) {
            throw new SVGParseException("Invalid length value: ".concat(str), e5);
        }
    }

    public static ArrayList t(String str) throws SVGParseException {
        if (str.length() == 0) {
            throw new SVGParseException("Invalid length list (empty string)");
        }
        ArrayList arrayList = new ArrayList(1);
        n0 n0Var = new n0(str);
        n0Var.v();
        while (!n0Var.j()) {
            float fN = n0Var.n();
            if (Float.isNaN(fN)) {
                StringBuilder sb2 = new StringBuilder("Invalid length list value: ");
                String str2 = (String) n0Var.f12381c;
                int i10 = n0Var.f12379a;
                while (!n0Var.j() && !n0.l(str2.charAt(n0Var.f12379a))) {
                    n0Var.f12379a++;
                }
                String strSubstring = str2.substring(i10, n0Var.f12379a);
                n0Var.f12379a = i10;
                sb2.append(strSubstring);
                throw new SVGParseException(sb2.toString());
            }
            int iS = n0Var.s();
            if (iS == 0) {
                iS = 1;
            }
            arrayList.add(new g0(fN, iS));
            n0Var.u();
        }
        return arrayList;
    }

    public static g0 u(n0 n0Var) {
        return n0Var.h(TtmlNode.TEXT_EMPHASIS_AUTO) ? new g0(0.0f) : n0Var.o();
    }

    public static Float v(String str) {
        try {
            float fP = p(str);
            float f10 = 0.0f;
            if (fP < 0.0f) {
                fP = f10;
            } else {
                f10 = 1.0f;
                if (fP > 1.0f) {
                    fP = f10;
                }
            }
            return Float.valueOf(fP);
        } catch (SVGParseException unused) {
            return null;
        }
    }

    public static e1 w(String str) {
        boolean zStartsWith = str.startsWith("url(");
        e1 e1VarN = x.f12486m;
        y yVar = y.f12493i;
        e1 e1Var = null;
        if (!zStartsWith) {
            if (str.equals("none")) {
                return e1VarN;
            }
            if (str.equals("currentColor")) {
                return yVar;
            }
            try {
                return n(str);
            } catch (SVGParseException unused) {
                return null;
            }
        }
        int iIndexOf = str.indexOf(")");
        if (iIndexOf == -1) {
            return new l0(str.substring(4).trim(), null);
        }
        String strTrim = str.substring(4, iIndexOf).trim();
        String strTrim2 = str.substring(iIndexOf + 1).trim();
        if (strTrim2.length() > 0) {
            if (!strTrim2.equals("none")) {
                if (strTrim2.equals("currentColor")) {
                    e1VarN = yVar;
                } else {
                    try {
                        e1VarN = n(strTrim2);
                    } catch (SVGParseException unused2) {
                        e1VarN = null;
                    }
                }
            }
            e1Var = e1VarN;
        }
        return new l0(strTrim, e1Var);
    }

    public static void x(f1 f1Var, String str) throws SVGParseException {
        int i10;
        n0 n0Var = new n0(str);
        n0Var.v();
        String strQ = n0Var.q();
        if ("defer".equals(strQ)) {
            n0Var.v();
            strQ = n0Var.q();
        }
        s sVar = (s) f2.f12315a.get(strQ);
        n0Var.v();
        if (n0Var.j()) {
            i10 = 0;
        } else {
            String strQ2 = n0Var.q();
            strQ2.getClass();
            if (strQ2.equals("meet")) {
                i10 = 1;
            } else {
                if (!strQ2.equals("slice")) {
                    throw new SVGParseException("Invalid preserveAspectRatio definition: ".concat(str));
                }
                i10 = 2;
            }
        }
        f1Var.f12314n = new t(sVar, i10);
    }

    public static HashMap y(n0 n0Var) {
        HashMap map = new HashMap();
        n0Var.v();
        String strR = n0Var.r('=', false);
        while (strR != null) {
            n0Var.g('=');
            map.put(strR, n0Var.p());
            n0Var.v();
            strR = n0Var.r('=', false);
        }
        return map;
    }

    public static Matrix z(String str) throws SVGParseException {
        Matrix matrix = new Matrix();
        n0 n0Var = new n0(str);
        n0Var.v();
        while (!n0Var.j()) {
            String str2 = (String) n0Var.f12381c;
            String strSubstring = null;
            if (!n0Var.j()) {
                int i10 = n0Var.f12379a;
                int iCharAt = str2.charAt(i10);
                while (true) {
                    if ((iCharAt >= 97 && iCharAt <= 122) || (iCharAt >= 65 && iCharAt <= 90)) {
                        iCharAt = n0Var.d();
                    }
                }
                int i11 = n0Var.f12379a;
                while (n0.l(iCharAt)) {
                    iCharAt = n0Var.d();
                }
                if (iCharAt == 40) {
                    n0Var.f12379a++;
                    strSubstring = str2.substring(i10, i11);
                } else {
                    n0Var.f12379a = i10;
                }
            }
            if (strSubstring == null) {
                throw new SVGParseException("Bad transform function encountered in transform list: ".concat(str));
            }
            switch (strSubstring) {
                case "matrix":
                    n0Var.v();
                    float fN = n0Var.n();
                    n0Var.u();
                    float fN2 = n0Var.n();
                    n0Var.u();
                    float fN3 = n0Var.n();
                    n0Var.u();
                    float fN4 = n0Var.n();
                    n0Var.u();
                    float fN5 = n0Var.n();
                    n0Var.u();
                    float fN6 = n0Var.n();
                    n0Var.v();
                    if (Float.isNaN(fN6) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    Matrix matrix2 = new Matrix();
                    matrix2.setValues(new float[]{fN, fN3, fN5, fN2, fN4, fN6, 0.0f, 0.0f, 1.0f});
                    matrix.preConcat(matrix2);
                    break;
                    break;
                case "rotate":
                    n0Var.v();
                    float fN7 = n0Var.n();
                    float fT = n0Var.t();
                    float fT2 = n0Var.t();
                    n0Var.v();
                    if (Float.isNaN(fN7) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    if (Float.isNaN(fT)) {
                        matrix.preRotate(fN7);
                    } else {
                        if (Float.isNaN(fT2)) {
                            throw new SVGParseException("Invalid transform list: ".concat(str));
                        }
                        matrix.preRotate(fN7, fT, fT2);
                    }
                    break;
                    break;
                case "scale":
                    n0Var.v();
                    float fN8 = n0Var.n();
                    float fT3 = n0Var.t();
                    n0Var.v();
                    if (Float.isNaN(fN8) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    if (!Float.isNaN(fT3)) {
                        matrix.preScale(fN8, fT3);
                    } else {
                        matrix.preScale(fN8, fN8);
                    }
                    break;
                    break;
                case "skewX":
                    n0Var.v();
                    float fN9 = n0Var.n();
                    n0Var.v();
                    if (Float.isNaN(fN9) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    matrix.preSkew((float) Math.tan(Math.toRadians(fN9)), 0.0f);
                    break;
                    break;
                case "skewY":
                    n0Var.v();
                    float fN10 = n0Var.n();
                    n0Var.v();
                    if (Float.isNaN(fN10) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    matrix.preSkew(0.0f, (float) Math.tan(Math.toRadians(fN10)));
                    break;
                    break;
                case "translate":
                    n0Var.v();
                    float fN11 = n0Var.n();
                    float fT4 = n0Var.t();
                    n0Var.v();
                    if (Float.isNaN(fN11) || !n0Var.g(')')) {
                        throw new SVGParseException("Invalid transform list: ".concat(str));
                    }
                    if (!Float.isNaN(fT4)) {
                        matrix.preTranslate(fN11, fT4);
                    } else {
                        matrix.preTranslate(fN11, 0.0f);
                    }
                    break;
                    break;
                default:
                    throw new SVGParseException(a0.c.l("Invalid transform list fn: ", strSubstring, ")"));
            }
            if (n0Var.j()) {
                return matrix;
            }
            n0Var.u();
        }
        return matrix;
    }

    public final void A(InputStream inputStream) throws SVGParseException {
        Log.d("SVGParser", "Falling back to SAX parser");
        try {
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            XMLReader xMLReader = sAXParserFactoryNewInstance.newSAXParser().getXMLReader();
            j2 j2Var = new j2(this);
            xMLReader.setContentHandler(j2Var);
            xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", j2Var);
            xMLReader.parse(new InputSource(inputStream));
        } catch (IOException e5) {
            throw new SVGParseException("Stream error", e5);
        } catch (ParserConfigurationException e6) {
            throw new SVGParseException("XML parser problem", e6);
        } catch (SAXException e10) {
            throw new SVGParseException("SVG parse error", e10);
        }
    }

    public final void B(InputStream inputStream) throws SVGParseException {
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                m2 m2Var = new m2();
                m2Var.f12376a = xmlPullParserNewPullParser;
                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                xmlPullParserNewPullParser.setInput(inputStream, null);
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.nextToken()) {
                    if (eventType == 0) {
                        E();
                    } else if (eventType == 8) {
                        Log.d("SVGParser", "PROC INSTR: " + xmlPullParserNewPullParser.getText());
                        n0 n0Var = new n0(xmlPullParserNewPullParser.getText());
                        String strQ = n0Var.q();
                        y(n0Var);
                        strQ.equals("xml-stylesheet");
                    } else if (eventType == 10) {
                        if (this.f12383a.f12469a == null && xmlPullParserNewPullParser.getText().contains("<!ENTITY ")) {
                            try {
                                Log.d("SVGParser", "Switching to SAX parser to process entities");
                                inputStream.reset();
                                A(inputStream);
                                return;
                            } catch (IOException unused) {
                                Log.w("SVGParser", "Detected internal entity definitions, but could not parse them.");
                                return;
                            }
                        }
                    } else if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (xmlPullParserNewPullParser.getPrefix() != null) {
                            name = xmlPullParserNewPullParser.getPrefix() + ':' + name;
                        }
                        F(xmlPullParserNewPullParser.getNamespace(), xmlPullParserNewPullParser.getName(), name, m2Var);
                    } else if (eventType == 3) {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (xmlPullParserNewPullParser.getPrefix() != null) {
                            name2 = xmlPullParserNewPullParser.getPrefix() + ':' + name2;
                        }
                        c(xmlPullParserNewPullParser.getNamespace(), xmlPullParserNewPullParser.getName(), name2);
                    } else if (eventType == 4) {
                        int[] iArr = new int[2];
                        H(xmlPullParserNewPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                    } else if (eventType == 5) {
                        G(xmlPullParserNewPullParser.getText());
                    }
                }
            } catch (XmlPullParserException e5) {
                throw new SVGParseException("XML parser problem", e5);
            }
        } catch (IOException e6) {
            throw new SVGParseException("Stream error", e6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x0343, code lost:
    
        android.util.Log.e("SVGParser", "Bad path coords for " + ((char) r5) + " path segment");
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0356 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v4, types: [byte[], java.io.Serializable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(org.xml.sax.Attributes r25) throws com.caverock.androidsvg.SVGParseException {
        /*
            Method dump skipped, instruction units count: 960
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.n2.C(org.xml.sax.Attributes):void");
    }

    public final void E() {
        v1 v1Var = new v1();
        v1Var.f12469a = null;
        v1Var.f12470b = new m();
        v1Var.f12471c = new HashMap();
        this.f12383a = v1Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:627:0x00ff, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:658:0x047b, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:685:0x0633, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:699:0x06ef, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:734:0x0913, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:762:0x0b43, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(java.lang.String r17, java.lang.String r18, java.lang.String r19, org.xml.sax.Attributes r20) throws com.caverock.androidsvg.SVGParseException {
        /*
            Method dump skipped, instruction units count: 3080
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.n2.F(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes):void");
    }

    public final void G(String str) {
        if (this.f12385c) {
            return;
        }
        if (this.f12387e) {
            if (this.f12389g == null) {
                this.f12389g = new StringBuilder(str.length());
            }
            this.f12389g.append(str);
        } else if (this.f12390h) {
            if (this.f12391i == null) {
                this.f12391i = new StringBuilder(str.length());
            }
            this.f12391i.append(str);
        } else if (this.f12384b instanceof o1) {
            a(str);
        }
    }

    public final void H(char[] cArr, int i10, int i11) {
        if (this.f12385c) {
            return;
        }
        if (this.f12387e) {
            if (this.f12389g == null) {
                this.f12389g = new StringBuilder(i11);
            }
            this.f12389g.append(cArr, i10, i11);
        } else if (this.f12390h) {
            if (this.f12391i == null) {
                this.f12391i = new StringBuilder(i11);
            }
            this.f12391i.append(cArr, i10, i11);
        } else if (this.f12384b instanceof o1) {
            a(new String(cArr, i10, i11));
        }
    }

    public final void a(String str) {
        y0 y0Var = (y0) this.f12384b;
        int size = y0Var.f12494i.size();
        d1 d1Var = size == 0 ? null : (d1) y0Var.f12494i.get(size - 1);
        if (d1Var instanceof s1) {
            s1 s1Var = (s1) d1Var;
            s1Var.f12437c = a0.c.p(new StringBuilder(), s1Var.f12437c, str);
        } else {
            z0 z0Var = this.f12384b;
            s1 s1Var2 = new s1();
            s1Var2.f12437c = str;
            z0Var.g(s1Var2);
        }
    }

    public final void c(String str, String str2, String str3) {
        if (this.f12385c) {
            int i10 = this.f12386d - 1;
            this.f12386d = i10;
            if (i10 == 0) {
                this.f12385c = false;
            }
        }
        if (Parser.NamespaceSvg.equals(str) || "".equals(str)) {
            if (str2.length() <= 0) {
                str2 = str3;
            }
            l2 l2Var = (l2) l2.f12371o.get(str2);
            if (l2Var == null) {
                l2Var = l2.f12370n;
            }
            switch (l2Var.ordinal()) {
                case 0:
                case 3:
                case 4:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 14:
                case 17:
                case 19:
                case 20:
                case 22:
                case 23:
                case 24:
                case 25:
                case 28:
                case 29:
                case 30:
                    this.f12384b = ((d1) this.f12384b).f12293b;
                    break;
                case 5:
                case 26:
                    this.f12387e = false;
                    if (this.f12389g != null) {
                        l2 l2Var2 = this.f12388f;
                        if (l2Var2 == l2.f12369m || l2Var2 == l2.f12367i) {
                            this.f12383a.getClass();
                        }
                        this.f12389g.setLength(0);
                    }
                    break;
                case 21:
                    StringBuilder sb2 = this.f12391i;
                    if (sb2 != null) {
                        this.f12390h = false;
                        String string = sb2.toString();
                        p pVar = new p(1);
                        v1 v1Var = this.f12383a;
                        c cVar = new c(string);
                        cVar.v();
                        v1Var.f12470b.b(pVar.e(cVar));
                        this.f12391i.setLength(0);
                    }
                    break;
            }
        }
    }
}
