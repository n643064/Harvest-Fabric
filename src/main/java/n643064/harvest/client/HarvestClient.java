package n643064.harvest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class HarvestClient implements ClientModInitializer {

    public static MinecraftClient client;
    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();
    }
}
