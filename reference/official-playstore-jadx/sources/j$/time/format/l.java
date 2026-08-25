package j$.time.format;

/* JADX INFO: loaded from: classes2.dex */
public final class l extends m {
    @Override // j$.time.format.m
    public final m d(String str, String str2, m mVar) {
        return new l(str, str2, mVar);
    }

    @Override // j$.time.format.m
    public final boolean b(char c10, char c11) {
        return u.b(c10, c11);
    }

    @Override // j$.time.format.m
    public final boolean e(CharSequence charSequence, int i10, int i11) {
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
            if (!u.b(this.f18262a.charAt(i12), charSequence.charAt(i10))) {
                return false;
            }
            i10 = i15;
            length = i13;
            i12 = i14;
        }
    }
}
