package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import java.util.List;

public class PingModule extends Module {
    public PingModule() { super("Ping", "Shows server latency", true); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null || mc.getConnection() == null) return List.of();
        PlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
        return info == null ? List.of() : List.of(new HudLine("Ping: " + info.getLatency() + " ms"));
    }
}
