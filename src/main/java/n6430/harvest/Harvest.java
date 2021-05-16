package n6430.harvest;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class Harvest {
    public static void register()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if (client.player != null && client.mouse.wasRightButtonClicked())
            {
                HitResult TargetHit = client.crosshairTarget;
                if (TargetHit.getType() == HitResult.Type.BLOCK)
                {
                    BlockPos pos = ((BlockHitResult) TargetHit).getBlockPos();
                    Block block = client.world.getBlockState(pos).getBlock();
                    if (block instanceof CropBlock)
                    {
                        if ( ((CropBlock) block).isMature(client.world.getBlockState(pos)) )
                        {
                            client.interactionManager.attackBlock(pos, ((BlockHitResult) TargetHit).getSide());
                        }
                    }
                }
            }
        });
    }
}
