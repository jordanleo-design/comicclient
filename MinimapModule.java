package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

/** Top-down terrain minimap, north-up. Data is resampled every 10 ticks to stay cheap. */
public class MinimapModule extends Module {
    public static final int RADIUS = 24, CELLS = RADIUS * 2 + 1, CELL_PX = 2;
    public final int[] colors = new int[CELLS * CELLS];
    private int ticks;

    public MinimapModule() { super("Minimap", "Top-right terrain minimap", true); }

    @Override public void onTick(Minecraft mc) {
        ClientLevel level = mc.level;
        if (level == null || mc.player == null || ticks++ % 10 != 0) return;
        int px = mc.player.getBlockX(), pz = mc.player.getBlockZ(), py = mc.player.getBlockY();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dz = -RADIUS; dz <= RADIUS; dz++) {
            for (int dx = -RADIUS; dx <= RADIUS; dx++) {
                int x = px + dx, z = pz + dz, idx = (dz + RADIUS) * CELLS + (dx + RADIUS);
                if (!level.hasChunk(x >> 4, z >> 4)) { colors[idx] = 0xFF101010; continue; }
                int h = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
                pos.set(x, h - 1, z);
                BlockState st = level.getBlockState(pos);
                int rgb = st.getMapColor(level, pos).col;
                if (rgb == 0) { colors[idx] = 0xFF101010; continue; }
                int shade = h > py + 2 ? 255 : h < py - 2 ? 190 : 225; // higher = lighter
                int r = ((rgb >> 16) & 255) * shade / 255, g = ((rgb >> 8) & 255) * shade / 255, b = (rgb & 255) * shade / 255;
                colors[idx] = 0xFF000000 | (r << 16) | (g << 8) | b;
            }
        }
    }
}
