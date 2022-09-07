package n643064.harvest;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Harvest {
    public static void register()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if (client.player != null && !client.player.isSneaking() && client.mouse.wasRightButtonClicked())
            {
                HitResult targetHit = client.crosshairTarget;
                if (targetHit != null && targetHit.getType() == HitResult.Type.BLOCK)
                {
                    assert client.world != null;
                    assert client.interactionManager != null;

                    final Direction side = ((BlockHitResult) targetHit).getSide();
                    final BlockPos pos = ((BlockHitResult) targetHit).getBlockPos();
                    final Block block = client.world.getBlockState(pos).getBlock();

                    if (block instanceof CropBlock)
                    {
                        if ( ((CropBlock) block).isMature(client.world.getBlockState(pos)) )
                        {
                            client.interactionManager.attackBlock(pos, side);
                            client.player.swingHand(Hand.MAIN_HAND);
                        }
                    } else if (block instanceof NetherWartBlock)
                    {
                        if (client.world.getBlockState(pos).get(NetherWartBlock.AGE) == 3)
                        {
                            client.interactionManager.attackBlock(pos, side);
                            client.player.swingHand(Hand.MAIN_HAND);
                        }
                    }
                }
            }
        });
    }
}
