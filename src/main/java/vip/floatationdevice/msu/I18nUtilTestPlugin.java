package vip.floatationdevice.msu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static vip.floatationdevice.msu.I18nUtil.translate;

public class I18nUtilTestPlugin extends JavaPlugin implements CommandExecutor
{
    YamlConfiguration cfg;

    void loadConfig()
    {
        File cfgFile = new File(getDataFolder(), "config.yml");
        if(!cfgFile.exists()) saveDefaultConfig();
        cfg = YamlConfiguration.loadConfiguration(cfgFile);
        I18nUtil.setLanguage(I18nUtilTestPlugin.class, cfg.getString("language", "en_US"));
    }

    @Override
    public void onEnable()
    {
        loadConfig();
        getCommand("i18ntest").setExecutor(this);
        getLogger().info("Language: " + I18nUtil.getLanguage() + " (" + cfg.getString("language", "en_US") + ") by " + I18nUtil.getLanguageFileContributor());
        getLogger().info(translate("plugin-loaded"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(translate("test-message"));
            return true;
        }
        else if(args.length == 1 && args[0].equals("reload"))
        {
            loadConfig();
            sender.sendMessage("Plugin reloaded\nLanguage: " + I18nUtil.getLanguage() + " (" + cfg.getString("language", "en_US") + ") by " + I18nUtil.getLanguageFileContributor());
            return true;
        }
        else if(args.length == 2 && args[0].equals("translate"))
        {
            sender.sendMessage(translate(args[1]));
            return true;
        }
        else return false;
    }

    @Override
    public void onDisable(){}
}
