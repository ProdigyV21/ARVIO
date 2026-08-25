package androidx.emoji2.text;

import j$.util.stream.IntStream;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k0 {
    public static IntStream a(CharSequence charSequence) {
        return IntStream.VivifiedWrapper.convert(charSequence.chars());
    }

    public static IntStream b(CharSequence charSequence) {
        return IntStream.VivifiedWrapper.convert(charSequence.codePoints());
    }
}
