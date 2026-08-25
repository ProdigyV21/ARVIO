package org.jsoup.nodes;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

/* JADX INFO: loaded from: classes5.dex */
public class Entities {
    private static Document.OutputSettings DefaultOutput = null;
    static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final String emptyName = "";
    private static final char[] codeDelims = {',', ';'};
    private static final HashMap<String, String> multipoints = new HashMap<>();

    /* JADX INFO: renamed from: org.jsoup.nodes.Entities$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$nodes$Entities$CoreCharset;

        static {
            int[] iArr = new int[CoreCharset.values().length];
            $SwitchMap$org$jsoup$nodes$Entities$CoreCharset = iArr;
            try {
                iArr[CoreCharset.ascii.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$nodes$Entities$CoreCharset[CoreCharset.utf.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum CoreCharset {
        ascii,
        utf,
        fallback;

        public static CoreCharset byName(String str) {
            return str.equals("US-ASCII") ? ascii : str.startsWith("UTF-") ? utf : fallback;
        }
    }

    public enum EscapeMode {
        xhtml(EntitiesData.xmlPoints, 4),
        base(EntitiesData.basePoints, 106),
        extended(EntitiesData.fullPoints, 2125);

        private int[] codeKeys;
        private int[] codeVals;
        private String[] nameKeys;
        private String[] nameVals;

        EscapeMode(String str, int i10) {
            Entities.load(this, str, i10);
        }

        private int size() {
            return this.nameKeys.length;
        }

        public int codepointForName(String str) {
            int iBinarySearch = Arrays.binarySearch(this.nameKeys, str);
            if (iBinarySearch >= 0) {
                return this.codeVals[iBinarySearch];
            }
            return -1;
        }

        public String nameForCodepoint(int i10) {
            int iBinarySearch = Arrays.binarySearch(this.codeKeys, i10);
            if (iBinarySearch < 0) {
                return "";
            }
            String[] strArr = this.nameVals;
            if (iBinarySearch < strArr.length - 1) {
                int i11 = iBinarySearch + 1;
                if (this.codeKeys[i11] == i10) {
                    return strArr[i11];
                }
            }
            return strArr[iBinarySearch];
        }
    }

    private Entities() {
    }

    private static void appendEncoded(Appendable appendable, EscapeMode escapeMode, int i10) throws IOException {
        String strNameForCodepoint = escapeMode.nameForCodepoint(i10);
        if ("".equals(strNameForCodepoint)) {
            appendable.append("&#x").append(Integer.toHexString(i10)).append(';');
        } else {
            appendable.append('&').append(strNameForCodepoint).append(';');
        }
    }

    private static boolean canEncode(CoreCharset coreCharset, char c10, CharsetEncoder charsetEncoder) {
        int i10 = AnonymousClass1.$SwitchMap$org$jsoup$nodes$Entities$CoreCharset[coreCharset.ordinal()];
        if (i10 == 1) {
            return c10 < 128;
        }
        if (i10 != 2) {
            return charsetEncoder.canEncode(c10);
        }
        return true;
    }

    public static int codepointsForName(String str, int[] iArr) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            iArr[0] = str2.codePointAt(0);
            iArr[1] = str2.codePointAt(1);
            return 2;
        }
        int iCodepointForName = EscapeMode.extended.codepointForName(str);
        if (iCodepointForName == -1) {
            return 0;
        }
        iArr[0] = iCodepointForName;
        return 1;
    }

    public static String escape(String str, Document.OutputSettings outputSettings) {
        if (str == null) {
            return "";
        }
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        try {
            escape(sbBorrowBuilder, str, outputSettings, false, false, false, false);
            return StringUtil.releaseBuilder(sbBorrowBuilder);
        } catch (IOException e5) {
            throw new SerializationException(e5);
        }
    }

    public static String getByName(String str) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            return str2;
        }
        int iCodepointForName = EscapeMode.extended.codepointForName(str);
        return iCodepointForName != -1 ? new String(new int[]{iCodepointForName}, 0, 1) : "";
    }

    public static boolean isBaseNamedEntity(String str) {
        return EscapeMode.base.codepointForName(str) != -1;
    }

    public static boolean isNamedEntity(String str) {
        return EscapeMode.extended.codepointForName(str) != -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void load(EscapeMode escapeMode, String str, int i10) {
        int i11;
        escapeMode.nameKeys = new String[i10];
        escapeMode.codeVals = new int[i10];
        escapeMode.codeKeys = new int[i10];
        escapeMode.nameVals = new String[i10];
        CharacterReader characterReader = new CharacterReader(str);
        int i12 = 0;
        while (!characterReader.isEmpty()) {
            try {
                String strConsumeTo = characterReader.consumeTo('=');
                characterReader.advance();
                int i13 = Integer.parseInt(characterReader.consumeToAny(codeDelims), 36);
                char cCurrent = characterReader.current();
                characterReader.advance();
                if (cCurrent == ',') {
                    i11 = Integer.parseInt(characterReader.consumeTo(';'), 36);
                    characterReader.advance();
                } else {
                    i11 = -1;
                }
                int i14 = Integer.parseInt(characterReader.consumeTo('&'), 36);
                characterReader.advance();
                escapeMode.nameKeys[i12] = strConsumeTo;
                escapeMode.codeVals[i12] = i13;
                escapeMode.codeKeys[i14] = i13;
                escapeMode.nameVals[i14] = strConsumeTo;
                if (i11 != -1) {
                    multipoints.put(strConsumeTo, new String(new int[]{i13, i11}, 0, 2));
                }
                i12++;
            } catch (Throwable th) {
                characterReader.close();
                throw th;
            }
        }
        Validate.isTrue(i12 == i10, "Unexpected count of entities loaded");
        characterReader.close();
    }

    public static String unescape(String str) {
        return unescape(str, false);
    }

    public static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    public static String escape(String str) {
        if (DefaultOutput == null) {
            DefaultOutput = new Document.OutputSettings();
        }
        return escape(str, DefaultOutput);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void escape(java.lang.Appendable r16, java.lang.String r17, org.jsoup.nodes.Document.OutputSettings r18, boolean r19, boolean r20, boolean r21, boolean r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Entities.escape(java.lang.Appendable, java.lang.String, org.jsoup.nodes.Document$OutputSettings, boolean, boolean, boolean, boolean):void");
    }
}
