package j$.time.format;

import java.text.ParsePosition;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f18262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f18263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char f18264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f18265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m f18266e;

    public boolean b(char c10, char c11) {
        return c10 == c11;
    }

    public m(String str, String str2, m mVar) {
        this.f18262a = str;
        this.f18263b = str2;
        this.f18265d = mVar;
        if (str.isEmpty()) {
            this.f18264c = (char) 65535;
        } else {
            this.f18264c = this.f18262a.charAt(0);
        }
    }

    public final String c(CharSequence charSequence, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        int length = charSequence.length();
        if (!e(charSequence, index, length)) {
            return null;
        }
        int length2 = this.f18262a.length() + index;
        m mVar = this.f18265d;
        if (mVar != null && length2 != length) {
            while (true) {
                if (b(mVar.f18264c, charSequence.charAt(length2))) {
                    parsePosition.setIndex(length2);
                    String strC = mVar.c(charSequence, parsePosition);
                    if (strC != null) {
                        return strC;
                    }
                } else {
                    mVar = mVar.f18266e;
                    if (mVar == null) {
                        break;
                    }
                }
            }
        }
        parsePosition.setIndex(length2);
        return this.f18263b;
    }

    public m d(String str, String str2, m mVar) {
        return new m(str, str2, mVar);
    }

    public boolean e(CharSequence charSequence, int i10, int i11) {
        if (charSequence instanceof String) {
            return ((String) charSequence).startsWith(this.f18262a, i10);
        }
        int length = this.f18262a.length();
        if (length > i11 - i10) {
            return false;
        }
        int i12 = 0;
        while (true) {
            int i13 = length - 1;
            if (length <= 0) {
                return true;
            }
            int i14 = i12 + 1;
            int i15 = i10 + 1;
            if (!b(this.f18262a.charAt(i12), charSequence.charAt(i10))) {
                return false;
            }
            i10 = i15;
            length = i13;
            i12 = i14;
        }
    }

    public final boolean a(String str, String str2) {
        int i10 = 0;
        while (i10 < str.length() && i10 < this.f18262a.length() && b(str.charAt(i10), this.f18262a.charAt(i10))) {
            i10++;
        }
        if (i10 == this.f18262a.length()) {
            if (i10 < str.length()) {
                String strSubstring = str.substring(i10);
                for (m mVar = this.f18265d; mVar != null; mVar = mVar.f18266e) {
                    if (b(mVar.f18264c, strSubstring.charAt(0))) {
                        return mVar.a(strSubstring, str2);
                    }
                }
                m mVarD = d(strSubstring, str2, null);
                mVarD.f18266e = this.f18265d;
                this.f18265d = mVarD;
                return true;
            }
            this.f18263b = str2;
            return true;
        }
        m mVarD2 = d(this.f18262a.substring(i10), this.f18263b, this.f18265d);
        this.f18262a = str.substring(0, i10);
        this.f18265d = mVarD2;
        if (i10 < str.length()) {
            this.f18265d.f18266e = d(str.substring(i10), str2, null);
            this.f18263b = null;
            return true;
        }
        this.f18263b = str2;
        return true;
    }
}
