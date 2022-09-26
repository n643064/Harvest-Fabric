package n643064.harvest.mixin;

import n643064.harvest.client.HarvestClient;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin
{
    @Inject(method = "onMouseButton", at = @At("TAIL"))
    public void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci)
    {
        MinecraftClient client = HarvestClient.client;
        if (client.player != null && !client.player.isSneaking() && button == 1 && action == 1)
        {
            harvest(client);
        }
    }

    private void harvest(MinecraftClient client)
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
}
