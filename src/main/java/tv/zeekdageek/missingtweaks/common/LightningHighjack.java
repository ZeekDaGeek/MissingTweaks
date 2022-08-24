package tv.zeekdageek.missingtweaks.common;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import tv.zeekdageek.missingtweaks.LoggerMT;
import tv.zeekdageek.missingtweaks.entities.EntityFireRemover;

import java.util.ArrayList;

/**
 * Backport from LightningTweaks 1.12.* by ItsPonks under the MIT License.
 * https://github.com/ItsPonks/Lightning-Tweaks
 */
public class LightningHighjack {

    /**
     * Creates an {@link ArrayList} that redirects {@link Entity Entities} when they are added.
     *
     * @param world the {@link World} that the {@link Entity} is being added to
     * @return a {@link ArrayList} that replaces {@link World#weatherEffects}
     */
    public static ArrayList<Entity> weatherEffectArray(World world) {

        return new ArrayList<Entity>(world.weatherEffects) {
            @Override
            public boolean add(Entity entity) {

                if (entity instanceof EntityLightningBolt) {
                    super.add(new EntityFireRemover(world, entity));

                    return super.add(entity);
                }

                return super.add(entity);

            }
        };

    }

    /* Sadly forcefully patching the EntityLightingBolt causes side effects with patchedBolt.getClass()
     * I hate deleting code that I've written. :(
    public static Entity patchedLightningUpdate(World world, Entity entity) {

        int x = entity.serverPosX;
        int y = entity.serverPosY;
        int z = entity.serverPosZ;

        return new EntityLightningBolt(world, x, y, z) {
            @Override
            public void onUpdate() {

                super.onUpdate();

                int x = this.serverPosX;
                int y = this.serverPosY;
                int z = this.serverPosZ;

                if (world.getBlock(x, y, z) instanceof BlockFire) {
                    world.setBlockToAir(x, y, z);
                }

            }

        };

    }
     */

    /**
     * Called when a {@link World} is first loaded. It changes {@link World#weatherEffects} to the {@link ArrayList}
     * returned from {@link #weatherEffectArray(World)}. The new {@link ArrayList} catches {@link Entity Entities} when
     * {@link ArrayList#add(Object)} is called.
     *
     * @param event the {@link World} that was loaded
     */
    @SubscribeEvent
    public void onLoad(WorldEvent.Load event) {
        World world = event.world;
        if (!world.isRemote) {

            ObfuscationReflectionHelper.setPrivateValue(World.class, world, weatherEffectArray(world), "weatherEffects", "field_73007_j");

            LoggerMT.log("Patched weatherEffects in dimension " + world.provider.getDimensionName());

        }
    }

}
