package com.cout970.magneticraft.block

import com.cout970.magneticraft.util.get
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

/**
 * Created by cout970 on 18/08/2016.
 */
object BlockAirBubble : BlockMultiState(Material.GLASS, "air_bubble") {

    lateinit var PROPERTY_DECAY: PropertyBool
        private set

    init {
        tickRandomly = true
        setLightOpacity(0)
    }

    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    override fun getMetaFromState(state: IBlockState): Int = if (PROPERTY_DECAY[state]) 1 else 0

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(PROPERTY_DECAY, meta == 1)
    }

    override fun createBlockState(): BlockStateContainer {
        PROPERTY_DECAY = PropertyBool.create("decay")
        return BlockStateContainer(this, PROPERTY_DECAY)
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random?) {
        super.updateTick(worldIn, pos, state, rand)
        if (PROPERTY_DECAY[state]) {
            worldIn.setBlockToAir(pos)
        }
    }

    override fun tickRate(worldIn: World): Int = 0
    override fun quantityDropped(random: Random): Int = 0
    override fun isOpaqueCube(state: IBlockState): Boolean = false
    override fun isFullCube(state: IBlockState): Boolean = false
    override fun isFullBlock(state: IBlockState?) = false
    override fun isVisuallyOpaque() = false

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?) = FULL_BLOCK_AABB

    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: World, pos: BlockPos): AxisAlignedBB? = NULL_AABB

    @SideOnly(Side.CLIENT)
    override fun shouldSideBeRendered(blockState: IBlockState, blockAccess: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean {
        val iblockstate = blockAccess.getBlockState(pos.offset(side))
        return if (iblockstate.block === this) false else !blockAccess.getBlockState(pos.offset(side)).doesSideBlockRendering(blockAccess, pos.offset(side), side.opposite)
    }
}