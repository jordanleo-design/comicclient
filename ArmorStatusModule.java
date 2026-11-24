package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import java.util.*;

public class ArmorStatusModule extends Module {
    private static final EquipmentSlot[] SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public ArmorStatusModule() { super("Armor Status", "Armor durability readout", true); }

    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null) return List.of();
        List<HudLine> out = new ArrayList<>();
        for (EquipmentSlot s : SLOTS) {
            ItemStack st = mc.player.getItemBySlot(s);
            if (st.isEmpty() || st.getMaxDamage() <= 0) continue;
            int left = st.getMaxDamage() - st.getDamageValue();
            float pct = left / (float) st.getMaxDamage();
            int col = pct > 0.5f ? 0xFF55FF55 : pct > 0.2f ? 0xFFFFFF55 : 0xFFFF5555;
            out.add(new HudLine(st.getHoverName().getString() + " " + left + "/" + st.getMaxDamage(), col));
        }
        return out;
    }
}
