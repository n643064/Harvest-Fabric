package n6430.harvest.client;

import n6430.harvest.Harvest;
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
