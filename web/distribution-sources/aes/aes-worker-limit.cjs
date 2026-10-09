// Scheduling-only limit for the historical rollup-plugin-terser worker pool.
// The source, compiler options, and minification options remain unchanged.
const os = require("node:os");
const available = os.cpus.bind(os);
os.cpus = () => available().slice(0, 2);
