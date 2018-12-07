package com.sapient.ibench;

import com.sapient.ibench.handlers.GuiHandler;
import com.sapient.ibench.init.ModBlocks;
import com.sapient.ibench.init.ModItems;
import com.sapient.ibench.init.ModTileEntities;
import com.sapient.ibench.init.Recipes;
import com.sapient.ibench.network.PacketHandler;
import com.sapient.ibench.proxy.CommonProxy;
import com.sapient.ibench.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION,
        guiFactory = Reference.GUI_FACTORY_CLASS,
        dependencies = "required-after:forge@[14.23.5.2768,)", useMetadata = true)
public class iBench {

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    public static final CreativeTabs creativeTab = new CreativeTabs(Reference.MOD_NAME) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLD_INGOT);
        }
    };

    @Mod.Instance(Reference.MOD_ID)
    public static iBench instance;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        proxy.init();
        ModItems.init();
        ModBlocks.init();
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        ModTileEntities.init();

        Recipes.init();
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
