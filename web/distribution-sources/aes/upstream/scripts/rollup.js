/* eslint-disable import/no-extraneous-dependencies */
/* eslint-disable global-require */
/* eslint-disable import/no-dynamic-require */
import typescript from '@rollup/plugin-typescript';
import resolve from '@rollup/plugin-node-resolve';
import { terser } from 'rollup-plugin-terser';

export default function getTypeScriptConfig({
  packageName,
  entryFile,
  formats = ['cjs', 'umd', 'es'],
  inlineDynamicImports = false,
}) {
  return [
    // js only
    {
      input: entryFile,
      inlineDynamicImports,
      output: [
        ...formats.map((format) => ({
          name: packageName,
          file: `dist/${format}/${packageName}.js`,
          format,
        })),
        ...formats.map((format) => ({
          name: packageName,
          file: `dist/${format}/${packageName}.min.js`,
          format,
          plugins: [terser()],
        })),
      ],
      plugins: [
        typescript({
          typescript: require('typescript'),
          exclude: ['tests/*'],
        }),
        resolve({
          jsnext: true,
          main: false,
        }),
      ],
    },

    // declaration
    {
      input: entryFile,
      output: {
        dir: 'dist/typings',
      },
      plugins: [
        typescript({
          emitDeclarationOnly: true,
          declaration: true,
          outDir: 'dist/typings',
          target: 'es5',
          rootDir: 'src',
          composite: true,
          exclude: ['tests/*'],
          include: ['src/**/*'],
        }),
      ],
    },
  ];
}
