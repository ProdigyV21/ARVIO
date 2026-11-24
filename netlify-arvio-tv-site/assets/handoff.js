// The hero handoff: as the visitor scrolls, the series paused on the TV resumes on the iPad.
// Without JavaScript, with reduced motion or on narrow screens the stage shows its final state.
(function () {
  const section = document.querySelector('.handoff');
  if (!section) return;
  const wide = window.matchMedia('(min-width: 721px)');
  const reduce = window.matchMedia('(prefers-reduced-motion: reduce)');
  let paused = reduce.matches, frame = 0;
  const live = () => wide.matches && !paused;
  function update() {
    frame = 0;
    if (!live()) { section.classList.remove('handoff-live'); section.style.removeProperty('--p'); return; }
    section.classList.add('handoff-live');
    const rect = section.getBoundingClientRect();
    const travel = Math.max(1, rect.height - window.innerHeight);
    const raw = Math.min(1, Math.max(0, (-rect.top + window.innerHeight * 0.1) / travel));
    const eased = raw < 0.5 ? 4 * raw * raw * raw : 1 - Math.pow(-2 * raw + 2, 3) / 2;
    section.style.setProperty('--p', eased.toFixed(4));
  }
  const schedule = () => { if (!frame) frame = requestAnimationFrame(update); };
  window.addEventListener('scroll', schedule, { passive: true });
  window.addEventListener('resize', schedule);
  wide.addEventListener('change', schedule);
  document.addEventListener('arvio:motionchange', event => { paused = event.detail.paused; schedule(); });
  update();
})();
