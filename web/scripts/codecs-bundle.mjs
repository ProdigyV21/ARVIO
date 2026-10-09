import fs from 'node:fs/promises';
import path from 'node:path';
import { pathToFileURL } from 'node:url';
import { createRequire } from 'node:module';

const root = path.resolve(process.argv[2]);
const require = createRequire(path.join(root, 'package.json'));
const esbuild = require('esbuild');
const pluginModule = await import(pathToFileURL(require.resolve('esbuild-plugin-external-global')).href);
const { inlineWorkerPlugin } = await import(pathToFileURL(path.join(root, 'scripts/esbuild/inlined-workers.ts')).href);
const plugin = pluginModule.default ?? pluginModule;
process.chdir(root);
const banner = `/*!
 * Copyright (c) 2026-present, Vanilagy and contributors.
 * Mediabunny Source Code Form is governed by Mozilla Public License 2.0.
 * This ARVIO rebuild embeds FFmpeg 8.1.3 under LGPL-2.1-or-later.
 * Corresponding sources, compiler pins and relink objects are supplied in
 * /distribution-sources/codecs/. No additional restrictions on modification.
 */`;
for (const [name, globalName] of [['ac3', 'MediabunnyAc3'], ['dts', 'MediabunnyDts'], ['aac-encoder', 'MediabunnyAacEncoder']]) {
  await fs.mkdir(`packages/${name}/dist/bundles`, { recursive: true });
  for (const format of ['esm', 'iife']) {
    for (const minify of [false, true]) {
      await esbuild.build({
        absWorkingDir: root,
        entryPoints: [`packages/${name}/src/index.ts`], bundle: true, target: 'es2021',
        format, globalName: format === 'iife' ? globalName : undefined, minify,
        outfile: `packages/${name}/dist/bundles/mediabunny-${name}${minify ? '.min' : ''}.${format === 'esm' ? 'mjs' : 'js'}`,
        external: format === 'esm' ? ['mediabunny'] : undefined,
        plugins: [
          ...(format === 'iife' ? [plugin.externalGlobalPlugin({ mediabunny: 'Mediabunny' })] : []),
          inlineWorkerPlugin({ define: { 'import.meta.url': '""' }, legalComments: 'none' }),
        ],
        banner: { js: banner }, legalComments: 'none',
        footer: format === 'iife' ? { js: `if(typeof module === "object" && typeof module.exports === "object")Object.assign(module.exports,${globalName});` } : undefined,
      });
    }
  }
}
