package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.cast.MediaError;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class l extends o {
    public static Font o(FontFamily fontFamily, int i10) {
        FontStyle fontStyle = new FontStyle((i10 & 1) != 0 ? 700 : MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, (i10 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int iP = p(fontStyle, font.getStyle());
        for (int i11 = 1; i11 < fontFamily.getSize(); i11++) {
            Font font2 = fontFamily.getFont(i11);
            int iP2 = p(fontStyle, font2.getStyle());
            if (iP2 < iP) {
                font = font2;
                iP = iP2;
            }
        }
        return font;
    }

    public static int p(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    @Override // androidx.core.graphics.o
    public final Typeface f(Context context, i0.h hVar, Resources resources, int i10) {
        try {
            FontFamily.Builder builder = null;
            for (i0.i iVar : hVar.f15988a) {
                try {
                    Font fontBuild = new Font.Builder(resources, iVar.f15994f).setWeight(iVar.f15990b).setSlant(iVar.f15991c ? 1 : 0).setTtcIndex(iVar.f15993e).setFontVariationSettings(iVar.f15992d).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily fontFamilyBuild = builder.build();
            return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(o(fontFamilyBuild, i10).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.o
    public final Typeface g(Context context, androidx.core.provider.k[] kVarArr, int i10) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily.Builder builder = null;
            for (androidx.core.provider.k kVar : kVarArr) {
                try {
                    parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(kVar.f2161a, "r", null);
                } catch (IOException unused) {
                }
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                    }
                } else {
                    try {
                        Font fontBuild = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(kVar.f2163c).setSlant(kVar.f2164d ? 1 : 0).setTtcIndex(kVar.f2162b).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(fontBuild);
                        } else {
                            builder.addFont(fontBuild);
                        }
                    } catch (Throwable th) {
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            if (builder != null) {
                FontFamily fontFamilyBuild = builder.build();
                return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(o(fontFamilyBuild, i10).getStyle()).build();
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    @Override // androidx.core.graphics.o
    public final Typeface h(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.o
    public final Typeface i(Context context, Resources resources, int i10, String str, int i11) {
        try {
            Font fontBuild = new Font.Builder(resources, i10).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.o
    public final androidx.core.provider.k l(androidx.core.provider.k[] kVarArr, int i10) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
