package net.somepixels;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMaracujaPlugin extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (command.getName().equalsIgnoreCase("goodboy")) {

			if (sender instanceof Player) {

				int wolfCount = 0;

				// Get all entities
				Player player = (Player) sender;
				Iterator<Entity> entities = player.getWorld().getEntities()
						.iterator();

				// Find player wolves
				while (entities.hasNext()) {
					Entity entity = (Entity) entities.next();

					// Is it angry?
					if (entity instanceof Wolf
							&& player.equals(((Wolf) entity).getOwner())) {

						// Calm it down!
						((Wolf) entity).setAngry(false);
						wolfCount++;
					}
				}

				// Tell user what we did
				if (wolfCount > 0) {
					player.sendMessage(ChatColor.GOLD + "Your " + wolfCount
							+ " dog(s) are calm now! =D");
				} else {
					player.sendMessage(ChatColor.GOLD
							+ "Sorry, couldn't find any dog that you own!");
				}

				return true;

			} else {
				sender.sendMessage("This command can only be run by a player.");
			}
		}

		return false;
	}

}
