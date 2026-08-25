package com.google.android.gms.cast.framework.media.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.core.content.b;
import com.google.android.gms.cast.internal.Logger;
import j0.a;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzo {
    private static final Logger zza = new Logger("WidgetUtil");

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Bitmap zza(Context context, Bitmap bitmap, float f10, float f11) {
        Logger logger = zza;
        logger.d("Begin blurring bitmap %s, original width = %d, original height = %d.", bitmap, Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
        int iRound = Math.round(bitmap.getWidth() * 0.25f);
        int iRound2 = Math.round(bitmap.getHeight() * 0.25f);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, iRound, iRound2, false);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, bitmapCreateScaledBitmap.getConfig());
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, allocationCreateFromBitmap.getElement());
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(7.5f);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        allocationCreateTyped.copyTo(bitmapCreateBitmap);
        renderScriptCreate.destroy();
        logger.d("End blurring bitmap %s, original width = %d, original height = %d.", bitmapCreateScaledBitmap, Integer.valueOf(iRound), Integer.valueOf(iRound2));
        return bitmapCreateBitmap;
    }

    public static Drawable zzb(Context context, int i10, int i11) {
        return zzd(context, i10, i11, R.attr.colorForeground, 0);
    }

    public static Drawable zzc(Context context, int i10, int i11) {
        return zzd(context, i10, i11, 0, R.color.white);
    }

    private static Drawable zzd(Context context, int i10, int i11, int i12, int i13) {
        int iA;
        ColorStateList colorStateList;
        Drawable drawableMutate = context.getResources().getDrawable(i11).mutate();
        a.i(drawableMutate, PorterDuff.Mode.SRC_IN);
        if (i10 != 0) {
            colorStateList = d.C(context, i10);
        } else {
            if (i12 != 0) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i12});
                iA = typedArrayObtainStyledAttributes.getColor(0, 0);
                typedArrayObtainStyledAttributes.recycle();
            } else {
                iA = b.a(context, i13);
            }
            colorStateList = new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}, new int[]{-16842910}}, new int[]{iA, androidx.core.graphics.a.e(iA, 128)});
        }
        a.h(drawableMutate, colorStateList);
        return drawableMutate;
    }
}
