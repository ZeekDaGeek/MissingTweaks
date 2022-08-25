package tv.zeekdageek.missingtweaks;

//import com.zuxelus.gt6orehelper.config.ConfigHandler;
//import com.zuxelus.gt6orehelper.proxy.ServerProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;
import tv.zeekdageek.missingtweaks.common.CommandHandler;
import tv.zeekdageek.missingtweaks.common.LightningHighjack;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.MODNAME,
        dependencies = "",
        guiFactory = "",
        acceptedMinecraftVersions = "[1.7.10]",
        acceptableRemoteVersions = "*"
)
public class MissingTweaks {
    public static final String NAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID;
    public static final String MODVERSION = Tags.VERSION;

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
