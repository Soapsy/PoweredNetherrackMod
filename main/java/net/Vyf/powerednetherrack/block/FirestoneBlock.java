package net.Vyf.powerednetherrack.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.block.AbstractFireBlock;


import java.util.Random;

import javax.annotation.Nullable;

public class FirestoneBlock extends Block {

    //getting blockstate for redstone activation
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public FirestoneBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(POWERED, Boolean.valueOf(false)));
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
       return this.getDefaultState().with(POWERED, Boolean.valueOf(context.getWorld().isBlockPowered(context.getPos())));
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
           boolean flag = state.get(POWERED);
           if (flag != worldIn.isBlockPowered(pos)) {
              if (flag) {
                 worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
              } else {
                 worldIn.setBlockState(pos, state.cycleValue(POWERED), 2);

                 BlockPos blockPosForFire = pos.up();
                 if(worldIn.isAirBlock(blockPosForFire))
                 {
                    BlockState blockstateFire = AbstractFireBlock.getFireForPlacement(worldIn, blockPosForFire);
                    worldIn.setBlockState(blockPosForFire, blockstateFire, 11);
                 }

              }
           }
  
        }
     }

     @Override
     public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (state.get(POWERED) && !worldIn.isBlockPowered(pos)) {
           worldIn.setBlockState(pos, state.cycleValue(POWERED), 2);

           worldIn.destroyBlock(pos.up(), false);
        }
  
     }


     @Override
     protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
     }
    
}