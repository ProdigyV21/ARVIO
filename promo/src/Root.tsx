import React from "react";
import { Composition, Folder } from "remotion";
import { Promo, PROMO_DURATION, SCENES } from "./Promo";
import { FPS, loadBrandFonts } from "./theme";

loadBrandFonts();

export const RemotionRoot: React.FC = () => (
  <>
    <Composition
      id="Promo"
      component={Promo}
      width={1920}
      height={1080}
      fps={FPS}
      durationInFrames={PROMO_DURATION}
    />
    <Folder name="Scenes">
      {SCENES.map((s) => (
        <Composition
          key={s.id}
          id={s.id}
          component={s.C}
          width={1920}
          height={1080}
          fps={FPS}
          durationInFrames={s.d}
        />
      ))}
    </Folder>
  </>
);
