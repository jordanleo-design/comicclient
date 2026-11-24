package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.*;

public class CpsModule extends Module {
    private final Deque<Long> clicks = new ArrayDeque<>();
    private boolean wasDown;
    public CpsModule() { super("CPS", "Clicks per second (tick-sampled)", true); }

    @Override public void onTick(Minecraft mc) {
        boolean down = mc.options.keyAttack.isDown();
        if (down && !wasDown) clicks.add(System.currentTimeMillis());
        wasDown = down;
        long now = System.currentTimeMillis();
        while (!clicks.isEmpty() && now - clicks.peekFirst() > 1000) clicks.pollFirst();
    }
    @Override public List<HudLine> hudLines(Minecraft mc) { return List.of(new HudLine("CPS: " + clicks.size())); }
}
