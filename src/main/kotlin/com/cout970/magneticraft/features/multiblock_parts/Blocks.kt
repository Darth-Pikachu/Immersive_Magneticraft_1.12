package com.cout970.magneticraft.features.multiblock_parts

import com.cout970.magneticraft.misc.CreativeTabMg
import com.cout970.magneticraft.misc.RegisterBlocks
import com.cout970.magneticraft.systems.blocks.BlockBase
import com.cout970.magneticraft.systems.blocks.BlockBuilder
import com.cout970.magneticraft.systems.blocks.IBlockMaker
import com.cout970.magneticraft.systems.blocks.IStatesEnum
import com.cout970.magneticraft.systems.itemblocks.itemBlockListOf
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.IProperty
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemBlock
import net.minecraft.util.IStringSerializable

/**
 * Created by cout970 on 2017/06/13.
 */
@RegisterBlocks
object Blocks : IBlockMaker {

    val PROPERTY_PART_TYPE: PropertyEnum<PartType> =
        PropertyEnum.create("part_type", PartType::class.java)

    lateinit var parts: BlockBase private set

    override fun initBlocks(): List<Pair<Block, ItemBlock>> {
        val builder = BlockBuilder().apply {
            material = Material.IRON
            creativeTab = CreativeTabMg
        }

        parts = builder.withName("multiblock_parts").copy {
            states = PartType.values().toList()
        }.build()

        return itemBlockListOf(parts)
    }

    enum class PartType(override val stateName: String,
                        override val isVisible: Boolean) : IStatesEnum, IStringSerializable {

        BASE("base", true),
        GRATE("grate", true),
        STRIPED("striped", true),
        CORRUGATED_IRON("corrugated_iron", true);

        override fun getName() = name.toLowerCase()
        override val properties: List<IProperty<*>> get() = listOf(PROPERTY_PART_TYPE)

        override fun getBlockState(block: Block): IBlockState {
            return block.defaultState.withProperty(PROPERTY_PART_TYPE, this)
        }
    }
}
