package tv.zeekdageek.missingtweaks.common;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import tv.zeekdageek.missingtweaks.MissingTweaks;

import java.util.Arrays;
import java.util.List;

public class CommandHandler extends CommandBase
{
    private final String CommandName;

    public CommandHandler(String assignedName) {
        if (assignedName.isEmpty()) {
            CommandName = "missingtweaks";
        } else {
            CommandName = assignedName;
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public String getCommandName() {
        return CommandName;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/missingtweaks [smite]";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("mt");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        World world = sender.getEntityWorld();

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("smite") || args[0].equalsIgnoreCase("s")) {
                ChunkCoordinates pCoords = sender.getPlayerCoordinates();

                world.addWeatherEffect(new EntityLightningBolt(world, pCoords.posX, pCoords.posY, pCoords.posZ));

                sender.addChatMessage(new ChatComponentText(String.format("[%s] Test lightning spawned.", MissingTweaks.NAME)));
            }
        } else {
            throw new WrongUsageException(String.format("/%s [smite]", CommandName));
        }

    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
