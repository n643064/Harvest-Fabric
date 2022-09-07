package n643064.harvest.client;

import n643064.harvest.Harvest;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class HarvestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Harvest.register();
    }
}
