CREATE TABLE IF NOT EXISTS "planets" (
  "id" serial PRIMARY KEY NOT NULL,
  "name" text NOT NULL,
  "mass_kg" double precision NOT NULL,
  "temperature_celsius" integer NOT NULL
);
