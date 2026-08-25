package io.ktor.server.util;

import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0004\u001a\u001d\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000*\b\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a'\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\u000b\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\t\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0013\u0010\u000e\u001a\u00020\r*\u00020\u0001H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0012\u001a\u00020\u0011*\u00020\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001c\u0010\u0016\u001a\u00020\r*\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0002¢\u0006\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019\"\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c\"\u0014\u0010\u001d\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0019¨\u0006\u001e"}, d2 = {"", "", "normalizePathComponents", "(Ljava/util/List;)Ljava/util/List;", "", "startIndex", "filterComponentsImpl", "(Ljava/util/List;I)Ljava/util/List;", "", "component", "Lx6/t0;", "processAndReplaceComponent", "(Ljava/util/List;Ljava/lang/String;)V", "", "shouldBeReplaced", "(Ljava/lang/String;)Z", "", "", "toASCIITable", "([C)[Z", "", "char", "contains", "([ZC)Z", "FirstReservedLetters", "[Z", "", "ReservedWords", "Ljava/util/Set;", "ReservedCharacters", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PathsKt {
    private static final boolean[] FirstReservedLetters = toASCIITable(new char[]{'A', 'a', 'C', 'c', 'l', 'L', 'P', 'p', 'n', 'N'});
    private static final Set<String> ReservedWords = r.p0(new String[]{"CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"});
    private static final boolean[] ReservedCharacters = toASCIITable(new char[]{'\\', '/', ':', GMTDateParser.ANY, '?', '\"', '<', '>', '|'});

    private static final boolean contains(boolean[] zArr, char c10) {
        return c10 < zArr.length && zArr[c10];
    }

    private static final List<String> filterComponentsImpl(List<String> list, int i10) throws IOException {
        ArrayList arrayList = new ArrayList(list.size());
        if (i10 > 0) {
            arrayList.addAll(list.subList(0, i10));
        }
        processAndReplaceComponent(arrayList, list.get(i10));
        int size = list.size();
        for (int i11 = i10 + 1; i11 < size; i11++) {
            String str = list.get(i11);
            if (shouldBeReplaced(str)) {
                processAndReplaceComponent(arrayList, str);
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static final List<String> normalizePathComponents(List<String> list) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (shouldBeReplaced(list.get(i10))) {
                return filterComponentsImpl(list, i10);
            }
        }
        return list;
    }

    private static final void processAndReplaceComponent(List<String> list, String str) throws IOException {
        CharSequence charSequenceSubSequence;
        if (str.length() == 0 || p.a(str, ".") || p.a(str, "~") || ReservedWords.contains(TextKt.toUpperCasePreservingASCIIRules(str))) {
            return;
        }
        if (p.a(str, "..")) {
            if (list.isEmpty()) {
                return;
            }
            list.remove(a.w(list));
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (p.c(cCharAt, 32) >= 0 && !contains(ReservedCharacters, cCharAt)) {
                sb2.append(cCharAt);
            }
        }
        String string = sb2.toString();
        int length2 = string.length() - 1;
        if (length2 >= 0) {
            while (true) {
                int i11 = length2 - 1;
                char cCharAt2 = string.charAt(length2);
                if (cCharAt2 != ' ' && cCharAt2 != '.') {
                    charSequenceSubSequence = string.subSequence(0, length2 + 1);
                    break;
                } else if (i11 < 0) {
                    break;
                } else {
                    length2 = i11;
                }
            }
            charSequenceSubSequence = "";
        } else {
            charSequenceSubSequence = "";
        }
        String string2 = charSequenceSubSequence.toString();
        if (string2.length() <= 0) {
            string2 = null;
        }
        if (string2 != null) {
            list.add(string2);
        }
    }

    private static final boolean shouldBeReplaced(String str) {
        int length = str.length();
        if (length == 0) {
            return true;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == '.' && (length == 1 || (length == 2 && str.charAt(1) == '.'))) {
            return true;
        }
        if (cCharAt == '~' && length == 1) {
            return true;
        }
        if (contains(FirstReservedLetters, cCharAt)) {
            Set<String> set = ReservedWords;
            if (set.contains(str) || set.contains(TextKt.toUpperCasePreservingASCIIRules(str))) {
                return true;
            }
        }
        char cCharAt2 = str.charAt(length - 1);
        if (cCharAt2 == ' ' || cCharAt2 == '.') {
            return true;
        }
        boolean[] zArr = ReservedCharacters;
        for (int i10 = 0; i10 < str.length(); i10++) {
            char cCharAt3 = str.charAt(i10);
            if (p.c(cCharAt3, 32) < 0 || contains(zArr, cCharAt3)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean[] toASCIITable(char[] cArr) {
        boolean[] zArr = new boolean[256];
        for (int i10 = 0; i10 < 256; i10++) {
            zArr[i10] = r.H(cArr, (char) i10);
        }
        return zArr;
    }
}
