import getTypeScriptConfig from '../../scripts/rollup';

export default getTypeScriptConfig({
  packageName: 'aes',
  entryFile: 'src/index.ts',
  inlineDynamicImports: true,
});
