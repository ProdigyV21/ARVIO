INSERT INTO "planets" ("name", "mass_kg", "temperature_celsius") VALUES
  ('Mercury', 3.3011e23, 167),
  ('Venus', 4.8675e24, 464),
  ('Earth', 5.97237e24, 15),
  ('Mars', 6.4171e23, -65)
ON CONFLICT DO NOTHING;
