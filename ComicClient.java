package dev.comic;

import com.mojang.blaze3d.platform.InputConstants;
import dev.comic.hud.HudRenderer;
import dev.comic.module.ModuleManager;
import dev.comic.screen.ModulesScreen;
import dev.comic.screen.ComicTitleScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import dev.comic.module.impl.CrystalOptimizerModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.Identifier;

public class ComicClient implements ClientModInitializer {
    public static final String ID = "comicclient";
    public static boolean customTitleScreen = true;
    public static KeyMapping openMenuKey;
    public static KeyMapping zoomKey;
    public static ModuleManager modules;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category cat = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(ID, "main"));
        openMenuKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping("key.comicclient.menu", InputConstants.KEY_RSHIFT, cat));
        zoomKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping("key.comicclient.zoom", InputConstants.KEY_C, cat));

        modules = new ModuleManager();
        modules.load();
        HudRenderer.register();

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hit) -> {
            if (CrystalOptimizerModule.active && world.isClientSide() && entity instanceof EndCrystal) {
                entity.discard(); // client-side only; attack packet still goes out
            }
            return InteractionResult.PASS;
        });

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            // Swap vanilla title screen for ours (no mixin needed)
            if (customTitleScreen && mc.gui.screen() instanceof TitleScreen) {
                mc.gui.setScreen(new ComicTitleScreen());
                return;
            }
            while (openMenuKey.consumeClick()) {
                if (mc.gui.screen() == null) mc.gui.setScreen(new ModulesScreen(null));
            }
            modules.tick(mc);
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(mc -> modules.save());
    }
}
