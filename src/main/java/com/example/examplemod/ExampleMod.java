package com.example.examplemod

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String NAME = "Мой Крутой Мод";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
    }

    // При входе в мир даём подарки
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;
        
        if (!world.isRemote) {
            player.sendMessage(new TextComponentString("§b[МойМод] §aПривет, " + player.getName() + "! Лови подарки!"));
            
            ItemStack diamondSword = new ItemStack(Items.DIAMOND_SWORD);
            player.inventory.addItemStackToInventory(diamondSword);
            
            ItemStack diamonds = new ItemStack(Items.DIAMOND, 64);
            player.inventory.addItemStackToInventory(diamonds);
        }
    }

    // При ударе по блоку — МОЛНИЯ!
    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        World world = event.getWorld();
        EntityPlayer player = event.getEntityPlayer();
        
        if (!world.isRemote) {
            world.addWeatherEffect(new net.minecraft.entity.effect.EntityLightningBolt(
                world, 
                event.getPos().getX() + 0.5, 
                event.getPos().getY(), 
                event.getPos().getZ() + 0.5, 
                false
            ));
            player.sendMessage(new TextComponentString("§eБА-БАХ! Молния!"));
        }
    }
}

