package tv.zeekdageek.missingtweaks.entities;

import net.minecraft.block.BlockFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tv.zeekdageek.missingtweaks.LoggerMT;

public class EntityFireRemover extends EntityWeatherEffect {

    private final Entity StalkEntity;


    public EntityFireRemover(World world, Entity stalk) {
        super(world);
        this.setLocationAndAngles(stalk.posX, stalk.posY, stalk.posZ, 0.0f, 0.0f);
        this.StalkEntity = stalk;

        removeFire();
    }

    /**
     * Removes newly created fire without touching existing fire.
     */
    public void removeFire() {
        World world = this.worldObj;
        int x, y, z;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    x = MathHelper.floor_double(this.posX) + i;
                    y = MathHelper.floor_double(this.posY) + j;
                    z = MathHelper.floor_double(this.posZ) + k;

                    if (world.getBlock(x, y, z) instanceof BlockFire) {
                            world.setBlockToAir(x, y, z);
                            LoggerMT.vlog("Removed a fire [%d, %d, %d]", x, y, z);
                        }
                }
            }
        }

        // "Fire created on lightning bolt construction removed");
    }


    public void onUpdate() {
        super.onUpdate();

        this.setLocationAndAngles(StalkEntity.posX, StalkEntity.posY, StalkEntity.posZ, 0.0f, 0.0f);

        // On first run remove construction fire.
        if (!this.worldObj.isRemote)
            removeFire();

        if (StalkEntity.isDead) {
            this.setDead();
            LoggerMT.vlog("I think the entity I was stalking died. :(");
        }

    }

    protected void entityInit() {}
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}

}
