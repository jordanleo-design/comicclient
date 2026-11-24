# Comic Client (Fabric, Minecraft 26.3)

## Get the JAR
Option A (no setup): push this folder to a new GitHub repo -> Actions tab -> "Build Comic Client" -> download artifact `comicclient-jar`
(use the jar that does NOT end in -sources).
Option B (local): install JDK 25 + Gradle 9.6.0, run `gradle build`, jar is in build/libs/.

## Install
Fabric Loader >= 0.19.5 + Fabric API for 26.3, put comicclient-1.0.0.jar in `mods/`.
RIGHT SHIFT = module menu, C = zoom. Config: config/comicclient.json

## Modules
HUD: FPS, CPS, Ping, Coordinates, Direction, Clock, Keystrokes, Armor Status, Potion Effects, Memory, Speed, Minimap
QoL: Toggle Sprint, Fullbright, Zoom, Low Fire
Performance: Performance Mode (render/sim distance, particles, shadows, vsync off, uncapped FPS)
PvP: Crystal Optimizer (off by default; may be punished by server anti-cheat)

## Performance tips
Comic Client can only change settings. For big FPS gains also install Sodium (+ Lithium/Entity Culling if available for 26.3),
and give Java 4-6 GB RAM.
