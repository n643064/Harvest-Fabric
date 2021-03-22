package n6430.harvest;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Harvest {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.mouse.wasRightButtonClicked() && client.player != null) {
                HitResult TargetHit = client.crosshairTarget;
                if (TargetHit.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult tblock = (BlockHitResult) TargetHit;
                    BlockPos block_pos = tblock.getBlockPos();
                    Block block = client.world.getBlockState(block_pos).getBlock();
                    if (block instanceof CropBlock) {
                        CropBlock cblock = (CropBlock) block;
                        if (cblock.isMature(client.world.getBlockState(block_pos))) {
                            client.interactionManager.attackBlock(block_pos, Direction.UP);
                        }
                    }
                }
            }
        });
    }
}
