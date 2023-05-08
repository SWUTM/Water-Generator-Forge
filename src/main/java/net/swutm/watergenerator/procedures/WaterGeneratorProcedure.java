package net.swutm.watergenerator.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Map;

@Mod.EventBusSubscriber
public class WaterGeneratorProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getPlayer().getUsedItemHand())
			return;
		execute(event, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.OAK_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.SPRUCE_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BIRCH_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.JUNGLE_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.ACACIA_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.DARK_OAK_LEAVES)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.COAL) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = Blocks.WATER.defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
					if (_property != null && _bs.getValue(_property) != null)
						try {
							_bs = _bs.setValue(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(Items.COAL);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		} else if (((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.OAK_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.SPRUCE_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BIRCH_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.JUNGLE_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.ACACIA_LEAVES
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.DARK_OAK_LEAVES)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.CHARCOAL) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = Blocks.WATER.defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
					if (_property != null && _bs.getValue(_property) != null)
						try {
							_bs = _bs.setValue(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(Items.CHARCOAL);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
						_player.inventoryMenu.getCraftSlots());
			}
		}
	}
}
