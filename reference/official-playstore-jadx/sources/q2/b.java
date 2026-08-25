package q2;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f21325a = new b();

    public final String a(int i10, String str, String str2) {
        String str3;
        if (i10 == 0 && str == null) {
            return str2;
        }
        StringBuilder sb2 = new StringBuilder();
        if (i10 != 0) {
            switch (i10) {
                case 1:
                    str3 = "Verbose";
                    break;
                case 2:
                    str3 = "Debug";
                    break;
                case 3:
                    str3 = "Info";
                    break;
                case 4:
                    str3 = "Warn";
                    break;
                case 5:
                    str3 = "Error";
                    break;
                case 6:
                    str3 = "Assert";
                    break;
                default:
                    str3 = "null";
                    break;
            }
            sb2.append(str3.concat(":"));
            sb2.append(" ");
        }
        if (str != null && str.length() > 0) {
            sb2.append("(" + str + ')');
            sb2.append(" ");
        }
        sb2.append(str2);
        return sb2.toString();
    }
}
