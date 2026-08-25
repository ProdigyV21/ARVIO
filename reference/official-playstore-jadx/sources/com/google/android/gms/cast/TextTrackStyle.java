package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import androidx.media3.extractor.text.ttml.TtmlNode;
import c4.e;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class TextTrackStyle extends a {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final Parcelable.Creator<TextTrackStyle> CREATOR = new zzdf();
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    String zza;
    private float zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private String zzj;
    private int zzk;
    private int zzl;
    private JSONObject zzm;

    public TextTrackStyle(float f10, int i10, int i11, int i12, int i13, int i14, int i15, int i16, String str, int i17, int i18, String str2) {
        this.zzb = f10;
        this.zzc = i10;
        this.zzd = i11;
        this.zze = i12;
        this.zzf = i13;
        this.zzg = i14;
        this.zzh = i15;
        this.zzi = i16;
        this.zzj = str;
        this.zzk = i17;
        this.zzl = i18;
        this.zza = str2;
        if (str2 == null) {
            this.zzm = null;
            return;
        }
        try {
            this.zzm = new JSONObject(this.zza);
        } catch (JSONException unused) {
            this.zzm = null;
            this.zza = null;
        }
    }

    public static TextTrackStyle fromSystemSettings(Context context) {
        TextTrackStyle textTrackStyle = new TextTrackStyle();
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        if (captioningManager != null) {
            textTrackStyle.setFontScale(captioningManager.getFontScale());
            CaptioningManager.CaptionStyle userStyle = captioningManager.getUserStyle();
            textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
            textTrackStyle.setForegroundColor(userStyle.foregroundColor);
            int i10 = userStyle.edgeType;
            if (i10 == 1) {
                textTrackStyle.setEdgeType(1);
            } else if (i10 != 2) {
                textTrackStyle.setEdgeType(0);
            } else {
                textTrackStyle.setEdgeType(2);
            }
            textTrackStyle.setEdgeColor(userStyle.edgeColor);
            Typeface typeface = userStyle.getTypeface();
            if (typeface != null) {
                if (Typeface.MONOSPACE.equals(typeface)) {
                    textTrackStyle.setFontGenericFamily(1);
                } else if (!Typeface.SANS_SERIF.equals(typeface) && Typeface.SERIF.equals(typeface)) {
                    textTrackStyle.setFontGenericFamily(2);
                } else {
                    textTrackStyle.setFontGenericFamily(0);
                }
                boolean zIsBold = typeface.isBold();
                boolean zIsItalic = typeface.isItalic();
                if (zIsBold && zIsItalic) {
                    textTrackStyle.setFontStyle(3);
                    return textTrackStyle;
                }
                if (zIsBold) {
                    textTrackStyle.setFontStyle(1);
                    return textTrackStyle;
                }
                if (zIsItalic) {
                    textTrackStyle.setFontStyle(2);
                    return textTrackStyle;
                }
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }

    private static final int zzb(String str) {
        if (str != null && str.length() == 9 && str.charAt(0) == '#') {
            try {
                return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    private static final String zzc(int i10) {
        return String.format("#%02X%02X%02X%02X", Integer.valueOf(Color.red(i10)), Integer.valueOf(Color.green(i10)), Integer.valueOf(Color.blue(i10)), Integer.valueOf(Color.alpha(i10)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) obj;
        JSONObject jSONObject = this.zzm;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = textTrackStyle.zzm;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || e.a(jSONObject, jSONObject2)) && this.zzb == textTrackStyle.zzb && this.zzc == textTrackStyle.zzc && this.zzd == textTrackStyle.zzd && this.zze == textTrackStyle.zze && this.zzf == textTrackStyle.zzf && this.zzg == textTrackStyle.zzg && this.zzh == textTrackStyle.zzh && this.zzi == textTrackStyle.zzi && CastUtils.zza(this.zzj, textTrackStyle.zzj) && this.zzk == textTrackStyle.zzk && this.zzl == textTrackStyle.zzl;
    }

    public void fromJson(JSONObject jSONObject) throws JSONException {
        int i10;
        this.zzb = (float) jSONObject.optDouble("fontScale", 1.0d);
        this.zzc = zzb(jSONObject.optString("foregroundColor"));
        this.zzd = zzb(jSONObject.optString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR));
        if (jSONObject.has("edgeType")) {
            String string = jSONObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.zze = 0;
            } else if ("OUTLINE".equals(string)) {
                this.zze = 1;
            } else if ("DROP_SHADOW".equals(string)) {
                this.zze = 2;
            } else if ("RAISED".equals(string)) {
                this.zze = 3;
            } else if ("DEPRESSED".equals(string)) {
                this.zze = 4;
            }
        }
        this.zzf = zzb(jSONObject.optString("edgeColor"));
        if (jSONObject.has("windowType")) {
            String string2 = jSONObject.getString("windowType");
            if ("NONE".equals(string2)) {
                this.zzg = 0;
            } else if ("NORMAL".equals(string2)) {
                this.zzg = 1;
            } else if ("ROUNDED_CORNERS".equals(string2)) {
                this.zzg = 2;
            }
        }
        this.zzh = zzb(jSONObject.optString("windowColor"));
        if (this.zzg == 2) {
            this.zzi = jSONObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.zzj = CastUtils.optStringOrNull(jSONObject, TtmlNode.ATTR_TTS_FONT_FAMILY);
        if (jSONObject.has("fontGenericFamily")) {
            String string3 = jSONObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string3)) {
                this.zzk = 0;
            } else if ("MONOSPACED_SANS_SERIF".equals(string3)) {
                this.zzk = 1;
            } else if ("SERIF".equals(string3)) {
                this.zzk = 2;
            } else if ("MONOSPACED_SERIF".equals(string3)) {
                this.zzk = 3;
            } else if ("CASUAL".equals(string3)) {
                this.zzk = 4;
            } else {
                if (!"CURSIVE".equals(string3)) {
                    i10 = "SMALL_CAPITALS".equals(string3) ? 6 : 5;
                }
                this.zzk = i10;
            }
        }
        if (jSONObject.has(TtmlNode.ATTR_TTS_FONT_STYLE)) {
            String string4 = jSONObject.getString(TtmlNode.ATTR_TTS_FONT_STYLE);
            if ("NORMAL".equals(string4)) {
                this.zzl = 0;
            } else if ("BOLD".equals(string4)) {
                this.zzl = 1;
            } else if ("ITALIC".equals(string4)) {
                this.zzl = 2;
            } else if ("BOLD_ITALIC".equals(string4)) {
                this.zzl = 3;
            }
        }
        this.zzm = jSONObject.optJSONObject("customData");
    }

    public int getBackgroundColor() {
        return this.zzd;
    }

    public JSONObject getCustomData() {
        return this.zzm;
    }

    public int getEdgeColor() {
        return this.zzf;
    }

    public int getEdgeType() {
        return this.zze;
    }

    public String getFontFamily() {
        return this.zzj;
    }

    public int getFontGenericFamily() {
        return this.zzk;
    }

    public float getFontScale() {
        return this.zzb;
    }

    public int getFontStyle() {
        return this.zzl;
    }

    public int getForegroundColor() {
        return this.zzc;
    }

    public int getWindowColor() {
        return this.zzh;
    }

    public int getWindowCornerRadius() {
        return this.zzi;
    }

    public int getWindowType() {
        return this.zzg;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf), Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), Integer.valueOf(this.zzi), this.zzj, Integer.valueOf(this.zzk), Integer.valueOf(this.zzl), String.valueOf(this.zzm)});
    }

    public void setBackgroundColor(int i10) {
        this.zzd = i10;
    }

    public void setCustomData(JSONObject jSONObject) {
        this.zzm = jSONObject;
    }

    public void setEdgeColor(int i10) {
        this.zzf = i10;
    }

    public void setEdgeType(int i10) {
        if (i10 < 0 || i10 > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.zze = i10;
    }

    public void setFontFamily(String str) {
        this.zzj = str;
    }

    public void setFontGenericFamily(int i10) {
        if (i10 < 0 || i10 > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.zzk = i10;
    }

    public void setFontScale(float f10) {
        this.zzb = f10;
    }

    public void setFontStyle(int i10) {
        if (i10 < 0 || i10 > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.zzl = i10;
    }

    public void setForegroundColor(int i10) {
        this.zzc = i10;
    }

    public void setWindowColor(int i10) {
        this.zzh = i10;
    }

    public void setWindowCornerRadius(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("invalid windowCornerRadius");
        }
        this.zzi = i10;
    }

    public void setWindowType(int i10) {
        if (i10 < 0 || i10 > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.zzg = i10;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzm;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        float fontScale = getFontScale();
        a.a.l0(parcel, 2, 4);
        parcel.writeFloat(fontScale);
        int foregroundColor = getForegroundColor();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(foregroundColor);
        int backgroundColor = getBackgroundColor();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(backgroundColor);
        int edgeType = getEdgeType();
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(edgeType);
        int edgeColor = getEdgeColor();
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(edgeColor);
        int windowType = getWindowType();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(windowType);
        int windowColor = getWindowColor();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(windowColor);
        int windowCornerRadius = getWindowCornerRadius();
        a.a.l0(parcel, 9, 4);
        parcel.writeInt(windowCornerRadius);
        a.a.g0(parcel, 10, getFontFamily(), false);
        int fontGenericFamily = getFontGenericFamily();
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(fontGenericFamily);
        int fontStyle = getFontStyle();
        a.a.l0(parcel, 12, 4);
        parcel.writeInt(fontStyle);
        a.a.g0(parcel, 13, this.zza, false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fontScale", this.zzb);
            int i10 = this.zzc;
            if (i10 != 0) {
                jSONObject.put("foregroundColor", zzc(i10));
            }
            int i11 = this.zzd;
            if (i11 != 0) {
                jSONObject.put(TtmlNode.ATTR_TTS_BACKGROUND_COLOR, zzc(i11));
            }
            int i12 = this.zze;
            if (i12 == 0) {
                jSONObject.put("edgeType", "NONE");
            } else if (i12 == 1) {
                jSONObject.put("edgeType", "OUTLINE");
            } else if (i12 == 2) {
                jSONObject.put("edgeType", "DROP_SHADOW");
            } else if (i12 == 3) {
                jSONObject.put("edgeType", "RAISED");
            } else if (i12 == 4) {
                jSONObject.put("edgeType", "DEPRESSED");
            }
            int i13 = this.zzf;
            if (i13 != 0) {
                jSONObject.put("edgeColor", zzc(i13));
            }
            int i14 = this.zzg;
            if (i14 == 0) {
                jSONObject.put("windowType", "NONE");
            } else if (i14 == 1) {
                jSONObject.put("windowType", "NORMAL");
            } else if (i14 == 2) {
                jSONObject.put("windowType", "ROUNDED_CORNERS");
            }
            int i15 = this.zzh;
            if (i15 != 0) {
                jSONObject.put("windowColor", zzc(i15));
            }
            if (this.zzg == 2) {
                jSONObject.put("windowRoundedCornerRadius", this.zzi);
            }
            String str = this.zzj;
            if (str != null) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_FAMILY, str);
            }
            switch (this.zzk) {
                case 0:
                    jSONObject.put("fontGenericFamily", "SANS_SERIF");
                    break;
                case 1:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
                    break;
                case 2:
                    jSONObject.put("fontGenericFamily", "SERIF");
                    break;
                case 3:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
                    break;
                case 4:
                    jSONObject.put("fontGenericFamily", "CASUAL");
                    break;
                case 5:
                    jSONObject.put("fontGenericFamily", "CURSIVE");
                    break;
                case 6:
                    jSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
                    break;
            }
            int i16 = this.zzl;
            if (i16 == 0) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "NORMAL");
            } else if (i16 == 1) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "BOLD");
            } else if (i16 == 2) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "ITALIC");
            } else if (i16 == 3) {
                jSONObject.put(TtmlNode.ATTR_TTS_FONT_STYLE, "BOLD_ITALIC");
            }
            JSONObject jSONObject2 = this.zzm;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, null, -1, -1, null);
    }
}
