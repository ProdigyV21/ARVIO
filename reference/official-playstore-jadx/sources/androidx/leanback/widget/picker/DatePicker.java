package androidx.leanback.widget.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.view.b2;
import androidx.leanback.widget.u;
import androidx.work.impl.t;
import io.ktor.util.date.GMTDateParser;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import k1.a;
import m1.e;
import m1.f;

/* JADX INFO: loaded from: classes3.dex */
public class DatePicker extends e {
    public static final int[] M = {5, 2, 1};
    public f A;
    public f B;
    public f C;
    public int D;
    public int E;
    public int F;
    public final SimpleDateFormat G;
    public final fi.iki.elonen.f H;
    public final Calendar I;
    public final Calendar J;
    public final Calendar K;
    public final Calendar L;
    public String z;

    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Locale locale = Locale.getDefault();
        getContext().getResources();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f();
        fVar.f15416i = locale;
        fVar.f15417l = DateFormatSymbols.getInstance(locale).getShortMonths();
        Calendar calendar = Calendar.getInstance(locale);
        int minimum = calendar.getMinimum(5);
        int maximum = calendar.getMaximum(5);
        String[] strArr = new String[(maximum - minimum) + 1];
        for (int i10 = minimum; i10 <= maximum; i10++) {
            strArr[i10 - minimum] = String.format("%02d", Integer.valueOf(i10));
        }
        this.H = fVar;
        this.L = t.r(this.L, locale);
        this.I = t.r(this.I, (Locale) this.H.f15416i);
        this.J = t.r(this.J, (Locale) this.H.f15416i);
        this.K = t.r(this.K, (Locale) this.H.f15416i);
        f fVar2 = this.A;
        if (fVar2 != null) {
            fVar2.f20169d = (String[]) this.H.f15417l;
            a(this.D, fVar2);
        }
        int[] iArr = a.f19454d;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0);
        try {
            String string = typedArrayObtainStyledAttributes.getString(0);
            String string2 = typedArrayObtainStyledAttributes.getString(1);
            String string3 = typedArrayObtainStyledAttributes.getString(2);
            typedArrayObtainStyledAttributes.recycle();
            this.L.clear();
            if (TextUtils.isEmpty(string) || !g(string, this.L)) {
                this.L.set(1900, 0, 1);
            }
            this.I.setTimeInMillis(this.L.getTimeInMillis());
            this.L.clear();
            if (TextUtils.isEmpty(string2) || !g(string2, this.L)) {
                this.L.set(2100, 0, 1);
            }
            this.J.setTimeInMillis(this.L.getTimeInMillis());
            setDatePickerFormat(TextUtils.isEmpty(string3) ? new String(DateFormat.getDateFormatOrder(context)) : string3);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final boolean g(String str, Calendar calendar) {
        try {
            calendar.setTime(this.G.parse(str));
            return true;
        } catch (ParseException unused) {
            Log.w("DatePicker", "Date: " + str + " not in format: MM/dd/yyyy");
            return false;
        }
    }

    public long getDate() {
        return this.K.getTimeInMillis();
    }

    public String getDatePickerFormat() {
        return this.z;
    }

    public long getMaxDate() {
        return this.J.getTimeInMillis();
    }

    public long getMinDate() {
        return this.I.getTimeInMillis();
    }

    public final void h(int i10, int i11, int i12) {
        if (this.K.get(1) == i10 && this.K.get(2) == i12 && this.K.get(5) == i11) {
            return;
        }
        this.K.set(i10, i11, i12);
        if (this.K.before(this.I)) {
            this.K.setTimeInMillis(this.I.getTimeInMillis());
        } else if (this.K.after(this.J)) {
            this.K.setTimeInMillis(this.J.getTimeInMillis());
        }
        post(new u(this, 15));
    }

    public void setDate(long j10) {
        this.L.setTimeInMillis(j10);
        h(this.L.get(1), this.L.get(2), this.L.get(5));
    }

    public void setDatePickerFormat(String str) {
        if (TextUtils.isEmpty(str)) {
            str = new String(DateFormat.getDateFormatOrder(getContext()));
        }
        if (TextUtils.equals(this.z, str)) {
            return;
        }
        this.z = str;
        fi.iki.elonen.f fVar = this.H;
        String bestDateTimePattern = DateFormat.getBestDateTimePattern((Locale) fVar.f15416i, str);
        if (TextUtils.isEmpty(bestDateTimePattern)) {
            bestDateTimePattern = "MM/dd/yyyy";
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        char[] cArr = {GMTDateParser.YEAR, 'y', GMTDateParser.MONTH, GMTDateParser.MINUTES, 'D', GMTDateParser.DAY_OF_MONTH};
        boolean z = false;
        char c10 = 0;
        for (int i10 = 0; i10 < bestDateTimePattern.length(); i10++) {
            char cCharAt = bestDateTimePattern.charAt(i10);
            if (cCharAt != ' ') {
                if (cCharAt != '\'') {
                    if (!z) {
                        int i11 = 0;
                        while (true) {
                            if (i11 >= 6) {
                                sb2.append(cCharAt);
                                break;
                            } else if (cCharAt != cArr[i11]) {
                                i11++;
                            } else if (cCharAt != c10) {
                                arrayList.add(sb2.toString());
                                sb2.setLength(0);
                            }
                        }
                    } else {
                        sb2.append(cCharAt);
                    }
                    c10 = cCharAt;
                } else if (z) {
                    z = false;
                } else {
                    sb2.setLength(0);
                    z = true;
                }
            }
        }
        arrayList.add(sb2.toString());
        if (arrayList.size() != str.length() + 1) {
            throw new IllegalStateException("Separators size: " + arrayList.size() + " must equal the size of datePickerFormat: " + str.length() + " + 1");
        }
        setSeparators(arrayList);
        this.B = null;
        this.A = null;
        this.C = null;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        String upperCase = str.toUpperCase((Locale) fVar.f15416i);
        ArrayList arrayList2 = new ArrayList(3);
        for (int i12 = 0; i12 < upperCase.length(); i12++) {
            char cCharAt2 = upperCase.charAt(i12);
            if (cCharAt2 == 'D') {
                if (this.B != null) {
                    throw new IllegalArgumentException("datePicker format error");
                }
                f fVar2 = new f();
                this.B = fVar2;
                arrayList2.add(fVar2);
                this.B.f20170e = "%02d";
                this.E = i12;
            } else if (cCharAt2 != 'M') {
                if (cCharAt2 != 'Y') {
                    throw new IllegalArgumentException("datePicker format error");
                }
                if (this.C != null) {
                    throw new IllegalArgumentException("datePicker format error");
                }
                f fVar3 = new f();
                this.C = fVar3;
                arrayList2.add(fVar3);
                this.F = i12;
                this.C.f20170e = "%d";
            } else {
                if (this.A != null) {
                    throw new IllegalArgumentException("datePicker format error");
                }
                f fVar4 = new f();
                this.A = fVar4;
                arrayList2.add(fVar4);
                this.A.f20169d = (String[]) fVar.f15417l;
                this.D = i12;
            }
        }
        setColumns(arrayList2);
        post(new u(this, 15));
    }

    public void setMaxDate(long j10) {
        this.L.setTimeInMillis(j10);
        if (this.L.get(1) != this.J.get(1) || this.L.get(6) == this.J.get(6)) {
            this.J.setTimeInMillis(j10);
            if (this.K.after(this.J)) {
                this.K.setTimeInMillis(this.J.getTimeInMillis());
            }
            post(new u(this, 15));
        }
    }

    public void setMinDate(long j10) {
        this.L.setTimeInMillis(j10);
        if (this.L.get(1) != this.I.get(1) || this.L.get(6) == this.I.get(6)) {
            this.I.setTimeInMillis(j10);
            if (this.K.before(this.I)) {
                this.K.setTimeInMillis(this.I.getTimeInMillis());
            }
            post(new u(this, 15));
        }
    }
}
