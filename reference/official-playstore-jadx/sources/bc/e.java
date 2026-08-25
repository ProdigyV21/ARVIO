package bc;

import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends a8.e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f7356m;

    public e(OutputStream outputStream, int i10) {
        super(outputStream, 7);
        this.f7356m = i10;
    }

    @Override // a8.e
    public final boolean R(ArrayList arrayList) {
        return false;
    }

    @Override // a8.e
    public final boolean f0(int i10, ArrayList arrayList) throws IOException {
        boolean z;
        if (i10 != 109) {
            return false;
        }
        boolean z5 = true;
        int i11 = this.f7356m;
        if (i11 != 2 && i11 != 1) {
            return false;
        }
        Iterator it = arrayList.iterator();
        boolean z10 = false;
        while (true) {
            int i12 = 48;
            if (!it.hasNext()) {
                if (!z10) {
                    return false;
                }
                StringBuilder sb2 = new StringBuilder(32);
                sb2.append("\u001b[");
                Iterator it2 = arrayList.iterator();
                boolean z11 = true;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (next != null) {
                        int iIntValue = ((Integer) next).intValue();
                        if (iIntValue == 38 || iIntValue == i12) {
                            int iJ = a8.e.J(it2);
                            if (iJ == 2) {
                                int iJ2 = a8.e.J(it2);
                                z = z5;
                                int iJ3 = a8.e.J(it2);
                                int iJ4 = a8.e.J(it2);
                                if (i11 == 2) {
                                    int iE = d.e(iJ2, iJ3, iJ4, 256);
                                    if (!z11) {
                                        sb2.append(';');
                                    }
                                    sb2.append(iIntValue);
                                    sb2.append(";5;");
                                    sb2.append(iE);
                                } else {
                                    int iE2 = d.e(iJ2, iJ3, iJ4, 16);
                                    if (!z11) {
                                        sb2.append(';');
                                    }
                                    sb2.append(iIntValue == 38 ? iE2 >= 8 ? iE2 + 82 : iE2 + 30 : iE2 >= 8 ? iE2 + 92 : iE2 + 40);
                                }
                            } else {
                                z = z5;
                                if (iJ != 5) {
                                    throw new IllegalArgumentException();
                                }
                                int iJ5 = a8.e.J(it2);
                                if (i11 == 2) {
                                    if (!z11) {
                                        sb2.append(';');
                                    }
                                    sb2.append(iIntValue);
                                    sb2.append(";5;");
                                    sb2.append(iJ5);
                                } else {
                                    if (iJ5 >= 16) {
                                        int[] iArr = d.f7355a;
                                        iJ5 = d.d(iArr[iJ5], 16, iArr);
                                    }
                                    if (!z11) {
                                        sb2.append(';');
                                    }
                                    sb2.append(iIntValue == 38 ? iJ5 >= 8 ? iJ5 + 82 : iJ5 + 30 : iJ5 >= 8 ? iJ5 + 92 : iJ5 + 40);
                                }
                            }
                            z11 = false;
                            z5 = z;
                            i12 = 48;
                        } else {
                            if (!z11) {
                                sb2.append(';');
                            }
                            sb2.append(iIntValue);
                            z = z5;
                            z11 = false;
                        }
                    } else {
                        z = z5;
                    }
                    z5 = z;
                    i12 = 48;
                }
                boolean z12 = z5;
                sb2.append(GMTDateParser.MINUTES);
                ((OutputStream) this.f183l).write(sb2.toString().getBytes());
                return z12;
            }
            Object next2 = it.next();
            if (next2 != null && next2.getClass() != Integer.class) {
                throw new IllegalArgumentException();
            }
            Integer num = (Integer) next2;
            z10 |= num.intValue() == 38 || num.intValue() == 48;
        }
    }

    @Override // a8.e
    public final boolean h0(ArrayList arrayList) {
        return false;
    }
}
