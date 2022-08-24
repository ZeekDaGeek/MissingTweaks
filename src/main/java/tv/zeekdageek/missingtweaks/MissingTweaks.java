package tv.zeekdageek.missingtweaks;

//import com.zuxelus.gt6orehelper.config.ConfigHandler;
//import com.zuxelus.gt6orehelper.proxy.ServerProxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import tv.zeekdageek.missingtweaks.common.CommandHandler;
import tv.zeekdageek.missingtweaks.common.LightningHighjack;

@Mod(
        modid = Tags.MODID,
        name = Tags.MODNAME,
        version = Tags.VERSION,
        dependencies = "",
        guiFactory = "",
        acceptedMinecraftVersions = "[1.7.10]",
        acceptableRemoteVersions = "*"
)
public class MissingTweaks {
    public static final String NAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID;
    public static final String VERSION = Tags.VERSION;

    /*
    @SidedProxy(clientSide = "com.zuxelus.gt6orehelper.proxy.ClientProxy", serverSide = "com.zuxelus.gt6orehelper.proxy.ServerProxy")
    public static ServerProxy proxy;

    public static ConfigHandler config;
    public static boolean useWaypoints;
*/
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //proxy.loadConfig(event);
        //if (event.getSide() == Side.SERVER) {

        MinecraftForge.EVENT_BUS.register(new LightningHighjack());

        //}

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandHandler("missingtweaks"));
        event.registerServerCommand(new CommandHandler("mt"));

    }

    /*
    @EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        if (event.getSide() == Side.CLIENT)
            new OreHelper();
        proxy.registerEventHandlers();
        useWaypoints = Loader.isModLoaded("journeymap") && config.useWaypoints;
    }
     */
}
