package k9;

import g8.b0;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends o {
    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        d8.k kVarJ = b0Var.j();
        kVarJ.getClass();
        v9.b0 b0VarS = kVarJ.s(d8.m.CHAR);
        if (b0VarS != null) {
            return b0VarS;
        }
        d8.k.a(63);
        throw null;
    }

    @Override // k9.g
    public final String toString() {
        String strValueOf;
        Object obj = this.f19530a;
        Integer numValueOf = Integer.valueOf(((Character) obj).charValue());
        char cCharValue = ((Character) obj).charValue();
        if (cCharValue == '\b') {
            strValueOf = "\\b";
        } else if (cCharValue == '\t') {
            strValueOf = "\\t";
        } else if (cCharValue == '\n') {
            strValueOf = "\\n";
        } else if (cCharValue == '\f') {
            strValueOf = "\\f";
        } else if (cCharValue == '\r') {
            strValueOf = "\\r";
        } else {
            byte type = (byte) Character.getType(cCharValue);
            strValueOf = (type == 0 || type == 13 || type == 14 || type == 15 || type == 16 || type == 18 || type == 19) ? "?" : String.valueOf(cCharValue);
        }
        return String.format("\\u%04X ('%s')", Arrays.copyOf(new Object[]{numValueOf, strValueOf}, 2));
    }
}
