package dev.comic.module;

import com.google.gson.*;
import dev.comic.module.impl.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

import java.nio.file.*;
import java.util.*;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    private final Path file = FabricLoader.getInstance().getConfigDir().resolve("comicclient.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public ModuleManager() {
        modules.add(new FpsModule());
        modules.add(new CpsModule());
        modules.add(new PingModule());
        modules.add(new CoordsModule());
        modules.add(new DirectionModule());
        modules.add(new ClockModule());
        modules.add(new KeystrokesModule());
        modules.add(new ArmorStatusModule());
        modules.add(new ToggleSprintModule());
        modules.add(new FullbrightModule());
        modules.add(new ZoomModule());
        modules.add(new MemoryModule());
        modules.add(new SpeedModule());
        modules.add(new PotionEffectsModule());
        modules.add(new MinimapModule());
        modules.add(new LowFireModule());
        modules.add(new CrystalOptimizerModule());
        modules.add(new PerformanceModule());
    }

    public List<Module> all() { return modules; }

    public Module get(String name) {
        for (Module m : modules) if (m.name.equals(name)) return m;
        throw new IllegalArgumentException(name);
    }

    public void tick(Minecraft mc) {
        for (Module m : modules) {
            if (m.enabled || m instanceof ZoomModule) m.onTick(mc);
        }
    }

    public void load() {
        try {
            if (!Files.exists(file)) return;
            JsonObject o = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            for (Module m : modules) if (o.has(m.name)) m.enabled = o.get(m.name).getAsBoolean();
            LowFireModule.active = get("Low Fire").enabled;
            CrystalOptimizerModule.active = get("Crystal Optimizer").enabled;
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void save() {
        try {
            JsonObject o = new JsonObject();
            for (Module m : modules) o.addProperty(m.name, m.enabled);
            Files.writeString(file, GSON.toJson(o));
        } catch (Exception e) { e.printStackTrace(); }
    }
}
